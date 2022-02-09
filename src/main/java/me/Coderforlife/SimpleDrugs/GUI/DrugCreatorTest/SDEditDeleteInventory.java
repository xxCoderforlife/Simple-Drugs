package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.CraftingComponent;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Druging.DrugPlants.DrugPlantItem;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories.AbstractSDCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories.SDFurnaceCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories.SDShapedCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories.SDShapelessCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.SDObjectType;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class SDEditDeleteInventory<E extends SDCraftableItem> extends InventoryUI {
	
	public SDEditDeleteInventory(Collection<E> items, Boolean editing) {
		super((int)Math.ceil((double)items.size() / 9.0) * 9, ChatColor.translateAlternateColorCodes('&', "&6&lSelect Item"));
		
		for(SDCraftableItem d : items) {
			addButton(new InventoryButton(d.getItem()) {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					if(editing) {
						close(p);
						
						String s = d.getDisplayName();
						
						AbstractSDCInventory inv = null;
						DrugCraftingType type = null;
						
						if(d.getRecipe() instanceof SDShaped) {
							inv = new SDShapedCInventory(s, getStackFromShaped((SDShaped) d.getRecipe()));
							type = DrugCraftingType.SHAPED;
						} else if(d.getRecipe() instanceof SDShapeless) {
							inv = new SDShapelessCInventory(s, getStackFromShapeless((SDShapeless) d.getRecipe()));
							type = DrugCraftingType.SHAPELESS;
						} else if(d.getRecipe() instanceof SDFurnace) {
							inv = new SDFurnaceCInventory(s, getStackFromFurnace((SDFurnace) d.getRecipe()));
							type = DrugCraftingType.FURNACE;
						}
						
						SDObjectType sdot = null;
						if(d instanceof Drug) sdot = SDObjectType.DRUG;
						else if(d instanceof DrugPlantItem) sdot = SDObjectType.SEED;
						else if(d instanceof CraftingComponent) sdot = SDObjectType.CC;
						
						inv.setType(sdot);
						InventoryAddons ia = new InventoryAddons(inv, d, type, sdot);
						ia.loadOptionAndValues();
						inv.loadAndSetInventoryAddon(ia);
						inv.open(p);
						
					} else {
						close(p);
						SDDeleteConfirmationInventory ddc = new SDDeleteConfirmationInventory(d);
						ddc.open(p);
					}
				}
			});
		}
		
		updateInventory();
		
	}
	
	private HashMap<Integer, ItemStack> getStackFromShaped(SDShaped shaped) {
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
		items.put(17, new ItemStack(shaped.getResult().getType()));
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
	
	private HashMap<Integer, ItemStack> getStackFromShapeless(SDShapeless recipe) {
		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		for(int i = 0; i < recipe.getItems().size(); i++) {
			items.put(convertNumber(i), recipe.getItems().get(i));
		}
		items.put(17, new ItemStack(recipe.getResult().getType()));
		return items;
	}
	
	private HashMap<Integer, ItemStack> getStackFromFurnace(SDFurnace recipe) {
		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		items.put(4, recipe.getItems().get(0));
		items.put(17, new ItemStack(recipe.getResult().getType()));
		return items;
	}
	
}