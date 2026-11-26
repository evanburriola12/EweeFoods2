package net.ewee.eweefoods.block;

import com.mojang.serialization.MapCodec;
import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.custom.*;
import net.ewee.eweefoods.item.ModItems;
import net.ewee.eweefoods.worldgen.tree.ModTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(EweeFoods.MOD_ID);


    //Salts
    public static final DeferredBlock<Block> PINK_SALT_DEPOSIT = registerBlock("pink_salt_deposit",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));

    public static final DeferredBlock<Block> PINK_SALT_BLOCK = registerBlock("pink_salt_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));

    public static final DeferredBlock<Block> SEA_SALT_DEPOSIT = registerBlock("sea_salt_deposit",
            () -> new FallingBlock(BlockBehaviour.Properties.of()
                    .strength(0.5F).sound(SoundType.SAND)) {
                @Override
                protected MapCodec<? extends FallingBlock> codec() {
                    return simpleCodec(AnvilBlock::new);
                }
            });

    public static final DeferredBlock<Block> SEA_SALT_BlOCK = registerBlock("sea_salt_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));

    public static final DeferredBlock<Block> SALT_BlOCK = registerBlock("salt_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));

    //Salt Lamp
    public static final DeferredBlock<Block> SALT_LAMP = registerBlock("salt_lamp",
            () -> new SaltLampBlock(BlockBehaviour.Properties.of().strength(0.2f).lightLevel(state -> state.getValue(SaltLampBlock.CLICKED) ? 15 : 0)));


    //Crops
    public static final DeferredBlock<Block> RICE_CROP = BLOCKS.register("rice_block",
            () -> new RiceCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));

    public static final DeferredBlock<Block> ONION_CROP = BLOCKS.register("onion_block",
            () -> new OnionCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));

    public static final DeferredBlock<Block> PEPPER_CROP = BLOCKS.register("pepper_block",
            () -> new PepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));


    //Apple Tree
    public static final DeferredBlock<Block> APPLE_LEAVES = registerBlock("apple_leaves",
            () -> new AppleLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).randomTicks()));

    public static final DeferredBlock<Block> APPLE_SAPLING = registerBlock("apple_sapling",
            () -> new SaplingBlock(ModTreeGrower.APPLEWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> APPLE_LOG = registerBlock("apple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final DeferredBlock<Block> APPLE_WOOD = registerBlock("apple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final DeferredBlock<Block> STRIPPED_APPLE_LOG = registerBlock("stripped_apple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final DeferredBlock<Block> STRIPPED_APPLE_WOOD = registerBlock("stripped_apple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final DeferredBlock<Block> APPLE_PLANKS = registerBlock("apple_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final DeferredBlock<Block> DRYING_RACK = registerBlock("drying_rack",
            () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .strength(2f)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
