package owmii.powah.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import owmii.powah.network.packet.NextEnergyConfigPacket;
import owmii.powah.network.packet.NextRedstoneModePacket;
import owmii.powah.network.packet.SetChannelPacket;
import owmii.powah.network.packet.SwitchGenModePacket;

public final class Network {
    public static <T extends IPacket> void register(CustomPacketPayload.Type<T> id, StreamCodec<RegistryFriendlyByteBuf, T> codec) {
        PayloadTypeRegistry.playC2S().register(id, codec);
        ServerPlayNetworking.registerGlobalReceiver(id, (payload, context) -> {
            payload.handle(context.player());
        });
    }

    public static void register() {
        register(NextEnergyConfigPacket.TYPE, NextEnergyConfigPacket.CODEC);
        register(NextRedstoneModePacket.TYPE, NextRedstoneModePacket.CODEC);
        register(SetChannelPacket.TYPE, SetChannelPacket.CODEC);
        register(SwitchGenModePacket.TYPE, SwitchGenModePacket.CODEC);
    }

    public static void toServer(IPacket msg) {
        ClientPlayNetworking.send(msg);
    }
}
