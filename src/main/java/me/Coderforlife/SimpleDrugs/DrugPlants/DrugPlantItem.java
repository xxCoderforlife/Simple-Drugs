package me.Coderforlife.SimpleDrugs.DrugPlants;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.Drug;

public class DrugPlantItem {
	
	private Drug drugToBePlaced;
	private ItemStack plantableItem;
	private Material plantOn;
	private Integer amount;
	
	public DrugPlantItem(Drug d, ItemStack pi, Material m, Integer i) {
		drugToBePlaced = d;
		plantableItem = pi;
		plantOn = m;
		amount = i;
	}
	
	public ItemStack makeItem(Integer i) {
		ItemMeta im = plantableItem.getItemMeta();
		im.setDisplayName("Plant " + drugToBePlaced.getDisplayname());
		
		im.getPersistentDataContainer().set(Main.plugin.getDrugMain(), PersistentDataType.BYTE, (byte)1);
		im.getPersistentDataContainer().set(Main.plugin.getDrugKey(), PersistentDataType.STRING, drugToBePlaced.getName());
		im.getPersistentDataContainer().set(Main.plugin.getDrugPlantedOn(), PersistentDataType.STRING, plantOn.toString());
		im.getPersistentDataContainer().set(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER, amount);
		im.getPersistentDataContainer().set(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING, plantableItem.getType().toString());
		
		plantableItem.setItemMeta(im);
		plantableItem.setAmount(i);
		return plantableItem;
	}
	
	public ItemStack makeItem() {
		return makeItem(1);
	}
	
}