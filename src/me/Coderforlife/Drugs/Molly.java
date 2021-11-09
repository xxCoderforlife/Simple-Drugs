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

public class Molly implements Listener {

	Drugs D = new Drugs();

	public Molly() {
		return;
	}

	private Main plugin;

	public Molly(Main plugin) {
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
						.equals(D.Molly.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.molly")) {
						try {
							ItemStack hand = p.getInventory().getItemInMainHand();
							int amount = hand.getAmount();
							if (amount > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Molly")
										.getKeys(false)) {
									String confusion = "Drugs.Molly.CONFUSION";
									String fastdig = "Drugs.Molly.FAST_DIGGING";
									String speed = "Drugs.Molly.SPEED";
									String fireres = "Drugs.Molly.FIRE_RESISTANCE";
									String nightvision = "Drugs.Molly.NIGHT_VISION";
									int conTime = plugin.getCustomConfig().getInt(confusion + ".Time");
									int conLvl = plugin.getCustomConfig().getInt(confusion + ".Level");
									int fastdigTime = plugin.getCustomConfig().getInt(fastdig + ".Time");
									int fastdigLvl = plugin.getCustomConfig().getInt(fastdig + ".Level");
									int speedTime = plugin.getCustomConfig().getInt(speed + ".Time");
									int speedLvl = plugin.getCustomConfig().getInt(speed + ".Level");
									int fireresTime = plugin.getCustomConfig().getInt(fireres + ".Time");
									int fireresLvl = plugin.getCustomConfig().getInt(fireres + ".Level");
									int nightTime = plugin.getCustomConfig().getInt(nightvision + ".Time");
									int nightLvl = plugin.getCustomConfig().getInt(nightvision + ".Level");
									p.getInventory().getItemInMainHand().setAmount(0);
									p.addPotionEffect(
											PotionEffectType.CONFUSION.createEffect(conTime * 20, conLvl - 1));
									p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(fastdigTime * 20,
											fastdigLvl - 1));
									p.addPotionEffect(
											PotionEffectType.SPEED.createEffect(speedTime * 20, speedLvl - 1));
									p.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(fireresTime * 20,
											fireresLvl - 1));
									p.addPotionEffect(
											PotionEffectType.NIGHT_VISION.createEffect(nightTime * 20, nightLvl - 1));
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
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use "
								+ D.Molly.getItemMeta().getDisplayName());

					}
					// END OF MOLLY
				}

			}
		}
	}
}
