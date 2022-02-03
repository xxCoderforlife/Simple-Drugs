package me.Coderforlife.SimpleDrugs.Druging.Addiction;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Coderforlife.SimpleDrugs.Main;

public class AddictionListener implements Listener{

    private Main plugin = Main.plugin;
    private AddictionManager am = plugin.gAddictionManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev){
        Player p = (Player) ev.getPlayer();
        if(!am.addictionMap().containsKey(p.getUniqueId())){
            am.addictionMap().put(p.getUniqueId(), 0.0);

        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent ev){
        Player p = (Player) ev.getPlayer();
        if(am.addictionMap().containsKey(p.getUniqueId())){
            am.addictionMap().remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev){
        if(!(ev.getEntity() instanceof Player)){
            return;
        }
        Player p = (Player) ev.getEntity();
        if(am.addictionMap().containsKey(p.getUniqueId())){
            am.addictionMap().remove(p.getUniqueId());
        }
    }
}
