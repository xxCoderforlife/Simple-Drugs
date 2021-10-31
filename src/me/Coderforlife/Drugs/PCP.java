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

public class PCP implements Listener {

	Drugs D = new Drugs();

	public PCP() {
		return;
	}

	private Main plugin;

	public PCP(Main plugin) {
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
						.equals(D.PCP.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.pcp")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Ketamine")
										.getKeys(false)) {
									String confusion = "Drugs.PCP.CONFUSION";
									String omen = "Drugs.PCP.BAD_OMEN";
									String damageres = "Drugs.PCP.DAMAGE_RESISTANCE";
									int conTime = plugin.getCustomConfig().getInt(confusion + ".Time");
									int conLvl = plugin.getCustomConfig().getInt(confusion + ".Level");
									int omenTime = plugin.getCustomConfig().getInt(omen + ".Time");
									int omenLvl = plugin.getCustomConfig().getInt(omen + ".Level");
									int damageresTime = plugin.getCustomConfig().getInt(damageres + ".Time");
									int damageresLvl = plugin.getCustomConfig().getInt(damageres + ".Level");
									p.addPotionEffect(
											PotionEffectType.CONFUSION.createEffect(conTime * 20, conLvl - 1));
									p.addPotionEffect(
											PotionEffectType.BAD_OMEN.createEffect(omenTime * 20, omenLvl - 1));
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
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use " + D.PCP.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
