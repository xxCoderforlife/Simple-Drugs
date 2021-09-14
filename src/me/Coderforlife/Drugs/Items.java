package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

public class Items implements Listener {
	
	public static String prefix = ChatColor.GRAY +
			""
			+ ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" +
			ChatColor.BOLD + "SD" + 
			ChatColor.GRAY + "" +
			ChatColor.BOLD + "] " + ChatColor.RESET;

   private Main plugin;

   public Items(Main plugin) {
      this.setPlugin(plugin);
   }

   public Main getPlugin() {
      return this.plugin;
   }

   public void setPlugin(Main plugin) {
      this.plugin = plugin;
   }
  @EventHandler
  public void RightClickEvent (PlayerInteractEvent e) {
	  Player p = (Player) e.getPlayer();
	  Action pa = e.getAction();
	  if(pa.equals(Action.RIGHT_CLICK_AIR) || pa.equals(Action.RIGHT_CLICK_BLOCK)) {
		  if(p.getInventory().getItemInMainHand().getType() == null) {return;}
		  if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
		  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}

		  //WEED
		  if(p.hasPermission("drugs.use.weed")) {
			  try {
					  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "" 
			  + ChatColor.BOLD + "WEED")) {
						  if(p.getInventory().getItemInMainHand().getAmount() > 1) {
							  p.sendMessage(prefix + "Do not use it in a stack.");
						  }else {
							  p.addPotionEffect(PotionEffectType.SLOW.createEffect(20*60*1, 1));
							  p.addPotionEffect(PotionEffectType.SLOW_DIGGING.createEffect(20*60*1, 1));
							  p.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(20*60*1, 1));
							  p.addPotionEffect(PotionEffectType.LUCK.createEffect(20*60*1, 1));    
							  p.getInventory().getItemInMainHand().getAmount();
							  p.getInventory().getItemInMainHand().setAmount(0);
							  if(p.getInventory().getItemInMainHand().getType() == null) {return;}
							  if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
							  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
						  }

					  }
			  }catch (Exception e1) {
				  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
				  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
				  e1.printStackTrace();
				  
			  }
		  }else {
			  p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
			  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.weed");
		  }
		  //END OF WEED
		  
		  //COKE
		  if(p.hasPermission("drugs.use.coke")) {
			  try {
			  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA +""
							  + ChatColor.BOLD + "COKE")) {
				  if(p.getInventory().getItemInMainHand().getAmount() > 1) {
					  p.sendMessage(prefix + "Do not use it in a stack.");
				  }else {
					  p.addPotionEffect(PotionEffectType.SPEED.createEffect(20*60*1, 1));
					  p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(20*60*1, 1));
					  p.addPotionEffect(PotionEffectType.GLOWING.createEffect(20*60*2, 1));
					  p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(20*60*2, 2));
					  p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(20*60*2, 2));
					  p.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(20*60*2, 1));
					  p.getInventory().getItemInMainHand().getAmount();
					  p.getInventory().getItemInMainHand().setAmount(0);
					  if(p.getInventory().getItemInMainHand().getType() == null) {return;}
					  if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
					  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
				  }					 
			   }				  
			  }catch(Exception e1) {
				  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
				  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
				  e1.printStackTrace();
			  }
		  }else {
			  p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
			  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.coke");
		  }
		  //END OF COKE
		  
		  //HEROIN
		  if(p.hasPermission("drugs.use.heroin")) {
			  try {
					  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLACK + "" + 
				  ChatColor.MAGIC + "HEROINHEROIN")) {
						  if(p.getInventory().getItemInMainHand().getAmount() > 1) {
							  p.sendMessage(prefix + "Do not use it in a stack.");
						  }else {
							  p.addPotionEffect(PotionEffectType.SLOW.createEffect(20*60*1, 1));
							  p.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(20*60*1, 1));
							  p.addPotionEffect(PotionEffectType.POISON.createEffect(20*60*1, 1));
							  p.addPotionEffect(PotionEffectType.BAD_OMEN.createEffect(20*60*1, 1));
							  p.addPotionEffect(PotionEffectType.UNLUCK.createEffect(20*60*1, 1));
							  p.getInventory().getItemInMainHand().getAmount();
							  p.getInventory().getItemInMainHand().setAmount(0);
							  if(p.getInventory().getItemInMainHand().getType() == null) {return;}
							  if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
							  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
						  }
					  }
			  }catch(Exception e1) {
				  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
				  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
				  e1.printStackTrace();
			  }
		  }else {
			  p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
			  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.heroin");
		  }
		  //END OF HEROIN
		  
		  //PERCOCET
		  if(p.hasPermission("drugs.use.percocet")) {
			  try {
				  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "" + 
			  ChatColor.BOLD + "PERCOCET")) {
					  if(p.getInventory().getItemInMainHand().getAmount() > 1) {
						  p.sendMessage(prefix + "Do not use it in a stack.");
					  }else {
						  p.addPotionEffect(PotionEffectType.SLOW.createEffect(20*60*1, 1));
						  p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(200, 1));
						  p.addPotionEffect(PotionEffectType.GLOWING.createEffect(20*60*1, 1));
						  p.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(20*60*1, 1));
						  p.addPotionEffect(PotionEffectType.LUCK.createEffect(20*60*1, 1));
						  p.getInventory().getItemInMainHand().getAmount();
						  p.getInventory().getItemInMainHand().setAmount(0);
						  if(p.getInventory().getItemInMainHand().getType() == null) {return;}
						  if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
						  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
					  }
				  }
		  }catch(Exception e1) {
			  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
			  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
			  e1.printStackTrace();
		  }
			  		  
		}else {
			p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
			  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.percocet");
		}
	      //END OF PERCOCET
		  
		//ACID
		  if(p.hasPermission("drugs.use.acid")) {
			  try {
				  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + 
			  ChatColor.BOLD + "ACID")) {
					  if(p.getInventory().getItemInMainHand().getAmount() > 1) {
						  p.sendMessage(prefix + "Do not use it in a stack.");
					  }else {
						  p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(200, 1));
						  p.addPotionEffect(PotionEffectType.GLOWING.createEffect(20*60*5, 3));
						  p.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(20*60*2, 2)); 
						  p.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(20*60*5, 2)); 
						  p.getInventory().getItemInMainHand().getAmount();
						  p.getInventory().getItemInMainHand().setAmount(0);
						  if(p.getInventory().getItemInMainHand().getType() == null) {return;}
						  if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
						  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
					  }
				  }
		  }catch(Exception e1) {
			  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
			  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
			  e1.printStackTrace();
		  }		  
	  }else {
		  p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
		  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.acid");
	  }
     //END OF ACID
	 }
   }
  
 }
