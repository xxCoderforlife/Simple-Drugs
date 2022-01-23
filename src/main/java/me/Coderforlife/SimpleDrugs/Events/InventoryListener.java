package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;
import me.Coderforlife.SimpleDrugs.GUI.RecipeGUI;
import me.Coderforlife.SimpleDrugs.GUI.SettingsGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryListener implements Listener {

    BagOfDrugsGUI BagOfDrugsGUI = new BagOfDrugsGUI();
    SettingsGUI SettingsGUI = new SettingsGUI();
    RecipeGUI RecipeGUI = new RecipeGUI();

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        String name = event.getView().getTitle();

        if(name.contains("Recipe")) {
            RecipeGUI.handleClick(event);
            return;
        }

        if(name.equalsIgnoreCase("§6§lDrugs Settings")) {
            SettingsGUI.handleClick(event);
            return;
        }

        if(name.equalsIgnoreCase(BagOfDrugsGUI.invName)) {
            BagOfDrugsGUI.handleInventoryClick(event);
        }
    }

    @EventHandler
    public void IntentoryDragEvent(InventoryDragEvent event) {
        String name = event.getView().getTitle();

        if(name.contains("Recipe")) {
            new RecipeGUI().handleDrag(event);
        }
    }

}
