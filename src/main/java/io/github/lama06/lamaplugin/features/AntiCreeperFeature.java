package io.github.lama06.lamaplugin.features;

import io.github.lama06.lamaplugin.LamaPlugin;
import io.github.lama06.lamaplugin.LamaPluginOptions;
import io.github.lama06.lamaplugin.Prefix;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public final class AntiCreeperFeature implements Listener, CommandExecutor {
    private LamaPlugin plugin;

    public AntiCreeperFeature(LamaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void preventCreeperExplosion(EntityExplodeEvent e) {
        if(e.getEntityType() == EntityType.CREEPER && plugin.options.load().antiCreeperEnabled) {
            e.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("ichmagkeinecreeper")) {
            LamaPluginOptions options = plugin.options.load();

            if(options.antiCreeperEnabled) {
                options.antiCreeperEnabled = false;
                sender.sendMessage("<Creeper> Ich kann jetzt wieder explodieren und alles kaputt machen! MUHAHAHA");
            } else {
                options.antiCreeperEnabled = true;
                sender.sendMessage(Prefix.MAIN + "Die boesen, boesen Creeper dürfen jetzt nicht mehr explodieren");
            }

            plugin.options.save();
        }

        return true;
    }
}
