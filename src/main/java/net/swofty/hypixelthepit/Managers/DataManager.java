package net.swofty.hypixelthepit.Managers;

import net.swofty.hypixelthepit.Items.SpawnItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class DataManager {

    public static void initialize() {
        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("HypixelThePit").getDataFolder(), File.separator + "PlayerDatabase");

        userdata.mkdirs();
    }

    public static void editData(Player player, String dataType, String newValue) {
        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("HypixelThePit").getDataFolder(), File.separator + "PlayerDatabase");
        File f = new File(userdata, File.separator + player.getPlayer().getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        try {
            playerData.set(dataType, newValue);

            playerData.save(f);
        } catch (Exception exception) {
            exception.printStackTrace();
            Bukkit.getLogger().info("Could not edit " + player.getName() + " data");
        }
    }

    public static void editLevel(Player player, int newLevel) {
        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("HypixelThePit").getDataFolder(), File.separator + "PlayerDatabase");
        File f = new File(userdata, File.separator + player.getPlayer().getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        try {
            playerData.set("level", newLevel);

            playerData.save(f);
        } catch (Exception exception) {
            exception.printStackTrace();
            Bukkit.getLogger().info("Could not edit " + player.getName() + " data");
        }
    }

    public static void editXP(Player player, int newXP) {
        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("HypixelThePit").getDataFolder(), File.separator + "PlayerDatabase");
        File f = new File(userdata, File.separator + player.getPlayer().getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        try {
            playerData.set("xp", newXP);

            playerData.save(f);
        } catch (Exception exception) {
            exception.printStackTrace();
            Bukkit.getLogger().info("Could not edit " + player.getName() + " data");
        }
    }

    public static void editGold(Player player, int newGold) {
        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("HypixelThePit").getDataFolder(), File.separator + "PlayerDatabase");
        File f = new File(userdata, File.separator + player.getPlayer().getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        try {
            playerData.set("gold", newGold);

            playerData.save(f);
        } catch (Exception exception) {
            exception.printStackTrace();
            Bukkit.getLogger().info("Could not edit " + player.getName() + " data");
        }
    }

    public static void startLoginProcess(Player player) throws IOException {
        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("HypixelThePit").getDataFolder(), File.separator + "PlayerDatabase");
        File f = new File(userdata, File.separator + player.getPlayer().getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        if (!f.exists()) {
            try {
                SpawnItems.giveItems(new PlayerManager(player));
                playerData.createSection("rank");
                playerData.createSection("lastlogin");
                playerData.createSection("level");
                playerData.createSection("xp");
                playerData.createSection("gold");

                playerData.createSection("kills");
                playerData.createSection("sword-hits");
                playerData.createSection("arrow-shots");
                playerData.createSection("damage-dealt");
                playerData.createSection("streak");
                playerData.createSection("infight");

                playerData.createSection("deaths");
                playerData.createSection("damage-taken");

                playerData.set("rank", "default");
                playerData.set("lastlogin", System.currentTimeMillis());
                playerData.set("level", "1");
                playerData.set("gold", "0");
                playerData.set("xp", "200");
                playerData.set("infight", "false");

                playerData.set("kills", "1");
                playerData.set("sword-hits", "0");
                playerData.set("arrow-shots", "1");
                playerData.set("damage-dealt", "1");
                playerData.set("streak", "0");

                playerData.set("deaths", "1");
                playerData.set("damage-taken", "1");

                playerData.save(f);
            } catch (Exception exception) {
                exception.printStackTrace();
                Bukkit.getLogger().info("Could not make " + player.getName() + " data");
            }
        } else {
            playerData.set("lastlogin", System.currentTimeMillis());

            playerData.save(f);
        }
    }

    public static String getData(Player player, String dataType) {
        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("HypixelThePit").getDataFolder(), File.separator + "PlayerDatabase");
        File f = new File(userdata, File.separator + player.getPlayer().getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        String requestedData = "err";

        try {
            requestedData = playerData.getString(String.valueOf(dataType));
        } catch (Exception exception) {
            exception.printStackTrace();
            Bukkit.getLogger().info("Could not get " + player.getName() + " data");
        }

        return requestedData;
    }
}
