# 🐛 Troubleshooting & FAQ

## Common Issues

### Issue: ClickGUI ne s'ouvre pas avec Right Shift

**Cause**: Right Shift peut être bindé à un autre raccourci système

**Solution**:
```bash
# Option 1: Modifier le keybinding dans le code
# Éditer: client/src/main/java/com/fullrageedon/FullrageedonClient.java
# Remplacer GLFW.GLFW_KEY_RIGHT_SHIFT par GLFW.GLFW_KEY_R

# Option 2: Vérifier les logs Fabric
cat .minecraft/logs/latest.log | grep -i clickgui
```

### Issue: Modules ne se chargent pas

**Cause**: Le fichier de configuration est corrompu

**Solution**:
```bash
# Supprimer et recréer la configuration
rm ~/.minecraft/fullrageedon/config.json
# Relancer Minecraft
```

### Issue: Crash au démarrage avec Aura

**Cause**: Exception dans la boucle de recherche de cible

**Solution**:
```java
// Dans Aura.java - Ajouter null checks
if (target != null && !target.isRemoved()) {
    rotateToEntity(target);
}
```

### Issue: FPS drops avec ESP activé

**Cause**: Rendu des boîtes ESP pour tous les joueurs

**Solution**:
```java
// Limiter la distance
if (mc.player.distanceTo(player) < 50) {
    drawESP(player);
}
```

### Issue: Launcher n'apparaît pas

**Cause**: Problème avec Electron

**Solution**:
```bash
# Réinstaller les dépendances
cd launcher
rm -rf node_modules package-lock.json
npm install
npm start
```

## Performance Optimization

### Réduire les FPS drops

1. **Désactiver les modules non-utilisés**
```java
// Dans ClickGUI, désactiver AutoTrap, Tracers, ESP
```

2. **Limiter la distance de rendu**
```java
float range = 30.0f;  // Réduire de 50 à 30
```

3. **Optimiser la boucle de tick**
```java
// Good - Condition check
if (mc.player.isOnGround()) {
    // Do something
}

// Bad - No condition
// Do something every tick
```

### Mémoire

```bash
# Augmenter heap size Minecraft
JVM Args: -Xmx4G -Xms2G
```

## Debugging

### Activer les logs détaillés

```java
// Dans n'importe quel module
System.out.println("[Fullrageedon] Module: " + getName());
System.out.println("[Fullrageedon] Enabled: " + isEnabled());
```

### Vérifier les erreurs Fabric

```bash
tail -f ~/.minecraft/logs/latest.log | grep -i "fullrageedon\|error\|exception"
```

### Débugger le ClickGUI

```java
// Ajouter dans ClickGUI.render()
System.out.println("Categories: " + categories.size());
for (Category cat : categories) {
    System.out.println("  - " + cat.name + ": " + cat.modules.size());
}
```

## Anti-Cheat Bypass Issues

### Problem: BzPass détecté

**Cause**: Anti-cheat a des signatures mises à jour

**Solution**:
- Attendre la mise à jour du client
- Utiliser sur serveurs privés
- Désactiver certains modules sensibles

### Problem: Banissement permanent

**Cause**: Utilisation sur serveur anticheat fort

**Solution**:
- Lire les règles du serveur
- Utiliser uniquement sur serveurs autorisés
- Créer des alts pour tester

## Config Issues

### Problem: Modules ne se sauvegardent pas

**Vérifier**:
```bash
# Vérifier le fichier existe
ls -la ~/.minecraft/fullrageedon/config.json

# Vérifier les permissions
chmod 644 ~/.minecraft/fullrageedon/config.json

# Vérifier le format JSON
jq . ~/.minecraft/fullrageedon/config.json
```

### Problem: Config corrompue

**Solution**:
```bash
# Créer une config par défaut
cat > ~/.minecraft/fullrageedon/config.json << 'EOF'
{
  "modules": {}
}
EOF
```

## Installation Issues

### Problem: Gradle build fails

**Solution**:
```bash
cd client

# Option 1: Clean build
./gradlew clean build

# Option 2: Refresh dependencies
./gradlew --refresh-dependencies build

# Option 3: Vérifier Java version
java -version  # Doit être 17+
```

### Problem: Fabric Loader not found

**Solution**:
```bash
# Éditer gradle.properties et vérifier les versions
cat client/gradle.properties

# Mettre à jour si nécessaire
minecraft_version=1.20.1
loader_version=0.15.3
```

## Launcher Issues

### Problem: White screen

**Solution**:
```bash
cd launcher

# Supprimer cache
rm -rf node_modules .cache dist

# Réinstaller et rebuild
npm install
npm start
```

### Problem: Server not connecting

**Solution**:
1. Vérifier l'adresse du serveur
2. Vérifier le port (par défaut 25565)
3. Vérifier les logs du serveur

## Getting Help

### Ressources
- 📖 [README](README.md) - Overview
- 🛠️ [INSTALLATION.md](INSTALLATION.md) - Setup guide
- 👨‍💻 [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md) - Development
- 🎨 [THEMING.md](THEMING.md) - Customization

### Report Issues
```
GitHub Issues: https://github.com/edin3037-art/fullrageedon3526/issues
```

### FAQ Checklist
- [ ] Avez-vous lu le README?
- [ ] Vérifiez-vous Java 17+?
- [ ] Vérifiez-vous les logs?
- [ ] Avez-vous essayé clean rebuild?
- [ ] Avez-vous supprimé le cache?

---

**Still stuck?** Create a detailed GitHub issue with:
1. Error messages
2. Logs
3. Steps to reproduce
4. Your system info (OS, Java version, Minecraft version)
