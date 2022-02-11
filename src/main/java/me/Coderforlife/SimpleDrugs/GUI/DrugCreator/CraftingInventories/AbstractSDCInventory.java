package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingInventories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingTypeSelector;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.InventoryAddons;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.SDObjectType;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public abstract class AbstractSDCInventory extends InventoryUI {

	private Map<Integer, ItemStack> items;
	private InventoryAddons inventoryAddons;
	private String name;
	private SDObjectType objectType;
	
	public AbstractSDCInventory(String title, String n, Map<Integer, ItemStack> i) {
		super(27, title);
		setShouldRemove(false);
		items = i;
		name = n;
		addNullItems();
		reApplyItems();
		setCraftingSlots();
		
		addButton(new InventoryButton(Material.FEATHER, "&6&lChange Crafting Type", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				CraftingTypeSelector cts = new CraftingTypeSelector(n, inventoryAddons.getItem(), objectType, i, inventoryAddons.getCraftingType());
				cts.open(p);
			}
		}, 11);
		
		addButton(new InventoryButton(Material.RED_WOOL, "&4&lCancel", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
			}
		}, 8);
		
		addButton(new InventoryButton(Material.GREEN_WOOL, "&2&lAdd Item", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				handleAccept(p);
			}
		}, 26);
		
		updateInventory();
	}
	
	public abstract void handleAccept(Player p);
	public abstract void setCraftingSlots();
	public abstract List<String> getRecipe();
	
	public void updateAddons() {
		if(!inventoryAddons.getOptionalButtons().isEmpty()) {
			inventoryAddons.getOptionalButtons().forEach((k, v) -> {
				clearSlot(k);
				addButton(v, k);
			});
		}
		updateInventory();
	}
	
	public void loadAndSetInventoryAddon(InventoryAddons ai) {
		inventoryAddons = ai;
		if(!inventoryAddons.getOptionalButtons().isEmpty()) {
			inventoryAddons.getOptionalButtons().forEach((k, v) -> {
				clearSlot(k);
				addButton(v, k);
			});
		}
		
		updateInventory();
	}
	
	public SDObjectType getType() {
		return objectType;
	}
	
	public void setType(SDObjectType oType) {
		objectType = oType;
	}
	
	public InventoryAddons getAddons() {
		return inventoryAddons;
	}
	
	public String getName() {
		return name;
	}
	
	protected boolean allBlank() {
		return isSlotNull(3) && isSlotNull(4) && isSlotNull(5)
				 && isSlotNull(12) && isSlotNull(13) && isSlotNull(14)
						 && isSlotNull(21) && isSlotNull(22) && isSlotNull(23);
	}
	
	protected boolean isSlotNull(int i) {
		return getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR);
	}
	
	protected void addNullItem(int i) {
		InventoryButton ib = new InventoryButton(Material.GRAY_STAINED_GLASS_PANE, " ", "") {
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		addButton(ib, i);
	}
	
	private void reApplyItems() {
		items.forEach((k, v) -> {
			getInventory().setItem(k, v);
		});
	}
	
	
	private void addNullItems() {
		addNullItem(0);
		addNullItem(1);
		addNullItem(2);
		addNullItem(6);
		addNullItem(7);
		addNullItem(9);
		addNullItem(10);
		addNullItem(15);
		
		ItemStack sign = new ItemStack(Material.OAK_SIGN);
		ItemMeta im = sign.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&lINFO"));
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN + "Place ItemStack To Represent Item Here");
		lore.add(ChatColor.GREEN + "                              -->");
		im.setLore(lore);
		sign.setItemMeta(im);
		
		addButton(new InventoryButton(sign) {
			public void onPlayerClick(Player p, ClickAction action) {}
		}, 16);
		
		addNullItem(18);
		addNullItem(19);
		addNullItem(20);
		addNullItem(24);
		addNullItem(25);
	}
	
}