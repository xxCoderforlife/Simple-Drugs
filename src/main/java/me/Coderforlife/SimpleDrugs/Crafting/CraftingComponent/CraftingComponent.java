package me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent;

import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Util.AbstractSDCraftableManager;

public class CraftingComponent implements SDCraftableItem {

	private String name;
	private String diplayName;
	private ItemStack item;
	private String fileName;
	private SDRecipe recipe;
	
	public CraftingComponent(String s, String displayName, ItemStack i) {
		name = s;
		item = i;
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
	
	public SDRecipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(SDRecipe rec) {
		recipe = rec;
	}

	@Override
	public ItemStack getItem() {
		return item;
	}

	@Override
	public String getNamespaceName() {
		return "DrugCraftingComponent_" + name;
	}

	@Override
	public AbstractSDCraftableManager<CraftingComponent> getManager() {
		return Main.plugin.getCraftingManager();
	}

	@Override
	public String getDisplayName() {
		return diplayName;
	}
	
}