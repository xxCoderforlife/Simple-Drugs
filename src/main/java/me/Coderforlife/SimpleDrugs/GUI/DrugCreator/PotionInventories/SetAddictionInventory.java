package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.PotionInventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingInventories.AbstractSDCInventory;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class SetAddictionInventory extends InventoryUI {

	private AbstractSDCInventory asdci;
	
	public SetAddictionInventory(AbstractSDCInventory inv) {
		super(18, ChatColor.translateAlternateColorCodes('&', "&6&lSet Addication Level"));
		asdci = inv;
		
		addNullButtons();
		
		addButton(new InventoryButton(Material.RED_WOOL, "&4&lCancel", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				asdci.open(p);
			}
		}, 17);
		
		addButton(createAddBtn(0.1));
		addButton(createAddBtn(0.2));
		addButton(createAddBtn(0.3));
		addButton(createAddBtn(0.4));
		addButton(createAddBtn(0.5));
		addButton(createAddBtn(0.6));
		addButton(createAddBtn(0.7));
		addButton(createAddBtn(0.8));
		addButton(createAddBtn(0.9));
		addButton(createAddBtn(1.0));
		
		updateInventory();
	}
	
	private InventoryButton createAddBtn(Double t) {
		ItemStack item = new ItemStack(Material.STONE_BUTTON);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bAddication Level: &c" + String.valueOf(t)));
		item.setItemMeta(im);
		
		InventoryButton ib = new InventoryButton(item) {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				if(asdci.getAddons().getOptionValues().containsKey("AddictionLevel")) asdci.getAddons().getOptionValues().remove("AddictionLevel");
				asdci.getAddons().getOptionValues().put("AddictionLevel", t);
				asdci.getAddons().updateDrugButtons();
				asdci.updateAddons();
				asdci.open(p);
			}
		};
		return ib;
	}
	
	private void addNullButtons() {
		InventoryButton ib = new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		addButton(ib, 9);
		addButton(ib, 10);
		addButton(ib, 11);
		addButton(ib, 12);
		addButton(ib, 14);
		addButton(ib, 15);
		addButton(ib, 16);
	}
	
}