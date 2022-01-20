package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Settings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        if(!Settings.BagOfDrugs_DropOnDeath) {
            ev.getDrops().remove(new PlayerJoin().bag);
        }
    }
}
