package owmii.powah.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.UUID;

public record BoundPlayer(UUID playerUuid, String playerName) {
    public static final Codec<BoundPlayer> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            UUIDUtil.CODEC.fieldOf("bound_player_id").forGetter(BoundPlayer::playerUuid),
            Codec.STRING.fieldOf("bound_player_name").forGetter(BoundPlayer::playerName)
    ).apply(instance, BoundPlayer::new));
    public static StreamCodec<ByteBuf, BoundPlayer> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC,
            BoundPlayer::playerUuid,
            ByteBufCodecs.STRING_UTF8,
            BoundPlayer::playerName,
            BoundPlayer::new
    );
}
