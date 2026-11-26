package net.ewee.eweefoods.worldgen;

import net.ewee.eweefoods.EweeFoods;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_PINK_SALT_DEPOSIT = registerKey("add_pink_salt_deposit");
    public static final ResourceKey<BiomeModifier> ADD_SEA_SALT_DEPOSIT = registerKey("add_sea_salt_deposit");
    public static final ResourceKey<BiomeModifier> ADD_APPLE_TREE = registerKey("add_apple_tree");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

//        Dont think we need this anymore?
//        context.register(ADD_SALT_DEPOSIT, new BiomeModifiers.AddFeaturesBiomeModifier(
//                HolderSet.direct(biomes.getOrThrow(Biomes.STONY_PEAKS), biomes.getOrThrow(Biomes.JAGGED_PEAKS), biomes.getOrThrow(Biomes.FROZEN_PEAKS)),
//                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SALT_DEPOSIT_PLACED_KEY)),
//                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_PINK_SALT_DEPOSIT, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PINK_SALT_DEPOSIT_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SEA_SALT_DEPOSIT, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OCEAN),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SEA_SALT_DEPOSIT_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_APPLE_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.APPLE_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, name));
    }
}
