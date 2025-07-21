package owmii.powah.fabric.data;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ITags {
    public static class Blocks {
        // Remove non-dry ice if Forge handles them in the future
        public static final TagKey<Block> ICES = tag("ices");
        public static final TagKey<Block> ICES_ICE = tag("ices/ice");
        public static final TagKey<Block> ICES_PACKED = tag("ices/packed");
        public static final TagKey<Block> ICES_BLUE = tag("ices/blue");
        public static final TagKey<Block> ICES_DRY = tag("ices/dry");
        public static final TagKey<Block> URANINITE_ORE = tag("ores/uraninite");
        public static final TagKey<Block> URANINITE_BLOCK = tag("storage_blocks/uraninite");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Items {
        // Remove non-dry ice if Forge handles them in the future
        public static final TagKey<Item> ICES = tag("ices");
        public static final TagKey<Item> ICES_ICE = tag("ices/ice");
        public static final TagKey<Item> ICES_PACKED = tag("ices/packed");
        public static final TagKey<Item> ICES_BLUE = tag("ices/blue");
        public static final TagKey<Item> ICES_DRY = tag("ices/dry");
        public static final TagKey<Item> URANINITE_ORE = tag("ores/uraninite");
        public static final TagKey<Item> URANINITE_BLOCK = tag("storage_blocks/uraninite");
        public static final TagKey<Item> URANINITE_RAW = tag("storage_blocks/raw_uraninite");
        public static final TagKey<Item> WRENCHES = tag("wrenches");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
}
