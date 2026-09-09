package com.fullrageedon.modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

/**
 * Base Module class
 */
public abstract class Module {
    private String name;
    private String description;
    private String category;
    private boolean enabled = false;
    private int keyCode = -1;

    public Module(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void onTick();

    public void toggle() {
        if (enabled) {
            disable();
        } else {
            enable();
        }
    }

    public void enable() {
        enabled = true;
        onEnable();
    }

    public void disable() {
        enabled = false;
        onDisable();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
