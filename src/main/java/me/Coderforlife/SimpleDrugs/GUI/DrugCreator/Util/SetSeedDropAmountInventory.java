package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingInventories.AbstractSDCInventory;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class SetSeedDropAmountInventory extends InventoryUI {

	public SetSeedDropAmountInventory(AbstractSDCInventory asdci) {
		super(18, ChatColor.translateAlternateColorCodes('&', "&6&lSet Seed Drop Amount"));
		
		for(int i = 0; i < 18; i++) {
			final int o = i;
			ItemStack item = new ItemStack(Material.STONE_BUTTON);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "Drop " + String.valueOf(o) + " Drugs");
			item.setItemMeta(im);
			
			addButton(new InventoryButton(item) {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					asdci.getAddons().getOptionValues().put("HarvestAmount", o);
					asdci.getAddons().updateSeedAmountButton();
					asdci.updateAddons();
					asdci.open(p);
				}
			});
		}
		
		updateInventory();
		
	}
	
}