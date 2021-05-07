package net.swofty.hypixelthepit.Managers;

import net.swofty.hypixelthepit.Core.Utils;
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
    public void sendFormattedMessage(String message) { player.sendMessage(ChatColor.translateAlternateColorCodes('&', message)); }

    @Override
    public String getRank() {
        return DataManager.getData(player, "rank");
    }

    @Override
    public int getLevel() {
        return Integer.parseInt(DataManager.getData(player, "level"));
    }

    @Override
    public int getGold() {
        return Integer.parseInt(DataManager.getData(player, "gold"));
    }

    @Override
    public String getLevelColorized() {
        int level = Integer.parseInt(DataManager.getData(player, "level"));

        if (level >= 0 && level <= 9) {
            return ChatColor.translateAlternateColorCodes('&', "&7" + level);
        } else if (level >= 10 && level <= 19) {
            return ChatColor.translateAlternateColorCodes('&', "&9" + level);
        } else if (level >= 20 && level <= 29) {
            return ChatColor.translateAlternateColorCodes('&', "&3" + level);
        } else if (level >= 30 && level <= 39) {
            return ChatColor.translateAlternateColorCodes('&', "&2" + level);
        } else if (level >= 40 && level <= 49) {
            return ChatColor.translateAlternateColorCodes('&', "&a" + level );
        } else if (level >= 50 && level <= 59) {
            return ChatColor.translateAlternateColorCodes('&', "&e" + level);
        } else if (level >= 60 && level <= 69) {
            return ChatColor.translateAlternateColorCodes('&', "&6&l" + level);
        } else if (level >= 70 && level <= 79) {
            return ChatColor.translateAlternateColorCodes('&', "&c&l" + level);
        } else if (level >= 80 && level <= 89) {
            return ChatColor.translateAlternateColorCodes('&', "&4&l" + level);
        } else if (level >= 90 && level <= 99) {
            return ChatColor.translateAlternateColorCodes('&', "&5&l" + level);
        } else if (level >= 100 && level <= 109) {
            return ChatColor.translateAlternateColorCodes('&', "&d&l" + level);
        } else if (level >= 110 && level <= 119) {
            return ChatColor.translateAlternateColorCodes('&', "&f&l" + level);
        } else if (level == 120) {
            return ChatColor.translateAlternateColorCodes('&', "&b&l" + level);
        }

        return ChatColor.translateAlternateColorCodes('&', "&cERROR");
    }

    @Override
    public String getGoldColorized() {
        return Utils.formatMessage("&6" + DataManager.getData(player, "gold"));
    }

    @Override
    public int getXP() {
        return Integer.parseInt(DataManager.getData(player, "xp"));
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
    public void setGold(int newGold) {
        DataManager.editGold(player, newGold);
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