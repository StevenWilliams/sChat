package org.stevenw.chat.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.stevenw.chat.sChat;

import java.util.HashMap;

public class ChatListener implements Listener {
    private sChat plugin;

    public ChatListener(sChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void format(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (player.hasPermission("schat.colors")) {
            message = ChatColor.translateAlternateColorCodes('&', message);
        } else {
            message = ChatColor.stripColor(message).replace("%", "%%");
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("MESSAGE", message);

        message = plugin.getManager().replace(plugin, player, "{FORMAT}", map);
        event.setFormat(message);
    }
}
