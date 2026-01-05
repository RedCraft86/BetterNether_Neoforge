package org.betterx.betternether.mixin.common;

import org.betterx.betternether.registry.NetherEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DefaultAttributes.class, remap = false)
public class DefaultAttributeRegistryMixin {
    @Inject(method = "getSupplier", at = @At("HEAD"), cancellable = true)
    private static void getAttribute(
            EntityType<? extends LivingEntity> type,
            CallbackInfoReturnable<AttributeSupplier> info
    ) {
        var builder = NetherEntities.ATTR_BUILDERS.get(type);
        if (builder != null) {
            info.setReturnValue(builder.build());
            info.cancel();
        }
    }

    @Inject(method = "hasSupplier", at = @At("HEAD"), cancellable = true)
    private static void hasDefinition(EntityType<?> type, CallbackInfoReturnable<Boolean> info) {
        if (NetherEntities.ATTR_BUILDERS.containsKey(type)) {
            info.setReturnValue(true);
            info.cancel();
        }
    }
}
