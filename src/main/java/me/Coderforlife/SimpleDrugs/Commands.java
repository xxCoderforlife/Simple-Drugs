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
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Druging.Addiction.AddictionManager;
import me.Coderforlife.SimpleDrugs.Druging.DrugPlants.DrugPlantItem;
import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;
import me.Coderforlife.SimpleDrugs.GUI.SDRecipeInventory;
import me.Coderforlife.SimpleDrugs.GUI.SettingsGUI;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugMainMenu;
//import me.Coderforlife.SimpleDrugs.GUI.Shop.buyGUI;
import net.milkbowl.vault.economy.Economy;

public class Commands implements CommandExecutor {
    private Main plugin = Main.plugin;
    private AddictionManager am = plugin.getAddictionManager();
    private HashMap<UUID, Double> addic = am.addictionMap();

    public String dash = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "- ";
    private BagOfDrugsGUI bd = new BagOfDrugsGUI();

    public Commands() {
    }

    public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("drugs.main")) {
                if (args.length == 0) {
                    p.sendMessage(plugin.getMessages().getHeader());
                    p.sendMessage("§7§o§nUse the following commands:");
                    p.sendMessage("§7- §f/drugs help §8| §fHow-To and Wiki Link.");
                    p.sendMessage("§7- §f/drugs list §8| §fSee a list of All Drugs.");
                    p.sendMessage("§7- §f/drugs soberup §e[player] §8| §fSoberup yourself or another player.");
                    p.sendMessage("§7- §f/drugs bagofdrugs §e[player] §8| §fGives you or another " + "player the bag.");
                    p.sendMessage(
                            "§7- §f/drugs give §a<drug> §e[player] §8| §fGives you or " + "another player a drug.");
                    p.sendMessage("§7- §f/drugs version §8| §fCheck the version you are running.");
                    p.sendMessage("§7- §f/drugs reload §8| §fReloads the config.");
                    p.sendMessage(
                            "§7- §f/drugs giveSeed §a<drug> §e[player] §8| §fGives you or another player a Drug Seed.");
                    p.sendMessage("§7- §f/drugs recipe §a<drug> §8| §fSee the recipe for a drug.");
                    p.sendMessage("§7- §f/drugs addiction §e[player] §8| §fCheck your or another player's addiction.");
                    p.sendMessage("§7- §f/drugs settings §8| §fOpen the General Settings.");
                    p.sendMessage("§7- §f/drugs editor §8| §fOpen the Drug Editor.");

                    p.sendMessage("§7- §f/drugs sell §8| §fSells the Drug in your hand.");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("settings")) {
                        if (p.hasPermission("drugs.use.settings")) {
                            SettingsGUI g = new SettingsGUI();
                            p.openInventory(g.create());
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + "§cYou don't have permission to use that command.");
                            p.sendMessage("SD " + "§4Permission: §fdrugs.use.settings");
                        }
                    } else if (args[0].equalsIgnoreCase("help")) {
                        if (p.hasPermission("drugs.help")) {
                            p.sendMessage(plugin.getMessages().getHeader());
                            p.sendMessage("SD "
                                    + "Craft the drugs and Right-Click with in your hand.");
                            p.sendMessage("SD " + "Find out how to craft on the Wiki.");
                            p.sendMessage("https://xxcoderforlife.gitbook.io/simpledrugswiki/");
                            p.sendMessage("SD " +
                                    ChatColor.translateAlternateColorCodes('&',
                                            "            &f&oUse &a&o/drugs &f&ofor more information."));
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.help");
                        }
                    } else if (args[0].equalsIgnoreCase("soberup")) {
                        if (p.hasPermission("drugs.soberup")) {
                            if (!p.getActivePotionEffects().isEmpty()) {
                                for (PotionEffect effect : p.getActivePotionEffects()) {
                                    p.removePotionEffect(effect.getType());
                                }
                                p.sendTitle("§a§lSOBERED UP", "§l§egood job.", 10, 4 * 20, 10);
                                p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
                                HashMap<UUID, Double> addic = am.addictionMap();
                                addic.replace(p.getUniqueId(), 0.0);
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, (float) 0.2);
                                // Text | SubText | FadeIn | Stay | FadeOut
                                p.sendTitle("§c§lYOU ARE SOBER", "§f§oYou need some drugs", 10, 4 * 20, 10);
                            }
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.soberup");
                        }
                    } else if (args[0].equalsIgnoreCase("list")) {
                        if (p.hasPermission("drugs.list")) {
                            p.sendMessage(plugin.getMessages().getHeader());
                            for (Drug drug : Main.plugin.getDrugManager().getItems().values()) {
                                p.sendMessage(dash + drug.getDisplayName());
                            }
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.list");
                        }
                    } else if (args[0].equalsIgnoreCase("bagofdrugs")) {
                        if (p.hasPermission("drugs.command.bagofdrugs")) {
                            if (!p.getInventory().contains(bd.getBagOfDrugs())) {
                                p.sendMessage("SD " + ChatColor.GRAY
                                        + "You've Been Given The Bag Of Drugs");
                                p.getInventory().addItem(bd.getBagOfDrugs());
                            } else {
                                p.sendMessage(
                                        "SD " + ChatColor.RED + "You already have the bag");
                            }
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.command.bagofdrugs");
                        }
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        if (p.hasPermission("drugs.reload")) {
                            try {
                                plugin.reloadConfig();
                                p.sendMessage("SD " + ChatColor.GREEN + "Config Reloaded");
                            } catch (Exception e) {
                                p.sendMessage(
                                        "SD " + ChatColor.RED + "Error Reloading Config");
                            }
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.DARK_GRAY + "drugs.reload");
                        }
                    } else if (args[0].equalsIgnoreCase("sell")) {
                        if (p.hasPermission("drugs.sell")) {
                            if (!(plugin.isEcoSetUp())) {
                                p.sendMessage("SD " + ChatColor.RED
                                        + "You need to setup an economy plugin.");
                            } else {
                                ItemStack hand = p.getInventory().getItemInMainHand();
                                Drug d = plugin.getDrugManager().matchDrug(hand);
                                if (plugin.getDrugManager().isDrugItem(hand)) {
                                    p.sendMessage("SD " +
                                            ChatColor.GREEN + "You sold " + d.getDisplayName() + " for "
                                            + d.getSellPrice());
                                    hand.setAmount(hand.getAmount() - 1);
                                    Economy eco = plugin.getEconomy();
                                    eco.depositPlayer(p, d.getSellPrice());
                                } else {
                                    p.sendMessage("SD " +
                                            ChatColor.RED + "Must be a drug in your hand.");
                                }
                            }

                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.sell");
                        }
                    } else if (args[0].equalsIgnoreCase("version")) {
                        if (p.hasPermission("drugs.version")) {
                            p.sendMessage(plugin.getMessages().getHeader());
                            p.sendMessage("SD " + "§4§oYou are running version:§f "
                                    + Main.plugin.getDescription().getVersion());
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.version");
                        }
                    } else if (args[0].equalsIgnoreCase("give")) {
                        if (p.hasPermission("drugs.give")) {
                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                                    "&f&oUse &a&o/drugs give &c&o<drug>"));
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.give");
                        }
                    } else if (args[0].equalsIgnoreCase("recipe")) {
                        if (p.hasPermission("drugs.recipe")) {
                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                                    "&f&lUse &a&o/drugs recipe &c&o<drug>"));
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.recipe");
                        }
                    } else if (args[0].equalsIgnoreCase("addiction")) {
                        if (p.hasPermission("drugs.addiction")) {
                            Double addLvl = addic.get(p.getUniqueId());
                            p.sendMessage("SD " +
                                    ChatColor.translateAlternateColorCodes('&', "&3Addiction Level:&r&l ")
                                    + Double.toString(addLvl));
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.addiction");
                        }
                    } else if (args[0].equalsIgnoreCase("editor")) {
                        if (p.hasPermission("drugs.editor")) {
                            DrugMainMenu sde = new DrugMainMenu();
                            sde.open(p);
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.editor");
                        }
                    } else if (args[0].equalsIgnoreCase("giveSeed")) {
                        if (p.hasPermission("drugs.give.seed")) {
                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                                    "&f&oUse &a&o/drugs give &c&o<drug>"));
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.give.seed");
                        }
