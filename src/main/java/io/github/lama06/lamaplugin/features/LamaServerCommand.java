package io.github.lama06.lamaplugin.features;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.lama06.lamaplugin.LamaPlugin;
import io.github.lama06.lamaplugin.Prefix;

public final class LamaServerCommand implements CommandExecutor {
    private LamaPlugin plugin;

    public LamaServerCommand(LamaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("lamaserver")) {
            sender.sendMessage(Prefix.MAIN + "Dieser Server benutzt das Lama Plugin " + plugin.getDescription().getVersion());
        }
        return true;
    }
}
