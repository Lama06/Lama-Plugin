package io.github.lama06.lamaplugin.command;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class CommandManager implements CommandExecutor {
    private Set<CommandExecutor> commands = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for(CommandExecutor cmd : commands) {
            boolean result = cmd.onCommand(sender, command, label, args);

            if(!result) {
                return result;
            }
        }

        return true;
    }

    public void register(CommandExecutor command) {
        commands.add(command);
    }
}
