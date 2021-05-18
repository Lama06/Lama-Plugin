package io.github.lama06.lamaplugin;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.lama06.lamaplugin.command.CommandManager;
import io.github.lama06.lamaplugin.features.AntiCreeperFeature;
import io.github.lama06.lamaplugin.features.JaNeinCommand;
import io.github.lama06.lamaplugin.features.PlayerCountFeature;
import io.github.lama06.lamaplugin.features.RandomMotdFeature;
import io.github.lama06.lamaplugin.vote.VoteManager;

public final class LamaPlugin extends JavaPlugin {
    public Logger logger = getLogger();
    public CommandManager commands = new CommandManager();
    public VoteManager vote = new VoteManager();
    private File optionsFile;
    public OptionsFile<LamaPluginOptions> options;

    public AntiCreeperFeature antiCreeperFeature = new AntiCreeperFeature(this);
    public JaNeinCommand jaNeinCommand = new JaNeinCommand(this);
    public PlayerCountFeature playerCountFeature = new PlayerCountFeature(this);
    public RandomMotdFeature randomMotdFeature = new RandomMotdFeature(this);

    public void registerFeatures() {
        commands.register(antiCreeperFeature);
        commands.register(jaNeinCommand);
        commands.register(playerCountFeature);
        commands.register(randomMotdFeature);

        PluginManager plugins = Bukkit.getPluginManager();

        plugins.registerEvents(vote, this);

        plugins.registerEvents(antiCreeperFeature, this);
        plugins.registerEvents(playerCountFeature, this);
        plugins.registerEvents(randomMotdFeature, this);
    }

    @Override
    public void onEnable() {
        logger.info("Das Lama Plugin wurde aktiviert");

        if(!getDataFolder().exists()) {
            try {
                getDataFolder().mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        optionsFile = new File(getDataFolder().getAbsolutePath() + File.separator + "options.json");
        options = new OptionsFile<>(LamaPluginOptions.class, optionsFile);
        
        options.load();
        options.save();

        registerFeatures();
    }

    @Override
    public void onDisable() {
        logger.info("Das Lama Plugin wurde deaktiviert");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commands.onCommand(sender, command, label, args);
    }
}
