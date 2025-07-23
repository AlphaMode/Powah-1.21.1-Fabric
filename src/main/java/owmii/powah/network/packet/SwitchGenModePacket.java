package owmii.powah.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import owmii.powah.Powah;
import owmii.powah.block.reactor.ReactorTile;
import owmii.powah.network.IPacket;

public record SwitchGenModePacket(BlockPos pos) implements IPacket {
    public static final Type<SwitchGenModePacket> TYPE = new Type<>(Powah.id("switch_gen_mode"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SwitchGenModePacket> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            SwitchGenModePacket::pos,
            SwitchGenModePacket::new);

    @Override
    public Type<SwitchGenModePacket> type() {
        return TYPE;
    }

    @Override
    public void handle(Player player) {
        Level world = player.getCommandSenderWorld();
        BlockEntity te = world.getBlockEntity(pos);
        if (te instanceof ReactorTile reactor) {
            reactor.setGenModeOn(!reactor.isGenModeOn());
        }
    }
}
