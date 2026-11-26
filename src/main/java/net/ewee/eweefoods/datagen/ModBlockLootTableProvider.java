package net.ewee.eweefoods.datagen;

import net.ewee.eweefoods.block.ModBlocks;
import net.ewee.eweefoods.block.custom.OnionCropBlock;
import net.ewee.eweefoods.block.custom.PepperCropBlock;
import net.ewee.eweefoods.block.custom.RiceCropBlock;
import net.ewee.eweefoods.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        add(ModBlocks.PINK_SALT_DEPOSIT.get(),
               block ->  createMultipleOreDrops(ModBlocks.PINK_SALT_DEPOSIT.get(), ModItems.PINK_SALT.get(), 2, 6));
        add(ModBlocks.SEA_SALT_DEPOSIT.get(),
                block -> createMultipleOreDrops(ModBlocks.SEA_SALT_DEPOSIT.get(), ModItems.SEA_SALT.get(), 2, 6));

        //Salt Blocks
        dropSelf(ModBlocks.PINK_SALT_BLOCK.get());
        dropSelf(ModBlocks.SEA_SALT_BlOCK.get());
        dropSelf(ModBlocks.SALT_BlOCK.get());
        dropSelf(ModBlocks.SALT_LAMP.get());

        //Apple Tree
        dropSelf(ModBlocks.APPLE_LOG.get());
        dropSelf(ModBlocks.APPLE_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_APPLE_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_APPLE_LOG.get());
        dropSelf(ModBlocks.APPLE_PLANKS.get());
        dropSelf(ModBlocks.APPLE_SAPLING.get());
        //Leaves drop saplings
        this.add(ModBlocks.APPLE_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.APPLE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        //Rice crop drops
        dropSelf(ModBlocks.DRYING_RACK.get());

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceCropBlock.AGE, 3));

        this.add(ModBlocks.RICE_CROP.get(), this.createCropDrops(ModBlocks.RICE_CROP.get(),
                ModItems.RICE.get(),
                ModItems.RICE_SEEDS.get(),
                lootItemConditionBuilder));


        //Pepper crop drops. Drops different pepper color based on age
        LootItemCondition.Builder greenPepperCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEPPER_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(PepperCropBlock.AGE, 4));

        LootItemCondition.Builder yellowPepperCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEPPER_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(PepperCropBlock.AGE, 5));

        LootItemCondition.Builder redPepperCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.PEPPER_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(PepperCropBlock.AGE, 6));

        this.add(ModBlocks.PEPPER_CROP.get(),
                LootTable.lootTable()

                        .withPool(LootPool.lootPool()
                                .when(greenPepperCondition)
                                .add(LootItem.lootTableItem(ModItems.GREEN_PEPPER.get())))

                        .withPool(LootPool.lootPool()
                                .when(yellowPepperCondition)
                                .add(LootItem.lootTableItem(ModItems.YELLOW_PEPPER.get())))

                        .withPool(LootPool.lootPool()
                                .when(redPepperCondition)
                                .add(LootItem.lootTableItem(ModItems.RED_PEPPER.get())))

                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(ModItems.PEPPER_SEEDS.get()))));


        //Onion crop drops. Works like carrots
        LootItemCondition.Builder onionCropCondition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.ONION_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(OnionCropBlock.AGE, OnionCropBlock.MAX_AGE));


        this.add(ModBlocks.ONION_CROP.get(),
                this.createCropDrops(
                        ModBlocks.ONION_CROP.get(),
                        ModItems.ONION.get(),
                        ModItems.ONION.get(),
                        onionCropCondition));
    }

    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
