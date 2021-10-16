package me.Coderforlife.Drugs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class KillerCommands implements CommandExecutor {

	private Main plugin;

	public KillerCommands(Main plugin) {
		this.setPlugin(plugin);
	}

	PlayerJoin pj = new PlayerJoin();
	Weed weed = new Weed();
	Coke coke = new Coke();
	Acid acid = new Acid();
	Heroin heroin = new Heroin();
	Molly molly = new Molly();
	Percocet percocet = new Percocet();
	Ciggy ciggy = new Ciggy();

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public static String dash = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "- ";
	public static String header = ChatColor.DARK_RED + "" + ChatColor.BOLD + "==========" + ChatColor.WHITE + ""
			+ ChatColor.BOLD + "[SIMPLE-DRUGS]" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "==========";

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
							p.sendMessage(
									"https://github.com/xxCoderforlife/Simple-Drugs/wiki/FAQs-&-How-To#how-to-craft-and-use-drugs");
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
									p.removePotionEffect(effect.getType());
								}
							} else {
								p.sendMessage(header);
								p.sendMessage(Main.prefix + ChatColor.RED + "You need some drugs.");
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
							p.sendMessage(dash + weed.WeedName);
							p.sendMessage(dash + molly.MollyName);
							p.sendMessage(dash + coke.CokeName);
							p.sendMessage(dash + heroin.HeroinName);
							p.sendMessage(dash + percocet.PercocetName);
							p.sendMessage(dash + ciggy.CiggyName);
							p.sendMessage(dash + acid.AcidName);

						} else {
							p.sendMessage(
									Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
							p.sendMessage(
									Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.list");
						}

					} else if (args[0].equalsIgnoreCase("bagofdrugs")) {
						if (p.hasPermission("drugs.command.bagofdrugs")) {
							p.sendMessage(Main.prefix + ChatColor.GRAY + "You've Been Given The Bag Of Drugs");
							p.getInventory().addItem(pj.bag);
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
				} else {
					p.sendMessage(ChatColor.ITALIC + "incorrect usage of args.");
				}
			} else {
				p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
				p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.main");
			}
		}
		return true;
	}

}
