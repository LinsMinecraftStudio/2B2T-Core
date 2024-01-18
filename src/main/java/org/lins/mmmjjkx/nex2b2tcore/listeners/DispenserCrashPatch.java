package org.lins.mmmjjkx.nex2b2tcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.lins.mmmjjkx.nex2b2tcore.Nex2B2TCore;

public class DispenserCrashPatch implements Listener {
    public DispenserCrashPatch() {
        Bukkit.getPluginManager().registerEvents(this, Nex2B2TCore.instance);
    }
}
