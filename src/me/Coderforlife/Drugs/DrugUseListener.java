package me.Coderforlife.Drugs;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * A listener to handle clicking on any registered drug.
 */
public class DrugUseListener implements Listener {
	
	private final List<Drug> availableDrugs;
	private final Main plugin;
	
	public DrugUseListener(Main plugin, List<Drug> availableDrugs) {
		this.availableDrugs = availableDrugs;
		this.plugin = plugin;
	}
	
	/**
	 * Tries to match a right clicked item with a drug item.
	 * Influences the player with the drug if the item was one.
	 */
	@EventHandler
	public void RightClickEvent(PlayerInteractEvent ev) {
		Player p = ev.getPlayer();
		Action pa = ev.getAction();
		
		if (!pa.equals(Action.RIGHT_CLICK_AIR) && !pa.equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		ItemStack itemInHand = p.getInventory().getItemInMainHand();
		Drug drug = matchDrug(itemInHand);
		
		if (null == drug || !p.hasPermission(drug.getUsePermission())) {
			return;
		}
		if (itemInHand.getAmount() > 1) {
			p.sendMessage(Main.prefix + Main.stack);
			return;
		}
		drug.influencePlayer(p, plugin.getCustomConfig());
		p.playSound(p.getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 10, 29);
		itemInHand.getAmount();
		itemInHand.setAmount(0);
	}
	
	private Drug matchDrug(ItemStack item) {
		for (Drug drug : availableDrugs) {
			if (drug.isDrugItem(item)) {
				return drug;
			}
		}
		return null;
	}
}
