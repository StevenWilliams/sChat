package org.stevenw.chat.variables;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.stevenw.chat.Placeholder;

public class PlaceholderDisplayName implements Placeholder {

    @Override
    public String getPlaceholderName() {
        return "DISPLAYNAME";
    }

    @Override
    public String getValue(Player player) {
        return ChatColor.translateAlternateColorCodes('&', player.getDisplayName());
    }
}