//                    } else if (args[0].equalsIgnoreCase("buy")) {
//                        if (p.hasPermission("drugs.buy")) {
//                            if (!(plugin.isEcoSetUp())) {
//                                p.sendMessage("SD " + ChatColor.RED
//                                        + "You need to setup an economy plugin.");
//                            }
//                            buyGUI bGUI = new buyGUI();
//                            bGUI.openShop(p);
//                        } else {
//                            p.sendMessage("SD " + ChatColor.RED
//                                    + "You don't have permission to use that command.");
//                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
//                                    + ChatColor.WHITE + "drugs.buy");
//                        }

                    }else if(args[0].equalsIgnoreCase("gui")){
                        if(p.hasPermission("drugs.gui.bagofdrugs")){
                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                                    "&f&oUse &a&o/drugs gui bagofdrugs"));                            
                        }
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("recipe")) {
                        Drug drug = Main.plugin.getDrugManager().getItem(args[1].toUpperCase());
                        if (drug == null) {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&oThat drug does not exist."));
//                            p.sendMessage("SD " + "§e" + args[1] + " §cdoes not exist.");
                            return true;
                        }

                        if (drug.getRecipe() == null) {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&oThat drug does not exist."));
//                            p.sendMessage("SD " + "§e" + args[1] + " §cdoes not have a recipe.");
                            return true;
                        }

                        new SDRecipeInventory(drug).createSDRecipeInventory(p);
                    } else if (args[0].equalsIgnoreCase("bagofdrugs")) {
                        if (p.hasPermission("drugs.command.bagofdrugs.others")) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if (args[1].equalsIgnoreCase(players.getName())) {
                                    if (players.isOnline()) {
                                        if (players.getInventory().contains(bd.getBagOfDrugs())) {
                                            p.sendMessage("SD " + ChatColor.GRAY + players.getDisplayName() + " Already has a bag");
                                        } else {
                                            p.sendMessage("SD " + ChatColor.GRAY + "You sent " + players.getDisplayName() + " a " + bd.getBagName());
                                            players.getInventory().addItem(bd.getBagOfDrugs());
                                            players.sendMessage("SD " + ChatColor.GRAY + p.getDisplayName() + " Sent you a " + bd.getBagName());
                                        }
                                    } else {
                                    	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&oPlayer is not online"));
//                                        p.sendMessage("SD " + ChatColor.GRAY + "That player is not online.");
                                    }
                                } else {
                                	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&oThat is not a player"));
//                                    p.sendMessage("SD " + ChatColor.GRAY + "That player doesn't exsit.");
                                }
                            }
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: " + ChatColor.WHITE + "drugs.command.bagofdrugs.others");
                        }
                    } else if (args[0].equalsIgnoreCase("soberup")) {
                        if (p.hasPermission("drugs.soberup.others")) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if (args[1].equalsIgnoreCase(players.getName())) {
                                    if (players.isOnline()) {
                                        if (!players.getActivePotionEffects().isEmpty()) {
                                            for (PotionEffect effect2 : players.getActivePotionEffects()) {
                                                players.removePotionEffect(effect2.getType());
                                            }
                                            p.sendMessage("SD " + ChatColor.GRAY + "You sobered up " + players.getDisplayName());
                                            players.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', p.getDisplayName() + "&7 sobered you up!"));
                                            players.sendTitle("§a§lSOBERED UP", "§l§egood job.", 10, 4 * 20, 10);
                                            players.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
                                        } else {
                                            p.sendMessage("SD " + ChatColor.GRAY + players.getDisplayName() + " is already Sober");
                                        }
                                    } else {
                                    	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&oPlayer is not online"));
//                                        p.sendMessage("SD " + ChatColor.GRAY + "That player isn't online.");
                                    }
                                } else {
                                	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&oThat is not a player"));
//                                    p.sendMessage("SD " + ChatColor.GRAY + "That player doesn't exsit.");
                                }
                            }
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.soberup.others");
                        }
                    } else if (args[0].equalsIgnoreCase("give")) {
                        if (p.hasPermission("drugs.give")) {
                            for (Drug drugs : Main.plugin.getDrugManager().getItems().values()) {
                                if (args[1].equalsIgnoreCase(drugs.getName())) {
                                    p.getInventory().addItem(drugs.getItem());
                                    p.sendMessage("SD " + ChatColor.translateAlternateColorCodes( '&', "&7You've been given " + drugs.getDisplayName()));
                                    return true;
                                }
                            }
                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&f is not a drug "));
//                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&f is not a drug"));
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.give");
                        }
                    } else if (args[0].equalsIgnoreCase("addiction")) {
                        if (p.hasPermission("drugs.addiction.others")) {
                            for (Player target : Bukkit.getOnlinePlayers()) {
                                Double addLvl = addic.get(target.getUniqueId());
                                p.sendMessage("SD " +
                                        ChatColor.translateAlternateColorCodes('&', " &3&lAddiction Level For &f&o")
                                        + target.getDisplayName() +
                                        ChatColor.translateAlternateColorCodes('&', "&f: &l") +
                                        Double.toString(addLvl));
                                return true;
                            }
                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&r is not a player"));
//                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',"&c&o" + args[1] + "&r is not a player"));
                            return true;
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.addiction.others");
                        }
                    } else if (args[0].equalsIgnoreCase("giveSeed")) {
                        if (p.hasPermission("drugs.give.seed")) {
                            for (DrugPlantItem d : plugin.getDrugSeedManager().getItems().values()) {
                                if (args[1].equalsIgnoreCase(d.getName())) {
                                    p.getInventory().addItem(d.getItem());
                                    p.sendMessage("SD " +
                                            ChatColor.translateAlternateColorCodes('&',
                                                    "You've been given " + d.getDisplayName() + " Seed"));
                                    return true;
                                }
                            }
                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&f is not a drug "));
//                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&ris not a drug"));
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.give.seed");
                        }
                    }else if(args[0].equalsIgnoreCase("gui")){
                        if(args[1].equalsIgnoreCase("bagofdrugs")){
                            if(p.hasPermission("drugs.gui.bagofdrugs")){
                                BagOfDrugsGUI bGUI = new BagOfDrugsGUI();
                                p.openInventory(bGUI.create());
                            }else{
                            	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                                p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                                p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                        + ChatColor.WHITE + "drugs.gui");
                            }
                        }

                    }
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("give")) {
                        if (p.hasPermission("drugs.give.others")) {
                            for (Drug drugs : Main.plugin.getDrugManager().getItems().values()) {
                                if (args[1].equalsIgnoreCase(drugs.getName())) {
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        if (args[2].equalsIgnoreCase(players.getName())) {
                                            p.sendMessage("SD "
                                                    + ChatColor.translateAlternateColorCodes('&', "You sent "
                                                            + players.getName() + " " + drugs.getDisplayName()));
                                            players.sendMessage("SD "
                                                    + ChatColor.translateAlternateColorCodes('&', p.getDisplayName()
                                                            + " Sent you some " + drugs.getDisplayName()));
                                            players.getInventory().addItem(drugs.getItem());
                                            return true;
                                        }
                                    }
                                    p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[2] + "&f is not a player"));
//                                    p.sendMessage("SD " + args[2] + " §cis not a " + "player");
                                    return true;
                                }
                            }
                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&f is not a drug "));
