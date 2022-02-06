package me.Coderforlife.SimpleDrugs.DrugPlants;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.Druging.Drug;

public class DrugPlantItem implements SDCraftableItem {
	
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
	
	public Drug getDrug() {
		return drugToBePlaced;
	}
	
	public ItemStack makeItem() {
		ItemMeta im = plantableItem.getItemMeta();
		
		im.getPersistentDataContainer().set(Main.plugin.getDrugMain(), PersistentDataType.BYTE, (byte)1);
		im.getPersistentDataContainer().set(Main.plugin.getDrugKey(), PersistentDataType.STRING, drugToBePlaced.getName());
		im.getPersistentDataContainer().set(Main.plugin.getDrugPlantedOn(), PersistentDataType.STRING, plantOn.toString());
		im.getPersistentDataContainer().set(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER, amount);
		im.getPersistentDataContainer().set(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING, plantableItem.getType().toString());
		
		plantableItem.setItemMeta(im);
		return plantableItem;
	}

	@Override
	public ItemStack getItem() {
		return makeItem();
	}

	@Override
	public String getNamespaceName() {
		return "DrugSeed_" + getDrug().getName();
	}
	
}