package org.lins.mmmjjkx.nex2b2tcore.cmd;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lins.mmmjjkx.nex2b2tcore.Nex2B2TCore;
import org.lins.mmmjjkx.nex2b2tcore.utils.MessageHandler;

import java.util.List;
import java.util.stream.Collectors;

public class WorldInfoCmd extends AbstractSimpleCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (hasPerm(sender, "worldinfo")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                World world = player.getWorld();
                List<String> playerNames = world.getPlayers().stream().map(Player::getName).collect(Collectors.toList());
                MessageHandler mh = Nex2B2TCore.messageHandler;
                player.sendMessage(
                        new String[]{
                                mh.colorize(mh.get("worldinfo-1").replaceAll("%world%", world.getName())),
                                mh.colorize(mh.get("worldinfo-2").replaceAll("%seed%", String.valueOf(world.getSeed()))),
                                mh.colorize(mh.get("worldinfo-3").replaceAll("%players%", playerNames.toString()))
                        }
                );
                return true;
            }
            sendNoConsole(sender);
            return false;
        }
        sendNoPermission(sender);
        return false;
    }
}
