package com.fullrageedon.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Central module management system
 */
public class ModuleManager {
    private Map<String, Module> modules = new HashMap<>();
    private List<Module> activeModules = new ArrayList<>();

    public ModuleManager() {
        registerAllModules();
    }

    private void registerAllModules() {
        // Combat
        register(new modules.combat.Aura());
        register(new modules.combat.AutoTotem());
        register(new modules.combat.AutoTrap());
        register(new modules.combat.AutoWeb());
        register(new modules.combat.TargetStrafe());
        register(new modules.combat.Criticals());
        register(new modules.combat.Velocity());

        // Movement
        register(new modules.movement.Speed());
        register(new modules.movement.Scaffold());
        register(new modules.movement.FreeCam());
        register(new modules.movement.Sprint());
        register(new modules.movement.NoSlow());

        // Player
        register(new modules.player.ClickFriend());
        register(new modules.player.FakeLag());
        register(new modules.player.NoFall());

        // Render
        register(new modules.render.Nametags());
        register(new modules.render.ESP());
        register(new modules.render.Tracers());
        register(new modules.render.FullBright());
        register(new modules.render.HUD());

        // Misc
        register(new modules.misc.DiscordRPC());
        register(new modules.misc.MiddleClickFriend());
    }

    public void register(Module module) {
        modules.put(module.getName(), module);
    }

    public Module getModule(String name) {
        return modules.get(name);
    }

    public void toggleModule(String name) {
        Module module = modules.get(name);
        if (module != null) {
            module.toggle();
            if (module.isEnabled()) {
                activeModules.add(module);
            } else {
                activeModules.remove(module);
            }
        }
    }

    public void onTick() {
        for (Module module : activeModules) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }

    public List<Module> getActiveModules() {
        return activeModules;
    }

    public Map<String, Module> getAllModules() {
        return modules;
    }

    public void loadModulesFromConfig() {
        // Load enabled modules from config
    }
}
