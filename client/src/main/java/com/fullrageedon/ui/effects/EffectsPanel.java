package com.fullrageedon.ui.effects;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import com.fullrageedon.FullrageedonClient;

/**
 * Visual Effects Configuration Panel
 * Manage hologram, fog, ambiance, and saturation settings
 */
public class EffectsPanel {
    private VisualEffectsEngine effectsEngine;
    private boolean enabled = false;
    private static final int PANEL_WIDTH = 250;
    private static final int PANEL_HEIGHT = 300;
    private static final int PRIMARY_COLOR = 0xFF9d4edd;
    private static final int ACCENT_COLOR = 0xFF00ff88;
    private static final int DARK_BG = 0xFF0a0a0a;

    public EffectsPanel() {
        this.effectsEngine = new VisualEffectsEngine();
    }

    public void render(DrawContext context, int screenWidth, int screenHeight, float delta) {
        if (!enabled) return;

        int x = screenWidth - PANEL_WIDTH - 20;
        int y = 70;

        // Panel background
        context.fill(x, y, x + PANEL_WIDTH, y + PANEL_HEIGHT, DARK_BG);
        context.drawBorder(x, y, PANEL_WIDTH, PANEL_HEIGHT, PRIMARY_COLOR);

        // Title
        context.drawCenteredTextWithShadow(
            MinecraftClient.getInstance().textRenderer,
            "Visual Effects",
            x + PANEL_WIDTH / 2,
            y + 5,
            ACCENT_COLOR
        );

        // Draw sliders
        int sliderY = y + 20;
        int spacing = 60;

        drawSlider(context, x, sliderY, "Hologram", effectsEngine.getHologramIntensity(), value -> {
            effectsEngine.setHologramIntensity(value);
        });

        drawSlider(context, x, sliderY + spacing, "Fog", effectsEngine.getFogDensity(), value -> {
            effectsEngine.setFogDensity(value);
        });

        drawSlider(context, x, sliderY + spacing * 2, "Ambiance", (effectsEngine.getAmbiance() - 0.5f) / 1.0f, value -> {
            effectsEngine.setAmbiance(value * 1.0f + 0.5f);
        });

        drawSlider(context, x, sliderY + spacing * 3, "Saturation", (effectsEngine.getSaturation() - 0.5f) / 1.5f, value -> {
            effectsEngine.setSaturation(value * 1.5f + 0.5f);
        });

        // Toggle button
        int buttonY = y + PANEL_HEIGHT - 30;
        context.fill(x + 10, buttonY, x + PANEL_WIDTH - 10, buttonY + 20, PRIMARY_COLOR);
        context.drawCenteredTextWithShadow(
            MinecraftClient.getInstance().textRenderer,
            "Close",
            x + PANEL_WIDTH / 2,
            buttonY + 6,
            DARK_BG
        );
    }

    private void drawSlider(DrawContext context, int x, int y, String label, float value, SliderCallback callback) {
        int sliderWidth = 200;
        int sliderHeight = 8;

        // Label
        context.drawTextWithShadow(
            MinecraftClient.getInstance().textRenderer,
            label,
            x + 10,
            y,
            ACCENT_COLOR
        );

        // Background
        context.fill(x + 10, y + 15, x + 10 + sliderWidth, y + 15 + sliderHeight, 0xFF303030);

        // Progress
        int progressWidth = (int) (sliderWidth * value);
        context.fill(x + 10, y + 15, x + 10 + progressWidth, y + 15 + sliderHeight, PRIMARY_COLOR);

        // Value text
        String valueStr = String.format("%.0f%%", value * 100);
        context.drawTextWithShadow(
            MinecraftClient.getInstance().textRenderer,
            valueStr,
            x + sliderWidth + 20,
            y + 15,
            ACCENT_COLOR
        );
    }

    public void update() {
        effectsEngine.tick();
    }

    public void toggle() {
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public VisualEffectsEngine getEffectsEngine() {
        return effectsEngine;
    }

    @FunctionalInterface
    interface SliderCallback {
        void onValueChanged(float value);
    }
}
