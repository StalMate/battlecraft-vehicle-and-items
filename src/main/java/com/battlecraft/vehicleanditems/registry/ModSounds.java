package com.battlecraft.vehicleanditems.registry;

import com.battlecraft.vehicleanditems.BattlecraftVehicleAndItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BattlecraftVehicleAndItems.MOD_ID);

    public static final RegistryObject<SoundEvent> SPAWN_BLOCK_OPEN =
            SOUNDS.register("spawnblock.open",
                    () -> SoundEvent.createVariableRangeEvent(
                            new ResourceLocation(
                                    BattlecraftVehicleAndItems.MOD_ID,
                                    "spawnblock.open"
                            )
                    ));
    public static final RegistryObject<SoundEvent> SPAWN_BLOCK_WAIT =
            SOUNDS.register("spawnblock.wait",
                    () -> SoundEvent.createVariableRangeEvent(
                            new ResourceLocation(
                                    BattlecraftVehicleAndItems.MOD_ID,
                                    "spawnblock.wait"
                            )
                    ));
}