package net.ewee.eweefoods.block.custom;

import com.mojang.serialization.MapCodec;
import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.block.entity.DryingRackBlockEntity;
import net.ewee.eweefoods.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.antlr.v4.runtime.tree.xpath.XPathLexerErrorListener;
import org.jetbrains.annotations.Nullable;

public class DryingRackBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0,0,0,16,16,16);
    public static final MapCodec<DryingRackBlock> CODEC = simpleCodec(DryingRackBlock::new);

    public DryingRackBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    /* BLOCK ENTITY */

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override 
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DryingRackBlockEntity(blockPos, blockState);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()){
            if(level.getBlockEntity(pos) instanceof DryingRackBlockEntity dryingRackEntity){
                dryingRackEntity.drops();
                level.updateNeighbourForOutputSignal(pos,this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        boolean soundEvent = false;
        // Process the block interaction on the server
        // We need to implement correct multi-block blocks to prevent interaction range issues
        // So for now, only listen to what the server allows
        if (!level.isClientSide()) {
                if(level.getBlockEntity(pos) instanceof  DryingRackBlockEntity dryingRackBlockEntity){
                    int emptySlotIndex = dryingRackBlockEntity.openItemStackIndex();
                    if(emptySlotIndex != -1 && !stack.isEmpty()){
                        dryingRackBlockEntity.inventory.insertItem(emptySlotIndex, stack.copy(), false);
                        stack.shrink(1);
                        soundEvent = true;
                    } else if(stack.isEmpty()){
                        ItemStack stackOnDryingRack = dryingRackBlockEntity.extractItemStack();
                        if(stackOnDryingRack != ItemStack.EMPTY) {
                            player.addItem(stackOnDryingRack);
                            soundEvent = true;
                        }
                    }

                    // DEBUG
//                for (int j = 0; j < dryingRackBlockEntity.inventory.getSlots(); j++) {
//                    EweeFoods.LOGGER.debug("{}: {}  ", j, dryingRackBlockEntity.inventory.getStackInSlot(j).getDisplayName().getString());
//                }

                // This lets the server play a sound to the specific client that caused the interaction event
                if (soundEvent && player instanceof ServerPlayer serverPlayer){
                    serverPlayer.connection.send(new ClientboundSoundPacket(
                            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.ITEM_PICKUP),
                            SoundSource.BLOCKS,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            1f,
                            level.getRandom().nextIntBetweenInclusive(1,2),
                            level.getRandom().nextLong()
                    ));
                    return ItemInteractionResult.SUCCESS;
                }
                return  ItemInteractionResult.FAIL;
            }
        }

        // As a client, listen to what the server reports the result as
        return ItemInteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, ModBlockEntities.DRYING_RACK_BE.get(),
                (level1, blockPos, blockState, blockEntity) -> blockEntity.tick(level1, blockPos, blockState));
    }
}
