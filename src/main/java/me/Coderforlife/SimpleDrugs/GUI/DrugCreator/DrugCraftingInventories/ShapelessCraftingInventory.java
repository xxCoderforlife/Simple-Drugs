package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.PotionUtil.PotionEffectInventoryUtil;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.SubInventories.AbstractDrugCraftingInventory;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import net.md_5.bungee.api.ChatColor;

public class ShapelessCraftingInventory extends AbstractDrugCraftingInventory {
	
	public ShapelessCraftingInventory(String drugName, PotionEffectInventoryUtil p, HashMap<Integer, ItemStack> i, double addiction) {
		super(27, ChatColor.translateAlternateColorCodes('&', "&6&lCreate Shapeless Recipe"), drugName, p, i, addiction);
	}
	
	protected void handleAccept(Player p) {
		if(allBlank()) return;
		if(getInventory().getItem(17) == null || getInventory().getItem(17).getType().equals(Material.AIR)) return;
		Main.plugin.getDrugManager().addDrug(getDrugName(), getInventory().getItem(17), this);
		close(p);
	}
	
	private boolean allBlank() {
		return getInventory().getItem(3) == null && getInventory().getItem(4) == null && getInventory().getItem(5) == null
				 && getInventory().getItem(12) == null && getInventory().getItem(13) == null && getInventory().getItem(14) == null
						 && getInventory().getItem(21) == null && getInventory().getItem(22) == null && getInventory().getItem(23) == null;
	}
	
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
	
	protected void saveItems() {
		getItems().clear();
		
		for(int i = 4; i < 7; i++) {
			if(getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR)) continue;
			getItems().put(i, getInventory().getItem(i));
		}
		for(int i = 12; i < 15; i++) {
			if(getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR)) continue;
			getItems().put(i, getInventory().getItem(i));
		}
		for(int i = 21; i < 24; i++) {
			if(getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR)) continue;
			getItems().put(i, getInventory().getItem(i));
		}
	}
	
	protected void addNullItems() {
		InventoryButton ib = new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		addButton(ib, 0);
		addButton(ib, 2);
		addButton(ib, 6);
		addButton(ib, 7);
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
	public DrugCraftingType getRecipeType() {
		return DrugCraftingType.SHAPELESS;
	}
	
}