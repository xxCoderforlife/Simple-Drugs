package me.Coderforlife.Drugs;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events
  implements Listener
{
  Logger logger = Logger.getLogger("Minecraft");
  public final String prefix2 = ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Drugs" + ChatColor.WHITE + "] " +ChatColor.RESET;
  public final String prefix3 = ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Simple Drugs" + ChatColor.WHITE + "] " + ChatColor.RESET;
  private Main plugin;
  
  public Events(Main plugin)
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
  
  @SuppressWarnings("deprecation")
@EventHandler
  public void onRightClick(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if(this.plugin.getConfig().getBoolean("Drugs.Toggle.shift")){
    if(p.isSneaking()){
    	
    org.bukkit.inventory.ItemStack itemStack = p.getInventory().getItemInMainHand();
    int amount = itemStack.getAmount();
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.wheat")) && 
      (p.hasPermission("drugs.wheat")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) 
      && (p.getInventory().getItemInMainHand().getType() == Material.WHEAT))    {
    	for(String effectString : this.plugin.getConfig().getStringList("Drugs.Items.Weed.Effects")) {
    	    PotionEffectType wtype = PotionEffectType.getByName(effectString.toUpperCase());
    	 
    	    //if type is null that means that the type could not be interpreted
    	    if(wtype == null) {
    	        System.err.println(effectString + " could not be interpreted into a correct PotionEffectType.");
    	        continue;
    	    }
    	 
    	    //if the player already has the potion effect, make sure to remove it at first
    	    if(p.hasPotionEffect(wtype))
    	        p.removePotionEffect(wtype);
    	 
    	    //create the effect
    	    PotionEffect weffect = wtype.createEffect(20 * 30 /* duration in ticks */, 1 /* strength */);
    	 
    	    //apply effect to the player
    	    p.addPotionEffect(weffect);
    	}
     /* p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length",1), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
*/
      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used marijuana.");
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Weed");
      }
      if (amount > 1) {
      	itemStack.setAmount(amount - 1);
      	p.getInventory().setItemInMainHand(itemStack);
      }
      if (amount == 1) {
      	p.getInventory().setItemInMainHand(null);;
      	}
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.sugar")) && 
      (p.hasPermission("drugs.sugar")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() ==  Action.RIGHT_CLICK_BLOCK )
      && (p.getInventory().getItemInMainHand().getItemMeta().hasItemFlag(ItemFlag.valueOf("coke"))))
    {

        if (amount > 1) {
          	itemStack.setAmount(amount - 1);
          }
          if (amount == 1) {
          	p.getInventory().setItemInMainHand(null);
          	}
          
      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
        p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Coke.");

       
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Cocaine");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.paper")) && 
      (p.hasPermission("drugs.paper")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
      && (p.getInventory().getItemInMainHand().getType() == Material.PAPER))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Acid.");
      if (amount > 1) {
        	itemStack.setAmount(amount - 1);
        	p.getInventory().setItemInMainHand(itemStack);
        }
        if (amount == 1) {
        	p.getInventory().setItemInMainHand(null);
        	}
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Acid");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.bone")) && 
      (p.hasPermission("drugs.bone")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) 
      && (p.getInventory().getItemInMainHand().getType() == Material.BONE))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Angel Dust.");
      if (amount > 1) {
        	itemStack.setAmount(amount - 1);
        	p.getInventory().setItemInMainHand(itemStack);
        }
        if (amount == 1) {
        	p.getInventory().setItemInMainHand(null);
        	}
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Angel Dust");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.beet")) && 
    	      (p.hasPermission("drugs.beet")) && 
    	      (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() ==  Action.RIGHT_CLICK_BLOCK )
    	      && (p.getInventory().getItemInMainHand().getType() == Material.BEETROOT))
    	    {
    	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
    	      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
    	      p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
    	      p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

    	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Heroin.");
    	      p.getInventory().setItemInMainHand(null);
    	      
    	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
    	        this.logger.info(p.getName() + " Used Heroin");
    	      }
    	 

    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.cactus")) && 
  	      (p.hasPermission("drugs.cactus")) && 
  	      (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() ==  Action.RIGHT_CLICK_BLOCK )
  	      && (p.getInventory().getItemInMainHand().getType() == Material.INK_SACK 
  	      && p.getInventory().getItemInMainHand().getDurability() == 2))
    	//Use Ink Sack and Durability to check for a cactus green.
    	//Cactus Data value = 2
  	    {
  	      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
  	      p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
  	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
  	      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

  	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Hash.");
  	      if (amount > 1) {
  	        	itemStack.setAmount(amount - 1);
  	        	p.getInventory().setItemInMainHand(itemStack);
  	        }
  	        if (amount == 1) {
  	        	p.getInventory().setItemInMainHand(null);
  	        	}
  	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
  	        this.logger.info(p.getName() + " Used Hash");
  	      }

    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.wart")) && 
    	      (p.hasPermission("drugs.wart")) && 
    	      (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() ==  Action.RIGHT_CLICK_BLOCK )
    	      && (p.getInventory().getItemInMainHand().getTypeId() == 372))

    	    {
    	      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
    	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
    	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
    	      p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

    	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Shrooms.");
    	      if (amount > 1) {
    	        	itemStack.setAmount(amount - 1);
    	        	p.getInventory().setItemInMainHand(itemStack);
    	        }
    	        if (amount == 1) {
    	        	p.getInventory().setItemInMainHand(null);
    	        	}
    	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
    	        this.logger.info(p.getName() + " Used Shrooms");
    	      }
    	    }
   }else{
	   //Do whatever it normally does.
   }
  }else{
	    org.bukkit.inventory.ItemStack itemStack = p.getInventory().getItemInMainHand();
	    int amount = itemStack.getAmount();
	    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.wheat")) && 
	    	      (p.hasPermission("drugs.wheat")) && 
	    	      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) 
	    	      && (p.getInventory().getItemInMainHand().getType() == Material.WHEAT))    {
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

	    	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used marijuana.");
	    	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
	    	        this.logger.info(p.getName() + " Used Weed");
	    	      }
	    	      if (amount > 1) {
	    	      	itemStack.setAmount(amount - 1);
	    	      	p.getInventory().setItemInMainHand(itemStack);
	    	      }
	    	      if (amount == 1) {
	    	      	p.getInventory().setItemInMainHand(null);;
	    	      	}
	    	    }
	    	    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.sugar")) && 
	    	      (p.hasPermission("drugs.sugar")) && 
	    	      (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() ==  Action.RIGHT_CLICK_BLOCK )
	    	      && (p.getInventory().getItemInMainHand().getType() == Material.SUGAR))
	    	    {
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

	    	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Coke.");
	    	      if (amount > 1) {
	    	        	itemStack.setAmount(amount - 1);
	    	        	p.getInventory().setItemInMainHand(itemStack);
	    	        }
	    	        if (amount == 1) {
	    	        	p.getInventory().setItemInMainHand(null);
	    	        	}
	    	       
	    	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
	    	        this.logger.info(p.getName() + " Used Cocaine");
	    	      }
	    	    }
	    	    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.paper")) && 
	    	      (p.hasPermission("drugs.paper")) && 
	    	      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
	    	      && (p.getInventory().getItemInMainHand().getType() == Material.PAPER))
	    	    {
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

	    	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Acid.");
	    	      if (amount > 1) {
	    	        	itemStack.setAmount(amount - 1);
	    	        	p.getInventory().setItemInMainHand(itemStack);
	    	        }
	    	        if (amount == 1) {
	    	        	p.getInventory().setItemInMainHand(null);
	    	        	}
	    	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
	    	        this.logger.info(p.getName() + " Used Acid");
	    	      }
	    	    }
	    	    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.bone")) && 
	    	      (p.hasPermission("drugs.bone")) && 
	    	      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) 
	    	      && (p.getInventory().getItemInMainHand().getType() == Material.BONE))
	    	    {
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Angel Dust.");
	    	      if (amount > 1) {
	    	        	itemStack.setAmount(amount - 1);
	    	        	p.getInventory().setItemInMainHand(itemStack);
	    	        }
	    	        if (amount == 1) {
	    	        	p.getInventory().setItemInMainHand(null);
	    	        	}
	    	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
	    	        this.logger.info(p.getName() + " Used Angel Dust");
	    	      }
	    	    }
	    	    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.beet")) && 
	    	    	      (p.hasPermission("drugs.beet")) && 
	    	    	      (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() ==  Action.RIGHT_CLICK_BLOCK )
	    	    	      && (p.getInventory().getItemInMainHand().getType() == Material.BEETROOT))
	    	    	    {
	    	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

	    	    	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Heroin.");
	    	    	      p.getInventory().setItemInMainHand(null);
	    	    	      
	    	    	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
	    	    	        this.logger.info(p.getName() + " Used Heroin");
	    	    	      }
	    	    	 

	    	    }
	    	    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.cactus")) && 
	    	  	      (p.hasPermission("drugs.cactus")) && 
	    	  	      (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() ==  Action.RIGHT_CLICK_BLOCK )
	    	  	      && (p.getInventory().getItemInMainHand().getType() == Material.INK_SACK 
	    	  	      && p.getInventory().getItemInMainHand().getDurability() == 2))
	    	    	//Use Ink Sack and Durability to check for a cactus green.
	    	    	//Cactus Data value = 2
	    	  	    {
	    	  	      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	  	      p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	  	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	  	      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

	    	  	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Hash.");
	    	  	      if (amount > 1) {
	    	  	        	itemStack.setAmount(amount - 1);
	    	  	        	p.getInventory().setItemInMainHand(itemStack);
	    	  	        }
	    	  	        if (amount == 1) {
	    	  	        	p.getInventory().setItemInMainHand(null);
	    	  	        	}
	    	  	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
	    	  	        this.logger.info(p.getName() + " Used Hash");
	    	  	      }

	    	    }
	    	    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.wart")) && 
	    	    	      (p.hasPermission("drugs.wart")) && 
	    	    	      (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() ==  Action.RIGHT_CLICK_BLOCK )
	    	    	      && (p.getInventory().getItemInMainHand().getTypeId() == 372))

	    	    	    {
	    	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
	    	    	      p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

	    	    	      p.sendMessage(prefix2 + ChatColor.DARK_RED + "You used Shrooms.");
	    	    	      if (amount > 1) {
	    	    	        	itemStack.setAmount(amount - 1);
	    	    	        	p.getInventory().setItemInMainHand(itemStack);
	    	    	        }
	    	    	        if (amount == 1) {
	    	    	        	p.getInventory().setItemInMainHand(null);
	    	    	        	}
	    	    	      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
	    	    	        this.logger.info(p.getName() + " Used Shrooms");
	    	    	      }
	    	    	    } 
  }
 }
}
