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

public class Weed implements Listener {

	Drugs D = new Drugs();

	public Weed() {
		return;
	}

	private Main plugin;

	public Weed(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent ev) {
		Player p = ev.getPlayer();
		Action pa = ev.getAction();

		if (pa.equals(Action.RIGHT_CLICK_AIR) || pa.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (p.getInventory().getItemInMainHand().hasItemMeta()) {
				if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
						.equals(D.WeedStack.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.weed")) {
						try {

							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Weed")
										.getKeys(false)) {
									String slow = "Drugs.Weed.SLOW";
									String slowdig = "Drugs.Weed.SLOW_DIGGING";
									String slowfall = "Drugs.Weed.SLOW_FALLING";
									String luck = "Drugs.Weed.LUCK";
									int slowTime = plugin.getCustomConfig().getInt(slow + ".Time");
									int slowLvl = plugin.getCustomConfig().getInt(slow + ".Level");
									int slowdigTime = plugin.getCustomConfig().getInt(slowdig + ".Time");
									int slowdigLvl = plugin.getCustomConfig().getInt(slowdig + ".Level");
									int slowfallTime = plugin.getCustomConfig().getInt(slowfall + ".Time");
									int slowfallLvl = plugin.getCustomConfig().getInt(slowfall + ".Level");
									int luckTime = plugin.getCustomConfig().getInt(luck + ".Time");
									int luckLvl = plugin.getCustomConfig().getInt(luck + ".Level");
									p.addPotionEffect(PotionEffectType.SLOW.createEffect(slowTime * 20, slowLvl - 1));
									p.addPotionEffect(PotionEffectType.SLOW_DIGGING.createEffect(slowdigTime * 20,
											slowdigLvl - 1));
									p.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(slowfallTime * 20,
											slowfallLvl - 1));
									p.addPotionEffect(PotionEffectType.LUCK.createEffect(luckTime * 20, luckLvl - 1));
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
								+ D.WeedStack.getItemMeta().getDisplayName());
					}
				}
			}
		}

	}
}
