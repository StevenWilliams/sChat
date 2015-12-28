package org.stevenw.chat;

import org.bukkit.entity.Player;

public interface Placeholder {
    String getPlaceholderName();

    String getValue(Player player);
}
