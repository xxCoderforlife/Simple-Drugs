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

public class Oxy implements Listener {

	Drugs D = new Drugs();

	public Oxy() {
		return;
	}

	private Main plugin;

	public Oxy(Main plugin) {
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
						.equals(D.Oxy.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.oxy")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Oxy")
										.getKeys(false)) {
									String abs = "Drugs.Oxy.ABSORPTION";
									String damageres = "Drugs.Oxy.DAMAGE_RESISTANCE";
									String confusion = "Drugs.Oxy.CONFUSION";
									String glowing = "Drugs.Oxy.GLOWING";
									int glowTime = plugin.getCustomConfig().getInt(glowing + ".Time");
									int glowLvl = plugin.getCustomConfig().getInt(glowing + ".Level");
									int absTime = plugin.getCustomConfig().getInt(abs + ".Time");
									int absLvl = plugin.getCustomConfig().getInt(abs + ".Level");
									int conTime = plugin.getCustomConfig().getInt(confusion + ".Time");
									int conLvl = plugin.getCustomConfig().getInt(confusion + ".Level");
									int damageresTime = plugin.getCustomConfig().getInt(damageres + ".Time");
									int damageresLvl = plugin.getCustomConfig().getInt(damageres + ".Level");

									p.addPotionEffect(
											PotionEffectType.ABSORPTION.createEffect(absTime * 20, absLvl - 1));
									p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE
											.createEffect(damageresTime * 20, damageresLvl - 1));
									p.addPotionEffect(
											PotionEffectType.CONFUSION.createEffect(conTime * 20, conLvl - 1));
									p.addPotionEffect(
											PotionEffectType.GLOWING.createEffect(glowTime * 20, glowLvl - 1));
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
								+ D.Oxy.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
