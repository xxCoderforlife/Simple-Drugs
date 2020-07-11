package me.Coderforlife.Drugs;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class KillerCommands implements CommandExecutor {
   private Main plugin;
   Logger logger = Logger.getLogger("Minecraft");
   public final String prefix = ChatColor.RED + "====" + ChatColor.BLACK + "[" + ChatColor.DARK_RED + "Simple Drugs v2.6.7 (beta)" + ChatColor.BLACK + "]" + ChatColor.RED + "====";
   public final String crafting = ChatColor.RED + "====" + ChatColor.BLACK + "[" + ChatColor.DARK_RED + "How-To" + ChatColor.BLACK + "]" + ChatColor.RED + "====";

   final String dash = ChatColor.GRAY + "- " + ChatColor.RESET;
   final String dash2 = ChatColor.BLACK + " - " + ChatColor.RESET;
   public static final String prefix2 = ChatColor.BLACK + "[" + ChatColor.DARK_RED + "SD" + ChatColor.BLACK + "] " + ChatColor.RESET;
   final String perm = prefix2 + ChatColor.RED + "You don't have the right permission";


   static {
   }

   public KillerCommands(Main plugin) {
      this.setPlugin(plugin);
   }

   public Main getPlugin() {
      return this.plugin;
   }

   public void setPlugin(Main plugin) {
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
      if (command.getName().equalsIgnoreCase("drugs")) {
         Player p;
         if (args.length == 0) {
            if (sender instanceof Player) {
               p = (Player)sender;
               if (p.hasPermission("drugs.main")) {
                  p.sendMessage(ChatColor.RED + "Use the the sub commands listed below:");
                  p.sendMessage(this.dash + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs help");
                  p.sendMessage(this.dash + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs remove");
                  p.sendMessage(this.dash + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs reload");
                  p.sendMessage(this.dash + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs howto");
               } else {
                  p.sendMessage(this.perm);
                  p.sendMessage(prefix2 + ChatColor.DARK_RED + "Needed Permission: " + ChatColor.RESET + "drugs.main");
               }
            } else {
               sender.sendMessage(ChatColor.RED + "Use the the sub commands listed below:");
               sender.sendMessage(this.dash + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs help");
               sender.sendMessage(this.dash + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs remove");
               sender.sendMessage(this.dash + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs reload");
               sender.sendMessage(this.dash + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs howto");
            }
         } else if (args[0].equalsIgnoreCase("remove")) {
            if (sender instanceof Player) {
               p = (Player)sender;
               if (!p.getActivePotionEffects().isEmpty()) {
                  if (p.hasPermission("drugs.remove")) {
                	  for (PotionEffect effect : p.getActivePotionEffects()) {
                          p.removePotionEffect(effect.getType());
                        }
                	  
                     p.sendMessage(prefix2 + ChatColor.AQUA + "Sobered Up!");
                  } else {
                     p.sendMessage(this.perm);
                     p.sendMessage(prefix2 + ChatColor.DARK_RED + "Needed Permission: " + ChatColor.RESET + "drugs.remove");
                  }
               } else {
                  p.sendMessage(ChatColor.MAGIC + "|||" + ChatColor.RESET + "you need drugs" + ChatColor.MAGIC + "|||");
               }
            } else {
               sender.sendMessage("Only players may use this command.");
            }
         } else if (args[0].equalsIgnoreCase("help")) {
            if (sender instanceof Player) {
               p = (Player)sender;
               if (p.hasPermission("drugs.help")) {
                  p.sendMessage(this.prefix);
                  p.sendMessage("If you need help contact me on Discord or Spigot");
                  p.sendMessage(ChatColor.GOLD + "Spigot: https://www.spigotmc.org/members/xxcoderforlife.68663/");
                  p.sendMessage(ChatColor.DARK_PURPLE + "Discord: https://discord.gg/jnmKj7Z");
               } else {
                  p.sendMessage(this.perm);
                  p.sendMessage(prefix2 + ChatColor.DARK_RED + "Needed Permission: " + ChatColor.RESET + "drugs.help");
               }
            } else {
               sender.sendMessage(this.prefix);
               sender.sendMessage("If you need help contact me on Discord or Spigot");
               sender.sendMessage(ChatColor.GOLD + "Spigot: https://www.spigotmc.org/members/xxcoderforlife.68663/");
               sender.sendMessage(ChatColor.DARK_PURPLE + "Discord: https://discord.gg/jnmKj7Z");
            }
         } else if (args[0].equalsIgnoreCase("reload")) {
            try {
               if (sender instanceof Player) {
                  p = (Player)sender;
                  if (p.hasPermission("drugs.reload")) {
                     p.sendMessage(ChatColor.GREEN + "Reloading config...");
                     this.plugin.reloadConfig();
                     p.sendMessage(ChatColor.GREEN + "Reloaded Config");
                  } else {
                     p.sendMessage(this.perm);
                     p.sendMessage(prefix2 + ChatColor.DARK_RED + "Needed Permission: " + ChatColor.RESET + "drugs.reload");
                  }
               } else {
                  this.plugin.reloadConfig();
               }
            } catch (Exception e) {
               if (sender instanceof Player) {
                  sender.sendMessage(ChatColor.DARK_RED + "ERROR!");
                  sender.sendMessage(ChatColor.BOLD + "Check the Console for the stack trace.");
               }

               sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.WHITE + " Config failed to load.");
               sender.sendMessage(ChatColor.GREEN + "If this keeps happening tell me on Spigot");
               sender.sendMessage(ChatColor.GREEN + "DON'T FORGET TO BRING THIS ERROR!");
               e.printStackTrace();
               System.out.println(ChatColor.DARK_RED + "this is the end of the error. Have a Nice Day :)");
            }
         } else if (args[0].equalsIgnoreCase("howto")) {
            if (sender instanceof Player) {
               p = (Player)sender;
               if (p.hasPermission("drugs.howto")) {
                  p.sendMessage(this.crafting);
                  p.sendMessage(prefix2 + "You need to craft the items to use them");
                  p.sendMessage(prefix2 + "Use the commands below to see the recipes");
                  p.sendMessage(prefix2 + ChatColor.GREEN + "Weed" + this.dash2 + "/drugs weed");
                  p.sendMessage(prefix2 + ChatColor.AQUA + "Acid" + this.dash2 + "/drugs acid");
                  p.sendMessage(prefix2 + ChatColor.GOLD + "Coke" + this.dash2 + "/drugs coke");
               } else {
                  p.sendMessage(this.perm);
                  p.sendMessage(prefix2 + ChatColor.DARK_RED + "Needed Permission: " + ChatColor.RESET + "drugs.howto");
               }
            } else {
               sender.sendMessage("Only Player may use this command");
            }
         } else if (args[0].equalsIgnoreCase("weed")) {
            if (sender instanceof Player) {
               p = (Player)sender;
               if (p.hasPermission("drugs.howto.weed")) {
                  p.sendMessage(this.crafting);
                  p.sendMessage(prefix2 + "First you need a crafting table and 3 wheat");
                  p.sendMessage(prefix2 + "The crafting recipe will be displayed below as if it were in the table itself");
                  p.sendMessage(prefix2 + "X = blank space");
                  p.sendMessage(prefix2 + "W = wheat");
                  p.sendMessage(prefix2 + ChatColor.BOLD + "   'XWX'   ");
                  p.sendMessage(prefix2 + ChatColor.BOLD + "   'XWX'   ");
                  p.sendMessage(prefix2 + ChatColor.BOLD + "   'XWX'   ");
               } else {
                  p.sendMessage(this.perm);
                  p.sendMessage(prefix2 + ChatColor.DARK_RED + "Needed Permission: " + ChatColor.RESET + "drugs.howto.weed");
               }
            } else {
               sender.sendMessage("Only Players may use this command");
            }
         } else if (args[0].equalsIgnoreCase("acid")) {
            if (sender instanceof Player) {
               p = (Player)sender;
               if (p.hasPermission("drugs.howto.acid")) {
                  p.sendMessage(this.crafting);
                  p.sendMessage(prefix2 + "First you need a crafting table and 3 paper");
                  p.sendMessage(prefix2 + "The crafting recipe will be displayed below as if it were in the table itself");
                  p.sendMessage(prefix2 + "X = blank space");
                  p.sendMessage(prefix2 + "P = acid");
                  p.sendMessage(prefix2 + ChatColor.BOLD + "   'XPX'   ");
                  p.sendMessage(prefix2 + ChatColor.BOLD + "   'XPX'   ");
                  p.sendMessage(prefix2 + ChatColor.BOLD + "   'XPX'   ");
               } else {
                  p.sendMessage(this.perm);
                  p.sendMessage(prefix2 + ChatColor.DARK_RED + "Needed Permission: " + ChatColor.RESET + "drugs.howto.acid");
               }
            } else {
               sender.sendMessage("Only Players may use this command");
            }
         } else if (args[0].equalsIgnoreCase("coke")) {
            if (sender instanceof Player) {
               p = (Player)sender;
               if (p.hasPermission("drugs.howto.coke")) {
                  p.sendMessage(this.crafting);
                  p.sendMessage(prefix2 + "First you need a crafting table and 3 sugar");
                  p.sendMessage(prefix2 + "The crafting recipe will be displayed below as if it were in the table itself");
                  p.sendMessage(prefix2 + "X = blank space");
                  p.sendMessage(prefix2 + "S = sugar");
                  p.sendMessage(prefix2 + ChatColor.BOLD + "   'XSX'   ");
                  p.sendMessage(prefix2 + ChatColor.BOLD + "   'XSX'   ");
                  p.sendMessage(prefix2 + ChatColor.BOLD + "   'XSX'   ");
               } else {
                  p.sendMessage(this.perm);
                  p.sendMessage(prefix2 + ChatColor.DARK_RED + "Needed Permission: " + ChatColor.RESET + "drugs.howto.coke");
               }
            } else {
               sender.sendMessage("Only Players may use this command");
            }
         }
      }

      return true;
   }
}
