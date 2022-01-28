package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import me.Coderforlife.SimpleDrugs.Main;

public class RecipeChecker implements Listener {

	@EventHandler
	public void onCraft(PrepareItemCraftEvent e) {
		if(e.getRecipe() == null) return;
		if(e.getRecipe().getResult() == null) return;
		if(!(e.getInventory() instanceof CraftingInventory)) return;
		
		CraftingInventory inv = e.getInventory();
		Recipe r = e.getRecipe();
		ItemStack i = r.getResult();
		
		SDRecipe sdR = Main.plugin.getRecipeManager().getRecipeFromResult(i);
		if(sdR == null) return;
		
		if(sdR instanceof SDShapeless) {
			if(!Main.plugin.getRecipeManager().itemsMatch(inv.getMatrix(), (SDShapeless)sdR)) {
				inv.setResult(null);
			}
		}
	}
	
}