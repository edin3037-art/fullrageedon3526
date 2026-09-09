package com.fullrageedon;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import com.fullrageedon.ui.ClickGUI;
import com.fullrageedon.modules.ModuleManager;
import com.fullrageedon.config.ConfigManager;

public class FullrageedonClient implements ClientModInitializer {
    public static final String MOD_ID = "fullrageedon";
    public static final String MOD_NAME = "Fullrageedon3526";
    public static final String VERSION = "1.0.0";

    public static KeyBinding GUI_KEY;
    public static ClickGUI clickGUI;
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;

    @Override
    public void onInitializeClient() {
        // Initialize modules
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        clickGUI = new ClickGUI();

        // Register keybindings
        GUI_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fullrageedon.gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.fullrageedon.main"
        ));

        // Register client tick
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (GUI_KEY.wasPressed()) {
                clickGUI.toggle();
            }
            moduleManager.onTick();
        });

        // Load config
        configManager.loadConfig();
        moduleManager.loadModulesFromConfig();
    }
}
