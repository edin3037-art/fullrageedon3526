package com.fullrageedon.modules.render;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;

/**
 * HUD Module - In-game display with info
 */
public class HUD extends Module {
    private boolean showFPS = true;
    private boolean showCoords = true;
    private boolean showSpeed = true;
    private boolean showModules = true;
    private String alignment = "BottomRight";

    public HUD() {
        super("HUD", "In-game heads-up display", "Render");
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        // HUD rendering handled in render event
    }

    public boolean isShowFPS() {
        return showFPS;
    }

    public boolean isShowCoords() {
        return showCoords;
    }

    public boolean isShowSpeed() {
        return showSpeed;
    }

    public boolean isShowModules() {
        return showModules;
    }
}
