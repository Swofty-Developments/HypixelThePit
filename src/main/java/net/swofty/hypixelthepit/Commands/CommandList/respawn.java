package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Managers.DataManager;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.Rank;
import org.bukkit.Bukkit;

public class respawn
{

    public static void run(HypixelPlayer player, String[] args)
    {

        if (player.getBukkitPlayer().getLocation().getBlockY() > 100) {
            player.sendFormattedMessage("&cYou cannot /respawn here!");
            return;
        }

        player.getBukkitPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
        DataManager.editData(player.getBukkitPlayer(), "streak", "0");
        DataManager.editData(player.getBukkitPlayer(), "infight", "false");

    }

}
