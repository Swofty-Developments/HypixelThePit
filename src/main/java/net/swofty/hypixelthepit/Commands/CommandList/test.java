package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.Rank;

public class test
{

    public static void run(HypixelPlayer player, String[] args)
    {

        if (player.rankHigherOrSameThan(Rank.ADMIN)) {
            player.sendFormattedMessage("&a" + player.getRank());
        } else {
            player.sendFormattedMessage("&cYou need ADMIN or above to do this command!");
        }

    }

}
