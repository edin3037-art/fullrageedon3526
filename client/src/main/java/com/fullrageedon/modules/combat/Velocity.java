package com.fullrageedon.modules.combat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.ProjectileEntity;

import com.fullrageedon.modules.Module;

/**
 * Velocity Module - Reduces knockback from hits
 */
public class Velocity extends Module {
    private float resistance = 0.5f;

    public Velocity() {
        super("Velocity", "Reduces knockback and damage from projectiles", "Combat");
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

        // Reduce knockback
        if (mc.player.hasVelocity()) {
            mc.player.setVelocity(
                mc.player.getVelocity().x * (1.0 - resistance),
                mc.player.getVelocity().y,
                mc.player.getVelocity().z * (1.0 - resistance)
            );
        }
    }

    public void setResistance(float resistance) {
        this.resistance = Math.max(0.0f, Math.min(1.0f, resistance));
    }
}
