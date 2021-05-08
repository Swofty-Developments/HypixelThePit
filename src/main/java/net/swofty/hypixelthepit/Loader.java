package net.swofty.hypixelthepit;

import net.swofty.hypixelthepit.Commands.CommandLoader;
import net.swofty.hypixelthepit.Core.Holograms;
import net.swofty.hypixelthepit.Core.Placeholder;
import net.swofty.hypixelthepit.Core.Runnable;
import net.swofty.hypixelthepit.Events.PlayerEvents;
import net.swofty.hypixelthepit.GUIs.GUIsList.lobbyGUI;
import net.swofty.hypixelthepit.GUIs.GUIsList.statsGUI;
import net.swofty.hypixelthepit.Managers.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Loader extends JavaPlugin
{

    public static Plugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);

        Bukkit.getPluginManager().registerEvents(new lobbyGUI(), this);
        Bukkit.getPluginManager().registerEvents(new statsGUI(), this);

        DataManager.initialize();

        Holograms.spawnHolograms();

        new BukkitRunnable() {
            public void run() {

                Runnable.halfsecondRunnable();

            }

        }.runTaskTimer(this, 10, 10);

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new Placeholder(this).register();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CommandLoader.executeCommand(sender, label, args);
        return false;
    }

    public static Plugin getInstance() {
        return plugin;
    }

}
