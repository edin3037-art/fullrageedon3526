package com.fullrageedon.modules.combat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import com.fullrageedon.modules.Module;

/**
 * Advanced Swing Animation System
 * Supports: Normal, Slow, Custom Speed, Delayed, Spin, Reverse
 */
public class SwingAnimations extends Module {
    private String mode = "Normal";
    private float customSpeed = 1.0f;
    private float swingProgress = 0.0f;
    private int swingDelay = 0;
    private boolean shouldSwing = false;
    private Hand lastHand = Hand.MAIN_HAND;

    public SwingAnimations() {
        super("SwingAnimations", "Advanced attack swing animations", "Combat");
    }

    @Override
    public void onEnable() {
        System.out.println("[Fullrageedon] Swing Animations enabled - Mode: " + mode);
    }

    @Override
    public void onDisable() {
        swingProgress = 0.0f;
        swingDelay = 0;
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        // Handle swing delay
        if (swingDelay > 0) {
            swingDelay--;
            return;
        }

        // Update swing progress based on mode
        switch (mode) {
            case "Normal":
                updateNormalSwing(mc);
                break;
            case "Slow":
                updateSlowSwing(mc);
                break;
            case "Custom":
                updateCustomSwing(mc);
                break;
            case "Delayed":
                updateDelayedSwing(mc);
                break;
            case "Spin":
                updateSpinSwing(mc);
                break;
            case "Reverse":
                updateReverseSwing(mc);
                break;
            case "Wave":
                updateWaveSwing(mc);
                break;
            case "Teleport":
                updateTeleportSwing(mc);
                break;
        }
    }

    /**
     * Normal Swing - Standard attack speed
     */
    private void updateNormalSwing(MinecraftClient mc) {
        if (mc.player.isAttacking()) {
            swingProgress += 0.1f;
            if (swingProgress >= 1.0f) {
                swingProgress = 0.0f;
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }

    /**
     * Slow Swing - Very slow and dramatic attack
     * Ideal for combos and style points
     */
    private void updateSlowSwing(MinecraftClient mc) {
        if (mc.player.isAttacking()) {
            swingProgress += 0.03f;  // Much slower progression
            if (swingProgress >= 1.0f) {
                swingProgress = 0.0f;
                mc.player.swingHand(Hand.MAIN_HAND);
            }
            applySlowSwingAnimation(mc);
        }
    }

    /**
     * Custom Speed Swing - User-defined speed
     */
    private void updateCustomSwing(MinecraftClient mc) {
        if (mc.player.isAttacking()) {
            swingProgress += (0.05f * customSpeed);
            if (swingProgress >= 1.0f) {
                swingProgress = 0.0f;
                mc.player.swingHand(Hand.MAIN_HAND);
            }
            applyCustomSpeedAnimation(mc);
        }
    }

    /**
     * Delayed Swing - Swing after a delay (combos)
     */
    private void updateDelayedSwing(MinecraftClient mc) {
        if (mc.player.isAttacking()) {
            swingDelay = 5;  // 5 tick delay before swing
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    /**
     * Spin Swing - Rotational attack animation
     * Player rotates while attacking
     */
    private void updateSpinSwing(MinecraftClient mc) {
        if (mc.player.isAttacking()) {
            swingProgress += 0.08f;
            
            // Rotate player during swing
            float rotation = swingProgress * 360.0f;
            mc.player.setYaw(mc.player.getYaw() + rotation);
            
            if (swingProgress >= 1.0f) {
                swingProgress = 0.0f;
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }

    /**
     * Reverse Swing - Swing animation goes backward
     */
    private void updateReverseSwing(MinecraftClient mc) {
        if (mc.player.isAttacking()) {
            swingProgress -= 0.08f;  // Negative progression
            if (swingProgress <= 0.0f) {
                swingProgress = 1.0f;
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }

    /**
     * Wave Swing - Sinusoidal motion for smooth flow
     */
    private void updateWaveSwing(MinecraftClient mc) {
        if (mc.player.isAttacking()) {
            swingProgress += 0.05f;
            
            // Sinusoidal motion
            float waveAmount = (float) Math.sin(swingProgress * Math.PI) * 0.3f;
            mc.player.setVelocity(
                mc.player.getVelocity().x + waveAmount,
                mc.player.getVelocity().y,
                mc.player.getVelocity().z + waveAmount
            );
            
            if (swingProgress >= 1.0f) {
                swingProgress = 0.0f;
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }

    /**
     * Teleport Swing - Player teleports between swings
     * Creates fast-hitting effect
     */
    private void updateTeleportSwing(MinecraftClient mc) {
        if (mc.player.isAttacking()) {
            swingProgress += 0.12f;
            
            if (swingProgress >= 0.5f && swingProgress < 0.6f) {
                // Teleport slightly forward
                mc.player.setPosition(
                    mc.player.getX() + mc.player.getForward().x * 0.5f,
                    mc.player.getY(),
                    mc.player.getZ() + mc.player.getForward().z * 0.5f
                );
            }
            
            if (swingProgress >= 1.0f) {
                swingProgress = 0.0f;
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }

    /**
     * Apply visual effects for slow swing
     */
    private void applySlowSwingAnimation(MinecraftClient mc) {
        // Add particle effects or camera shake for style
        float armRotation = swingProgress * 360.0f;
        // Apply rotation to arm rendering (requires mixin)
    }

    /**
     * Apply visual effects for custom speed
     */
    private void applyCustomSpeedAnimation(MinecraftClient mc) {
        // Speed-based visual feedback
        if (customSpeed > 1.5f) {
            // Fast attack - add red tint
        } else if (customSpeed < 0.5f) {
            // Slow attack - add blue tint
        }
    }

    /**
     * Dual-wield swing - Attack with both hands
     */
    public void dualWieldSwing(MinecraftClient mc) {
        if (mc.player != null) {
            mc.player.swingHand(Hand.MAIN_HAND);
            mc.player.swingHand(Hand.OFF_HAND);
        }
    }

    /**
     * Rapid swings - Multiple quick attacks
     */
    public void rapidSwings(MinecraftClient mc, int count) {
        if (mc.player != null) {
            for (int i = 0; i < count; i++) {
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }

    /**
     * Combo swing - Sequential attacks with timing
     */
    public void comboSwing(MinecraftClient mc, SwingCombo combo) {
        if (mc.player == null) return;
        
        switch (combo) {
            case FAST_3x:
                // Fast 3 hit combo
                for (int i = 0; i < 3; i++) {
                    swingDelay = i * 2;
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
                break;
            case HEAVY_SLOW:
                // Slow heavy attack
                mode = "Slow";
                mc.player.swingHand(Hand.MAIN_HAND);
                break;
            case SPINNING_ATTACK:
                // Spinning attack combo
                mode = "Spin";
                mc.player.swingHand(Hand.MAIN_HAND);
                break;
        }
    }

    // Getters and Setters
    public void setMode(String mode) {
        this.mode = mode;
        System.out.println("[Swing] Mode set to: " + mode);
    }

    public String getMode() {
        return mode;
    }

    public void setCustomSpeed(float speed) {
        this.customSpeed = Math.max(0.1f, Math.min(3.0f, speed));
    }

    public float getCustomSpeed() {
        return customSpeed;
    }

    public float getSwingProgress() {
        return swingProgress;
    }

    public enum SwingCombo {
        FAST_3x("Fast 3-Hit Combo"),
        HEAVY_SLOW("Heavy Slow Attack"),
        SPINNING_ATTACK("Spinning Attack");

        private String description;

        SwingCombo(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
