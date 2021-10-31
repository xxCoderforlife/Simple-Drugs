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

public class DMT implements Listener {

	Drugs D = new Drugs();

	public DMT() {
		return;
	}

	private Main plugin;

	public DMT(Main plugin) {
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
						.equals(D.DMT.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.dmt")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.DMT")
										.getKeys(false)) {
									String slowfall = "Drugs.DMT.SLOW_FALLING";
									String slow = "Drugs.DMT.SLOW";
									String glowing = "Drugs.DMT.GLOWING";
									String damageres = "Drugs.DMT.DAMAGE_RESISTANCE";
									int slowfallTime = plugin.getCustomConfig().getInt(slowfall + ".Time");
									int slowfallLvl = plugin.getCustomConfig().getInt(slowfall + ".Level");
									int slowTime = plugin.getCustomConfig().getInt(slow + ".Time");
									int slowLvl = plugin.getCustomConfig().getInt(slow + ".Level");
									int glowTime = plugin.getCustomConfig().getInt(glowing + ".Time");
									int glowLvl = plugin.getCustomConfig().getInt(glowing + ".Level");
									int damageTime = plugin.getCustomConfig().getInt(damageres + ".Time");
									int damageLvl = plugin.getCustomConfig().getInt(damageres + ".Level");
									p.addPotionEffect(PotionEffectType.SLOW.createEffect(slowTime * 20, slowLvl - 1));
									p.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(slowfallTime * 20,
											slowfallLvl - 1));
									p.addPotionEffect(
											PotionEffectType.GLOWING.createEffect(glowTime * 20, glowLvl - 1));
									p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(damageTime * 20,
											damageLvl - 1));
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
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use " + D.DMT.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
