package me.Coderforlife.Drugs.Events;

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

	public CraftingEvent() {
		return;
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
					}
				}
			}
		}
	}

}
