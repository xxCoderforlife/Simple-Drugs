package me.Coderforlife.SimpleDrugs.Crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
	
	public SDRecipe loadRecipe(SDCraftableItem item, List<ItemStack> items, DrugCraftingType dct) {
		switch(dct) {
		case FURNACE:
			ItemStack fItem = items.get(0);
			
			SDFurnace furnace = new SDFurnace("Simple-Drug_" + item.getNamespaceName(), item.getItem(), fItem, 0f, 90);
			furnace.registerRecipe();
			return furnace;
		case SHAPED:
			SDShaped shaped = new SDShaped("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			
			if(items == null) return null;
			
			for(int i = 0; i < items.size(); i++) {
				shaped.addItemStack(items.get(i));
			}
			
			shaped.registerRecipe();
			return shaped;
		case SHAPELESS:
			SDShapeless shapeless = new SDShapeless("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			
			items.forEach(e -> {
				shapeless.addItemStack(e);
			});
			
			shapeless.registerRecipe();
			
			return shapeless;
		default:
			return null;
		}
	}
	
	public SDRecipe loadRecipe(String fileName, SDCraftableItem item, JsonObject jo, DrugCraftingType dct) {
		switch(dct) {
		case FURNACE:
			ItemStack fItem = getItemForFurnace(fileName, jo);
			if(fItem == null) return null;
			
			SDFurnace furnace = new SDFurnace("Simple-Drug_" + item.getNamespaceName(), item.getItem(), fItem, 0f, 90);
			furnace.registerRecipe();
			return furnace;
		case SHAPED:
			SDShaped shaped = new SDShaped("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			
			List<ItemStack> mats = getItemsForShaped(fileName, jo);
			if(mats == null) return null;
			
			for(int i = 0; i < mats.size(); i++) {
				shaped.addItemStack(mats.get(i));
			}
			
			shaped.registerRecipe();
			return shaped;
		case SHAPELESS:
			SDShapeless shapeless = new SDShapeless("Simple-Drug_" + item.getNamespaceName(), item.getItem());
			List<ItemStack> materials = getItemsForShapeless(fileName, jo);
			
			if(materials == null) return null;
			
			materials.forEach(e -> {
				shapeless.addItemStack(e);
			});
			
			shapeless.registerRecipe();
			
			return shapeless;
		default:
			return null;
		}
	}
	
	private ItemStack getItemForFurnace(String fileName, JsonObject jo) {
		if(!jo.has("item")) {
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] `Item` JsonObject Required In Recipe");
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
			return null;
		}
		
		return getCCOrMaterial(fileName, jo.get("item").getAsString());
	}
	
	private List<ItemStack> getItemsForShaped(String fileName, JsonObject jo) {
		List<ItemStack> materials = new ArrayList<>();
		
		if(hasNone(jo)) {
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Items Required In Recipe");
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
			return null;
		}
		
		for(int i = 1; i < 10; i++) {
			if(!jo.has(String.valueOf(i))) {
				materials.add(new ItemStack(Material.AIR));
				continue;
			}
			
			ItemStack item = getCCOrMaterial(fileName, jo.get(String.valueOf(i)).getAsString());
			if(item == null) return null;
			materials.add(item);
		}
		
		return materials;
	}
	
	private boolean hasNone(JsonObject jo) {
		return (!jo.has("1") && !jo.has("2") && !jo.has("3") && !jo.has("4") && !jo.has("5") && !jo.has("6") && !jo.has("7") && !jo.has("8") && !jo.has("9"));
	}
	
	private List<ItemStack> getItemsForShapeless(String fileName, JsonObject jo) {
		List<ItemStack> materials = new ArrayList<>();
	    
		if(!jo.has("items") || !jo.get("items").isJsonArray()) {
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] `items` JsonElement Required In Recipe OR `items` is not a JsonArray");
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
			return null;
		}
		
		JsonArray ja = jo.get("items").getAsJsonArray();
		
		for(int i = 0; i < ja.size(); i++) {
    		ItemStack item = getCCOrMaterial(fileName, ja.get(i).getAsString());
    		if(item == null) return null;
    		materials.add(item);
    	}
		
    	if(materials.size() > 9) {
    		Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
    		Bukkit.getConsoleSender().sendMessage("§c[ERROR] Materials added cannot be above 9");
    		return null;
    	}
    	
    	return materials;
	}
	
	private ItemStack getCCOrMaterial(String fileName, String name) {
		Material m = Material.getMaterial(name.toUpperCase());
		if(m == null) {
			CraftingComponent cc = Main.plugin.getCraftingManager().getByName(name.toUpperCase());
			if(cc == null) {
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Material: §7" + name.toUpperCase() + " §cdoes not exist as a Minecraft Material or Custom Crafting Component");
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
				return null;
			}
			return cc.getStack();
		}
		return new ItemStack(m);
	}
	
}