package net.swofty.hypixelthepit.GUIs;

import net.swofty.hypixelthepit.GUIs.GUIsList.lobbyGUI;
import net.swofty.hypixelthepit.GUIs.GUIsList.statsGUI;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;

public class GUIsLoader
{

    public static void openGUI(HypixelPlayer player, String GUI) {

        switch (GUI) {

            case "lobby":
                lobbyGUI.open(player);
                break;

            case "stats":
                statsGUI.open(player);
                break;



        }
    }

}
