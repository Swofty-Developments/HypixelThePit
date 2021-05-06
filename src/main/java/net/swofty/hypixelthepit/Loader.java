package net.swofty.hypixelthepit;

import net.swofty.hypixelthepit.Commands.CommandLoader;
import net.swofty.hypixelthepit.Events.PlayerEvents;
import net.swofty.hypixelthepit.Managers.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Loader extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);

        DataManager.initialize();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CommandLoader.executeCommand(sender, label, args);
        return false;
    }
}
