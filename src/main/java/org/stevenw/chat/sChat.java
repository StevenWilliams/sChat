package org.stevenw.chat;

import org.bukkit.plugin.java.JavaPlugin;
import org.stevenw.chat.commands.Placeholders;
import org.stevenw.chat.listeners.ChatListener;
import org.stevenw.chat.variables.PlaceholderDisplayName;
import org.stevenw.chat.variables.PlaceholderName;
import org.stevenw.chat.variables.PlaceholderStringPerm;
import org.stevenw.chat.variables.vault.PlaceholderVaultPrefix;
import org.stevenw.chat.variables.vault.PlaceholderVaultSuffix;

import java.util.Set;

public class sChat extends JavaPlugin {
    private PlaceholderManager manager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        manager = new PlaceholderManager();
        loadPlaceholders();
        getCommand("placeholders").setExecutor(new Placeholders(manager));
        this.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    public void loadPlaceholders() {
        manager.addPlaceholder(new PlaceholderName());
        manager.addPlaceholder(new PlaceholderDisplayName());
        if (this.getServer().getPluginManager().getPlugin("Vault") != null) {
            manager.addPlaceholder(new PlaceholderVaultPrefix(this));
            manager.addPlaceholder(new PlaceholderVaultSuffix(this));
        }
        Set<String> variables = this.getConfig().getConfigurationSection("variables").getKeys(false);
        for (String variable : variables) {
            PlaceholderStringPerm placeholder = new PlaceholderStringPerm(this.getConfig().getConfigurationSection("variables." + variable));
            manager.addPlaceholder(placeholder);
        }
    }

    public PlaceholderManager getManager() {
        return manager;
    }
}
