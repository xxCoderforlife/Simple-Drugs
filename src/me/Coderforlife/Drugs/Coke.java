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

public class Coke implements Listener {

	Drugs D = new Drugs();

	public Coke() {
		return;
	}

	private Main plugin;

	public Coke(Main plugin) {
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
						.equals(D.Coke.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.coke")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Coke")
										.getKeys(false)) {
									String speed = "Drugs.Coke.SPEED";
									String fastdigging = "Drugs.Coke.FAST_DIGGING";
									String increasedamage = "Drugs.Coke.INCREASE_DAMAGE";
									String healthboost = "Drugs.Coke.HEALTH_BOOST";
									String damageres = "Drugs.Coke.DAMAGE_RESISTANCE";
									int speedTime = plugin.getCustomConfig().getInt(speed + ".Time");
									int speedLvl = plugin.getCustomConfig().getInt(speed + ".Level");
									int fastdigTime = plugin.getCustomConfig().getInt(fastdigging + ".Time");
									int fastdigLvl = plugin.getCustomConfig().getInt(fastdigging + ".Level");
									int damageTime = plugin.getCustomConfig().getInt(increasedamage + ".Time");
									int damageLvl = plugin.getCustomConfig().getInt(increasedamage + ".Level");
									int healTime = plugin.getCustomConfig().getInt(healthboost + ".Time");
									int healLvl = plugin.getCustomConfig().getInt(healthboost + ".Level");
									int damageresTime = plugin.getCustomConfig().getInt(damageres + ".Time");
									int damageresLvl = plugin.getCustomConfig().getInt(damageres + ".Level");
									p.addPotionEffect(
											PotionEffectType.SPEED.createEffect(speedTime * 20, speedLvl - 1));
									p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(fastdigTime * 20,
											fastdigLvl - 1));
									p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(damageTime * 20,
											damageLvl - 1));
									p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE
											.createEffect(damageresTime * 20, damageresLvl - 1));
									p.addPotionEffect(
											PotionEffectType.HEALTH_BOOST.createEffect(healTime * 20, healLvl - 1));
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
								+ D.Coke.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}