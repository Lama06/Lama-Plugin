package io.github.lama06.lamaplugin.vote;

import io.github.lama06.lamaplugin.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.*;

public final class Vote {
    private final Plugin plugin;
    private boolean running = false;
    private final String creator;
    private final String question;
    private final Set<String> choices;
    private final Map<Player, String> votes = new HashMap<>();
    private final int time;
    private final VoteEndedCallback endedCallback;

    /**
     * Erstellt eine neue Abstimmung
     *
     * @param plugin        das Plugin
     * @param creator       der Spieler, der die Abstimmung erstellt hat. Kann auch
     *                      null sein.
     * @param question      die Frage der Abstimmung
     * @param choices       die Antwortmöglichkeiten
     * @param time          die Zeit, nachder die Abstimmung beendet wird
     * @param endedCallback eine Funktion, die aufgerufen wird, nachdem die
     *                      Abstimmung beendet wurde
     */
    public Vote(Plugin plugin, String creator, String question, Set<String> choices, int time,
            VoteEndedCallback endedCallback) {
        this.plugin = plugin;
        this.creator = creator;
        this.question = question;
        this.choices = choices;
        this.time = time;
        this.endedCallback = endedCallback;
    }

    /**
     * Startet die Abstimmung
     */
    public void start() {
        running = true;
        Bukkit.getScheduler().runTaskLater(plugin, this::onEnded, time);

        Bukkit.broadcastMessage(Prefix.VOTE + "Neue Abstimmung: ");
        if (creator == null) {
            Bukkit.broadcastMessage(question);
        } else {
            Bukkit.broadcastMessage(String.format("<%s> %s", creator, question));
        }

        StringBuilder choicesBuilder = new StringBuilder();
        choicesBuilder.append("Es gibt folgende Antwortmöglichkeiten:");
        for (String choice : choices) {
            choicesBuilder.append(" ").append(choice);
        }
        Bukkit.broadcastMessage(Prefix.VOTE + choicesBuilder);
    }

    public boolean isChoice(String text) {
        for (String choice : choices) {
            if (choice.toLowerCase(Locale.ROOT).equals(text.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gibt ein Set aller Spieler zurück, die für eine Antwort abgestimmt haben
     *
     * @param choice die Antwortmöglichkeit
     * @return ein Set aller Spieler, die für diese Antwort abgestimmt haben
     */
    public Set<Player> getVotes(String choice) {
        Set<Player> players = new HashSet<>();
        for (Map.Entry<Player, String> vote : votes.entrySet()) {
            if (vote.getValue().toLowerCase(Locale.ROOT).equals(choice.toLowerCase(Locale.ROOT))) {
                players.add(vote.getKey());
            }
        }
        return players;
    }

    public int getAmountOfVotes(String choice) {
        return getVotes(choice).size();
    }

    public boolean isRunning() {
        return running;
    }

    public void onChatMessage(AsyncPlayerChatEvent event) {
        if (!running) {
            return;
        }

        Player sender = event.getPlayer();
        String msg = event.getMessage();

        if (isChoice(msg)) {
            votes.put(sender, msg);
            sender.sendMessage(Prefix.VOTE + String.format("Du hast für %s abgestimmt", msg));
        } else {
            sender.sendMessage(Prefix.VOTE + "Das ist keine Abstimmungsmöglichkeit");
        }
    }

    private void onEnded() {
        running = false;

        Bukkit.broadcastMessage(Prefix.VOTE + "Die Abstimmung ist beendet. Das Ergebnisse sind:");
        for (String choice : choices) {
            Bukkit.broadcastMessage(Prefix.VOTE + String.format("%s -> %s", choice, getAmountOfVotes(choice)));
        }

        if (endedCallback != null) {
            endedCallback.onVoteEnded(this);
        }
    }
}
