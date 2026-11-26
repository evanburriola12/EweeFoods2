package net.ewee.eweefoods.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ewee.eweefoods.block.entity.AppleLeavesBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import java.util.Random;

public class AppleLeavesBlockEntityRenderer implements BlockEntityRenderer<AppleLeavesBlockEntity> {

    public AppleLeavesBlockEntityRenderer(
            BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(

            AppleLeavesBlockEntity blockEntity,
            float partialTick,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int packedOverlay) {

        if (blockEntity.getAppleCount() <= 0 || blockEntity.getLevel() == null) {
            return;
        }

        ItemRenderer itemRenderer =
                Minecraft.getInstance().getItemRenderer();

        ItemStack apple = new ItemStack(Items.APPLE);


        Direction[] directions = {
                Direction.NORTH,
                Direction.SOUTH,
                Direction.WEST,
                Direction.EAST};

        int renderedApples = 0;

        for (Direction direction : directions) {

            if (renderedApples >= blockEntity.getAppleCount()) {
                break;
            }

            BlockPos neighborPos = blockEntity.getBlockPos().relative(direction);

            if (!blockEntity.getLevel().isEmptyBlock(neighborPos)) {
                continue;
            }

            Random random = new Random(
                    blockEntity.getBlockPos().asLong()
                            ^ ((long) direction.ordinal() * 31L)
            );

            float horizontalOffset = 0.25F + random.nextFloat() * 0.5F;
            float verticalOffset = 0.35F + random.nextFloat() * 0.4F;

            poseStack.pushPose();

            switch (direction) {
                case NORTH -> {
                    poseStack.translate(horizontalOffset, verticalOffset, -0.005);
                    poseStack.mulPose(Axis.YP.rotationDegrees(180));
                }
                case SOUTH -> {
                    poseStack.translate(horizontalOffset, verticalOffset, 1.005);
                }
                case WEST -> {
                    poseStack.translate(-0.005, verticalOffset, horizontalOffset);
                    poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                }
                case EAST -> {
                    poseStack.translate(1.005, verticalOffset, horizontalOffset);
                    poseStack.mulPose(Axis.YP.rotationDegrees(90));
                }
            }

            poseStack.scale(0.35F, 0.35F, 0.35F);

            itemRenderer.renderStatic(
                    apple,
                    ItemDisplayContext.FIXED,
                    packedLight,
                    packedOverlay,
                    poseStack,
                    bufferSource,
                    blockEntity.getLevel(),
                    renderedApples);

            poseStack.popPose();

            renderedApples++;
        }
    }
}