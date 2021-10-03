package me.Coderforlife.Drugs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class KillerCommands implements CommandExecutor {
	
	Drugs D =  new Drugs(this);
		
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
   
	public static String dash = ChatColor.DARK_GRAY + "" + ChatColor.BOLD +"- ";
	public static String header = ChatColor.DARK_RED + "" + ChatColor.BOLD + "==========" + 
	ChatColor.WHITE + "" + ChatColor.BOLD + "[SIMPLE-DRUGS]" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "==========";
    public static String prefix = ChatColor.GRAY + "" + ChatColor.BOLD + "[" 
                                 + ChatColor.DARK_RED + "" + ChatColor.BOLD + "SD"
                                 + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET;
    
   public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
	   if(sender instanceof Player) {
		   Player p = (Player) sender;
		   if(p.hasPermission("drugs.main")) {
			   if(args.length == 0) {
				p.sendMessage(header);
				p.sendMessage(ChatColor.ITALIC + "Use these following commands.");
				p.sendMessage(ChatColor.DARK_GRAY + "- " 
			     + ChatColor.WHITE + "/drugs help");
				p.sendMessage(ChatColor.DARK_GRAY + "- " 
				 + ChatColor.WHITE + "/drugs soberup");
				p.sendMessage(ChatColor.DARK_GRAY + "- " 
					     + ChatColor.WHITE + "/drugs list");
			   }else if(args.length == 1){
				   if(args[0].equalsIgnoreCase("help")) {
					   if(p.hasPermission("drugs.help")) {
						   p.sendMessage(header);
						   p.sendMessage(prefix + "Craft the drugs and Right-Click with in your hand.");
						   p.sendMessage(prefix + "Find out how to craft on the Wiki.");
						   p.sendMessage("https://github.com/xxCoderforlife/Simple-Drugs/wiki/FAQs-&-How-To#how-to-craft-and-use-drugs");
					   }else {
						   p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that command.");
						   p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.help");
					   }
				   }else if(args[0].equalsIgnoreCase("soberup")){
					   if(p.hasPermission("drugs.soberup")) {
						   if(!p.getActivePotionEffects().isEmpty()) {
								  for(PotionEffect effect : p.getActivePotionEffects()) {
									  p.removePotionEffect(effect.getType());
								  }
								}else {
									p.sendMessage(prefix + 
											ChatColor.RED + "You need some drugs.");
								}
					   }else {
						   p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that command.");						 				   
						   p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.soberup");
					   }
				   }else if(args[0].equalsIgnoreCase("list")) {
						   if(p.hasPermission("drugs.list")) {
							   p.sendMessage(header);
							   p.sendMessage(dash + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "WEED");
							   p.sendMessage(dash + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "MOLLY");
							   p.sendMessage(dash + ChatColor.AQUA + "" + ChatColor.BOLD + "COKE");
							   p.sendMessage(dash + ChatColor.WHITE + "" + ChatColor.BOLD + "HEROIN");
							   p.sendMessage(dash + ChatColor.WHITE + "" + ChatColor.BOLD + "PERCOCET");
							   p.sendMessage(dash + ChatColor.GOLD + "" + ChatColor.BOLD + "CIGGY");
							   p.sendMessage(dash + ChatColor.AQUA + "" + ChatColor.BOLD + "ACID");
							   }
					   
				   }
			   }else {
				   p.sendMessage("Don't do that");
			   }
		   }else {
			   p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that command.");
			   p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.main");
		   }
	   }
      return true;
   }
}
