package com.fullrageedon.modules.render;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;
import com.fullrageedon.ui.effects.VisualEffectsEngine;

/**
 * Advanced Renderer Module with Visual Effects
 */
public class AdvancedRenderer extends Module {
    private VisualEffectsEngine effectsEngine;
    private boolean showHologram = true;
    private boolean showFog = true;
    private boolean showAmbiance = true;

    public AdvancedRenderer() {
        super("AdvancedRenderer", "Advanced visual effects and rendering", "Render");
        this.effectsEngine = new VisualEffectsEngine();
    }

    @Override
    public void onEnable() {
        System.out.println("[Fullrageedon] Advanced Renderer enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("[Fullrageedon] Advanced Renderer disabled");
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        // Update effects
        effectsEngine.tick();

        // Apply effects
        if (showHologram) {
            effectsEngine.spawnHologramParticles(mc);
        }

        if (showFog) {
            effectsEngine.applyFogEffect(mc);
        }

        if (showAmbiance) {
            effectsEngine.applyAmbiance(mc);
        }
    }

    public VisualEffectsEngine getEffectsEngine() {
        return effectsEngine;
    }

    public void setShowHologram(boolean show) {
        this.showHologram = show;
    }

    public void setShowFog(boolean show) {
        this.showFog = show;
    }

    public void setShowAmbiance(boolean show) {
        this.showAmbiance = show;
    }
}
