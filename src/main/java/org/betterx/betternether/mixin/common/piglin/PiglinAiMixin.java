package org.betterx.betternether.mixin.common.piglin;

import org.betterx.betternether.config.Configs;
import org.betterx.betternether.items.materials.BNArmorTiers;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PiglinAi.class, remap = false)
public class PiglinAiMixin {
    @Inject(method = "isWearingGold", at = @At("RETURN"), cancellable = true)
    private static void bn_isWearingGold(
            LivingEntity entity,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (cir.getReturnValue() || !Configs.GAME_RULES.piglinIgnoreNetherArmor.get()) {
            return;
        }

        for (ItemStack stack : entity.getArmorAndBodyArmorSlots()) {
            if (stack.getItem() instanceof ArmorItem armorItem) {
                Holder<ArmorMaterial> material = armorItem.getMaterial();
                if (material.is(BNArmorTiers.CINCINNASITE.armorMaterial)
                        || material.is(BNArmorTiers.NETHER_RUBY.armorMaterial)
                        || material.is(BNArmorTiers.FLAMING_RUBY.armorMaterial)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
        }
    }
}
