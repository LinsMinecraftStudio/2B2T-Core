package org.lins.mmmjjkx.nex2b2tcore.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.lins.mmmjjkx.nex2b2tcore.Nex2B2TCore;

import java.util.HashMap;
import java.util.Map;

public class ClearSuperEnchantsCmd extends AbstractSimpleCommand {
    private final boolean replace = Nex2B2TCore.config.getBoolean("clearSuperEnchants.replace");
    private final int maxLvl = Nex2B2TCore.config.getInt("clearSuperEnchants.lvl");
    private final boolean cleanHigherThanMax = Nex2B2TCore.config.getBoolean("clearSuperEnchants.higherThanMax");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (hasPerm(sender, "clearsuperenchants")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    doClear(p);
                    sendMsg(sender, "superEnchantsCleared");
                    return true;
                }
                sendNoConsole(sender);
                return false;
            } else if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player == null) {
                    sendPlayerNotFound(sender);
                    return false;
                }
                doClear(player);
                sendMsg(sender, "superEnchantsCleared");
                return true;
            } else {
                sendWrongInput(sender);
            }
            return false;
        }
        sendNoPermission(sender);
        return false;
    }

    private void doClear(Player p) {
        PlayerInventory inventory = p.getInventory();
        for (int i = 0; i < 36; i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType() == Material.AIR || item.getEnchantments().isEmpty()) continue;
            Map<Enchantment, Integer> enchants = new HashMap<>(item.getEnchantments());
            enchants.forEach((en, lvl) -> {
                if (lvl > en.getMaxLevel() && cleanHigherThanMax || lvl > maxLvl) {
                    item.removeEnchantment(en);
                    if (replace) {
                        item.addEnchantment(en, en.getMaxLevel());
                    }
                }
            });
        }
    }
}
