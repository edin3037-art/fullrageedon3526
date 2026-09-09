package com.fullrageedon.modules.combat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import com.fullrageedon.modules.Module;

/**
 * Aura Module - Automatic kill aura with rotation
 */
public class Aura extends Module {
    private float range = 6.0f;
    private float rotationSpeed = 1.0f;
    private boolean rotate = true;
    private int attackDelay = 0;
    private PlayerEntity target;

    public Aura() {
        super("Aura", "Kill aura with smooth rotation", "Combat");
    }

    @Override
    public void onEnable() {
        target = null;
    }

    @Override
    public void onDisable() {
        target = null;
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;

        // Find nearest target
        target = findTarget();
        if (target == null) return;

        // Rotate to target
        if (rotate) {
            rotateToEntity(target);
        }

        // Attack
        if (attackDelay <= 0) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(net.minecraft.util.Hand.MAIN_HAND);
            attackDelay = 4;
        }
        attackDelay--;
    }

    private PlayerEntity findTarget() {
        MinecraftClient mc = MinecraftClient.getInstance();
        PlayerEntity closest = null;
        double distance = range;

        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof PlayerEntity player && player != mc.player) {
                double d = mc.player.distanceTo(player);
                if (d < distance && !player.isInvulnerable()) {
                    closest = player;
                    distance = d;
                }
            }
        }

        return closest;
    }

    private void rotateToEntity(Entity entity) {
        MinecraftClient mc = MinecraftClient.getInstance();
        double dx = entity.getX() - mc.player.getX();
        double dy = entity.getEyeY() - mc.player.getEyeY();
        double dz = entity.getZ() - mc.player.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);

        float yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90.0f;
        float pitch = (float) -Math.toDegrees(Math.atan2(dy, dist));

        mc.player.setYaw(yaw);
        mc.player.setPitch(pitch);
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getRange() {
        return range;
    }
}
