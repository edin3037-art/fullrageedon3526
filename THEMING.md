# 🎨 Theming & Customization Guide

## Color Scheme

### Palette Standard

```
Primary (Violet):     #9d4edd RGB(157, 78, 221)
Accent (Lime):        #00ff88 RGB(0, 255, 136)
Secondary (Cyan):     #00d4ff RGB(0, 212, 255)
Dark BG:              #0a0a0a RGB(10, 10, 10)
Panel BG:             #1e1e2e RGB(30, 30, 46)
Text:                 #e0e0e0 RGB(224, 224, 224)
Subtext:              #b0b0c0 RGB(176, 176, 192)
```

## Customizing Launcher Colors

### Edit `launcher/src/App.css`

```css
.btn {
    border: 2px solid #9d4edd;        /* Primary color */
    color: #e0e0e0;                   /* Text color */
}

.btn:hover {
    box-shadow: 0 0 20px rgba(157, 78, 221, 0.4);
}
```

### Preset Themes

#### 1. Dark Violet (Default)
```css
--primary: #9d4edd;
--accent: #00ff88;
--bg: #0a0a0a;
```

#### 2. Neon Cyber
```css
--primary: #00ffff;  /* Cyan */
--accent: #ff00ff;   /* Magenta */
--bg: #000000;       /* Pure black */
```

#### 3. Matrix Green
```css
--primary: #00ff00;
--accent: #00dd00;
--bg: #0a0a0a;
```

#### 4. Blood Red
```css
--primary: #ff0000;
--accent: #ff3333;
--bg: #1a0a0a;
```

## Customizing ClickGUI Colors

### Edit `client/src/main/java/com/fullrageedon/ui/ClickGUI.java`

```java
private static final int[] PRIMARY_COLOR = {157, 78, 221};    // Change this
private static final int[] ACCENT_COLOR = {0, 255, 136};      // Change this
private static final int[] DARK_BG = {10, 10, 10};            // Change this
private static final int[] PANEL_BG = {30, 30, 46};           // Change this
```

## Scanlines Effect

### Intensité des scanlines

```java
// Dans ClickGUI.drawScanlines()
int scanlineColor = (int) (24 * animationProgress) << 24 | 0x000000;
// Augmenter 24 pour des scanlines plus visibles
```

## Animation Customization

### Duration

```java
private static final int ANIMATION_DURATION = 15;  // Milliseconds
// Réduire pour animation plus rapide
```

### Easing Functions

```java
private float easeOutCubic(float t) {
    t = 1.0f - t;
    return 1.0f - (t * t * t);
}

// Autres easings:
// easeInCubic, easeInOutCubic, easeInQuad, easeOutQuad...
```

## Font Customization

### Launcher

```css
body {
    font-family: 'Inter', 'Roboto', -apple-system, BlinkMacSystemFont;
}
```

### Remplacer par:
- `'JetBrains Mono'` - Code-like appearance
- `'Source Code Pro'` - Professional
- `'Fira Code'` - Tech aesthetic

## Custom Module Styling

### Modifier l'apparence d'un module

```java
// Dans Category.render()
int moduleBg = moduleHovered ? rgbToInt(50, 30, 80) : bgColor;
if (module.isEnabled()) {
    moduleBg = rgbToInt(60, 100, 60);  // Green when enabled
}
```

## HUD Visual Effects

### Hologram Effect

```java
private void drawHologramEffect(DrawContext context, String text, int x, int y, int color) {
    float offset = (float) Math.sin(animationTick / 15.0f) * 2.0f;
    for (int i = 0; i < 3; i++) {
        int alpha = (255 - (i * 85)) << 24;
        int offsetY = (int) (y + (i * offset));
        // Draw text with reduced alpha
    }
}
```

### Pulse Effect

```java
private void drawPulsingText(DrawContext context, String text, int x, int y, int color) {
    float pulse = (float) Math.sin(animationTick / 10.0f) * 0.5f + 0.5f;
    int alpha = (int) (255 * pulse) << 24;
    // Draw with pulsing alpha
}
```

## Layout Customization

### Category Panel Dimensions

```java
private static final int CATEGORY_WIDTH = 110;     // Adjust width
private static final int PANEL_HEIGHT = 20;        // Adjust height
private static final int MARGIN = 10;              // Space between
```

### Starting Position

```java
private static final int START_X = 20;             // X offset from left
private static final int START_Y = 20;             // Y offset from top
```

## Creating Custom Themes

### Theme Template

```java
public class DarkVioletTheme extends Theme {
    @Override
    public int getPrimaryColor() {
        return rgbToInt(157, 78, 221);
    }

    @Override
    public int getAccentColor() {
        return rgbToInt(0, 255, 136);
    }

    @Override
    public int getBackgroundColor() {
        return rgbToInt(10, 10, 10);
    }

    @Override
    public int getPanelColor() {
        return rgbToInt(30, 30, 46);
    }
}
```

## Accessibility

### High Contrast Mode

```css
.btn {
    border: 3px solid #ffffff;  /* Thicker border */
    font-weight: bold;           /* Bold text */
    font-size: 16px;             /* Larger text */
}
```

### Reduced Motion

```css
@media (prefers-reduced-motion: reduce) {
    * {
        animation-duration: 0.01ms !important;
        transition-duration: 0.01ms !important;
    }
}
```

---

**Pro Tips:**
- Use online color pickers to test combinations
- Export your custom theme for sharing
- Test on different monitor brightness levels
