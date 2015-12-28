package org.stevenw.chat.variables;

import org.bukkit.entity.Player;
import org.stevenw.chat.Placeholder;

public class PlaceholderName implements Placeholder {
    @Override
    public String getPlaceholderName() {
        return "USERNAME";
    }

    @Override
    public String getValue(Player player) {
        return player.getName();
    }
}

