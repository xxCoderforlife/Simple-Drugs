package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
public class PlayerDeath implements Listener {   
    
    private Main plugin;
    private Settings s = new Settings();
    private PlayerJoin pj = new PlayerJoin(plugin);

    public PlayerDeath(Main plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        if(!s.BagOfDrugs_DropOnDeath) {
            ev.getDrops().remove(pj.bag);
        }
    }
}
