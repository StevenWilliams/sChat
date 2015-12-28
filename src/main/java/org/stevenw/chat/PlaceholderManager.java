package org.stevenw.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.stevenw.chat.variables.PlaceholderString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderManager {
    private static final String variablePattern = "\\{(.*?)\\}";
    private List<Placeholder> placeholders = new ArrayList<>();
    private Pattern pattern;

    public PlaceholderManager() {
        pattern = Pattern.compile(variablePattern);
    }

    public Placeholder getPlaceholder(String name) {
        for (Placeholder placeholder : placeholders) {
            if (name.toUpperCase().equals(placeholder.getPlaceholderName().toUpperCase())) return placeholder;
        }
        return null;
    }

    public void addPlaceholder(Placeholder placeholder) {
        this.placeholders.add(placeholder);
    }

    private String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String replace(JavaPlugin plugin, Player player, String text, HashMap<String, String> map) {
        List<Placeholder> placeholders = findPlaceholders(text, map);
        for (Placeholder placeholder : placeholders) {
            text = text.replace("{" + placeholder.getPlaceholderName() + "}", placeholder.getValue(player));
        }
        if (findPlaceholders(text, map).size() > 0) {
            text = replace(plugin, player, text, map);
        }
        return text;
    }


    public List<Placeholder> findPlaceholders(String text, HashMap<String, String> map) {
        List<Placeholder> foundPlaceholders = new ArrayList<>();
        Matcher m = pattern.matcher(text);
        while (m.find()) {
            String placeholderName = m.group(1);
            if (map != null) {
                if (map.containsKey(placeholderName)) {
                    String value = map.get(placeholderName);
                    foundPlaceholders.add(new PlaceholderString(placeholderName, value));
                    continue;
                }
            }
            if (this.getPlaceholder(placeholderName) != null) {
                foundPlaceholders.add(this.getPlaceholder(placeholderName));
            }
        }
        return foundPlaceholders;
    }

    public List<Placeholder> getPlaceholders() {
        return this.placeholders;
    }

}
