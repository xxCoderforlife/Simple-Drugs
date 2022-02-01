package me.Coderforlife.SimpleDrugs.Crafting.Recipes.Brewing;

import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

public abstract class BrewAction {
	public abstract void Brew(BrewerInventory inv, ItemStack stack, ItemStack ingredient);
}
