package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Crafting.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingComponent.CCCreatorInventory;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;

public abstract class AbstractPlainCrafting extends InventoryUI {

	private String itemName;
	private HashMap<Integer, ItemStack> items;
	
	public AbstractPlainCrafting(String title, String name, HashMap<Integer, ItemStack> i) {
		super(27, title);
		setShouldRemove(false);
		itemName = name;
		items = i;
		
		addNullItems();
		
		if(!i.isEmpty()) reApplyItems();
		
		addButton(new InventoryButton(Material.FEATHER, "&6&lChange Crafting Type", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				CCCreatorInventory dci = new CCCreatorInventory(name, items, getRecipeType());
				dci.open(p);
			}
		}, 10);
		
		addButton(new InventoryButton(Material.RED_WOOL, "&4&lCancel", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
			}
		}, 8);
		
		addButton(new InventoryButton(Material.GREEN_WOOL, "&2&lCreate", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				handleAccept(p);
			}
		}, 26);
		
		updateInventory();
	}
	
	public abstract List<ItemStack> getRecipe();
	public abstract DrugCraftingType getRecipeType();
	
	public abstract void handleAccept(Player p);
	public abstract void addNullItems();
	
	protected boolean isSlotNull(int i) {
		return getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR);
	}
	
	public String getName() {
		return itemName;
	}
	
	private void reApplyItems() {
		items.forEach((k, v) -> {
			getInventory().setItem(k, v);
		});
	}
	
}