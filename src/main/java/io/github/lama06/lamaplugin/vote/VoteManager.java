package io.github.lama06.lamaplugin.vote;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class VoteManager implements Listener {
    private Vote currentVote = null;

    public boolean isVoteRunning() {
        return currentVote != null && currentVote.isRunning();
    }

    public boolean startVote(Vote vote) {
        if (!isVoteRunning()) {
            currentVote = vote;
            currentVote.start();
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {
        if (currentVote != null) {
            currentVote.onChatMessage(event);
        }
    }
}
