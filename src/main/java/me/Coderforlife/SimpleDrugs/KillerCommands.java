package me.Coderforlife.SimpleDrugs;

import me.Coderforlife.SimpleDrugs.Druging.BagOfDrugs;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Events.PlayerJoin;
import me.Coderforlife.SimpleDrugs.GUI.RecipeGUI;
import me.Coderforlife.SimpleDrugs.GUI.SettingsGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public class KillerCommands implements CommandExecutor {

    private Main plugin;

    public KillerCommands(Main plugin) {
        this.setPlugin(plugin);
    }

    PlayerJoin pj = new PlayerJoin();

    public Main getPlugin() {
        return this.plugin;
    }

    public void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    public static String dash = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "- ";
    public static String header = ChatColor.translateAlternateColorCodes('&', "&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&f&l[&4&o&lSIMPLE DRUGS&f&l]" + "&8&l&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=");

    public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
        if(sender instanceof Player p) {
            if(p.hasPermission("drugs.main")) {
                if(args.length == 0) {
                    p.sendMessage(header);
                    p.sendMessage("§7§o&nUse the following commands:");
                    p.sendMessage("§7- §f/drugs help §8| §fHow-To and Wiki Link.");
                    p.sendMessage("§7- §f/drugs list §8| §fSee a list of All Drugs.");
                    p.sendMessage("§7- §f/drugs soberup §e[player] §8| §fSoberup yourself or another player.");
                    p.sendMessage("§7- §f/drugs bagofdrugs §e[player] §8| §fGives you or another " + "player the bag.");
                    p.sendMessage("§7- §f/drugs give §a<drug> §e[player] §8| §fGives you or " + "another player a drug.");
                    p.sendMessage("§7- §f/drugs version §8| §fCheck the version you are running.");
                    p.sendMessage("§7- §f/drugs reload §8| §fReloads the config.");
                    // p.sendMessage("§7- §f/drugs sell §8| §fSells the Drug in your hand."));
                } else if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("settings")) {
                        if(p.hasPermission("drugs.use.settings")) {
                            SettingsGUI g = new SettingsGUI();
                            p.openInventory(g.create());
                        } else {
                            p.sendMessage(Main.prefix + "§cYou don't have permission to use that command.");
                            p.sendMessage(Main.prefix + "§4Permission: §cdrugs.use.settings");
                        }
                    } else if(args[0].equalsIgnoreCase("help")) {
                        if(p.hasPermission("drugs.help")) {
                            p.sendMessage(header);
                            p.sendMessage(Main.prefix + "Craft the drugs and Right-Click with in your hand.");
                            p.sendMessage(Main.prefix + "Find out how to craft on the Wiki.");
                            p.sendMessage("https://xxcoderforlife.gitbook.io/simpledrugswiki/");
                        } else {
                            p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.help");
                        }
                    } else if(args[0].equalsIgnoreCase("soberup")) {
                        if(p.hasPermission("drugs.soberup")) {
                            if(!p.getActivePotionEffects().isEmpty()) {
                                for(PotionEffect effect : p.getActivePotionEffects()) {
                                    p.sendTitle("§a§lSOBERED UP", "§l§egood job.", 10, 4 * 20, 10);
                                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
                                    p.removePotionEffect(effect.getType());
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, (float) 0.2);
                                // Text | SubText | FadeIn | Stay | FadeOut
                                p.sendTitle("§c§lYOU ARE SOBER", "§f§oYou need some drugs", 10, 4 * 20, 10);
                            }
                        } else {
                            p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.soberup");
                        }
                    } else if(args[0].equalsIgnoreCase("list")) {
                        if(p.hasPermission("drugs.list")) {
                            p.sendMessage(header);
                            for(Drug drug : Drug.getallDrugs()) {
                                p.sendMessage(dash + drug.getDisplayname());
                            }
                        } else {
                            p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.list");
                        }
                    } else if(args[0].equalsIgnoreCase("bagofdrugs")) {
                        if(p.hasPermission("drugs.command.bagofdrugs")) {
                            if(!p.getInventory().contains(pj.bag)) {
                                p.sendMessage(Main.prefix + ChatColor.GRAY + "You've Been Given The Bag Of Drugs");
                                p.getInventory().addItem(pj.bag);
                            } else {
                                p.sendMessage(Main.prefix + ChatColor.RED + "You already have the bag");
                            }
                        } else {
                            p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.command.bagofdrugs");
                        }
                    } else if(args[0].equalsIgnoreCase("reload")) {
                        p.sendMessage(Main.prefix + "§aUse §e/drugs settings §ato Change Settings");
                    } else if(args[0].equalsIgnoreCase("sell")) {
                        p.sendMessage(Main.prefix + "§eThis will be added in soon.");
                    } else if(args[0].equalsIgnoreCase("version")) {
                        if(p.hasPermission("drugs.version")) {
                            p.sendMessage(header);
                            p.sendMessage(Main.prefix + "§4§oYou are running version:§f " + plugin.getDescription().getVersion());
                        }
                    }
                } else if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("recipe")) {
                        Drug drug = Drug.getDrug(args[1]);
                        if(drug == null) {
                            p.sendMessage(Main.prefix + "§e" + args[1] + " §cdoes not exist.");
                            return true;
                        }
                        p.openInventory(new RecipeGUI().create(drug));
                    } else if(args[0].equalsIgnoreCase("bagofdrugs")) {
                        if(p.hasPermission("drugs.command.bagofdrugs.others")) {
                            for(Player players : Bukkit.getOnlinePlayers()) {
                                if(args[1].equalsIgnoreCase(players.getName())) {
                                    if(players.isOnline()) {
                                        if(players.getInventory().contains(this.pj.bag)) {
                                            p.sendMessage(Main.prefix + ChatColor.GRAY + players.getDisplayName() + " Already has a bag");
                                        } else {
                                            p.sendMessage(Main.prefix + ChatColor.GRAY + "You sent " + players.getDisplayName() + " a " + BagOfDrugs.bagName);
                                            players.getInventory().addItem(this.pj.bag);
                                            players.sendMessage(Main.prefix + ChatColor.GRAY + p.getDisplayName() + " Sent you a " + BagOfDrugs.bagName);
                                        }
                                    } else {
                                        p.sendMessage(Main.prefix + ChatColor.GRAY + "That player is not online.");
                                    }
                                } else {
                                    p.sendMessage(Main.prefix + ChatColor.GRAY + "That player doesn't exsit.");
                                }
                            }
                        } else {
                            p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.GRAY + "drugs.command.bagofdrugs.others");
                        }
                    } else if(args[0].equalsIgnoreCase("soberup")) {
                        if(p.hasPermission("drugs.soberup.others")) {
                            for(Player players : Bukkit.getOnlinePlayers()) {
                                if(args[1].equalsIgnoreCase(players.getName())) {
                                    if(players.isOnline()) {
                                        if(!players.getActivePotionEffects().isEmpty()) {
                                            for(PotionEffect effect2 : p.getActivePotionEffects()) {
                                                players.removePotionEffect(effect2.getType());
                                            }
                                            p.sendMessage(Main.prefix + ChatColor.GRAY + "You sobered up " + players.getDisplayName());
                                            players.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', p.getDisplayName() + "&7 sobered you up!"));
                                            players.sendTitle("§a§lSOBERED UP", "§l§egood job.", 10, 4 * 20, 10);
                                            players.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
                                        } else {
                                            p.sendMessage(Main.prefix + ChatColor.GRAY + p.getDisplayName() + " is already Sober");
                                        }
                                    } else {
                                        p.sendMessage(Main.prefix + ChatColor.GRAY + "That player isn't online.");
                                    }
                                } else {
                                    p.sendMessage(Main.prefix + ChatColor.GRAY + "That player doesn't exsit.");
                                }
                            }
                        } else {
                            p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.DARK_GRAY + "drugs.soberup.others");
                        }
                    } else if(args[0].equalsIgnoreCase("give")) {
                        if(p.hasPermission("drugs.give")) {
                            for(Drug drugs : Drug.getallDrugs()) {
                                if(args[1].equalsIgnoreCase(drugs.getName())) {
                                    p.getInventory().addItem(drugs.getItem());
                                    p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', "&7You've been given " + drugs.getDisplayname()));
                                    return true;
                                }
                            }
                            p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&f is not a drug"));
                        } else {
                            p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.DARK_GRAY + "drugs.give");
                        }
                    }
                } else if(args.length == 3) {
                    if(args[0].equalsIgnoreCase("give")) {
                        if(p.hasPermission("durgs.give.others")) {
                            for(Drug drugs : Drug.getallDrugs()) {
                                if(args[1].equalsIgnoreCase(drugs.getName())) {
                                    for(Player players : Bukkit.getOnlinePlayers()) {
                                        if(args[2].equalsIgnoreCase(players.getName())) {
                                            p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', "You sent " + players.getName() + " " + drugs.getDisplayname()));
                                            players.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', p.getDisplayName() + " Sent you some " + drugs.getDisplayname()));
                                            players.getInventory().addItem(drugs.getItem());
                                        } else {
                                            p.sendMessage(Main.prefix + args[2] + " §cis not a " + "player");
                                        }
                                    }
                                } else {
                                    p.sendMessage(Main.prefix + "§c§o" + args[1] + "§f is not a " + "drug");
                                }
                            }
                        } else {
                            p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.DARK_GRAY + "drugs.give.others");
                        }
                    }
                } else if(args.length > 4) {
                    p.sendMessage(Main.prefix + "Don't use " + Arrays.toString(args));
                }
            } else {
                p.sendMessage(Main.prefix + ChatColor.RED + "You don't have permission to use that command.");
                p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.main");
            }
        } else if(args.length == 0) {
            sender.sendMessage(Main.prefix + "§eUse §a§odrugs reload §eand §a§odrugs version");
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("reload")) {
                Bukkit.getServer().getConsoleSender().sendMessage(Main.prefix + ChatColor.GREEN + "Attempting to reload config...");
                new Settings();
                Bukkit.getServer().getConsoleSender().sendMessage(Main.prefix + ChatColor.GREEN + "Config has been reloaded");
            }
        }
        return true;
    }

}
