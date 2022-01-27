package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
public class PlayerDeath implements Listener {   

    private Main plugin = Main.plugin;
    private PlayerJoin pj = new PlayerJoin();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        if(!(plugin.getSettings().isBagOfDrugs_DropOnDeath())) {
            ev.getDrops().remove(pj.bag);
        }
    }
}
