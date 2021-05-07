package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.Rank;

import java.util.Random;

public class randomleveltest
{

    public static void run(HypixelPlayer player, String[] args) {

        if (player.rankHigherOrSameThan(Rank.ADMIN)) {

            Random randomGenerator = new Random();
            int randomLevel = randomGenerator.nextInt(50) + 1;

            player.setLevel(randomLevel);
            player.sendFormattedMessage("&c&lDEBUG! &fRandomized your display statistics!");

        } else {
            player.sendFormattedMessage("&cYou need ADMIN or above to do this command!");
        }

    }

}
