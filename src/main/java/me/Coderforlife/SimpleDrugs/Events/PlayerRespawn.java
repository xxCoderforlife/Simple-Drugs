package me.Coderforlife.SimpleDrugs.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.Coderforlife.SimpleDrugs.Main;

public class PlayerRespawn implements Listener {

    private PlayerJoin pj = new PlayerJoin();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev) {
        Player p = ev.getPlayer();
        if(Main.plugin.getSettings().BagOfDrugs_GiveOnRespawn) {
            if(p.getInventory().contains(pj.bag))
                return;

            p.getInventory().addItem(pj.bag);
        }
    }
}
