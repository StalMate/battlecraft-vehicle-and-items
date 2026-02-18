package com.battlecraft.vehicleanditems.renderers.blocks;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import com.mojang.blaze3d.vertex.PoseStack;

import com.battlecraft.vehicleanditems.entity.blocks.VehicleSpawnBlockEntity;
import com.battlecraft.vehicleanditems.models.blocks.VehicleSpawnBlockModel;


public class VehicleSpawnBlockRenderer extends GeoBlockRenderer<VehicleSpawnBlockEntity> {
    public VehicleSpawnBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new VehicleSpawnBlockModel());
    }

    @Override
    public void preRender(
            PoseStack poseStack,
            VehicleSpawnBlockEntity animatable,
            BakedGeoModel model, 
            MultiBufferSource bufferSource,
            VertexConsumer buffer,
            boolean isReRender,
            float partialTick,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

    }
}