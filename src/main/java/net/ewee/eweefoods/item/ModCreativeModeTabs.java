package net.ewee.eweefoods.item;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EweeFoods.MOD_ID);

    public static final Supplier<CreativeModeTab> EWEEFOODS_ITEMS_TAB =  CREATIVE_MOD_TAB.register("eweefoods_items_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.SALT.get()))
                    .title(Component.translatable("creativetab.eweefoods.items"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SALT);
                        output.accept(ModItems.CURED_MEAT);
                        output.accept(ModItems.BACON_AND_EGGS);
                    })).build());

    public static final Supplier<CreativeModeTab> EWEEFOODS_BLOCKS_TAB =  CREATIVE_MOD_TAB.register("eweefoods_blocks_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModBlocks.SALT_DEPOSIT.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID,"eweefoods_items_tab"))
                    .title(Component.translatable("creativetab.eweefoods.blocks"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.SALT_DEPOSIT);
                    })).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MOD_TAB.register(eventBus);
    }
}
