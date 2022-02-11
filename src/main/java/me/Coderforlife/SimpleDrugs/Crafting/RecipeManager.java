package me.Coderforlife.SimpleDrugs.Crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.Brewing.SDBrewingRecipe;

public class RecipeManager {

	private List<SDRecipe> recipes = new ArrayList<>();
	private List<NamespacedKey> keys = new ArrayList<>();
	private List<SDBrewingRecipe> brewingRecipes = new ArrayList<>();
	
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
	
	public void convertAllRecipes() {
		for(SDRecipe recipe : recipes) {
			recipe.convertItems();
		}
	}
	
	public void addAllRecipes() {
		for(SDRecipe recipe : recipes) {
			recipe.createRecipe();
		}
	}
	
	public SDRecipe getRecipeFromResult(ItemStack result) {
		for(SDRecipe r : recipes) {
			if(r.getResult().equals(result)) return r;
		}
		return null;
	}
	
	public void addBrewingRecipe(SDBrewingRecipe br) {
		brewingRecipes.add(br);
	}
	
	public List<SDBrewingRecipe> getBrewingRecipes() {
		return brewingRecipes;
	}
	
	public SDBrewingRecipe getBrewingRecipe(BrewerInventory inv) {
		if(inv.getFuel() == null) return null;
		if(inv.getIngredient() == null) return null;
		if(inv.getItem(0) == null && inv.getItem(1) == null && inv.getItem(2) == null) return null;
		
		for(SDBrewingRecipe br : brewingRecipes) {
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
		List<ItemStack> sdItems = new ArrayList<>(shapeless.getConvertedItems());
		for(ItemStack i : items) {
			if(i == null) continue;
			ItemStack newI = i.clone();
			if(newI == null) continue;
			newI.setAmount(1);
			if(shapeless.getConvertedItems().contains(newI)) {
				sdItems.remove(newI);
			}
		}
		if(sdItems.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public SDRecipe loadRecipe(SDCraftableItem item, List<String> items, DrugCraftingType dct) {
		switch(dct) {
		case FURNACE:
			String fItem = items.get(0);
			SDFurnace furnace = new SDFurnace("Simple-Drug_" + item.getNamespaceName(), item.getItem(), fItem, 0f, 90);
			furnace.registerRecipe();
			return furnace;
		case SHAPED:
			SDShaped shaped = new SDShaped("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			
			if(items == null) return null;
			
			for(int i = 0; i < items.size(); i++) {
				String sItem = items.get(i);	
				shaped.addItemStack(sItem);
			}
			
			shaped.registerRecipe();
			return shaped;
		case SHAPELESS:
			SDShapeless shapeless = new SDShapeless("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			
			if(items == null) return null;
			
			items.forEach(e -> {
				String sItem = e;
				shapeless.addItemStack(sItem);
			});
			
			shapeless.registerRecipe();
			
			return shapeless;
		default:
			return null;
		}
	}
	
}