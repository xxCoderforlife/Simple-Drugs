package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Items implements Listener {
	
	public static String prefix = ChatColor.GRAY +
			""
			+ ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" +
			ChatColor.BOLD + "SD" + 
			ChatColor.GRAY + "" +
			ChatColor.BOLD + "] " + ChatColor.RESET;
	public static String stack = ChatColor.RED + "" + ChatColor.BOLD + "Do Not Use It In A Stack.";

   private Main plugin;
   private Drugs drugs;

   public Items(Main plugin) {
      this.setPlugin(plugin);
	  this.drugs = new Drugs(this.getPlugin());
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
		ItemStack hand = p.getInventory().getItemInMainHand();
		if(hand.getItemMeta() == null) {return;}
		if(hand.getType() == null) {return;}
		if(hand.getItemMeta().getDisplayName() == null) {return;}
		if(p.hasCooldown(hand.getType())) {return;}

		  //WEED
		  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "" 
  + ChatColor.BOLD + "WEED")) {	
			  if(p.hasPermission("drugs.use.weed")) {
				  try {
					int amount = hand.getAmount();
					p.addPotionEffect(PotionEffectType.SLOW.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.SLOW_DIGGING.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.LUCK.createEffect(20*60*1, 1));
					p.getInventory().getItemInMainHand().setAmount(amount - 1);
					p.setCooldown(drugs.weed1.getType(), 5/*sec*/*20/*ticks*/);
					if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
					if(p.getInventory().getItemInMainHand().getType() == null) {return;}
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
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
		  }
		  
		  //COKE
		  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA +""
				  + ChatColor.BOLD + "COKE")) {
			  if(p.hasPermission("drugs.use.coke")) {
				  try {
					int amount = hand.getAmount();
					p.addPotionEffect(PotionEffectType.SPEED.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.GLOWING.createEffect(20*60*2, 1));
					p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(20*60*2, 2));
					p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(20*60*2, 2));
					p.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(20*60*2, 1));
					p.getInventory().getItemInMainHand().setAmount(amount - 1);
					p.setCooldown(drugs.coke.getType(), 5/*sec*/*20/*ticks*/);
					if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
					if(p.getInventory().getItemInMainHand().getType() == null) {return;}
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
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
		  }
		  
		  //HEROIN
		  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLACK + "" + 
	  ChatColor.MAGIC + "HEROINHEROIN")) {
			  if(p.hasPermission("drugs.use.heroin")) {
				  try {
					int amount = hand.getAmount();
					p.addPotionEffect(PotionEffectType.SLOW.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.POISON.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.BAD_OMEN.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.UNLUCK.createEffect(20*60*1, 1));
					p.getInventory().getItemInMainHand().setAmount(amount - 1);
					if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
					if(p.getInventory().getItemInMainHand().getType() == null) {return;}
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
				  }catch(Exception e1) {
					  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
					  Bukkit.getLogger().severe(prefix + "Send this Error to xxCoderforlife on https://Spigotmc.org");
					  e1.printStackTrace();
				  }
			  }else {
				  p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
				  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.heroin");
			  }  
		  }
		  //END OF HEROIN
		  
		  //PERCOCET
		  if(p.hasPermission("drugs.use.percocet")) {
			  try {
				  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "" + 
			  ChatColor.BOLD + "PERCOCET")) {
					int amount = hand.getAmount();
					p.addPotionEffect(PotionEffectType.SLOW.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(200, 1));
					p.addPotionEffect(PotionEffectType.GLOWING.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(20*60*1, 1));
					p.addPotionEffect(PotionEffectType.LUCK.createEffect(20*60*1, 1));
					p.getInventory().getItemInMainHand().setAmount(amount - 1);
					p.setCooldown(drugs.percocet.getType(), 5/*sec*/*20/*ticks*/);
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
			p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
			  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.percocet");
		}
	      //END OF PERCOCET
		  
		//ACID
		  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + 
	  ChatColor.BOLD + "ACID")) {
			  if(p.hasPermission("drugs.use.acid")) {
				  try {
					int amount = hand.getAmount();
					p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(200, 1));
					p.addPotionEffect(PotionEffectType.GLOWING.createEffect(20*60*5, 3));
					p.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(20*60*2, 2)); 
					p.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(20*60*5, 2));
					p.playSound(p.getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 10, 29);
					p.getInventory().getItemInMainHand().setAmount(amount - 1);
					p.setCooldown(drugs.acid.getType(), 5/*sec*/*20/*ticks*/);
					if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
					if(p.getInventory().getItemInMainHand().getType() == null) {return;}
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
			  }catch(Exception e1) {
				  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
				  Bukkit.getLogger().severe(prefix + ChatColor.GREEN + "Send this Error to xxCoderforlife on https://Spigotmc.org");
				  e1.printStackTrace();
			  }		  
		  }else {
			  p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
			  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.acid");
		  }
	     //END OF ACID  
		  }
		  
	//MOLLY
		  if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "" + 
	  ChatColor.BOLD + "MOLLY")) {
			  if(p.hasPermission("drugs.use.molly")) {
				  try {
					int amount = hand.getAmount();
					p.setCooldown(drugs.molly.getType(), 5/*sec*/*20/*ticks*/);
					p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(200, 1));
					p.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(20*60*5, 1));
					p.addPotionEffect(PotionEffectType.SPEED.createEffect(20*60*5, 1));
					p.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(20*60*5, 1));
					p.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(20*60*5, 1));
					p.getInventory().getItemInMainHand().setAmount(amount - 1);
					if(p.getInventory().getItemInMainHand().getItemMeta() == null) {return;}
					if(p.getInventory().getItemInMainHand().getType() == null) {return;}
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {return;}
			  }catch(Exception e1) {
				  p.sendMessage(prefix + ChatColor.DARK_RED + "Error in the Console");
				  Bukkit.getLogger().severe(prefix + ChatColor.GREEN + "Send this Error to xxCoderforlife on https://Spigotmc.org");
				  e1.printStackTrace();
			  }		  
		  }else {
			  p.sendMessage(prefix + ChatColor.RED + "You don't have permission to use that drug.");
			  p.sendMessage(prefix +ChatColor.DARK_RED + "Permission: " + ChatColor.RED + "drugs.use.molly");
		  }
	     //END OF MOLLY  
		  }
	 }
   }
  
 }
