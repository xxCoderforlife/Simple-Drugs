package me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;

public class CraftingComponent implements SDCraftableItem {

	private String name;
	private String fileName;
	private DrugCraftingType type;
	private JsonObject materials;
	private SDRecipe recipe;
	
	public CraftingComponent(String s) {
		name = s;
	}
	
	public CraftingComponent(String s, String fN, ItemStack is, DrugCraftingType t, List<ItemStack> items) {
		name = s;
		fileName = fN;
		type = t;
		recipe = Main.plugin.getRecipeManager().loadRecipe(this, items, t);
	}
	
	public CraftingComponent(String s, String fn, ItemStack is, DrugCraftingType t, JsonObject m) {
		name = s;
		fileName = fn;
		type = t;
		materials = m;
		Bukkit.getConsoleSender().sendMessage("Test");
		recipe = Main.plugin.getRecipeManager().loadRecipe(fn, this, materials, type);
	}
	
	public DrugCraftingType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFile() {
		return fileName;
	}
	
	public void setFile(String s) {
		fileName = s;
	}
	
	public JsonObject getMaterials() {
		return materials;
	}
	
	public SDRecipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(SDRecipe rec) {
		recipe = rec;
	}

	@Override
	public ItemStack getItem() {
		return recipe.getResult();
	}

	@Override
	public String getNamespaceName() {
		return "DrugCraftingComponent_" + name;
	}
	
}