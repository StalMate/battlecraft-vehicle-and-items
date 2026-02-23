package com.battlecraft.vehicleanditems.registry;

import net.minecraftforge.common.ForgeConfigSpec;
import com.battlecraft.vehicleanditems.configs.VehicleConfig;

public class ModConfigs {

    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        VehicleConfig.init(builder);
        SERVER_SPEC = builder.build();
    }
}