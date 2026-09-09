package com.fullrageedon.modules.player;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

import com.fullrageedon.modules.Module;

import java.util.HashSet;
import java.util.Set;

/**
 * ClickFriend Module - Mark players as friends by clicking
 */
public class ClickFriend extends Module {
    private Set<String> friends = new HashSet<>();

    public ClickFriend() {
        super("ClickFriend", "Mark players as friends by clicking them", "Player");
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        // Listen for right-click events to add friends
    }

    public void addFriend(String name) {
        friends.add(name);
    }

    public void removeFriend(String name) {
        friends.remove(name);
    }

    public boolean isFriend(String name) {
        return friends.contains(name);
    }

    public Set<String> getFriends() {
        return friends;
    }
}
