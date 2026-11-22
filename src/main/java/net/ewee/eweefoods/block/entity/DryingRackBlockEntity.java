package net.ewee.eweefoods.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class DryingRackBlockEntity extends BlockEntity {
    public final ItemStackHandler inventory = new ItemStackHandler(3){
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 4);
            }
        }
    };

    private float rotation;

    public DryingRackBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DRYING_RACK_BE.get(), pos, blockState);
    }

    public float getRenderingRotation(){
        return rotation = 0f;
    }

    // Find the and return index of first open item slot or return -1
    public int openItemStackIndex(){
        for (int i = 0; i < inventory.getSlots(); i++){
            if (inventory.getStackInSlot(i).isEmpty()){
                return i;
            }
        }
        return -1;
    }

    // Pop the latest item off the inventory or return null if none
    // Later will intelligently pop off only "finished" items first
    public ItemStack extractItemStack(){
        for (int i = inventory.getSlots() - 1; i >= 0; i--){
            if(!inventory.getStackInSlot(i).isEmpty()){
                return inventory.extractItem(i,1,false);
            }
        }
        return ItemStack.EMPTY;
    }

    public void clearContents(){
        inventory.setStackInSlot(0, ItemStack.EMPTY);
        inventory.setStackInSlot(1, ItemStack.EMPTY);
        inventory.setStackInSlot(2, ItemStack.EMPTY);
    }

    public void drops(){
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++){
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
    }
}
