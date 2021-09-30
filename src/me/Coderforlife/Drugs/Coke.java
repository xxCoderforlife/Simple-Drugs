package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

public class Coke implements Listener{

	public static String prefix = ChatColor.GRAY +
			""
			+ ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" +
			ChatColor.BOLD + "SD" + 
			ChatColor.GRAY + "" +
			ChatColor.BOLD + "] " + ChatColor.RESET;
	
	public static String stack = ChatColor.RED + "" + ChatColor.BOLD + "Do Not Use It In A Stack.";
	
	private Main plugin;

	   public Coke(Main plugin) {
	      this.setPlugin(plugin);
	   }

	   public Main getPlugin() {
	      return this.plugin;
	   }

	   public void setPlugin(Main plugin) {
	      this.plugin = plugin;
	   }
	   
		 @EventHandler
		 public void RightClickEvent(PlayerInteractEvent ev) {
			 Player p = ev.getPlayer();
			 Action pa = ev.getAction();
			 if(pa.equals(Action.RIGHT_CLICK_AIR) || pa.equals(Action.RIGHT_CLICK_BLOCK)) {
				 if(p.getInventory().getItemInMainHand().hasItemMeta()) {
					  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA +""
							  + ChatColor.BOLD + "COKE")) {
						  if(p.hasPermission("drugs.use.coke")) {
							  try {
								  if(p.getInventory().getItemInMainHand().getAmount() > 1) {
									  p.sendMessage(prefix + stack);
								  }else {
									  p.addPotionEffect(PotionEffectType.SPEED.createEffect(20*60*1, 1));
									  p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(20*60*1, 1));
									  p.addPotionEffect(PotionEffectType.GLOWING.createEffect(20*60*2, 1));
									  p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(20*60*2, 2));
									  p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(20*60*2, 2));
									  p.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(20*60*2, 1));
									  p.getInventory().getItemInMainHand().getAmount();
									  p.getInventory().getItemInMainHand().setAmount(0);
									  if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
									  if(p.getInventory().getItemInMainHand().getType() == null) {return;}
									  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
								  }					 				  
							  }catch(Exception e1) {
								  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
								  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
								  e1.printStackTrace();
							  }
						  }else {
							  p.sendMessage(prefix + ChatColor.DARK_RED + "You can't use" + ChatColor.AQUA
									  + "" + ChatColor.BOLD + " COKE");
						  }
					  }
				 }
			 }
		 }
}
