package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Molly implements Listener {

	public String MollyName = ChatColor.DARK_AQUA + "" + 
			  ChatColor.BOLD + "MOLLY";
	
	public static String prefix = ChatColor.GRAY +
			""
			+ ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" +
			ChatColor.BOLD + "SD" + 
			ChatColor.GRAY + "" +
			ChatColor.BOLD + "] " + ChatColor.RESET;
	
	public static String stack = ChatColor.RED + "" + ChatColor.BOLD + "Do Not Use It In A Stack.";
	
	public Molly() {
		return;
	}
	
	
	private Main plugin;

	   public Molly(Main plugin) {
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
				  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(MollyName)) {
					  if(p.hasPermission("drugs.use.molly")) {
									  try {
										  ItemStack hand = p.getInventory().getItemInMainHand();
										  int amount = hand.getAmount();
											  if(amount > 1) {
												  p.sendMessage(prefix + stack);
											  }else {
												  p.getInventory().getItemInMainHand().setAmount(0);
												  p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(200, 1));
												  p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(20*60*5, 1));
												  p.addPotionEffect(PotionEffectType.SPEED.createEffect(20*60*5, 1));
												  p.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(20*60*5, 1));
												  p.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(20*60*5, 1));
											  }
								  }catch(Exception e1) {
									  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
									  Bukkit.getLogger().severe(prefix + ChatColor.GREEN + "Send this Error to xxCoderforlife on https://Spigotmc.org");
									  e1.printStackTrace();
								  }		  
							  }else {
								  p.sendMessage(prefix + ChatColor.DARK_RED + "You can't use " + ChatColor.DARK_AQUA + 
										  "" + ChatColor.BOLD + "MOLLY"); 
								  
							  }
						     //END OF MOLLY  
							  }
		 
			 }
		 }
	 }
}
