package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

import me.Coderforlife.SimpleDrugs.Main;

public class SDShapeless extends SDRecipe {
	
	public SDShapeless(String name, ItemStack result) {
		super(name, result);
	}
	
	public void addItemStack(ItemStack i) {
		items.add(i);
	}
	
	public List<ItemStack> getItems() {
		return items;
	}
	
	public void registerRecipe() {
		ShapelessRecipe sr = new ShapelessRecipe(new NamespacedKey(Main.plugin, "drugs_crafting_" + getName()), getResult());
		for(ItemStack i : items) {
			sr.addIngredient(new RecipeChoice.ExactChoice(i));
		}
		Bukkit.getServer().addRecipe(sr);
		Main.plugin.getRecipeManager().addRecipe(this);
	}
	
}