package me.Coderforlife.Drugs;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import me.Coderforlife.Drugs.Drugs;

public class Cmds
  implements CommandExecutor
{
	  Logger logger = Logger.getLogger("Minecraft");
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
  final String dash1 = ChatColor.GOLD + "- " + ChatColor.GRAY;
  
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
            sender.sendMessage(ChatColor.RED + "==============" + ChatColor.AQUA + "[Simple Drugs v2.5]"+ ChatColor.RED + "==============");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Wheat " + ChatColor.WHITE + "(Weed)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Sugar " + ChatColor.WHITE + "(Cocaine)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Paper " + ChatColor.WHITE + "(Acid)");
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
      if ((args.length == 1) && 
    	      (args[0].equalsIgnoreCase("changelog"))) {
    	Player player = (Player) sender;
    	player.sendMessage(ChatColor.GRAY + "==========["+ ChatColor.GOLD + "Simple Drugs ChangeLog"+ ChatColor.GRAY + "]==========");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.5" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "Uploaded the Plugin to BukkitDev.");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.6" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "Added Auto-Updater");
    	player.sendMessage(dash1 + "Added Metritcs");
    	player.sendMessage(dash1 +"Uploaded source to GitHub");
    	player.sendMessage(dash1 + "Made code more stable.");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.7" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "BUG FIX: Drugs can now be right clicked on blocks.");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.8" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "BUG FIX: Drugs will be removed on use and creative bug fix as well.");
    	player.sendMessage(dash1 + "REMOVED: Drugs may no longer be clicked on blocks.");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.9" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "REMOVED: Mushrooms as a drug");
    	player.sendMessage(dash1  + "Updated Plugin");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.1" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "REMOVED: Some drug effects");
    	player.sendMessage(dash1  + "UPDATED: 1.5.2-R0.1");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.2" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "UPDATED: 1.6.2-R0.1");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.3" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "UPDATED: 1.8");
    	player.sendMessage(dash1 + "BUG FIX: Fixed random problems");
    	player.sendMessage(dash1 + "Now able to use drugs in both gamemodes");
    	player.sendMessage(dash1 +"Now able to use drugs on both air and blocks.");
    	player.sendMessage(dash1 + "REMOVED: Command /drgus reload");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.4" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "REMOVED: Melons and Milk");
    	player.sendMessage(dash1 + "Cleaned Up code");
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.1" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "ADDED: New command /changelog");
    	player.sendMessage(dash1 + "REMOVED: All Blindness effects for drugs.");
    	player.sendMessage(dash1 + "Updated code on GitHub");
      }
    }
  
    return true;
  }


}
