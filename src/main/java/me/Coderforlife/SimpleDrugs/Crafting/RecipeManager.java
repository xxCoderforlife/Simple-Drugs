package me.Coderforlife.SimpleDrugs.Crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonArray;

import me.Coderforlife.SimpleDrugs.Main;
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
	
	public SDRecipe loadRecipe(String fileName, SDCraftableItem item, JsonArray ja, DrugCraftingType dct) {
    	switch(dct) {
		case FURNACE:
			List<ItemStack> furnaceMats = loadMaterialsForCrafting(fileName, ja);
			
			if(furnaceMats.size() > 1 || furnaceMats.size() == 0) {
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Can only have one input for Recipe using FURNACE type");
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
			}
			
			SDFurnace furnace = new SDFurnace("Simple-Drug_" + item.getNamespaceName(), item.getItem(), furnaceMats.get(0), 0f, 90);
			furnace.registerRecipe();
			
			return furnace;
		case SHAPED:
			SDShaped shaped = new SDShaped("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			List<ItemStack> mats = loadMaterialsForCrafting(fileName, ja);
			
			if(mats == null) {
				return null;
			}
			
			for(int i = 0; i < mats.size(); i++) {
				shaped.addItemStack(mats.get(i));
			}

			shaped.registerRecipe();
			return shaped;
		case SHAPELESS:
			SDShapeless shapeless = new SDShapeless("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			List<ItemStack> materials = loadMaterialsForCrafting(fileName, ja);
			
			if(materials == null) {
				return null;
			}
			
			materials.forEach(e -> {
				shapeless.addItemStack(e);
			});
			
			shapeless.registerRecipe();
			
			return shapeless;
		default:
			return null;
    	}
    }
	
	private List<ItemStack> loadMaterialsForCrafting(String fileName, JsonArray ja) {
		List<ItemStack> materials = new ArrayList<>();
	    
		for(int i = 0; i < ja.size(); i++) {
    		Material m = Material.getMaterial(ja.get(i).getAsString().toUpperCase());
    		if(m == null) {
    			CraftingComponent cc = Main.plugin.getCraftingManager().getByName(ja.get(i).getAsString().toUpperCase());
    			if(cc == null) {
    				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
    				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Material: §7" + ja.get(i).getAsString().toUpperCase() + " §cdoes not exist as a Minecraft Material or Custom Crafting Component");
    				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
    				return null;
    			}
    			
    			materials.add(cc.getStack());
    		} else {
    			materials.add(new ItemStack(m));
    		}
    	}
		
    	if(materials.size() > 9) {
    		Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
    		Bukkit.getConsoleSender().sendMessage("§c[ERROR] Materials added cannot be above 9");
    		return null;
    	}
    	
    	return materials;
	}
	
}