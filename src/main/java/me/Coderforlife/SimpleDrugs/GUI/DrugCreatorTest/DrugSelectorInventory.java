package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Druging.DrugEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingTypeInventories.FurnaceCraftingInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingTypeInventories.ShapedCraftingInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingTypeInventories.ShapelessCraftingInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.InventoryPotionEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.PotionEffectInventoryUtil;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class DrugSelectorInventory extends InventoryUI {
	
	public DrugSelectorInventory(boolean editing) {
		super((int)Math.ceil((double)Main.plugin.getDrugManager().getallDrugs().size() / 9.0) * 9, ChatColor.translateAlternateColorCodes('&', "&6&lSelect Drug"));
		
		for(Drug d : Main.plugin.getDrugManager().getallDrugs()) {
			addButton(new InventoryButton(d.getItem()) {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					if(editing) {
						close(p);
						
						String name = d.getDisplayname();
						PotionEffectInventoryUtil peiu = getPEIU(d);
						Double addLevel = d.getAddictionLevel();
						
						if(d.getRecipe() instanceof SDShaped) {
							ShapedCraftingInventory sci = new ShapedCraftingInventory(name, peiu, getStackFromShaped(d, (SDShaped) d.getRecipe()), addLevel);
							sci.open(p);
						} else if(d.getRecipe() instanceof SDShapeless) {
							ShapelessCraftingInventory sci = new ShapelessCraftingInventory(name, peiu, getStackFromShapeless(d, (SDShapeless) d.getRecipe()), addLevel);
							sci.open(p);
						} else if(d.getRecipe() instanceof SDFurnace) {
							FurnaceCraftingInventory sci = new FurnaceCraftingInventory(name, peiu, getStackFromFurnace(d, (SDFurnace) d.getRecipe()), addLevel);
							sci.open(p);
						}
						
					} else {
						close(p);
						DrugDeleteConfirmation ddc = new DrugDeleteConfirmation(d);
						ddc.open(p);
					}
				}
			});
		}
		
		updateInventory();
	}
	
	private PotionEffectInventoryUtil getPEIU(Drug d) {
		PotionEffectInventoryUtil peiu = new PotionEffectInventoryUtil();
		
		for(DrugEffect de : d.getEffects()) {
			InventoryPotionEffect ipe = new InventoryPotionEffect(de.getEffect(), de.getTime(), de.getIntensity());
			peiu.getPotionEffects().add(ipe);
		}
		
		return peiu;
	}
	
	private HashMap<Integer, ItemStack> getStackFromShaped(Drug d, SDShaped shaped) {
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
		items.put(17, new ItemStack(d.getItem().getType()));
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
	
	private HashMap<Integer, ItemStack> getStackFromShapeless(Drug d, SDShapeless recipe) {
		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		for(int i = 0; i < recipe.getItems().size(); i++) {
			items.put(convertNumber(i), recipe.getItems().get(i));
		}
		items.put(17, new ItemStack(d.getItem().getType()));
		return items;
	}
	
	private HashMap<Integer, ItemStack> getStackFromFurnace(Drug d, SDFurnace recipe) {
		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		items.put(4, recipe.getItems().get(0));
		items.put(17, new ItemStack(d.getItem().getType()));
		return items;
	}
	
}