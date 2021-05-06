package net.swofty.hypixelthepit.Managers;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import sun.jvm.hotspot.utilities.IntervalNode;

public class PlayerManager implements HypixelPlayer {

    protected Player player;

    public PlayerManager(Player thisPlayer) {
        player = thisPlayer;
    }

    public Player getBukkitPlayer() {
        return player;
    }

    @Override
    public void sendFormattedMessage(String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public String getRank() {
        return DataManager.getData(player, "rank");
    }

    @Override
    public int getLevel() {
        return Integer.parseInt(DataManager.getData(player, "level"));
    }

    @Override
    public int getXP() {
        return Integer.parseInt(DataManager.getData(player, "rank"));
    }

    @Override
    public String getDisplayName() {
        String playerRank = DataManager.getData(player, "rank");

        switch (playerRank) {
            case "default":
                return "&7" + player.getName();
            case "vip+":
                return "&a[VIP+] " + player.getName();
            case "mvp++":
                return "&6[MVP++] " + player.getName();
            case "helper":
                return "&9[HELPER] " + player.getName();
            case "mod":
                return "&2[MOD] " + player.getName();
            case "admin":
                return "&c[ADMIN] " + player.getName();
        }

        return "&7" + player.getName();
    }

    @Override
    public void setLevel(int newLevel) {
        DataManager.editLevel(player, newLevel);
    }

    @Override
    public void setXP(int newXP) {
        DataManager.editXP(player, newXP);
    }

    @Override
    public Boolean rankHigherOrSameThan(String rank) {
        String playerRank = DataManager.getData(player, "rank");

        int playerRankInt = 0;
        int rankInt = 0;

        switch (rank.toString()) {
            case "default":
                rankInt = 0;
                break;
            case "vip+":
                rankInt = 1;
                break;
            case "mvp++":
                rankInt = 2;
                break;
            case "helper":
                rankInt = 3;
                break;
            case "mod":
                rankInt = 4;
                break;
            case "admin":
                rankInt = 5;
                break;
        }

        switch (playerRank) {
            case "default":
                playerRankInt = 0;
                break;
            case "vip+":
                playerRankInt = 1;
                break;
            case "mvp++":
                playerRankInt = 2;
                break;
            case "helper":
                playerRankInt = 3;
                break;
            case "mod":
                playerRankInt = 4;
                break;
            case "admin":
                playerRankInt = 5;
                break;
        }

        return playerRankInt >= rankInt;
    }

}