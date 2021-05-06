package net.swofty.hypixelthepit.Managers.InterfacesAndEnums;

import org.bukkit.entity.Player;

public interface HypixelPlayer {

    Player getBukkitPlayer();

    void sendFormattedMessage(String message);

    String getRank();

    Boolean rankHigherOrSameThan(String rank);

    int getLevel();

    int getXP();

    String getDisplayName();

    void setLevel(int newLevel);

    void setXP(int newXP);

}
