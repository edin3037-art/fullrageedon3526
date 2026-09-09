package com.fullrageedon.modules.render;

import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;

/**
 * FullBright Module - Maximum brightness always
 */
public class FullBright extends Module {
    private String mode = "Gamma";

    public FullBright() {
        super("FullBright", "Maximum brightness", "Render");
    }

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.options != null) {
            mc.options.getGamma().setValue(16.0);
        }
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.options != null) {
            mc.options.getGamma().setValue(1.0);
        }
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.options != null && mode.equals("Gamma")) {
            mc.options.getGamma().setValue(16.0);
        }
    }
}
