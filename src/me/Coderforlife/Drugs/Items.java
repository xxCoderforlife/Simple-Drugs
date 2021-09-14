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

		  if(p.hasPermission("drugs.use.weed")) {
			  try {
					  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "" 
			  + ChatColor.BOLD + "WEED")) {
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
			  }catch (Exception e1) {
				  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
				  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
				  e1.printStackTrace();
				  
			  }
		  }else {
			  p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
			  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.weed");
		  }
		  
		  if(p.hasPermission("drugs.use.coke")) {
			  try {
			  if(!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA +""
							  + ChatColor.BOLD + "COKE")) {

					  }else {
						  p.addPotionEffect(PotionEffectType.SPEED.createEffect(20*60*1, 1));
						  p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(20*60*1, 1));
						  p.addPotionEffect(PotionEffectType.GLOWING.createEffect(20*60*1, 1));
						  p.getInventory().getItemInMainHand().getAmount();
						  p.getInventory().getItemInMainHand().setAmount(0);
						  if(p.getInventory().getItemInMainHand().getType() == null) {return;}
						  if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
						  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
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
		  if(p.hasPermission("drugs.use.heroin")) {
			  try {
					  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLACK + "" + 
				  ChatColor.MAGIC + "HEROINHEROIN")) {			
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
			  }catch(Exception e1) {
				  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
				  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
				  e1.printStackTrace();
			  }
		  }
		  if(p.hasPermission("drugs.use.percocet")) {
			  try {
				  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "" + 
			  ChatColor.BOLD + "PERCOCET")) {			
					  p.addPotionEffect(PotionEffectType.SLOW.createEffect(10*60*1, 1));
					  p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(45*60*1, 1));
					  p.addPotionEffect(PotionEffectType.GLOWING.createEffect(45*60*1, 1));
					  p.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(45*60*1, 1));
					  p.addPotionEffect(PotionEffectType.LUCK.createEffect(45*60*1, 1));
					  p.getInventory().getItemInMainHand().getAmount();
					  p.getInventory().getItemInMainHand().setAmount(0);
					  if(p.getInventory().getItemInMainHand().getType() == null) {return;}
					  if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
					  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}

				  }
		  }catch(Exception e1) {
			  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
			  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
			  e1.printStackTrace();
		  }
		  }
	  }
   }
  
 }
