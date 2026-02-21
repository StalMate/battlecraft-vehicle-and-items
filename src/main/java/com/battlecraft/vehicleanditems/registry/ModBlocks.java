package com.battlecraft.vehicleanditems.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.battlecraft.vehicleanditems.BattlecraftVehicleAndItems;
import com.battlecraft.vehicleanditems.blocks.VehicleSpawnBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,
            BattlecraftVehicleAndItems.MOD_ID
    );

    public static final RegistryObject<Block> EXAMPLE_VEHICLE_SPAWN_BLOCK = BLOCKS.register(
            "example_vehicle_spawn_block",
            () -> new VehicleSpawnBlock(BlockBehaviour.Properties.of()
                    .strength(3.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .lightLevel(state -> 0)
                    .pushReaction(PushReaction.BLOCK)
            )
    );
}
