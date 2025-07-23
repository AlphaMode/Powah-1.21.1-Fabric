package owmii.powah.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import owmii.powah.Powah;
import owmii.powah.lib.block.AbstractTileEntity;
import owmii.powah.lib.logistics.IRedstoneInteract;
import owmii.powah.network.IPacket;

public record NextRedstoneModePacket(BlockPos pos) implements IPacket {
    public static final Type<NextRedstoneModePacket> TYPE = new Type<>(Powah.id("next_redstone_mode"));
    public static final StreamCodec<RegistryFriendlyByteBuf, NextRedstoneModePacket> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            NextRedstoneModePacket::pos,
            NextRedstoneModePacket::new);

    @Override
    public Type<NextRedstoneModePacket> type() {
        return TYPE;
    }

    @Override
    public void handle(Player player) {
        if (player instanceof ServerPlayer) {
            BlockEntity tileEntity = player.level().getBlockEntity(pos);
            if (tileEntity instanceof AbstractTileEntity ate) {
                if (tileEntity instanceof IRedstoneInteract ri) {
                    ri.nextRedstoneMode();
                    ate.sync();
                }
            }
        }
    }
}
