package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

import me.Coderforlife.SimpleDrugs.Main;

public class SDShapeless extends SDRecipe {
	
	public SDShapeless() {}
	
	public SDShapeless(String name, ItemStack result) {
		super(name, result);
	}
	
	public void addItemStack(String i) {
		items.add(i);
	}
	
	public void registerRecipe() {
		nk = new NamespacedKey(Main.plugin, "drugs_crafting_" + getName());
		registerNamespacedKey(nk);
		Main.plugin.getRecipeManager().addRecipe(this);
	}

	@Override
	public void createRecipe() {
		Bukkit.getServer().removeRecipe(nk);
		ShapelessRecipe sr = new ShapelessRecipe(nk, getResult());
		for(ItemStack i : convertedItems) {
			sr.addIngredient(new RecipeChoice.ExactChoice(i));
		}
		Bukkit.getServer().addRecipe(sr);
	}
	
}