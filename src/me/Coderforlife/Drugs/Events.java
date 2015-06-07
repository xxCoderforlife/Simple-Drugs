package me.Coderforlife.Drugs;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events
  implements Listener
{
  Logger logger = Logger.getLogger("Minecraft");
  private Drugs plugin;
  final String perm = ChatColor.RED + "Sorry Bro you can't get Drugged up";
  
  public Events(Drugs plugin)
  {
    setPlugin(plugin);
  }
  
  public Drugs getPlugin()
  {
    return this.plugin;
  }
  
  public void setPlugin(Drugs plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onRightClick(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if ((this.plugin.getConfig().getBoolean("Drugs.Enabled.wheat")) && 
      (p.hasPermission("drugs.wheat")) && 
      (e.getAction().equals(Action.RIGHT_CLICK_AIR)) && (p.getItemInHand().getType() == Material.WHEAT))
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
    if ((this.plugin.getConfig().getBoolean("Drugs.Enabled.sugar")) && 
      (p.hasPermission("drugs.sugar")) &&
  	(e.getAction().equals(Action.RIGHT_CLICK_AIR)) && (p.getItemInHand().equals(new ItemStack(Material.SUGAR))))
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
    if ((this.plugin.getConfig().getBoolean("Drugs.Enabled.paper")) && 
      (p.hasPermission("drugs.paper")) && 
      (e.getAction().equals(Action.RIGHT_CLICK_AIR)) && (p.getItemInHand().equals(new ItemStack(Material.PAPER))))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.AQUA + "You just hit Acid!");
      p.getItemInHand();
      p.setItemInHand(null);
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Acid");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Enabled.milk")) && 
      (p.hasPermission("drugs.milk")) && 
      (e.getAction().equals(Action.RIGHT_CLICK_AIR)) && (p.getItemInHand().equals(new ItemStack(Material.MILK_BUCKET))))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.AQUA + "Yummy Beer!!");
      p.getItemInHand();
      p.setItemInHand(null);
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Beer");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Enabled.melons")) && 
      (p.hasPermission("drugs.melons")) && 
      (e.getAction().equals(Action.RIGHT_CLICK_AIR))&& (p.getItemInHand().equals(new ItemStack(Material.MELON))))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.AQUA + "Ecstasy!!!");
      p.getItemInHand();
      p.setItemInHand(null);
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Excdodsy");
      }
    }
    if ((this.plugin.getConfig().getBoolean("Drugs.Enabled.gunpowder")) && 
      (p.hasPermission("drugs.gun")) && 
      (e.getAction().equals(Action.RIGHT_CLICK_AIR))&& (p.getItemInHand().equals(new ItemStack(Material.SULPHUR))))
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
    if ((this.plugin.getConfig().getBoolean("Drugs.Enabled.bone")) && 
      (p.hasPermission("drugs.bone")) &&      
      (e.getAction().equals(Action.RIGHT_CLICK_AIR)) && (p.getItemInHand().equals(new ItemStack(Material.BONE))))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.AQUA + "Angel Dust!");
      p.getItemInHand();
      p.setItemInHand(null);
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs"))
        this.logger.info(p.getName() + " Used Angel Dust");
      }
    if ((this.plugin.getConfig().getBoolean("Drugs.Enabled.smoke")) && 
      (p.hasPermission("drugs.smoke")) &&   
      (e.getAction().equals(Action.RIGHT_CLICK_AIR)) && (p.getItemInHand().equals(new ItemStack(Material.TORCH))))
    {
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.plugin.getConfig().getInt("Drugs.Effect.length"), 1), true);
      p.sendMessage(ChatColor.GREEN + "Smoking Yeah " + ChatColor.RED + " <3");
      p.getItemInHand();
      p.setItemInHand(null);
      if (this.plugin.getConfig().getBoolean("Drugs.Console.logs")) {
        this.logger.info(p.getName() + " Used Weed");
      }
    }
  }
}
