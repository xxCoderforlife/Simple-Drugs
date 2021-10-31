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

public class Flakka implements Listener {

	Drugs D = new Drugs();

	public Flakka() {
		return;
	}

	private Main plugin;

	public Flakka(Main plugin) {
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
						.equals(D.Flakka.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.flakka")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Flakka")
										.getKeys(false)) {
									String speed = "Drugs.Flakka.SPEED";
									String unluck = "Drugs.Flakka.UNLUCK";
									String grace = "Drugs.Flakka.DOLPHINS_GRACE";
									String damageres = "Drugs.Flakka.DAMAGE_RESISTANCE";
									int speedTime = plugin.getCustomConfig().getInt(speed + ".Time");
									int speedLvl = plugin.getCustomConfig().getInt(speed + ".Level");
									int unluckTime = plugin.getCustomConfig().getInt(unluck + ".Time");
									int unluckLvl = plugin.getCustomConfig().getInt(unluck + ".Level");
									int graceTime = plugin.getCustomConfig().getInt(grace + ".Time");
									int graceLvl = plugin.getCustomConfig().getInt(grace + ".Level");
									int damageresTime = plugin.getCustomConfig().getInt(damageres + ".Time");
									int damageresLvl = plugin.getCustomConfig().getInt(damageres + ".Level");
									p.addPotionEffect(
											PotionEffectType.SPEED.createEffect(speedTime * 20, speedLvl - 1));
									p.addPotionEffect(
											PotionEffectType.UNLUCK.createEffect(unluckTime * 20, unluckLvl - 1));
									p.addPotionEffect(
											PotionEffectType.DOLPHINS_GRACE.createEffect(graceTime * 20, graceLvl - 1));
									p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE
											.createEffect(damageresTime * 20, damageresLvl - 1));
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
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use " + D.Flakka.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
