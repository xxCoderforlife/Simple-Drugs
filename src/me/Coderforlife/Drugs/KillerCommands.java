package me.Coderforlife.Drugs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class KillerCommands implements CommandExecutor {

  Drugs D = new Drugs(this);

  private Main plugin;
  public KillerCommands(Main plugin) {
    this.setPlugin(plugin);
  }

  public Main getPlugin() {
    return this.plugin;
  }

  public void setPlugin(Main plugin) {
    this.plugin = plugin;
  }

  public static String prefix = ChatColor.GRAY + "" + ChatColor.BOLD + "[" +
    ChatColor.DARK_RED + "" + ChatColor.BOLD + "SD" +
    ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET;
  public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
    if (sender instanceof Player) {
      Player p = (Player) sender;
      if (p.hasPermission("drugs.main")) {
        if (args.length == 0) {
          p.sendMessage(
            ChatColor.DARK_RED + "========" +
            ChatColor.WHITE + "[Simple-Drugs]" +
            ChatColor.DARK_RED + "========");
          p.sendMessage(ChatColor.ITALIC + "Use these following commands.");
          p.sendMessage(ChatColor.DARK_GRAY + "- " +
            ChatColor.WHITE + "/drugs help");
          p.sendMessage(ChatColor.DARK_GRAY + "- " +
            ChatColor.WHITE + "/drugs soberup");
        } else if (args.length == 1) {
          if (args[0].equalsIgnoreCase("help")) {
            if (p.hasPermission("drugs.help")) {
              p.sendMessage(ChatColor.DARK_RED + "========" +
                ChatColor.WHITE + "[Simple-Drugs]" +
                ChatColor.DARK_RED + "========");
              p.sendMessage("Craft the drugs and Right-Click with in your hand.");
              p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "DO NOT USE THEM IN A STACK YOU WILL LOSE ALL OF THEM.");
            } else {
              p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that command.");
              p.sendMessage(prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.help");
            }
          } else if (args[0].equalsIgnoreCase("soberup")) {
            if (p.hasPermission("drugs.soberup")) {
              if (!p.getActivePotionEffects().isEmpty()) {
                for (PotionEffect effect: p.getActivePotionEffects()) {
                  p.removePotionEffect(effect.getType());
                }
              } else {
                p.sendMessage(prefix +
                  ChatColor.RED + "You need some drugs.");
              }
            } else {
              p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that command.");

              p.sendMessage(prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.soberup");
            }
          }
        } else {
          p.sendMessage("Don't do that");
        }
      } else {
        p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that command.");
        p.sendMessage(prefix + ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.main");
      }
    }
    return true;
  }
}
