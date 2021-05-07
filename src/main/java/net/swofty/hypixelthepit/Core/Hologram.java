package net.swofty.hypixelthepit.Core;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import net.swofty.hypixelthepit.Loader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

public class Hologram {

    public static void spawnHolograms() {
        com.gmail.filoghost.holographicdisplays.api.Hologram enderChest = HologramsAPI.createHologram(Loader.getInstance(), new Location(Bukkit.getWorld("world"), 12.5, 115.6, 7.5));
        enderChest.appendTextLine("&5&lENDER CHEST");
        enderChest.appendTextLine("&7Store items forever");
    }

    public static void deleteHolograms() {

        for (com.gmail.filoghost.holographicdisplays.api.Hologram hologram : HologramsAPI.getHolograms(Loader.getInstance())) {
            hologram.delete();
        }

    }
}
