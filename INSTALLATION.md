# 🎮 Installation & Setup Guide

## Prérequis

- **Java 17+** (Required for Fabric)
- **Gradle** (Pour build le client)
- **Node.js 16+** (Pour le launcher)
- **.minecraft** directory standard

## 🚀 Installation Rapide

### 1. Launcher (Electron + React)

```bash
# Naviguer au dossier launcher
cd launcher

# Installer les dépendances
npm install

# Mode développement
npm run dev

# Build production
npm run build
```

**Résultat**: Application Electron déployable sur Windows/Mac/Linux

### 2. Client Minecraft (Fabric)

```bash
# Naviguer au dossier client
cd client

# Builder avec Gradle
./gradlew build

# Le jar est généré dans: build/libs/fullrageedon-client-1.0.0.jar
```

**Installation du mod**:
1. Copier `fullrageedon-client-1.0.0.jar` vers `~/.minecraft/mods/`
2. Lancer Minecraft avec Fabric Loader
3. Appuyer sur **Right Shift** pour ouvrir le ClickGUI

## ⌨️ Contrôles Principaux

| Touche | Action |
|--------|--------|
| **Right Shift** | Open/Close ClickGUI |
| **Middle Click** | Toggle Friend |
| **Right Click** (ClickFriend) | Mark as Friend |
| **Scroll** | Navigate Categories |

## 🎨 Configuration

Tous les paramètres sont sauvegardés dans:
```
~/.minecraft/fullrageedon/config.json
```

### Format de Configuration

```json
{
  "modules": {
    "aura": {
      "enabled": true,
      "range": 6.0,
      "keyCode": -1
    },
    "autoTotem": {
      "enabled": true,
      "healthThreshold": 3.0,
      "keyCode": -1
    }
  }
}
```

## 🛠️ Build Personnalisé

### Modifier la version Minecraft

Éditer `client/gradle.properties`:
```properties
minecraft_version=1.20.1
loader_version=0.15.3
```

Soutenues: **1.8.9**, **1.12.2**, **1.20.1**, **1.20.4**

### Ajouter un nouveau module

1. Créer une classe dans `client/src/main/java/com/fullrageedon/modules/[category]/`
2. Hériter de `Module`
3. Implémenter `onEnable()`, `onDisable()`, `onTick()`
4. Enregistrer dans `ModuleManager.registerAllModules()`

```java
public class MyModule extends Module {
    public MyModule() {
        super("MyModule", "Description", "Category");
    }

    @Override
    public void onEnable() { }

    @Override
    public void onDisable() { }

    @Override
    public void onTick() {
        // Logic here
    }
}
```

## 🔒 Anti-Cheat Bypass (BzPass)

Le système BzPass est intégré dans le launcher. Activer depuis:

**Menu Principal → Anti-Cheat Bypass**

Systèmes supportés:
- ✅ FLAG Anti-Cheat
- ✅ Warden (Microsoft)
- ✅ BattlEye
- ✅ Easy Anti-Cheat (EAC)
- ✅ GameGuard
- ✅ nProtect
- ✅ XignCode3
- ✅ Valve Anti-Cheat (VAC)

## 🎯 Troubleshooting

### Problem: ClickGUI ne s'ouvre pas

**Solution**:
```bash
# Vérifier que Right Shift n'est pas bindé ailleurs
# Vérifier que le mod est bien chargé
# Voir les logs Fabric
```

### Problem: Modules ne se sauvegardent pas

**Solution**:
```bash
# Vérifier les permissions du dossier:
chmod -R 755 ~/.minecraft/fullrageedon/

# Supprimer et recréer config.json
rm ~/.minecraft/fullrageedon/config.json
```

### Problem: Crash au démarrage

**Solution**:
```bash
# Vérifier la version Fabric Loader
./gradlew --refresh-dependencies clean build

# Vérifier les dépendances manquantes
./gradlew dependencies
```

## 📦 Distribution

### Package Launcher

```bash
cd launcher
npm run dist  # Crée des installers pour Windows/Mac/Linux
```

### Package Client

```bash
cd client
./gradlew build  # JAR compilé prêt à distribuer
```

## 📝 Logs

- **Launcher**: `~/.fullrageedon/logs/`
- **Client**: `.minecraft/logs/latest.log`

## ⚖️ Disclaimer

Cet outil est fourni à titre éducatif. L'utilisation sur des serveurs publics peut entraîner des bans. À utiliser sur des serveurs privés uniquement.

---

**Besoin d'aide?** Consultez la documentation complète ou créez une issue GitHub.