//                            p.sendMessage("SD " + "§c§o" + args[1] + "§f is not a " + "drug");
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.give.others");
                        }
                    } else if (args[0].equalsIgnoreCase("giveSeed")) {
                        if (p.hasPermission("drugs.give.seed.others")) {
                            for (DrugPlantItem d : plugin.getDrugSeedManager().getItems().values()) {
                                if (args[1].equalsIgnoreCase(d.getName())) {
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        if (args[2].equalsIgnoreCase(players.getName())) {
                                            p.sendMessage("SD " +
                                                    ChatColor.translateAlternateColorCodes('&', "You sent "
                                                            + players.getName() + " " + d.getDisplayName() + " Seed"));
                                            players.sendMessage("SD " +
                                                    ChatColor.translateAlternateColorCodes('&', p.getDisplayName()
                                                            + " Sent you some " + d.getDisplayName() + " Seed"));
                                            players.getInventory().addItem(d.getItem());
                                            return true;
                                        }
                                    }
                                    p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[2] + "&f is not a player"));
//                                    p.sendMessage("SD " + args[2] + " §cis not a " + "player");
                                    return true;
                                }
                            }
                            p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&f is not a drug "));
//                            p.sendMessage("SD " + "§c§o" + args[1] + "§f is not a " + "drug");
                        } else {
                        	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                            p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                            p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: "
                                    + ChatColor.WHITE + "drugs.give.seed.others");
                        }
                    }
                } else if (args.length > 4) {
                    p.sendMessage("SD " + "Don't use " + Arrays.toString(args));
                }
            } else {
            	p.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&', "&4&oYou don't have permission to use that command."));
//                p.sendMessage("SD " + ChatColor.RED + "You don't have permission to use that command.");
                p.sendMessage("SD " + ChatColor.DARK_RED + "Permission: " + ChatColor.WHITE
                        + "drugs.main");
            }
            // Start of Console Commands
        } else if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender cSender = Bukkit.getConsoleSender();
            if (args.length == 0) {
                cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                        "&7- &a&odrugs reload"));
                cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                        "&7- &a&odrugs list"));
                cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                        "&7- &a&odrugs soberup <player>"));
                cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                        "&7- &a&odrugs bagofdrugs <player>"));
                cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                        "&7- &a&odrugs addiction <player>"));
                cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                        "&7- &a&odrugs give <drug> <player>"));
                cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                        "&7- &a&odrugs giveSeed <drug> <player>"));
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    cSender.sendMessage(
                            "SD " + ChatColor.GREEN + "Attempting to reload config...");
                    plugin.reloadConfig();
                    cSender.sendMessage(
                            "SD " + ChatColor.GREEN + "Config has been reloaded");
                } else if (args[0].equalsIgnoreCase("version")) {
                    cSender.sendMessage("SD " +
                            ChatColor.translateAlternateColorCodes('&', "&a&oVersion: ")
                            + plugin.getDescription().getVersion().toString());
                } else if (args[0].equalsIgnoreCase("soberup")) {
                    cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                            "&f&oUse &a&o/drugs soberup <player>"));
                } else if (args[0].equalsIgnoreCase("give")) {
                    cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                            "&f&oUse &a&o/drugs give <drug> <player>"));
                } else if (args[0].equalsIgnoreCase("giveSeed")) {
                    cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                            "&f&oUse &a&o/drugs giveSeed <drug> <player>"));
                } else if (args[0].equalsIgnoreCase("bagofdrugs")) {
                    cSender.sendMessage("SD " + ChatColor.translateAlternateColorCodes('&',
                            "&f&oUse &a&o/drugs bagofdrugs <player>"));
                } else if (args[0].equalsIgnoreCase("recipe")) {
                    cSender.sendMessage("SD " +
                            ChatColor.translateAlternateColorCodes('&', "&c&oCan't use this in console."));
                } else if (args[0].equalsIgnoreCase("list")) {
                    cSender.sendMessage(plugin.getMessages().getHeader());
                    for (Drug drug : Main.plugin.getDrugManager().getItems().values()) {
                        cSender.sendMessage(dash + drug.getDisplayName());
                    }
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("soberup")) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (args[1].equalsIgnoreCase(players.getName())) {
                            if (players.isOnline()) {
                                if (!players.getActivePotionEffects().isEmpty()) {
                                    for (PotionEffect effect2 : players.getActivePotionEffects()) {
                                        players.removePotionEffect(effect2.getType());
                                    }
                                    cSender.sendMessage("SD " + ChatColor.GRAY
                                            + "You sobered up " + players.getDisplayName());
                                    players.sendMessage("SD " + ChatColor
                                            .translateAlternateColorCodes('&', "&aConsole" + "&7 sobered you up!"));
                                    players.sendTitle("§a§lSOBERED UP", "§l§egood job.", 10, 4 * 20, 10);
                                    players.playSound(players.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 2);
                                } else {
                                    cSender.sendMessage("SD " + ChatColor.GRAY
                                            + players.getDisplayName() + " is already Sober");
                                }
                            } else {
                                cSender.sendMessage("SD " + ChatColor.GRAY
                                        + "That player isn't online.");
                            }
                        } else {
                            cSender.sendMessage(
                                    "SD " + ChatColor.GRAY + "That player doesn't exsit.");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("bagofdrugs")) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (args[1].equalsIgnoreCase(players.getName())) {
                            if (players.isOnline()) {
                                if (players.getInventory().contains(bd.getBagOfDrugs())) {
                                    cSender.sendMessage("SD " + ChatColor.GRAY
                                            + players.getDisplayName() + " Already has a bag");
                                } else {
                                    cSender.sendMessage("SD " + ChatColor.GRAY + "You sent "
                                            + players.getDisplayName() + " a " + bd.getBagName());
                                    players.getInventory().addItem(bd.getBagOfDrugs());
                                    players.sendMessage("SD " + ChatColor.GRAY
                                            + "The Console" + " Sent you a " + bd.getBagName());
                                }
                            } else {
                                cSender.sendMessage("SD " + ChatColor.GRAY
                                        + "That player is not online.");
                            }
                        } else {
                            cSender.sendMessage(
                                    "SD " + ChatColor.GRAY + "That player doesn't exsit.");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("addiction")) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        Double addLvl = addic.get(target.getUniqueId());
                        cSender.sendMessage("SD " +
                                ChatColor.translateAlternateColorCodes('&', " &3&lAddiction Level For &f&o")
                                + target.getDisplayName() +
                                ChatColor.translateAlternateColorCodes('&', "&f: &l") +
                                Double.toString(addLvl));
                        return true;
                    }
                    cSender.sendMessage("SD " +
                            ChatColor.translateAlternateColorCodes('&', "&c&o" + args[1] + "&r is not a player"));
                    return true;
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("give")) {
                    for (Drug drugs : Main.plugin.getDrugManager().getItems().values()) {
                        if (args[1].equalsIgnoreCase(drugs.getName())) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if (args[2].equalsIgnoreCase(players.getName())) {
                                    cSender.sendMessage("SD "
                                            + ChatColor.translateAlternateColorCodes('&',
                                                    "You sent " + players.getName() + " " + drugs.getDisplayName()));
                                    players.sendMessage("SD "
                                            + ChatColor.translateAlternateColorCodes('&',
                                                    "&a&oThe Console" + " Sent you some " + drugs.getDisplayName()));
                                    players.getInventory().addItem(drugs.getItem());
                                    return true;
                                }
                            }
                            cSender.sendMessage("SD " + args[2] + " §cis not a " + "player");
                            return true;
                        }
                    }
                    cSender.sendMessage("SD " + "§c§o" + args[1] + "§f is not a " + "drug");
                } else if (args[0].equalsIgnoreCase("giveSeed")) {
                    for (DrugPlantItem d : plugin.getDrugSeedManager().getItems().values()) {
                        if (args[1].equalsIgnoreCase(d.getName())) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if (args[2].equalsIgnoreCase(players.getName())) {
                                    cSender.sendMessage("SD " +
                                            ChatColor.translateAlternateColorCodes('&', "You sent " + players.getName()
                                                    + " " + d.getDisplayName() + " Seed"));
                                    players.sendMessage("SD " +
                                            ChatColor.translateAlternateColorCodes('&', "&a&oThe Console"
                                                    + " Sent you some " + d.getDisplayName() + " Seed"));
                                    players.getInventory().addItem(d.getItem());
                                    return true;
                                }
                            }
                            cSender.sendMessage("SD " + args[2] + " §cis not a " + "player");
                            return true;
                        }
                    }
                    cSender.sendMessage("SD " + "§c§o" + args[1] + "§f is not a " + "drug");
                }
            }
        }
        return true;
    }

}
