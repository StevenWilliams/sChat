package org.stevenw.chat.variables;

import org.bukkit.entity.Player;
import org.stevenw.chat.Placeholder;

public class PlaceholderString implements Placeholder {
    private String name;
    private String value;

    public PlaceholderString(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getPlaceholderName() {
        return name;
    }

    @Override
    public String getValue(Player player) {
        return value;
    }
}
