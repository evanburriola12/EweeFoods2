package net.ewee.eweefoods;

import net.ewee.eweefoods.block.ModBlocks;
import net.ewee.eweefoods.block.entity.ModBlockEntities;
import net.ewee.eweefoods.block.entity.renderer.DryingRackBlockEntityRenderer;
import net.ewee.eweefoods.item.ModCreativeModeTabs;
import net.ewee.eweefoods.item.ModItems;
import net.ewee.eweefoods.recipe.ModRecipes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(EweeFoods.MOD_ID)
public class EweeFoods {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "eweefoods";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public EweeFoods(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModRecipes.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
//        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
//            event.accept(ModItems.SALT);
//            event.accept(ModItems.CURED_MEAT);
//            event.accept(ModItems.BACON_AND_EGGS);
//        }
//        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
//            event.accept(ModBlocks.SALT_DEPOSIT);
//        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents{

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(ModBlockEntities.DRYING_RACK_BE.get(), DryingRackBlockEntityRenderer::new);
        }

    }


}
