package net.ewee.eweefoods.block;

import com.mojang.serialization.MapCodec;
import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.custom.SaltLampBlock;
import net.ewee.eweefoods.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(EweeFoods.MOD_ID);

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

    public static final DeferredBlock<Block> SALT_LAMP = registerBlock("salt_lamp",
            () -> new SaltLampBlock(BlockBehaviour.Properties.of().strength(0.2f).lightLevel(state -> state.getValue(SaltLampBlock.CLICKED) ? 15 : 0)));

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
