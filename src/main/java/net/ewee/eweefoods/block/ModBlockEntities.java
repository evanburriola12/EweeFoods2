package net.ewee.eweefoods.block;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.custom.entity.AppleLeavesBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(
                    Registries.BLOCK_ENTITY_TYPE,
                    EweeFoods.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AppleLeavesBlockEntity>> APPLE_LEAVES =
            BLOCK_ENTITIES.register("apple_leaves", () -> BlockEntityType.Builder.of(AppleLeavesBlockEntity::new, ModBlocks.APPLE_LEAVES.get()).build(null));
}