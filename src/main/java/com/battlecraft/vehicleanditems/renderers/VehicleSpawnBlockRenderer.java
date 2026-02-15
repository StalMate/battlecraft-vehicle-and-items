package com.battlecraft.vehicleanditems.renderers;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import com.battlecraft.vehicleanditems.entity.VehicleSpawnBlockEntity;
import com.battlecraft.vehicleanditems.models.VehicleSpawnBlockModel;

public class VehicleSpawnBlockRenderer extends GeoBlockRenderer<VehicleSpawnBlockEntity> {
    public VehicleSpawnBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new VehicleSpawnBlockModel());
    }
}