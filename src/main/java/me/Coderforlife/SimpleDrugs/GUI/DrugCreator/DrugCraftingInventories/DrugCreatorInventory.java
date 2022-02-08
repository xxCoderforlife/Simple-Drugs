package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.PotionUtil.PotionEffectInventoryUtil;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class DrugCreatorInventory extends InventoryUI {

	public DrugCreatorInventory(String drugName, PotionEffectInventoryUtil peiu, HashMap<Integer, ItemStack> items, Double al, DrugCraftingType dct) {
		super(9, ChatColor.translateAlternateColorCodes('&', "&6&lSelect Crafting Type"));
		
		addButton(new InventoryButton(Material.CRAFTING_TABLE, "&b&lShaped", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				if(dct == null || !dct.equals(DrugCraftingType.SHAPED)) items.clear();
				ShapedCraftingInventory sci = new ShapedCraftingInventory(drugName, peiu, items, al);
				sci.open(p);
			}
		}, 1);
		
		addButton(new InventoryButton(Material.CRAFTING_TABLE, "&b&lShapeless", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				if(dct == null || !dct.equals(DrugCraftingType.SHAPELESS)) items.clear();
				ShapelessCraftingInventory sci = new ShapelessCraftingInventory(drugName, peiu, items, al);
				sci.open(p);
			}
		}, 3);
		
		addButton(new InventoryButton(Material.FURNACE, "&b&lFurnace", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				if(dct == null || !dct.equals(DrugCraftingType.FURNACE)) items.clear();
				FurnaceCraftingInventory sci = new FurnaceCraftingInventory(drugName, peiu, items, al);
				sci.open(p);
			}
		}, 5);
		
		addButton(new InventoryButton(Material.BREWING_STAND, "&b&lBrewing", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
			}
		}, 7);
		
		for(int i = 0; i < 5; i++) {
			addButton(new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {}
			});
		}
		
		updateInventory();
	}
	
}