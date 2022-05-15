package me.Coderforlife.SimpleDrugs.Druging.DrugPlants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Util.AbstractSDCraftableManager;
import net.md_5.bungee.api.ChatColor;

public class DrugPlantItem implements SDCraftableItem {
	
	private Drug drug;
	private String fileName;
	private ItemStack plantableItem;
	private Material plantOn;
	private Integer amount = 1;
	private SDRecipe recipe;
	
	public DrugPlantItem(Drug d, ItemStack pi, Material m, Integer i) {
		drug = d;
		plantableItem = pi;
		plantOn = m;
		amount = i;
	}
	
	public DrugPlantItem(Drug d, String fN, ItemStack pi, Material m, Integer i) {
		drug = d;
		plantableItem = pi;
		plantOn = m;
		amount = i;
		fileName = fN;
	}
	
	public String getName() {
		return drug.getName().toUpperCase();
	}
	
	public Drug getDrug() {
		return drug;
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
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&o" + drug.getDisplayName() + " Seeds"));
		
		im.getPersistentDataContainer().set(Main.plugin.getDrugMain(), PersistentDataType.BYTE, (byte)1);
		im.getPersistentDataContainer().set(Main.plugin.getDrugKey(), PersistentDataType.STRING, drug.getName().toUpperCase());
		im.getPersistentDataContainer().set(Main.plugin.getDrugPlantedOn(), PersistentDataType.STRING, plantOn.toString());
		im.getPersistentDataContainer().set(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER, amount);
		im.getPersistentDataContainer().set(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING, plantableItem.getType().toString());
		
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7- &ePlant these to make a &2&o" + drug.getName() + " Plant"));
		im.setLore(lore);
		
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

	@Override
	public AbstractSDCraftableManager<DrugPlantItem> getManager() {
		return Main.plugin.getDrugSeedManager();
	}

	@Override
	public String getDisplayName() {
		return getName();
	}
	
}