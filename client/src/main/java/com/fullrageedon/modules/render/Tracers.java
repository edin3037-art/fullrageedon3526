package com.fullrageedon.modules.render;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;

/**
 * Tracers Module - Draw lines to players
 */
public class Tracers extends Module {
    private float colorR = 1.0f, colorG = 1.0f, colorB = 0.0f;
    private String point = "Head";

    public Tracers() {
        super("Tracers", "Draw lines to nearby players", "Render");
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        // Tracers rendering handled in render event
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPoint() {
        return point;
    }
}
