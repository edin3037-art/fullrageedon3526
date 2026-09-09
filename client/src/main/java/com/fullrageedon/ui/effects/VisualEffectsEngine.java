package com.fullrageedon.ui.effects;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

/**
 * Advanced Visual Effects Engine
 * Hologram, Fog, Ambiance, Saturation
 */
public class VisualEffectsEngine {
    private float hologramIntensity = 0.8f;
    private float fogDensity = 0.5f;
    private float ambiance = 1.0f;
    private float saturation = 1.2f;
    private float time = 0.0f;

    private float[] hologramColor = {0.0f, 1.0f, 0.5f};  // Cyan
    private float[] crownColor = {1.0f, 0.8f, 0.0f};     // Gold
    private float[] fogColor = {0.1f, 0.15f, 0.2f};      // Dark blue

    public VisualEffectsEngine() {
    }

    public void tick() {
        time += 0.016f; // 60 FPS
        if (time > 360.0f) time = 0.0f;
    }

    /**
     * Hologram Crown Effect - Renders a holographic crown above player
     */
    public void renderHologramCrown(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        matrices.push();

        // Position at head level
        matrices.translate(0, 2.5f, 0);

        // Rotation animation
        float rotation = (time * 30.0f) % 360.0f;
        matrices.multiply(new org.joml.Quaternionf().rotateY((float) Math.toRadians(rotation)));

        // Draw hologram effect
        float scale = 0.5f + (float) Math.sin(time / 10.0f) * 0.2f;
        matrices.scale(scale, scale, scale);

        // Hologram glow effect
        float glow = 0.5f + (float) Math.sin(time / 5.0f) * 0.5f;
        drawHologramBox(matrices, vertexConsumers, glow);
        drawCrownShapes(matrices, vertexConsumers, glow);

        matrices.pop();
    }

    /**
     * Hologram Box - Glowing box with scanlines
     */
    private void drawHologramBox(MatrixStack matrices, VertexConsumerProvider vertexConsumers, float intensity) {
        var vertexConsumer = vertexConsumers.getBuffer(net.minecraft.client.render.RenderLayer.getLines());
        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();

        float size = 1.0f;
        float[] color = hologramColor;
        int alpha = (int) (255 * intensity * hologramIntensity);

        // Draw cube edges
        drawLine(vertexConsumer, positionMatrix, -size, size, -size, size, size, -size, color, alpha);
        drawLine(vertexConsumer, positionMatrix, -size, size, size, size, size, size, color, alpha);
        drawLine(vertexConsumer, positionMatrix, size, size, size, size, size, -size, color, alpha);
        drawLine(vertexConsumer, positionMatrix, size, size, -size, -size, size, -size, color, alpha);

        // Vertical edges
        drawLine(vertexConsumer, positionMatrix, -size, -size, -size, -size, size, -size, color, alpha);
        drawLine(vertexConsumer, positionMatrix, size, -size, -size, size, size, -size, color, alpha);
        drawLine(vertexConsumer, positionMatrix, size, -size, size, size, size, size, color, alpha);
        drawLine(vertexConsumer, positionMatrix, -size, -size, size, -size, size, size, color, alpha);
    }

    /**
     * Crown Shapes - Multiple rotating crowns
     */
    private void drawCrownShapes(MatrixStack matrices, VertexConsumerProvider vertexConsumers, float intensity) {
        var vertexConsumer = vertexConsumers.getBuffer(net.minecraft.client.render.RenderLayer.getLines());
        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();

        float[] color = crownColor;
        int alpha = (int) (255 * intensity * hologramIntensity);

        // Draw rotating triangular crowns
        for (int i = 0; i < 3; i++) {
            matrices.push();
            float rotY = (time * 45.0f + i * 120.0f) % 360.0f;
            matrices.multiply(new org.joml.Quaternionf().rotateY((float) Math.toRadians(rotY)));

            // Crown triangle points
            drawLine(vertexConsumer, positionMatrix, 0, 0, 1.5f, 0.8f, 0.5f, 0.5f, color, alpha);
            drawLine(vertexConsumer, positionMatrix, 0.8f, 0.5f, 0.5f, 0.8f, -0.5f, 0.5f, color, alpha);
            drawLine(vertexConsumer, positionMatrix, 0.8f, -0.5f, 0.5f, 0, 0, 1.5f, color, alpha);

            matrices.pop();
        }
    }

