package net.swofty.hypixelthepit.Events;

import net.swofty.hypixelthepit.Core.Scoreboards;
import net.swofty.hypixelthepit.Core.Utils;
import net.swofty.hypixelthepit.Managers.DataManager;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelServer;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.NameTag;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import net.swofty.hypixelthepit.Managers.Rank;
import net.swofty.hypixelthepit.Managers.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.EnderChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

        event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[" + player.getLevelColorized() + "&7] " + player.getDisplayName() + "&f: " + event.getMessage()));
    }

    @EventHandler
    public void onPlayerHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(InventoryOpenEvent event) {
        HypixelPlayer player = new PlayerManager(Bukkit.getPlayer(event.getPlayer().getName()));

        if (event.getInventory().equals(player.getBukkitPlayer().getEnderChest()) && player.getLevel() < 15) {
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

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event) {
        HypixelPlayer player = new PlayerManager(Bukkit.getPlayer(event.getPlayer().getName()));

        if (!player.getRank().equals(Rank.ADMIN)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        int streak = Integer.parseInt(DataManager.getData(event.getPlayer(), "streak"));
        new PlayerManager(event.getPlayer()).setData("streak", String.valueOf(streak + 1));

        event.setQuitMessage("");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamageElse(EntityDamageEvent event) {
        if (!(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                event.setCancelled(true);
            }
            return;
        }

        HypixelPlayer player = new PlayerManager(Bukkit.getPlayer(event.getEntity().getName()));
        HypixelPlayer aggresor = new PlayerManager(Bukkit.getPlayer(event.getDamager().getName()));

        if (player.getBukkitPlayer().getLocation().getBlockY() > 100) {
            event.setCancelled(true);
        } else {

            player.setData("infight", "true");
            aggresor.setData("infight", "true");

            double damageTaken = Double.valueOf(DataManager.getData(player.getBukkitPlayer(), "damage-taken"));
            player.setData("damage-taken", String.valueOf(damageTaken + event.getDamage()));

            double damageDealt = Double.valueOf(DataManager.getData(aggresor.getBukkitPlayer(), "damage-dealt"));
            aggresor.setData("damage-dealt", String.valueOf(damageDealt + event.getDamage()));

            switch (event.getCause()) {

                case PROJECTILE:
                    aggresor.setData("arrow-shots", String.valueOf(Long.parseLong(DataManager.getData(player.getBukkitPlayer(), "arrow-shots")) + 1));
                    break;

                case ENTITY_ATTACK:
                case ENTITY_SWEEP_ATTACK:
                    aggresor.setData("sword-hits", String.valueOf(Long.parseLong(DataManager.getData(player.getBukkitPlayer(), "melee-damage")) + 1));
                    break;

            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        HypixelPlayer player = new PlayerManager(Bukkit.getPlayer(event.getEntity().getName()));
        HypixelPlayer aggresor;

        try {
            aggresor = new PlayerManager(Bukkit.getPlayer(player.getBukkitPlayer().getKiller().getName()));

            player.getBukkitPlayer().spigot().respawn();

            event.setDeathMessage("");
            player.sendFormattedMessage("&c&lDEATH! &7by [" + aggresor.getLevelColorized() + "&7] " + aggresor.getDisplayName() + " &e&lVIEW RECAP");
            aggresor.sendFormattedMessage("&a&lKILL! &7on [" + player.getLevelColorized() + "&7] " + player.getDisplayName() + " &b+10XP &6+10.00g");
            aggresor.setGold(aggresor.getGold() + 10);
            aggresor.setXP(aggresor.getXP() - 10);

            player.setData("deaths", String.valueOf(Integer.parseInt(DataManager.getData(player.getBukkitPlayer(), "deaths")) + 1));
            aggresor.setData("kills", String.valueOf(Integer.parseInt(DataManager.getData(aggresor.getBukkitPlayer(), "kills")) + 1));

            int streak = Integer.parseInt(DataManager.getData(aggresor.getBukkitPlayer(), "streak"));
            aggresor.setData("streak", String.valueOf(streak + 1));

            player.setData("streak", "0");
            player.setData("infight", "false");
            aggresor.setData("infight", "false");

            event.setKeepInventory(true);
            event.setKeepLevel(true);

        } catch (Exception e) {
            player.sendFormattedMessage("&cYou died in an unverifiable way, please contact developers.");
            player.getBukkitPlayer().spigot().respawn();
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

}
