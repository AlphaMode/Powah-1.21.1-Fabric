package owmii.powah.item;

import java.util.List;
import java.util.Optional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import owmii.powah.Powah;
import owmii.powah.components.BoundPlayer;
import owmii.powah.components.PowahComponents;
import owmii.powah.lib.item.ItemBase;
import owmii.powah.lib.util.Player;
import owmii.powah.lib.util.Stack;

public class BindingCardItem extends ItemBase {
    private final boolean isMultiDim;

    public BindingCardItem(Properties properties, boolean isMultiDim) {
        super(properties);
        this.isMultiDim = isMultiDim;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, net.minecraft.world.entity.player.Player playerIn, LivingEntity target,
            InteractionHand hand) {
        if (Powah.config().general.dimensional_binding_card) {
            if (this == Itms.BINDING_CARD.get()) {
                if (target.getClass() == EnderMan.class || target.getClass() == Endermite.class) {
                    if (!playerIn.level().isClientSide) {
                        ItemStack stack1 = playerIn.getItemInHand(hand);
                        ItemStack stack2 = new ItemStack(Itms.BINDING_CARD_DIM.get());
                        BoundPlayer boundPlayer = stack1.get(PowahComponents.BOUND_PLAYER);
                        if (boundPlayer != null) {
                            stack2.set(PowahComponents.BOUND_PLAYER, boundPlayer);
                        }
                        playerIn.setItemInHand(hand, stack2);
                        target.playSound(SoundEvents.ENDERMAN_DEATH, 0.5F, 1.0F);
                        target.remove(Entity.RemovalReason.KILLED);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.interactLivingEntity(stack, playerIn, target, hand);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, net.minecraft.world.entity.player.Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        BoundPlayer lastBoundPlayer = stack.get(PowahComponents.BOUND_PLAYER);
        if (!stack.has(PowahComponents.BOUND_PLAYER)) {
            BoundPlayer boundPlayer = new BoundPlayer(playerIn.getUUID(), playerIn.getDisplayName().getString());
            stack.set(PowahComponents.BOUND_PLAYER, boundPlayer);
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
        } else if (lastBoundPlayer != null && !playerIn.getUUID().equals(lastBoundPlayer.playerUuid())) {
            playerIn.displayClientMessage(
                    Component.translatable("chat.powah.no.binding", lastBoundPlayer.playerName()).withStyle(ChatFormatting.DARK_RED), true);
            return new InteractionResultHolder<>(InteractionResult.FAIL, stack);
        }
        return new InteractionResultHolder<>(InteractionResult.PASS, stack);
    }

    public Optional<ServerPlayer> getPlayer(ServerLevel level, ItemStack stack) {
        BoundPlayer boundPlayer = stack.get(PowahComponents.BOUND_PLAYER);
        if (boundPlayer != null) {
            return Player.get(level, boundPlayer.playerName());
        }
        return Optional.empty();
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
        BoundPlayer boundPlayer = stack.get(PowahComponents.BOUND_PLAYER);
        if (boundPlayer == null) {
            tooltip.add(Component.translatable("info.powah.click.to.bind").withStyle(ChatFormatting.DARK_GRAY));
        } else {
            tooltip.add(Component.translatable("info.lollipop.owner", ChatFormatting.YELLOW + boundPlayer.playerName())
                    .withStyle(ChatFormatting.GRAY));
        }
    }

    public boolean isMultiDim(ItemStack stack) {
        return this.isMultiDim;
    }
}
