package owmii.powah.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import owmii.powah.Powah;
import owmii.powah.lib.block.AbstractEnergyStorage;
import owmii.powah.network.IPacket;

public record NextEnergyConfigPacket(int mode, BlockPos pos) implements IPacket {
    public static final Type<NextEnergyConfigPacket> TYPE = new Type<>(Powah.id("next_energy_config"));
    public static final StreamCodec<RegistryFriendlyByteBuf, NextEnergyConfigPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            NextEnergyConfigPacket::mode,
            BlockPos.STREAM_CODEC,
            NextEnergyConfigPacket::pos,
            NextEnergyConfigPacket::new);

    @Override
    public Type<NextEnergyConfigPacket> type() {
        return TYPE;
    }

    @Override
    public void handle(Player player) {
        if (player instanceof ServerPlayer) {
            BlockEntity tileEntity = player.level().getBlockEntity(pos);
            if (tileEntity instanceof AbstractEnergyStorage storage) {
                if (mode > 5)
                    storage.getSideConfig().nextTypeAll();
                else
                    storage.getSideConfig().nextType(Direction.from3DDataValue(mode));
                storage.sync();
            }
        }
    }
}
