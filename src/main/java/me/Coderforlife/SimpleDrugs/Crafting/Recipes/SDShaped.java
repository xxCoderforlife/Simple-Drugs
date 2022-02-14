package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import me.Coderforlife.SimpleDrugs.Main;

public class SDShaped extends SDRecipe {
	
	public SDShaped() {}
	
	public SDShaped(ItemStack r) {
		super(r);
	}
	
	public void addItemStack(String i) {
		items.add(i);
	}

	@Override
	public void registerRecipe() {
		nk = new NamespacedKey(Main.plugin, "drugs_crafting_" + String.valueOf(Main.plugin.getRecipeManager().getRecipeNum()));
		registerNamespacedKey(nk);
		Main.plugin.getRecipeManager().addRecipe(this);
	}

	@Override
	public void createRecipe() {
		Bukkit.getServer().removeRecipe(nk);
		ShapedRecipe r = new ShapedRecipe(nk, getResult());
		r.shape("ABC", "DEF", "GHI");
		r.setIngredient('A', new RecipeChoice.ExactChoice(convertedItems.get(0)));
		r.setIngredient('B', new RecipeChoice.ExactChoice(convertedItems.get(1)));
		r.setIngredient('C', new RecipeChoice.ExactChoice(convertedItems.get(2)));
		r.setIngredient('D', new RecipeChoice.ExactChoice(convertedItems.get(3)));
		r.setIngredient('E', new RecipeChoice.ExactChoice(convertedItems.get(4)));
		r.setIngredient('F', new RecipeChoice.ExactChoice(convertedItems.get(5)));
		r.setIngredient('G', new RecipeChoice.ExactChoice(convertedItems.get(6)));
		r.setIngredient('H', new RecipeChoice.ExactChoice(convertedItems.get(7)));
		r.setIngredient('I', new RecipeChoice.ExactChoice(convertedItems.get(8)));
		
		Bukkit.getServer().addRecipe(r);
	}
	
}