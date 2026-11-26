package net.ewee.eweefoods.block.custom;

import net.ewee.eweefoods.block.entity.AppleLeavesBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AppleLeavesBlock extends LeavesBlock implements EntityBlock {

    public AppleLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(
            BlockState state,
            ServerLevel level,
            BlockPos pos,
            RandomSource random) {
        super.randomTick(state, level, pos, random);

        if (!level.getBlockState(pos).is(this)) {
            return;
        }

        if (level.getBlockEntity(pos) instanceof AppleLeavesBlockEntity blockEntity) {
            blockEntity.randomTick(level);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hit
    ) {
        if (!(level.getBlockEntity(pos) instanceof AppleLeavesBlockEntity blockEntity)) {
            return InteractionResult.PASS;
        }

        if (blockEntity.getAppleCount() <= 0) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide) {

                    popResource(
                    level,
                    pos,
                    new ItemStack(Items.APPLE)
            );

            blockEntity.setAppleCount(blockEntity.getAppleCount() - 1);

            level.playSound(
                    null,
                    pos,
                    SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,
                    SoundSource.BLOCKS,
                    1.0F,
                    0.8F + level.random.nextFloat() * 0.4F
            );
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockEntity newBlockEntity(
            BlockPos pos,
            BlockState state) {
        return new AppleLeavesBlockEntity(pos, state);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public boolean isFlammable(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            Direction direction) {
        return 30;
    }
}