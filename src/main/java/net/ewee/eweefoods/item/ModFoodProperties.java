package net.ewee.eweefoods.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    //600T = 30s
    //1200T = 1 min
    //6000T = 5 min
    public static final FoodProperties CURED_MEAT = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 220, 0), 0.5F)
            .fast()
            .build();

    public static final FoodProperties CHICKEN_FRIED_STEAK = (new FoodProperties.Builder())
            .nutrition(10)
            .saturationModifier(.80F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 0), 1.0F)
            .build();

    public static final FoodProperties FRESH_LOAF = (new FoodProperties.Builder())
            .nutrition(5)
            .saturationModifier(.60F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0), 1.0F)
            .build();

    public static final FoodProperties GLAZED_RABBIT = (new FoodProperties.Builder())
            .nutrition(6)
            .saturationModifier(.6F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 800, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 800, 0), 0.25F)
            .build();

    public static final FoodProperties ROAST = (new FoodProperties.Builder())
            .nutrition(12)
            .saturationModifier(1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 800,0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 800,0), 1.0F)
            .build();

    public static final FoodProperties NUTRITIOUS_SOUP = (new FoodProperties.Builder())
            .nutrition(10)
            .saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 6000,5), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 6000,3), 1.0F)
            .build();

    public static final FoodProperties ROAST_BEEF = (new FoodProperties.Builder())
            .nutrition(8)
            .saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 6000, 1), 1.0F)
            .build();

    public static final FoodProperties SPICY_CHICKEN_SANDWICH = (new FoodProperties.Builder())
            .nutrition(8)
            .saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0), 1.0F)
            .build();

    public static final FoodProperties BACON_AND_EGGS = (new FoodProperties.Builder())
            .nutrition(8)
            .saturationModifier(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 2400, 0), 1.0F)
            .build();

    public static final FoodProperties LAMB_BAT = (new FoodProperties.Builder())
            .nutrition(7)
            .saturationModifier(0.7F)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0F)
            .fast()
            .build();

    public static final FoodProperties SUSHI = (new FoodProperties.Builder())
            .nutrition(6)
            .saturationModifier(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 6000, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 0), 1.0F)
            .fast()
            .build();
}
