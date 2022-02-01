package me.Coderforlife.SimpleDrugs.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;

public class PlayerRespawn implements Listener {

    private Main plugin = Main.plugin;
    BagOfDrugsGUI bd = new BagOfDrugsGUI();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev) {
        Player p = ev.getPlayer();
        if(plugin.getSettings().isBagOfDrugs_GiveOnRespawn()) {
            if(p.getInventory().contains(bd.getBagOfDrugs()))
                return;

            p.getInventory().addItem(bd.getBagOfDrugs());
        }
    }
}
