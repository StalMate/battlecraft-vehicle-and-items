package com.battlecraft.vehicleanditems.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class VehicleConfig {
    public static ForgeConfigSpec.IntValue EXAMPLE_VEHICLE_SPAWN_BLOCK_DELAY;

    public static void init(ForgeConfigSpec.Builder builder) {
        builder.push("vehicle");

        builder.comment("Example vehicle");
        EXAMPLE_VEHICLE_SPAWN_BLOCK_DELAY = builder.defineInRange(
                "example_vahicle_spawn_block_delay",
                200,
                0,
                Integer.MAX_VALUE
        );

        builder.pop();
    }
}
