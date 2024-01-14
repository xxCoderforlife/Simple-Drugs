package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Util.TranslationFile;
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
            for(Drug drugs : Main.plugin.getDrugManager().getItems().values()) {
                if(e.getInventory().getResult().equals(drugs.getItem())) {
                    if(!p.hasPermission(drugs.getCraftingPermission())) {
                        e.setCancelled(true);
                        p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', TranslationFile.Error_Cant_Use_Command));
//                        p.sendMessage("SD " + ChatColor.RED + "You don't have the permissions to use this command");
                        p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.craft." + drugs.getName());
                    }  
                }
            }
        }
    }

}
