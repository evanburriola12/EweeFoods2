package net.ewee.eweefoods.item;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EweeFoods.MOD_ID);

    public static final Supplier<CreativeModeTab> EWEEFOODS_TAB =  CREATIVE_MODE_TAB.register("eweefoods_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.BACON_AND_EGGS.get()))
                    .title(Component.translatable("creativetab.eweefoods"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SALT);
                        output.accept(ModItems.FLOUR);
                        output.accept(ModItems.FLOUR_BAG);
                        output.accept(ModItems.CURED_MEAT);
                        output.accept(ModItems.CURED_MEAT);
                        output.accept(ModItems.CHICKEN_FRIED_STEAK);
                        output.accept(ModItems.FRESH_LOAF);
                        output.accept(ModItems.GLAZED_RABBIT);
                        output.accept(ModItems.ROAST);
                        output.accept(ModItems.NUTRITIOUS_SOUP);
                        output.accept(ModItems.ROAST_BEEF);
                        output.accept(ModItems.SPICY_CHICKEN_SANDWICH);
                        output.accept(ModItems.BACON_AND_EGGS);
                        output.accept(ModItems.LAMB_BAT);
                        output.accept(ModItems.SUSHI);
                        output.accept(ModBlocks.SALT_DEPOSIT);
                    })).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
