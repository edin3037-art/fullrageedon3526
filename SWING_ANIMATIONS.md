# 🎯 Swing Animations Guide

## Overview

Fullrageedon3526 includes an advanced **Swing Animations System** that provides multiple attack animation modes for combat customization.

## 🌊 Available Swing Modes

### 1. Normal Swing (Default)

**Description**: Standard Minecraft attack speed

**Features**:
- Vanilla-like attack speed
- Standard swing progress (0.1f per tick)
- Suitable for vanilla gameplay

**Usage**:
```java
swingAnimations.setMode("Normal");
```

### 2. Slow Swing 🐢

**Description**: Very slow and dramatic attack animation

**Features**:
- Progress: 0.03f per tick (3x slower)
- Dramatic effect for combat style
- Better for precision timing
- Visual feedback for hits

**Usage**:
```java
swingAnimations.setMode("Slow");
// Creates dramatic, slow-motion combat feel
```

### 3. Custom Speed ⚙️

**Description**: User-defined swing speed

**Features**:
- Configurable speed multiplier (0.1 - 3.0)
- Real-time adjustment
- Fast or slow based on preference
- Visual indicators for speed

**Usage**:
```java
swingAnimations.setMode("Custom");
swingAnimations.setCustomSpeed(1.5f);  // 1.5x speed
swingAnimations.setCustomSpeed(0.5f);  // 0.5x speed (slow)
```

### 4. Delayed Swing ⏱️

**Description**: Attack with a built-in delay (for combos)

**Features**:
- 5 tick delay before swing
- Perfect for combo timing
- Synchronized chain attacks
- Cinematic feel

**Usage**:
```java
swingAnimations.setMode("Delayed");
```

### 5. Spin Swing 🌀

**Description**: Rotational attack animation

**Features**:
- Player rotates 360° during swing
- Dynamic combat feel
- Rotation adds 0-360° per swing cycle
- Great for stylish plays

**Usage**:
```java
swingAnimations.setMode("Spin");
// Player spins while attacking
```

### 6. Reverse Swing 🔄

**Description**: Attack animation plays backward

**Features**:
- Negative swing progress
- Unique visual effect
- Confuses enemies
- Experimental mode

**Usage**:
```java
swingAnimations.setMode("Reverse");
```

### 7. Wave Swing 〰️

**Description**: Sinusoidal motion for smooth flow

**Features**:
- Math.sin() based motion
- Smooth, flowing attack pattern
- Velocity modulation
- Elegant combat style

**Usage**:
```java
swingAnimations.setMode("Wave");
// Creates smooth wave-like motion
```

### 8. Teleport Swing ⚡

**Description**: Player teleports between swings

**Features**:
- Fast-hitting effect
- 0.5 block forward teleport at 50% swing
- Rapid combo potential
- High-skill mode

**Usage**:
```java
swingAnimations.setMode("Teleport");
// Teleports forward while swinging
```

## 🎮 Advanced Features

### Dual-Wield Swings

```java
// Attack with both hands simultaneously
swingAnimations.dualWieldSwing(mc);
```

**Result**: Both Main Hand and Off-Hand swing at the same time

### Rapid Swings

```java
// Multiple quick successive attacks
swingAnimations.rapidSwings(mc, 5);  // 5 quick swings
```

**Usage**: Great for burst damage combos

### Combo System

```java
enum SwingCombo {
    FAST_3x("Fast 3-Hit Combo"),
    HEAVY_SLOW("Heavy Slow Attack"),
    SPINNING_ATTACK("Spinning Attack")
}
```

**Using Combos**:
```java
swingAnimations.comboSwing(mc, SwingCombo.FAST_3x);
swingAnimations.comboSwing(mc, SwingCombo.HEAVY_SLOW);
swingAnimations.comboSwing(mc, SwingCombo.SPINNING_ATTACK);
```

## 🎯 Swing Speed Customization

### Speed Ranges

```
0.1  - Extremely Slow (1/10 speed)
0.5  - Very Slow (1/2 speed)
1.0  - Normal Speed (default)
1.5  - Fast (1.5x speed)
2.0  - Very Fast (2x speed)
3.0  - Maximum Speed (3x speed)
```

### Setting Custom Speed

```java
swingAnimations.setMode("Custom");
swingAnimations.setCustomSpeed(0.3f);  // Slow motion
swingAnimations.setCustomSpeed(2.5f);  // Lightning fast
```

