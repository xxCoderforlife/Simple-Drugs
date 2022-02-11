package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Util.CCMaterialConverter;

public abstract class SDRecipe {

	private String name;
	private ItemStack result;
	protected List<String> items = new ArrayList<>();
	protected List<ItemStack> convertedItems = new ArrayList<>();
	protected NamespacedKey nk;
	
	public SDRecipe() {
		
	}
	
	public SDRecipe(String n, ItemStack r) {
		result = r;
		name = n;
	}
	
	public ItemStack getResult() {
		return result;
	}
	
	public void setResult(ItemStack item) {
		result = item;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public List<String> getItems() {
		return items;
	}
	
	public List<ItemStack> getConvertedItems() {
		return convertedItems;
	}
	
	protected void registerNamespacedKey(NamespacedKey k) {
		Main.plugin.getRecipeManager().addKey(k);
	}
	
	public void convertItems() {
		List<ItemStack> stack = new ArrayList<>();
		for(String s : getItems()) {
			stack.add(CCMaterialConverter.getCCOrMaterial(null, s));
		}
		convertedItems = stack;
	}
	
	public abstract void registerRecipe();
	public abstract void createRecipe();
	
}