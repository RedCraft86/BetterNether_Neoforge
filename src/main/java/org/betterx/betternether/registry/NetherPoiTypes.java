package org.betterx.betternether.registry;

import org.betterx.betternether.BetterNether;
import org.betterx.wover.poi.api.PoiManager;
import org.betterx.wover.poi.api.WoverPoiType;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;

public class NetherPoiTypes {
    public static WoverPoiType PIG_STATUE;

    public static void register() {
        if (PIG_STATUE != null || NetherBlocks.PIG_STATUE_RESPAWNER == null) return;
        var states = new java.util.HashSet<>(WoverPoiType.getBlockStates(NetherBlocks.PIG_STATUE_RESPAWNER));
        states.removeIf(PoiTypes::hasPoi);
        if (states.isEmpty()) return;
        try {
            PIG_STATUE = PoiManager.register(BetterNether.C.id("pig_statue"), states, 1, 1);
        } catch (IllegalStateException ex) {
            BetterNether.C.log.warn("Skip pig_statue POI registration: {}", ex.getMessage());
        }
    }

    public static void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.POINT_OF_INTEREST_TYPE)) {
            register();
        }
    }
}
