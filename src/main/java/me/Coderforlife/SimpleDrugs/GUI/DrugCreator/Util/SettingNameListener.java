package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util;

import java.util.HashMap;
import java.util.concurrent.Callable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingTypeSelector;
import net.md_5.bungee.api.ChatColor;

public class SettingNameListener implements Listener {

	@EventHandler
	public void onCreateName(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(!Main.plugin.getCreatingName().containsKey(p.getUniqueId())) return;
		e.setCancelled(true);
		
		if(Main.plugin.getCreatingName().get(p.getUniqueId()).equals(SDObjectType.DRUG)) {
			Main.plugin.getCreatingName().remove(p.getUniqueId());
			
			String name = ChatColor.translateAlternateColorCodes('&', e.getMessage());
			String a = ChatColor.stripColor(name).replace(" ", "_");
			
			if(Main.plugin.getDrugManager().getItem(a.toUpperCase()) != null) {
				p.sendMessage(ChatColor.RED + "A drug with that name already exists");
				return;
			}
			
			Bukkit.getScheduler().callSyncMethod(Main.plugin, new Callable<CraftingTypeSelector>() {
				@Override
				public CraftingTypeSelector call() throws Exception {
					CraftingTypeSelector cts = new CraftingTypeSelector(name, null, SDObjectType.DRUG, new HashMap<Integer, ItemStack>(), null);
					cts.open(p);
					return cts;
				}
			});
		} else if(Main.plugin.getCreatingName().get(p.getUniqueId()).equals(SDObjectType.CC)) {
			Main.plugin.getCreatingName().remove(p.getUniqueId());
			
			String name = ChatColor.translateAlternateColorCodes('&', e.getMessage());
			String a = ChatColor.stripColor(name).replace(" ", "_");
			
			if(Main.plugin.getCraftingManager().getItem(a.toUpperCase()) != null) {
				p.sendMessage(ChatColor.RED + "A crafting component with that name already exists");
				return;
			}
			
			Bukkit.getScheduler().callSyncMethod(Main.plugin, new Callable<CraftingTypeSelector>() {
				@Override
				public CraftingTypeSelector call() throws Exception {
					CraftingTypeSelector cts = new CraftingTypeSelector(name, null, SDObjectType.CC, new HashMap<Integer, ItemStack>(), null);
					cts.open(p);
					return cts;
				}
			});
		}
	}
	
}