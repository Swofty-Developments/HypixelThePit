package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import net.swofty.hypixelthepit.Managers.Rank;
import org.bukkit.Bukkit;

public class setgold
{

    public static void run(HypixelPlayer player, String[] args)
    {

        if (player.rankHigherOrSameThan(Rank.ADMIN)) {
            int wantedGold;
            switch (args.length) {
                case 0:
                    player.sendFormattedMessage("&cUsage: /setgold <gold> [player]");
                    break;

                case 1:
                    wantedGold = Integer.parseInt(args[0]);

                    player.setGold(wantedGold);
                    player.sendFormattedMessage("&c&lDEBUG! &fSet your own gold to " + player.getGoldColorized() + "g");
                    break;

                case 2:
                    wantedGold = Integer.parseInt(args[0]);

                    if (Bukkit.getPlayer(args[1]) == null) {
                        player.sendFormattedMessage("&cCould not find player specified");
                        return;
                    }

                    HypixelPlayer target = new PlayerManager(Bukkit.getPlayer(args[1]));

                    player.setGold(wantedGold);
                    player.sendFormattedMessage("&c&lDEBUG! &fSet &a" + target + "'s gold to " + target.getGoldColorized() + "g");
                    break;
            }

        } else {
            player.sendFormattedMessage("&cYou need ADMIN or above to do this command!");
        }

    }

}
