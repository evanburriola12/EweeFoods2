package net.ewee.eweefoods.datagen;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.ModBlocks;
import net.ewee.eweefoods.block.custom.OnionCropBlock;
import net.ewee.eweefoods.block.custom.PepperCropBlock;
import net.ewee.eweefoods.block.custom.RiceCropBlock;
import net.ewee.eweefoods.block.custom.SaltLampBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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
        dryingRack();

        makeCrop(((CropBlock) ModBlocks.RICE_CROP.get()), RiceCropBlock.AGE, "rice_crop_stage","rice_crop_stage");
        makeCrop(((CropBlock) ModBlocks.ONION_CROP.get()), OnionCropBlock.AGE, "onion_crop_stage","onion_crop_stage");
        makeCrop(((CropBlock) ModBlocks.PEPPER_CROP.get()), PepperCropBlock.AGE, "pepper_crop_stage","pepper_crop_stage");

        //Apple Tree Stuff
        logBlock(((RotatedPillarBlock) ModBlocks.APPLE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.APPLE_WOOD.get()), blockTexture(ModBlocks.APPLE_LOG.get()), blockTexture(ModBlocks.APPLE_LOG.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_APPLE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_APPLE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_APPLE_LOG.get()), blockTexture(ModBlocks.STRIPPED_APPLE_LOG.get()));

        blockItem(ModBlocks.APPLE_LOG);
        blockItem(ModBlocks.APPLE_WOOD);
        blockItem(ModBlocks.STRIPPED_APPLE_LOG);
        blockItem(ModBlocks.STRIPPED_APPLE_WOOD);

        blockWithItem(ModBlocks.APPLE_LEAVES);
        blockWithItem(ModBlocks.APPLE_PLANKS);

        saplingBlock(ModBlocks.APPLE_SAPLING);
    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void makeCrop(CropBlock block, IntegerProperty ageProperty, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function =
                state -> states(state, ageProperty, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, IntegerProperty ageProperty, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];

        int age = state.getValue(ageProperty);

        models[0] = new ConfiguredModel(models().crop(
                modelName + age,
                ResourceLocation.fromNamespaceAndPath(
                        EweeFoods.MOD_ID,
                        "block/" + textureName + age
                )
        ).renderType("cutout"));

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

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("eweefoods:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("eweefoods:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private void dryingRack(){
    horizontalBlock(ModBlocks.DRYING_RACK.get(), models().getExistingFile(modLoc("block/drying_rack")));

//        getVariantBuilder(ModBlocks.DRYING_RACK.get()).forAllStates(blockState ->{
//            return new ConfiguredModel[]{new ConfiguredModel(models().singleTexture("drying_rack_gen",
//                    ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/drying_rack"),
//                    ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/drying_rack")))};
//        });

        simpleBlockItem(ModBlocks.DRYING_RACK.get(), models().singleTexture("drying_rack_gen",
                ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/drying_rack"),
                ResourceLocation.fromNamespaceAndPath(EweeFoods.MOD_ID, "block/drying_rack")));
    }
}
