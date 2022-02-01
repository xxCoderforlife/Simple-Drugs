package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
public class PlayerDeath implements Listener {   

    private Main plugin = Main.plugin;
    BagOfDrugsGUI  bd = new BagOfDrugsGUI();
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        if(!(plugin.getSettings().isBagOfDrugs_DropOnDeath())) {
            ev.getDrops().remove(bd.getBagOfDrugs());
        }
    }
}
