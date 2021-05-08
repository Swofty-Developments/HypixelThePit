package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Core.Holograms;
import net.swofty.hypixelthepit.GUIs.GUIsLoader;
import net.swofty.hypixelthepit.Managers.DataManager;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.Rank;
import org.bukkit.Bukkit;

public class simulatedeath
{

    public static void run(HypixelPlayer player, String[] args)
    {

        if (player.rankHigherOrSameThan(Rank.ADMIN)) {
            player.sendFormattedMessage("&aSimulating death");
            player.sendFormattedMessage("&c&lDEATH! &7by [&c&lRANDOM&7] &7Test" + " &e&lVIEW RECAP");

            player.setData("deaths", String.valueOf(Integer.parseInt(DataManager.getData(player.getBukkitPlayer(), "deaths")) + 1));

            player.setData("streak", "0");
            player.setData("infight", "false");

            player.getBukkitPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
        } else {
            player.sendFormattedMessage("&cYou need ADMIN or above to do this command!");
        }

    }

}