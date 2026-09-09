package com.fullrageedon.modules.combat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;

import com.fullrageedon.modules.Module;

/**
 * AutoWeb Module - Automatically places webs to slow enemies
 */
public class AutoWeb extends Module {
    private float range = 10.0f;
    private int webDelay = 0;

    public AutoWeb() {
        super("AutoWeb", "Automatically places webs around enemies", "Combat");
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
        if (mc.player == null || mc.world == null) return;

        if (webDelay > 0) {
            webDelay--;
            return;
        }

        mc.world.getPlayers().forEach(player -> {
            if (player != mc.player && mc.player.distanceTo(player) < range) {
                if (hasWebs()) {
                    // Place web
                    webDelay = 10;
                }
            }
        });
    }

    private boolean hasWebs() {
        MinecraftClient mc = MinecraftClient.getInstance();
        for (int i = 0; i < mc.player.getInventory().size(); i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.COBWEB) {
                return true;
            }
        }
        return false;
    }
}
