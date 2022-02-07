package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import net.md_5.bungee.api.ChatColor;

public class CCFurnaceCraftingInventory extends AbstractPlainCrafting {

	public CCFurnaceCraftingInventory(String name, HashMap<Integer, ItemStack> i) {
		super(ChatColor.translateAlternateColorCodes('&', "&6&lCreate Furnace Recipe"), name, i);
	}

	@Override
	public void handleAccept(Player p) {
		if(getInventory().getItem(4) == null || getInventory().getItem(4).getType().equals(Material.AIR)) return;
		if(getInventory().getItem(17) == null || getInventory().getItem(17).getType().equals(Material.AIR)) return;
		Main.plugin.getCraftingManager().createCraftingComponent(getName(), getInventory().getItem(17), getRecipeType(), getRecipe());
		close(p);
	}

	@Override
	public void addNullItems() {
		InventoryButton ib = new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		addButton(ib, 0);
		addButton(ib, 1);
		addButton(ib, 2);
		addButton(ib, 3);
		addButton(ib, 5);
		addButton(ib, 6);
		addButton(ib, 7);
		addButton(ib, 9);
		addButton(ib, 11);
		addButton(ib, 12);
		addButton(ib, 13);
		addButton(ib, 14);
		addButton(ib, 15);
		
		ItemStack sign = new ItemStack(Material.OAK_SIGN);
		ItemMeta im = sign.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&lINFO"));
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN + "Place Item To Represent Crafting Component Here");
		lore.add(ChatColor.GREEN + "                              -->");
		im.setLore(lore);
		sign.setItemMeta(im);
		
		addButton(new InventoryButton(sign) {
			public void onPlayerClick(Player p, ClickAction action) {}
		}, 16);
		addButton(ib, 18);
		addButton(ib, 19);
		addButton(ib, 20);
		addButton(ib, 21);
		
		ItemStack item = new ItemStack(Material.COAL);
		addButton(new InventoryButton(item) {
			public void onPlayerClick(Player p, ClickAction action) {}
		}, 22);
		
		addButton(ib, 23);
		addButton(ib, 24);
		addButton(ib, 25);
	}

	@Override
	public List<ItemStack> getRecipe() {
		List<ItemStack> items = new ArrayList<>();
		items.add(getInventory().getItem(4));
		return items;
	}

	@Override
	public DrugCraftingType getRecipeType() {
		return DrugCraftingType.FURNACE;
	}
	
}