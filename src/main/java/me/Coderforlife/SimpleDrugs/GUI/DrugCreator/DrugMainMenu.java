package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.CraftingComponent;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Druging.DrugPlants.DrugPlantItem;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.SDObjectType;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;

public class DrugMainMenu extends InventoryUI {

	public DrugMainMenu() {
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
				Main.plugin.getCreatingName().put(p.getUniqueId(), SDObjectType.DRUG);
			}
		}, 3);
		
		addButton(new InventoryButton(Material.ANVIL, "&b&oEdit A Drug", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				if(Main.plugin.getDrugManager().getItems().size() == 0) return;
				SDEditDeleteInventory<Drug> sedi = new SDEditDeleteInventory<Drug>(Main.plugin.getDrugManager().getItems().values(), true);
				sedi.open(p);
			}
		}, 4);
		
		addButton(new InventoryButton(Material.BARRIER, "&4&o&lDELETE A DRUG", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				if(Main.plugin.getDrugManager().getItems().size() == 0) return;
				SDEditDeleteInventory<Drug> sedi = new SDEditDeleteInventory<Drug>(Main.plugin.getDrugManager().getItems().values(), false);
				sedi.open(p);
			}
		}, 5);
	}
	
	private void addSeedButtons() {
		addButton(new InventoryButton(Material.NETHER_STAR, "&b&oCreate A New Seed", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				CraftingTypeSelector cts = new CraftingTypeSelector("", null, SDObjectType.SEED, new HashMap<Integer, ItemStack>(), null);
				cts.open(p);
			}
		}, 12);
		
		addButton(new InventoryButton(Material.ANVIL, "&b&oEdit A Seed", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				if(Main.plugin.getDrugSeedManager().getItems().size() == 0) return;
				SDEditDeleteInventory<DrugPlantItem> sedi = new SDEditDeleteInventory<DrugPlantItem>(Main.plugin.getDrugSeedManager().getItems().values(), true);
				sedi.open(p);
			}
		}, 13);
		
		addButton(new InventoryButton(Material.BARRIER, "&4&o&lDELETE A SEED", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				if(Main.plugin.getDrugSeedManager().getItems().size() == 0) return;
				SDEditDeleteInventory<DrugPlantItem> sedi = new SDEditDeleteInventory<DrugPlantItem>(Main.plugin.getDrugSeedManager().getItems().values(), false);
				sedi.open(p);
			}
		}, 14);
	}
	
	private void addComponentButtons() {
		addButton(new InventoryButton(Material.NETHER_STAR, "&b&oCreate A New Crafting Component", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				p.sendMessage(ChatColor.GREEN + "Enter Name For Crafting Component Using '&' As Color Codes");
				Main.plugin.getCreatingName().put(p.getUniqueId(), SDObjectType.CC);
			}
		}, 21);
		
		addButton(new InventoryButton(Material.ANVIL, "&b&oEdit A Crafting Component", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				if(Main.plugin.getCraftingManager().getItems().size() == 0) return;
				SDEditDeleteInventory<CraftingComponent> sedi = new SDEditDeleteInventory<CraftingComponent>(Main.plugin.getCraftingManager().getItems().values(), true);
				sedi.open(p);
			}
		}, 22);
		
		addButton(new InventoryButton(Material.BARRIER, "&4&o&lDELETE A CRAFTING COMPONENT", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				if(Main.plugin.getCraftingManager().getItems().size() == 0) return;
				SDEditDeleteInventory<CraftingComponent> sedi = new SDEditDeleteInventory<CraftingComponent>(Main.plugin.getCraftingManager().getItems().values(), false);
				sedi.open(p);
			}
		}, 23);
	}
	
}
