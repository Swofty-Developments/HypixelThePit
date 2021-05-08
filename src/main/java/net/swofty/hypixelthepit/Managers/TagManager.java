package net.swofty.hypixelthepit.Managers;

import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.NameTag;
import org.bukkit.ChatColor;

public class TagManager implements NameTag {

    private String text;

    public TagManager(String text) {
        this.text = ChatColor.translateAlternateColorCodes('&', text);
    }

    public String getText() {
        return this.text;
    }
}