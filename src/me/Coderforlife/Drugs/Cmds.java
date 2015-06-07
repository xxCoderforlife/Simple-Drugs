package me.Coderforlife.Drugs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class Cmds
  implements CommandExecutor
{
  private Drugs plugin;
  
  public Cmds(Drugs plugin)
  {
    setPlugin(plugin);
  }
  
  public Drugs getPlugin()
  {
    return this.plugin;
  }
  final String dash = ChatColor.GRAY + "- ";
  
  public void setPlugin(Drugs plugin) {}
  
  public boolean onCommand(CommandSender sender, Command cmd, String Commandlabel, String[] args)
  {
    cmd.getName().equalsIgnoreCase("drugs");
    if ((args.length == 0)){
    	if ((sender instanceof Player)){
        	sender.sendMessage(ChatColor.RED + "Use the the sub commands listed below:");
        	sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED +"/" + ChatColor.WHITE + "drugs help");
        	sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED +"/" + ChatColor.WHITE + "drugs remove");    	}else{
    		sender.sendMessage("Console has been disabled for this command.");
    	}
    		
    }
    
    if ((args.length == 1) && 
      (args[0].equalsIgnoreCase("remove"))) {
      if ((sender instanceof Player))
      {
        Player player = (Player)sender;
        if (player.hasPermission("drugs.remove"))
        {
          player.removePotionEffect(PotionEffectType.CONFUSION);
          player.removePotionEffect(PotionEffectType.BLINDNESS);
          player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
          player.removePotionEffect(PotionEffectType.FAST_DIGGING);
          player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
          player.removePotionEffect(PotionEffectType.HARM);
          player.removePotionEffect(PotionEffectType.HEAL);
          player.removePotionEffect(PotionEffectType.HUNGER);
          player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
          player.removePotionEffect(PotionEffectType.INVISIBILITY);
          player.removePotionEffect(PotionEffectType.JUMP);
          player.removePotionEffect(PotionEffectType.NIGHT_VISION);
          player.removePotionEffect(PotionEffectType.POISON);
          player.removePotionEffect(PotionEffectType.REGENERATION);
          player.removePotionEffect(PotionEffectType.SLOW);
          player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
          player.removePotionEffect(PotionEffectType.SPEED);
          player.removePotionEffect(PotionEffectType.WATER_BREATHING);
          player.removePotionEffect(PotionEffectType.WEAKNESS);
          player.removePotionEffect(PotionEffectType.WITHER);
          player.sendMessage(ChatColor.RED + "Removed all the Drugs!");
        }
        else
        {
          sender.sendMessage(ChatColor.RED + "You Do Not Have Permission To Do That!");
        }
      }
      else
      {
        sender.sendMessage("Console has been disabled for this command.");
      }
    }
    if (args.length == 1)
    {
      if (args[0].equalsIgnoreCase("help")) {
        if ((sender instanceof Player))
        {
          Player player = (Player)sender;
          if (player.hasPermission("drugs.help"))
          {
            sender.sendMessage(ChatColor.RED + "==============" + ChatColor.AQUA + "[Simple Drugs]" + ChatColor.RED + "==============");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Wheat " + ChatColor.WHITE + "(Weed)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Sugar " + ChatColor.WHITE + "(Cocaine)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Paper " + ChatColor.WHITE + "(Acid)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Milk " + ChatColor.WHITE + "(Beer)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Melons " + ChatColor.WHITE + "(Ecstasy)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "GunPowder " + ChatColor.WHITE + "(PowPow)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Bone " + ChatColor.WHITE + "(Angel Dust)");
          }
          else
          {
            sender.sendMessage(ChatColor.RED + "You Do Not Have Permission To Do That!");
          }
        }
        else
        {
        	sender.sendMessage("Console has been disabled for this command.");
        }
      }
    }
  
    return true;
  }
}
