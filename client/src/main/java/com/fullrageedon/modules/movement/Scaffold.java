package com.fullrageedon.modules.movement;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import com.fullrageedon.modules.Module;

/**
 * Scaffold Module - Automatic bridge building
 */
public class Scaffold extends Module {
    private float range = 5.0f;
    private boolean placeDown = true;
    private int placeDelay = 0;

    public Scaffold() {
        super("Scaffold", "Automatically builds bridges while walking", "Movement");
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

        if (placeDelay > 0) {
            placeDelay--;
            return;
        }

        if (placeDown && mc.player.input.movementForward != 0.0f) {
            BlockPos blockPos = mc.player.getBlockPos().down();
            if (mc.world.getBlockState(blockPos).getMaterial().isReplaceable()) {
                placeBlock(blockPos, mc);
                placeDelay = 2;
            }
        }
    }

    private void placeBlock(BlockPos pos, MinecraftClient mc) {
        // Find block in inventory
        for (int i = 0; i < mc.player.getInventory().size(); i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.COBBLESTONE || 
                mc.player.getInventory().getStack(i).getItem() == Items.OBSIDIAN) {
                // Place block
                mc.interactionManager.clickCreativeStack(mc.player.getInventory().getStack(i), i);
                break;
            }
        }
    }
}
