package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Drugs.DrugSelectorInventory;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;

public class SimpleDrugsEditor extends InventoryUI {

	public SimpleDrugsEditor() {
		super(27, ChatColor.translateAlternateColorCodes('&', "&8&lSimple Drugs Editor"));
		
		addDrugButtons();
		addSeedButtons();
		addComponentButtons();
		
		addButton(new InventoryButton(Material.BARREL, "&c&oClose Menu", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
			}
		}, 17);
		
		for(int i = 0; i < 17; i++) {
			addButton(new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {}
			});
		}
		
		updateInventory();
	}
	
	private void addDrugButtons() {
		addButton(new InventoryButton(Material.NETHER_STAR, "&b&oCreate A New Drug", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				p.sendMessage(ChatColor.GREEN + "Enter Name For Drug Using '&' As Color Codes");
				Main.plugin.getCreatingName().put(p.getUniqueId(), "drug");
			}
		}, 3);
		
		addButton(new InventoryButton(Material.ANVIL, "&b&oEdit A Drug", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				DrugSelectorInventory dsi = new DrugSelectorInventory(true);
				dsi.open(p);
			}
		}, 4);
		
		addButton(new InventoryButton(Material.BARRIER, "&4&o&lDELETE A DRUG", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				DrugSelectorInventory dsi = new DrugSelectorInventory(false);
				dsi.open(p);
			}
		}, 5);
	}
	
	private void addSeedButtons() {
		addButton(new InventoryButton(Material.NETHER_STAR, "&b&oCreate A New Seed", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				
			}
		}, 12);
		
		addButton(new InventoryButton(Material.ANVIL, "&b&oEdit A Seed", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				
			}
		}, 13);
		
		addButton(new InventoryButton(Material.BARRIER, "&4&o&lDELETE A SEED", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				
			}
		}, 14);
	}
	
	private void addComponentButtons() {
		addButton(new InventoryButton(Material.NETHER_STAR, "&b&oCreate A New Crafting Component", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				p.sendMessage(ChatColor.GREEN + "Enter Name For Crafting Component Using '&' As Color Codes");
				Main.plugin.getCreatingName().put(p.getUniqueId(), "cc");
			}
		}, 21);
		
		addButton(new InventoryButton(Material.ANVIL, "&b&oEdit A Crafting Component", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				
			}
		}, 22);
		
		addButton(new InventoryButton(Material.BARRIER, "&4&o&lDELETE A CRAFTING COMPONENT", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				
			}
		}, 23);
	}

}