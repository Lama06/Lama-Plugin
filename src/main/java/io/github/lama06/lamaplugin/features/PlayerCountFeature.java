package io.github.lama06.lamaplugin.features;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import io.github.lama06.lamaplugin.LamaPlugin;
import io.github.lama06.lamaplugin.LamaPluginOptions;
import io.github.lama06.lamaplugin.Prefix;

public final class PlayerCountFeature implements Listener, CommandExecutor {
    private LamaPlugin plugin;
    private Map<InetAddress, Integer> maxPlayerCounts = new HashMap<>();

    public PlayerCountFeature(LamaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        if(!(plugin.options.load().incrementingPlayerCountEnabled)) {
            return;
        }

        InetAddress address = e.getAddress();

        if(!maxPlayerCounts.containsKey(address)) {
            maxPlayerCounts.put(address, Bukkit.getMaxPlayers());
        }

        int maxPlayerCount = maxPlayerCounts.get(address);
        e.setMaxPlayers(maxPlayerCount);
        maxPlayerCounts.put(address, maxPlayerCount + 1);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("spielercounter")) {
            LamaPluginOptions options = plugin.options.load();

            if(options.incrementingPlayerCountEnabled) {
                options.incrementingPlayerCountEnabled = false;
                sender.sendMessage(Prefix.MAIN + "Das Walross wird die Spielerzahl jetzt nicht mehr erhöhen. Walross ist traurig :(");
            } else {
                options.incrementingPlayerCountEnabled = true;
                sender.sendMessage(Prefix.MAIN + "Das Walross darf jetzt wieder die Spielerzahl erhöhen. Walross ist wieder glücklich :)");
            }

            plugin.options.save();
        }

        return true;
    }
}
