package net.swofty.hypixelthepit.Managers.InterfacesAndEnums;

import org.bukkit.entity.Player;

public interface HypixelPlayer {

    Player getBukkitPlayer();

    void sendFormattedMessage(String message);

    String getRank();

    Boolean rankHigherOrSameThan(String rank);

    int getLevel();

    int getGold();

    String getLevelColorized();

    String getGoldColorized();

    int getXP();

    String getDisplayName();

    String getDisplayNameColor();

    void setLevel(int newLevel);

    void setXP(int newXP);

    void setGold(int newGold);

    void setData(String data, String newData);

    String getStatus();

}
