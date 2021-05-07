package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import net.swofty.hypixelthepit.Managers.Rank;
import org.bukkit.Bukkit;

public class setxp
{

    public static void run(HypixelPlayer player, String[] args)
    {

        if (player.rankHigherOrSameThan(Rank.ADMIN)) {
            int wantedXP;
            switch (args.length) {
                case 0:
                    player.sendFormattedMessage("&cUsage: /setxp <xp> [player]");
                    break;

                case 1:
                    wantedXP = Integer.parseInt(args[0]);

                    player.setXP(wantedXP);
                    player.sendFormattedMessage("&c&lDEBUG! &fSet your own xp to &b" + player.getXP());
                    break;

                case 2:
                    wantedXP = Integer.parseInt(args[0]);

                    if (Bukkit.getPlayer(args[1]) == null) {
                        player.sendFormattedMessage("&cCould not find player specified");
                        return;
                    }

                    HypixelPlayer target = new PlayerManager(Bukkit.getPlayer(args[1]));

                    player.setXP(wantedXP);
                    player.sendFormattedMessage("&c&lDEBUG! &fSet &a" + target + "'s gold to &b" + target.getXP());
                    break;
            }

        } else {
            player.sendFormattedMessage("&cYou need ADMIN or above to do this command!");
        }

    }

}
