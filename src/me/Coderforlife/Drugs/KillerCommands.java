package me.Coderforlife.Drugs;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class KillerCommands
  implements CommandExecutor
{
  Logger logger = Logger.getLogger("Minecraft");
  private Main plugin;
  
  public KillerCommands(Main plugin)
  {
    setPlugin(plugin);
  }
  
  public Main getPlugin()
  {
    return this.plugin;
  }
  
  public final String prefix = ChatColor.RED + "==============" + ChatColor.AQUA + "[Simple Drugs v2.6.5 (beta)]" + ChatColor.RED + "==============";
  final String dash = ChatColor.GRAY + "- ";
  public final String prefix2 = ChatColor.BLACK + "[" + ChatColor.DARK_RED + "SD" + ChatColor.BLACK + "] " + ChatColor.RESET;
  final String dash1 = ChatColor.GOLD + "- " + ChatColor.GRAY;
  final String perm = ChatColor.RED + "You don't have the right permission";
  
  public <plugin> void setPlugin(Main plugin) {}
  
  public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args)
  {
    if (command.getName().equalsIgnoreCase("drugs"))
    {
      if (args.length == 0)
      {
        if ((sender instanceof Player))
        {
          Player p = (Player)sender;
          if (p.hasPermission("drugs.main"))
          {
            p.sendMessage(ChatColor.RED + "Use the the sub commands listed below:");
            p.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs help");
            p.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs remove");
            p.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs reload");
          }
          else
          {
            p.sendMessage(this.perm);
          }
        }
        else
        {
          sender.sendMessage(ChatColor.RED + "Use the the sub commands listed below:");
          sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs help");
          sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs remove");
          sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs reload");
        }
      }
      else if (args[0].equalsIgnoreCase("remove"))
      {
        if ((sender instanceof Player))
        {
          Player p = (Player)sender;
          if (!p.getActivePotionEffects().isEmpty())
          {
            if (p.hasPermission("drugs.remove"))
            {
              for (PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
              }
              p.sendMessage(this.prefix2 + ChatColor.AQUA + "Sobered Up!");
            }
            else
            {
              p.sendMessage(this.perm);
            }
          }
          else {
            p.sendMessage(ChatColor.MAGIC + "|||" + ChatColor.RESET + "you need drugs" + ChatColor.MAGIC + "|||");
          }
        }
        else
        {
          sender.sendMessage("Only players may use this command.");
        }
      }
      else if (args[0].equalsIgnoreCase("help")) {
        if ((sender instanceof Player))
        {
          Player p = (Player)sender;
          if (p.hasPermission("drugs.help"))
          {
            sender.sendMessage(this.prefix);
            sender.sendMessage(this.dash + ChatColor.GREEN + "Wheat " + ChatColor.WHITE + "(Weed)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Sugar " + ChatColor.WHITE + "(Cocaine)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Paper " + ChatColor.WHITE + "(Acid)");
          }
          else
          {
            p.sendMessage(this.perm);
          }
        }
        else
        {
          sender.sendMessage(this.prefix);
          sender.sendMessage(this.dash + ChatColor.GREEN + "Wheat " + ChatColor.WHITE + "(Weed)");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Sugar " + ChatColor.WHITE + "(Cocaine)");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Paper " + ChatColor.WHITE + "(Acid)");
        }
      }    else if (args[0].equalsIgnoreCase("reload")) {
          try
          {
            if ((sender instanceof Player))
            {
              Player p = (Player)sender;
              if (p.hasPermission("drugs.reload"))
              {
                p.sendMessage(ChatColor.GREEN + "Reloading config...");
                this.plugin.reloadConfig();
                p.sendMessage(ChatColor.GREEN + "Reloaded Config");
              }
              else
              {
                p.sendMessage(this.perm);
              }
            }
            else
            {
             this.plugin.reloadConfig();
            }
          }
          catch (Exception e)
          {
        	  if(sender instanceof Player) {
        		  Player p = (Player) sender;
        		  p.sendMessage(ChatColor.DARK_RED + "ERROR!");
        		  p.sendMessage(ChatColor.BOLD + "Check the Console for the stack trace.");
        	  }
            sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.WHITE + "Config failed to load.");
            sender.sendMessage(ChatColor.GREEN + "If this keeps happening tell me on Spigot");
            sender.sendMessage(ChatColor.GREEN + "DON'T FORGET TO BRING THIS ERROR!");
            e.printStackTrace();
          }
        }
    }
    return true;
  }
}