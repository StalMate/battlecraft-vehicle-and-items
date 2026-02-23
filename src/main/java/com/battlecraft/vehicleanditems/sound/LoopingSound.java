package com.battlecraft.vehicleanditems.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class LoopingSound extends AbstractTickableSoundInstance {
    private static final Map<BlockPos, LoopingSound> ACTIVE = new HashMap<>();

    private final BlockPos pos;
    private final BooleanSupplier stopCondition;

    private LoopingSound(SoundEvent sound, SoundSource source, BlockPos pos, float volume,
                                   float pitch, BooleanSupplier stopCondition) {
        super(sound, source, SoundInstance.createUnseededRandom());

        this.pos = pos;
        this.stopCondition = stopCondition;

        this.x = pos.getX() + 0.5;
        this.y = pos.getY() + 0.5;
        this.z = pos.getZ() + 0.5;

        this.volume = volume;
        this.pitch = pitch;

        this.looping = true;
        this.delay = 0;
    }

    public static void play(BlockPos pos, SoundEvent sound, SoundSource source, float volume,
                            float pitch, BooleanSupplier stopCondition) {
        if (ACTIVE.containsKey(pos)) return;

        LoopingSound instance = new LoopingSound(sound, source, pos, volume, pitch, stopCondition);

        ACTIVE.put(pos, instance);
        Minecraft.getInstance().getSoundManager().play(instance);
    }

    public static void stop(BlockPos pos) {
        LoopingSound sound = ACTIVE.remove(pos);
        if (sound != null) {
            sound.stop();
        }
    }

    @Override
    public void tick() {
        if (stopCondition.getAsBoolean()) {
            this.stop();
            ACTIVE.remove(pos);
        }
    }
}
