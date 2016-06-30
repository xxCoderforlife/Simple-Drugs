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
  public final String prefix = ChatColor.RED + "==============" + ChatColor.AQUA + "[Simple Drugs v2.6.0]"+ ChatColor.RED + "==============" ;
  final String dash = ChatColor.GRAY + "- ";
  public final String prefix2 = ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Drugs" + ChatColor.WHITE + "] " +ChatColor.RESET;
  final String dash1 = ChatColor.GOLD + "- " + ChatColor.GRAY;
  final String perm = ChatColor.RED + "You don't have the right permission";
  
  public <plugin> void setPlugin(Main plugin) {}
  
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
          for (PotionEffect effect : p.getActivePotionEffects())
        	  p.removePotionEffect(effect.getType());
            p.sendMessage( this.prefix2 + ChatColor.DARK_RED + "All drugs have been removed.");
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
          if (p.hasPermission("drugs.help")){
            sender.sendMessage(prefix);
            sender.sendMessage(this.dash + ChatColor.GREEN + "Wheat " + ChatColor.WHITE + "(Weed)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Sugar " + ChatColor.WHITE + "(Cocaine)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Paper " + ChatColor.WHITE + "(Acid)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Beet " + ChatColor.WHITE + "(Heroin)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Bone " + ChatColor.WHITE + "(Angel Dust)");
            sender.sendMessage(this.dash + ChatColor.GREEN + "Cactus Dye " + ChatColor.WHITE + "(Hash)");
            //sender.sendMessage(this.dash + ChatColor.GREEN + "Red and Brown Mushrooms " + ChatColor.WHITE + "(Shrooms)");
            //sender.sendMessage(this.dash + ChatColor.GREEN + "Nether Wart " + ChatColor.WHITE + "(Ecstasy)");



          }
          else
          {
            p.sendMessage(this.perm);
          }
        }
        else
        {
          sender.sendMessage(prefix);
          sender.sendMessage(this.dash + ChatColor.GREEN + "Wheat " + ChatColor.WHITE + "(Weed)");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Sugar " + ChatColor.WHITE + "(Cocaine)");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Paper " + ChatColor.WHITE + "(Acid)");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Beet " + ChatColor.WHITE + "(Heroin)");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Bone " + ChatColor.WHITE + "(Angel Dust)");
          sender.sendMessage(this.dash + ChatColor.GREEN + "Cactus Dye " + ChatColor.WHITE + "(Hash)");
        }
      }
        
      }else if (args[0].equalsIgnoreCase("reload")){
    	  try{
    		  if(sender instanceof Player){
        		  Player p = (Player) sender;
    			  if(p.hasPermission("drugs.reload")){
    		  p.sendMessage(ChatColor.GREEN + "Reloading config...");
    		  this.plugin.reloadConfig();
    		  p.sendMessage(ChatColor.GREEN + "Reloaded Config");
    			  }else{
    				  p.sendMessage(perm);
    			  }
    		  }else{

    			sender.sendMessage(ChatColor.GREEN + "Hey sorry, I'm working on fixing this! Stick around for 2.7.0!");
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