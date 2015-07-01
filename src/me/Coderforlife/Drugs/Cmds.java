package me.Coderforlife.Drugs;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import me.Coderforlife.Drugs.Drugs;

public class Cmds
  implements CommandExecutor
{
	FileConfiguration config;
	File cfile;
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
  	/* Command /drugs */
    cmd.getName().equalsIgnoreCase("drugs");
    if ((args.length == 0)){
    	if ((sender instanceof Player)){
        	sender.sendMessage(ChatColor.RED + "Use the the sub commands listed below:");
        	sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED +"/" + ChatColor.WHITE + "drugs help");
        	sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED +"/" + ChatColor.WHITE + "drugs remove");
        	sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED +"/" + ChatColor.WHITE + "drugs changelog");  
        	}else{
    		sender.sendMessage("Console has been disabled for this command.");
    	}
    		
    }
    /* Command /drugs remove */
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
          	/*Command /drugs help*/
            sender.sendMessage(ChatColor.RED + "==============" + ChatColor.AQUA + "[Simple Drugs v2.5.2]"+ ChatColor.RED + "==============");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Wheat " + ChatColor.WHITE + "(Weed)" + ChatColor.GRAY + 
            		" Effects: " + ChatColor.AQUA + "Speed Boost,Confusion");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Sugar " + ChatColor.WHITE + "(Cocaine)" + ChatColor.GRAY + 
            		" Effects: " + ChatColor.AQUA + "Night Vison,Increase Damage,Fire Resistance");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Paper " + ChatColor.WHITE + "(Acid)" + ChatColor.GRAY + 
            		" Effects: " + ChatColor.AQUA + "Night Vison,Jump Boost");
            sender.sendMessage(this.dash + ChatColor.GREEN + "GunPowder " + ChatColor.WHITE + "(PowPow)" + ChatColor.GRAY + 
            		" Effects: " + ChatColor.AQUA + "Jump Boost,Health Regeneration,Speed Boost,Confusion");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Bone " + ChatColor.WHITE + "(Angel Dust)" + ChatColor.GRAY + 
            		" Effects: " + ChatColor.AQUA + "Speed Boost,Confusion");
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
       /* Command /drugs changelog */
      if ((args.length == 1) && 
    	      (args[0].equalsIgnoreCase("changelog"))) {
    	  if ((sender instanceof Player)){
    	  Player player = (Player) sender;
    	  if(player.hasPermission("drugs.changelog")){
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
    	player.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.2" + ChatColor.GRAY + "]==========");
    	player.sendMessage(dash1 + "ADDED: View effects with /drugs help");
    	player.sendMessage(dash1 + "ADDED: Plugin Metrics back in");
    	player.sendMessage(dash1 + "Fixed Version and Spelling Errors");
    	player.sendMessage(dash1 + "ADDED: Console Changelog command");
    	player.sendMessage(dash1 + "ADDED: Permission for Changelog command");
    	player.sendMessage(dash1 + "Updated code on GitHub");
    	}else{
    		  player.sendMessage(ChatColor.RED + "You Do Not Have Permission To Do That!");
    	  }
    	 }else{
    	 	/* Console Command For /drugs changelog */
    	    	sender.sendMessage(ChatColor.GRAY + "==========["+ ChatColor.GOLD + "Simple Drugs ChangeLog"+ ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.5" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "Uploaded the Plugin to BukkitDev.");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.6" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "Added Auto-Updater");
    	    	sender.sendMessage(dash1 + "Added Metritcs");
    	    	sender.sendMessage(dash1 +"Uploaded source to GitHub");
    	    	sender.sendMessage(dash1 + "Made code more stable.");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.7" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "BUG FIX: Drugs can now be right clicked on blocks.");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.8" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "BUG FIX: Drugs will be removed on use and creative bug fix as well.");
    	    	sender.sendMessage(dash1 + "REMOVED: Drugs may no longer be clicked on blocks.");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.9" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "REMOVED: Mushrooms as a drug");
    	    	sender.sendMessage(dash1  + "Updated Plugin");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.1" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "REMOVED: Some drug effects");
    	    	sender.sendMessage(dash1  + "UPDATED: 1.5.2-R0.1");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.2" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "UPDATED: 1.6.2-R0.1");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.3" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "UPDATED: 1.8");
    	    	sender.sendMessage(dash1 + "BUG FIX: Fixed random problems");
    	    	sender.sendMessage(dash1 + "Now able to use drugs in both gamemodes");
    	    	sender.sendMessage(dash1 +"Now able to use drugs on both air and blocks.");
    	    	sender.sendMessage(dash1 + "REMOVED: Command /drgus reload");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.4" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "REMOVED: Melons and Milk");
    	    	sender.sendMessage(dash1 + "Cleaned Up code");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.1" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1 + "ADDED: New command /changelog");
    	    	sender.sendMessage(dash1 + "REMOVED: All Blindness effects for drugs.");
    	    	sender.sendMessage(dash1 + "Updated code on GitHub");
    	    	sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.2" + ChatColor.GRAY + "]==========");
    	    	sender.sendMessage(dash1  + "ADDED: View effects with /drugs help");
    	    	sender.sendMessage(dash1  + "ADDED: Plugin Metrics back in");
    	    	sender.sendMessage(dash1  + "Fixed Version and Spelling Errors");
    	    	sender.sendMessage(dash1 + "ADDED: Console Changelog command");
    	    	sender.sendMessage(dash1 + "ADDED: Permission for Changelog command");
    	    	sender.sendMessage(dash1 + "Updated code on GitHub");
    	 }
      }
      /* Coming soon /drugs reload */
      if ((args.length == 1) &&
    	      (args[0].equalsIgnoreCase("reload"))) {
    	  if ((sender instanceof Player)){
    	  Player player = (Player) sender;
    	  if(player.hasPermission("drugs.reload")){
    		  player.sendMessage(ChatColor.RED + "Reloading Config...");
    		  config = YamlConfiguration.loadConfiguration(cfile);
    		  player.sendMessage(ChatColor.RED + "Reloaded Config");
    	}else{
  		  player.sendMessage(ChatColor.RED + "You Do Not Have Permission To Do That!");

    	}
      }else{
    	  
      }
    		  
   }*/
    }
  
    return true;
  }


}
