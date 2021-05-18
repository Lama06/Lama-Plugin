package io.github.lama06.lamaplugin;

import org.bukkit.ChatColor;

public final class Prefix {
    public static final String MAIN = createPrefix("LamaPlugin", ChatColor.AQUA);
    public static final String VOTE = createPrefix("Abstimmung", ChatColor.BLUE);
    public static final String NEWS = createPrefix("Zeitung", ChatColor.GREEN);
    public static final String JACOB = createPrefix("Jacob", ChatColor.DARK_BLUE);

    private static String createPrefix(String name, ChatColor color) {
        return ChatColor.GRAY + "[" + ChatColor.RESET + color + name + ChatColor.RESET + ChatColor.GRAY + "]" + ChatColor.RESET + " ";
    }
}
