package net.swofty.hypixelthepit.Core;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.NameTag;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Runnable
{

    static int halfSecond = 0;

    public static void halfsecondRunnable() {

        halfSecond++;

        for (Player players : Bukkit.getOnlinePlayers()) {
            Scoreboards.giveScoreboard(new PlayerManager(players));

            HypixelPlayer player = new PlayerManager(players);

            players.setLevel(player.getLevel());

            player.getBukkitPlayer().setPlayerListName(Utils.formatMessage("&7[" + player.getLevelColorized() + "&7] " + player.getDisplayName()));

            if (player.getXP() <= 0) {

                player.setXP(200);
                String oldFormat = player.getLevelColorized();
                player.setLevel(player.getLevel() + 1);

                player.sendFormattedMessage("&b&lPIT LEVEL UP! &7[" + oldFormat + "&7] &l> &7[" + player.getLevelColorized() + "&7]");
                player.getBukkitPlayer().sendTitle("&b&lLEVEL UP!", "&7[" + oldFormat + "&7] &l> &7[" + player.getLevelColorized() + "&7]");
            }
        }
    }

}
