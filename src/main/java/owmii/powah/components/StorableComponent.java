package owmii.powah.components;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record StorableComponent(CompoundTag tag) {
    public static final Codec<StorableComponent> CODEC = CompoundTag.CODEC.xmap(StorableComponent::new, StorableComponent::tag);
    public static final StreamCodec<ByteBuf, StorableComponent> STREAM_CODEC = ByteBufCodecs.COMPOUND_TAG.map(StorableComponent::new, StorableComponent::tag);
    public static final StorableComponent EMPTY = new StorableComponent(new CompoundTag());
}
