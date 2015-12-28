package org.stevenw.chat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.stevenw.chat.Placeholder;
import org.stevenw.chat.PlaceholderManager;

import java.util.List;

public class Placeholders implements CommandExecutor {
    private PlaceholderManager manager;

    public Placeholders(PlaceholderManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String tag, String[] strings) {
        List<Placeholder> placeholders = manager.getPlaceholders();
        for (Placeholder placeholder : placeholders) {
            if (commandSender instanceof Player) {
                commandSender.sendMessage(placeholder.getPlaceholderName() + ": " + placeholder.getValue((Player) commandSender));
            } else {
                commandSender.sendMessage(placeholder.getPlaceholderName());
            }
        }
        return true;
    }
}
