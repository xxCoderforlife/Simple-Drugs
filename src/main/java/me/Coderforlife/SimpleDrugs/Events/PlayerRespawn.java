package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    private Main plugin;
    private PlayerJoin pj = new PlayerJoin(plugin);
    private Settings s = new Settings();

    public PlayerRespawn(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev) {
        Player p = ev.getPlayer();
        if(s.BagOfDrugs_GiveOnRespawn) {
            if(p.getInventory().contains(pj.bag))
                return;

            p.getInventory().addItem(pj.bag);
        }
    }
}
