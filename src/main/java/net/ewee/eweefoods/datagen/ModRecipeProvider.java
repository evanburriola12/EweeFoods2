package net.ewee.eweefoods.datagen;

import net.ewee.eweefoods.block.ModBlocks;
import net.ewee.eweefoods.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        List<ItemLike> SALT_SMELTABLES = List.of(ModBlocks.SALT_DEPOSIT);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SALT_DEPOSIT.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SALT.get())
                .unlockedBy("has_salt", has(ModItems.SALT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALT.get(), 1)
                .requires(Items.POINTED_DRIPSTONE)
                .unlockedBy("has_pointed_dripstone", has(Items.POINTED_DRIPSTONE)).save(recipeOutput, "eweefoods:salt_from_dripstone");

        oreSmelting(recipeOutput, SALT_SMELTABLES, RecipeCategory.MISC, ModItems.SALT, 0.25f, 200, "salt");
    }
}
