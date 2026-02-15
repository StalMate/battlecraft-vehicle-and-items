package com.battlecraft.vehicleanditems.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import com.battlecraft.vehicleanditems.registry.ModBlockEntities;

public class VehicleSpawnBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation OPEN_WAIT_ANIMATION = RawAnimation.begin().thenLoop("wait");
    private static final RawAnimation OPEN_ANIMATION = RawAnimation.begin().thenPlay("open");
    private boolean playWaitAnimation = false;
    private boolean playOpenAnimation = false;

    public VehicleSpawnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VEHICLE_SPAWN_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::animationController));
    }

    private <E extends GeoBlockEntity> PlayState animationController(AnimationState<E> state) {
        if (playOpenAnimation) {
            state.getController().setAnimation(OPEN_ANIMATION);
            playWaitAnimation = false;
            playOpenAnimation = false;
            return PlayState.CONTINUE;
        }
        if (playWaitAnimation) {
            state.getController().setAnimation(OPEN_WAIT_ANIMATION);
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    public void triggerWaitAnimation() {
        if (level != null && level.isClientSide) {
            this.playWaitAnimation = true;
        }
    }

    public void triggerOpenAnimation() {
        if (level != null && level.isClientSide) {
            this.playOpenAnimation = true;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
