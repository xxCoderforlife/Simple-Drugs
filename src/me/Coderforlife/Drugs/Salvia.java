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

public class Salvia implements Listener {

	Drugs D = new Drugs();

	public Salvia() {
		return;
	}

	private Main plugin;

	public Salvia(Main plugin) {
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
						.equals(D.Salvia.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.salvia")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Salvia")
										.getKeys(false)) {
									String regen = "Drugs.Salvia.REGENERATION";
									String weakness = "Drugs.Salvia.WEAKNESS";
									String glowing = "Drugs.Salvia.GLOWING";
									String nightvision = "Drugs.Salvia.NIGHT_VISION";
									int regenTime = plugin.getCustomConfig().getInt(regen + ".Time");
									int regenLvl = plugin.getCustomConfig().getInt(regen + ".Level");
									int weakTime = plugin.getCustomConfig().getInt(weakness + ".Time");
									int weakLvl = plugin.getCustomConfig().getInt(weakness + ".Level");
									int glowTime = plugin.getCustomConfig().getInt(glowing + ".Time");
									int glowLvl = plugin.getCustomConfig().getInt(glowing + ".Level");
									int nightTime = plugin.getCustomConfig().getInt(nightvision + ".Time");
									int nightLvl = plugin.getCustomConfig().getInt(nightvision + ".Level");
									p.addPotionEffect(
											PotionEffectType.REGENERATION.createEffect(regenTime * 20, regenLvl - 1));
									p.addPotionEffect(
											PotionEffectType.WEAKNESS.createEffect(weakTime * 20, weakLvl - 1));
									p.addPotionEffect(
											PotionEffectType.GLOWING.createEffect(glowTime * 20, glowLvl - 1));
									p.addPotionEffect(
											PotionEffectType.NIGHT_VISION.createEffect(nightTime * 20, nightLvl - 1));
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
								+ D.Salvia.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
