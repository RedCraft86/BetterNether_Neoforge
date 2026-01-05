package org.betterx.betternether.integrations.blockus;

import net.neoforged.fml.ModList;

public class BlockusUtils {
    public static boolean hasBlockus() {
        return ModList.get().isLoaded("blockus");
    }
}
