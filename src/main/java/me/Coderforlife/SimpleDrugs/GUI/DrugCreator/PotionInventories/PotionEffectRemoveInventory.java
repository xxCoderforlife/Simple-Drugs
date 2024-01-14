package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.PotionInventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingInventories.AbstractSDCInventory;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.InventoryPotionEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.PotionEffectInventoryUtil;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class PotionEffectRemoveInventory extends InventoryUI {

	public PotionEffectRemoveInventory(AbstractSDCInventory asdci) {
		super(36, ChatColor.translateAlternateColorCodes('&', "&4&lRemove Potion Effect"));
		
		for(InventoryPotionEffect ipe : ((PotionEffectInventoryUtil)asdci.getAddons().getOptionValues().get("DrugEffects")).getPotionEffects()) {
			ItemStack item = new ItemStack(Material.POTION);
			PotionMeta pm = (PotionMeta)item.getItemMeta();
			pm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&l" + ipe.getType().getKey().getKey()));
			pm.addCustomEffect(new PotionEffect(ipe.getType(), 0, 1), true);
			item.setItemMeta(pm);
			
			addButton(new InventoryButton(item) {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					((PotionEffectInventoryUtil)asdci.getAddons().getOptionValues().get("DrugEffects")).getPotionEffects().remove(ipe);
					close(p);
					asdci.getAddons().updateDrugButtons();
					asdci.updateAddons();
					asdci.open(p);
				}
			});
		}
		
		addButton(new InventoryButton(Material.RED_WOOL, "&4&lCancel", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				asdci.open(p);
			}
		}, 35);
		
		updateInventory();
	}
	
}