package com.battlecraft.vehicleanditems.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.battlecraft.vehicleanditems.BattlecraftVehicleAndItems;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            BattlecraftVehicleAndItems.MOD_ID
    );

    public static final RegistryObject<Item> VEHICLE_SPAWN_BLOCK_ITEM = ITEMS.register(
            "test_vehicle_spawn_block",
            () -> new BlockItem(ModBlocks.VEHICLE_SPAWN_BLOCK.get(), new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )
    );
}
