package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public abstract class SDRecipe {

	private String name;
	private ItemStack result;
	protected List<ItemStack> items = new ArrayList<>();
	
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
	
	public List<ItemStack> getItems() {
		return items;
	}
	
	public abstract void registerRecipe();
	
}