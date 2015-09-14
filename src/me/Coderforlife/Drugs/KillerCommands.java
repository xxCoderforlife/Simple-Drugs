package me.Coderforlife.Drugs;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

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
  public final String prefix = ChatColor.RED + "==============" + ChatColor.AQUA + "[Simple Drugs v2.5.7]"+ ChatColor.RED + "==============" ;
  final String dash = ChatColor.GRAY + "- ";
  final String dash1 = ChatColor.GOLD + "- " + ChatColor.GRAY;
  final String perm = ChatColor.RED + "You don't have the right permission";
  
  public <plugin> void setPlugin(Main plugin) {}
  
  @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args)
  {
    if (command.getName().equalsIgnoreCase("drugs")) {
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
            p.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs changelog");
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
          sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs changelog");
          sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + "Ex. " + ChatColor.RED + "/" + ChatColor.WHITE + "drugs reload");
        }
      }
      else if (args[0].equalsIgnoreCase("remove"))
      {
        if ((sender instanceof Player))
        {
          Player p = (Player)sender;
          if(!(p.getActivePotionEffects().isEmpty())){
          if (p.hasPermission("drugs.remove"))
          {
            p.playSound(p.getLocation(), Sound.SPLASH2, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1);
            p.removePotionEffect(PotionEffectType.CONFUSION);
            p.removePotionEffect(PotionEffectType.BLINDNESS);
            p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            p.removePotionEffect(PotionEffectType.HARM);
            p.removePotionEffect(PotionEffectType.HEAL);
            p.removePotionEffect(PotionEffectType.HUNGER);
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
            p.removePotionEffect(PotionEffectType.JUMP);
            p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            p.removePotionEffect(PotionEffectType.POISON);
            p.removePotionEffect(PotionEffectType.REGENERATION);
            p.removePotionEffect(PotionEffectType.SLOW);
            p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
            p.removePotionEffect(PotionEffectType.SPEED);
            p.removePotionEffect(PotionEffectType.WATER_BREATHING);
            p.removePotionEffect(PotionEffectType.WEAKNESS);
            p.removePotionEffect(PotionEffectType.WITHER);
            p.sendMessage(ChatColor.RED + "Removed all the Drugs!");
          }
          else
          {
            p.sendMessage(this.perm);
          }
        }else{
        	p.sendMessage(ChatColor.RED + "Error: " + ChatColor.WHITE + "You don't have any Potions active.");
        }
      }else{
    	  sender.sendMessage("Only players may use this command.");
      }
     }
      else if (args[0].equalsIgnoreCase("help"))
      {
        if ((sender instanceof Player))
        {
          Player p = (Player)sender;
          if (p.hasPermission("drugs.help")) /* ChatColor.RED + "==============" + */
          {
            sender.sendMessage(prefix);
            sender.sendMessage(this.dash + ChatColor.GREEN + "Wheat " + ChatColor.WHITE + "(Weed)" + ChatColor.GRAY + 
              " Effects: " + ChatColor.AQUA + "Speed Boost,Confusion");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Sugar " + ChatColor.WHITE + "(Cocaine)" + ChatColor.GRAY + 
              " Effects: " + ChatColor.AQUA + "Night Vison,Increase Damage,Fire Resistance, Speed II");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Paper " + ChatColor.WHITE + "(Acid)" + ChatColor.GRAY + 
              " Effects: " + ChatColor.AQUA + "Night Vison,Jump Boost");
            sender.sendMessage(this.dash + ChatColor.GREEN + "GunPowder " + ChatColor.WHITE + "(PowPow)" + ChatColor.GRAY + 
              " Effects: " + ChatColor.AQUA + "Jump Boost,Health Regeneration,Speed Boost,Confusion");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Bone " + ChatColor.WHITE + "(Angel Dust)" + ChatColor.GRAY + 
              " Effects: " + ChatColor.AQUA + "Speed Boost,Confusion");
          }
          else
          {
            p.sendMessage(this.perm);
          }
        }
        else
        {
          sender.sendMessage(prefix);
          sender.sendMessage(this.dash + ChatColor.GREEN + "Wheat " + ChatColor.WHITE + "(Weed)" + ChatColor.GRAY + 
            " Effects: " + ChatColor.AQUA + "Speed Boost,Confusion");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Sugar " + ChatColor.WHITE + "(Cocaine)" + ChatColor.GRAY + 
            " Effects: " + ChatColor.AQUA + "Night Vison,Increase Damage,Fire Resistance,Speed II");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Paper " + ChatColor.WHITE + "(Acid)" + ChatColor.GRAY + 
            " Effects: " + ChatColor.AQUA + "Night Vison,Jump Boost");
          sender.sendMessage(this.dash + ChatColor.GREEN + "GunPowder " + ChatColor.WHITE + "(PowPow)" + ChatColor.GRAY + 
            " Effects: " + ChatColor.AQUA + "Jump Boost,Health Regeneration,Speed Boost,Confusion");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Bone " + ChatColor.WHITE + "(Angel Dust)" + ChatColor.GRAY + 
            " Effects: " + ChatColor.AQUA + "Speed Boost,Confusion");
        }
      }
      else if (args[0].equalsIgnoreCase("changelog"))
      {
        if ((sender instanceof Player))
        {
          Player p = (Player)sender;
          if (p.hasPermission("drugs.changelog"))
          {
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "Simple Drugs ChangeLog" + ChatColor.GRAY + "]==========");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.5" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "Uploaded the Plugin to BukkitDev.");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.6" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "Added Auto-Updater");
            p.sendMessage(this.dash1 + "Added Metritcs");
            p.sendMessage(this.dash1 + "Uploaded source to GitHub");
            p.sendMessage(this.dash1 + "Made code more stable.");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.7" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "BUG FIX: Drugs can now be right clicked on blocks.");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.8" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "BUG FIX: Drugs will be removed on use and creative bug fix as well.");
            p.sendMessage(this.dash1 + "REMOVED: Drugs may no longer be clicked on blocks.");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.9" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "REMOVED: Mushrooms as a drug");
            p.sendMessage(this.dash1 + "Updated Plugin");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.1" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "REMOVED: Some drug effects");
            p.sendMessage(this.dash1 + "UPDATED: 1.5.2-R0.1");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.2" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "UPDATED: 1.6.2-R0.1");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.3" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "UPDATED: 1.8");
            p.sendMessage(this.dash1 + "BUG FIX: Fixed random problems");
            p.sendMessage(this.dash1 + "Now able to use drugs in both gamemodes");
            p.sendMessage(this.dash1 + "Now able to use drugs on both air and blocks.");
            p.sendMessage(this.dash1 + "REMOVED: Command /drgus reload");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.4" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "REMOVED: Melons and Milk");
            p.sendMessage(this.dash1 + "Cleaned Up code");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.1" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "ADDED: New command /changelog");
            p.sendMessage(this.dash1 + "REMOVED: All Blindness effects for drugs.");
            p.sendMessage(this.dash1 + "Updated code on GitHub");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.2" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "ADDED: View effects with /drugs help");
            p.sendMessage(this.dash1 + "ADDED: Plugin Metrics back in");
            p.sendMessage(this.dash1 + "Fixed Version and Spelling Errors");
            p.sendMessage(this.dash1 + "ADDED: Console Changelog command");
            p.sendMessage(this.dash1 + "ADDED: Permission for Changelog command");
            p.sendMessage(this.dash1 + "Updated code on GitHub");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.3" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "ADDED: Reload Command '/dreload'");
            p.sendMessage(this.dash1 + "Cleaned Up and Udated Code");
            p.sendMessage(this.dash1 + "Updated code on GitHub");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.4" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "REMOVED: Plugin Metrics");
            p.sendMessage(this.dash1 + "Updated code on GitHub");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.6" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "Re-wrote commands");
            p.sendMessage(this.dash1 + "FIXED: Removed Command bugs");
            p.sendMessage(this.dash1 + "ADDED: Sounds and effects to commands /drugs remove");
            p.sendMessage(this.dash1 + "REMOVED: Reload Command" + ChatColor.GOLD + " (" + 
              ChatColor.GRAY + "Will be coming back in next update" + ChatColor.GOLD + ")");
            p.sendMessage(this.dash1 + "ADDED: Plugin Metrics back.");
            p.sendMessage(this.dash1 + "Cleaned my room as well.");
            p.sendMessage(this.dash1 + "Updated code on GitHub");
            p.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.7" + ChatColor.GRAY + "]==========");
            p.sendMessage(this.dash1 + "Oh boy! Coder what do we have this time?");
            p.sendMessage(this.dash1 + "Re-DID the config file!" + ChatColor.RED + " (You wanna delete your old config.yml)" );
            p.sendMessage(this.dash1 + "ADDDED: Reloading back in! " + ChatColor.GOLD + "/drugs reload");
            p.sendMessage(this.dash1 + "Did some performce fixes and a bunch of other stuff you guys don't care about!");
            p.sendMessage(this.dash1 + "REMOVED PLUGIN METRICS AGAIN FOR THE LAST AND FINAL TIME! ");
            p.sendMessage(this.dash1 + "Updated code on GitHub");
          }
        }
        else
        {
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "Simple Drugs ChangeLog" + ChatColor.GRAY + "]==========");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.5" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "Usenderloaded the senderlugin to BukkitDev.");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.6" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "Added Auto-Usenderdater");
          sender.sendMessage(this.dash1 + "Added Metritcs");
          sender.sendMessage(this.dash1 + "Usenderloaded source to GitHub");
          sender.sendMessage(this.dash1 + "Made code more stable.");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.7" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "BUG FIX: Drugs can now be right clicked on blocks.");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.8" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "BUG FIX: Drugs will be removed on use and creative bug fix as well.");
          sender.sendMessage(this.dash1 + "REMOVED: Drugs may no longer be clicked on blocks.");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v1.9" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "REMOVED: Mushrooms as a drug");
          sender.sendMessage(this.dash1 + "Updated senderlugin");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.1" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "REMOVED: Some drug effects");
          sender.sendMessage(this.dash1 + "Updated: 1.5.2-R0.1");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.2" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "Updated: 1.6.2-R0.1");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.3" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "Updated: 1.8");
          sender.sendMessage(this.dash1 + "BUG FIX: Fixed random senderroblems");
          sender.sendMessage(this.dash1 + "Now able to use drugs in both gamemodes");
          sender.sendMessage(this.dash1 + "Now able to use drugs on both air and blocks.");
          sender.sendMessage(this.dash1 + "REMOVED: Command /drgus reload");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.4" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "REMOVED: Melons and Milk");
          sender.sendMessage(this.dash1 + "Cleaned Usender code");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.1" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "ADDED: New command /changelog");
          sender.sendMessage(this.dash1 + "REMOVED: All Blindness effects for drugs.");
          sender.sendMessage(this.dash1 + "Updated code on GitHub");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.2" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "ADDED: View effects with /drugs helsender");
          sender.sendMessage(this.dash1 + "ADDED: senderlugin Metrics back in");
          sender.sendMessage(this.dash1 + "Fixed Version and Ssenderelling Errors");
          sender.sendMessage(this.dash1 + "ADDED: Console Changelog command");
          sender.sendMessage(this.dash1 + "ADDED: senderermission for Changelog command");
          sender.sendMessage(this.dash1 + "Updated code on GitHub");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.3" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "ADDED: Reload Command '/dreload'");
          sender.sendMessage(this.dash1 + "Cleaned and Udated Code");
          sender.sendMessage(this.dash1 + "Updated code on GitHub");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.4" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "REMOVED: Plugin Metrics");
          sender.sendMessage(this.dash1 + "Updated code on GitHub");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.6" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "Re-wrote commands");
          sender.sendMessage(this.dash1 + "FIXED: Removed Command bugs");
          sender.sendMessage(this.dash1 + "ADDED: Sounds and effects to commands /drugs remove");
          sender.sendMessage(this.dash1 + "REMOVED: Reload Command" + ChatColor.GOLD + "(" + 
            ChatColor.GRAY + "Will be coming back in next update" + ChatColor.GOLD + ")");
          sender.sendMessage(this.dash1 + "ADDED: Plugin Metrics back.");
          sender.sendMessage(this.dash1 + "Cleaned my room as well.");
          sender.sendMessage(this.dash1 + "Updated code on GitHub");
          sender.sendMessage(ChatColor.GRAY + "==========[" + ChatColor.GOLD + "v2.5.7" + ChatColor.GRAY + "]==========");
          sender.sendMessage(this.dash1 + "Oh boy! Coder what do we have this time?");
          sender.sendMessage(this.dash1 + "Re-DID the config file!" + ChatColor.RED + " (You wanna delete your old config.yml)" );
          sender.sendMessage(this.dash1 + "ADDDED: Reloading back in! " + ChatColor.GOLD + "/drugs reload");
          sender.sendMessage(this.dash1 + "Did some performce fixes and a bunch of other stuff you guys don't care about!");
          sender.sendMessage(this.dash1 + "REMOVED PLUGIN METRICS AGAIN FOR THE LAST AND FINAL TIME! ");
          sender.sendMessage(this.dash1 + "Updated code on GitHub");
        }

        
      }else if (args[0].equalsIgnoreCase("reload")){
    	  try{
    		  if(sender instanceof Player){
        		  Player p = (Player) sender;
    			  if(p.hasPermission("drugs.reload")){
    		  p.sendMessage(ChatColor.GREEN + "Reloading config...");
    		  p.performCommand("dreload");
    		  p.sendMessage(ChatColor.GREEN + "Reloaded Config");
    			  }else{
    				  p.sendMessage(perm);
    			  }
    		  }else{

    			sender.sendMessage(ChatColor.GREEN + "Hey sorry, I'm working on fixing this! Stick around for 2.6.0!");
    			sender.sendMessage(ChatColor.GREEN + "If you are a player the command will work just fine.");
    		  }
    	  }catch (Exception e){
    		  sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.WHITE + "Config failed to load.");
    		  e.printStackTrace();
    	  }
      }
    }
    return true;
  }
}
