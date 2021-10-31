package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Acid implements Listener {

	Drugs D = new Drugs();

	public Acid() {
		return;
	}

	private Main plugin;

	public Acid(Main plugin) {
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
						.equals(D.Acid.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.acid")) {
						try {
							ItemStack hand = p.getInventory().getItemInMainHand();
							int amount = hand.getAmount();
							if (amount > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Acid")
										.getKeys(false)) {
									String confusion = "Drugs.Acid.CONFUSION";
									String healthboost = "Drugs.Acid.HEALTH_BOOST";
									String nightvision = "Drugs.Acid.NIGHT_VISION";
									String slowfalling = "Drugs.Acid.SLOW_FALLING";
									int conTime = plugin.getCustomConfig().getInt(confusion + ".Time");
									int conLvl = plugin.getCustomConfig().getInt(confusion + ".Level");
									int healthTime = plugin.getCustomConfig().getInt(healthboost + ".Time");
									int healthLvl = plugin.getCustomConfig().getInt(healthboost + ".Level");
									int nightTime = plugin.getCustomConfig().getInt(nightvision + ".Time");
									int nightLvl = plugin.getCustomConfig().getInt(nightvision + ".Level");
									int fallingTime = plugin.getCustomConfig().getInt(slowfalling + ".Time");
									int fallingLvl = plugin.getCustomConfig().getInt(slowfalling + ".Level");
									p.getInventory().getItemInMainHand().setAmount(0);
									p.addPotionEffect(
											PotionEffectType.CONFUSION.createEffect(conTime * 20, conLvl - 1));
									p.addPotionEffect(
											PotionEffectType.HEALTH_BOOST.createEffect(healthTime * 20, healthLvl - 1));
									p.addPotionEffect(
											PotionEffectType.NIGHT_VISION.createEffect(nightTime * 20, nightLvl - 1));
									p.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(fallingTime * 20,
											fallingLvl - 1));
									p.playSound(p.getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 10, 29);

								}

							}
						} catch (Exception e1) {
							p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Error in the Console");
							Bukkit.getLogger().severe(Main.prefix + ChatColor.GREEN
									+ "Send this Error to xxCoderforlife on https://Spigotmc.org");
							e1.printStackTrace();
						}
					} else {
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use " + D.Acid.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
