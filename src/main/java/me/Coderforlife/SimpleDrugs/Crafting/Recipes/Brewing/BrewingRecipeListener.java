package me.Coderforlife.SimpleDrugs.Crafting.Recipes.Brewing;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;

public class BrewingRecipeListener implements Listener {

	@EventHandler
	public void brewingRecipe(InventoryClickEvent e) {
		Inventory i = e.getClickedInventory();
		if(i == null || i.getType() != InventoryType.BREWING) return;
		if(!(e.getClick().equals(ClickType.LEFT) || e.getClick().equals(ClickType.RIGHT))) return;
		ItemStack item = e.getCurrentItem();
		ItemStack item2 = e.getCursor();
		
		if(e.getClick() == ClickType.RIGHT && item.equals(item2)) return;
		
		e.setCancelled(true);
		
		Player p = (Player) e.getView().getPlayer();
		
		boolean areItemsSame = item.isSimilar(item2);
		ClickType ct = e.getClick();
		
		int firstAmount = item.getAmount();
		int secondAmount = item2.getAmount();
		
		int stack = item.getMaxStackSize();
		int half = firstAmount / 2;
		
		int clickedSlot = e.getSlot();
		
		Bukkit.getConsoleSender().sendMessage(String.valueOf(clickedSlot));
		
		if(ct.equals(ClickType.LEFT)) {
			if(clickedSlot == 0 || clickedSlot == 1 || clickedSlot == 2) {
				if(item == null || (item != null && item.getType().equals(Material.AIR))) {
					ItemStack copy = item2.clone();
					copy.setAmount(1);
					i.setItem(clickedSlot, copy);
					item2.setAmount(item2.getAmount() - 1);
				}
				if(item2 == null || (item2 != null && item2.getType().equals(Material.AIR))) {
					if(item.getType().equals(Material.AIR)) return;
					p.setItemOnCursor(item);
					i.setItem(clickedSlot, null);
				}
			} else {
				if(item == null || (item != null && item.getType().equals(Material.AIR))) {
					p.setItemOnCursor(item);
					i.setItem(clickedSlot, item2);
				} else if(areItemsSame) {
					int used = stack - firstAmount;
					if(secondAmount <= used) {
						item.setAmount(firstAmount + secondAmount);
						p.setItemOnCursor(null);
					} else {
						item2.setAmount(secondAmount - used);
						item.setAmount(firstAmount + used);
						p.setItemOnCursor(item2);
					}
				} else if(!areItemsSame) {
					i.setItem(clickedSlot, item2);
					p.setItemOnCursor(item);
				}
			}
		}
		
//		if(ct.equals(ClickType.LEFT)) {
//			if(item == null || (item != null && item.getType().equals(Material.AIR))) {
//				p.setItemOnCursor(item);
//				i.setItem(clickedSlot, item2);
//			} else if(areItemsSame) {
//				int used = stack - firstAmount;
//				if(secondAmount <= used) {
//					item.setAmount(firstAmount + secondAmount);
//					p.setItemOnCursor(null);
//				} else {
//					item2.setAmount(secondAmount - used);
//					item.setAmount(firstAmount + used);
//					p.setItemOnCursor(item2);
//				}
//			} else if (!areItemsSame) {
//				i.setItem(clickedSlot, item2);
//				p.setItemOnCursor(item);
//			}
//		} else if(ct.equals(ClickType.RIGHT)) {
//			if(item == null || (item != null && item.getType().equals(Material.AIR))) {
//				p.setItemOnCursor(item);
//				i.setItem(clickedSlot, item2);
//			} else if((item != null && item.getType() != Material.AIR) && (item2 != null || (item2 != null && item2.getType().equals(Material.AIR)))) {
//				ItemStack clone = item.clone();
//				clone.setAmount(item.getAmount() % 2 == 0 ? firstAmount - half : firstAmount - half - 1);
//				p.setItemOnCursor(clone);
//				
//				item.setAmount(firstAmount - half);
//			} else if(areItemsSame) {
//				if((firstAmount + 1) <= stack) {
//					item2.setAmount(secondAmount - 1);
//					item.setAmount(firstAmount + 1);
//				}
//			} else if(!areItemsSame) {
//				i.setItem(clickedSlot, item2);
//				p.setItemOnCursor(item);
//			}
//		}
		
		if(((BrewerInventory)i).getIngredient() == null) return;
		
		BrewingRecipe recipe = Main.plugin.getRecipeManager().getBrewingRecipe((BrewerInventory) i);
		if(recipe == null) {
			Bukkit.getConsoleSender().sendMessage("No found recipes");
			return;
		}
		recipe.startBrewing((BrewerInventory) i, 100);
	}
	
}