package me.Coderforlife.Drugs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.Coderforlife.Drugs.Events.PlayerJoin;

public class KillerCommands implements CommandExecutor {

	private Main plugin;

	public KillerCommands(Main plugin, Drugs D) {
		this.setPlugin(plugin);
		this.D = D;
	}

	PlayerJoin pj = new PlayerJoin();
	Drugs D;

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public static String dash = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "- ";
	public static String header = ChatColor.translateAlternateColorCodes('&',
			"&8&l=============&4&oSIMPLE DRUGS&8&l=============");

	@Override
	public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("drugs.main")) {
				if (args.length == 0) {
					p.sendMessage(header);
					p.sendMessage(ChatColor.ITALIC + "Use these following commands.");
					p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.WHITE + "/drugs help" + ChatColor.GRAY + "|"
							+ ChatColor.ITALIC + " Basic How-to");
					p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.WHITE + "/drugs soberup" + ChatColor.GRAY + "|"
							+ ChatColor.ITALIC + " Remove all effects.");
					p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.WHITE + "/drugs list" + ChatColor.GRAY + "|"
							+ ChatColor.ITALIC + " A list of all the drugs.");
					p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.WHITE + "/drugs bagofdrugs" + ChatColor.GRAY
							+ "|" + ChatColor.ITALIC + " Be given The Bag Of Drugs.");
					p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.WHITE + "/drugs reload" + ChatColor.GRAY + "|"
							+ ChatColor.ITALIC + " Reload the config.");
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						if (p.hasPermission("drugs.help")) {
							p.sendMessage(header);
							p.sendMessage(Main.prefix + "Craft the drugs and Right-Click with in your hand.");
							p.sendMessage(Main.prefix + "Find out how to craft on the Wiki.");
							p.sendMessage("https://xxcoderforlife.gitbook.io/simpledrugswiki/");
						} else {
							p.sendMessage(
									Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
							p.sendMessage(
									Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.help");
						}
					} else if (args[0].equalsIgnoreCase("soberup")) {
						if (p.hasPermission("drugs.soberup")) {
							if (!p.getActivePotionEffects().isEmpty()) {
								for (PotionEffect effect : p.getActivePotionEffects()) {
									p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&a&lSOBERED UP"),
											ChatColor.translateAlternateColorCodes('&', "&l&egood job."), 10, 4 * 20,
											10);
									p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
									p.removePotionEffect(effect.getType());
								}
							} else {
								p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, (float) 0.2);
								// Text | SubText | FadeIn | Stay | FadeOut
								p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&c&lYOU ARE SOBER"),
										ChatColor.translateAlternateColorCodes('&', "&f&oYou need some drugs"), 10,
										4 * 20, 10);
							}
						} else {
							p.sendMessage(
									Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
							p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED
									+ "drugs.soberup");
						}
					} else if (args[0].equalsIgnoreCase("list")) {
						if (p.hasPermission("drugs.list")) {
							p.sendMessage(header);
							for (Drug drug : D.getAllDrugs()) {
								p.sendMessage(dash + drug.getDisplayName());
							}

						} else {
							p.sendMessage(
									Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
							p.sendMessage(
									Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.list");
						}

					} else if (args[0].equalsIgnoreCase("bagofdrugs")) {
						if (p.hasPermission("drugs.command.bagofdrugs")) {
							if (!p.getInventory().contains(pj.bag)) {
								p.sendMessage(Main.prefix + ChatColor.GRAY + "You've Been Given The Bag Of Drugs");
								p.getInventory().addItem(pj.bag);
							} else {
								p.sendMessage(Main.prefix + ChatColor.RED + "You already have the bag");
							}
						} else {
							p.sendMessage(
									Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
							p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED
									+ "drugs.command.bagofdrugs");
						}
					} else if (args[0].equalsIgnoreCase("reload")) {
						if (p.hasPermission("drugs.reload")) {
							p.sendMessage(header);
							p.sendMessage(Main.prefix + "Reloading Config...");
							try {
								plugin.drugsConfig.load(plugin.drugsConfigFile);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} catch (InvalidConfigurationException e) {
								e.printStackTrace();
							}
							p.sendMessage(Main.prefix + "Reloaded Config.");
						} else {
							p.sendMessage(
									Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
							p.sendMessage(
									Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.reload");
						}
					}
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("bagofdrugs")) {
						if (p.hasPermission("drugs.command.bagofdrugs.others")) {
							for (final Player players : Bukkit.getOnlinePlayers()) {
								if (args[1].equalsIgnoreCase(players.getName())) {
									if (players.isOnline()) {
										if (players.getInventory().contains(this.pj.bag)) {
											p.sendMessage(String.valueOf(Main.prefix) + ChatColor.GRAY
													+ players.getDisplayName() + " Already has a bag");
										} else {
											p.sendMessage(String.valueOf(Main.prefix) + ChatColor.GRAY + "You sent "
													+ players.getDisplayName() + " a " + BagOfDrugs.bagName);
											players.getInventory().addItem(new ItemStack[] { this.pj.bag });
											players.sendMessage(String.valueOf(Main.prefix) + ChatColor.GRAY
													+ p.getDisplayName() + " Sent you a " + BagOfDrugs.bagName);
										}
									} else {
										p.sendMessage(String.valueOf(Main.prefix) + ChatColor.GRAY
												+ "That player is not online.");
									}
								} else {
									p.sendMessage(String.valueOf(Main.prefix) + ChatColor.GRAY
											+ "That player doesn't exsit.");
								}
							}
						} else {
							p.sendMessage(String.valueOf(Main.prefix) + ChatColor.RED
									+ "You don't have permission to use that command.");
							p.sendMessage(String.valueOf(Main.prefix) + ChatColor.DARK_RED + "Permission: "
									+ ChatColor.GRAY + "drugs.command.bagofdrugs.others");
						}
					} else if (args[0].equalsIgnoreCase("soberup")) {
						if (p.hasPermission("drugs.soberup.others")) {
							for (final Player players : Bukkit.getOnlinePlayers()) {
								if (args[1].equalsIgnoreCase(players.getName())) {
									if (players.isOnline()) {
										if (!players.getActivePotionEffects().isEmpty()) {
											for (final PotionEffect effect2 : p.getActivePotionEffects()) {
												players.removePotionEffect(effect2.getType());
											}
											p.sendMessage(String.valueOf(Main.prefix) + ChatColor.GRAY
													+ "You sobered up " + players.getDisplayName());
											players.sendTitle(
													ChatColor.translateAlternateColorCodes('&', "&a&lSOBERED UP"),
													ChatColor.translateAlternateColorCodes('&', "&l&egood job."), 10,
													4 * 20, 10);
											players.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
										} else {
											p.sendMessage(Main.prefix + ChatColor.GRAY + p.getDisplayName()
													+ " is already Sober");
										}
									} else {
										p.sendMessage(Main.prefix + ChatColor.GRAY + "That player isn't online.");
									}
								} else {
									p.sendMessage(Main.prefix + ChatColor.GRAY + "That player doesn't exsit.");
								}
							}
						} else {
							p.sendMessage(
									Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
							p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.DARK_GRAY
									+ "drugs.soberup.others");
						}
					} else if (args[0].equalsIgnoreCase("give")) {
						if (p.hasPermission("drugs.give")) {
							for (Drug drugs : D.getAllDrugs()) {
								if (args[1].equalsIgnoreCase(drugs.getName())) {
									p.getInventory().addItem(drugs.getDrugItem());
									p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
											"&7You've been given "  + drugs.getDisplayName()));
								} else {

								}
							}
						} else {

						}
					}
				} else if (args.length == 3) {
					if (p.hasPermission("durgs.give.others")) {
						if (args[0].equalsIgnoreCase("give")) {
							for (Drug drugs : D.getAllDrugs()) {
								if (args[1].equalsIgnoreCase(drugs.getName())) {
									for (Player players : Bukkit.getOnlinePlayers()) {
										if (args[2].equalsIgnoreCase(players.getName())) {
											p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
													"You sent "  + players.getName() + " " + drugs.getDisplayName()));
											players.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
													p.getDisplayName() + " Sent you some " + drugs.getDisplayName()));
											players.getInventory().addItem(drugs.getDrugItem());
										}else {
											p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
													args[2] + " &cis not a player"));
										}
									}
								}
							}
						}
					} else {
						p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
						p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.DARK_GRAY
								+ "drugs.give.others");
					}
				} else if (args.length > 3) {
					p.sendMessage(Main.prefix + "Don't use " + args[4]);
				}
			} else {
				p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
				p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.main");
			}
		} else if (args.length == 0) {
			sender.sendMessage("Only /drugs reload works in the console");
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(String.valueOf(Main.prefix) + ChatColor.GREEN + "Attempting to reload config...");
				try {
					this.plugin.drugsConfig.load(this.plugin.drugsConfigFile);
				} catch (FileNotFoundException e4) {
					e4.printStackTrace();
				} catch (IOException e5) {
					e5.printStackTrace();
				} catch (InvalidConfigurationException e6) {
					e6.printStackTrace();
				}
				Bukkit.getServer().getConsoleSender()
						.sendMessage(String.valueOf(Main.prefix) + ChatColor.GREEN + "Config has been reloaded");
			} else if (args[0].length() >= 2) {
				final Random sev = new Random();
				final int ef = sev.nextInt(4);
				if (ef == 1) {
					Bukkit.getServer().getConsoleSender().sendMessage(String.valueOf(Main.prefix) + ChatColor.GREEN
							+ "That's all you can do for now. I'm sorry and I'm also not that sorry, because you guys asked me to make this");
				}
				if (ef == 2) {
					Bukkit.getServer().getConsoleSender()
							.sendMessage(String.valueOf(Main.prefix) + ChatColor.GREEN + "Please stop doing that.");
				}
				if (ef == 3) {
					final Logger log = Bukkit.getLogger();
					log.severe("SERVER SHUTTING DOWN....");
					log.severe("Unloading all plugins...");
					log.severe("Sending all your private data to the Goverment");
					log.severe("Deleting all files and cookies...");
					log.severe("Reusing a tissue...");
					log.severe("*cough* *cough* should'nt have done that");
					log.severe("Installing CCLEANER...");
					Bukkit.getServer().getConsoleSender()
							.sendMessage(String.valueOf(Main.prefix) + ChatColor.GREEN + "jK LOL");
				}
				if (ef == 4) {
					Bukkit.getServer().getConsoleSender().sendMessage(
							String.valueOf(Main.prefix) + ChatColor.GREEN + "You must be a very boring person.");
				}
			}
		}
		return true;
	}

}
