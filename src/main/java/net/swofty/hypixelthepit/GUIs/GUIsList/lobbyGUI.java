package net.swofty.hypixelthepit.GUIs.GUIsList;

import net.swofty.hypixelthepit.Core.Holograms;
import net.swofty.hypixelthepit.Core.Utils;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import net.swofty.hypixelthepit.Managers.Rank;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import java.util.ArrayList;

public class lobbyGUI implements Listener
{

    public static void open(HypixelPlayer player)
    {

        Inventory gui = Bukkit.createInventory(player.getBukkitPlayer(), 27, "The Keeper");

        ItemStack returnItem = new ItemStack(Material.BARRIER);
        returnItem.setAmount(1);
        ItemMeta returnItemMeta = returnItem.getItemMeta();
        returnItemMeta.setDisplayName(Utils.formatMessage("&aBack to lobby"));
        ArrayList<String> returnItemLore = new ArrayList<String>();
        returnItemLore.add(Utils.formatMessage("&7Tired of frantically"));
        returnItemLore.add(Utils.formatMessage("&7left-clicking other"));
        returnItemLore.add(Utils.formatMessage("&7players?"));
        returnItemLore.add(Utils.formatMessage("&7 "));
        returnItemLore.add(Utils.formatMessage("&eClick to warp!"));
        returnItemMeta.setLore(returnItemLore);
        returnItem.setItemMeta(returnItemMeta);

        gui.setItem(13, returnItem);
        player.getBukkitPlayer().openInventory(gui);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {

        if (event.getClickedInventory().getTitle().equals("The Keeper")) {

            if (event.getCurrentItem().equals(Material.AIR) || event.getCurrentItem().getType().equals(Material.AIR)) { event.setCancelled(true); }

            switch (event.getCurrentItem().getType()) {

                case STONE:
                case DARK_OAK_DOOR:
                case BARRIER:
                    new PlayerManager(Bukkit.getPlayer(event.getWhoClicked().getName())).sendFormattedMessage("&cYou cannot do this right now!");
                    event.setCancelled(true);
                    break;

            }

            event.setCancelled(true);
        }
    }

}
