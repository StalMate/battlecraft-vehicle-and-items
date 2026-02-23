package com.battlecraft.vehicleanditems.registry;

import com.battlecraft.vehicleanditems.BattlecraftVehicleAndItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB,
            BattlecraftVehicleAndItems.MOD_ID
    );

    public static final RegistryObject<CreativeModeTab> VEHICLE_TAB = CREATIVE_MODE_TABS.register(
            "battlecraft_vehicle",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab." + BattlecraftVehicleAndItems.MOD_ID + ".vehicle_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.VEHICLE_SPAWN_BLOCK_ITEM.get());
                    })
                    .icon(() -> new ItemStack(ModItems.VEHICLE_SPAWN_BLOCK_ITEM.get()))
                    .build()
    );
}