package me.Coderforlife.SimpleDrugs.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;

public class PlayerJoin implements Listener {

    private Main plugin = Main.plugin;
    BagOfDrugsGUI bd = new BagOfDrugsGUI();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();
        p.discoverRecipes(Main.plugin.getRecipeManager().getKeys());
        if(plugin.getConfig().getBoolean("Drugs.BagOfDrugs.GiveOnJoin")) {
            if(!p.getInventory().contains(bd.getBagOfDrugs())) {
                p.getInventory().addItem(bd.getBagOfDrugs());
            }
        }
        if(plugin.getSettings().isJoinMessage()) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    p.sendMessage("SD " + "§f§lServer is running §5§l§oSIMPLE DRUGS");
                }
            }, 40L);
        }
    }

    
}
