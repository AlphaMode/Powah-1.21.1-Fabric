package owmii.powah.lib.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import owmii.powah.components.PowahComponents;
import owmii.powah.components.StorableComponent;
import owmii.powah.lib.logistics.IRedstoneInteract;
import owmii.powah.lib.logistics.Redstone;
import owmii.powah.lib.logistics.fluid.Tank;
import owmii.powah.lib.logistics.inventory.Inventory;
import owmii.powah.lib.registry.IVariant;

@SuppressWarnings("unchecked")
public class AbstractTileEntity<V extends IVariant, B extends AbstractBlock<V, B>> extends BlockEntity implements IBlockEntity, IRedstoneInteract {
    /**
     * Used when this is instance of {@link IInventoryHolder}
     **/
    protected final Inventory inv = Inventory.createBlank();
    /**
     * Used when this is instance of {@link ITankHolder}
     **/
    protected final Tank tank = new Tank(0);

    protected V variant;
    protected boolean isContainerOpen;
    /**
     * Used when this is instance of {@link IRedstoneInteract}
     **/
    private Redstone redstone = Redstone.IGNORE;

    public AbstractTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        this(type, pos, state, IVariant.getEmpty());
        this.tank.validate(stack -> true);
    }

    public AbstractTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, V variant) {
        super(type, pos, state);
        this.variant = variant;
        if (this instanceof IInventoryHolder) {
            this.inv.setTile((IInventoryHolder) this);
        }
    }

    public B getBlock() {
        return (B) getBlockState().getBlock();
    }

    public V getVariant() {
        return this.variant;
    }

    @Override
    public final void loadAdditional(CompoundTag tag, HolderLookup.Provider registryLookup) {
        super.loadAdditional(tag, registryLookup);
        readSync(registryLookup, tag);

        if (!tag.contains("#c")) { // Server only...
            loadServerOnly(tag);
        }
    }

    @Override
    protected final void saveAdditional(CompoundTag tag, HolderLookup.Provider registryLookup) {
        super.saveAdditional(tag, registryLookup);
        writeSync(registryLookup, tag);
        saveServerOnly(tag);
    }

    @Override
    public final CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        var tag = saveWithoutMetadata(registryLookup);
        tag.putBoolean("#c", true); // mark client tag
        return tag;
    }

    @Nullable
    @Override
    public final ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    protected void loadServerOnly(CompoundTag compound) {
    }

    protected CompoundTag saveServerOnly(CompoundTag compound) {
        return compound;
    }

    protected void readSync(HolderLookup.Provider registries, CompoundTag nbt) {
        if (!this.variant.isEmpty() && nbt.contains("variant", 3)) {
            this.variant = (V) this.variant.read(nbt, "variant");
        }
        if (this instanceof IInventoryHolder && !keepInventory()) {
            this.inv.deserializeNBT(nbt, registries);
        }
        if (this instanceof ITankHolder tankHolder) {
            if (!tankHolder.keepFluid()) {
                this.tank.readFromNBT(registries, nbt);
            }
        }
        this.redstone = Redstone.values()[nbt.getInt("redstone_mode")];
        readStorable(registries, nbt);
    }

    protected CompoundTag writeSync(HolderLookup.Provider registries, CompoundTag nbt) {
        if (!this.variant.isEmpty()) {
            this.variant.write(nbt, (Enum<?>) this.variant, "variant");
        }
        if (this instanceof IInventoryHolder && !keepInventory()) {
            nbt.merge(this.inv.serializeNBT(registries));
        }
        if (this instanceof ITankHolder tankHolder) {
            if (!tankHolder.keepFluid()) {
                this.tank.writeToNBT(registries, nbt);
            }
        }
        nbt.putInt("redstone_mode", this.redstone.ordinal());
        return writeStorable(registries, nbt);
    }

    public void readStorable(HolderLookup.Provider registries, CompoundTag nbt) {
        if (this instanceof IInventoryHolder && keepInventory()) {
            this.inv.deserializeNBT(nbt, registries);
        }
        if (this instanceof ITankHolder tankHolder) {
            if (tankHolder.keepFluid()) {
                this.tank.readFromNBT(registries, nbt);
            }
        }
    }

    public CompoundTag writeStorable(HolderLookup.Provider registries, CompoundTag nbt) {
        if (this instanceof IInventoryHolder && keepInventory()) {
            nbt.merge(this.inv.serializeNBT(registries));
        }
        if (this instanceof ITankHolder tankHolder) {
            if (tankHolder.keepFluid()) {
                this.tank.writeToNBT(registries, nbt);
            }
        }
        return nbt;
    }

    @Override
    public void onPlaced(Level world, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        StorableComponent storable = stack.get(PowahComponents.STORED);
        if (storable != null) {
            CompoundTag tag = storable.tag();
            if (!tag.isEmpty()) {
                readStorable(world.registryAccess(), tag);
            }
        }
    }

    @Override
    public void onRemoved(Level world, BlockState state, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            if (this instanceof IInventoryHolder) {
                if (!keepInventory() || !keepStorable()) {
                    getInventory().drop(world, this.worldPosition);
                }
            }
        }
    }

    public ItemStack storeToStack(ItemStack stack) {
        CompoundTag nbt = writeStorable(level.registryAccess(), new CompoundTag());
        if (!nbt.isEmpty() && keepStorable()) {
            stack.set(PowahComponents.STORED, new StorableComponent(nbt));
        }
        return stack;
    }

    public static <T extends AbstractTileEntity> T fromStack(ItemStack stack, T tile) {
        StorableComponent storable = stack.get(PowahComponents.STORED);
        if (storable != null) {
            tile.readStorable(tile.level.registryAccess(), storable.tag());
        }
        return tile;
    }

    public boolean keepStorable() {
        return true;
    }

    protected boolean keepInventory() {
        return false;
    }

    public Tank getTank() {
        return this.tank;
    }

    public Redstone getRedstoneMode() {
        return this.redstone;
    }

    public void setRedstoneMode(Redstone mode) {
        this.redstone = mode;
    }

    public boolean checkRedstone() {
        boolean power = this.level != null && this.level.getBestNeighborSignal(this.worldPosition) > 0;
        return Redstone.IGNORE.equals(getRedstoneMode()) || power && Redstone.ON.equals(getRedstoneMode())
                || !power && Redstone.OFF.equals(getRedstoneMode());
    }

    public void sync() {
        if (this.level instanceof ServerLevel) {
            final BlockState state = getBlockState();
            this.level.sendBlockUpdated(this.worldPosition, state, state, 3);
            setChanged();
        }
    }

    public boolean isRemote() {
        return this.level != null && this.level.isClientSide;
    }

    public void setContainerOpen(boolean value) {
        final boolean b = this.isContainerOpen;
        this.isContainerOpen = value;
        if (b != value) {
            sync();
        }
    }

    public Inventory getInventory() {
        return this.inv;
    }
}
