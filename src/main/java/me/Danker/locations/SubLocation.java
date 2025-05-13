package me.Danker.locations;

import me.Danker.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

/*
public enum SubLocation {
    NONE(""),
    MYSTIC_MARSH("Mystic Marsh"),
    BLAZING_VOLCANO("Blazing Volcano"),
    BURNING_DESERT("Burning Desert"),
    DOJO("Dojo");

    final String text;

    SubLocation(String text) {
        this.text = text;
    }

    public static SubLocation fromSidebar(String text) {

    for (SubLocation sublocation : SubLocation.values()) {
        if (sublocation.text.equalsIgnoreCase(text)) return sublocation;
    }

    if (Utils.isInDojo()) {
        return DOJO;
    } else if (!Utils.isInDojo()) {
        return NONE;
    }

    return NONE;
    }
}
*/

public enum SubLocation {
    NONE(""),
    MYSTIC_MARSH("Mystic Marsh"),
    BLAZING_VOLCANO("Blazing Volcano"),
    BURNING_DESERT("Burning Desert"),
    DOJO("Dojo");

    final String text;

    SubLocation(String text) {
        this.text = text;
    }

    public static SubLocation fromPosition() {
        if (Utils.sublocationUtil() == "Dojo") return DOJO;

        return NONE;
    }
}

