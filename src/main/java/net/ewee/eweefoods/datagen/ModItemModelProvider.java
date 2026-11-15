package net.ewee.eweefoods.datagen;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EweeFoods.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SALT.get());
        basicItem(ModItems.FLOUR.get());
        basicItem(ModItems.FLOUR_BAG.get());
        basicItem(ModItems.CURED_MEAT.get());
        basicItem(ModItems.CHICKEN_FRIED_STEAK.get());
        basicItem(ModItems.FRESH_LOAF.get());
        basicItem(ModItems.GLAZED_RABBIT.get());
        basicItem(ModItems.ROAST.get());
        basicItem(ModItems.NUTRITIOUS_SOUP.get());
        basicItem(ModItems.ROAST_BEEF.get());
        basicItem(ModItems.SPICY_CHICKEN_SANDWICH.get());
        basicItem(ModItems.BACON_AND_EGGS.get());
        basicItem(ModItems.LAMB_BAT.get());
        basicItem(ModItems.SUSHI.get());
    }
}
