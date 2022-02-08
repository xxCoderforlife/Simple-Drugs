package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories;

import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.InventoryOptionButton;
import net.md_5.bungee.api.ChatColor;

public class SDShapedCInventory extends AbstractSDCInventory {

	public SDShapedCInventory(String name, Map<Integer, ItemStack> i, Map<Integer, InventoryOptionButton> options, Map<Integer, Object> optionVals) {
		super(ChatColor.translateAlternateColorCodes('&', "&6&lCreate Shaped Recipe"), name, i, options, optionVals);
	}

	public void handleAccept(Player p) {
		close(p);
	}

	public void handleUpdate(int i) {
		
	}

	public void setCraftingSlots() {
		return;
	}
	
}