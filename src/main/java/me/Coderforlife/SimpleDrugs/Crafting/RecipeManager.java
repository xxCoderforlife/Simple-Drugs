package me.Coderforlife.SimpleDrugs.Crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.Coderforlife.SimpleDrugs.Main;
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
	
	public SDRecipe loadRecipe(SDCraftableItem item, List<ItemStack> items, DrugCraftingType dct) {
		switch(dct) {
		case FURNACE:
			ItemStack fItem = items.get(0);
			
			if(fItem.hasItemMeta()) {
				ItemMeta im = fItem.getItemMeta();
				PersistentDataContainer pdc = im.getPersistentDataContainer();
				if(pdc.has(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE) && (pdc.get(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE) == (byte)1)) {
					fItem = Main.plugin.getCraftingManager().getItem(pdc.get(Main.plugin.getCraftingComponentName(), PersistentDataType.STRING)).getItem();
				}
			}
			
			SDFurnace furnace = new SDFurnace("Simple-Drug_" + item.getNamespaceName(), item.getItem(), fItem, 0f, 90);
			furnace.registerRecipe();
			return furnace;
		case SHAPED:
			SDShaped shaped = new SDShaped("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			
			if(items == null) return null;
			
			for(int i = 0; i < items.size(); i++) {
				ItemStack sItem = items.get(i);
				
				if(sItem.hasItemMeta()) {
					ItemMeta im = sItem.getItemMeta();
					PersistentDataContainer pdc = im.getPersistentDataContainer();
					if(pdc.has(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE) && (pdc.get(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE) == (byte)1)) {
						sItem = Main.plugin.getCraftingManager().getItem(pdc.get(Main.plugin.getCraftingComponentName(), PersistentDataType.STRING)).getItem();
					}
				}
				
				shaped.addItemStack(items.get(i));
			}
			
			shaped.registerRecipe();
			return shaped;
		case SHAPELESS:
			SDShapeless shapeless = new SDShapeless("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			
			items.forEach(e -> {
				ItemStack sItem = e;
				
				if(sItem.hasItemMeta()) {
					ItemMeta im = sItem.getItemMeta();
					PersistentDataContainer pdc = im.getPersistentDataContainer();
					if(pdc.has(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE) && (pdc.get(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE) == (byte)1)) {
						sItem = Main.plugin.getCraftingManager().getItem(pdc.get(Main.plugin.getCraftingComponentName(), PersistentDataType.STRING)).getItem();
					}
				}
				
				shapeless.addItemStack(e);
			});
			
			shapeless.registerRecipe();
			
			return shapeless;
		default:
			return null;
		}
	}
	
}