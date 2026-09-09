package com.fullrageedon.ui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import com.fullrageedon.modules.Module;
import com.fullrageedon.modules.ModuleManager;
import com.fullrageedon.FullrageedonClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Advanced ClickGUI with smooth animations and categories
 */
public class ClickGUI extends Screen {
    private boolean open = false;
    private List<Category> categories = new ArrayList<>();
    private float animationProgress = 0.0f;
    private static final int ANIMATION_DURATION = 15;
    private static final int CATEGORY_WIDTH = 110;
    private static final int PANEL_HEIGHT = 20;
    private static final int MARGIN = 10;
    private static final int START_X = 20;
    private static final int START_Y = 20;
    private static final int[] PRIMARY_COLOR = {157, 78, 221}; // #9d4edd
    private static final int[] ACCENT_COLOR = {0, 255, 136}; // #00ff88
    private static final int[] DARK_BG = {10, 10, 10};
    private static final int[] PANEL_BG = {30, 30, 46};

    public ClickGUI() {
        super(Text.literal("ClickGUI"));
        initializeCategories();
    }

    private void initializeCategories() {
        categories.add(new Category("Combat", 0));
        categories.add(new Category("Movement", 1));
        categories.add(new Category("Player", 2));
        categories.add(new Category("Render", 3));
        categories.add(new Category("Misc", 4));

        // Add modules to categories
        ModuleManager manager = FullrageedonClient.moduleManager;
        if (manager != null) {
            for (Module module : manager.getAllModules().values()) {
                for (Category cat : categories) {
                    if (cat.name.equals(module.getCategory())) {
                        cat.addModule(module);
                        break;
                    }
                }
            }
        }
    }

    public void toggle() {
        open = !open;
        if (open) {
            animationProgress = 0.0f;
            MinecraftClient.getInstance().setScreen(this);
        } else {
            MinecraftClient.getInstance().setScreen(null);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Update animation
        if (open && animationProgress < 1.0f) {
            animationProgress = Math.min(1.0f, animationProgress + 1.0f / ANIMATION_DURATION);
        }

        // Draw background with scanlines
        drawBackground(context);
        drawScanlines(context);

        // Draw title
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            "FULLRAGEEDON 3526",
            this.width / 2,
            10,
            rgbToInt(ACCENT_COLOR[0], ACCENT_COLOR[1], ACCENT_COLOR[2])
        );

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            "ClickGUI - Right Shift to Close",
            this.width / 2,
            20,
            rgbToInt(PRIMARY_COLOR[0], PRIMARY_COLOR[1], PRIMARY_COLOR[2])
        );

