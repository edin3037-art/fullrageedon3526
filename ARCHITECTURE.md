# 🏗️ Project Architecture

## Complete Structure Overview

```
Fullrageedon3526/
├── 📦 LAUNCHER (Electron + React)
│   ├── public/
│   │   ├── electron.js          # Main Electron process
│   │   └── preload.js           # IPC Bridge
│   ├── src/
│   │   ├── App.jsx              # Main app component
│   │   ├── App.css              # Global styles
│   │   ├── index.js             # React entry
│   │   ├── index.css            # Global CSS
│   │   ├── pages/
│   │   │   ├── MainMenu.jsx      # Home screen
│   │   │   ├── Multiplayer.jsx   # Server browser
│   │   │   ├── Settings.jsx      # Configuration
│   │   │   └── AntiCheatBypass.jsx # BzPass Manager
│   │   └── styles/
│   │       ├── MainMenu.css
│   │       ├── Multiplayer.css
│   │       ├── Settings.css
│   │       └── AntiCheatBypass.css
│   ├── package.json
│   └── electron-builder.json
│
├── 🎮 CLIENT (Fabric Mod)
│   ├── src/main/java/com/fullrageedon/
│   │   ├── FullrageedonClient.java      # Main mod entry
│   │   ├── modules/
│   │   │   ├── Module.java              # Base class
│   │   │   ├── ModuleManager.java       # Module controller
│   │   │   ├── combat/
│   │   │   │   ├── Aura.java
│   │   │   │   ├── AutoTotem.java
│   │   │   │   ├── AutoTrap.java
│   │   │   │   ├── AutoWeb.java
│   │   │   │   ├── TargetStrafe.java
│   │   │   │   ├── Criticals.java
│   │   │   │   ├── Velocity.java
│   │   │   │   └── SwingAnimations.java
│   │   │   ├── movement/
│   │   │   │   ├── Speed.java
│   │   │   │   ├── Scaffold.java
│   │   │   │   ├── FreeCam.java
│   │   │   │   ├── Sprint.java
│   │   │   │   └── NoSlow.java
│   │   │   ├── player/
│   │   │   │   ├── ClickFriend.java
│   │   │   │   ├── FakeLag.java
│   │   │   │   ├── NoFall.java
│   │   │   │   └── MiddleClickFriend.java
│   │   │   ├── render/
│   │   │   │   ├── Nametags.java
│   │   │   │   ├── ESP.java
│   │   │   │   ├── Tracers.java
│   │   │   │   ├── FullBright.java
│   │   │   │   ├── HUD.java
│   │   │   │   └── AdvancedRenderer.java
│   │   │   └── misc/
│   │   │       ├── DiscordRPC.java
│   │   │       └── MiddleClickFriend.java
│   │   ├── ui/
│   │   │   ├── ClickGUI.java           # Main GUI (Right Shift)
│   │   │   ├── hud/
│   │   │   │   └── HUDRenderer.java    # Overlay display
│   │   │   └── effects/
│   │   │       ├── VisualEffectsEngine.java   # Hologram, Fog, etc
│   │   │       └── EffectsPanel.java         # Effects UI
│   │   ├── config/
│   │   │   └── ConfigManager.java      # Save/Load system
│   │   ├── events/
│   │   │   └── EventManager.java       # Event handling
│   │   └── mixin/
│   │       ├── MixinClientConnection.java
│   │       ├── MixinEntity.java
│   │       ├── MixinGameRenderer.java
│   │       ├── MixinClientPlayerEntity.java
│   │       ├── MixinCameraEntity.java
│   │       └── MixinWorldRenderer.java
│   ├── src/main/resources/
│   │   ├── fabric.mod.json              # Mod metadata
│   │   ├── fabric-mod-metadata.json
│   │   ├── fullrageedon.mixins.json     # Mixin config
│   │   └── assets/
│   │       └── fullrageedon/
│   ├── build.gradle
│   ├── gradle.properties
│   └── settings.gradle
│
├── 📚 DOCUMENTATION
│   ├── README.md                    # Main overview
│   ├── INSTALLATION.md              # Setup guide
│   ├── DEVELOPER_GUIDE.md           # Development
│   ├── THEMING.md                   # Customization
│   ├── TROUBLESHOOTING.md           # FAQ & fixes
│   ├── VISUAL_EFFECTS.md            # Effects guide
│   ├── SWING_ANIMATIONS.md          # Swing modes
│   └── ARCHITECTURE.md              # This file
│
└── 🔧 PROJECT FILES
    ├── .gitignore
    ├── gradle.properties
    ├── settings.gradle
    └── build.gradle
```

## Layer Architecture

### Presentation Layer (UI)
```
ClickGUI
├── Categories (Combat, Movement, etc)
│   └── Modules (Toggle/Configure)
├── HUD Overlay (FPS, Coords, Modules, Potions)
└── Effects Panel (Hologram, Fog, Saturation)
```

### Business Logic Layer (Modules)
```
ModuleManager
├── Combat Modules
├── Movement Modules
├── Player Modules
├── Render Modules
└── Misc Modules
```

### Data Layer
```
ConfigManager
├── Save Module State
├── Load Module State
└── Persist Settings
```

### Rendering Layer
```
VisualEffectsEngine
├── Hologram Crown
├── Fog Effect
├── Ambiance
└── Saturation/Color Grading
```

## Data Flow

