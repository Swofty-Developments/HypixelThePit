package net.swofty.hypixelthepit.GUIs.GUIsList;

import net.swofty.hypixelthepit.Core.Utils;
import net.swofty.hypixelthepit.Managers.DataManager;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class statsGUI implements Listener
{

    public static void open(HypixelPlayer player)
    {
        Inventory gui = Bukkit.createInventory(player.getBukkitPlayer(), 27, "My pit stats");

        ItemStack offensiveItem = new ItemStack(Material.IRON_SWORD);
        offensiveItem.setAmount(1);
        ItemMeta offensiveItemMeta = offensiveItem.getItemMeta();
        offensiveItemMeta.setDisplayName(Utils.formatMessage("&cOffensive Stats"));
        ArrayList<String> offensiveItemLore = new ArrayList<String>();
        offensiveItemLore.add(Utils.formatMessage("&7Kills: &a" + DataManager.getData(player.getBukkitPlayer(), "kills")));
        offensiveItemLore.add(Utils.formatMessage("&7Assists: &a0"));
        offensiveItemLore.add(Utils.formatMessage("&7Sword Hits: &a" + DataManager.getData(player.getBukkitPlayer(), "sword-hits")));
        offensiveItemLore.add(Utils.formatMessage("&7Arrows Shot: &a" + DataManager.getData(player.getBukkitPlayer(), "arrow-shots")));
        offensiveItemLore.add(Utils.formatMessage("&a "));
        offensiveItemLore.add(Utils.formatMessage("&7Damage Dealt: &a" + DataManager.getData(player.getBukkitPlayer(), "damage-dealt")));
        offensiveItemLore.add(Utils.formatMessage("&7Melee Damage Dealt: &cSoon"));
        offensiveItemLore.add(Utils.formatMessage("&7Bow Damage Dealt: &cSoon"));
        offensiveItemLore.add(Utils.formatMessage("&7 "));
        offensiveItemLore.add(Utils.formatMessage("&7Highest Streak: &cSoon"));
        offensiveItemMeta.setLore(offensiveItemLore);
        offensiveItem.setItemMeta(offensiveItemMeta);
        gui.setItem(10, offensiveItem);

        ItemStack defensiveItem = new ItemStack(Material.IRON_CHESTPLATE);
        defensiveItem.setAmount(1);
        ItemMeta defensiveItemMeta = defensiveItem.getItemMeta();
        defensiveItemMeta.setDisplayName(Utils.formatMessage("&9Defensive Stats"));
        ArrayList<String> defensiveItemLore = new ArrayList<String>();
        defensiveItemLore.add(Utils.formatMessage("&7Deaths: &a" + DataManager.getData(player.getBukkitPlayer(), "deaths")));
        defensiveItemLore.add(Utils.formatMessage("&7 "));
        defensiveItemLore.add(Utils.formatMessage("&7Damage Taken: &a" + DataManager.getData(player.getBukkitPlayer(), "damage-taken")));
        defensiveItemLore.add(Utils.formatMessage("&7Melee Damage Dealt: &cSoon"));
        defensiveItemLore.add(Utils.formatMessage("&7Bow Damage Dealt: &cSoon"));
        defensiveItemMeta.setLore(defensiveItemLore);
        defensiveItem.setItemMeta(defensiveItemMeta);
        gui.setItem(12, defensiveItem);

        ItemStack performanceItem = new ItemStack(Material.WHEAT);
        performanceItem.setAmount(1);
        ItemMeta performanceItemMeta = performanceItem.getItemMeta();
        performanceItemMeta.setDisplayName(Utils.formatMessage("&ePerformance Stats"));
        ArrayList<String> performanceItemLore = new ArrayList<String>();
        performanceItemLore.add(Utils.formatMessage("&7XP: &b" + DataManager.getData(player.getBukkitPlayer(), "xp")));
        performanceItemLore.add(Utils.formatMessage("&7 "));
        performanceItemLore.add(Utils.formatMessage("&7K/D: &a" + Integer.parseInt(DataManager.getData(player.getBukkitPlayer(), "kills")) / Integer.parseInt(DataManager.getData(player.getBukkitPlayer(), "deaths"))));
        performanceItemLore.add(Utils.formatMessage("&7K+A/D: &cComing soon"));
        performanceItemLore.add(Utils.formatMessage("&b "));
        performanceItemLore.add(Utils.formatMessage("&7Damage dealt/taken: &a" + Integer.parseInt(DataManager.getData(player.getBukkitPlayer(), "damage-dealt")) / Integer.parseInt(DataManager.getData(player.getBukkitPlayer(), "damage-taken"))));
        performanceItemLore.add(Utils.formatMessage("&7Arrows hit/shot: &cComing soon"));
        performanceItemMeta.setLore(performanceItemLore);
        performanceItem.setItemMeta(performanceItemMeta);
        gui.setItem(14, performanceItem);

        ItemStack miscItem = new ItemStack(Material.BEDROCK);
        miscItem.setAmount(1);
        ItemMeta miscItemMeta = miscItem.getItemMeta();
        miscItemMeta.setDisplayName(Utils.formatMessage("&dMiscellaneous Stats"));
        ArrayList<String> miscItemLore = new ArrayList<String>();
        miscItemLore.add(Utils.formatMessage("&cComing soon"));
        miscItemMeta.setLore(miscItemLore);
        miscItem.setItemMeta(miscItemMeta);
        gui.setItem(16, miscItem);

        player.getBukkitPlayer().openInventory(gui);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {

        if (event.getClickedInventory().getTitle().equals("My pit stats")) {

            if (event.getCurrentItem().equals(Material.AIR) || event.getCurrentItem().getType().equals(Material.AIR)) { event.setCancelled(true); }

            switch (event.getCurrentItem().getType()) {

                case BEDROCK:
                    new PlayerManager(Bukkit.getPlayer(event.getWhoClicked().getName())).sendFormattedMessage("&cComing soon!");
                    event.setCancelled(true);
                    break;

                case IRON_SWORD:
                case IRON_CHESTPLATE:
                case WHEAT:
                    event.setCancelled(true);
                    break;

            }

            event.setCancelled(true);
        }
    }

}
