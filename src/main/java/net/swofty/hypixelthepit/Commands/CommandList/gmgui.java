package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class gmgui
{

    public static void run(HypixelPlayer player, String[] args)
    {

        if (player.rankHigherOrSameThan(Rank.ADMIN)) {

            ItemStack creative;
            ItemStack survival;
            ItemStack spectator;
            ItemMeta creativeMeta;
            ItemMeta survivalMeta;
            ItemMeta spectatorMeta;

            // Set Material/Initialize/Give a name to the ItemStacks
            creative = new ItemStack(Material.BARRIER);
            survival = new ItemStack(Material.BARRIER);
            spectator = new ItemStack(Material.BARRIER);

            // Get Item Metadata so we can edit it
            creativeMeta = creative.getItemMeta();
            survivalMeta = survival.getItemMeta();
            spectatorMeta = spectator.getItemMeta();

            // Set Item Display name meta
            creativeMeta.setDisplayName("Creative Mode!");
            survivalMeta.setDisplayName("Survival Mode!");
            spectatorMeta.setDisplayName("Spectator Mode!");

            // Assign Item Names/Meta to the items
            creative.setItemMeta(creativeMeta);
            survival.setItemMeta(survivalMeta);
            spectator.setItemMeta(spectatorMeta);

            // Create the new Inventory GUI
            Inventory inv = Bukkit.createInventory(null, 9, "Gamemode GUI");

            // Place the items in the inventory
            inv.setItem(2, creative);
            inv.setItem(4, survival);
            inv.setItem(6, spectator);

            // Opens GUI
            player.getBukkitPlayer().openInventory(inv);

        } else {
            player.sendFormattedMessage("&cYou need ADMIN or above to do this command!");
        }

    }

}
