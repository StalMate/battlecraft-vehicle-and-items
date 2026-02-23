package com.battlecraft.vehicleanditems.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.battlecraft.vehicleanditems.BattlecraftVehicleAndItems;
import com.battlecraft.vehicleanditems.items.VehicleSpawnBlockItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            BattlecraftVehicleAndItems.MOD_ID
    );

    public static final RegistryObject<Item> VEHICLE_SPAWN_BLOCK_ITEM = ITEMS.register(
            "vehicle_spawn_block",
            () -> new VehicleSpawnBlockItem(new Item.Properties()
                    .stacksTo(64)
                    .fireResistant()
            )
    );
}
