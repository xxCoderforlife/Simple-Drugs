package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.AbstractPlainCrafting;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import net.md_5.bungee.api.ChatColor;

public class CCShapelessCraftingInventory extends AbstractPlainCrafting {

	public CCShapelessCraftingInventory(String name, HashMap<Integer, ItemStack> i) {
		super(ChatColor.translateAlternateColorCodes('&', "&6&lCreate Shapeless Recipe"), name, i);
	}

	@Override
	public List<ItemStack> getRecipe() {
		List<ItemStack> items = new ArrayList<>();
		
		items.add(isSlotNull(3) ? new ItemStack(Material.AIR) : getInventory().getItem(3));
		items.add(isSlotNull(4) ? new ItemStack(Material.AIR) : getInventory().getItem(4));
		items.add(isSlotNull(5) ? new ItemStack(Material.AIR) : getInventory().getItem(5));
		items.add(isSlotNull(12) ? new ItemStack(Material.AIR) : getInventory().getItem(12));
		items.add(isSlotNull(13) ? new ItemStack(Material.AIR) : getInventory().getItem(13));
		items.add(isSlotNull(14) ? new ItemStack(Material.AIR) : getInventory().getItem(14));
		items.add(isSlotNull(21) ? new ItemStack(Material.AIR) : getInventory().getItem(21));
		items.add(isSlotNull(22) ? new ItemStack(Material.AIR) : getInventory().getItem(22));
		items.add(isSlotNull(23) ? new ItemStack(Material.AIR) : getInventory().getItem(23));
		
		return items;
	}

	@Override
	public DrugCraftingType getRecipeType() {
		return DrugCraftingType.SHAPELESS;
	}

	@Override
	public void handleAccept(Player p) {
		if(allBlank()) return;
		if(getInventory().getItem(17) == null || getInventory().getItem(17).getType().equals(Material.AIR)) return;
		Main.plugin.getCraftingManager().createCraftingComponent(getName(), getInventory().getItem(17), getRecipeType(), getRecipe());
		close(p);
	}
	
	private boolean allBlank() {
		return getInventory().getItem(3) == null && getInventory().getItem(4) == null && getInventory().getItem(5) == null
				 && getInventory().getItem(12) == null && getInventory().getItem(13) == null && getInventory().getItem(14) == null
						 && getInventory().getItem(21) == null && getInventory().getItem(22) == null && getInventory().getItem(23) == null;
	}


	@Override
	public void addNullItems() {
		InventoryButton ib = new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		addButton(ib, 0);
		addButton(ib, 1);
		addButton(ib, 2);
		addButton(ib, 6);
		addButton(ib, 7);
		addButton(ib, 9);
		addButton(ib, 11);
		addButton(ib, 15);
		addButton(ib, 18);
		addButton(ib, 19);
		addButton(ib, 20);
		addButton(ib, 24);
		addButton(ib, 25);
	}
	
}
