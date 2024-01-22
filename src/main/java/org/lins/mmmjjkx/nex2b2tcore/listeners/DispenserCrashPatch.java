package org.lins.mmmjjkx.nex2b2tcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Directional;
import org.lins.mmmjjkx.nex2b2tcore.Nex2B2TCore;

public class DispenserCrashPatch implements Listener {
    public DispenserCrashPatch() {
        if (Bukkit.getVersion().contains("1.12.2")) {
            Bukkit.getPluginManager().registerEvents(this, Nex2B2TCore.instance);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEvent(BlockPlaceEvent e) {
        Block block = e.getBlock();
        if (block.getType() == Material.DISPENSER) {
            Directional directional = (Directional) block.getState().getData();
            boolean b1 = block.getY() == 255 && directional.getFacing().equals(BlockFace.UP);
            boolean b2 = block.getY() == 0 && directional.getFacing().equals(BlockFace.DOWN);
            if (b1 || b2) {
                e.setCancelled(true);
                Nex2B2TCore.messageHandler.send(e.getPlayer(), "un-placeable-dispenser");
            }
        }
    }
}
