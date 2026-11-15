package net.ewee.eweefoods.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RevealItem extends Item {
    private static final double RANGE = 20;
    private static final Vec3 RADIUS = new Vec3(RANGE,RANGE, RANGE);

    public RevealItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        revealEntities(level, livingEntity);
        return super.finishUsingItem(stack, level, livingEntity);
    }

    private static void revealEntities(Level level, LivingEntity livingEntity){
        Vec3 topCorner = livingEntity.position().add(RADIUS);
        Vec3 bottomCorner = livingEntity.position().subtract(RADIUS);
        AABB boundingBox = new AABB(topCorner,bottomCorner);

        List<Entity> entities = level.getEntities(null,boundingBox);
        for (Entity e : entities){
            if (e instanceof LivingEntity && !(e instanceof Player))
                ((LivingEntity)e).addEffect(new MobEffectInstance(MobEffects.GLOWING, 600, 1));
        }
    }
}
