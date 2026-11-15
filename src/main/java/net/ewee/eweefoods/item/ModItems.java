package net.ewee.eweefoods.item;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.item.custom.RevealItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EweeFoods.MOD_ID);

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
            () -> new Item(new Item.Properties().food(ModFoodProperties.GLAZED_RABBIT)));

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

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
