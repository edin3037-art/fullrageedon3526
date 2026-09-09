package com.fullrageedon.ui.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import com.fullrageedon.modules.Module;
import com.fullrageedon.FullrageedonClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Advanced HUD Renderer with animations and visual effects
 */
public class HUDRenderer {
    private static final int DARK_BG = 0xFF0a0a0a;
    private static final int PRIMARY_COLOR = 0xFF9d4edd;
    private static final int ACCENT_COLOR = 0xFF00ff88;
    private static final int TEXT_COLOR = 0xFFe0e0e0;
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    private float animationTick = 0.0f;

    public void render(DrawContext context, MinecraftClient mc, float tickDelta) {
        if (mc.player == null || mc.world == null) return;

        animationTick += 0.016f; // 60 FPS
        if (animationTick > 360.0f) animationTick = 0.0f;

        // FPS, Server, Time (Top Left)
        renderTopLeft(context, mc, tickDelta);

        // Coordinates (Top Right)
        renderTopRight(context, mc);

        // Active Modules (Bottom Left)
        renderBottomLeft(context, mc);

        // Potions & Effects (Bottom Right)
        renderBottomRight(context, mc);
    }

    private void renderTopLeft(DrawContext context, MinecraftClient mc, float tickDelta) {
        int y = 10;
        int x = 10;

        // FPS
        int fps = (int) (1.0f / tickDelta);
        drawText(context, "FPS: " + fps, x, y, fps > 60 ? ACCENT_COLOR : PRIMARY_COLOR);

        // Server
        String server = mc.getServerAddress() != null ? mc.getServerAddress() : "Single Player";
        drawText(context, "Server: " + server, x, y + 12, PRIMARY_COLOR);

        // Time
        String time = LocalDateTime.now().format(TIME_FORMAT);
        drawText(context, "Time: " + time, x, y + 24, PRIMARY_COLOR);
    }

    private void renderTopRight(DrawContext context, MinecraftClient mc) {
        int y = 10;
        int x = mc.getWindow().getGuiScaledWidth() - 150;

        // Coordinates
        drawText(context, "X: " + String.format("%.1f", mc.player.getX()), x, y, ACCENT_COLOR);
        drawText(context, "Y: " + String.format("%.1f", mc.player.getY()), x, y + 12, ACCENT_COLOR);
        drawText(context, "Z: " + String.format("%.1f", mc.player.getZ()), x, y + 24, ACCENT_COLOR);

        // Speed (BPS - Blocks Per Second)
        double speed = Math.sqrt(
            mc.player.getVelocity().x * mc.player.getVelocity().x +
            mc.player.getVelocity().z * mc.player.getVelocity().z
        ) * 20; // Multiply by 20 (ticks per second)
        drawText(context, "Speed: " + String.format("%.2f bps", speed), x, y + 36, PRIMARY_COLOR);
    }

    private void renderBottomLeft(DrawContext context, MinecraftClient mc) {
        int y = mc.getWindow().getGuiScaledHeight() - 150;
        int x = 10;

        drawText(context, "Enabled Modules:", x, y, ACCENT_COLOR);

        List<Module> activeModules = FullrageedonClient.moduleManager.getActiveModules();
        int moduleY = y + 12;

        for (Module module : activeModules) {
            if (module.isEnabled()) {
                drawText(context, "✓ " + module.getName(), x, moduleY, PRIMARY_COLOR);
                moduleY += 11;
                if (moduleY > mc.getWindow().getGuiScaledHeight() - 20) break;
            }
        }
    }

    private void renderBottomRight(DrawContext context, MinecraftClient mc) {
        int y = mc.getWindow().getGuiScaledHeight() - 80;
        int x = mc.getWindow().getGuiScaledWidth() - 200;

        drawText(context, "Potions & Effects:", x, y, ACCENT_COLOR);

        int effectY = y + 12;
        for (var statusEffect : mc.player.getStatusEffects()) {
            String effectName = statusEffect.getEffectType().getName().getString();
            int amplifier = statusEffect.getAmplifier();
            drawText(context, effectName + " x" + (amplifier + 1), x, effectY, PRIMARY_COLOR);
            effectY += 11;
            if (effectY > mc.getWindow().getGuiScaledHeight() - 10) break;
        }
    }

    private void drawText(DrawContext context, String text, int x, int y, int color) {
        context.drawTextWithShadow(
            MinecraftClient.getInstance().textRenderer,
            text,
            x,
            y,
            color
        );
    }

    // Advanced visual effects
    private void drawGlowingBox(DrawContext context, int x, int y, int width, int height, int color) {
        float pulse = (float) Math.sin(animationTick / 10.0f) * 0.5f + 0.5f;
        int pulseColor = (int) (color & 0xFF) | ((int) (((color >> 8) & 0xFF) * pulse) << 8);
        context.drawBorder(x, y, width, height, pulseColor);
    }

    private void drawHologramEffect(DrawContext context, String text, int x, int y, int color) {
        // Draw multiple offset texts for hologram effect
        float offset = (float) Math.sin(animationTick / 15.0f) * 2.0f;
        for (int i = 0; i < 3; i++) {
            int alpha = (255 - (i * 85)) << 24;
            int offsetY = (int) (y + (i * offset));
            // Draw text with reduced alpha
        }
    }
}
