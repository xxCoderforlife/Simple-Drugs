package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.SubInventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.PotionUtil.InventoryPotionEffect;
import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class PotionEffectRemoveInventory extends InventoryUI {

	public PotionEffectRemoveInventory(AbstractDrugCraftingInventory pesi) {
		super(36, ChatColor.translateAlternateColorCodes('&', "&4&lRemove Potion Effect"));
		
		for(InventoryPotionEffect ipe : pesi.getPotionEffects().getPotionEffects()) {
			ItemStack item = new ItemStack(Material.POTION);
			PotionMeta pm = (PotionMeta)item.getItemMeta();
			pm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&l" + ipe.getType().getName()));
			pm.addCustomEffect(new PotionEffect(ipe.getType(), 0, 1), true);
			item.setItemMeta(pm);
			
			addButton(new InventoryButton(item) {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					pesi.getPotionEffects().getPotionEffects().remove(ipe);
					close(p);
					pesi.updateEffectsButton();
					pesi.open(p);
				}
			});
		}
		
		addButton(new InventoryButton(Material.RED_WOOL, "&4&lCancel", "") {
			@Override
			public void onPlayerClick(Player p, ClickAction action) {
				close(p);
				pesi.open(p);
			}
		}, 35);
		
		updateInventory();
	}
	
}