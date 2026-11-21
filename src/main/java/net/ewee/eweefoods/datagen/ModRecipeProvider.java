package net.ewee.eweefoods.datagen;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.ewee.eweefoods.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {


        //Campfire Cooking
        campfireRecipe(recipeOutput, ModItems.SALTED_BEEF.get(),
                ModItems.COOKED_SALTED_BEEF.get(), "cooked_salted_beef");

        campfireRecipe(recipeOutput, ModItems.SALTED_PORK.get(),
                ModItems.COOKED_SALTED_PORK.get(), "cooked_salted_pork");

        campfireRecipe(recipeOutput, ModItems.SALTED_RABBIT.get(),
                ModItems.COOKED_SALTED_RABBIT.get(), "cooked_salted_rabbit");

        campfireRecipe(recipeOutput, ModItems.SALTED_MUTTON.get(),
                ModItems.COOKED_SALTED_MUTTON.get(), "cooked_salted_mutton");

        campfireRecipe(recipeOutput, ModItems.SALTED_CHICKEN.get(),
                ModItems.COOKED_SALTED_CHICKEN.get(), "cooked_salted_chicken");




        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLOUR_BAG)
                .pattern("FFF")
                .pattern("FFF")
                .pattern("FFF")
                .define('F', ModItems.FLOUR.get())
                .unlockedBy("has_flour", has(ModItems.FLOUR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PINK_SALT_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.PINK_SALT.get())
                .unlockedBy("has_sea_salt", has(ModItems.PINK_SALT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SEA_SALT_BlOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SEA_SALT.get())
                .unlockedBy("has_sea_salt", has(ModItems.SEA_SALT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SALT_BlOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SALT.get())
                .unlockedBy("has_sea_salt", has(ModItems.SALT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SALT_LAMP.get())
                .pattern("S")
                .pattern("L")
                .define('S', ModBlocks.PINK_SALT_BLOCK)
                .define('L', Items.LANTERN)
                .unlockedBy("has_salt_block", has(ModBlocks.PINK_SALT_BLOCK)).save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PINK_SALT.get(), 9)
                .requires(ModBlocks.PINK_SALT_BLOCK,1)
                .unlockedBy("has_pink_salt_block", has(ModBlocks.PINK_SALT_BLOCK)).save(recipeOutput, "eweefoods:pink_salt_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SEA_SALT.get(), 9)
                .requires(ModBlocks.SEA_SALT_BlOCK,1)
                .unlockedBy("has_sea_salt_block", has(ModBlocks.SEA_SALT_BlOCK)).save(recipeOutput, "eweefoods:sea_salt_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALT.get(), 9)
                .requires(ModBlocks.SALT_BlOCK,1)
                .unlockedBy("has_salt_block", has(ModBlocks.SALT_BlOCK)).save(recipeOutput, "eweefoods:salt_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLOUR.get(), 1)
                .requires(Items.WHEAT,2)
                .unlockedBy("has_wheat", has(Items.WHEAT)).save(recipeOutput, "eweefoods:flour_from_wheat");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLOUR.get(), 9)
                .requires(ModItems.FLOUR_BAG,1)
                .unlockedBy("has_flour_bag", has(ModItems.FLOUR_BAG)).save(recipeOutput, "eweefoods:flour_from_flour_bag");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALT.get(), 1)
                .requires(Items.POINTED_DRIPSTONE)
                .unlockedBy("has_pointed_dripstone", has(Items.POINTED_DRIPSTONE)).save(recipeOutput, "eweefoods:salt_from_dripstone");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SALTED_BEEF.get())
                .requires(Items.BEEF)
                .requires(ModItems.SALT.get())
                .unlockedBy("has_beef", has(Items.BEEF))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SALTED_PORK.get())
                .requires(Items.PORKCHOP)
                .requires(ModItems.SALT.get())
                .unlockedBy("has_porkchop", has(Items.PORKCHOP))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SALTED_CHICKEN.get())
                .requires(Items.CHICKEN)
                .requires(ModItems.SALT.get())
                .unlockedBy("has_chicken", has(Items.CHICKEN))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SALTED_MUTTON.get())
                .requires(Items.MUTTON)
                .requires(ModItems.SALT.get())
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SALTED_RABBIT.get())
                .requires(Items.RABBIT)
                .requires(ModItems.SALT.get())
                .unlockedBy("has_rabbit", has(Items.RABBIT))
                .save(recipeOutput);

        oreSmelting(recipeOutput, List.of(ModBlocks.PINK_SALT_DEPOSIT), RecipeCategory.MISC, ModItems.PINK_SALT, 0.25f, 200, "salt");
        oreSmelting(recipeOutput, List.of(ModItems.FLOUR), RecipeCategory.MISC, Items.BREAD, 0.25f, 200, "flour");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, EweeFoods.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected void campfireRecipe(RecipeOutput output, Item input,
                                  Item result, String recipeName) {

        SimpleCookingRecipeBuilder.campfireCooking(
                        Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35f,
                        600
                )
                .unlockedBy("has_" + recipeName, has(input))
                .save(output, ResourceLocation.fromNamespaceAndPath(
                        EweeFoods.MOD_ID, recipeName + "_campfire"
                ));
    }
}


