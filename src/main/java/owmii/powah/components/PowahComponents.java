package owmii.powah.components;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import owmii.powah.Powah;
import owmii.powah.api.wrench.WrenchMode;

public class PowahComponents {
    public static final DataComponentType<StorableComponent> STORED = DataComponentType.<StorableComponent>builder()
            .persistent(StorableComponent.CODEC).networkSynchronized(StorableComponent.STREAM_CODEC).build();
    public static final DataComponentType<BoundPlayer> BOUND_PLAYER = DataComponentType.<BoundPlayer>builder()
            .persistent(BoundPlayer.CODEC).networkSynchronized(BoundPlayer.STREAM_CODEC).build();
    public static final DataComponentType<WrenchMode> WRENCH_MODE = DataComponentType.<WrenchMode>builder()
            .persistent(WrenchMode.CODEC).build();
    public static final DataComponentType<WrenchLink> WRENCH_LINK = DataComponentType.<WrenchLink>builder()
            .persistent(WrenchLink.CODEC).networkSynchronized(WrenchLink.STREAM_CODEC).build();
    public static final DataComponentType<Unit> CHARGING = DataComponentType.<Unit>builder()
            .persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)).build();

    public static void register() {
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Powah.id("tile_data"), STORED);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Powah.id("bound_player"), BOUND_PLAYER);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Powah.id("wrench_mode"), WRENCH_MODE);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Powah.id("wrench_link"), WRENCH_LINK);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Powah.id("charging"), CHARGING);
    }
}
