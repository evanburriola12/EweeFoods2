package net.ewee.eweefoods.worldgen.tree;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrower {
    public static final TreeGrower APPLEWOOD = new TreeGrower(EweeFoods.MOD_ID + "applewood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.APPLEWOOD_KEY), Optional.empty());
}
