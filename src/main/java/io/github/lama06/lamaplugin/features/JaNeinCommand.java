package io.github.lama06.lamaplugin.features;

import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.lama06.lamaplugin.LamaPlugin;
import io.github.lama06.lamaplugin.Prefix;
import io.github.lama06.lamaplugin.Util;
import io.github.lama06.lamaplugin.vote.Vote;

public final class JaNeinCommand implements CommandExecutor {
    private LamaPlugin plugin;

    public JaNeinCommand(LamaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("janein")) {
            if(plugin.vote.isVoteRunning()) {
                sender.sendMessage(Prefix.VOTE + "Es laeuft aktuell bereits eine Abstimmung auf dem Server");
            } else {
                plugin.vote.startVote(new Vote(plugin, sender.getName(), Util.argsToString(args), Set.of("ja", "nein"), 600, null));
            }
        }

        return true;
    }
}
