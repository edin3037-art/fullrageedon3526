# 📚 Developer Guide

## Architecture Générale

```
Fullrageedon3526/
├── Launcher (React + Electron)
│   ├── Main Menu
│   ├── Multiplayer Browser
│   ├── Anti-Cheat Bypass Manager
│   └── Settings
│
└── Client (Fabric Mod)
    ├── Module System
    ├── ClickGUI (Right Shift)
    ├── HUD Overlay
    ├── Event Manager
    └── Config Manager
```

## Module System

### Hiérarchie des Classes

```
Module (Abstract Base Class)
├── Combat Modules
│   ├── Aura
│   ├── AutoTotem
│   ├── AutoTrap
│   ├── AutoWeb
│   ├── TargetStrafe
│   ├── Criticals
│   └── Velocity
├── Movement Modules
│   ├── Speed
│   ├── Scaffold
│   ├── FreeCam
│   ├── Sprint
│   └── NoSlow
├── Player Modules
│   ├── ClickFriend
│   ├── FakeLag
│   └── NoFall
├── Render Modules
│   ├── ESP
│   ├── Tracers
│   ├── FullBright
│   ├── Nametags
│   └── HUD
└── Misc Modules
    ├── DiscordRPC
    └── MiddleClickFriend
```

### Créer un Module Personnalisé

#### 1. Définir la classe

```java
package com.fullrageedon.modules.combat;

import net.minecraft.client.MinecraftClient;
import com.fullrageedon.modules.Module;

public class MyAwesomeModule extends Module {
    private float configValue = 1.0f;

    public MyAwesomeModule() {
        super("MyAwesome", "Does something awesome", "Combat");
    }

    @Override
    public void onEnable() {
        System.out.println("[MyAwesome] Enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("[MyAwesome] Disabled!");
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        // Your logic here
    }

    public void setConfigValue(float value) {
        this.configValue = value;
    }
}
```

#### 2. Enregistrer dans ModuleManager

```java
// Dans ModuleManager.registerAllModules()
register(new modules.combat.MyAwesomeModule());
```

## ClickGUI Architecture

### Composants

- **ClickGUI Screen**: Main render class
- **Category**: Container for modules
- **Module Items**: Individual module toggles

### Animation System

```java
float animationProgress = 0.0f;
if (shouldAnimate) {
    animationProgress += 1.0f / ANIMATION_DURATION;
}
float eased = easeOutCubic(animationProgress);
```

### Customizing Colors

```java
// Dans ClickGUI.java
private static final int[] PRIMARY_COLOR = {157, 78, 221};    // #9d4edd
private static final int[] ACCENT_COLOR = {0, 255, 136};      // #00ff88
private static final int[] DARK_BG = {10, 10, 10};            // #0a0a0a
```

## Event System

### Types d'Événements Supportés

1. **ClientTickEvents** - Every game tick
2. **WorldRenderEvents** - Rendering
3. **InputEvents** - Keyboard/Mouse input
4. **AttackEntityEvent** - Combat
5. **MovementEvent** - Movement

### Écouter un Événement

```java
ClientTickEvents.END_CLIENT_TICK.register(client -> {
    // Your code here runs at the end of each game tick
});
```

## HUD Rendering

### HUDRenderer Structure

```java
public void render(DrawContext context, MinecraftClient mc, float tickDelta) {
    renderTopLeft(context, mc, tickDelta);      // FPS, Server, Time
    renderTopRight(context, mc);                // Coordinates, Speed
    renderBottomLeft(context, mc);              // Active Modules
    renderBottomRight(context, mc);             // Potions/Effects
}
```

### Adding Custom HUD Elements

```java
private void renderCustomElement(DrawContext context, int x, int y) {
    String text = "Custom Info";
    int color = 0xFF9d4edd;
    context.drawTextWithShadow(
        MinecraftClient.getInstance().textRenderer,
        text,
        x, y,
        color
    );
}
```

## Configuration System

### Saving Module State

```java
public void saveConfig() {
    JsonObject root = new JsonObject();
    // Add module data
    try (FileWriter writer = new FileWriter(configFile)) {
        GSON.toJson(root, writer);
    }
}
```

### Loading Module State

```java
public void loadConfig() {
    try (FileReader reader = new FileReader(configFile)) {
        JsonObject root = GSON.fromJson(reader, JsonObject.class);
        // Restore module data
    }
}
```

## Launcher Architecture

### React Components

```
App
├── MainMenu
├── Multiplayer
├── Settings
└── AntiCheatBypass
    ├── Overview Tab
    ├── Systems Tab
    └── Configuration Tab
```

### State Management

```javascript
const [currentPage, setCurrentPage] = useState('menu');
const [account, setAccount] = useState(null);
```

### Animation Transitions

```javascript
<AnimatePresence mode="wait">
  <motion.div
    initial={{ opacity: 0 }}
    animate={{ opacity: 1 }}
    exit={{ opacity: 0 }}
  >
    {/* Content */}
  </motion.div>
</AnimatePresence>
```

## Building & Packaging

### Build Client

```bash
cd client
./gradlew build
# Output: build/libs/fullrageedon-client-1.0.0.jar
```

### Build Launcher

```bash
cd launcher
npm run build        # React build
npm run dist         # Electron packaging
```

## Testing

### Test Module

```bash
# Add to ModuleManager for testing
register(new MyTestModule());

# Launch Minecraft
# Open ClickGUI and toggle module
```

### Debug Logs

```java
System.out.println("[Fullrageedon] Debug message");
System.err.println("[Fullrageedon] Error message");
```

## Performance Optimization

### Best Practices

1. **Minimize Tick Operations**
   ```java
   // Good - Only when needed
   if (shouldCheck) {
       // expensive operation
   }
   
   // Bad - Every tick
   expensiveOperation();
   ```

2. **Cache World Data**
   ```java
   List<PlayerEntity> players = mc.world.getPlayers();
   for (PlayerEntity player : players) {
       // process
   }
   ```

3. **Use Distance Checks**
   ```java
   if (mc.player.distanceTo(entity) < range) {
       // only process nearby entities
   }
   ```

## Debugging

### Enable Debug Logs

```java
System.setProperty("fullrageedon.debug", "true");
```

### Check Module Status

```java
for (Module module : moduleManager.getAllModules().values()) {
    System.out.println(module.getName() + ": " + module.isEnabled());
}
```

---

**Need help?** Check the main README or create an issue!
