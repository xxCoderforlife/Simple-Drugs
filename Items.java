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

public class Items implements Listener {
   public final String prefix2 = ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Drugs" + ChatColor.WHITE + "] " + ChatColor.RESET;

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
   public void onRightClick(PlayerInteractEvent e) {
      Player p = e.getPlayer();
      ItemStack itemStack = p.getInventory().getItemInHand();
      int amount = itemStack.getAmount();
      
      if (this.plugin.getConfig().getBoolean("Drugs.Coke.Enable") 
    		  && p.hasPermission("drugs.sugar") 
    		  && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) 
    		  && p.getInventory().getItemInHand().equals(Main.suagrd)) {
    	  p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Coke.EffectLength"), 1), true);
         p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Coke.EffectLength"), 1), true);
         p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, this.plugin.getConfig().getInt("Drugs.Coke.EffectLength"), 1), true);
         p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, this.plugin.getConfig().getInt("Drugs.Coke.EffectLength"), 1), true);
         p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, this.plugin.getConfig().getInt("Drugs.Coke.EffectLength"), 1), true);
         p.sendMessage(this.prefix2 + ChatColor.DARK_RED + "You used Coke.");
         if (amount > 1) {
             itemStack.setAmount(amount - 1);
             p.getInventory().setItemInHand(itemStack);
          }

          if (amount == 1) {
             p.getInventory().getItemInHand().setAmount(0);
          }
      }

      if (this.plugin.getConfig().getBoolean("Drugs.Weed.Enable") 
    		  && p.hasPermission("drugs.wheat") 
    		  && (e.getAction() 
    				  == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getInventory().getItemInHand().equals(Main.wheatd)) {
         p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, this.plugin.getConfig().getInt("Drugs.Weed.EffectLength"), 1), true);
         p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, this.plugin.getConfig().getInt("Drugs.Weed.EffectLength"), 1), true);
         p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, this.plugin.getConfig().getInt("Drugs.Weed.EffectLength"), 1), true);
         p.sendMessage(this.prefix2 + ChatColor.DARK_RED + "You used Marijuana.");
         if (amount > 1) {
            itemStack.setAmount(amount - 1);
            p.getInventory().setItemInHand(itemStack);
         }

         if (amount == 1) {
            p.getInventory().getItemInHand().setAmount(0);
         }
      }

      if (this.plugin.getConfig().getBoolean("Drugs.Acid.Enable") && 
    		  p.hasPermission("drugs.paper") 
    		  && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getInventory().getItemInHand().equals(Main.paperd)) {
         p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, this.plugin.getConfig().getInt("Drugs.Acid.EffectLength"), 1), true);
         p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, this.plugin.getConfig().getInt("Drugs.Acid.EffectLength"), 1), true);
         p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, this.plugin.getConfig().getInt("Drugs.Acid.EffectLength"), 1), true);
         p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, this.plugin.getConfig().getInt("Drugs.Acid.EffectLength"), 1), true);
         p.sendMessage(this.prefix2 + ChatColor.DARK_RED + "You used Acid.");
         if (amount > 1) {
            itemStack.setAmount(amount - 1);
            p.getInventory().setItemInHand(itemStack);
         }

         if (amount == 1) {
            p.getInventory().getItemInHand().setAmount(0);
         }
      }

   }
}
