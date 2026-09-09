package com.fullrageedon.modules.movement;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;

/**
 * Sprint Module - Always sprint when moving
 */
public class Sprint extends Module {
    private boolean allDirections = false;

    public Sprint() {
        super("Sprint", "Automatically sprint when moving", "Movement");
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            mc.player.setSprinting(false);
        }
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        if (allDirections) {
            if (mc.player.input.movementForward != 0.0f || mc.player.input.movementSideways != 0.0f) {
                mc.player.setSprinting(true);
            }
        } else {
            if (mc.player.input.movementForward > 0.0f) {
                mc.player.setSprinting(true);
            }
        }
    }
}
