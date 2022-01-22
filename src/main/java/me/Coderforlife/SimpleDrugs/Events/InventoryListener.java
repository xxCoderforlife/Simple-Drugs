package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;
import me.Coderforlife.SimpleDrugs.GUI.RecipeGUI;
import me.Coderforlife.SimpleDrugs.GUI.SettingsGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryListener implements Listener {

    private Main plugin;
    public InventoryListener(Main plugin){
        this.plugin = plugin;
    }
    BagOfDrugsGUI bdg = new BagOfDrugsGUI(plugin);
    SettingsGUI sGui = new SettingsGUI(plugin);
    BagOfDrugsGUI bGui = new BagOfDrugsGUI(plugin);

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        String name = event.getView().getTitle();

        if(name.contains("Recipe")) {
            new RecipeGUI().handleClick(event);
            return;
        }

        if(name.equalsIgnoreCase("§6§lDrugs Settings")) {
            sGui.handleClick(event);
            return;
        }

        if(name.equalsIgnoreCase(bdg.invName)) {
            bGui.handleInventoryClick(event);
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
