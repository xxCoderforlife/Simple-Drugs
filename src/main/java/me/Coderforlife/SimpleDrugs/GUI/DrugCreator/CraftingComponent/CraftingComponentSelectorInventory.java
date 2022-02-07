package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingComponent;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class CraftingComponentSelectorInventory extends InventoryUI {

	public CraftingComponentSelectorInventory(boolean editing) {
		super((int)Math.ceil((double)Main.plugin.getCraftingManager().getCraftingComponents().values().size() / 9.0) * 9, ChatColor.translateAlternateColorCodes('&', "&6&lSelect Crafting Component"));
		
		for(CraftingComponent cc : Main.plugin.getCraftingManager().getCraftingComponents().values()) {
			addButton(new InventoryButton(cc.getItem()) {

				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					if(editing) {
						close(p);
						
						String n = cc.getName();
						if(cc.getRecipe() instanceof SDShaped) {
							CCShapedCraftingInventory csci = new CCShapedCraftingInventory(n, getStackFromShaped(cc, (SDShaped)cc.getRecipe()));
							csci.open(p);
						} else if(cc.getRecipe() instanceof SDShapeless) {
							CCShapelessCraftingInventory csci = new CCShapelessCraftingInventory(n, getStackFromShapeless(cc, (SDShapeless)cc.getRecipe()));
							csci.open(p);
						} else if(cc.getRecipe() instanceof SDFurnace) {
							CCFurnaceCraftingInventory csci = new CCFurnaceCraftingInventory(n, getStackFromFurnace(cc, (SDFurnace) cc.getRecipe()));
							csci.open(p);
						}
						
					} else {
						CCDeleteConfirmation cdc = new CCDeleteConfirmation(cc);
						cdc.open(p);
					}
				}
				
			});
		}
		
		updateInventory();
	}
	
	private HashMap<Integer, ItemStack> getStackFromShaped(CraftingComponent cc, SDShaped shaped) {
		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		items.put(3, shaped.getItems().get(0));
		items.put(4, shaped.getItems().get(1));
		items.put(5, shaped.getItems().get(2));
		items.put(12, shaped.getItems().get(3));
		items.put(13, shaped.getItems().get(4));
		items.put(14, shaped.getItems().get(5));
		items.put(21, shaped.getItems().get(6));
		items.put(22, shaped.getItems().get(7));
		items.put(23, shaped.getItems().get(8));
		items.put(17, new ItemStack(cc.getItem().getType()));
		return items;
	}
	
	private int convertNumber(int i) {
		if(i == 0) return 3;
		else if(i == 1) return 4;
		else if(i == 2) return 5;
		else if(i == 3) return 12;
		else if(i == 4) return 13;
		else if(i == 5) return 14;
		else if(i == 6) return 21;
		else if(i == 7) return 22;
		else if(i == 8) return 23;
		
		return -1;
	}
	
	private HashMap<Integer, ItemStack> getStackFromShapeless(CraftingComponent cc, SDShapeless recipe) {
		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		for(int i = 0; i < recipe.getItems().size(); i++) {
			items.put(convertNumber(i), recipe.getItems().get(i));
		}
		items.put(17, new ItemStack(cc.getItem().getType()));
		return items;
	}
	
	private HashMap<Integer, ItemStack> getStackFromFurnace(CraftingComponent cc, SDFurnace recipe) {
		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		items.put(4, recipe.getItems().get(0));
		items.put(17, new ItemStack(cc.getItem().getType()));
		return items;
	}
	
}