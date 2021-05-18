package io.github.lama06.lamaplugin.features;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import io.github.lama06.lamaplugin.LamaPlugin;
import io.github.lama06.lamaplugin.LamaPluginOptions;
import io.github.lama06.lamaplugin.Prefix;

public final class RandomMotdFeature implements Listener, CommandExecutor {
    private LamaPlugin plugin;
    private static final List<String> motds = new ArrayList<>();
    private static final Random random = new Random();

    public RandomMotdFeature(LamaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        if(!(plugin.options.load().randomMotdEnabled)) {
            return;
        }

        e.setMotd(randomMotd());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("motd")) {
            LamaPluginOptions options = plugin.options.load();

            if(options.randomMotdEnabled) {
                options.randomMotdEnabled = false;
                sender.sendMessage(Prefix.MAIN + "Es gibt jetzt keinen zufälligen Text in der Server Info mehr");
            } else {
                options.randomMotdEnabled = true;
                sender.sendMessage(Prefix.MAIN + "Es gibt jetzt wieder einen zufälligen Text in der Server Info");
            }

            plugin.options.save();
        }

        return true;
    }

    private static void registerMotd(String motd) {
        motds.add(motd);
    }

    private static String randomMotd() {
        return motds.get(random.nextInt(motds.size()));
    }

    static {
        registerMotd("Und da geht es hinab");
        registerMotd("WeberGMBH");
        registerMotd("Lady Magret Hunt lehnt ihr Geldgeschenk ab");
        registerMotd("Alles falsch!!!");
        registerMotd("leitstellenspiel.agb.suport@gmail.com");
        registerMotd("Weird Flex");
        registerMotd("SchonerGMBH");
        registerMotd("Linienschiff Dome hat sich festgefahren");
        registerMotd("Dumm und Daemlich");
        registerMotd("Mission Internet Closed");
        registerMotd("Mission Internet Opened");
        registerMotd("MIC");
        registerMotd("MIO");
        registerMotd("Das Konzentrat muss konzentriert werden");
        registerMotd("H.W.");
        registerMotd("HEXE.png");
        registerMotd("Die Figur, die hat sich bewegt!");
        registerMotd("Das hab ich mir doch nicht eingebildet");
        registerMotd("Lama Server");
        registerMotd("Benutzt die Lama Mod!");
        registerMotd("Ich kann es mir leisten");
        registerMotd("Pinging...");
        registerMotd(ChatColor.RED + "Couldnt resolve Hostname");
        registerMotd("Und da war der Roll Roll in 2 Teile!");
        registerMotd("Der Drucker hat kein Papier");
        registerMotd("Bill Gates will Schnitzel essen");
        registerMotd("Multiaccounts");
        registerMotd("Benutzt das WPack!");
        registerMotd("Walross");
        registerMotd("Kanonenboot Walross wurde zerstoert");
        registerMotd("Oink Oink");
        registerMotd("Und da war der Oinky in 2 Teile");
        registerMotd("Frau LP");
        registerMotd("Da haett ich ja noch eine rauchen koennen");
        registerMotd("Die Brueder");
        registerMotd("Besser wie alles");
        registerMotd("Sie sie koennen mit vertrauen");
        registerMotd("W-Auswanderer-LOL");
        registerMotd("W-Schoner-GMBH");
        registerMotd("Teetasse");
        registerMotd("Oesterreichische Sachatorten");
        registerMotd("MLF");
        registerMotd("MLT");
        registerMotd("In der Ruhe liegt die Kraft");
        registerMotd("Herr Steinhoff ist ein Ehrenmann");
        registerMotd("Nemo nesciunt virem Steinhoffem optimum esse.");
        registerMotd("Stellt Fragen");
        registerMotd("Windows 7 Home PREMIUM");
        registerMotd("Gruß vom Brocker");
        registerMotd("Ich bin ein Huhn. Ich hab ein Ei gelegt!");
        registerMotd("Kunst ist sinnlos");
        registerMotd("Die Flagge mus standesgemaes verbrannt werdem");
        registerMotd("HEXEVERFOLGUNG");
        registerMotd("Leitstellenspiel Pro Tool 3010");
        registerMotd("Wehner GMBH");
        registerMotd("Verneigt euch vor dem Chef!");
        registerMotd("Andreas ist ein Ehrenamnn");
        registerMotd("Du bist mein kleiner Diamant");
        registerMotd("Alaaaaarm!!!");
    }
}
