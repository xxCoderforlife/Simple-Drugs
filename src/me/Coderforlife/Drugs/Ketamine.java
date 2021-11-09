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

public class Ketamine implements Listener {

	Drugs D = new Drugs();

	public Ketamine() {
		return;
	}

	private Main plugin;

	public Ketamine(Main plugin) {
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
				if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
						.equals(D.Ketamine.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.ketamine")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Ketamine")
										.getKeys(false)) {
									String slowfall = "Drugs.Ketamine.SLOW_FALLING";
									String speed = "Drugs.Ketamine.SPEED";
									String nightvision = "Drugs.Ketamine.NIGHT_VISION";
									String fastdig = "Drugs.Ketamine.FAST_DIGGING";
									String confusion = "Drugs.Ketamine.CONFUSION";
									int slowTime = plugin.getCustomConfig().getInt(slowfall + ".Time");
									int slowLvl = plugin.getCustomConfig().getInt(slowfall = ".Level");
									int speedTime = plugin.getCustomConfig().getInt(speed + ".Time");
									int speedLvl = plugin.getCustomConfig().getInt(speed + ".Level");
									int nightTime = plugin.getCustomConfig().getInt(nightvision + ".Time");
									int nightLvl = plugin.getCustomConfig().getInt(nightvision + ".Level");
									int fastdigTime = plugin.getCustomConfig().getInt(fastdig + ".Time");
									int fastdigLvl = plugin.getCustomConfig().getInt(fastdig + ".Level");
									int conTime = plugin.getCustomConfig().getInt(confusion + ".Time");
									int conLvl = plugin.getCustomConfig().getInt(confusion + ".Level");
									p.addPotionEffect(
											PotionEffectType.SLOW_FALLING.createEffect(slowTime * 20, slowLvl - 1));
									p.addPotionEffect(
											PotionEffectType.SPEED.createEffect(speedTime * 20, speedLvl - 1));
									p.addPotionEffect(
											PotionEffectType.NIGHT_VISION.createEffect(nightTime * 20, nightLvl - 1));
									p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(fastdigTime * 20,
											fastdigLvl - 1));
									p.addPotionEffect(
											PotionEffectType.CONFUSION.createEffect(conTime * 20, conLvl - 1));
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
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use "
								+ D.Ketamine.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
