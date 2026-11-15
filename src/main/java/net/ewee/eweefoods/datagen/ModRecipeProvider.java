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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLOUR_BAG)
                .pattern("FFF")
                .pattern("FFF")
                .pattern("FFF")
                .define('F', ModItems.FLOUR.get())
                .unlockedBy("has_flour", has(ModItems.FLOUR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SALT_DEPOSIT.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SALT.get())
                .unlockedBy("has_salt", has(ModItems.SALT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLOUR.get(), 1)
                .requires(Items.WHEAT,2)
                .unlockedBy("has_wheat", has(Items.POINTED_DRIPSTONE)).save(recipeOutput, "eweefoods:flour_from_wheat");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLOUR.get(), 9)
                .requires(ModItems.FLOUR_BAG,1)
                .unlockedBy("has_flour_bag", has(ModItems.FLOUR_BAG)).save(recipeOutput, "eweefoods:flour_from_flour_bag");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALT.get(), 1)
                .requires(Items.POINTED_DRIPSTONE)
                .unlockedBy("has_pointed_dripstone", has(Items.POINTED_DRIPSTONE)).save(recipeOutput, "eweefoods:salt_from_dripstone");

        oreSmelting(recipeOutput, List.of(ModBlocks.SALT_DEPOSIT), RecipeCategory.MISC, ModItems.SALT, 0.25f, 200, "salt");
        oreSmelting(recipeOutput, List.of(ModItems.FLOUR), RecipeCategory.MISC, Items.BREAD, 0.25f, 200, "flour");
    }
}
