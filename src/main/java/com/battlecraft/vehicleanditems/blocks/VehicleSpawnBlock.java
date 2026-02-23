package com.battlecraft.vehicleanditems.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

import com.battlecraft.vehicleanditems.registry.ModBlockEntities;
import com.battlecraft.vehicleanditems.entity.blocks.VehicleSpawnBlockEntity;
import com.battlecraft.vehicleanditems.sound.LoopingSound;

public class VehicleSpawnBlock extends BaseEntityBlock {

    public VehicleSpawnBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VehicleSpawnBlockEntity(pos, state);
    }

    //Все обрабатывать на сервере. Ничего на клиенте!
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        if (hand != InteractionHand.MAIN_HAND || !player.getMainHandItem().isEmpty()) return InteractionResult.PASS;

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof VehicleSpawnBlockEntity vehicleBlock) {
            if (!vehicleBlock.getProgressBar().isInit()) { // только если прогресс ещё не стартовал
                vehicleBlock.getProgressBar().init(level);
                vehicleBlock.setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
        if (level.isClientSide) {
            LoopingSound.stop(pos);
        }
        if (!state.is(newState.getBlock())) {
            level.removeBlockEntity(pos);
        }
        super.onRemove(state, level, pos, newState, moved);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return 0;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type,
                ModBlockEntities.VEHICLE_SPAWN_BLOCK_ENTITY.get(),
                VehicleSpawnBlockEntity::tick);
    }
}
