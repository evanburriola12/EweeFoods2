package net.ewee.eweefoods.item;

import net.ewee.eweefoods.EweeFoods;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EweeFoods.MOD_ID);

    public static final DeferredItem<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CURED_MEAT = ITEMS.register("cured_meat",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BACON_AND_EGGS = ITEMS.register("bacon_and_eggs",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BACON_AND_EGGS)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
