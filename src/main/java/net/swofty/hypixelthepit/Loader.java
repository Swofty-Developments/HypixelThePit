package net.swofty.hypixelthepit;

import net.swofty.hypixelthepit.Commands.CommandLoader;
import net.swofty.hypixelthepit.Core.Runnable;
import net.swofty.hypixelthepit.Core.Scoreboards;
import net.swofty.hypixelthepit.Events.PlayerEvents;
import net.swofty.hypixelthepit.Managers.DataManager;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Loader extends JavaPlugin implements CommandExecutor {

    public static Plugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);

        DataManager.initialize();

        new BukkitRunnable() {
            public void run() {

                Runnable.secondRunnable();

            }

        }.runTaskTimerAsynchronously(this, 20, 20);
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
