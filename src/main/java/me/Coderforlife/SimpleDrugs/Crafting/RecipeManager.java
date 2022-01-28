package me.Coderforlife.SimpleDrugs.Crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;

public class RecipeManager {

private List<SDRecipe> recipes = new ArrayList<>();
	
	public void addRecipe(SDShapeless r) {
		recipes.add(r);
	}
	
	public void addRecipe(SDShaped r) {
		recipes.add(r);
	}
	
	public SDRecipe getRecipeFromResult(ItemStack result) {
		for(SDRecipe r : recipes) {
			if(r.getResult().equals(result)) return r;
		}
		return null;
	}
	
	public boolean itemsMatch(ItemStack[] items, SDShapeless shapeless) {
		List<ItemStack> sdItems = new ArrayList<>(shapeless.getItems());
		for(ItemStack i : items) {
			if(shapeless.getItems().contains(i)) {
				sdItems.remove(i);
			}
		}
		if(sdItems.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
}