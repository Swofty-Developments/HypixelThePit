package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Core.Holograms;
import net.swofty.hypixelthepit.Managers.DataManager;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import net.swofty.hypixelthepit.Managers.Rank;
import org.bukkit.Bukkit;

public class simulatekill
{

    public static void run(HypixelPlayer player, String[] args)
    {

        if (player.rankHigherOrSameThan(Rank.ADMIN)) {
            player.sendFormattedMessage("&aSimulating kill");

            player.sendFormattedMessage("&a&lKILL! &7on &7[&c&lRANDOM&7] &7Test" + " &b+10XP &6+10.00g");
            player.setGold(player.getGold() + 10);
            player.setXP(player.getXP() - 10);

            player.setData("kills", String.valueOf(Integer.parseInt(DataManager.getData(player.getBukkitPlayer(), "kills")) + 1));

            int streak = Integer.parseInt(DataManager.getData(player.getBukkitPlayer(), "streak"));
            player.setData("streak", String.valueOf(streak + 1));

            player.setData("infight", "false");
        } else {
            player.sendFormattedMessage("&cYou need ADMIN or above to do this command!");
        }

    }

}
