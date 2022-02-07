package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import java.util.HashMap;
import java.util.concurrent.Callable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingComponent.CCCreatorInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.DrugCreatorInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.PotionUtil.PotionEffectInventoryUtil;
import net.md_5.bungee.api.ChatColor;

public class SettingNameListener implements Listener {

	@EventHandler
	public void onCreateName(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(!Main.plugin.getCreatingName().containsKey(p.getUniqueId())) return;
		e.setCancelled(true);
		
		if(Main.plugin.getCreatingName().get(p.getUniqueId()).equalsIgnoreCase("drug")) {
			Main.plugin.getCreatingName().remove(p.getUniqueId());
			
			String name = ChatColor.translateAlternateColorCodes('&', e.getMessage());
			String a = ChatColor.stripColor(name).replace(" ", "_");
			
			if(Main.plugin.getDrugManager().getDrug(a.toUpperCase()) != null) {
				p.sendMessage(ChatColor.RED + "A drug with that name already exists");
				return;
			}
			
			Bukkit.getScheduler().callSyncMethod(Main.plugin, new Callable<DrugCreatorInventory>() {
				@Override
				public DrugCreatorInventory call() throws Exception {
					DrugCreatorInventory dci = new DrugCreatorInventory(name, new PotionEffectInventoryUtil(), new HashMap<Integer, ItemStack>(), 0.1, null);
					dci.open(p);
					return dci;
				}
			});
		} else if(Main.plugin.getCreatingName().get(p.getUniqueId()).equalsIgnoreCase("cc")) {
			Main.plugin.getCreatingName().remove(p.getUniqueId());
			
			String name = ChatColor.translateAlternateColorCodes('&', e.getMessage());
			String a = ChatColor.stripColor(name).replace(" ", "_");
			
			if(Main.plugin.getCraftingManager().getByName(a.toUpperCase()) != null) {
				p.sendMessage(ChatColor.RED + "A crafting component with that name already exists");
				return;
			}
			
			Bukkit.getScheduler().callSyncMethod(Main.plugin, new Callable<CCCreatorInventory>() {
				@Override
				public CCCreatorInventory call() throws Exception {	
					CCCreatorInventory dci = new CCCreatorInventory(name, new HashMap<Integer, ItemStack>(), null);
					dci.open(p);
					return dci;
				}
			});
		}
	}
	
}