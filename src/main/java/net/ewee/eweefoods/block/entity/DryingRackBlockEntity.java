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
    private final int SLOTS = 3;

    public final ItemStackHandler inventory = new ItemStackHandler(SLOTS){
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
    private int[] progress = new int[SLOTS];
    private int[] maxProgress = new int[]{72,72,72};

    
    public DryingRackBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DRYING_RACK_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> DryingRackBlockEntity.this.progress[0];
                    case 1 -> DryingRackBlockEntity.this.maxProgress[0];
                    case 2 -> DryingRackBlockEntity.this.progress[1];
                    case 3 -> DryingRackBlockEntity.this.maxProgress[1];
                    case 4 -> DryingRackBlockEntity.this.progress[2];
                    case 5 -> DryingRackBlockEntity.this.maxProgress[2];
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: DryingRackBlockEntity.this.progress[0] = value;
                    case 1: DryingRackBlockEntity.this.maxProgress[0] = value;
                    case 2: DryingRackBlockEntity.this.progress[1] = value;
                    case 3: DryingRackBlockEntity.this.maxProgress[1] = value;
                    case 4: DryingRackBlockEntity.this.progress[2] = value;
                    case 5: DryingRackBlockEntity.this.maxProgress[2] = value;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    private Optional<RecipeHolder<DryingRackRecipe>> getCurrentRecipe(int slotIndex){
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.DRYING_RACK_TYPE.get(), new DryingRackRecipeInput(inventory.getStackInSlot(slotIndex)), level);
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
        tag.putIntArray("drying_rack.progress", progress);
        tag.putIntArray("drying_rack.max_progress", maxProgress);
        tag.put("inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getIntArray("drying_rack.progress");
        maxProgress = tag.getIntArray("drying_rack.max_progress");
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        for (int i = 0; i < inventory.getSlots(); i++) {
            if (hasRecipe(i)) {
                increaseCraftingProgress(i);
                setChanged(level, blockPos, blockState);

                if (hasCraftingFinished(i)) {
                    craftItem(i);
                    resetProgress(i);
                }
            } else {
                resetProgress(i);
            }
        }
    }

    private void resetProgress() {
        progress = new int[]{0,0,0};
        maxProgress = new int[]{72,72,72};
    }

    private void resetProgress(int slotIndex) {
        progress[slotIndex] = 0;
        maxProgress[slotIndex] = 72;
    }

    private boolean hasCraftingFinished(int slotIndex) {
        return this.progress[slotIndex] >= this.maxProgress[slotIndex];
    }

    private void increaseCraftingProgress(int slotIndex) {
        progress[slotIndex]++;
    }

    private void craftItem(int slotIndex) {
        Optional<RecipeHolder<DryingRackRecipe>> recipe = getCurrentRecipe(slotIndex);
        ItemStack output = recipe.get().value().output();

        inventory.extractItem(slotIndex, 1, false);
        inventory.setStackInSlot(slotIndex, new ItemStack(output.getItem(),
                inventory.getStackInSlot(slotIndex).getCount() + output.getCount()));
    }

    private boolean hasRecipe(int slotIndex){
        Optional<RecipeHolder<DryingRackRecipe>> recipe = getCurrentRecipe(slotIndex);
        return recipe.isPresent();
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
