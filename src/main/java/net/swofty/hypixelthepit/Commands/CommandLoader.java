package net.swofty.hypixelthepit.Commands;

import net.swofty.hypixelthepit.Commands.CommandList.randominfotest;
import net.swofty.hypixelthepit.Commands.CommandList.test;
import net.swofty.hypixelthepit.Managers.PlayerManager;
import net.swofty.hypixelthepit.Managers.InterfacesAndEnums.HypixelPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLoader {

    public static void executeCommand(CommandSender sender, String label, String[] args) {
        HypixelPlayer player = new PlayerManager((Player) sender);

        switch (label) {

            case "test":
                test.run(player, args);
                break;

            case "randominfotest":
                randominfotest.run(player, args);
                break;


        }
    }
}
