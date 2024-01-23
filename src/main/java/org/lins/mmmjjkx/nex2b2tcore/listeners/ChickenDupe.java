package org.lins.mmmjjkx.nex2b2tcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.lins.mmmjjkx.nex2b2tcore.Nex2B2TCore;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class ChickenDupe implements Listener {
    private final Map<Player, Instant> cooldownMap;
    private final int cooldown = Nex2B2TCore.config.getInt("chickenDupe.cooldown");

    public ChickenDupe() {
        cooldownMap = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, Nex2B2TCore.instance);
    }

    @EventHandler
    public void onEvent(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.CHICKEN)) {
            Player player = e.getPlayer();
            PlayerInventory pl = player.getInventory();
            ItemStack stack = pl.getItemInMainHand();
            if (stack != null && stack.getType() != Material.AIR) {
                int amount = stack.getAmount() * 2;
                if (amount < 256) {
                    if (cooldownMap.containsKey(player)) {
                        Instant instant = cooldownMap.get(player);
                        if (!instant.isBefore(Instant.now())) {
                            long dur = Instant.now().getEpochSecond();
                            long aft = instant.getEpochSecond();

                            long between = aft - dur;
                            player.sendMessage(
                                    Nex2B2TCore.messageHandler.colorize(Nex2B2TCore.messageHandler.get("chickenDupeCooldown")
                                            .replaceAll("%sec%", String.valueOf(between)))
                            );

                            return;
                        }
                    }

                    stack.setAmount(amount);
                    pl.setItemInMainHand(stack);

                    Duration duration = Duration.of(cooldown, ChronoUnit.SECONDS);
                    Instant after = Instant.now().plus(duration);
                    cooldownMap.put(player, after);
                }
            }
        }
    }
}
