package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.GUI.RecipeGUI;
import me.Coderforlife.SimpleDrugs.GUI.SettingsGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        String name = event.getView().getTitle();

        if(name.contains("Recipe")) {
            new RecipeGUI().handleClick(event);
            return;
        }
        if(name.equalsIgnoreCase("§6§lDrugs Settings")) {
            new SettingsGUI().handleClick(event);
            return;
        }

    }
}
