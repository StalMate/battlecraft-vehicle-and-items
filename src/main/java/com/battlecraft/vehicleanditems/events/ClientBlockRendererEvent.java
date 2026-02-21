package com.battlecraft.vehicleanditems.events;

import com.battlecraft.vehicleanditems.BattlecraftVehicleAndItems;
import com.battlecraft.vehicleanditems.registry.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.battlecraft.vehicleanditems.renderers.blocks.VehicleSpawnBlockRenderer;

@Mod.EventBusSubscriber(modid = BattlecraftVehicleAndItems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientBlockRendererEvent {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.EXAMPLE_VEHICLE_SPAWN_BLOCK_ENTITY.get(), VehicleSpawnBlockRenderer::new);
    }
}