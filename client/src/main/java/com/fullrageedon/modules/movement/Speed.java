package com.fullrageedon.modules.movement;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;

/**
 * Speed Module - Increases movement speed
 */
public class Speed extends Module {
    private float speedMultiplier = 1.5f;
    private String mode = "OnGround";

    public Speed() {
        super("Speed", "Increase movement speed", "Movement");
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            mc.player.getAbilities().flySpeed = 0.05f;
        }
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        if (mode.equals("OnGround")) {
            if (mc.player.isOnGround() && mc.player.input.movementForward != 0.0f) {
                mc.player.setVelocity(
                    mc.player.getVelocity().x * speedMultiplier,
                    mc.player.getVelocity().y,
                    mc.player.getVelocity().z * speedMultiplier
                );
            }
        } else if (mode.equals("Flight")) {
            mc.player.getAbilities().flySpeed = 0.2f * speedMultiplier;
        }
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setSpeedMultiplier(float multiplier) {
        this.speedMultiplier = multiplier;
    }
}