        // Draw categories with animation
        int xOffset = START_X;
        for (int i = 0; i < categories.size(); i++) {
            Category cat = categories.get(i);
            int catX = xOffset + (i * (CATEGORY_WIDTH + MARGIN));
            float animScale = easeOutCubic(animationProgress);
            int animY = (int) (START_Y + 40 + (1.0f - animScale) * 20);

            cat.render(context, catX, animY, mouseX, mouseY, this.textRenderer);
        }
    }

    private void drawBackground(DrawContext context) {
        int bgColor = rgbToInt(DARK_BG[0], DARK_BG[1], DARK_BG[2]);
        context.fill(0, 0, this.width, this.height, bgColor);

        // Draw semi-transparent overlay
        context.fill(
            0, 0, this.width, this.height,
            (int) (animationProgress * 64) << 24 | 0x0a0a0a
        );
    }

    private void drawScanlines(DrawContext context) {
        int scanlineColor = (int) (24 * animationProgress) << 24 | 0x000000;
        for (int y = 0; y < this.height; y += 2) {
            context.fill(0, y, this.width, y + 1, scanlineColor);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        for (Category cat : categories) {
            if (cat.mouseScroll(mouseX, mouseY, verticalAmount)) {
                return true;
            }
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Category cat : categories) {
            if (cat.mouseClicked(mouseX, mouseY, button)) {
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 340) { // RIGHT_SHIFT
            toggle();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void close() {
        open = false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    private int rgbToInt(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    private float easeOutCubic(float t) {
        t = 1.0f - t;
        return 1.0f - (t * t * t);
    }

    /**
     * Category class for organizing modules
     */
    private class Category {
        String name;
        int index;
        List<Module> modules = new ArrayList<>();
        boolean expanded = true;
        int scrollOffset = 0;
        private static final int MODULE_HEIGHT = 16;
        private static final int VISIBLE_MODULES = 8;

        Category(String name, int index) {
            this.name = name;
            this.index = index;
        }

        void addModule(Module module) {
            modules.add(module);
        }

        void render(DrawContext context, int x, int y, int mouseX, int mouseY, net.minecraft.client.font.TextRenderer textRenderer) {
            int bgColor = rgbToInt(PANEL_BG[0], PANEL_BG[1], PANEL_BG[2]);
            int borderColor = rgbToInt(PRIMARY_COLOR[0], PRIMARY_COLOR[1], PRIMARY_COLOR[2]);
            int accentColor = rgbToInt(ACCENT_COLOR[0], ACCENT_COLOR[1], ACCENT_COLOR[2]);

            // Category header with hover effect
            boolean headerHovered = mouseX >= x && mouseX < x + CATEGORY_WIDTH &&
                                   mouseY >= y && mouseY < y + PANEL_HEIGHT;

            int headerBg = headerHovered ? rgbToInt(60, 40, 100) : bgColor;
            context.fill(x, y, x + CATEGORY_WIDTH, y + PANEL_HEIGHT, headerBg | 0xFF000000);
            context.drawBorder(x, y, CATEGORY_WIDTH, PANEL_HEIGHT, borderColor);

            // Draw category name
            context.drawTextWithShadow(
                textRenderer,
                this.name + (expanded ? " ▼" : " ▶"),
                x + 5,
                y + 6,
                accentColor
            );

            if (expanded) {
                // Draw modules
                int moduleY = y + PANEL_HEIGHT;
                int visibleCount = Math.min(VISIBLE_MODULES, modules.size() - scrollOffset);

                for (int i = scrollOffset; i < scrollOffset + visibleCount; i++) {
                    if (i >= modules.size()) break;

                    Module module = modules.get(i);
                    boolean moduleHovered = mouseX >= x && mouseX < x + CATEGORY_WIDTH &&
                                          mouseY >= moduleY && mouseY < moduleY + MODULE_HEIGHT;

                    int moduleBg = moduleHovered ? rgbToInt(50, 30, 80) : bgColor;
                    if (module.isEnabled()) {
                        moduleBg = rgbToInt(60, 100, 60);
                    }
                    context.fill(x, moduleY, x + CATEGORY_WIDTH, moduleY + MODULE_HEIGHT, moduleBg | 0xFF000000);
                    context.drawBorder(x, moduleY, CATEGORY_WIDTH, MODULE_HEIGHT, borderColor);

                    String status = module.isEnabled() ? "✓" : "○";
                    context.drawTextWithShadow(
                        textRenderer,
                        status + " " + module.getName(),
                        x + 5,
                        moduleY + 4,
                        module.isEnabled() ? rgbToInt(0, 255, 136) : rgbToInt(176, 176, 192)
                    );

                    moduleY += MODULE_HEIGHT;
                }
            }
        }

        boolean mouseClicked(double mouseX, double mouseY, int button) {
            int x = START_X + (index * (CATEGORY_WIDTH + MARGIN));
            int y = START_Y + 40;

            // Check header click
            if (mouseX >= x && mouseX < x + CATEGORY_WIDTH &&
                mouseY >= y && mouseY < y + PANEL_HEIGHT) {
                expanded = !expanded;
                return true;
            }

            if (expanded) {
                int moduleY = y + PANEL_HEIGHT;
                for (int i = scrollOffset; i < modules.size(); i++) {
                    if (i >= scrollOffset + VISIBLE_MODULES) break;

                    if (mouseX >= x && mouseX < x + CATEGORY_WIDTH &&
                        mouseY >= moduleY && mouseY < moduleY + MODULE_HEIGHT) {
                        modules.get(i).toggle();
                        return true;
                    }
                    moduleY += MODULE_HEIGHT;
                }
            }

            return false;
        }

        boolean mouseScroll(double mouseX, double mouseY, double amount) {
            int x = START_X + (index * (CATEGORY_WIDTH + MARGIN));
            int y = START_Y + 40;

            if (mouseX >= x && mouseX < x + CATEGORY_WIDTH &&
                mouseY >= y && mouseY < y + (VISIBLE_MODULES + 1) * PANEL_HEIGHT) {
                scrollOffset -= (int) amount;
                scrollOffset = Math.max(0, Math.min(scrollOffset, Math.max(0, modules.size() - VISIBLE_MODULES)));
                return true;
            }
            return false;
        }
    }
}
