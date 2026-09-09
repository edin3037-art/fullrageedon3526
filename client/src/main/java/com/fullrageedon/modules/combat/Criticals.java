package com.fullrageedon.modules.combat;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;

/**
 * Criticals Module - Guarantees critical hits
 */
public class Criticals extends Module {
    private String mode = "jump";

    public Criticals() {
        super("Criticals", "Guarantees critical hits on attacks", "Combat");
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

        if (mode.equals("jump")) {
            // Jump for critical hit
            if (mc.player.isOnGround() && mc.player.fallDistance == 0.0f) {
                mc.player.jump();
            }
        }
    }
}
