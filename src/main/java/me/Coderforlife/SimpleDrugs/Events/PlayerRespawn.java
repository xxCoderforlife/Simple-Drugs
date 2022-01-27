package me.Coderforlife.SimpleDrugs.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.Coderforlife.SimpleDrugs.Main;

public class PlayerRespawn implements Listener {

    private PlayerJoin pj = new PlayerJoin();
    private Main plugin = Main.plugin;

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev) {
        Player p = ev.getPlayer();
        if(plugin.getSettings().isBagOfDrugs_GiveOnRespawn()) {
            if(p.getInventory().contains(pj.bag))
                return;

            p.getInventory().addItem(pj.bag);
        }
    }
}
