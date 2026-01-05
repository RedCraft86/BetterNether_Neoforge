package org.betterx.betternether.registry;

import org.betterx.bclib.blocks.BaseBarrelBlock;
import org.betterx.bclib.blocks.BaseChestBlock;
import org.betterx.betternether.BetterNether;
import org.betterx.betternether.blockentities.BNBrewingStandBlockEntity;
import org.betterx.betternether.blockentities.BlockEntityChestOfDrawers;
import org.betterx.betternether.blockentities.BlockEntityForge;
import org.betterx.betternether.blockentities.BlockEntityFurnace;
import org.betterx.betternether.blocks.BlockNetherFurnace;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.ArrayList;
import java.util.List;

public class BlockEntitiesRegistry {
    public static BlockEntityType<BlockEntityForge> CINCINNASITE_FORGE;
    public static BlockEntityType<BlockEntityFurnace> NETHERRACK_FURNACE;
    public static BlockEntityType<BlockEntityChestOfDrawers> CHEST_OF_DRAWERS;
    public static BlockEntityType<BNBrewingStandBlockEntity> NETHER_BREWING_STAND;

    public static void register() {
        // no-op, kept for binary compatibility
    }

    public static void register(net.neoforged.neoforge.registries.RegisterEvent event) {
        if (event.getRegistryKey().equals(net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE)) {
            event.register(net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE, helper -> {
                CINCINNASITE_FORGE = BlockEntityType.Builder.of(
                        BlockEntityForge::new,
                        NetherBlocks.CINCINNASITE_FORGE
                ).build(null);
                NETHERRACK_FURNACE = BlockEntityType.Builder.of(
                        BlockEntityFurnace::new,
                        getFurnaces()
                ).build(null);
                CHEST_OF_DRAWERS = BlockEntityType.Builder.of(
                        BlockEntityChestOfDrawers::new,
                        NetherBlocks.CHEST_OF_DRAWERS
                ).build(null);
                NETHER_BREWING_STAND = BlockEntityType.Builder.of(
                        BNBrewingStandBlockEntity::new,
                        NetherBlocks.NETHER_BREWING_STAND
                ).build(null);

                helper.register(BetterNether.C.mk("forge"), CINCINNASITE_FORGE);
                helper.register(BetterNether.C.mk("furnace"), NETHERRACK_FURNACE);
                helper.register(BetterNether.C.mk("chest_of_drawers"), CHEST_OF_DRAWERS);
                helper.register(BetterNether.C.mk("nether_brewing_stand"), NETHER_BREWING_STAND);
            });
        }
    }

    private static Block[] getChests() {
        List<Block> result = new ArrayList<Block>();
        NetherBlocks.getModBlocks().forEach((block) -> {
            if (block instanceof BaseChestBlock)
                result.add(block);
        });
        return result.toArray(new Block[]{});
    }

    private static Block[] getBarrels() {
        List<Block> result = new ArrayList<Block>();
        NetherBlocks.getModBlocks().forEach((block) -> {
            if (block instanceof BaseBarrelBlock)
                result.add(block);
        });
        return result.toArray(new Block[]{});
    }

    private static Block[] getFurnaces() {
        List<Block> result = new ArrayList<Block>();
        NetherBlocks.getModBlocks().forEach((block) -> {
            if (block instanceof BlockNetherFurnace)
                result.add(block);
        });
        return result.toArray(new Block[]{});
    }
}
