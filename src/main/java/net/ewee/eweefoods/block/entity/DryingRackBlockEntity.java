package net.ewee.eweefoods.block.entity;

import net.ewee.eweefoods.EweeFoods;
import net.ewee.eweefoods.recipe.DryingRackRecipe;
import net.ewee.eweefoods.recipe.DryingRackRecipeInput;
import net.ewee.eweefoods.recipe.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

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
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    
    public DryingRackBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DRYING_RACK_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> DryingRackBlockEntity.this.progress;
                    case 1 -> DryingRackBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: DryingRackBlockEntity.this.progress = value;
                    case 1: DryingRackBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.getStackInSlot(0).isEmpty() ||
                inventory.getStackInSlot(0).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.getStackInSlot(0).isEmpty() ? 64 : inventory.getStackInSlot(0).getMaxStackSize();
        int currentCount = inventory.getStackInSlot(0).getCount();

        return maxCount >= currentCount + count;
    }

    private Optional<RecipeHolder<DryingRackRecipe>> getCurrentRecipe(){
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.DRYING_RACK_TYPE.get(), new DryingRackRecipeInput(inventory.getStackInSlot(0)), level);
    }

    public float getRenderingRotation(){
        return rotation = 90f;
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
        tag.putInt("drying_rack.progress", progress);
        tag.putInt("drying_rack.max_progress", maxProgress);
        tag.put("inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("drying_rack.progress");
        maxProgress = tag.getInt("drying_rack.max_progress");
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, blockPos, blockState);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }

        EweeFoods.LOGGER.debug("TICK: {}", progress );
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 72;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private void craftItem() {
        Optional<RecipeHolder<DryingRackRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        inventory.extractItem(0, 1, false);
        inventory.setStackInSlot(0, new ItemStack(output.getItem(),
                inventory.getStackInSlot(0).getCount() + output.getCount()));
    }

    private boolean hasRecipe(){
        Optional<RecipeHolder<DryingRackRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

}
