package com.fullrageedon.events;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import com.fullrageedon.ui.hud.HUDRenderer;
import com.fullrageedon.FullrageedonClient;

/**
 * Event handlers for rendering and ticking
 */
public class EventManager {
    private static final HUDRenderer hudRenderer = new HUDRenderer();

    public static void register() {
        // Register HUD render event
        WorldRenderEvents.AFTER_TRANSLUCENT.register(context -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player != null && mc.world != null) {
                // HUD rendering would go here
            }
        });

        // Register client tick for modules
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (FullrageedonClient.moduleManager != null) {
                FullrageedonClient.moduleManager.onTick();
            }
        });
    }
}
