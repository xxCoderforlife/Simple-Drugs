package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingTypeInventories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.InventoryPotionEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.PotionEffectInventoryUtil;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.PotionEffectRemoveInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.PotionEffectSelectorInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.PotionEffectSetterInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.SetAddictionInventory;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import net.md_5.bungee.api.ChatColor;

public class ShapedCraftingInventory extends PotionEffectSetterInventory {

	private PotionEffectInventoryUtil peiu;
	private ShapedCraftingInventory sci;
	private Map<Integer, ItemStack> items = new HashMap<>();
	private double addLevel = 0.1;
	
	public ShapedCraftingInventory(String drugName) {
		super(27, ChatColor.translateAlternateColorCodes('&', "&6&lCreate Shaped Recipe"));
		setShouldRemove(false);
		
		peiu = new PotionEffectInventoryUtil();
		sci = this;
		
		addAllNullButtons();
		addEffectButton();
		addAddictionButton();
		
		addButton(new InventoryButton(Material.BARRIER, "&4&lRemove Potion Effect", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				if(getPotionEffects().getPotionEffects().size() == 0) return;
				saveItems();
				close(p);
				PotionEffectRemoveInventory peri = new PotionEffectRemoveInventory(sci);
				peri.open(p);
			}
		}, 10);
		
		addButton(new InventoryButton(Material.POTION, "&6&lAdd Potion Effect", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				saveItems();
				close(p);
				PotionEffectSelectorInventory pesi = new PotionEffectSelectorInventory(sci);
				pesi.open(p);
			}
		}, 19);
		
		addButton(new InventoryButton(Material.RED_WOOL, "&4&lCancel", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
			}
		}, 8);
		
		addButton(new InventoryButton(Material.GREEN_WOOL, "&2&lAdd Drug", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
			}
		}, 26);
		
		updateInventory();
	}
	
	private void saveItems() {
		items.clear();
		
		for(int i = 4; i < 7; i++) {
			if(getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR)) continue;
			items.put(i, getInventory().getItem(i));
		}
		for(int i = 12; i < 15; i++) {
			if(getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR)) continue;
			items.put(i, getInventory().getItem(i));
		}
		for(int i = 21; i < 24; i++) {
			if(getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR)) continue;
			items.put(i, getInventory().getItem(i));
		}
	}
	
	private void reApplyItems() {
		items.forEach((k, v) -> {
			getInventory().setItem(k, v);
		});
	}
	
	private void addEffectButton() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lPotion Effects"));
		List<String> lore = new ArrayList<>();
		if(peiu.getPotionEffects().size() == 0) {
			lore.add("No Effects");
		} else {
			for(InventoryPotionEffect ipe : peiu.getPotionEffects()) {
				lore.add(ChatColor.translateAlternateColorCodes('&', "&b&o" + ipe.getType().getName().toLowerCase().replaceAll("_", " ") + " Time: " + "&c&o" + String.valueOf(ipe.getTime()) + " &b&oIntensity: " + "&c&o" + String.valueOf(ipe.getIntensity())));
			}
		}
		im.setLore(lore);
		item.setItemMeta(im);
		
		addButton(new InventoryButton(item) {
			public void onPlayerClick(Player p, ClickAction action) { }
		}, 1);
	}
	
	private void addAddictionButton() {
		ItemStack item = new ItemStack(Material.GUNPOWDER);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lSet Addiction Level"));
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&bCurrent Addiction Level: &c" + String.valueOf(addLevel)));
		im.setLore(lore);
		item.setItemMeta(im);
		
		addButton(new InventoryButton(item) {
			public void onPlayerClick(Player p, ClickAction action) {
				saveItems();
				close(p);
				SetAddictionInventory sai = new SetAddictionInventory(sci);
				sai.open(p);
			}
		}, 9);
	}
	
	private void addAllNullButtons() {
		InventoryButton ib = new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		addButton(ib, 0);
		addButton(ib, 2);
		addButton(ib, 6);
		addButton(ib, 7);
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

	@Override
	public PotionEffectInventoryUtil getPotionEffects() {
		return peiu;
	}

	@Override
	public void updateEffectsButton() {
		clearSlot(1);
		reApplyItems();
		addEffectButton();
		updateInventory();
	}
	
	@Override
	public void updateAddictionButton() {
		clearSlot(9);
		reApplyItems();
		addAddictionButton();
		updateInventory();
	}

	@Override
	public void setAddLevel(double b) {
		addLevel = b;
	}
	
}