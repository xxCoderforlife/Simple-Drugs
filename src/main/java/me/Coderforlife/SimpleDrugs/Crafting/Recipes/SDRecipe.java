package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import org.bukkit.inventory.ItemStack;

public abstract class SDRecipe {

	private String name;
	private ItemStack result;
	
	public SDRecipe(String n, ItemStack r) {
		result = r;
		name = n;
	}
	
	public ItemStack getResult() {
		return result;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void registerRecipe();
	
}