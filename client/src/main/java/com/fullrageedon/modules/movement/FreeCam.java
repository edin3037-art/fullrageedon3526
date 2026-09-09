package com.fullrageedon.modules.movement;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;

/**
 * FreeCam Module - Allows flying around while spectating
 */
public class FreeCam extends Module {
    private float flySpeed = 0.2f;
    private boolean original = false;
    private double originalX, originalY, originalZ;

    public FreeCam() {
        super("FreeCam", "Fly around freely without moving your player", "Movement");
    }

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            mc.player.getAbilities().allowFlying = true;
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().flySpeed = flySpeed;
            originalX = mc.player.getX();
            originalY = mc.player.getY();
            originalZ = mc.player.getZ();
        }
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            mc.player.getAbilities().flying = false;
            mc.player.getAbilities().allowFlying = false;
        }
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            mc.player.getAbilities().flySpeed = flySpeed;
        }
    }
}
