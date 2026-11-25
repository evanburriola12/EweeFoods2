package net.ewee.eweefoods.client;

import net.ewee.eweefoods.block.ModBlockEntities;
import net.ewee.eweefoods.client.renderer.AppleLeavesRenderer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ClientModEvents {

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ClientModEvents::registerRenderers);
    }

    private static void registerRenderers(
            EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                ModBlockEntities.APPLE_LEAVES.get(),
                AppleLeavesRenderer::new);
    }
}