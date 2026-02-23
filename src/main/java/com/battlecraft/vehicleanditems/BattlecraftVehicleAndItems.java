package com.battlecraft.vehicleanditems;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import com.battlecraft.vehicleanditems.registry.ModCreativeTabs;
import com.battlecraft.vehicleanditems.registry.ModItems;
import com.battlecraft.vehicleanditems.registry.ModBlocks;
import com.battlecraft.vehicleanditems.registry.ModBlockEntities;
import com.battlecraft.vehicleanditems.registry.ModConfigs;
import com.battlecraft.vehicleanditems.registry.ModSounds;

@Mod(BattlecraftVehicleAndItems.MOD_ID)
public class BattlecraftVehicleAndItems {
    public static final String MOD_ID = "battlecraftvehicleanditems";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BattlecraftVehicleAndItems() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ModConfigs.SERVER_SPEC);
        ModSounds.SOUNDS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Мод {} запущен", MOD_ID);
    }
}