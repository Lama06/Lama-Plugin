package io.github.lama06.lamaplugin.features;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import io.github.lama06.lamaplugin.LamaPlugin;
import io.github.lama06.lamaplugin.Prefix;

public final class JacobFeature implements Listener, CommandExecutor {
    private static ItemStack buildingBlocks = new ItemStack(Material.ORANGE_WOOL, 64);

    private LamaPlugin plugin;
    private Set<Player> activatedFor = new HashSet<>();
    private Map<Player, ItemStack> savedItems = new HashMap<>();

    public JacobFeature(LamaPlugin plugin) {
        this.plugin = plugin;
    }

    private void activateFor(Player player) {
        activatedFor.add(player);
        
        PlayerInventory inventory = player.getInventory();
        savedItems.put(player, inventory.getItemInMainHand());
        inventory.setItemInMainHand(buildingBlocks);
    }

    private void deactivateFor(Player player) {
        activatedFor.remove(player);
        PlayerInventory inventory = player.getInventory();
        inventory.setItemInMainHand(savedItems.get(player));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Prefix.MAIN + "Nur Spieler können ");
            return true;
        }

        Player player = (Player) sender;

        if(command.getName().equals("jacob")) {
            if(activatedFor.contains(player)) {
                deactivateFor(player);
                player.sendMessage(Prefix.JACOB + "Das Jacob Feature ist jetzt ausgeschaltet");
            } else {
                activateFor(player);
                player.sendMessage(Prefix.JACOB + "Das Jacob Feature ist jetzt eingeschaltet");
            }
        }

        return true;
    }

    @EventHandler
    public void onPlayerSwitchesItemSlot(PlayerItemHeldEvent event) {
        if(activatedFor.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlacesBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(activatedFor.contains(player)) {
            Block block = event.getBlock();

            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                block.setType(Material.RED_WOOL);

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    block.setType(Material.BLACK_WOOL);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        block.setType(Material.AIR);
                    }, 60);
                }, 60);
            }, 60);
        }
    }
}