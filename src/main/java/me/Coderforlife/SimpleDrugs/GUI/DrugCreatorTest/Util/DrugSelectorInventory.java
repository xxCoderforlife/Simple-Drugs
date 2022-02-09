package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util;

import org.bukkit.entity.Player;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories.AbstractSDCInventory;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class DrugSelectorInventory extends InventoryUI {

	public DrugSelectorInventory(AbstractSDCInventory inv) {
		super((int)Math.ceil((double)Main.plugin.getDrugManager().getItems().values().size() / 9.0) * 9, ChatColor.translateAlternateColorCodes('&', "&6&lSelect Drug"));
		
		for(Drug d : Main.plugin.getDrugManager().getItems().values()) {
			addButton(new InventoryButton(d.getItem()) {
				public void onPlayerClick(Player p, ClickAction action) {
					close(p);
					inv.getAddons().getOptionValues().remove("Drug");
					inv.getAddons().getOptionValues().put("Drug", d);
					inv.getAddons().updateSeedButton();
					inv.updateAddons();
					inv.open(p);
				}
			});
		}
		
		updateInventory();
	}
	
}