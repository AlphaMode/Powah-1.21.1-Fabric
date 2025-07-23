package owmii.powah.lib.logistics.inventory;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class RecipeWrapper implements RecipeInput {

    protected final ItemStackHandler inv;

    public RecipeWrapper(ItemStackHandler inv) {
        this.inv = inv;
    }

    /**
     * Returns the stack in this slot. This stack should be a modifiable reference, not a copy of a stack in your inventory.
     */
    @Override
    public ItemStack getItem(int slot) {
        return inv.getStackInSlot(slot);
    }

    @Override
    public int size() {
        return inv.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < inv.getSlots(); i++) {
            if (!inv.getStackInSlot(i).isEmpty())
                return false;
        }
        return true;
    }
}
