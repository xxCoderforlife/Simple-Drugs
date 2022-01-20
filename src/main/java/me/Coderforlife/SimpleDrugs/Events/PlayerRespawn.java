package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    public static PlayerJoin pj = new PlayerJoin();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev) {
        Player p = ev.getPlayer();
        if(Settings.BagOfDrugs_GiveOnRespawn) {
            if(p.getInventory().contains(pj.bag))
                return;

            p.getInventory().addItem(pj.bag);
        }
    }
}
