package org.lins.mmmjjkx.nex2b2tcore.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TheHelp extends AbstractSimpleCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (hasPerm(sender, "help")) {
            sendMsg(sender, "help-1");
            sendMsg(sender, "help-2");
            sendMsg(sender, "help-3");
            sendMsg(sender, "help-4");
            sendMsg(sender, "help-5");
            sendMsg(sender, "help-6");
            return true;
        }
        sendNoPermission(sender);
        return false;
    }
}
