package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class KillerCommands implements CommandExecutor {
	
	
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

   public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
	   if(sender instanceof Player) {
		   Player p = (Player) sender;
		   if(command.getName().equalsIgnoreCase("drugs")) {
			   if(p.hasPermission("drugs.main")) {
				   if(args.length == 0) {
					   //INFO - If the Player only types '/drugs'
					   p.sendMessage(ChatColor.DARK_RED + "========" 
					    + ChatColor.WHITE + "[Simple-Drugs]" + ChatColor.DARK_RED + "========");
					   p.sendMessage(ChatColor.ITALIC + "Use these following commands.");
					   p.sendMessage(ChatColor.DARK_GRAY + "- " 
					   + ChatColor.WHITE + "/drugs help");
					   p.sendMessage(ChatColor.DARK_GRAY + "- " 
					   + ChatColor.WHITE + "/drugs craft");
					   p.sendMessage(ChatColor.DARK_GRAY + "- " 
					   + ChatColor.WHITE + "/drugs soberup");
				   }
				   else if(args.length == 1) {
					  if(args[0].equalsIgnoreCase("help")) {
						  if(p.hasPermission("drugs.help")) {
							  //INFO - Gives a brief description of how to use the commands and new mechanics.
							  //TODO - Flush the '/drugs help' command a bit more out.
							  p.sendMessage(ChatColor.DARK_RED + "========" 
							+ ChatColor.WHITE + "[Simple-Drugs]" + ChatColor.DARK_RED + "========");
							  p.sendMessage("Use the command '/drugs craft' with the right "
							  	+ "amount of items and the right kind to make drugs.");
							  p.sendMessage("Use the command '/drugs soberup' to remove all PotionEffects");
						  }
					  }
					  else if(args[0].equalsIgnoreCase("craft")) {
						  if(p.hasPermission("drugs.craft")) {
							  //TODO - Make drugs craftable with commands...fuck.
							  ConfigurationSection drugs = plugin.drugsConfig.getConfigurationSection("Core.Drugs");
							  for (String drugsKeys : drugs.getKeys(false)) {
								  ItemStack drug = new ItemStack(Material.valueOf(plugin.drugsConfig.getString("Core.Drugs." 
							      +drugsKeys+ ".item")));
								  if(p.getInventory().getItemInMainHand().getAmount() == 
										  plugin.drugsConfig.getInt("Core.Drugs." +drugsKeys+ ".amountneededtocraft")) {
									  p.getInventory().getItemInMainHand().setType(Material.AIR);
									  String drugName = ChatColor.translateAlternateColorCodes('&', plugin.drugsConfig.getString("Core.Drugs."
											  +drugsKeys+ ".name"));
									  ItemMeta drugMeta = drug.getItemMeta();
									  drugMeta.setDisplayName(drugName);
									  for(String enchant : plugin.drugsConfig.getStringList("Core.Drugs." +drugsKeys+ ".effects")) {
					                        String enchantname = enchant.split(":")[0];
					                        Integer enchantlvl = Integer.valueOf(enchant.split(":")[1]);
					                        drugMeta.addEnchant(EnchantmentWrapper.getByKey(NamespacedKey.minecraft(enchantname)), enchantlvl, true);
					                        
					                  }
									  drug.setItemMeta(drugMeta);
									  drug.setAmount(plugin.drugsConfig.getInt("Core.Drugs." +drugsKeys+ ".givenamount"));
									  p.getInventory().setItemInMainHand(drug);
								  }else {
									  p.sendMessage("You need " + plugin.drugsConfig.getInt("Core.Drugs." +drugsKeys+ ".amountneededtocraft"));
								  }
								  
								  
							  }

						  }
					  }
					  //INFO - This is done.
					  else if(args[0].equalsIgnoreCase("soberup")) {
						  if(p.hasPermission("drugs.soberup")) {
							  if(!p.getActivePotionEffects().isEmpty()) {
								  for(PotionEffect effect : p.getActivePotionEffects()) {
									  p.removePotionEffect(effect.getType());
								  }
								}else {
									p.sendMessage("You need some drugs.");
								}
							  }
						  }
				   }else {
					   if(args.length >= 1) {
						   p.sendMessage("Too Many Args");
					   }
				   }
			   }
		   }
	   }else {
		   Bukkit.getServer().getLogger().severe(ChatColor.DARK_RED + "Don't fucking do that.");
	   }
      return true;
   }
}
