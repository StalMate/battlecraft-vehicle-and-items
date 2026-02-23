package com.battlecraft.vehicleanditems.renderers.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import com.battlecraft.vehicleanditems.entity.blocks.VehicleSpawnBlockEntity;
import com.battlecraft.vehicleanditems.models.blocks.VehicleSpawnBlockModel;
import com.battlecraft.vehicleanditems.renderers.blocks.components.ProgressBarRendererComponent;


public class VehicleSpawnBlockRenderer extends GeoBlockRenderer<VehicleSpawnBlockEntity> {
    private final ProgressBarRendererComponent progressBarRenderer = new ProgressBarRendererComponent(1f, 0.1f, 1.5f);

    public VehicleSpawnBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new VehicleSpawnBlockModel());
    }

    @Override
    public void actuallyRender(
            PoseStack poseStack, VehicleSpawnBlockEntity animatable, BakedGeoModel model,
            RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer,
            boolean isReRender, float partialTick, int packedLight,
            int packedOverlay, float red, float green, float blue, float alpha
    ) {
        super.actuallyRender(
                poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick,
                packedLight, packedOverlay, red, green, blue, alpha
        );

        if (!isReRender) {
            progressBarRenderer.render(poseStack, animatable.getLevel(), bufferSource, packedLight, animatable.getRenderProgress(partialTick));
        }
    }
}