package me.Coderforlife.Drugs;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events
  implements Listener
{
  Logger logger = Logger.getLogger("Minecraft");
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
    org.bukkit.inventory.ItemStack itemStack = p.getInventory().getItemInMainHand();
    int amount = itemStack.getAmount();
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.wheat")) && 
      (p.hasPermission("drugs.wheat")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) 
      && (p.getInventory().getItemInMainHand().getType() == Material.WHEAT))    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.GREEN + "You used marijuana.");
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
      && (p.getItemInHand().getType() == Material.SUGAR))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);

      p.sendMessage(ChatColor.AQUA + "You just used Coke.");
      if (amount > 1) {
        	itemStack.setAmount(amount - 1);
        	p.setItemInHand(itemStack);
        }
        if (amount == 1) {
        	p.setItemInHand(null);
        	}
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Cocaine");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.paper")) && 
      (p.hasPermission("drugs.paper")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
      && (p.getItemInHand().getType() == Material.PAPER))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.AQUA + "You just used Acid.");
      if (amount > 1) {
        	itemStack.setAmount(amount - 1);
        	p.setItemInHand(itemStack);
        }
        if (amount == 1) {
        	p.setItemInHand(null);
        	}
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Acid");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.gunpowder")) && 
      (p.hasPermission("drugs.gun")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() ==  Action.RIGHT_CLICK_BLOCK)
      && (p.getInventory().getItemInMainHand().getType() == Material.SULPHUR))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.AQUA + "You just used Heroin.");
      if (amount > 1) {
        	itemStack.setAmount(amount - 1);
        	p.setItemInHand(itemStack);
        }
        if (amount == 1) {
        	p.setItemInHand(null);
        	}
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used PowPow");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.bone")) && 
      (p.hasPermission("drugs.bone")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) 
      && (p.getItemInHand().getType() == Material.BONE))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.AQUA + "You just used Angel Dust.");
      if (amount > 1) {
        	itemStack.setAmount(amount - 1);
        	p.setItemInHand(itemStack);
        }
        if (amount == 1) {
        	p.setItemInHand(null);
        	}
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Angel Dust");
      }
    }
  }
}
