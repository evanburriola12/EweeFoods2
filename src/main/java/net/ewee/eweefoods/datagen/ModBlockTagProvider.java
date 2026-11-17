package net.ewee.eweefoods.datagen;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EweeFoods.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PINK_SALT_DEPOSIT.get())
                .add(ModBlocks.PINK_SALT_BLOCK.get())
                .add(ModBlocks.SALT_BlOCK.get())
                .add(ModBlocks.SEA_SALT_BlOCK.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.SEA_SALT_DEPOSIT.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.PINK_SALT_DEPOSIT.get())
                .add(ModBlocks.PINK_SALT_BLOCK.get())
                .add(ModBlocks.SEA_SALT_BlOCK.get())
                .add(ModBlocks.SALT_BlOCK.get());
    }


}
