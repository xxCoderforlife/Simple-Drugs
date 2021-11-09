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

public class Tussin implements Listener {

	Drugs D = new Drugs();

	public Tussin() {
		return;
	}

	private Main plugin;

	public Tussin(Main plugin) {
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
						.equals(D.Tussin.getItemMeta().getDisplayName())) {
					if (p.hasPermission("drugs.use.tussin")) {
						try {
							if (p.getInventory().getItemInMainHand().getAmount() > 1) {
								p.sendMessage(Main.prefix + Main.stack);
							} else {
								for (@SuppressWarnings("unused")
								String enchant : plugin.getCustomConfig().getConfigurationSection("Drugs.Tussin")
										.getKeys(false)) {
									String increasedam = "Drugs.Tussin.INCREASE_DAMAGE";
									String heal = "Drugs.Tussin.REGENERATION";
									String confusion = "Drugs.Tussin.CONFUSION";
									String jumping = "Drugs.Tussin.JUMP";
									int jumpTime = plugin.getCustomConfig().getInt(jumping + ".Time");
									int jumpLvl = plugin.getCustomConfig().getInt(jumping + ".Level");
									int increasedamTime = plugin.getCustomConfig().getInt(increasedam + ".Time");
									int increasedamLvl = plugin.getCustomConfig().getInt(increasedam + ".Level");
									int conTime = plugin.getCustomConfig().getInt(confusion + ".Time");
									int conLvl = plugin.getCustomConfig().getInt(confusion + ".Level");
									int healTime = plugin.getCustomConfig().getInt(heal + ".Time");
									int healLvl = plugin.getCustomConfig().getInt(heal + ".Level");

									p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE
											.createEffect(increasedamTime * 20, increasedamLvl - 1));
									p.addPotionEffect(
											PotionEffectType.REGENERATION.createEffect(healTime * 20, healLvl - 1));
									p.addPotionEffect(
											PotionEffectType.CONFUSION.createEffect(conTime * 20, conLvl - 1));
									p.addPotionEffect(PotionEffectType.JUMP.createEffect(jumpTime * 20, jumpLvl - 1));
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
								+ D.Tussin.getItemMeta().getDisplayName());
					}
				}
			}
		}
	}
}
