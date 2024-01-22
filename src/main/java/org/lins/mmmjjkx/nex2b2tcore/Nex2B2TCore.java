package org.lins.mmmjjkx.nex2b2tcore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.lins.mmmjjkx.nex2b2tcore.cmd.*;
import org.lins.mmmjjkx.nex2b2tcore.listeners.DispenserCrashPatch;
import org.lins.mmmjjkx.nex2b2tcore.utils.MessageHandler;

public final class Nex2B2TCore extends JavaPlugin {
    public static Nex2B2TCore instance;
    public static MessageHandler messageHandler;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        saveDefaultConfig();
        saveConfig();

        config = this.getConfig();
        messageHandler = new MessageHandler(this);

        new DispenserCrashPatch();

        getCommand("nex2b2tcore").setExecutor(new TheHelp());
        getCommand("clearsuperenchants").setExecutor(new ClearSuperEnchantsCmd());
        getCommand("suicide").setExecutor(new SuicideCmd());
        getCommand("worldinfo").setExecutor(new WorldInfoCmd());
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        config = this.getConfig();
        messageHandler.reload();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
