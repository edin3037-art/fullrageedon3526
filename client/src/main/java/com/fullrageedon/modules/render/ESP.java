package com.fullrageedon.modules.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import com.fullrageedon.modules.Module;

/**
 * ESP Module - See players through walls with boxes
 */
public class ESP extends Module {
    private String mode = "Box";
    private float colorR = 1.0f, colorG = 0.0f, colorB = 0.0f;

    public ESP() {
        super("ESP", "See players through walls", "Render");
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world != null) {
            mc.world.getPlayers().forEach(player -> {
                if (player != mc.player) {
                    // ESP logic will be handled in render event
                }
            });
        }
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
