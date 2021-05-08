package net.swofty.hypixelthepit.Core;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import de.inventivegames.hologram.Hologram;
import de.inventivegames.hologram.HologramAPI;
import net.swofty.hypixelthepit.Loader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

public class Holograms
{

    public static void spawnHolograms() {

        Hologram enderChest = HologramAPI.createHologram(new Location(Bukkit.getWorld("world"), -12.5, 115.6, 7.5), Utils.formatMessage("&5&lENDER CHEST!"));
        enderChest.spawn();

        Hologram enderChestInfo = HologramAPI.createHologram(new Location(Bukkit.getWorld("world"), -12.5, 115.3, 7.5), Utils.formatMessage("&7Store items forever"));
        enderChestInfo.spawn();

        Hologram returnToLobbyInfo = HologramAPI.createHologram(new Location(Bukkit.getWorld("world"), -12.5, 117.3, 2.5), Utils.formatMessage("&7Return to lobby"));
        returnToLobbyInfo.spawn();

        Hologram returnToLobby = HologramAPI.createHologram(new Location(Bukkit.getWorld("world"), -12.5, 117.6, 2.5), Utils.formatMessage("&2&lTHE KEEPER"));
        returnToLobby.spawn();

        Hologram statsInfo = HologramAPI.createHologram(new Location(Bukkit.getWorld("world"), 11.5, 116.3, 5.5), Utils.formatMessage("&7View your stats"));
        statsInfo.spawn();

        Hologram stats = HologramAPI.createHologram(new Location(Bukkit.getWorld("world"), 11.5, 116.6, 5.5), Utils.formatMessage("&b&lSTATS"));
        stats.spawn();

    }

    public static void deleteHolograms() {

        for (Hologram holograms : HologramAPI.getHolograms()) {
            if (holograms.isSpawned()) {
                holograms.despawn();
            }
        }

    }
}
