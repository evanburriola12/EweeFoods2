package net.ewee.eweefoods.worldgen;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PINK_SALT_DEPOSIT_KEY = registerKey("salt_deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SEA_SALT_DEPOSIT_KEY = registerKey("sea_salt_deposit");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldPinkSaltDeposits = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.PINK_SALT_DEPOSIT.get().defaultBlockState())
        );

        RuleTest sandReplaceables = new TagMatchTest(BlockTags.SAND);

        List<OreConfiguration.TargetBlockState> overworldSeaSaltDeposits = List.of(
                OreConfiguration.target(sandReplaceables, ModBlocks.SEA_SALT_DEPOSIT.get().defaultBlockState())
        );

        register(context, OVERWORLD_PINK_SALT_DEPOSIT_KEY, Feature.ORE, new OreConfiguration(overworldPinkSaltDeposits, 6));
        register(context, OVERWORLD_SEA_SALT_DEPOSIT_KEY, Feature.ORE, new OreConfiguration(overworldSeaSaltDeposits, 12));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
