package me.Coderforlife.SimpleDrugs.GUI.Shop;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;

public class sellGUI implements Listener {

    private final Inventory inv;
    private Main plugin = Main.plugin;
    private ArrayList<ItemStack> sellItems = new ArrayList<>();

    public sellGUI() {
        inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&',
                "&5&oSell Drugs"));
    }

    public Inventory getSellInventory() {
        return inv;
    }

    public void openInventory(HumanEntity he) {
        he.openInventory(inv);
    }

    @EventHandler
    public void openInventoryClose(InventoryCloseEvent ev) {
        Player p = (Player) ev.getPlayer();
        if (ev.getInventory().equals(inv)) {
            ItemStack[] s = ev.getInventory().getContents();
            for (ItemStack i : s) {
                if (i != null) {
                    sellItems.add(i);
                }
            }
            if (ev.getInventory().isEmpty()) {
                return;
            }
            Inventory sellInv = ev.getInventory();
            for (ItemStack is : sellItems) {
                if (plugin.getDrugManager().isDrugItem(is)) {
                    Economy eco = plugin.getEconomy();
                    Drug d = plugin.getDrugManager().matchDrug(is);
                    sellInv.remove(is);
                    eco.depositPlayer(p, d.getSellPrice() * is.getAmount());
                }
                p.getInventory().addItem(is);
            }
            p.updateInventory();
            p.sendMessage("You sold everything that's a drug");
        }
    }

}
