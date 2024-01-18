package org.lins.mmmjjkx.nex2b2tcore.cmd;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.lins.mmmjjkx.nex2b2tcore.Nex2B2TCore;

public abstract class AbstractSimpleCommand implements CommandExecutor {
    protected boolean hasPerm(CommandSender s, String cmdName) {
        return s.hasPermission("nex2b2tcore.cmd." + cmdName);
    }

    protected void sendNoPermission(CommandSender s) {
        Nex2B2TCore.messageHandler.send(s, "no-permission");
    }

    protected void sendWrongInput(CommandSender s) {
        Nex2B2TCore.messageHandler.send(s, "wrong-arg-inputs");
    }

    protected void sendPlayerNotFound(CommandSender s) {
        Nex2B2TCore.messageHandler.send(s, "player-not-found");
    }

    protected void sendNoConsole(CommandSender s) {
        Nex2B2TCore.messageHandler.send(s, "no-console");
    }

    protected void sendMsg(CommandSender s, String key) {
        Nex2B2TCore.messageHandler.send(s, key);
    }
}
