package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

import me.Coderforlife.SimpleDrugs.Main;

public class SDFurnace extends SDRecipe {

	private ItemStack input;
	private Float xp = 0.0f;
	private int time = 90;
	
	public SDFurnace(String n, ItemStack result, ItemStack inputItem, Float xpToGive, Integer timeToCook) {
		super(n, result);
		input = inputItem;
		xp = xpToGive;
		time = timeToCook;
	}

	@Override
	public void registerRecipe() {
		FurnaceRecipe fr = new FurnaceRecipe(new NamespacedKey(Main.plugin, "drugs_crafting_" + getName()), getResult(), new RecipeChoice.ExactChoice(input), xp, time);
		Bukkit.getServer().addRecipe(fr);
	}

}