package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import net.swofty.hypixelthepit.Managers.Rank;
import org.bukkit.Bukkit;

public class setlevel
{

    public static void run(HypixelPlayer player, String[] args)
    {

        if (player.rankHigherOrSameThan(Rank.ADMIN)) {
            int wantedLevel;
            switch (args.length) {
                case 0:
                    player.sendFormattedMessage("&cUsage: /setlevel <level> [player]");
                    break;

                case 1:
                    wantedLevel = Integer.parseInt(args[0]);

                    player.setLevel(wantedLevel);
                    player.sendFormattedMessage("&c&lDEBUG! &fSet your own level to " + player.getLevelColorized());
                    break;

                case 2:
                    wantedLevel = Integer.parseInt(args[0]);

                    if (Bukkit.getPlayer(args[1]) == null) {
                        player.sendFormattedMessage("&cCould not find player specified");
                        return;
                    }

                    HypixelPlayer target = new PlayerManager(Bukkit.getPlayer(args[1]));

                    player.setLevel(wantedLevel);
                    player.sendFormattedMessage("&c&lDEBUG! &fSet &a" + target + "'s level to " + target.getLevelColorized());
                    break;
            }

        } else {
            player.sendFormattedMessage("&cYou need ADMIN or above to do this command!");
        }

    }

}
