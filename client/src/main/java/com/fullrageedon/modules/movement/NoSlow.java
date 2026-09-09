package com.fullrageedon.modules.movement;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;

/**
 * NoSlow Module - Removes slowdown from items like bows and shields
 */
public class NoSlow extends Module {
    private boolean blockHit = true;
    private boolean eatDrink = true;

    public NoSlow() {
        super("NoSlow", "Remove slowness effects from items", "Movement");
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

        // Check if blocking
        if (blockHit && mc.player.isBlocking()) {
            mc.player.setVelocity(
                mc.player.getVelocity().x * 1.2,
                mc.player.getVelocity().y,
                mc.player.getVelocity().z * 1.2
            );
        }
    }
}
