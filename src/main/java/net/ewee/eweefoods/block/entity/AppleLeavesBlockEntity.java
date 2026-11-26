package net.ewee.eweefoods.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class AppleLeavesBlockEntity extends BlockEntity {

    private int appleCount = 0;

    public AppleLeavesBlockEntity(
            BlockPos pos,
            BlockState state) {
        super(ModBlockEntities.APPLE_LEAVES.get(), pos, state);
    }

    public int getAppleCount() {return appleCount;}

    public void setAppleCount(int count) {
        appleCount = Mth.clamp(count, 0, 4);
        setChanged();

        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(
                    worldPosition,
                    getBlockState(),
                    getBlockState(),
                    Block.UPDATE_CLIENTS);
        }
    }

    public void randomTick(ServerLevel level) {

        if (appleCount >= 4) {
            return;
        }

        if (level.random.nextInt(5) != 0) {
            return;
        }

        int availableSides = 0;

        for (Direction direction : Direction.Plane.HORIZONTAL) {

            BlockPos neighborPos = worldPosition.relative(direction);

            if (level.isEmptyBlock(neighborPos)) {
                availableSides++;
            }
        }

        if (availableSides > appleCount) {
            setAppleCount(appleCount + 1);
        }
    }



    @Override
    protected void saveAdditional(
            CompoundTag tag,
            HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("AppleCount", appleCount);
    }

    @Override
    protected void loadAdditional(
            CompoundTag tag,
            HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        appleCount = Mth.clamp(tag.getInt("AppleCount"), 0, 4);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}