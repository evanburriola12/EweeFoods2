package net.ewee.eweefoods.datagen;

import net.ewee.eweefoods.Config;
import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.ewee.eweefoods.block.custom.RiceCropBlock;
import net.ewee.eweefoods.block.custom.SaltLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EweeFoods.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PINK_SALT_DEPOSIT);
        blockWithItem(ModBlocks.PINK_SALT_BLOCK);
        blockWithItem(ModBlocks.SEA_SALT_DEPOSIT);
        blockWithItem(ModBlocks.SEA_SALT_BlOCK);
        blockWithItem(ModBlocks.SALT_BlOCK);

        customLamp();

        makeCrop(((CropBlock) ModBlocks.RICE_CROP.get()), "rice_crop_stage", "rice_crop_stage");

    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((RiceCropBlock) block).getAgeProperty()),
            ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/" + textureName + state.getValue(((RiceCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void customLamp(){
        getVariantBuilder(ModBlocks.SALT_LAMP.get()).forAllStates(blockState ->{
            if (blockState.getValue(SaltLampBlock.CLICKED)){
                return new ConfiguredModel[]{new ConfiguredModel(models().singleTexture("salt_lamp",
                        ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/salt_lamp_on"),
                        ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/salt_lamp_on")))};
            } else{
                return new ConfiguredModel[]{new ConfiguredModel(models().singleTexture("salt_lamp",
                        ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/salt_lamp_off"),
                        ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/salt_lamp_off")))};
            }
        });


        simpleBlockItem(ModBlocks.SALT_LAMP.get(), models().singleTexture("salt_lamp",
                ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/salt_lamp_on"),
                ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/salt_lamp_on")));
    }
}
