package org.betterx.betternether.registry;

import org.betterx.bclib.particles.BCLParticleType;
import org.betterx.betternether.BetterNether;
import org.betterx.betternether.config.Configs;
import org.betterx.betternether.particles.BNParticleProvider;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public class NetherParticles {
    public static SimpleParticleType BLUE_DRIPPING_OBSIDIAN_TEAR;
    public static SimpleParticleType BLUE_FALLING_OBSIDIAN_TEAR;
    public static SimpleParticleType BLUE_LANDING_OBSIDIAN_TEAR;

    public static SimpleParticleType BLUE_DRIPPING_OBSIDIAN_WEEP;
    public static SimpleParticleType BLUE_FALLING_OBSIDIAN_WEEP;
    public static SimpleParticleType BLUE_LANDING_OBSIDIAN_WEEP;

    public static SimpleParticleType DRIPPING_OBSIDIAN_WEEP;
    public static SimpleParticleType FALLING_OBSIDIAN_WEEP;
    public static SimpleParticleType LANDING_OBSIDIAN_WEEP;

    private static boolean registered = false;

    public static void onRegister(RegisterEvent event) {
        if (!event.getRegistryKey().equals(Registries.PARTICLE_TYPE)) {
            return;
        }
        registerTypes();
    }

    private static void registerTypes() {
        if (registered) {
            return;
        }
        registered = true;

        BLUE_DRIPPING_OBSIDIAN_TEAR = BCLParticleType.register(
                BetterNether.C.id("blue_dripping_obsidian_tear")
        );

        BLUE_FALLING_OBSIDIAN_TEAR = BCLParticleType.register(
                BetterNether.C.id("blue_falling_obsidian_tear")
        );

        BLUE_LANDING_OBSIDIAN_TEAR = BCLParticleType.register(
                BetterNether.C.id("blue_landing_obsidian_tear")
        );

        BLUE_DRIPPING_OBSIDIAN_WEEP = BCLParticleType.register(
                BetterNether.C.id("blue_dripping_obsidian_weep")
        );

        BLUE_FALLING_OBSIDIAN_WEEP = BCLParticleType.register(
                BetterNether.C.id("blue_falling_obsidian_weep")
        );

        BLUE_LANDING_OBSIDIAN_WEEP = BCLParticleType.register(
                BetterNether.C.id("blue_landing_obsidian_weep")
        );

        DRIPPING_OBSIDIAN_WEEP = BCLParticleType.register(
                BetterNether.C.id("dripping_obsidian_weep")
        );

        FALLING_OBSIDIAN_WEEP = BCLParticleType.register(
                BetterNether.C.id("falling_obsidian_weep")
        );

        LANDING_OBSIDIAN_WEEP = BCLParticleType.register(
                BetterNether.C.id("landing_obsidian_weep")
        );
    }

    public static void registerProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(BLUE_DRIPPING_OBSIDIAN_TEAR, BNParticleProvider.ObsidianTearHangProvider::new);
        event.registerSpriteSet(BLUE_FALLING_OBSIDIAN_TEAR, BNParticleProvider.ObsidianTearFallProvider::new);
        event.registerSpriteSet(BLUE_LANDING_OBSIDIAN_TEAR, BNParticleProvider.ObsidianTearLandProvider::new);

        event.registerSpriteSet(BLUE_DRIPPING_OBSIDIAN_WEEP, BNParticleProvider.ObsidianWeepHangProvider::new);
        event.registerSpriteSet(BLUE_FALLING_OBSIDIAN_WEEP, BNParticleProvider.ObsidianWeepFallProvider::new);
        event.registerSpriteSet(BLUE_LANDING_OBSIDIAN_WEEP, BNParticleProvider.ObsidianWeepLandProvider::new);

        event.registerSpriteSet(DRIPPING_OBSIDIAN_WEEP, BNParticleProvider.ObsidianVanillaWeepHangProvider::new);
        event.registerSpriteSet(FALLING_OBSIDIAN_WEEP, BNParticleProvider.ObsidianVanillaWeepFallProvider::new);
        event.registerSpriteSet(LANDING_OBSIDIAN_WEEP, BNParticleProvider.ObsidianVanillaWeepLandProvider::new);
    }

    public static SimpleParticleType getBlueDrippingObsidianWeep() {
        return Configs.CLIENT.weepingParticles.get()
                ? BLUE_DRIPPING_OBSIDIAN_WEEP
                : BLUE_DRIPPING_OBSIDIAN_TEAR;
    }

    public static SimpleParticleType getDrippingObsidianWeep() {
        return Configs.CLIENT.weepingParticles.get()
                ? DRIPPING_OBSIDIAN_WEEP
                : BLUE_DRIPPING_OBSIDIAN_TEAR;
    }
}
