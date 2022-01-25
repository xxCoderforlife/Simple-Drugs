package me.Coderforlife.SimpleDrugs.DrugPlants;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.Coderforlife.SimpleDrugs.Main;

public class PlantItemListener implements Listener {

	@EventHandler
	public void onPlant(PlayerInteractEvent e) {
		// Make sure they are using their main hand and right clicking on a block with an item
		if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		if(!e.getHand().equals(EquipmentSlot.HAND)) return;
		if(!e.hasItem()) return;
		
		// Make sure the item is not air
		ItemStack is = e.getPlayer().getInventory().getItemInMainHand();
		if(is == null) return;
		
		// get the item meta and ensure that all values are present
		ItemMeta im = is.getItemMeta();
		PersistentDataContainer pdc = im.getPersistentDataContainer();
		if(!pdc.has(Main.plugin.getDrugMain(), PersistentDataType.BYTE)) return;
		if(pdc.get(Main.plugin.getDrugMain(), PersistentDataType.BYTE) != (byte)1) return;
		if(!pdc.has(Main.plugin.getDrugPlantedOn(), PersistentDataType.STRING)) return;
		if(!pdc.has(Main.plugin.getDrugKey(), PersistentDataType.STRING)) return;
		
		
		// Debug and print
		Material m = Material.valueOf(pdc.get(Main.plugin.getDrugPlantedOn(), PersistentDataType.STRING));
		Bukkit.getConsoleSender().sendMessage(m.toString());
	}
	
}