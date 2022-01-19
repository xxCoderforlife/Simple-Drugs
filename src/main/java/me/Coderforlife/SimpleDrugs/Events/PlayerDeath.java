package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings.Settings;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener {

    private Main plugin;

    PlayerJoin pj = new PlayerJoin();

    public PlayerDeath(Main plugin) {
        this.setPlugin(plugin);
    }

    public Main getPlugin() {
        return this.plugin;
    }

    public void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    ItemStack handle = new ItemStack(Material.END_CRYSTAL);

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        Settings s = new Settings();
        if(s.BagOfDrugs_DropOnDeath() && ev.getDrops().contains(pj.bag)) {
            ev.getDrops().remove(pj.bag);
        }
    }
}
