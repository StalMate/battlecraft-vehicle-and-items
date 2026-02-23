package com.battlecraft.vehicleanditems.entity.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import com.battlecraft.vehicleanditems.registry.ModBlockEntities;
import com.battlecraft.vehicleanditems.entity.blocks.interfaces.ProgressBarEntity;
import com.battlecraft.vehicleanditems.entity.blocks.components.ProgressBarComponent;
import com.battlecraft.vehicleanditems.registry.ModSounds;
import com.battlecraft.vehicleanditems.sound.LoopingSound;

public class VehicleSpawnBlockEntity extends BlockEntity implements GeoBlockEntity, ProgressBarEntity {
    private final AnimatableInstanceCache animatableCache = GeckoLibUtil.createInstanceCache(this);
    private final ProgressBarComponent progressBar = new ProgressBarComponent(100);

    private int openPhase = 0;
    private int animationTimer = 0;
    private static final int ANIMATION_DURATION = 10;

    public VehicleSpawnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VEHICLE_SPAWN_BLOCK_ENTITY.get(), pos, state);
    }

    //GeoBlockEntity
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<VehicleSpawnBlockEntity> event) {
        if (!level.isClientSide || !progressBar.isInit()) return PlayState.STOP;
        AnimationController<VehicleSpawnBlockEntity> controller = event.getController();
        if (openPhase == 0 && !progressBar.isFinished(level)) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.container.wait", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        if (openPhase == 1) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.container.open", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }

        if (!controller.hasAnimationFinished()) {
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableCache;
    }

    //ProgressBarEntity
    public float getRenderProgress(float partialTick) {
        return progressBar.getProgress(level, partialTick);
    }

    @Override
    public ProgressBarComponent getProgressBar() {
        return progressBar;
    }

    //Все просчитывать на сервере. Ничего на клиенте!
    public static void tick(Level level, BlockPos pos, BlockState state, VehicleSpawnBlockEntity blockEntity) {
        if (level.isClientSide || !blockEntity.progressBar.isInit()) return;

        if (!blockEntity.progressBar.isFinished(level)) {
            LoopingSound.play(
                    pos,
                    ModSounds.SPAWN_BLOCK_WAIT.get(),
                    SoundSource.BLOCKS,
                    2f,
                    1f,
                    () -> blockEntity.isRemoved()
                            || blockEntity.getProgressBar().isFinished(level)
            );
            return;
        }
        LoopingSound.stop(pos);
        if (blockEntity.openPhase == 0) {
            blockEntity.openPhase = 1;
            blockEntity.animationTimer = 0;

            level.playSound(null, pos, ModSounds.SPAWN_BLOCK_OPEN.get(), SoundSource.BLOCKS, 1f, 1f);

            blockEntity.setChanged();
            blockEntity.level.sendBlockUpdated(pos, state, state, 3);
        }

        if (blockEntity.openPhase == 1) {
            blockEntity.animationTimer++;
            if (blockEntity.animationTimer >= ANIMATION_DURATION) {
                blockEntity.openPhase = 2;
                blockEntity.setChanged();
                blockEntity.level.sendBlockUpdated(pos, state, state, 3);
            }
        }

        if (blockEntity.openPhase == 2) {
            level.playSound(null, pos, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1f, 1f);
            ((ServerLevel) level).sendParticles(
                    net.minecraft.core.particles.ParticleTypes.EXPLOSION_EMITTER,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    1,
                    0, 0, 0, 0
            );
            level.destroyBlock(pos, false);
        }

    }

    //Синхронизация
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        progressBar.save(tag);
        tag.putInt("OpenPhase", openPhase);
        tag.putInt("AnimationTimer", animationTimer);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progressBar.load(tag);
        openPhase = tag.getInt("OpenPhase");
        animationTimer = tag.getInt("AnimationTimer");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        progressBar.writeSyncTag(tag);
        tag.putInt("OpenPhase", openPhase);
        tag.putInt("AnimationTimer", animationTimer);
        return tag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) {
            progressBar.syncFromTag(tag);
            openPhase = tag.getInt("OpenPhase");
            animationTimer = tag.getInt("AnimationTimer");
        }
    }

}
