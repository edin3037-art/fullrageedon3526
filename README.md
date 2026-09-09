# Fullrageedon3526 - Modern Minecraft Client & Launcher

🎮 **Un client Minecraft professionnel, fluide et puissant** inspiré des meilleurs clients anarchy du marché.

## 🎨 Caractéristiques Principales

### Launcher
- ✨ Interface ultra-moderne avec animations fluides
- 🎭 Thème sombre avec scanlines subtiles
- 🔐 Support Microsoft + Offline
- 📦 Gestion des profils
- 🎯 Multi-versions (1.8.9, 1.12.2, 1.20+)

### Client In-Game
- **ClickGUI Moderne** : Right Shift pour ouvrir/fermer
- **Combat Avancé** : Aura, Auto Totem, AutoTrap, AutoWeb, TargetStrafe, Criticals, Velocity
- **Movement** : Speed, Scaffold, FreeCam, Sprint, NoSlow
- **Player** : Click Friend, Fake Lag, NoFall
- **Render** : Nametags, ESP, Tracers, FullBright, HUD
- **Misc** : Discord RPC, Middle Click Friend
- **HUD In-Game** : FPS, Coordonnées, Vitesse, Liste modules, Potions

### Visuels Avancés
- 👑 Couronnes hologramme
- 🌫️ Fog personnalisable
- 🎨 Ambiance et saturation
- ✨ Particles personnalisés
- 🌈 Themes multiples

## 📁 Structure du Projet

```
fullrageedon3526/
├── launcher/                    # Electron + React Launcher
│   ├── src/
│   │   ├── components/         # Composants React
│   │   ├── pages/              # Pages (Menu, Multiplayer, Settings)
│   │   ├── styles/             # Styles (CSS/SCSS)
│   │   ├── assets/             # Logos, images
│   │   └── main.js             # Main Electron
│   ├── package.json
│   └── electron-builder.json
├── client/                      # Client Fabric Minecraft
│   ├── src/main/java/com/fullrageedon/
│   │   ├── client/             # Main client class
│   │   ├── modules/            # Modules (Combat, Movement, etc)
│   │   ├── ui/                 # ClickGUI + HUD
│   │   ├── events/             # Event system
│   │   ├── utils/              # Utilities
│   │   └── config/             # Config manager
│   ├── src/main/resources/
│   │   ├── fabric.mod.json
│   │   └── assets/
│   ├── build.gradle
│   └── gradle.properties
├── docs/                        # Documentation
└── .gitignore
```

## 🚀 Installation & Utilisation

### Launcher
```bash
cd launcher
npm install
npm start          # Development
npm run build      # Production
```

### Client Minecraft
```bash
cd client
./gradlew build
# Import dans votre .minecraft/mods/
```

## ⌨️ Keybinds

| Touche | Action |
|--------|--------|
| **Right Shift** | Open/Close ClickGUI |
| **Middle Click** | Toggle Friend |
| **ALT** | Quick Toggle Modules |

## 🎮 Modules Disponibles

### Combat
- **Aura** - KillAura automatique avec customization
- **Auto Totem** - Totem de protection automatique
- **Auto Trap** - Piégeage automatique
- **Auto Web** - Web automatique
- **TargetStrafe** - Strafe autour de la cible
- **Criticals** - Crits garantis
- **Velocity** - Réduction knockback

### Movement
- **Speed** - Accélération personnalisée
- **Scaffold** - Bridge automatique
- **FreeCam** - Caméra libre
- **Sprint** - Sprint permanent
- **NoSlow** - Pas de ralentissement

### Player
- **Click Friend** - Click droit pour marquer amis
- **Fake Lag** - Lag artificiel
- **NoFall** - Pas de dégâts de chute

### Render
- **Nametags** - Tags joueurs modifiés
- **ESP** - Tracé des joueurs
- **Tracers** - Lignes vers les joueurs
- **FullBright** - Luminosité max
- **HUD** - Interface custom

### Misc
- **Discord RPC** - Présence Discord
- **Middle Click Friend** - Ami au middle click

## 🎨 Thème & Design

- 🌑 **Fond** : Noir (#0a0a0a) avec scanlines subtiles
- 💜 **Accent** : Violet/Mauve (#9d4edd)
- 🎯 **Coins** : Arrondis (8px)
- ✨ **Animations** : Smooth (300ms easing)
- 🔹 **Typographie** : Inter / Roboto Mono

## 📝 Configuration

Tous les modules sont sauvegardables via JSON :
```json
{
  "modules": {
    "aura": { "enabled": true, "range": 6.0, "mode": "single" },
    "autoTotem": { "enabled": true, "health": 0.5 }
  }
}
```

## 🛠️ Stack Technique

- **Launcher** : Electron + React + TypeScript
- **Client** : Fabric Mod Loader
- **GUI** : Custom ImGui-like system
- **Build** : Gradle + Electron Builder

## 📄 License

Propriétaire - Développement privé

---

**Fullrageedon3526** - *Unleash the full potential of your game.*

Made with ❤️ by edin3037-art
