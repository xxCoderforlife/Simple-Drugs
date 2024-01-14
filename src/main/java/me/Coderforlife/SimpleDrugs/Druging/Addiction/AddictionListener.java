package me.Coderforlife.SimpleDrugs.Druging.Addiction;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Util.Messages;
import net.md_5.bungee.api.ChatColor;

public class AddictionListener implements Listener{

    private Main plugin = Main.plugin;
    private AddictionManager am = plugin.getAddictionManager();
    private HashMap<UUID,Double> addic = am.addictionMap();
    private Messages m = plugin.getMessages();

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
        Player p = (Player) ev.getEntity();
        if(am.addictionMap().containsKey(p.getUniqueId())){
            Double addLvl = addic.get(p.getUniqueId());
            if(addLvl >= 3){
                ev.setDeathMessage("SD " + p.getDisplayName() + 
                ChatColor.translateAlternateColorCodes('&', " &f&ljust ODed."));
            }
            am.addictionMap().remove(p.getUniqueId());
        }
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent ev){
        Player p = (Player) ev.getPlayer();
        if(!am.addictionMap().containsKey(p.getUniqueId())){
            am.addictionMap().put(p.getUniqueId(), 0.0);

        }
    }
}
