package com.fullrageedon.modules.player;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;

/**
 * NoFall Module - Negates fall damage
 */
public class NoFall extends Module {
    private String mode = "Packet";

    public NoFall() {
        super("NoFall", "Removes fall damage", "Player");
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
        if (mc.player == null) return;

        if (mode.equals("Packet")) {
            if (mc.player.fallDistance > 3.0f) {
                // Send position packet with onGround = true
                mc.player.setOnGround(true);
                mc.player.fallDistance = 0.0f;
            }
        } else if (mode.equals("Motion")) {
            // Reduce fall motion
            if (mc.player.getVelocity().y < 0) {
                mc.player.setVelocity(
                    mc.player.getVelocity().x,
                    Math.max(-0.5, mc.player.getVelocity().y * 0.5),
                    mc.player.getVelocity().z
                );
            }
        }
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
