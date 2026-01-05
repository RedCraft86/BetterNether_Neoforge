package org.betterx.betternether.integrations.emi;

import org.betterx.betternether.BetterNether;
import org.betterx.betternether.registry.NetherBlocks;

import net.minecraft.world.item.crafting.RecipeManager;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;

public class EMIPlugin implements EmiPlugin {
    private static EmiIngredient blackstoneFurnaceWorkstation() {
        return EmiStack.of(NetherBlocks.BLACKSTONE_FURNACE);
    }

    private static EmiIngredient netherrackFurnaceWorkstation() {
        return EmiStack.of(NetherBlocks.NETHERRACK_FURNACE);
    }

    private static EmiIngredient basaltFurnaceWorkstation() {
        return EmiStack.of(NetherBlocks.BASALT_FURNACE);
    }

    private static EmiIngredient cincinnasiteForgeWorkstation() {
        return EmiStack.of(NetherBlocks.CINCINNASITE_FORGE);
    }

    private static EmiRecipeCategory FORGE;

    public static EmiRecipeCategory getForgeCategory() {
        if (FORGE == null) {
            FORGE = new EmiRecipeCategory(
                    BetterNether.C.id("forge"),
                    cincinnasiteForgeWorkstation(),
                    org.betterx.bclib.integration.emi.EMIPlugin.getSprite(16, 16)
            );
        }
        return FORGE;
    }

    @Override
    public void register(EmiRegistry emiRegistry) {
        final RecipeManager manager = emiRegistry.getRecipeManager();

        EmiRecipeCategory forgeCategory = getForgeCategory();
        emiRegistry.addCategory(forgeCategory);
        emiRegistry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, blackstoneFurnaceWorkstation());
        emiRegistry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, netherrackFurnaceWorkstation());
        emiRegistry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, basaltFurnaceWorkstation());
        emiRegistry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, cincinnasiteForgeWorkstation());

        EMIForgeRecipe.addAllRecipes(emiRegistry, manager);
    }

}
