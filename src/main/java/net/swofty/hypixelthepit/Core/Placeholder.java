package net.swofty.hypixelthepit.Core;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.swofty.hypixelthepit.Loader;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import org.bukkit.OfflinePlayer;

public class Placeholder extends PlaceholderExpansion {

    private Loader plugin;

    public Placeholder(Loader plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return "Swooftyy";
    }

    @Override
    public String getIdentifier(){
        return "hypixelpit";
    }

    @Override
    public String getVersion(){
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier){

        switch (identifier) {

            case "displayname":
                return new PlayerManager(player.getPlayer()).getDisplayName();
            case "displayformat":
                return new PlayerManager(player.getPlayer()).getLevelColorized();
        }
        return null;
    }
}
