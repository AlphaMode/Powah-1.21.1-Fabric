package owmii.powah.item;

import java.util.List;
import java.util.Optional;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import owmii.powah.api.wrench.IWrench;
import owmii.powah.api.wrench.IWrenchable;
import owmii.powah.api.wrench.WrenchMode;
import owmii.powah.block.cable.CableBlock;
import owmii.powah.block.cable.CableTile;
import owmii.powah.block.energizing.EnergizingOrbBlock;
import owmii.powah.components.PowahComponents;
import owmii.powah.components.WrenchLink;
import owmii.powah.lib.block.AbstractEnergyBlock;
import owmii.powah.lib.client.handler.IHudItem;
import owmii.powah.lib.item.ItemBase;
import owmii.powah.lib.logistics.energy.SideConfig;

public class WrenchItem extends ItemBase implements IHudItem, IWrench {
    private static final Direction[] DIRECTIONS = Direction.values();

    public WrenchItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, Level world, BlockPos pos, Player player, InteractionHand hand, Direction side,
            Vec3 hit) {
        if (player.isShiftKeyDown())
            return InteractionResult.PASS;
        BlockEntity te = world.getBlockEntity(pos);
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof IWrenchable iWrenchable
                && iWrenchable.onWrench(state, world, pos, player, hand, side, getWrenchMode(stack), hit)) {
            return InteractionResult.SUCCESS;
        } else {
            if (!world.isClientSide && getWrenchMode(stack).config()) {
                if (te instanceof CableTile) {
                    CableTile cable = (CableTile) te;
                    if (stack.getItem() instanceof WrenchItem) {
                        Optional<Direction> sides = CableBlock.getHitSide(hit, pos);
                        boolean[] flag = { false };
                        sides.ifPresent(direction -> {
                            SideConfig config = cable.getSideConfig();
                            config.nextType(direction);
                            cable.sync();
                        });
                        return InteractionResult.SUCCESS;
                    }
                }
//                else if (te instanceof TileBase.EnergyStorage) {
//                    TileBase.EnergyStorage storage = (TileBase.EnergyStorage) te;
//                    if (storage.isEnergyPresent(side)) {
//                        SideConfig config = storage.getSideConfig();
//                        config.nextType(side);
//                        storage.markDirtyAndSync();
//                        return ActionResultType.SUCCESS;
//                    }
//                }
            }
            if (getWrenchMode(stack).rotate()
                    // Only rotate Powah machines
                    && (state.getBlock() instanceof AbstractEnergyBlock<?, ?> || state.getBlock() instanceof EnergizingOrbBlock)) {
                final BlockState rotatedState = rotateState(world, state, pos);
                if (!state.equals(rotatedState)) {
                    world.setBlockAndUpdate(pos, rotatedState);
                    world.playSound(player, pos, rotatedState.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1F, 1F);
                    return InteractionResult.sidedSuccess(world.isClientSide);
                }
            }
        }
        return super.onItemUseFirst(stack, world, pos, player, hand, side, hit);
    }

    private BlockState rotateState(Level world, BlockState state, BlockPos pos) {
        for (Property<?> property : state.getProperties()) {
            if (property.getName().equals("facing") && property instanceof DirectionProperty dirProp) {
                final Direction current = state.getValue(dirProp);
                Direction rotated = nextDirection(current);

                // if the rotation isn't valid, try the next rotation
                while (!property.getPossibleValues().contains(rotated) || !state.setValue(dirProp, rotated).canSurvive(world, pos)) {
                    rotated = nextDirection(rotated);
                    // give up if we went all the way around
                    if (rotated == current) {
                        return state;
                    }
                }
                return state.setValue(dirProp, rotated);
            }
        }
        return state;
    }

    private static Direction nextDirection(Direction dir) {
        return DIRECTIONS[(dir.ordinal() + 1) % DIRECTIONS.length];
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (playerIn.isShiftKeyDown()) {
            nextWrenchMode(stack);
            playerIn.displayClientMessage(
                    Component.translatable("info.powah.wrench.mode",
                            Component.translatable("info.powah.wrench.mode." + getWrenchMode(stack).name().toLowerCase())
                                    .withStyle(ChatFormatting.YELLOW)),
                    true);
            return InteractionResultHolder.success(stack);
        }
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("info.powah.wrench.mode",
                Component.translatable("info.powah.wrench.mode." + getWrenchMode(stack).name().toLowerCase())
                        .withStyle(ChatFormatting.YELLOW)));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof Player) {
            Player player = (Player) entityIn;
            oneTimeInfo(player, stack,
                    Component.translatable("info.powah.wrench.mode",
                            Component.translatable("info.powah.wrench.mode." + getWrenchMode(stack).name().toLowerCase())
                                    .withStyle(ChatFormatting.YELLOW)));
        }
    }

    @Override
    public boolean renderHud(Level world, BlockPos pos, Player player, InteractionHand hand, Direction side, Vec3 hit) {
        return false;
    }

    private boolean changeWrenchMode(ItemStack stack, boolean next) {
        if (stack.getItem() instanceof IWrench)
            if (next) {
                nextWrenchMode(stack);
                return true;
            } else {
                prevWrenchMode(stack);
                return true;
            }
        return false;
    }

    private void nextWrenchMode(ItemStack stack) {
        WrenchMode mode = stack.getOrDefault(PowahComponents.WRENCH_MODE, WrenchMode.CONFIG);
        stack.set(PowahComponents.WRENCH_MODE, mode.nextWrenchMode());
    }

    private void prevWrenchMode(ItemStack stack) {
        WrenchMode mode = stack.getOrDefault(PowahComponents.WRENCH_MODE, WrenchMode.CONFIG);
        stack.set(PowahComponents.WRENCH_MODE, mode.prevWrenchMode());
    }

    @Override
    public WrenchMode getWrenchMode(ItemStack stack) {
        return stack.getOrDefault(PowahComponents.WRENCH_MODE, WrenchMode.CONFIG);
    }

    public WrenchLink getLinkData(ItemStack stack) {
        return stack.getOrDefault(PowahComponents.WRENCH_LINK, WrenchLink.NONE);
    }
}
