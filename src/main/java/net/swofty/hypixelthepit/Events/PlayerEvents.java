package net.swofty.hypixelthepit.Events;

import net.swofty.hypixelthepit.Managers.DataManager;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelServer;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import net.swofty.hypixelthepit.Managers.Rank;
import net.swofty.hypixelthepit.Managers.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerEvents implements Listener
{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        HypixelPlayer player = new PlayerManager(event.getPlayer());

        DataManager.startLoginProcess(player.getBukkitPlayer());

        player.getBukkitPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());

        if (player.getRank().equals(Rank.ADMIN)) {
            player.sendFormattedMessage("&9[HELPER DEBUG] &fGeneration session token");
            player.sendFormattedMessage("&9[HELPER DEBUG] &fFinding server suitable for you");
            player.sendFormattedMessage("&9[HELPER DEBUG] &fSending to server");
        }

        player.sendFormattedMessage("&a&lSERVER FOUND! &7Sending to mega9A");
        player.sendFormattedMessage(" ");

        event.setJoinMessage("");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        HypixelServer server = new ServerManager(Bukkit.getServer());
        HypixelPlayer player = new PlayerManager(event.getPlayer());

        event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7&l[" + player.getLevel() + "] " + player.getDisplayName() + "&f: " + event.getMessage()));
    }

}
