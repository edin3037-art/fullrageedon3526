package com.fullrageedon.modules.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

import com.fullrageedon.modules.Module;

/**
 * Nametags Module - Customizable player nametags
 */
public class Nametags extends Module {
    private boolean showHealth = true;
    private boolean showDistance = true;
    private float scale = 1.0f;

    public Nametags() {
        super("Nametags", "Customize player nametags", "Render");
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        // Nametag rendering handled in render event
    }

    public boolean isShowHealth() {
        return showHealth;
    }

    public void setShowHealth(boolean show) {
        this.showHealth = show;
    }
}
