package net.ewee.eweefoods.item.custom;

import net.ewee.eweefoods.EweeFoods;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class TooltipItem extends Item {
    private final String itemName;
    public TooltipItem(Properties properties, String itemName) {
        super(properties);
        this.itemName = itemName;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            String key = "tooltip.eweefoods.".concat(itemName).concat(".shift_down");
            tooltipComponents.add(Component.translatable(key));
        } else {
            tooltipComponents.add(Component.translatable("Shift for §e§oSTATS§r§r"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