    /**
     * Draw a line in 3D space
     */
    private void drawLine(net.minecraft.client.render.VertexConsumer vertexConsumer, Matrix4f positionMatrix,
                         float x1, float y1, float z1, float x2, float y2, float z2,
                         float[] color, int alpha) {
        vertexConsumer.vertex(positionMatrix, x1, y1, z1)
                .color(color[0], color[1], color[2], alpha / 255.0f)
                .next();
        vertexConsumer.vertex(positionMatrix, x2, y2, z2)
                .color(color[0], color[1], color[2], alpha / 255.0f)
                .next();
    }

    /**
     * Advanced Fog Effect
     */
    public void applyFogEffect(MinecraftClient mc) {
        if (mc.world == null) return;

        // Adjust fog color
        com.mojang.blaze3d.systems.RenderSystem.setShaderFogColor(
            fogColor[0], fogColor[1], fogColor[2], 1.0f
        );

        // Adjust fog density
        float actualDensity = fogDensity * (0.8f + (float) Math.sin(time / 20.0f) * 0.2f);
        // Apply fog through render system
    }

    /**
     * Saturation Effect - Increase/decrease color saturation
     */
    public void applySaturationEffect(net.minecraft.client.gui.DrawContext context) {
        // Apply saturation shader/effect
        if (saturation != 1.0f) {
            // This would typically be done through a shader or post-processing
            applyColorGrading(saturation);
        }
    }

    /**
     * Color Grading - Adjust saturation
     */
    private void applyColorGrading(float saturation) {
        // Implement color grading using texture shaders
        // Desaturate RGB values based on saturation parameter
        float desat = 1.0f - saturation;
        float[] desatMatrix = {
            1.0f - (0.3f * desat), 0.59f * desat, 0.11f * desat, 0,
            0.3f * desat, 1.0f - (0.59f * desat), 0.11f * desat, 0,
            0.3f * desat, 0.59f * desat, 1.0f - (0.11f * desat), 0,
            0, 0, 0, 1
        };
    }

    /**
     * Ambiance - Overall brightness and mood adjustments
     */
    public void applyAmbiance(MinecraftClient mc) {
        if (mc.options == null) return;

        // Subtle brightness modulation based on ambiance
        float brightness = ambiance * (0.9f + (float) Math.sin(time / 15.0f) * 0.1f);
        // Apply through gamma or brightness settings
    }

    /**
     * Particle System - Hologram particles around player
     */
    public void spawnHologramParticles(MinecraftClient mc) {
        if (mc.world == null || mc.player == null) return;

        // Spawn hologram particles around player
        for (int i = 0; i < 5; i++) {
            double angle = (time + i * 72.0f) * 0.01745f; // Convert to radians
            double radius = 1.5f;
            double x = mc.player.getX() + Math.cos(angle) * radius;
            double y = mc.player.getY() + 1.5f + Math.sin(time * 0.02f) * 0.5f;
            double z = mc.player.getZ() + Math.sin(angle) * radius;

            // Spawn particle at position
            spawnParticle(mc, x, y, z);
        }
    }

    private void spawnParticle(MinecraftClient mc, double x, double y, double z) {
        if (mc.world != null) {
            // Spawn cyan particle
            mc.world.addParticle(
                net.minecraft.particle.ParticleTypes.GLOW,
                x, y, z,
                0, 0, 0
            );
        }
    }

    // Getters and Setters
    public void setHologramIntensity(float intensity) {
        this.hologramIntensity = Math.max(0.0f, Math.min(1.0f, intensity));
    }

    public void setFogDensity(float density) {
        this.fogDensity = Math.max(0.0f, Math.min(1.0f, density));
    }

    public void setAmbiance(float ambiance) {
        this.ambiance = Math.max(0.5f, Math.min(1.5f, ambiance));
    }

    public void setSaturation(float saturation) {
        this.saturation = Math.max(0.0f, Math.min(2.0f, saturation));
    }

    public void setHologramColor(float r, float g, float b) {
        this.hologramColor = new float[]{r, g, b};
    }

    public void setFogColor(float r, float g, float b) {
        this.fogColor = new float[]{r, g, b};
    }

    public void setCrownColor(float r, float g, float b) {
        this.crownColor = new float[]{r, g, b};
    }

    public float getHologramIntensity() {
        return hologramIntensity;
    }

    public float getFogDensity() {
        return fogDensity;
    }

    public float getAmbiance() {
        return ambiance;
    }

    public float getSaturation() {
        return saturation;
    }
}
