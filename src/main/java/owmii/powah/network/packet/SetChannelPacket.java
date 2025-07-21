package owmii.powah.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import owmii.powah.Powah;
import owmii.powah.block.ender.AbstractEnderTile;
import owmii.powah.network.IPacket;

public record SetChannelPacket(BlockPos pos, int channel) implements IPacket {
    public static final Type<SetChannelPacket> TYPE = new Type<>(Powah.id("set_channel"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SetChannelPacket> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            SetChannelPacket::pos,
            ByteBufCodecs.INT,
            SetChannelPacket::channel,
            SetChannelPacket::new
    );

    @Override
    public Type<SetChannelPacket> type() {
        return TYPE;
    }

    @Override
    public void handle(Player player) {
        Level world = player.getCommandSenderWorld();
        BlockEntity te = world.getBlockEntity(pos);
        if (te instanceof AbstractEnderTile cell) {
            cell.getChannel().set(channel);
            cell.sync();
        }
    }
}
