package owmii.powah.client;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import owmii.powah.components.BoundPlayer;
import owmii.powah.components.PowahComponents;
import owmii.powah.item.Itms;

public class ItemModelProperties {
    public static void register() {
        ItemPropertiesRegistry.register(Itms.BINDING_CARD.get(), ResourceLocation.withDefaultNamespace("bound"),
                ItemModelProperties::renderBindingCard);
        ItemPropertiesRegistry.register(Itms.BINDING_CARD_DIM.get(), ResourceLocation.withDefaultNamespace("bound"),
                ItemModelProperties::renderBindingCard);
    }

    static float renderBindingCard(ItemStack stack, ClientLevel world, LivingEntity livingEntity, int var4) {
        float f = 0.0F;
        BoundPlayer boundPlayer = stack.get(PowahComponents.BOUND_PLAYER);
        if (boundPlayer != null) {
            f = 1.0F;
        }
        return f;
    }
}
