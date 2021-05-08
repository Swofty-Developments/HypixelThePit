package net.swofty.hypixelthepit.Commands;

import net.swofty.hypixelthepit.Commands.CommandList.*;
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

            case "randomleveltest":
                randomleveltest.run(player, args);
                break;

            case "setlevel":
                setlevel.run(player, args);
                break;

            case "setgold":
                setgold.run(player, args);
                break;

            case "setxp":
                setxp.run(player, args);
                break;

            case "debugholograms":
                debugholograms.run(player, args);
                break;

            case "gmgui":
                gmgui.run(player, args);
                break;

            case "lobby":
                lobby.run(player, args);
                break;

            case "simulatekill":
                simulatekill.run(player, args);
                break;

            case "simulatedeath":
                simulatedeath.run(player, args);
                break;

            case "respawn":
                respawn.run(player, args);
                break;

            case "stats":
                stats.run(player, args);
                break;


        }
    }
}
