package com.battlecraft.vehicleanditems.renderers.blocks.components;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.Level;
import org.joml.Matrix4f;

public class ProgressBarRendererComponent {

    private final float width;
    private final float height;
    private final float yOffset;

    public ProgressBarRendererComponent(float width, float height, float yOffset) {
        this.width = width;
        this.height = height;
        this.yOffset = yOffset;
    }

    public void render(PoseStack poseStack, Level level, MultiBufferSource bufferSource,
                       int packedLight, float progress) {
        if (level == null || progress == 0) return;

        poseStack.pushPose();
        poseStack.translate(0, yOffset, 0);

        //Поворот прогресс-бара к игроку
        Minecraft mc = Minecraft.getInstance();
        poseStack.mulPose(mc.getEntityRenderDispatcher().cameraOrientation());
        poseStack.scale(1f, 1f, 1f);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.gui());
        Matrix4f matrix = poseStack.last().pose();

        float barX = -width / 2f;
        float barY = -height / 2f;
        float fillWidth = width * progress;
        float fillX = barX + (width - fillWidth);

        // Фон
        fillRect(matrix, vertexConsumer, barX, barY, 0, width, height, 0.2f, 0.2f, 0.2f, 0.8f, packedLight);
        // Заливка
        fillRect(matrix, vertexConsumer, fillX, barY, -0.001f, fillWidth, height, 0f, 1f, 0f, 0.9f, packedLight);

        poseStack.popPose();
    }

    private void fillRect(Matrix4f matrix, VertexConsumer consumer,
                          float x, float y, float z, float w, float h,
                          float r, float g, float b, float a, int light) {
        consumer.vertex(matrix, x,     y + h, z).color(r, g, b, a).uv(0, 0).uv2(light).endVertex();
        consumer.vertex(matrix, x + w, y + h, z).color(r, g, b, a).uv(0, 0).uv2(light).endVertex();
        consumer.vertex(matrix, x + w, y,     z).color(r, g, b, a).uv(0, 0).uv2(light).endVertex();
        consumer.vertex(matrix, x,     y,     z).color(r, g, b, a).uv(0, 0).uv2(light).endVertex();
    }
}