package owmii.powah.data;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import owmii.powah.block.Blcks;
import owmii.powah.block.Tier;
import owmii.powah.fabric.data.ITags;
import owmii.powah.item.ItemGroups;
import owmii.powah.item.Itms;

public class EnlishLangProvider extends FabricLanguageProvider {
    protected EnlishLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder builder) {
        // Tags

        // Block Tags
        builder.add(ITags.Blocks.ICES, "Any Ice");
        builder.add(ITags.Blocks.ICES_ICE, "Ice");
        builder.add(ITags.Blocks.ICES_PACKED, "Packed Ices");
        builder.add(ITags.Blocks.ICES_BLUE, "Blue Ices");
        builder.add(ITags.Blocks.ICES_DRY, "Dry Ices");
        builder.add(ITags.Blocks.URANINITE_ORE, "Uranite Ores");
        builder.add(ITags.Blocks.URANINITE_BLOCK, "Uranite Blocks");

        // Items Tags
        builder.add(ITags.Items.ICES, "Ices");
        builder.add(ITags.Items.ICES_ICE, "Ice");
        builder.add(ITags.Items.ICES_PACKED, "Packed Ices");
        builder.add(ITags.Items.ICES_BLUE, "Blue Ices");
        builder.add(ITags.Items.ICES_DRY, "Dry Ices");
        builder.add(ITags.Items.URANINITE_ORE, "Uranite Ores");
        builder.add(ITags.Items.URANINITE_BLOCK, "Uranite Blocks");
        builder.add(ITags.Items.URANINITE_RAW, "Raw Uraninite Ores");
        builder.add(ITags.Items.WRENCHES, "Wrenches");

        // Blocks
        builder.add(Blcks.BLAZING_CRYSTAL.get(), "Block Of Blazing Crystal");
        builder.add(Blcks.DEEPSLATE_URANINITE_ORE.get(), "Deepslate Uraninite Ore");
        builder.add(Blcks.DEEPSLATE_URANINITE_ORE_DENSE.get(), "Deepslate Uraninite Ore (Dense)");
        builder.add(Blcks.DEEPSLATE_URANINITE_ORE_POOR.get(), "Deepslate Uraninite Ore (Poor)");
        builder.add(Blcks.DRY_ICE.get(), "Dry Ice");

        builder.add(Blcks.ENERGIZED_STEEL.get(), "Block Of Energized Steel");
        builder.add(Blcks.ENERGIZING_ORB.get(), "Energizing Orb");

        builder.add(Blcks.NIOTIC_CRYSTAL.get(), "Block Of Niotic Crystal");
        builder.add(Blcks.NITRO_CRYSTAL.get(), "Block Of Nitro Crystal");

        builder.add(Blcks.SPIRITED_CRYSTAL.get(), "Block Of Spirited Crystal");

        builder.add(Blcks.URANINITE.get(), "Block Of Uraninite");
        builder.add(Blcks.URANINITE_ORE.get(), "Uraninite Ore");
        builder.add(Blcks.URANINITE_ORE_DENSE.get(), "Uraninite Ore (Dense)");
        builder.add(Blcks.URANINITE_ORE_POOR.get(), "Uraninite Ore (Poor)");

        for (Tier tier : Blcks.ENDER_CELL.getVariants()) {
            builder.add(Blcks.ENDER_CELL.get(tier), "Ender Cell (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.ENDER_GATE.getVariants()) {
            builder.add(Blcks.ENDER_GATE.get(tier), "Ender Gate (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.ENERGIZING_ROD.getVariants()) {
            builder.add(Blcks.ENERGIZING_ROD.get(tier), "Energizing Rod (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.ENERGY_CABLE.getVariants()) {
            builder.add(Blcks.ENERGY_CABLE.get(tier), "Energy Cable (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.ENERGY_CELL.getVariants()) {
            builder.add(Blcks.ENERGY_CELL.get(tier), "Energy Cell (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.ENERGY_DISCHARGER.getVariants()) {
            builder.add(Blcks.ENERGY_DISCHARGER.get(tier), "Energy Discharger (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.ENERGY_HOPPER.getVariants()) {
            builder.add(Blcks.ENERGY_HOPPER.get(tier), "Energy Hopper (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.FURNATOR.getVariants()) {
            builder.add(Blcks.FURNATOR.get(tier), "Furnator (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.MAGMATOR.getVariants()) {
            builder.add(Blcks.MAGMATOR.get(tier), "Magmator (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.PLAYER_TRANSMITTER.getVariants()) {
            builder.add(Blcks.PLAYER_TRANSMITTER.get(tier), "Player Transmitter (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.REACTOR.getVariants()) {
            builder.add(Blcks.REACTOR.get(tier), "Reactor (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.SOLAR_PANEL.getVariants()) {
            builder.add(Blcks.SOLAR_PANEL.get(tier), "Solar Panel (%s)".formatted(tier.getLocaleName()));
        }

        for (Tier tier : Blcks.THERMO_GENERATOR.getVariants()) {
            builder.add(Blcks.THERMO_GENERATOR.get(tier), "Thermo Generator (%s)".formatted(tier.getLocaleName()));
        }

        // Items
        builder.add(Itms.AERIAL_PEARL.get(), "Aerial Pearl");

        builder.add(Itms.BINDING_CARD.get(), "Binding Card");
        builder.add(Itms.BINDING_CARD_DIM.get(), "Binding Card (Dimensional)");
        builder.add(Itms.BLANK_CARD.get(), "Blank Card");

        builder.add(Itms.CAPACITOR_BASIC.get(), "Basic Capacitor");
        builder.add(Itms.CAPACITOR_BASIC_LARGE.get(), "Basic Capacitor (Large)");
        builder.add(Itms.CAPACITOR_BASIC_TINY.get(), "Basic Capacitor (Tiny)");

        builder.add(Itms.CAPACITOR_BLAZING.get(), "Blazing Capacitor");
        builder.add(Itms.CAPACITOR_HARDENED.get(), "Hardened Capacitor");
        builder.add(Itms.CAPACITOR_NIOTIC.get(), "Niotic Capacitor");
        builder.add(Itms.CAPACITOR_NITRO.get(), "Nitro Capacitor");
        builder.add(Itms.CAPACITOR_SPIRITED.get(), "Spirited Capacitor");

        builder.add(Itms.CHARGED_SNOWBALL.get(), "Charged Snowball");

        builder.add(Itms.BOOK.get(), "Manual (Powah!)");

        builder.add(Itms.BLAZING_CRYSTAL.get(), "Blazing Crystal");
        builder.add(Itms.NIOTIC_CRYSTAL.get(), "Niotic Crystal");
        builder.add(Itms.NITRO_CRYSTAL.get(), "Nitro Crystal");
        builder.add(Itms.SPIRITED_CRYSTAL.get(), "Spirited Crystal");

        builder.add(Itms.DIELECTRIC_CASING.get(), "Dielectric Casing");
        builder.add(Itms.DIELECTRIC_PASTE.get(), "Dielectric Paste");
        builder.add(Itms.DIELECTRIC_ROD.get(), "Dielectric Rod");
        builder.add(Itms.DIELECTRIC_ROD_HORIZONTAL.get(), "Dielectric Rod (Horizontal)");

        builder.add(Itms.ENDER_CORE.get(), "Ender Core");
        builder.add(Itms.ENERGIZED_STEEL.get(), "Energized Steel");

        builder.add(Itms.LENS_OF_ENDER.get(), "Lens Of Ender");
        builder.add(Itms.PHOTOELECTRIC_PANE.get(), "Photoelectric Pane");
        builder.add(Itms.PLAYER_AERIAL_PEARL.get(), "Player Aerial Pearl");
        builder.add(Itms.THERMOELECTRIC_PLATE.get(), "Thermo Plate");
        builder.add(Itms.URANINITE.get(), "Uraninite");
        builder.add(Itms.URANINITE_RAW.get(), "Raw Uraninite");
        builder.add(Itms.WRENCH.get(), "Wrench");

        for (Tier tier : Itms.BATTERY.getVariants()) {
            builder.add(Itms.BATTERY.get(tier), "Battery (%s)".formatted(tier.getLocaleName()));
        }

        // ItemGroup
        builder.add(ItemGroups.MAIN_KEY, "Powah");

        // Chat
        builder.add("chat.powah.no.binding", "This Card is already bound to %s!");
        builder.add("chat.powah.not.enough.blocks", "Not enough Blocks! you need %s more!");
        builder.add("chat.powah.wrench.link.done", "Linking done!");
        builder.add("chat.powah.wrench.link.fail", "Out of range!!");
        builder.add("chat.powah.wrench.link.start", "Started linking ...");

        // JEI
        builder.add("gui.powah.jei.category.coolant", "Coolants");
        builder.add("gui.powah.jei.category.energizing", "Energizing");
        builder.add("gui.powah.jei.category.heat.sources", "Heat Sources");
        builder.add("gui.powah.jei.category.magmatic", "Magmatic Fluids");
        builder.add("gui.powah.jei.category.solid.coolant", "Solid Coolants");
        builder.add("jei.powah.binding_card_dim", "Use a Binding card on Enderman or Endermite.");
        builder.add("jei.powah.lens_of_ender", "Use a Photoelectric Pane on Enderman or Endermite.");
        builder.add("jei.powah.player_aerial_pearl", "Use an Aerial Pearl on Zombie or Husk.");

        // Info
        builder.add("info.powah.carbon", "Carbon");
        builder.add("info.powah.channel", "Channel: %s");
        builder.add("info.powah.charging.speed", "Charging Speed: %s FE/t");
        builder.add("info.powah.click.to.bind", "Right click to bind.");
        builder.add("info.powah.fuel.consumption", "Fuel Consumption");
        builder.add("info.powah.gen.mode", "Auto Mode");
        builder.add("info.powah.gen.mode.desc", "The reactor will stop when is full and start \nwhen has less than 70% of energy.");
        builder.add("info.powah.generation.factor", "Generation Factor");
        builder.add("info.powah.new.capacity", "New Capacity: %s FE");
        builder.add("info.powah.new.energy", "New Energy: %s FE");
        builder.add("info.powah.production", "Production");
        builder.add("info.powah.redstone", "Redstone");
        builder.add("info.powah.shift.to.apply", "[SHIFT + CLICK] to apply.");
        builder.add("info.powah.solid.coolant", "Solid Coolant");
        builder.add("info.powah.unlimited", "Unlimited Powah!!!");
        builder.add("info.powah.wrench.mode", "Mode: %s");
        builder.add("info.powah.wrench.mode.config", "Config");
        builder.add("info.powah.wrench.mode.link", "Link");
        builder.add("info.powah.wrench.mode.rotate", "Rotate");

        // Wiki
        builder.add("wiki.powah.battery", "Batteries");
        builder.add("wiki.powah.battery_0",
                "Charge items when is in player inventory, can Also be used to upgrade the capacity of a Ender Network channel by Shift clicking a Battery to an Ender Cell GUI, if the Battery contain energy then will also be applied to the ender network channel.");
        builder.add("wiki.powah.binding_card", "Binding Card");
        builder.add("wiki.powah.binding_card_0", "Binding Card used to link a player with a Player Transmitter in the same dimension.");
        builder.add("wiki.powah.binding_card_1", "You need to link it with you before adding it to the player transmitter, Right-click it to bind.");
        builder.add("wiki.powah.binding_card_dim", "Binding Card (Dimensional)");
        builder.add("wiki.powah.binding_card_dim_0", "Dimensional Binding Card used to link a player with a Player Transmitter across dimensions.");
        builder.add("wiki.powah.binding_card_dim_1",
                "You need to link it with you before adding it to the player transmitter, Right-click it to bind.");
        builder.add("wiki.powah.charged_snowball", "Charged Snowball");
        builder.add("wiki.powah.charged_snowball_0",
                "Throwing a Charged Snowball will cause a bolt of lightning to spawn when hitting the ground or when it hits the mob, obtained by energizing a snowball.");
        builder.add("wiki.powah.dry_ice", "Dry Ice");
        builder.add("wiki.powah.dry_ice_0",
                "Dry Ice used mainly to cool down reactors, it can be found underground at levels below 64, you can also obtain it by energizing two pieces of blue ice.");
        builder.add("wiki.powah.ender_cell", "Ender Cells");
        builder.add("wiki.powah.ender_cell_0",
                "The Ender Cell its a block used to store energy (FE) to a specific channel of the ender network of the owner.");
        builder.add("wiki.powah.ender_cell_1",
                "You can access the energy stored of a selected channel from anywhere in the world if the Ender Cell that you want to transfer power from/to have an active channel with a valid capacity.");
        builder.add("wiki.powah.ender_cell_2",
                "You can add capacity to a selected channel by shift-clicking an Energy Cell or Battery to the Ender Cell GUI.");
        builder.add("wiki.powah.ender_cell_3", "The amount added is the same of the item you've added, max capacity a channel can have is 9E FE.");
        builder.add("wiki.powah.ender_gate", "Ender Gates");
        builder.add("wiki.powah.ender_gate_0", "Transfer energy between the adjacent block and the ender network.");
        builder.add("wiki.powah.ender_gate_1", "Unlike the Ender cell you can not Upgrade the network from it.");
        builder.add("wiki.powah.energizing", "Energizing");
        builder.add("wiki.powah.energizing_0",
                "The Energizing Orb its a block used to energize items, require at least one Energizing Rod in range of %s to work, the energizing speed depends on amount of rods and the rod tier (I/O rate).");
        builder.add("wiki.powah.energizing_1",
                "The orb does not require energy but the rods must be placed on cables or any Forge Energy (FE) block to work.");
        builder.add("wiki.powah.energizing_2",
                "When placing the orb/rod in range will automatically link to each other, also you can use the <powah:wrench> with Link mode to re-link them.");
        builder.add("wiki.powah.energy_blocks", "Functional Blocks");
        builder.add("wiki.powah.energy_cable", "Energy Cables");
        builder.add("wiki.powah.energy_cable_0", "Cables are used to transfer power between machines.");
        builder.add("wiki.powah.energy_cable_1",
                "You can change transfer mode of by right-clicking a cable using <powah:wrench> with Config mode selected.");
        builder.add("wiki.powah.energy_cell", "Energy Cells");
        builder.add("wiki.powah.energy_cell_0", "The Energy Cell its a block used to store energy (FE).");
        builder.add("wiki.powah.energy_cell_1",
                "Can Also be used to add capacity to an Ender Network channel by Shift-clicking it to an Ender Cell GUI, if the Energy Cell contains energy then will also be applied to the ender network channel.");
        builder.add("wiki.powah.energy_discharger", "Energy Discharger");
        builder.add("wiki.powah.energy_discharger_0",
                "The Energy Discharger its a block used to drain energy (FE) out of charged items and then store it to an internal buffer if then connected via cables to extract that stored power and re-using it again.");
        builder.add("wiki.powah.energy_hopper", "Energy Hopper");
        builder.add("wiki.powah.energy_hopper_0",
                "The Energy Hopper its a block used to charge chargeable items inside an adjacent inventory like a chest or any block with an accessible inventory and not a not has forge energy.");
        builder.add("wiki.powah.furnator", "Furnator");
        builder.add("wiki.powah.furnator_0", "The Furnator is an FE generator that generates energy from solid Furnace fuel like coal, wood ...");
        builder.add("wiki.powah.furnator_1",
                "High tiers generate more FE/t and it has higher energy output, also they burn the fuel faster with the same energy gained per fuel tick.");
        builder.add("wiki.powah.generators", "Generators");
        builder.add("wiki.powah.items", "Items");
        builder.add("wiki.powah.lens_of_ender", "Lens Of Ender");
        builder.add("wiki.powah.lens_of_ender_0", "When Applying a Lens Of Ender to a Solar Panel will make it see through blocks.");
        builder.add("wiki.powah.magmator", "Magmator");
        builder.add("wiki.powah.magmator_0", "The Magmator is an FE generator that generates energy from high temp fluids like Lava.");
        builder.add("wiki.powah.magmator_1",
                "High tiers generate more FE/t and it has higher energy output, also they burn the fuel faster with the same energy gained per fuel tick.");
        builder.add("wiki.powah.materials", "Materials");
        builder.add("wiki.powah.player_aerial_pearl", "Player Aerial Pearl");
        builder.add("wiki.powah.player_aerial_pearl_0", "Player Aerial Pearl used to craft the player transmitter.");
        builder.add("wiki.powah.player_aerial_pearl_1", "You can get it by using an Aerial Pearl on a Zombie or Husk.");
        builder.add("wiki.powah.player_transmitter", "Player Transmitter");
        builder.add("wiki.powah.player_transmitter_0",
                "The Player Transmitter its a block used to charge items wirelessly in linked player inventory including armor slots and off-hand anywhere in the same dimension when has a normal binding card and across dimensions when has a dimensional binding card.");
        builder.add("wiki.powah.reactor", "Reactor");
        builder.add("wiki.powah.reactor_0", "The Reactor is a multi-block (FE) generator that use Uraninite as main fuel.");
        builder.add("wiki.powah.reactor_1",
                "To build it you will need 36 Reactor block in your hand and placing them in a 3X4 replaceable area, then the reactor will complete building itself automatically.");
        builder.add("wiki.powah.reactor_2",
                "When Generating energy the reactor heats up causing it to consume fuel faster and generating less FE/t, so you need to cool it down using a coolant like water, also you can use a solid coolant like snow or ice for extra coldness, solid coolant require liquid coolant to work.");
        builder.add("wiki.powah.reactor_3", "Keep the reactor buffer full of fuel for better production.");
        builder.add("wiki.powah.reactor_4", "Carbon materials like coal, wood..., will improve the fuel efficiency and will add +180 C of heat.");
        builder.add("wiki.powah.reactor_5", "Redstone will speed up the production and the fuel consumption and will add +120 C of heat.");
        builder.add("wiki.powah.reactor_6", "The reactor will stop when is full and start when has less than 70% of energy if the auto mode is on.");
        builder.add("wiki.powah.solar_panel", "Solar Panel");
        builder.add("wiki.powah.solar_panel_0",
                "The Solar Panel is an FE generator that generates energy when exposed to sunlight, high tiers generates more FE/t, any block that stop light above the Solar panel will stop its production.");
        builder.add("wiki.powah.storage_transfer", "Storage / Transfer");
        builder.add("wiki.powah.thermo_generator", "Thermo Generator");
        builder.add("wiki.powah.thermo_generator_0",
                "The Thermo Generator is an FE generator that generates energy when placed on top of a high temp block/fluid like lava, require a coolant fluid like water to run.");
        builder.add("wiki.powah.uraninite", "Uraninite Ore");
        builder.add("wiki.powah.uraninite_0",
                "Uraninite Ore is an ore rarely found underground at levels below 64 for poor, below 20 for the normal, and below 0 for dense, and is found in 1 - 5 block deposits.");
        builder.add("wiki.powah.uraninite_1",
                "An iron or better pickaxe is needed to mine it, and when mined it will drop 1 piece of Raw Uraninite based on the ore type (the amount dropped are effected by fortune).");
        builder.add("wiki.powah.welcome_back", "Welcome back %s :)");
        builder.add("wiki.powah.wrench", "Wrench");
        builder.add("wiki.powah.wrench_0", "The Wrench has 3 modes:");
        builder.add("wiki.powah.wrench_1", "Config Mode: used to change cables I/O configuration.");
        builder.add("wiki.powah.wrench_2", "Link Mode: used to link linkable blocks like energizing orb and rods.");
        builder.add("wiki.powah.wrench_3", "Rotate Mode: used to rotate blocks horizontally.");
    }
}
