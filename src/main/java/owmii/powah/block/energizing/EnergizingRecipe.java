package owmii.powah.block.energizing;

import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import owmii.powah.Powah;
import owmii.powah.lib.logistics.inventory.RecipeWrapper;
import owmii.powah.recipe.Recipes;

public class EnergizingRecipe implements Recipe<RecipeWrapper> {
    public static final ResourceLocation ID = Powah.id("energizing");
    private final ItemStack output;
    private final long energy;
    private final NonNullList<Ingredient> ingredients;

    public EnergizingRecipe(ItemStack output, long energy, Ingredient... ingredients) {
        this(output, energy, NonNullList.of(Ingredient.EMPTY, ingredients));
    }

    public EnergizingRecipe(ItemStack output, long energy, NonNullList<Ingredient> ingredients) {
        this.output = output;
        this.energy = energy;
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level world) {
        List<Ingredient> stacks = new ArrayList<>(getIngredients());
        for (int i = 1; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                boolean flag = false;
                Iterator<Ingredient> itr = stacks.iterator();
                while (itr.hasNext()) {
                    Ingredient ingredient = itr.next();
                    if (ingredient.test(stack)) {
                        flag = true;
                        itr.remove();
                        break;
                    }
                }
                if (!flag) {
                    return false;
                }
            }
        }
        return stacks.isEmpty();
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv, HolderLookup.Provider lookup) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    public ItemStack getResultItem() {
        return output;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider lookup) {
        return this.output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Recipes.ENERGIZING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return Recipes.ENERGIZING.get();
    }

    public long getEnergy() {
        return this.energy;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<EnergizingRecipe> {
        public static final MapCodec<EnergizingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(EnergizingRecipe::getResultItem),
                Codec.LONG.optionalFieldOf("energy", 0L).flatXmap(energy -> {
                    if (energy <= 0) {
                        return DataResult.error(() -> "Energizing recipe require energy to work!!");
                    }
                    return DataResult.success(Math.max(1, (long) (energy * Powah.config().general.energizing_energy_ratio)));
                }, DataResult::success).forGetter(EnergizingRecipe::getEnergy),
                Ingredient.CODEC_NONEMPTY
                        .listOf()
                        .fieldOf("ingredients")
                        .flatXmap(
                                ingredients -> {
                                    Ingredient[] ingredientsArray = ingredients.stream().filter(ingredient -> !ingredient.isEmpty())
                                            .toArray(Ingredient[]::new);

                                    if (ingredientsArray.length == 0) {
                                        throw new JsonParseException("No ingredients for energizing recipe");
                                    } else if (ingredientsArray.length > 6) {
                                        throw new JsonParseException("Too many ingredients for energizing recipe the max is 6");
                                    }
                                    if (ingredientsArray.length == 0) {
                                        return DataResult.error(() -> "No ingredients for energizing recipe");
                                    } else {
                                        return ingredientsArray.length > 6
                                                ? DataResult.error(() -> "Too many ingredients for energizing recipe the max is 6")
                                                : DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredientsArray));
                                    }
                                },
                                DataResult::success)
                        .forGetter(recipe -> recipe.ingredients))
                .apply(instance, EnergizingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, EnergizingRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork,
                Serializer::fromNetwork);

        public static EnergizingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            NonNullList<Ingredient> list = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
            list.replaceAll(ingredient -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack output = ItemStack.STREAM_CODEC.decode(buffer);
            return new EnergizingRecipe(output, buffer.readLong(), list);
        }

        public static void toNetwork(RegistryFriendlyByteBuf buffer, EnergizingRecipe recipe) {
            buffer.writeInt(recipe.ingredients.size());
            recipe.ingredients.forEach(ingredient -> Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient));
            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
            buffer.writeLong(recipe.energy);
        }

        @Override
        public MapCodec<EnergizingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, EnergizingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
