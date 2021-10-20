package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

public class Percocet implements Listener {

	public String PercocetName = ChatColor.WHITE + "" + ChatColor.BOLD + "PERCOCET";

	public Percocet() {
		return;
	}

	private Main plugin;

	public Percocet(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void RightClickEvent(PlayerInteractEvent ev) {
		Player p = ev.getPlayer();
		Action pa = ev.getAction();

		if (pa.equals(Action.RIGHT_CLICK_AIR) || pa.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (p.getInventory().getItemInMainHand().hasItemMeta()) {
				if (p.hasPermission("drugs.use.percocet")) {
					try {
						if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(PercocetName)) {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								p.addPotionEffect(PotionEffectType.SLOW.createEffect(plugin.drugsConfig.getInt("Core.Drugs.Percocet.Time.SLOW"), 1));
								p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(plugin.drugsConfig.getInt("Core.Drugs.Percocet.Time.CONFUSION"), 1));
								p.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(plugin.drugsConfig.getInt("Core.Drugs.Percocet.Time.NIGHT_VISION"), 1));
								p.addPotionEffect(PotionEffectType.LUCK.createEffect(plugin.drugsConfig.getInt("Core.Drugs.Percocet.Time.LUCK"), 1));
								p.playSound(p.getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 10, 29);
								p.getInventory().getItemInMainHand().getAmount();
								p.getInventory().getItemInMainHand().setAmount(0);
							}
						}
					} catch (Exception e1) {
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Error in the Console");
						Bukkit.getLogger()
								.severe(Main.prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
						e1.printStackTrace();
					}

				} else {
					p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use " + ChatColor.WHITE + ""
							+ ChatColor.BOLD + "PERCOCET");
				}
			}
		}
	}
}
