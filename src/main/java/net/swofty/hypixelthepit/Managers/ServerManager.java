package net.swofty.hypixelthepit.Managers;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import sun.jvm.hotspot.utilities.IntervalNode;

public class ServerManager implements HypixelServer {

    protected Server server;

    public ServerManager(Server thisBukkit) {
        server = thisBukkit;
    }

    public Server getBukkit() {
        return server;
    }

    @Override
    public void broadcastFormattedMessage(String message) {
        for(Player playersOnline : Bukkit.getServer().getOnlinePlayers()) {
            HypixelPlayer player = new PlayerManager(playersOnline);

            player.sendFormattedMessage(message);
        }
    }


}