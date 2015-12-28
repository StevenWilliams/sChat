package org.stevenw.chat.variables.vault;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.stevenw.chat.Placeholder;

public class PlaceholderVaultSuffix implements Placeholder {
    private Chat chat;

    public PlaceholderVaultSuffix(JavaPlugin plugin) {
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
    }

    @Override
    public String getPlaceholderName() {
        return "SUFFIX";
    }

    @Override
    public String getValue(Player player) {
        return ChatColor.translateAlternateColorCodes('&', chat.getPlayerSuffix(player));
    }
}