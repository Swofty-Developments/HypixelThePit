package net.swofty.hypixelthepit.Events;

import net.swofty.hypixelthepit.Core.Scoreboards;
import net.swofty.hypixelthepit.Managers.DataManager;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelServer;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import net.swofty.hypixelthepit.Managers.Rank;
import net.swofty.hypixelthepit.Managers.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.EnderChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerEvents implements Listener
{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        HypixelPlayer player = new PlayerManager(event.getPlayer());

        DataManager.startLoginProcess(player.getBukkitPlayer());

        Scoreboards.giveScoreboard(player);

        player.getBukkitPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());

        if (player.getRank().equals(Rank.ADMIN)) {
            player.sendFormattedMessage("&9[HELPER DEBUG] &fGeneration session token");
            player.sendFormattedMessage("&9[HELPER DEBUG] &fFinding server suitable for you");
            player.sendFormattedMessage("&9[HELPER DEBUG] &fSending to server");
        }

        player.sendFormattedMessage("&a&lSERVER FOUND! &7Sending to mega9A");
        player.sendFormattedMessage(" ");
        player.sendFormattedMessage("&e&lPIT! &fLatest update: &ev1.0.3 &aNo-Rage Patch &7[&e&lCLICK&7]");

        event.setJoinMessage("");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        HypixelServer server = new ServerManager(Bukkit.getServer());
        HypixelPlayer player = new PlayerManager(event.getPlayer());

        event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7&l[" + player.getLevelColorized() + "&7] " + player.getDisplayName() + "&f: " + event.getMessage()));
    }

    @EventHandler
    public void onPlayerHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(InventoryOpenEvent event) {
        HypixelPlayer player = new PlayerManager(Bukkit.getPlayer(event.getPlayer().getName()));

        if (event.getInventory().equals(player.getBukkitPlayer().getEnderChest()) && player.getLevel() < 16) {
            player.sendFormattedMessage("&cYou need level 15 to use the ender chest!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory().getTitle() == "Gamemode GUI") {
            Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
            String name = event.getCurrentItem().getItemMeta().getDisplayName();

            switch (name) {
                case "Creative Mode!":
                    player.setGameMode(GameMode.CREATIVE);
                    break;

                case "Survival Mode!":
                    player.setGameMode(GameMode.SURVIVAL);
                    break;

                case "Spectator Mode!":
                    player.setGameMode(GameMode.SPECTATOR);
                    break;
            }

            event.setCancelled(true);
        }
        return;
    }

}
