package net.ewee.eweefoods.item;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.ewee.eweefoods.item.custom.RevealItem;
import net.ewee.eweefoods.item.custom.TooltipItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EweeFoods.MOD_ID);

    public static final DeferredItem<Item> PINK_SALT = ITEMS.register("pink_salt",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SEA_SALT = ITEMS.register("sea_salt",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FLOUR = ITEMS.register("flour",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FLOUR_BAG = ITEMS.register("flour_bag",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CURED_MEAT = ITEMS.register("cured_meat",
            () -> new RevealItem(new Item.Properties().food(ModFoodProperties.CURED_MEAT)));

    public static final DeferredItem<Item> CHICKEN_FRIED_STEAK = ITEMS.register("chicken_fried_steak",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHICKEN_FRIED_STEAK)));

    public static final DeferredItem<Item> FRESH_LOAF = ITEMS.register("fresh_loaf",
            () -> new Item(new Item.Properties().food(ModFoodProperties.FRESH_LOAF)));

    public static final DeferredItem<Item> GLAZED_RABBIT = ITEMS.register("glazed_rabbit",
            () -> new TooltipItem(new Item.Properties().food(ModFoodProperties.GLAZED_RABBIT), "glazed_rabbit"));

    public static final DeferredItem<Item> ROAST = ITEMS.register("roast",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROAST)));

    public static final DeferredItem<Item> NUTRITIOUS_SOUP = ITEMS.register("nutritious_soup",
            () -> new Item(new Item.Properties().food(ModFoodProperties.NUTRITIOUS_SOUP)));

    public static final DeferredItem<Item> ROAST_BEEF = ITEMS.register("roast_beef",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROAST_BEEF)));

    public static final DeferredItem<Item> SPICY_CHICKEN_SANDWICH = ITEMS.register("spicy_chicken_sandwich",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SPICY_CHICKEN_SANDWICH)));

    public static final DeferredItem<Item> BACON_AND_EGGS = ITEMS.register("bacon_and_eggs",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BACON_AND_EGGS)));

    public static final DeferredItem<Item> LAMB_BAT = ITEMS.register("lamb_bat",
            () -> new Item(new Item.Properties().food(ModFoodProperties.LAMB_BAT)));

    public static final DeferredItem<Item> SUSHI = ITEMS.register("sushi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SUSHI)));

    //Crops
    public static final DeferredItem<Item> RICE = ITEMS.register("rice",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RICE_SEEDS = ITEMS.register("rice_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RICE_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> PEPPER_SEEDS = ITEMS.register("pepper_seeds",
            () -> new ItemNameBlockItem(ModBlocks.PEPPER_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> ONION = ITEMS.register("onion",
            () -> new ItemNameBlockItem(ModBlocks.ONION_CROP.get(), new Item.Properties().food(ModFoodProperties.ONION)));

    public static final DeferredItem<Item> GREEN_PEPPER = ITEMS.register("green_pepper",
            () -> new Item(new Item.Properties().food(ModFoodProperties.PEPPER)));

    public static final DeferredItem<Item> RED_PEPPER = ITEMS.register("red_pepper",
            () -> new Item(new Item.Properties().food(ModFoodProperties.PEPPER)));

    public static final DeferredItem<Item> YELLOW_PEPPER = ITEMS.register("yellow_pepper",
            () -> new Item(new Item.Properties().food(ModFoodProperties.PEPPER)));


    //Beef Variants
    public static final DeferredItem<Item> SALTED_BEEF = ITEMS.register("salted_beef",
            () -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> SEA_SALTED_BEEF = ITEMS.register("sea_salted_beef",
            //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> PINK_SALTED_BEEF = ITEMS.register("pink_salted_beef",
            //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DRIED_BEEF = ITEMS.register("dried_beef",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_BEEF)));

    public static final DeferredItem<Item> DRIED_SALTED_BEEF = ITEMS.register("dried_salted_beef",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_SALTED_BEEF)));

    //public static final DeferredItem<Item> DRIED_SEA_SALTED_BEEF = ITEMS.register("dried_sea_salted_beef",
            //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> DRIED_PINK_SALTED_BEEF = ITEMS.register("dried_pink_salted_beef",
            //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COOKED_SALTED_BEEF = ITEMS.register("cooked_salted_beef",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_SALTED_BEEF)));

    //public static final DeferredItem<Item> COOKED_SEA_SALTED_BEEF = ITEMS.register("cooked_sea_salted_beef",
            //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> COOKED_PINK_SALTED_BEEF = ITEMS.register("cooked_pink_salted_beef",
            //() -> new Item(new Item.Properties()));

    //Pork Variants
    public static final DeferredItem<Item> SALTED_PORK = ITEMS.register("salted_pork",
            () -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> SEA_SALTED_PORK = ITEMS.register("sea_salted_pork",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> PINK_SALTED_PORK = ITEMS.register("pink_salted_pork",
    //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DRIED_PORK = ITEMS.register("dried_pork",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_PORK)));

    public static final DeferredItem<Item> DRIED_SALTED_PORK = ITEMS.register("dried_salted_pork",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_SALTED_PORK)));

    //public static final DeferredItem<Item> DRIED_SEA_SALTED_PORK = ITEMS.register("dried_sea_salted_pork",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> DRIED_PINK_SALTED_PORK = ITEMS.register("dried_pink_salted_pork",
    //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COOKED_SALTED_PORK = ITEMS.register("cooked_salted_pork",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_SALTED_PORK)));

    //public static final DeferredItem<Item> COOKED_SEA_SALTED_PORK = ITEMS.register("cooked_sea_salted_pork",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> COOKED_PINK_SALTED_PORK = ITEMS.register("cooked_pink_salted_pork",
    //() -> new Item(new Item.Properties()));


    //Chicken Variants
    public static final DeferredItem<Item> SALTED_CHICKEN = ITEMS.register("salted_chicken",
            () -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> SEA_SALTED_CHICKEN = ITEMS.register("sea_salted_chicken",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> PINK_SALTED_CHICKEN = ITEMS.register("pink_salted_chicken",
    //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DRIED_CHICKEN = ITEMS.register("dried_chicken",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_CHICKEN)));

    public static final DeferredItem<Item> DRIED_SALTED_CHICKEN = ITEMS.register("dried_salted_chicken",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_SALTED_CHICKEN)));

    //public static final DeferredItem<Item> DRIED_SEA_SALTED_CHICKEN = ITEMS.register("dried_sea_salted_chicken",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> DRIED_PINK_SALTED_CHICKEN = ITEMS.register("dried_pink_salted_chicken",
    //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COOKED_SALTED_CHICKEN = ITEMS.register("cooked_salted_chicken",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_SALTED_CHICKEN)));

    //public static final DeferredItem<Item> COOKED_SEA_SALTED_CHICKEN = ITEMS.register("cooked_sea_salted_chicken",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> COOKED_PINK_SALTED_CHICKEN = ITEMS.register("cooked_pink_salted_chicken",
    //() -> new Item(new Item.Properties()));

    //Mutton Variants
    public static final DeferredItem<Item> SALTED_MUTTON = ITEMS.register("salted_mutton",
            () -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> SEA_SALTED_MUTTON = ITEMS.register("sea_salted_mutton",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> PINK_SALTED_MUTTON = ITEMS.register("pink_salted_mutton",
    //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DRIED_MUTTON = ITEMS.register("dried_mutton",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_MUTTON)));

    public static final DeferredItem<Item> DRIED_SALTED_MUTTON = ITEMS.register("dried_salted_mutton",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_SALTED_MUTTON)));

    //public static final DeferredItem<Item> DRIED_SEA_SALTED_MUTTON = ITEMS.register("dried_sea_salted_mutton",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> DRIED_PINK_SALTED_MUTTON = ITEMS.register("dried_pink_salted_mutton",
    //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COOKED_SALTED_MUTTON = ITEMS.register("cooked_salted_mutton",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_SALTED_MUTTON)));

    //public static final DeferredItem<Item> COOKED_SEA_SALTED_MUTTON = ITEMS.register("cooked_sea_salted_mutton",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> COOKED_PINK_SALTED_MUTTON = ITEMS.register("cooked_pink_salted_mutton",
    //() -> new Item(new Item.Properties()));

    //Rabbit Variants
    public static final DeferredItem<Item> SALTED_RABBIT = ITEMS.register("salted_rabbit",
            () -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> SEA_SALTED_RABBIT = ITEMS.register("sea_salted_rabbit",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> PINK_SALTED_RABBIT = ITEMS.register("pink_salted_rabbit",
    //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DRIED_RABBIT = ITEMS.register("dried_rabbit",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_RABBIT)));

    public static final DeferredItem<Item> DRIED_SALTED_RABBIT = ITEMS.register("dried_salted_rabbit",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DRIED_SALTED_RABBIT)));

    //public static final DeferredItem<Item> DRIED_SEA_SALTED_RABBIT = ITEMS.register("dried_sea_salted_rabbit",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> DRIED_PINK_SALTED_RABBIT = ITEMS.register("dried_pink_salted_rabbit",
    //() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COOKED_SALTED_RABBIT = ITEMS.register("cooked_salted_rabbit",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_SALTED_RABBIT)));

    //public static final DeferredItem<Item> COOKED_SEA_SALTED_RABBIT = ITEMS.register("cooked_sea_salted_rabbit",
    //() -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> COOKED_PINK_SALTED_RABBIT = ITEMS.register("cooked_pink_salted_rabbit",
    //() -> new Item(new Item.Properties()));





    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
