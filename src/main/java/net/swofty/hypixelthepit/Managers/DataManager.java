package net.swofty.hypixelthepit.Managers;

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
            playerData.set("level", newXP);

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
                playerData.createSection("rank");
                playerData.createSection("lastlogin");
                playerData.createSection("level");
                playerData.createSection("xp");

                playerData.set("rank", "default");
                playerData.set("lastlogin", System.currentTimeMillis());
                playerData.set("level", "1");
                playerData.set("xp", "0");

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
            requestedData = playerData.getString(dataType);
        } catch (Exception exception) {
            exception.printStackTrace();
            Bukkit.getLogger().info("Could not get " + player.getName() + " data");
        }

        return requestedData;
    }
}
