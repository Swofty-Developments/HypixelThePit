package net.swofty.hypixelthepit.Items;

import net.swofty.hypixelthepit.Core.Utils;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SpawnItems
{

    public static void giveItems(HypixelPlayer player)
    {

        ItemStack bow = new ItemStack(Material.BOW);
        bow.setAmount(1);
        player.getBukkitPlayer().getInventory().setItem(0, bow);

        ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
        ironsword.setAmount(1);
        player.getBukkitPlayer().getInventory().setItem(1, ironsword);

        ItemStack arrow = new ItemStack(Material.ARROW);
        arrow.setAmount(32);
        player.getBukkitPlayer().getInventory().setItem(8, arrow);

        player.getBukkitPlayer().getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
        player.getBukkitPlayer().getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        player.getBukkitPlayer().getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));

    }

}
