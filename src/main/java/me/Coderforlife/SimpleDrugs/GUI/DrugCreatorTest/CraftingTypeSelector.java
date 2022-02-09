package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories.SDFurnaceCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories.SDShapedCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories.SDShapelessCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.SDObjectType;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class CraftingTypeSelector extends InventoryUI {

	public CraftingTypeSelector(String drugName, SDCraftableItem sdci, SDObjectType sot, Map<Integer, ItemStack> items, DrugCraftingType dct) {
		super(9, ChatColor.translateAlternateColorCodes('&', "&6&lSelect Crafting Type"));
		
		addButton(new InventoryButton(Material.CRAFTING_TABLE, "&b&lShaped", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				if(dct != null) if(dct == DrugCraftingType.FURNACE) items.clear();
				SDShapedCInventory sci = new SDShapedCInventory(drugName, items);
				sci.setType(sot);
				InventoryAddons ia = new InventoryAddons(sci, sdci, DrugCraftingType.SHAPED, sot);
				ia.loadOptionAndValues();
				sci.loadAndSetInventoryAddon(ia);
				sci.open(p);
			}
		}, 1);
		
		addButton(new InventoryButton(Material.CRAFTING_TABLE, "&b&lShapeless", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				if(dct != null) if(dct == DrugCraftingType.FURNACE) items.clear();
				SDShapelessCInventory sci = new SDShapelessCInventory(drugName, items);
				sci.setType(sot);
				InventoryAddons ia = new InventoryAddons(sci, sdci, DrugCraftingType.SHAPELESS, sot);
				ia.loadOptionAndValues();
				sci.loadAndSetInventoryAddon(ia);
				sci.open(p);
			}
		}, 3);
		
		addButton(new InventoryButton(Material.FURNACE, "&b&lFurnace", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				if(dct != null) if(dct != DrugCraftingType.FURNACE) items.clear();
				SDFurnaceCInventory sci = new SDFurnaceCInventory(drugName, items);
				sci.setType(sot);
				InventoryAddons ia = new InventoryAddons(sci, sdci, DrugCraftingType.FURNACE, sot);
				ia.loadOptionAndValues();
				sci.loadAndSetInventoryAddon(ia);
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