package net.ewee.eweefoods.block.entity;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EweeFoods.MOD_ID);


    public static final Supplier<BlockEntityType<DryingRackBlockEntity>> DRYING_RACK_BE =
            BLOCK_ENTITIES.register("drying_rack_be", () -> BlockEntityType.Builder.of(
                    DryingRackBlockEntity::new, ModBlocks.DRYING_RACK.get()).build(null));

    public static final Supplier<BlockEntityType<AppleLeavesBlockEntity>> APPLE_LEAVES =
            BLOCK_ENTITIES.register("apple_leaves", () -> BlockEntityType.Builder.of(
                AppleLeavesBlockEntity::new, ModBlocks.APPLE_LEAVES.get()).build(null));

    public static final Supplier<BlockEntityType<CrockpotBlockEntity>> CROCKPOT =
            BLOCK_ENTITIES.register("crockpot", () -> BlockEntityType.Builder.of(
                   CrockpotBlockEntity::new, ModBlocks.CROCKPOT.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
