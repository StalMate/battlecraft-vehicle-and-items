package com.battlecraft.vehicleanditems.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.battlecraft.vehicleanditems.BattlecraftVehicleAndItems;
import com.battlecraft.vehicleanditems.entity.VehicleSpawnBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES,
            BattlecraftVehicleAndItems.MOD_ID
    );

    public static final RegistryObject<BlockEntityType<VehicleSpawnBlockEntity>> VEHICLE_SPAWN_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("vehicle_spawn_block_entity",
                    () -> BlockEntityType.Builder.of(VehicleSpawnBlockEntity::new,
                                    ModBlocks.VEHICLE_SPAWN_BLOCK.get())
                            .build(null));
}