package owmii.powah.block.thermo;

import dev.architectury.fluid.FluidStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import owmii.powah.api.PowahAPI;
import owmii.powah.block.Tier;
import owmii.powah.block.Tiles;
import owmii.powah.lib.block.AbstractEnergyProvider;
import owmii.powah.lib.block.IInventoryHolder;
import owmii.powah.lib.block.ITankHolder;
import owmii.powah.lib.logistics.energy.Energy;
import owmii.powah.lib.util.Util;

public class ThermoTile extends AbstractEnergyProvider<ThermoBlock> implements IInventoryHolder, ITankHolder {
    public long generating;

    public ThermoTile(BlockPos pos, BlockState state, Tier variant) {
        super(Tiles.THERMO_GEN.get(), pos, state, variant);
        this.tank.setCapacity(FluidStack.bucketAmount() * 4)
                .validate(stack -> PowahAPI.getCoolant(stack.getFluid()) != 0)
                .setChange(() -> ThermoTile.this.sync(10));
        this.inv.add(1);
    }

    public ThermoTile(BlockPos pos, BlockState state) {
        this(pos, state, Tier.STARTER);
    }

    @Override
    public void readSync(HolderLookup.Provider registries, CompoundTag nbt) {
        super.readSync(registries, nbt);
        this.generating = nbt.getLong("generating");
    }

    @Override
    public CompoundTag writeSync(HolderLookup.Provider registries, CompoundTag nbt) {
        nbt.putLong("generating", this.generating);
        return super.writeSync(registries, nbt);
    }

    @Override
    protected int postTick(Level world) {
        boolean flag = chargeItems(1) + extractFromSides(world) > 0;
        int i = 0;
        if (!isRemote() && checkRedstone() && !this.tank.isEmpty()) {
            FluidStack fluid = this.tank.getFluid();
            int fluidCooling = PowahAPI.getCoolant(fluid.getFluid());
            if (fluidCooling != 0) {
                BlockPos heatPos = this.worldPosition.below();
                BlockState state = world.getBlockState(heatPos);
                Block block = state.getBlock();
                int heat = PowahAPI.getHeatSource(block);
                if (!this.energy.isFull() && heat != 0) {
                    if (block instanceof LiquidBlock) {
                        if (!state.getFluidState().isSource()) {
                            int level = state.getValue(LiquidBlock.LEVEL);
                            heat = (int) (heat / ((float) level + 1));
                        }
                    }
                    double heatRatio = heat / 1000.0D;
                    double coolingRatio = Math.max(1D, (1D + Math.abs(fluidCooling)) / 2D);
                    this.generating = (int) (heatRatio * coolingRatio * getGeneration());
                    this.energy.produce(this.generating);
                    if (world.getGameTime() % 40 == 0L) {
                        this.tank.drain(Util.millibucketAmount(), false);
                    }
                } else {
                    this.generating = 0;
                }
            } else {
                this.generating = 0;
            }
        }

        return flag || this.generating > 0 ? 5 : -1;
    }

    @Override
    public boolean keepEnergy() {
        return true;
    }

    @Override
    public boolean keepFluid() {
        return true;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack) {
        return Energy.chargeable(stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack) {
        return true;
    }
}