```
┌─────────────────────────────────────────────┐
│         User Input (ClickGUI)               │
└────────────────┬────────────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────────────┐
│        ModuleManager                        │
│  ├─ Register Modules                        │
│  ├─ Toggle On/Off                           │
│  └─ Execute onTick()                        │
└────────────────┬────────────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────────────┐
│        Individual Modules                   │
│  ├─ onEnable()                              │
│  ├─ onTick()                                │
│  └─ onDisable()                             │
└────────────────┬────────────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────────────┐
│        Minecraft World                      │
│  ├─ Player Movement                         │
│  ├─ Entity Rendering                        │
│  ├─ Particle Spawning                       │
│  └─ Block Placement                         │
└─────────────────────────────────────────────┘
```

## Module Lifecycle

```
┌─────────────────┐
│   Disabled      │
└────────┬────────┘
         │ enable()
         ▼
┌─────────────────┐
│   onEnable()    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐     onTick()     ┌──────────────┐
│    Enabled      │────────────────▶ │  Game Tick   │
└────────┬────────┘                  └──────────────┘
         │
         │ disable()
         ▼
┌─────────────────┐
│  onDisable()    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   Disabled      │
└─────────────────┘
```

## Event System

```
Fabric Events
├── ClientTickEvents.END_CLIENT_TICK
│   └─ ModuleManager.onTick()
├── WorldRenderEvents.AFTER_TRANSLUCENT
│   └─ HUD Rendering
├── AttackEntityEvent (Custom)
│   └─ Combat Modules
├── MovementEvent (Custom)
│   └─ Movement Modules
└── InputEvent (Custom)
    └─ Player Actions
```

## Configuration Flow

```
┌──────────────────────────┐
│  ConfigManager.loadConfig│
└──────────────┬───────────┘
               │
               ▼
┌──────────────────────────┐
│  config.json             │
│  {
│    "modules": { ... }
│  }
└──────────────┬───────────┘
               │
               ▼
┌──────────────────────────┐
│  ModuleManager           │
│  ├─ Load enabled modules │
│  └─ Set keyBindings      │
└──────────────────────────┘
```

## Launcher Architecture

### Component Hierarchy

```
App
├── MainMenu
│   ├── Logo Section
│   ├── Menu Buttons
│   └── Account Banner
├── Multiplayer
│   ├── Server List
│   ├── Scanning Status
│   └── Join Buttons
├── AntiCheatBypass
│   ├── Overview Tab (Stats)
│   ├── Systems Tab (Anti-Cheat list)
│   └── Configuration Tab (Settings)
└── Settings
    ├── JVM Arguments
    ├── Game Directory
    ├── Resolution
    └── Theme Selection
```

### IPC Communication

```
Renderer Process (React)
        │
        ▼
   IPC Channel
        │
        ▼
Main Process (Electron)
        │
        ├─ File System
        ├─ Minecraft Launch
        └─ Java Execution
```

## Mixin System

```
Mixin Classes
├── MixinClientConnection
│   └─ Intercept network packets
├── MixinEntity
│   └─ Entity rendering/physics
├── MixinGameRenderer
│   └─ HUD/overlay rendering
├── MixinClientPlayerEntity
│   └─ Player movement/actions
├── MixinCameraEntity
│   └─ Camera position/rotation
└── MixinWorldRenderer
    └─ Chunk rendering
```

## Threading Model

```
Main Thread (Game Thread)
├── Tick Logic
│   ├─ ModuleManager.onTick()
│   ├─ Player Input Processing
│   └─ Entity Updates
├── Render Thread
│   ├─ HUD Rendering
│   ├─ ClickGUI Drawing
│   └─ Visual Effects
└─ Network Thread
    ├─ Packet Handling
    └─ Server Communication
```

## File System

```
~/.minecraft/
├── fullrageedon/
│   ├── config.json          # Module settings
│   ├── logs/
│   │   └── latest.log       # Client logs
│   └── cache/
│       └── textures.dat     # Cached data
├── mods/
│   └── fullrageedon-client-1.0.0.jar
└── versions/
    └── 1.20.1-fabric/
```

## Dependencies

### Launcher
- React 18.2.0
- Electron 27.0.0
- Framer Motion 10.16.0
- Lucide React 0.263.0

### Client
- Minecraft 1.20.1
- Fabric Loader 0.15.3
- Fabric API 0.96.1
- Java 17+

## Build Pipeline

```
Launcher Build
┌─────────────────┐
│  npm install    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  npm run build  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Electron Build │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Distributable  │
│  (EXE/DMG/AppImage)
└─────────────────┘

Client Build
┌──────────────────────┐
│  ./gradlew build     │
└─────────┬────────────┘
          │
          ▼
┌──────────────────────┐
│  Compile & Decompile │
└─────────┬────────────┘
          │
          ▼
┌──────────────────────┐
│  Apply Mixins        │
└─────────┬────────────┘
          │
          ▼
┌──────────────────────┐
│  Package JAR         │
└─────────┬────────────┘
          │
          ▼
┌──────────────────────┐
│  fullrageedon-client-│
│  1.0.0.jar          │
└──────────────────────┘
```

## Performance Characteristics

### Memory Usage
- **Base**: ~200-300 MB
- **With modules**: +50-100 MB
- **HUD/GUI**: +10-20 MB
- **Visual Effects**: +30-50 MB

### CPU Usage
- **Idle**: <1% (when disabled)
- **Active**: 5-15% (depending on modules)
- **Combat**: 10-20% (Aura + Render modules)

### Disk Space
- **Launcher**: ~150 MB
- **Client JAR**: ~5-10 MB
- **Config**: <1 MB

---

**Architecture designed for extensibility and performance!** 🚀
