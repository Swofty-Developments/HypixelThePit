package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Core.Hologram;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.Rank;

public class debugholograms
{

    public static void run(HypixelPlayer player, String[] args)
    {

        if (player.rankHigherOrSameThan(Rank.ADMIN)) {

            Hologram.deleteHolograms();
            Hologram.spawnHolograms();
            player.sendFormattedMessage("&c&lDEBUG! &fRemoved all holograms and respawned them");
        } else {
            player.sendFormattedMessage("&cYou need ADMIN or above to do this command!");
        }

    }

}
