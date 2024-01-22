package org.lins.mmmjjkx.nex2b2tcore.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TheHelp extends AbstractSimpleCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                if (!hasPerm(sender, "help")) {
                    sendNoPermission(sender);
                    return false;
                }
                sendMsg(sender, "help-1");
                sendMsg(sender, "help-2");
                sendMsg(sender, "help-3");
                sendMsg(sender, "help-4");
                sendMsg(sender, "help-5");
                sendMsg(sender, "help-6");
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (!hasPerm(sender, "reload")) {
                    sendNoPermission(sender);
                    return false;
                }
                sendMsg(sender, "reloaded");
                return true;
            } else {
                sendWrongInput(sender);
                return false;
            }
        } else if (args.length == 0) {
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
        } else {
            sendWrongInput(sender);
            return false;
        }
    }
}
