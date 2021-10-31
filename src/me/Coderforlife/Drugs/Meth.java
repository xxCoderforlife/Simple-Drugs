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

public class Meth implements Listener {

	Drugs D = new Drugs();

	public Meth() {
		return;
	}

	private Main plugin;

	public Meth(Main plugin) {
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
						.equals(D.Meth.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.meth")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Meth")
										.getKeys(false)) {
									String abs = "Drugs.Meth.ABSORPTION";
									String damageres = "Drugs.Meth.DAMAGE_RESISTANCE";
									String fireres = "Drugs.Meth.FIRE_RESISTANCE";
									String lev = "Drugs.Meth.LEVITATION";
									int absTime = plugin.getCustomConfig().getInt(abs + ".Time");
									int absLvl = plugin.getCustomConfig().getInt(abs + ".Level");
									int damageresTime = plugin.getCustomConfig().getInt(damageres + ".Time");
									int damageresLvl = plugin.getCustomConfig().getInt(damageres + ".Level");
									int fireresTime = plugin.getCustomConfig().getInt(fireres + ".Time");
									int fireresLvl = plugin.getCustomConfig().getInt(fireres + ".Level");
									int levTime = plugin.getCustomConfig().getInt(lev + ".Time");
									int levLvl = plugin.getCustomConfig().getInt(lev + ".Level");
									p.addPotionEffect(
											PotionEffectType.ABSORPTION.createEffect(absTime * 20, absLvl - 1));
									p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE
											.createEffect(damageresTime * 20, damageresLvl - 1));
									p.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(fireresTime * 20,
											fireresLvl - 1));
									p.addPotionEffect(
											PotionEffectType.LEVITATION.createEffect(levTime * 20, levLvl - 1));
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
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use " + D.Meth.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
