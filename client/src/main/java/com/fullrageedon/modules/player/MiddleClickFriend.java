package com.fullrageedon.modules.player;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

import com.fullrageedon.modules.Module;

/**
 * MiddleClickFriend Module - Mark friends with middle mouse click
 */
public class MiddleClickFriend extends Module {
    private com.fullrageedon.modules.player.ClickFriend clickFriend;

    public MiddleClickFriend() {
        super("MiddleClickFriend", "Mark friends by middle-clicking them", "Player");
    }

    @Override
    public void onEnable() {
        // Get ClickFriend module reference
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        // Listen for middle mouse clicks
    }
}
