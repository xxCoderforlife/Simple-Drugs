package me.Coderforlife.SimpleDrugs.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;

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
		Player p = ev.getEntity();
		if (!plugin.drugsConfig.getBoolean(Main.bagofdrugs + ".DropOnDeath")) {
			if (ev.getDrops().contains(pj.bag)) {
				if (p instanceof Player) {
					ev.getDrops().remove(pj.bag);
				}
			}
		} else {
			return;
		}
	}
}
