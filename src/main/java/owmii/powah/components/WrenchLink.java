package owmii.powah.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;

public record WrenchLink(Optional<BlockPos> rodPos, Optional<BlockPos> orbPos) {
    public static final Codec<WrenchLink> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockPos.CODEC.optionalFieldOf("RodPos").forGetter(WrenchLink::rodPos),
            BlockPos.CODEC.optionalFieldOf("OrbPos").forGetter(WrenchLink::orbPos)
    ).apply(instance, WrenchLink::new));
    public static final StreamCodec<ByteBuf, WrenchLink> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.optional(BlockPos.STREAM_CODEC),
            WrenchLink::rodPos,
            ByteBufCodecs.optional(BlockPos.STREAM_CODEC),
            WrenchLink::orbPos,
            WrenchLink::new
    );

    public static final WrenchLink NONE = new WrenchLink(Optional.empty(), Optional.empty());
}
