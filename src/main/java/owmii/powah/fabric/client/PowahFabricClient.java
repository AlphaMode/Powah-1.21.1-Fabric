package owmii.powah.fabric.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.client.player.ClientPickBlockGatherCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import owmii.powah.block.Blcks;
import owmii.powah.block.cable.CableNet;
import owmii.powah.client.PowahClient;
import owmii.powah.client.handler.ReactorOverlayHandler;
import owmii.powah.client.render.tile.ReactorItemRenderer;
import owmii.powah.item.ReactorItem;
import owmii.powah.lib.block.AbstractBlock;

public class PowahFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PowahClient.init();
        PowahClient.clientSetup();

        ClientChunkEvents.CHUNK_UNLOAD.register(CableNet::removeChunk);

        var reactorRenderer = new ReactorItemRenderer();
        Blcks.REACTOR.getAll().forEach(block -> {
            var item = (ReactorItem) BuiltInRegistries.ITEM.get(BuiltInRegistries.BLOCK.getKey(block));
            BuiltinItemRendererRegistry.INSTANCE.register(item, reactorRenderer::renderByItem);
        });

        WorldRenderEvents.END.register(context -> {
            ReactorOverlayHandler.onRenderLast(new PoseStack(), context.camera());
        });

        ClientPickBlockGatherCallback.EVENT.register((player, result) -> {
            if (result instanceof BlockHitResult bhr) {
                var level = player.level();
                if (level.getBlockState(bhr.getBlockPos()).getBlock() instanceof AbstractBlock<?, ?> abstractBlock) {
                    return abstractBlock.getCloneItemStack(level, bhr.getBlockPos());
                }
            }
            return ItemStack.EMPTY;
        });
    }
}
