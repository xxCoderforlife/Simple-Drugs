package me.Coderforlife.SimpleDrugs.Crafting;

import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;

public class CraftingComponent implements SDCraftableItem {

	private String name;
	private String fileName;
	private ItemStack result;
	private DrugCraftingType type;
	private JsonObject materials;
	private SDRecipe recipe;
	
	public CraftingComponent(String s, String fn, ItemStack is, DrugCraftingType t, JsonObject m) {
		name = s;
		fileName = fn;
		result = is;
		type = t;
		materials = m;
		recipe = Main.plugin.getRecipeManager().loadRecipe(fn, this, materials, type);
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
	
	public JsonObject getMaterials() {
		return materials;
	}
	
	public SDRecipe getRecipe() {
		return recipe;
	}

	@Override
	public ItemStack getItem() {
		return result;
	}

	@Override
	public String getNamespaceName() {
		return "DrugCraftingComponent_" + name;
	}
	
}