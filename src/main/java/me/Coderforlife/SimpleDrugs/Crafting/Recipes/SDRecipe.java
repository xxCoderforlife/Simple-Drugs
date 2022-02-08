package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;

public abstract class SDRecipe {

	private String name;
	private ItemStack result;
	protected List<ItemStack> items = new ArrayList<>();
	
	public SDRecipe() {}
	
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
	
	public List<ItemStack> getItems() {
		return items;
	}
	
	protected void registerNamespacedKey(NamespacedKey k) {
		Main.plugin.getRecipeManager().addKey(k);
	}
	
	public abstract void registerRecipe();
	
}