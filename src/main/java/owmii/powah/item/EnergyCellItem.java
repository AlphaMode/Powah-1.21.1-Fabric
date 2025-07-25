package owmii.powah.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Nullable;
import owmii.powah.api.energy.endernetwork.IEnderExtender;
import owmii.powah.block.Tier;
import owmii.powah.block.energycell.EnergyCellBlock;
import owmii.powah.config.v2.types.EnergyConfig;
import owmii.powah.lib.item.EnergyBlockItem;
import owmii.powah.lib.logistics.energy.Energy;

public class EnergyCellItem extends EnergyBlockItem<EnergyConfig, EnergyCellBlock> implements IEnderExtender {
    public EnergyCellItem(EnergyCellBlock block, Properties properties, @Nullable ResourceKey<CreativeModeTab> group) {
        super(block, properties.rarity(block.getVariant().equals(Tier.CREATIVE) ? Rarity.EPIC : Rarity.COMMON), group);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return getVariant().equals(Tier.CREATIVE) || super.isFoil(stack);
    }

    @Override
    public long getExtendedCapacity(ItemStack stack) {
        if (getVariant().equals(Tier.CREATIVE)) {
            return 0;
        }
        return getConfig().getCapacity(getVariant());
    }

    @Override
    public long getExtendedEnergy(ItemStack stack) {
        if (getVariant().equals(Tier.CREATIVE)) {
            return 0;
        }
        return Energy.getStored(stack);
    }
}
