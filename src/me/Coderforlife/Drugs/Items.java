package me.Coderforlife.Drugs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Items implements Listener{
	
	public final String prefix2 = ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Drugs" + ChatColor.WHITE + "] " + ChatColor.RESET;
	
	private Main plugin;
	
	 public Items(Main plugin)
	  {
	    setPlugin(plugin);
	  }
	  
	  public Main getPlugin()
	  {
	    return this.plugin;
	  }
	  
	  public void setPlugin(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	  
	  @EventHandler
	  public void onRightClick(PlayerInteractEvent e) {
		  Player p = e.getPlayer();
		  
		  ItemStack itemStack = p.getInventory().getItemInMainHand();
	        int amount = itemStack.getAmount();
	        
	        
	       if(this.plugin.getConfig().getBoolean("Drugs.Toggle.sugar")) { 
		      if(p.hasPermission("drugs.sugar")) {
		        	if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		        		if(p.getInventory().getItemInMainHand().equals(Main.suagrd)) {
		        			
		        			 p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
					          p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
					          p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
					          p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
					          p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
					          p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
					          p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
					          
					          p.sendMessage(this.prefix2 + ChatColor.DARK_RED + "You used Coke.");
					          if (amount > 1)
					          {
					            itemStack.setAmount(amount - 1);
					            p.getInventory().setItemInMainHand(itemStack);
					          }
					          if (amount == 1) {
					            p.getInventory().getItemInMainHand().setAmount(0);;
					          }
		        		}else {
		        			//Do Nothing
		        		}
		        	}
		        }   
	       }
			  if(this.plugin.getConfig().getBoolean("Drugs.Toggle.wheat")) {
				  if(p.hasPermission("drugs.wheat")) {
					  if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						  if(p.getInventory().getItemInMainHand().equals(Main.wheatd)) {
							  
							  p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
        			          p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
        			          p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
        			          p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
        			          
        			          p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Marijuana.");
        			          if (amount > 1)
        			          {
        			            itemStack.setAmount(amount - 1);
        			            p.getInventory().setItemInMainHand(itemStack);
        			          }
        			          if (amount == 1) {
        			            p.getInventory().getItemInMainHand().setAmount(0);
        			          }
						  }else {
							  //Do nothing
						  }
					  }
				  }
			  }
			  if(this.plugin.getConfig().getBoolean("Drugs.Toggle.paper")) {
				  if(p.hasPermission("drugs.paper")) {
					  if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						  if(p.getInventory().getItemInMainHand().equals(Main.paperd)) {
							  
							  p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	        	                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	        	                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	        	                p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	        	                
	        	                p.sendMessage(this.prefix2 + ChatColor.DARK_RED + "You used Shrooms.");
	        	                if (amount > 1)
	        	                {
	        	                  itemStack.setAmount(amount - 1);
	        	                  p.getInventory().setItemInMainHand(itemStack);
	        	                }
	        	                if (amount == 1) {
	        	                  p.getInventory().getItemInMainHand().setAmount(0);
	        	                
	        	               }
						  }else {
							  //Do Nothing
						  }
					  }
				  }
			  }
			  if(this.plugin.getConfig().getBoolean("Drugs.Toggle.brown_mushroom")) {
				  if(p.hasPermission("drugs.brown_mushroom")) {
					  if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						  if(p.getInventory().getItemInMainHand().equals(Main.brown_mushroomd)) {
							  
							  p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	        	                p.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	        	                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	        	                
	        	                p.sendMessage(this.prefix2 + ChatColor.DARK_RED + "You used Acid.");
	        	                if (amount > 1)
	        	                {
	        	                  itemStack.setAmount(amount - 1);
	        	                  p.getInventory().setItemInMainHand(itemStack);
	        	                }
	        	                if (amount == 1) {
	        	                  p.getInventory().getItemInMainHand().setAmount(0);
	        	                
	        	               }
						  }else {
							  //Do Nothing
						  }
					  }
				  }
			  }
	  }
	  

}
