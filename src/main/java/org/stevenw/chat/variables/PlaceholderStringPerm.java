package org.stevenw.chat.variables;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.stevenw.chat.Placeholder;

import java.util.Set;

public class PlaceholderStringPerm implements Placeholder {
    private ConfigurationSection section;

    public PlaceholderStringPerm(ConfigurationSection section) {
        this.section = section;
    }

    @Override
    public String getPlaceholderName() {
        return section.getName();
    }

    @Override
    public String getValue(Player player) {
        Set<String> permissions = section.getKeys(false);
        for (String permission : permissions) {
            if (player.hasPermission(permission.replace("-", "."))) {
                return ChatColor.translateAlternateColorCodes('&', section.getString(permission));
            }
        }
        return ChatColor.translateAlternateColorCodes('&', section.getString("default", ""));
    }
}
