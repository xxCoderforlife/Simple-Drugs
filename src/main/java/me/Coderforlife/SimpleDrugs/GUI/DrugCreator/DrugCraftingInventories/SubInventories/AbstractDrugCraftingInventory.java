package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.SubInventories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.DrugCreatorInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.PotionUtil.InventoryPotionEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.PotionUtil.PotionEffectInventoryUtil;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public abstract class AbstractDrugCraftingInventory extends InventoryUI {
	
	private PotionEffectInventoryUtil peiu;
	private AbstractDrugCraftingInventory pesi;
	private HashMap<Integer, ItemStack> items;
	private double addLevel;
	private String drugName;
	
	public AbstractDrugCraftingInventory(int size, String title, String dN, PotionEffectInventoryUtil p, HashMap<Integer, ItemStack> i, double addiction) {
		super(size, title);
		setShouldRemove(false);
		pesi = this;
		peiu = p;
		items = i;
		addLevel = addiction;
		drugName = dN;
		
		addEffectButton();
		addAddictionButton();
		addNullItems();
		
		if(!i.isEmpty()) reApplyItems();
		
		addButton(new InventoryButton(Material.BARRIER, "&4&lRemove Potion Effect", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				if(getPotionEffects().getPotionEffects().size() == 0) return;
				saveItems();
				close(p);
				PotionEffectRemoveInventory peri = new PotionEffectRemoveInventory(pesi);
				peri.open(p);
			}
		}, 10);
		
		addButton(new InventoryButton(Material.FEATHER, "&6&lChange Crafting Type", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				DrugCreatorInventory dci = new DrugCreatorInventory(drugName, peiu, items, addLevel, getRecipeType());
				dci.open(p);
			}
		}, 11);
		
		addButton(new InventoryButton(Material.POTION, "&6&lAdd Potion Effect", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				saveItems();
				close(p);
				PotionEffectSelectorInventory pesi2 = new PotionEffectSelectorInventory(pesi);
				pesi2.open(p);
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
				handleAccept(p);
			}
		}, 26);
		
		updateInventory();
	}
	
	public abstract DrugCraftingType getRecipeType();
	public abstract List<ItemStack> getRecipe();
	
	protected abstract void saveItems();
	protected abstract void addNullItems();
	protected abstract void handleAccept(Player p);
	
	public Map<Integer, ItemStack> getItems() {
		return items;
	}
	
	public PotionEffectInventoryUtil getPotionEffects() {
		return peiu;
	}
	
	public void setAddLevel(double d) {
		addLevel = d;
	}
	
	public double getAddLevel() {
		return addLevel;
	}
	
	public String getDrugName() {
		return drugName;
	}
	
	protected boolean isSlotNull(int i) {
		return getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR);
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
				SetAddictionInventory sai = new SetAddictionInventory(pesi);
				sai.open(p);
			}
		}, 9);
	}
	
	public void updateEffectsButton() {
		clearSlot(1);
		reApplyItems();
		addEffectButton();
		updateInventory();
	}
	
	public void updateAddictionButton() {
		clearSlot(9);
		reApplyItems();
		addAddictionButton();
		updateInventory();
	}
}