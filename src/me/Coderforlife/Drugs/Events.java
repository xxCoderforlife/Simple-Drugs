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
  
  @EventHandler
  public void onRightClick(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.wheat")) && 
      (p.hasPermission("drugs.wheat")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) 
      && (p.getItemInHand().getType() == Material.WHEAT))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.GREEN + "Like that Weed?");
      p.getItemInHand();
      p.setItemInHand(null);
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Weed");
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
      p.sendMessage(ChatColor.AQUA + "You just hit Cocaine!");
      p.getItemInHand();
      p.setItemInHand(null);
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
      p.sendMessage(ChatColor.AQUA + "You just hit Acid!");
      p.getItemInHand();
      p.setItemInHand(null);
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Acid");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.gunpowder")) && 
      (p.hasPermission("drugs.gun")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() ==  Action.RIGHT_CLICK_BLOCK)
      && (p.getItemInHand().getType() == Material.SULPHUR))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.AQUA + "PowPow!!!");
      p.getItemInHand();
      p.setItemInHand(null);
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
      p.sendMessage(ChatColor.AQUA + "Angel Dust!");
      p.getItemInHand();
      p.setItemInHand(null);
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Angel Dust");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Toggle.smoke")) && 
      (p.hasPermission("drugs.smoke")) && 
      (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() ==  Action.RIGHT_CLICK_BLOCK) 
      && (p.getItemInHand().getType() == Material.TORCH))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.GREEN + "Smoking Yeah " + ChatColor.RED + " <3");
      p.getItemInHand();
      p.setItemInHand(null);
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Just smoked.");
      }
    }
  }
}
