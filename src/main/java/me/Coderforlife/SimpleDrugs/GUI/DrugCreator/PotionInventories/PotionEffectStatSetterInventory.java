package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.PotionInventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingInventories.AbstractSDCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.InventoryPotionEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.PotionEffectInventoryUtil;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class PotionEffectStatSetterInventory extends InventoryUI {

	private int previousTimeSlot = -1;
	private int previousAmpSlot = -1;
	
	private int timeSelected = -1;
	private int ampSelected = -1;
	
	private int time = 0;
	private int inte = 0;
	
	private List<InventoryButton> timeBtns;
	private List<InventoryButton> intBtns;
	
	public PotionEffectStatSetterInventory(PotionEffectType pet, AbstractSDCInventory inv) {
		super(27, ChatColor.translateAlternateColorCodes('&', "&6&lAdd: " + ChatColor.GREEN + pet.getKey().getKey()));
		
		addInfoSigns();
		timeBtns = createTimeButtons();
		intBtns = createIntensityButtons();
		
		for(InventoryButton ib : timeBtns) {
			addButton(ib);
		}
		
		for(InventoryButton ib : intBtns) {
			addButton(ib);
		}
		
		addButton(new InventoryButton(Material.RED_WOOL, "&4&lCancel", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				inv.open(p);
			}
		}, 18);
		
		addButton(new InventoryButton(Material.GREEN_WOOL, "&2&lAdd Potion Effect", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				if(time == 0 || inte == 0) return;
				
				((PotionEffectInventoryUtil)inv.getAddons().getOptionValues().get("DrugEffects")).getPotionEffects().add(new InventoryPotionEffect(pet, time, inte));
				close(p);
				inv.getAddons().updateDrugButtons();
				inv.updateAddons();
				inv.open(p);
			}
		}, 26);
		
		addNullButtons();
		
		updateInventory();
	}
	
	private void addNullButtons() {
		InventoryButton ib = new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		for(int i = 0; i < 11; i++) {
			addButton(ib);
		}
	}
	
	private void updateSelected() {
		if(timeSelected != -1) {
			if(previousTimeSlot != -1) {
				clearSlot(previousTimeSlot);
				addButton(timeBtns.get(previousTimeSlot - 1), previousTimeSlot);
			}
			clearSlot(timeSelected);
			ItemStack item = new ItemStack(Material.GREEN_WOOL);
			item.setItemMeta(timeBtns.get(timeSelected - 1).getItem().getItemMeta());
			addButton(new InventoryButton(item) {
				public void onPlayerClick(Player p, ClickAction action) {}
			}, timeSelected);
		}
		
		if(ampSelected != -1) {
			if(previousAmpSlot != -1) {
				clearSlot(previousAmpSlot);
				addButton(intBtns.get(previousAmpSlot - 9), previousAmpSlot);
			}
			clearSlot(ampSelected);
			ItemStack item = new ItemStack(Material.GREEN_WOOL);
			item.setItemMeta(intBtns.get(ampSelected - 9).getItem().getItemMeta());
			addButton(new InventoryButton(item) {
				public void onPlayerClick(Player p, ClickAction action) {}
			}, ampSelected);
		}
		
		updateInventory();
	}
	
	private List<InventoryButton> createTimeButtons() {
		List<InventoryButton> ibs = new ArrayList<>();
		
		for(int i = 1; i < 9; i++) {
			int slot = i;
			int timeVal = i * 30;
			ItemStack timeBtn = new ItemStack(Material.STONE_BUTTON);
			ItemMeta im = timeBtn.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSelect Time: &c" + String.valueOf(timeVal)));
			timeBtn.setItemMeta(im);
			InventoryButton ib = new InventoryButton(timeBtn) {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					previousTimeSlot = timeSelected;
					timeSelected = slot;
					time = timeVal;
					updateSelected();
				}
			};
			ibs.add(ib);
		}
		
		return ibs;
	}
	
	private List<InventoryButton> createIntensityButtons() {
		List<InventoryButton> ibs = new ArrayList<>();
		
		for(int i = 1; i < 5; i++) {
			int slot = i;
			int intVal = i;
			ItemStack timeBtn = new ItemStack(Material.STONE_BUTTON);
			ItemMeta im = timeBtn.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSelect Intensity: &c" + String.valueOf(intVal)));
			timeBtn.setItemMeta(im);
			InventoryButton ib = new InventoryButton(timeBtn) {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					previousAmpSlot = ampSelected;
					ampSelected = slot + 9;
					inte = intVal;
					updateSelected();
				}
			};
			ibs.add(ib);
		}
		
		return ibs;
	}
	
	private void addInfoSigns() {
		ItemStack item = new ItemStack(Material.OAK_SIGN);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aSet Time"));
		item.setItemMeta(im);
		ItemStack item1 = new ItemStack(Material.OAK_SIGN);
		ItemMeta im1 = item1.getItemMeta();
		im1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aSet Intensity"));
		item1.setItemMeta(im1);
		
		addButton(new InventoryButton(item) {
			public void onPlayerClick(Player p, ClickAction action) {}
		}, 0);
		
		addButton(new InventoryButton(item1) {
			public void onPlayerClick(Player p, ClickAction action) {}
		}, 9);
	}
	
}