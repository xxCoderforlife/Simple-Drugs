package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

import me.Coderforlife.SimpleDrugs.Main;

public class SDFurnace extends SDRecipe {

	private Float xp = 0.0f;
	private int time = 90;
	
	public SDFurnace() {}
	
	public SDFurnace(ItemStack result, String inputItem, Float xpToGive, Integer timeToCook) {
		super(result);
		getItems().add(inputItem);
		xp = xpToGive;
		time = timeToCook;
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
		FurnaceRecipe fr = new FurnaceRecipe(nk, getResult(), new RecipeChoice.ExactChoice(convertedItems.get(0)), xp, time);
		Bukkit.getServer().addRecipe(fr);
	}

}