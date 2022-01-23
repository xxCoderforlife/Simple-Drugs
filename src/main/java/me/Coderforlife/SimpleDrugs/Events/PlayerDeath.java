package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Settings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
public class PlayerDeath implements Listener {   

    private Settings s = new Settings();
    private PlayerJoin pj = new PlayerJoin();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        if(!s.BagOfDrugs_DropOnDeath) {
            ev.getDrops().remove(pj.bag);
        }
    }
}
