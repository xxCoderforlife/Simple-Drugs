package me.Coderforlife.SimpleDrugs.Crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.Brewing.BrewingRecipe;

public class RecipeManager {

	private List<SDRecipe> recipes = new ArrayList<>();
	private List<NamespacedKey> keys = new ArrayList<>();
	private List<BrewingRecipe> brewingRecipes = new ArrayList<>();
	
	public void addRecipe(SDShapeless r) {
		recipes.add(r);
	}
	
	public void addRecipe(SDShaped r) {
		recipes.add(r);
	}
	
	public void addRecipe(SDFurnace r) {
		recipes.add(r);
	}
	
	public void addKey(NamespacedKey key) {
		keys.add(key);
	}
	
	public SDRecipe getRecipeFromResult(ItemStack result) {
		for(SDRecipe r : recipes) {
			if(r.getResult().equals(result)) return r;
		}
		return null;
	}
	
	public void addBrewingRecipe(BrewingRecipe br) {
		brewingRecipes.add(br);
	}
	
	public List<BrewingRecipe> getBrewingRecipes() {
		return brewingRecipes;
	}
	
	public BrewingRecipe getBrewingRecipe(BrewerInventory inv) {
		if(inv.getFuel() == null) return null;
		if(inv.getIngredient() == null) return null;
		if(inv.getItem(0) == null && inv.getItem(1) == null && inv.getItem(2) == null) return null;
		
		for(BrewingRecipe br : brewingRecipes) {
			if(inv.getIngredient().isSimilar(br.getIngredient()) && inv.getFuel().isSimilar(br.getFuel()) && inv.containsAtLeast(br.getInput(), 1)) {
				return br;
			}
		}
		
		return null;
	}
	
	public List<NamespacedKey> getKeys() {
		return keys;
	}
	
	public boolean itemsMatch(ItemStack[] items, SDShapeless shapeless) {
		List<ItemStack> sdItems = new ArrayList<>(shapeless.getItems());
		for(ItemStack i : items) {
			if(i == null) continue;
			ItemStack newI = i.clone();
			if(newI == null) continue;
			newI.setAmount(1);
			if(shapeless.getItems().contains(newI)) {
				sdItems.remove(newI);
			}
		}
		if(sdItems.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
}