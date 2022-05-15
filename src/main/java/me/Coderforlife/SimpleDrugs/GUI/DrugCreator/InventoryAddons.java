package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Druging.DrugPlants.DrugPlantItem;
import me.Coderforlife.SimpleDrugs.Druging.Util.DrugEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingInventories.AbstractSDCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.PotionInventories.PotionEffectRemoveInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.PotionInventories.PotionSelectorInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.PotionInventories.SetAddictionInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.DrugSelectorInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.InventoryPotionEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.PotionEffectInventoryUtil;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.SDObjectType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.SetSeedDropAmountInventory;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import net.md_5.bungee.api.ChatColor;

public class InventoryAddons {

	private Map<Integer, InventoryButton> optionBtns = new HashMap<>();
	private Map<String, Object> optionValues = new HashMap<>();
	private SDCraftableItem sdci;
	private AbstractSDCInventory asdci;
	private ItemStack drugListItem;
	private ItemStack addictionLevelItem;
	private ItemStack drugPickerItem;
	private DrugCraftingType craftingType;
	private SDObjectType objectType;
	
	public InventoryAddons(AbstractSDCInventory inv, SDCraftableItem item, DrugCraftingType dct, SDObjectType oType) {
		sdci = item;
		asdci = inv;
		craftingType = dct;
		objectType = oType;
	}
	
	public void loadOptionAndValues() {
		if(objectType.equals(SDObjectType.DRUG)) {
			optionBtns.put(10, new InventoryButton(Material.BARRIER, "&4&lRemove Potion Effect", "") {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					asdci.close(p);
					PotionEffectRemoveInventory peri = new PotionEffectRemoveInventory(asdci);
					peri.open(p);
				}
			});
			optionBtns.put(19, new InventoryButton(Material.POTION, "&6&lAdd Potion Effect", "") {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					asdci.close(p);
					PotionSelectorInventory psi = new PotionSelectorInventory(asdci);
					psi.open(p);
				}
			});
		}
		
		if(objectType.equals(SDObjectType.SEED)) {
			optionBtns.put(11, new InventoryButton(Material.STONE_BUTTON, "&6&lSet Drop Amount: " + String.valueOf((Integer)optionValues.get("HarvestAmount")), "") {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					asdci.close(p);
					SetSeedDropAmountInventory ssdai = new SetSeedDropAmountInventory(asdci);
					ssdai.open(p);
				}
			});
		}
		
		if(craftingType.equals(DrugCraftingType.FURNACE)) {
			optionBtns.put(22, new InventoryButton(new ItemStack(Material.COAL)) {
				public void onPlayerClick(Player p, ClickAction action) {}
			});
		}
		
		if(sdci != null) {
			loadOptionsValues(sdci);
		} else {
			createNewOptions();
		}
	}
	
	public void updateSeedButton() {
		if(optionValues.get("Drug") == null) {
			drugPickerItem = new ItemStack(Material.BARRIER);
			ItemMeta meta = drugPickerItem.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "Select Drug");
		} else {
			drugPickerItem = ((Drug)optionValues.get("Drug")).getItem();
		}
		
		optionBtns.put(10, new InventoryButton(drugPickerItem) {
			public void onPlayerClick(Player p, ClickAction action) {
				asdci.close(p);
				DrugSelectorInventory dsi = new DrugSelectorInventory(asdci);
				dsi.open(p);
			}
		});
	}
	
	public void updateSeedAmountButton() {
		optionBtns.put(11, new InventoryButton(Material.STONE_BUTTON, "&6&lSet Drop Amount: " + String.valueOf((Integer)optionValues.get("HarvestAmount")), "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				asdci.close(p);
				SetSeedDropAmountInventory ssdai = new SetSeedDropAmountInventory(asdci);
				ssdai.open(p);
			}
		});
	}
	
	public void updateDrugButtons() {
		drugListItem = new ItemStack(Material.PAPER);
		ItemMeta im = drugListItem.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lPotion Effects"));
		List<String> lore = new ArrayList<>();
		if(((PotionEffectInventoryUtil)optionValues.get("DrugEffects")).getPotionEffects().size() == 0) {
			lore.add("No Effects");
		} else {
			for(InventoryPotionEffect ipe : ((PotionEffectInventoryUtil)optionValues.get("DrugEffects")).getPotionEffects()) {
				lore.add(ChatColor.translateAlternateColorCodes('&', "&b&o" + ipe.getType().getName().toLowerCase().replaceAll("_", " ") + " Time: " + "&c&o" + String.valueOf(ipe.getTime()) + " &b&oIntensity: " + "&c&o" + String.valueOf(ipe.getIntensity())));
			}
		}
		im.setLore(lore);
		drugListItem.setItemMeta(im);
		
		addictionLevelItem = new ItemStack(Material.GUNPOWDER);
		ItemMeta im2 = addictionLevelItem.getItemMeta();
		im2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lSet Addiction Level"));
		List<String> lore2 = new ArrayList<>();
		lore2.add(ChatColor.translateAlternateColorCodes('&', "&bCurrent Addiction Level: &c" + String.valueOf(optionValues.get("AddictionLevel"))));
		im2.setLore(lore2);
		addictionLevelItem.setItemMeta(im2);
		
		optionBtns.put(1, new InventoryButton(drugListItem) {
			public void onPlayerClick(Player p, ClickAction action) { }
		});
		
		optionBtns.put(9, new InventoryButton(addictionLevelItem) {
			public void onPlayerClick(Player p, ClickAction action) {
				asdci.close(p);
				SetAddictionInventory sai = new SetAddictionInventory(asdci);
				sai.open(p);
			}
		});
	}
	
	private void createNewOptions() {
		if(objectType.equals(SDObjectType.DRUG)) {
			optionValues.put("AddictionLevel", 0.1);
			PotionEffectInventoryUtil peiu = new PotionEffectInventoryUtil();
			optionValues.put("DrugEffects", peiu);
			updateDrugButtons();
		} else if(objectType.equals(SDObjectType.SEED)) {
			optionValues.put("Drug", null);
			optionValues.put("OldDrug", null);
			optionValues.put("HarvestAmount", 1);
			updateSeedButton();
		}
	}
	
	private void loadOptionsValues(SDCraftableItem item) {
		if(item instanceof Drug) {
			Drug d = (Drug)item;
			if(d.getEffects().isEmpty()) return;
			optionValues.put("AddictionLevel", d.getAddictionLevel());
			PotionEffectInventoryUtil peiu = new PotionEffectInventoryUtil();
			for(DrugEffect de : d.getEffects()) {
				peiu.getPotionEffects().add(new InventoryPotionEffect(de.getEffect(), de.getTime() / 20, de.getIntensity()));
			}
			optionValues.put("DrugEffects", peiu);
			updateDrugButtons();
		}
		if(item instanceof DrugPlantItem) {
			DrugPlantItem dpi = (DrugPlantItem)item;
			optionValues.put("Drug", dpi.getDrug());
			optionValues.put("OldDrug", dpi.getDrug());
			optionValues.put("HarvestAmount", dpi.getAmount());
			updateSeedButton();
		}
	}
	
	public DrugCraftingType getCraftingType() {
		return craftingType;
	}
	
	public SDCraftableItem getItem() {
		return sdci;
	}
	
	public Map<String, Object> getOptionValues() {
		return optionValues;
	}
	
	public Map<Integer, InventoryButton> getOptionalButtons() {
		return optionBtns;
	}
	
}