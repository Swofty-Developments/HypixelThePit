package net.swofty.hypixelthepit.Managers.InterfacesAndEnums;

import org.bukkit.Server;

public interface HypixelServer {

    Server getBukkit();

    void broadcastFormattedMessage(String message);

}