## 🎨 Visual Effects with Swings

### Speed-Based Color Feedback

```
Fast Attacks (>1.5x)   → Red Tint (aggressive)
Normal Attacks (1.0x)  → Default Colors
Slow Attacks (<0.5x)   → Blue Tint (tactical)
```

### Particle Effects

- **Spin Swing**: Particle trail during rotation
- **Wave Swing**: Wave particles following motion
- **Teleport Swing**: Teleportation particles

## 🔧 Configuration

### In ClickGUI

1. Open ClickGUI (Right Shift)
2. Navigate to **Combat** category
3. Select **SwingAnimations**
4. Choose mode from dropdown
5. Adjust speed slider if Custom mode

### In Code

```java
SwingAnimations swings = (SwingAnimations) moduleManager.getModule("SwingAnimations");
swings.enable();
swings.setMode("Slow");
swings.setCustomSpeed(0.5f);
```

### Save Configuration

All swing settings are automatically saved to:
```
~/.minecraft/fullrageedon/config.json
```

## ⚔️ Combat Applications

### PvP Strategy

**Fast Mode** (2.0-3.0x):
- Spam clicking for burst damage
- Overwhelming opponents
- Fast combo chains

**Normal Mode** (1.0x):
- Balanced play
- Standard PvP rhythm
- Vanilla compatibility

**Slow Mode** (0.3-0.5x):
- Precision timing
- Tactical positioning
- Dramatic comebacks

**Spin Mode**:
- Style points
- Confuse enemies
- Show dominance

### Combo Chains

```
FAST_3x     → Fast 3 hits with 2-tick delay
HEAVY_SLOW  → Single powerful slow attack
SPINNING    → 360° rotation while attacking
```

## 📊 Performance Impact

| Mode | FPS Cost | CPU Usage | Recommended |
|------|----------|-----------|-------------|
| Normal | ~0 | Minimal | Always |
| Slow | ~1 | Low | Yes |
| Custom | ~1-2 | Low | Yes |
| Delayed | ~1 | Low | Yes |
| Spin | ~3-5 | Medium | Combat only |
| Reverse | ~1 | Low | Testing |
| Wave | ~2-3 | Low-Medium | Yes |
| Teleport | ~5-8 | Medium | PvP only |

## 🎬 Demo Scenarios

### Scenario 1: Fast Aggressive Combat

```java
swingAnimations.setMode("Custom");
swingAnimations.setCustomSpeed(2.5f);
swingAnimations.rapidSwings(mc, 10);
```

**Result**: Overwhelming burst damage

### Scenario 2: Stylish Duel

```java
swingAnimations.setMode("Spin");
swingAnimations.dualWieldSwing(mc);
```

**Result**: Spinning dual-wield attack

### Scenario 3: Tactical Timing

```java
swingAnimations.setMode("Slow");
swingAnimations.comboSwing(mc, SwingCombo.HEAVY_SLOW);
```

**Result**: Slow, calculated attacks

## 🚀 Advanced Techniques

### Swing Chaining

```java
// Chain multiple swing types
swingAnimations.setMode("Fast");
swingAnimations.dualWieldSwing(mc);
Thread.sleep(100);
swingAnimations.setMode("Spin");
swingAnimations.comboSwing(mc, SwingCombo.SPINNING_ATTACK);
```

### Dynamic Speed Adjustment

```java
// Adjust speed based on target distance
float distance = player.distanceTo(target);
if (distance < 3.0f) {
    swingAnimations.setCustomSpeed(2.5f);  // Close: fast
} else {
    swingAnimations.setCustomSpeed(0.8f);  // Far: slow
}
```

## ⚠️ Anti-Cheat Considerations

**Detection Risk**:
- Spin/Teleport modes: High risk ⚠️
- Custom speed: Medium risk ⚡
- Slow/Wave: Low risk ✓

**Recommendations**:
- Use on private servers only
- Mix different modes to avoid patterns
- Keep within reasonable speed ranges

## 📝 Config Example

```json
{
  "modules": {
    "swingAnimations": {
      "enabled": true,
      "mode": "Wave",
      "customSpeed": 1.3,
      "keyCode": -1
    }
  }
}
```

---

**Master the Swing System for dominance in combat!** ⚔️
