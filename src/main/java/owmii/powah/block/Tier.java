package owmii.powah.block;

import owmii.powah.lib.registry.IVariant;

public enum Tier implements IVariant<Tier> {
    STARTER(0xA7A7A7, "Starter"),
    BASIC(0xA3AB9F, "Basic"),
    HARDENED(0xBBA993, "Hardened"),
    BLAZING(0xE4B040, "Blazing"),
    NIOTIC(0x13EED2, "Niotic"),
    SPIRITED(0xAFE241, "Spirited"),
    NITRO(0xD7746C, "Nitro"),
    CREATIVE(0x8D29AD, "Creative");

    private final int color;
    private final String name;

    Tier(int color, String name) {
        this.color = color;
        this.name = name;
    }

    @Override
    public Tier[] getVariants() {
        return values();
    }

    public static Tier[] getNormalVariants() {
        return new Tier[] { STARTER, BASIC, HARDENED, BLAZING, NIOTIC, SPIRITED, NITRO };
    }

    public int getColor() {
        return color;
    }

    public String getLocaleName() {
        return name;
    }
}
