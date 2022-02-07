package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingTypeInventories.FurnaceCraftingInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingTypeInventories.ShapedCraftingInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingTypeInventories.ShapelessCraftingInventory;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class DrugCreatorInventory extends InventoryUI {

	public DrugCreatorInventory(String drugName) {
		super(9, ChatColor.translateAlternateColorCodes('&', "&6&lSelect Crafting Type"));
		
		addButton(new InventoryButton(Material.CRAFTING_TABLE, "&b&lShaped", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				ShapedCraftingInventory sci = new ShapedCraftingInventory(drugName);
				sci.open(p);
			}
		}, 1);
		
		addButton(new InventoryButton(Material.CRAFTING_TABLE, "&b&lShapeless", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				ShapelessCraftingInventory sci = new ShapelessCraftingInventory(drugName);
				sci.open(p);
			}
		}, 3);
		
		addButton(new InventoryButton(Material.FURNACE, "&b&lFurnace", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				FurnaceCraftingInventory sci = new FurnaceCraftingInventory(drugName);
				sci.open(p);
			}
		}, 5);
		
		addButton(new InventoryButton(Material.BREWING_STAND, "&b&lBrewing", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				
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