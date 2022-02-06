package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingTypeInventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class ShapedCraftingInventory extends InventoryUI {

	public ShapedCraftingInventory(String drugName) {
		super(27, ChatColor.translateAlternateColorCodes('&', "&6&lCreate Shaped Recipe"));
		
		addAllNullButtons();
		
		addButton(new InventoryButton(Material.PAPER, "&6&lPotion Effects", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				
			}
		}, 1);
		
		addButton(new InventoryButton(Material.POTION, "&6&lAdd Potion Effect", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				
			}
		}, 19);
		
		addButton(new InventoryButton(Material.RED_WOOL, "&4&lCancel", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
			}
		}, 8);
		
		addButton(new InventoryButton(Material.GREEN_WOOL, "&4&lAdd Drug", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
			}
		}, 26);
		
		updateInventory();
	}
	
	private void addEffectButton() {
		
	}
	
	private void addAllNullButtons() {
		InventoryButton ib = new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		addButton(ib, 0);
		addButton(ib, 2);
		addButton(ib, 6);
		addButton(ib, 7);
		addButton(ib, 9);
		addButton(ib, 10);
		addButton(ib, 11);
		addButton(ib, 15);
		
		ItemStack sign = new ItemStack(Material.OAK_SIGN);
		ItemMeta im = sign.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&lINFO"));
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN + "Place Item To Represent Drug Here");
		lore.add(ChatColor.GREEN + "                              -->");
		im.setLore(lore);
		sign.setItemMeta(im);
		
		addButton(new InventoryButton(sign) {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				
			}
		}, 16);
		addButton(ib, 18);
		addButton(ib, 20);
		addButton(ib, 24);
		addButton(ib, 25);
	}
	
}