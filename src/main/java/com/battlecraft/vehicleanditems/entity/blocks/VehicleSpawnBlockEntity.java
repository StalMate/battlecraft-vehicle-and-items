package com.battlecraft.vehicleanditems.entity.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import com.battlecraft.vehicleanditems.registry.ModBlockEntities;
import com.battlecraft.vehicleanditems.entity.blocks.interfaces.ProgressBarEntity;
import com.battlecraft.vehicleanditems.entity.blocks.components.ProgressBarComponent;

public class VehicleSpawnBlockEntity extends BlockEntity implements GeoBlockEntity, ProgressBarEntity {
    private final AnimatableInstanceCache animatableCache = GeckoLibUtil.createInstanceCache(this);
    private final ProgressBarComponent progressBar = new ProgressBarComponent(200);

    public VehicleSpawnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VEHICLE_SPAWN_BLOCK_ENTITY.get(), pos, state);
    }

    //GeoBlockEntity
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableCache;
    }

    //ProgressBarEntity
    public float getRenderProgress(float partialTick) {
        return progressBar.getProgress(level, partialTick);
    }

    //Все просчитывать на сервере. Ничего на клиенте!
    @Override
    public void onLoad() {
        super.onLoad();
        if (level.isClientSide) return;

        progressBar.onLoad(level);
        setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, VehicleSpawnBlockEntity blockEntity) {
        if (level.isClientSide) return;

        if (blockEntity.progressBar.isFinished(level)) {
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
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progressBar.load(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        progressBar.writeSyncTag(tag);
        return tag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) {
            progressBar.syncFromTag(tag);
        }
    }

}
