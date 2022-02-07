package me.Coderforlife.SimpleDrugs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Druging.Addiction.AddictionManager;
import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;
import me.Coderforlife.SimpleDrugs.GUI.SettingsGUI;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.SimpleDrugsEditor;
import me.Coderforlife.SimpleDrugs.GUI.Framework.SDRecipeInventory;

public class Commands implements CommandExecutor {
    private Main plugin = Main.plugin;
    private AddictionManager am = plugin.getAddictionManager();
    private HashMap<UUID,Double> addic = am.addictionMap();


    public String dash = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "- ";
    private BagOfDrugsGUI bd = new BagOfDrugsGUI();

    public Commands() {
    }

    public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("drugs.main")) {
                if(args.length == 0) {
                    p.sendMessage(plugin.getMessages().getHeader());
                    p.sendMessage("§7§o§nUse the following commands:");
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
                            p.sendMessage(plugin.getMessages().getPrefix() + "§cYou don't have permission to use that command.");
                            p.sendMessage(plugin.getMessages().getPrefix() + "§4Permission: §cdrugs.use.settings");
                        }
                    } else if(args[0].equalsIgnoreCase("help")) {
                        if(p.hasPermission("drugs.help")) {
                            p.sendMessage(plugin.getMessages().getHeader());
                            p.sendMessage(plugin.getMessages().getPrefix() + "Craft the drugs and Right-Click with in your hand.");
                            p.sendMessage(plugin.getMessages().getPrefix() + "Find out how to craft on the Wiki.");
                            p.sendMessage("https://xxcoderforlife.gitbook.io/simpledrugswiki/");
                        } else {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.help");
                        }
                    } else if(args[0].equalsIgnoreCase("soberup")) {
                        if(p.hasPermission("drugs.soberup")) {
                            if(!p.getActivePotionEffects().isEmpty()) {
                                for(PotionEffect effect : p.getActivePotionEffects()) {
                                    p.removePotionEffect(effect.getType());
                                }
                                p.sendTitle("§a§lSOBERED UP", "§l§egood job.", 10, 4 * 20, 10);
                                p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
                                HashMap<UUID,Double> addic = am.addictionMap();
                                addic.replace(p.getUniqueId(), 0.0);
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, (float) 0.2);
                                // Text | SubText | FadeIn | Stay | FadeOut
                                p.sendTitle("§c§lYOU ARE SOBER", "§f§oYou need some drugs", 10, 4 * 20, 10);
                            }
                        } else {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.soberup");
                        }
                    } else if(args[0].equalsIgnoreCase("list")) {
                        if(p.hasPermission("drugs.list")) {
                            p.sendMessage(plugin.getMessages().getHeader());
                            for(Drug drug : Main.plugin.getDrugManager().getallDrugs()) {
                                p.sendMessage(dash + drug.getDisplayname());
                            }
                        } else {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.list");
                        }
                    } else if(args[0].equalsIgnoreCase("bagofdrugs")) {
                        if(p.hasPermission("drugs.command.bagofdrugs")) {
                            if(!p.getInventory().contains(bd.getBagOfDrugs())) {
                                p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + "You've Been Given The Bag Of Drugs");
                                p.getInventory().addItem(bd.getBagOfDrugs());
                            } else {
                                p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You already have the bag");
                            }
                        } else {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.command.bagofdrugs");
                        }
                    } else if(args[0].equalsIgnoreCase("reload")) {
                        p.sendMessage(plugin.getMessages().getPrefix() + "§aUse §e/drugs settings §ato Change Settings");
                    } else if(args[0].equalsIgnoreCase("sell")) {
                        p.sendMessage(plugin.getMessages().getPrefix() + "§eThis will be added in soon.");
                    } else if(args[0].equalsIgnoreCase("version")) {
                        if(p.hasPermission("drugs.version")) {
                            p.sendMessage(plugin.getMessages().getHeader());
                            p.sendMessage(plugin.getMessages().getPrefix() + "§4§oYou are running version:§f " + Main.plugin.getDescription().getVersion());
                        }
                    } else if(args[0].equalsIgnoreCase("give")) {
                        if(p.hasPermission("drugs.give")) {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&', "&f&oUse &a&o/drugs give &c&o<drug>"));
                        } else {
                            p.sendMessage(plugin.getMessages().getPermission());
                        }
                    } else if(args[0].equalsIgnoreCase("recipe")) {
                        if(p.hasPermission("drugs.recipe")) {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&', "&f&lUse &a&o/drugs recipe &c&o<drug>"));
                        } else {
                            p.sendMessage(plugin.getMessages().getPermission());
                        }
                    }else if(args[0].equalsIgnoreCase("addiction")){
                        if(p.hasPermission("drugs.addiction")){
                            Double addLvl = addic.get(p.getUniqueId());
                            p.sendMessage(plugin.getMessages().getPrefix() + 
                            ChatColor.translateAlternateColorCodes('&', "&3Addiction Level:&r&l ") + Double.toString(addLvl));
                        }
                    }else if(args[0].equalsIgnoreCase("editor")){
                        if(p.hasPermission("drugs.editor")){
                        	SimpleDrugsEditor sde = new SimpleDrugsEditor();
                    		sde.open(p);
                        }
                    }
                } else if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("recipe")) {
                        Drug drug = Main.plugin.getDrugManager().getDrug(args[1].toUpperCase());
                        if(drug == null) {
                            p.sendMessage(plugin.getMessages().getPrefix() + "§e" + args[1] + " §cdoes not exist.");
                            return true;
                        }

                        if(drug.getRecipe() == null) {
                            p.sendMessage(plugin.getMessages().getPrefix() + "§e" + args[1] + " §cdoes not have a recipe.");
                            return true;
                        }

                        if(drug.getRecipe() instanceof SDShapeless) {
                            p.sendMessage(plugin.getMessages().getPrefix() + "§e" + args[1] + " §cdoes not have support for shapeless.");
                            return true;
                        }

                        new SDRecipeInventory(drug).createSDRecipeInventory(p);
                    } else if(args[0].equalsIgnoreCase("bagofdrugs")) {
                        if(p.hasPermission("drugs.command.bagofdrugs.others")) {
                            for(Player players : Bukkit.getOnlinePlayers()) {
                                if(args[1].equalsIgnoreCase(players.getName())) {
                                    if(players.isOnline()) {
                                        if(players.getInventory().contains(bd.getBagOfDrugs())) {
                                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + players.getDisplayName() + " Already has a bag");
                                        } else {
                                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + "You sent " + players.getDisplayName() + " a " + bd.getBagName());
                                            players.getInventory().addItem(bd.getBagOfDrugs());
                                            players.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + p.getDisplayName() + " Sent you a " + bd.getBagName());
                                        }
                                    } else {
                                        p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + "That player is not online.");
                                    }
                                } else {
                                    p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + "That player doesn't exsit.");
                                }
                            }
                        } else {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.GRAY + "drugs.command.bagofdrugs.others");
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
                                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + "You sobered up " + players.getDisplayName());
                                            players.sendMessage(plugin.getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&', p.getDisplayName() + "&7 sobered you up!"));
                                            players.sendTitle("§a§lSOBERED UP", "§l§egood job.", 10, 4 * 20, 10);
                                            players.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
                                        } else {
                                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + p.getDisplayName() + " is already Sober");
                                        }
                                    } else {
                                        p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + "That player isn't online.");
                                    }
                                } else {
                                    p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.GRAY + "That player doesn't exsit.");
                                }
                            }
                        } else {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.DARK_GRAY + "drugs.soberup.others");
                        }
                    } else if(args[0].equalsIgnoreCase("give")) {
                        if(p.hasPermission("drugs.give")) {
                            for(Drug drugs : Main.plugin.getDrugManager().getallDrugs()) {
                                if(args[1].equalsIgnoreCase(drugs.getName())) {
                                    p.getInventory().addItem(drugs.getItem());
                                    p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&', "&7You've been given " + drugs.getDisplayname()));
                                    return true;
                                }
                            }
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&f is not a drug"));
                        } else {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.DARK_GRAY + "drugs.give");
                        }
                    }else if(args[0].equalsIgnoreCase("addiction")){
                        if(p.hasPermission("drugs.addiction.others")){
                            for(Player target : Bukkit.getOnlinePlayers()){
                                Double addLvl = addic.get(target.getUniqueId());
                                p.sendMessage(plugin.getMessages().getPrefix() + 
                                ChatColor.translateAlternateColorCodes('&', " &3&lAddiction Level For &f&o") + target.getDisplayName() + 
                                ChatColor.translateAlternateColorCodes('&', "&f: &l") +  
                                Double.toString(addLvl));
                                return true;
                            }
                            p.sendMessage(plugin.getMessages().getPrefix() + 
                            ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&r is not a player"));
                            return true;
                        }else{
                            p.sendMessage(plugin.getMessages().getPermission());
                        }
                    }
                } else if(args.length == 3) {
                    if(args[0].equalsIgnoreCase("give")) {
                        if(p.hasPermission("durgs.give.others")) {
                            for(Drug drugs : Main.plugin.getDrugManager().getallDrugs()) {
                                if(args[1].equalsIgnoreCase(drugs.getName())) {
                                    for(Player players : Bukkit.getOnlinePlayers()) {
                                        if(args[2].equalsIgnoreCase(players.getName())) {
                                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&', "You sent " + players.getName() + " " + drugs.getDisplayname()));
                                            players.sendMessage(plugin.getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&', p.getDisplayName() + " Sent you some " + drugs.getDisplayname()));
                                            players.getInventory().addItem(drugs.getItem());
                                            return true;
                                        }
                                    }
                                    p.sendMessage(plugin.getMessages().getPrefix() + args[2] + " §cis not a " + "player");
                                    return true;
                                }
                            }
                            p.sendMessage(plugin.getMessages().getPrefix() + "§c§o" + args[1] + "§f is not a " + "drug");
                        } else {
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.DARK_GRAY + "drugs.give.others");
                        }
                    }
                } else if(args.length > 4) {
                    p.sendMessage(plugin.getMessages().getPrefix() + "Don't use " + Arrays.toString(args));
                }
            } else {
                p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.RED + "You don't have permission to use that command.");
                p.sendMessage(plugin.getMessages().getPrefix() + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.main");
            }
        } else if(args.length == 0) {
            sender.sendMessage(plugin.getMessages().getPrefix() + "§eUse §a§odrugs reload §eand §a§odrugs version");
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("reload")) {
                Bukkit.getServer().getConsoleSender().sendMessage(plugin.getMessages().getPrefix() + ChatColor.GREEN + "Attempting to reload config...");
                plugin.reloadConfig();
                Bukkit.getServer().getConsoleSender().sendMessage(plugin.getMessages().getPrefix() + ChatColor.GREEN + "Config has been reloaded");
            }
        }
        return true;
    }

}
