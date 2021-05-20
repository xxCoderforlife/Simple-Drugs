package me.Coderforlife.Drugs;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Items implements Listener {

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
	   if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() ==  Action.RIGHT_CLICK_BLOCK) {
			  ConfigurationSection drugs = plugin.drugsConfig.getConfigurationSection("Core.Drugs");
			  for(String drugsKeys : drugs.getKeys(false)) {
				  if(p.getInventory().getItemInMainHand().getType().equals(Material.valueOf("Core.Drugs."+drugsKeys+".item"))) {
					  if(p.hasPermission("drugs.use" + plugin.drugsConfig.getString("Core.Drugs."+drugsKeys+".perm"))) {
						  for(String enchant : plugin.drugsConfig.getStringList("Core.Drugs." +drugsKeys+ ".effects")) {
		                        String enchantname = enchant.split(":")[0];
		                        Integer enchantlvl = Integer.valueOf(enchant.split(":")[1]);
		                        drugMeta.addEnchant(EnchantmentWrapper.getByKey(NamespacedKey.minecraft(enchantname)), enchantlvl, true);
		                        
		                  }
					  }
				  }
			  }
	   }
   }
}
