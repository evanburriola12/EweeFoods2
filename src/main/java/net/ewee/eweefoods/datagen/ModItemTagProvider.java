package net.ewee.eweefoods.datagen;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.ewee.eweefoods.item.ModItems;
import net.ewee.eweefoods.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, EweeFoods.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.EWEE_FOOD_ITEMS)
                .add(ModItems.BACON_AND_EGGS.get());


        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.APPLE_LOG.get().asItem())
                .add(ModBlocks.APPLE_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_APPLE_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_APPLE_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.APPLE_PLANKS.get().asItem());
    }
}
