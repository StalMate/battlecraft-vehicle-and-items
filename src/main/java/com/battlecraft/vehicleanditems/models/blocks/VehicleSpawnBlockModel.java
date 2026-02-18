package com.battlecraft.vehicleanditems.models.blocks;

import com.battlecraft.vehicleanditems.BattlecraftVehicleAndItems;
import com.battlecraft.vehicleanditems.entity.blocks.VehicleSpawnBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VehicleSpawnBlockModel extends GeoModel<VehicleSpawnBlockEntity> {

    @Override
    public ResourceLocation getModelResource(VehicleSpawnBlockEntity object) {
        return new ResourceLocation (BattlecraftVehicleAndItems.MOD_ID, "geo/block/vehicle_spawn_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VehicleSpawnBlockEntity object) {
        return new ResourceLocation(BattlecraftVehicleAndItems.MOD_ID, "textures/block/vehicle_spawn_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VehicleSpawnBlockEntity animatable) {
        return new ResourceLocation(BattlecraftVehicleAndItems.MOD_ID, "animations/block/vehicle_spawn_block.animation.json");
    }
}