package owmii.powah.api.wrench;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

public enum WrenchMode implements StringRepresentable {
    CONFIG("config"),
    LINK("link"),
    ROTATE("rotate");

    private static final WrenchMode[] VALUES = values();
    public static final Codec<WrenchMode> CODEC = StringRepresentable.fromEnum(WrenchMode::values);
    public static final StreamCodec<ByteBuf, WrenchMode> STREAM_CODEC = ByteBufCodecs.idMapper(ByIdMap.continuous(WrenchMode::ordinal, VALUES, ByIdMap.OutOfBoundsStrategy.WRAP), WrenchMode::ordinal);

    public WrenchMode nextWrenchMode() {
        int nextIdx = ordinal() + 1;
        int maxIdx = VALUES.length - 1;
        return VALUES[nextIdx > maxIdx ? 0 : nextIdx];
    }

    public WrenchMode prevWrenchMode() {
        int lastIdx = ordinal() - 1;
        int maxIdx = VALUES.length - 1;
        return VALUES[lastIdx < maxIdx ? maxIdx : lastIdx];
    }

    public boolean config() {
        return this == CONFIG;
    }

    public boolean link() {
        return this == LINK;
    }

    public boolean rotate() {
        return this == ROTATE;
    }

    private final String name;

    WrenchMode(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
