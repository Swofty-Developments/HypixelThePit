package net.swofty.hypixelthepit.Commands.CommandList;

import net.swofty.hypixelthepit.GUIs.GUIsLoader;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;

public class stats
{

    public static void run(HypixelPlayer player, String[] args)
    {

        GUIsLoader.openGUI(player, "stats");

    }

}
