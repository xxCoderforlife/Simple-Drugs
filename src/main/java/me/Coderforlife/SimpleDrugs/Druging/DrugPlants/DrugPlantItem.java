package me.Coderforlife.SimpleDrugs.Druging.DrugPlants;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Druging.Drug;

public class DrugPlantItem implements SDCraftableItem {
	
	private Drug drugToBePlaced;
	private String fileName;
	private ItemStack plantableItem;
	private Material plantOn;
	private Integer amount = 1;
	private SDRecipe recipe;
	
	public DrugPlantItem(Drug d, ItemStack pi, Material m, Integer i) {
		drugToBePlaced = d;
		plantableItem = pi;
		plantOn = m;
		amount = i;
	}
	
	public DrugPlantItem(Drug d, String fN, ItemStack pi, Material m, Integer i) {
		drugToBePlaced = d;
		plantableItem = pi;
		plantOn = m;
		amount = i;
		fileName = fN;
	}
	
	public Drug getDrug() {
		return drugToBePlaced;
	}
	
	public void setItem(ItemStack is) {
		plantableItem = is;
	}
	
	public String getFile() {
		return fileName;
	}
	
	public void setFile(String s) {
		fileName = s;
	}
	
	public SDRecipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(SDRecipe rec) {
		recipe = rec;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer i) {
		amount = i;
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