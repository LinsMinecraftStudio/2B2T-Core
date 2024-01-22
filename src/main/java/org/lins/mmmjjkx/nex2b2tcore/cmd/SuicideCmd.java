package org.lins.mmmjjkx.nex2b2tcore.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCmd extends AbstractSimpleCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (hasPerm(sender, "suicide")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setHealth(0);
                return true;
            }
            sendNoConsole(sender);
            return false;
        }
        sendNoPermission(sender);
        return false;
    }
}
