package org.lins.mmmjjkx.nex2b2tcore.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ServerInfoCmd extends AbstractSimpleCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (hasPerm(sender, "serverinfo")) {

            return false;
        }
        sendNoPermission(sender);
        return false;
    }
}
