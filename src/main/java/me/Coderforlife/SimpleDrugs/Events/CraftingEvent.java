package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryType;

public class CraftingEvent implements Listener {
    
    private Main plugin = Main.plugin;
    @EventHandler
    public void OnCraftEvent(CraftItemEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getType().equals(InventoryType.CRAFTING)) {
            for(Drug drugs : Main.plugin.getDrugManager().getallDrugs()) {
                if(e.getInventory().getResult().equals(drugs.getItem())) {
                    if(!p.hasPermission("drugs.craft" + drugs.getName().toLowerCase())) {
                        e.setCancelled(true);
                    } else {
                        p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                        p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.craft." + drugs.getName());
                    }
                }
            }
        }
    }

}
