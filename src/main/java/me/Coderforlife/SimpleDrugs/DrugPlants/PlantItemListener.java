package me.Coderforlife.SimpleDrugs.DrugPlants;

import org.bukkit.Material;
import org.bukkit.block.Block;
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
import me.Coderforlife.SimpleDrugs.Util.CustomBlockData;

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
		if(!pdc.has(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING)) return;
		if(!pdc.has(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER)) return;
		
		Material m = Material.valueOf(pdc.get(Main.plugin.getDrugPlantedOn(), PersistentDataType.STRING));
		if(!e.getClickedBlock().getType().equals(m)) return;
		
		is.setAmount(is.getAmount() - 1);
		Block b = e.getClickedBlock().getLocation().add(0, 1, 0).getBlock();
		b.setType(Material.WHEAT);
		PersistentDataContainer bPDC = new CustomBlockData(b, Main.plugin);
		bPDC.set(Main.plugin.getDrugMain(), PersistentDataType.BYTE, (byte)1);
		bPDC.set(Main.plugin.getDrugKey(), PersistentDataType.STRING, pdc.get(Main.plugin.getDrugKey(), PersistentDataType.STRING));
		bPDC.set(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER, pdc.get(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER));
		bPDC.set(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING, pdc.get(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING));
	}
	
}