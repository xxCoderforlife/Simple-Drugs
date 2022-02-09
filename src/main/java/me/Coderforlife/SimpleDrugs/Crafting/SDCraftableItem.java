package me.Coderforlife.SimpleDrugs.Crafting;

import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Util.AbstractSDCraftableManager;

public interface SDCraftableItem {

	public String getNamespaceName();
	public ItemStack getItem();
	public String getDisplayName();
	public String getFile();
	public void setFile(String s);
	public String getName();
	public SDRecipe getRecipe();
	public AbstractSDCraftableManager<?> getManager();
	
}