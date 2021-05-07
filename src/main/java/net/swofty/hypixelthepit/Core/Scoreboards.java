package net.swofty.hypixelthepit.Core;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Scoreboards
{

    public static void giveScoreboard(HypixelPlayer player) {

        BPlayerBoard board = Netherboard.instance().createBoard(player.getBukkitPlayer(), Utils.formatMessage("&e&lTHE HYPIXEL PIT"));

        board.set(Utils.formatMessage("&7" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " &8M9A"), 10);
        board.set(Utils.formatMessage("&b "), 9);
        board.set(Utils.formatMessage("&fLevel: &7[" + player.getLevelColorized() + "&7]"), 8);
        board.set(Utils.formatMessage("&fNeeded XP: &b" + player.getXP()), 7);
        board.set(Utils.formatMessage("&e "), 6);
        board.set(Utils.formatMessage("&fGold: " + player.getGoldColorized() + "g"), 5);
        board.set(Utils.formatMessage("&f "), 4);
        board.set(Utils.formatMessage("&fStatus: &aIdling"), 3);
        board.set(Utils.formatMessage("&a "), 2);
        board.set(Utils.formatMessage("&ewww.hypixel.net"), 1);

    }
}
