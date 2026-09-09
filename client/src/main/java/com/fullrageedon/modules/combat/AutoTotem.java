package com.fullrageedon.modules.combat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.screen.generic.GenericContainerScreenHandler;

import com.fullrageedon.modules.Module;

/**
 * AutoTotem Module - Automatically places totems in offhand
 */
public class AutoTotem extends Module {
    private float healthThreshold = 3.0f;

    public AutoTotem() {
        super("AutoTotem", "Automatically equips totem when health is low", "Combat");
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

        // Check health
        if (mc.player.getHealth() > healthThreshold) {
            // Find totem in inventory
            for (int i = 0; i < mc.player.getInventory().size(); i++) {
                if (mc.player.getInventory().getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {
                    // Swap to offhand
                    mc.interactionManager.clickCreativeStack(mc.player.getInventory().getStack(i), 45);
                    break;
                }
            }
        }
    }

    public void setHealthThreshold(float health) {
        this.healthThreshold = health;
    }
}
