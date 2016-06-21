package me.Coderforlife.Drugs;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
  public final String prefix = ChatColor.RED + "==============" + ChatColor.AQUA + "[Simple Drugs v2.5.9]"+ ChatColor.RED + "==============" ;
  final String dash = ChatColor.GRAY + "- ";
  final String prefix2 = ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Drugs" + ChatColor.WHITE + "] " +ChatColor.RESET;
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
          if(!(p.getActivePotionEffects().isEmpty())){
          if (p.hasPermission("drugs.remove"))
          {
            p.playSound(p.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1.0F, 1.0F);
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
            p.sendMessage( this.prefix2 + ChatColor.RED + " All drugs have been removed.");
          }
          else
          {
            p.sendMessage(this.perm);
          }
        }else{
        	p.sendMessage(this.prefix2 + ChatColor.WHITE + "You're not on any drugs.");
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
              " Effects: " + ChatColor.AQUA + "Hunger,Slowness");
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
            " Effects: " + ChatColor.AQUA + "Hunger,Slowness");
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
    		  sender.sendMessage(ChatColor.GREEN + "If this keeps happening tell me on Spigot");
    		  sender.sendMessage(ChatColor.GREEN + "DON'T FORGET TO BRING THIS ERROR!");
    		  e.printStackTrace();
    	  }
      }
    return true;
  }
}
