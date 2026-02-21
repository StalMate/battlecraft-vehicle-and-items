package com.battlecraft.vehicleanditems.entity.blocks.components;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

public class ProgressBarComponent {
    private long startTime = -1;
    private int maxProgress;

    public ProgressBarComponent(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void onLoad(Level level) {
        if (!isInit()) {
            startTime = level.getGameTime();
        }
    }

    public boolean isInit() {
        return startTime != -1;
    }

    public boolean isFinished(Level level) {
        if (!isInit()) return false;
        return level.getGameTime() - startTime >= maxProgress;
    }

    public float getProgress(Level level, float partialTick) {
        if (level == null || !isInit()) return 0f;

        float elapsed = (level.getGameTime() - startTime) + partialTick;
        return Math.min(elapsed / maxProgress, 1f);
    }

    public void save(CompoundTag tag) {
        tag.putLong("StartTime", startTime);
        tag.putInt("MaxProgress", maxProgress);
    }

    public void load(CompoundTag tag) {
        if (tag.contains("StartTime")) {
            startTime = tag.getLong("StartTime");
        }
        if (tag.contains("MaxProgress")) {
            maxProgress = tag.getInt("MaxProgress");
        }
    }

    public void syncFromTag(CompoundTag tag) {
        startTime = tag.getLong("StartTime");
        maxProgress = tag.getInt("MaxProgress");
    }

    public void writeSyncTag(CompoundTag tag) {
        tag.putLong("StartTime", startTime);
        tag.putInt("MaxProgress", maxProgress);
    }
}
