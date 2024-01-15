package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;
import me.Coderforlife.SimpleDrugs.GUI.SettingsGUI;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    BagOfDrugsGUI BagOfDrugsGUI = new BagOfDrugsGUI();
    SettingsGUI SettingsGUI = new SettingsGUI();

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        String name = event.getView().getTitle();

        if(name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&4&o&lSimple&f&o&l-&4&o&lDrugs &6&o&lSettings"))) {
        	SettingsGUI.handleClick(event);
        	return;
        }
        
//        if(name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', 
//        "&4&o&lSimple&f&o&l-&4&o&lDrugs &6&o&lSettings"))) {
//            SettingsGUI.handleClick(event);
//            return;
//        }
    }

}
