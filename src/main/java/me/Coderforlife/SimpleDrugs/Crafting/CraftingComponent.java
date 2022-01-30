package me.Coderforlife.SimpleDrugs.Crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonArray;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;

public class CraftingComponent {

	private String name;
	private String fileName;
	private ItemStack result;
	private DrugCraftingType type;
	private JsonArray materials;
	
	public CraftingComponent(String s, String fn, ItemStack is, DrugCraftingType t, JsonArray m) {
		name = s;
		fileName = fn;
		result = is;
		type = t;
		materials = m;
	}
	
	public DrugCraftingType getType() {
		return type;
	}
	
	public ItemStack getStack() {
		return result;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public JsonArray getMaterials() {
		return materials;
	}
	
	public void registerRecipe() {
		switch(type) {
		case FURNACE:
			List<ItemStack> furnaceMats = loadMaterialsForCrafting(fileName, materials);
			
			if(furnaceMats.size() > 1 || furnaceMats.size() == 0) {
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Can only have one input for Recipe using FURNACE type");
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
			}
			
			SDFurnace furnace = new SDFurnace(name, result, furnaceMats.get(0), 0f, 90);
			furnace.registerRecipe();
			break;
		case SHAPED:
			SDShaped shaped = new SDShaped(name, result);
			List<ItemStack> mats = loadMaterialsForCrafting(fileName, materials);
			
			if(mats == null) {
				return;
			}
			
			for(int i = 0; i < mats.size(); i++) {
				shaped.addItemStack(mats.get(i));
			}
			
			shaped.registerRecipe();
			break;
		case SHAPELESS:
			SDShapeless shapeless = new SDShapeless(name, result);
			List<ItemStack> recipeChoices = loadMaterialsForCrafting(fileName, materials);
			
			if(recipeChoices == null) {
				return;
			}
			
			recipeChoices.forEach(e -> {
				shapeless.addItemStack(e);
			});
			
			shapeless.registerRecipe();
			break;
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