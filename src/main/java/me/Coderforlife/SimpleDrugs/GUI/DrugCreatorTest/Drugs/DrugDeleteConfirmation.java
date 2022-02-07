package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Drugs;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class DrugDeleteConfirmation extends InventoryUI {

	public DrugDeleteConfirmation(Drug d) {
		super(27, ChatColor.translateAlternateColorCodes('&', "&4&lAre You Sure?"));
		
		createNullButtons();
		
		addButton(new InventoryButton(Material.RED_WOOL, "&4&lCancel", "") {
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
			}
		});
		
		addButton(new InventoryButton(d.getItem()) {
			public void onPlayerClick(Player p, ClickAction action) {}
		});
		
		addButton(new InventoryButton(Material.GREEN_WOOL, "&2&lDelete", "") {
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				Main.plugin.getDrugManager().deleteDrug(d);
			}
		});
		
		updateInventory();
	}
	
	private void createNullButtons() {
		InventoryButton ib = new InventoryButton(Material.GRAY_STAINED_GLASS_PANE, " ", "") {
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		for(int i = 0; i < 11; i++) {
			addButton(ib, i);
		}
		
		addButton(ib, 12);
		addButton(ib, 14);
		
		for(int i = 16; i < 27; i++) {
			addButton(ib, i);
		}
	}
	
}