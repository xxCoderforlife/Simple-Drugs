package me.Coderforlife.Drugs.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryType;

import me.Coderforlife.Drugs.Drug;
import me.Coderforlife.Drugs.Drugs;
import me.Coderforlife.Drugs.Main;

public class CraftingEvent implements Listener{

	private Main plugin;
	Drugs D;
	
	public CraftingEvent(Main plugin ,Drugs D) {
		this.setPlugin(plugin);
		this.D = D;
	}


	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void OnCraftEvent(CraftItemEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getType().equals(InventoryType.CRAFTING)) {
			for(Drug drugs : D.getAllDrugs()) {
				if(e.getInventory().getResult().equals(drugs.getDrugItem())) {
					if(!p.hasPermission("drugs.craft" + drugs.getName().toLowerCase())) {
						e.setCancelled(true);
					}else {
						p.sendMessage(
								Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED
								+ "drugs.craft." + drugs.getName());
					}
				}
			}
		}
	}

}
