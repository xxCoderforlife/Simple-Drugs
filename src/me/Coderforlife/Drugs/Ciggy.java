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

public class Ciggy implements Listener {

	Drugs D = new Drugs();

	public Ciggy() {
		return;
	}

	private Main plugin;

	public Ciggy(Main plugin) {
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
						.equals(D.Ciggy.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.ciggy")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Ciggy")
										.getKeys(false)) {
									String slowdigging = "Drugs.Ciggy.SLOW_DIGGING";
									String jump = "Drugs.Ciggy.JUMP";
									String saturation = "Drugs.Ciggy.SATURATION";
									String damageres = "Drugs.Ciggy.DAMAGE_RESISTANCE";
									int slowTime = plugin.getCustomConfig().getInt(slowdigging + ".Time");
									int slowLvl = plugin.getCustomConfig().getInt(slowdigging + ".Level");
									int jumpTime = plugin.getCustomConfig().getInt(jump + ".Time");
									int jumpLvl = plugin.getCustomConfig().getInt(jump + ".Level");
									int satTime = plugin.getCustomConfig().getInt(saturation + ".Time");
									int satLvl = plugin.getCustomConfig().getInt(saturation + ".Level");
									int damageTime = plugin.getCustomConfig().getInt(damageres + ".Time");
									int damageLvl = plugin.getCustomConfig().getInt(damageres + ".Level");
									p.addPotionEffect(
											PotionEffectType.SLOW_DIGGING.createEffect(slowTime * 20, slowLvl - 1));
									p.addPotionEffect(PotionEffectType.JUMP.createEffect(jumpTime * 20, jumpLvl - 1));
									p.addPotionEffect(
											PotionEffectType.SATURATION.createEffect(satTime * 20, satLvl - 1));
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
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use" + ChatColor.GOLD + ""
								+ ChatColor.BOLD + " CIGGY");
					}
				}
			}
		}
	}
}
