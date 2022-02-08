package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.SubInventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Coderforlife.SimpleDrugs.GUI.Framework.ClickAction;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryButton;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;
import net.md_5.bungee.api.ChatColor;

public class PotionEffectSelectorInventory extends InventoryUI {

	public PotionEffectSelectorInventory(AbstractDrugCraftingInventory pesi) {
		super(36, ChatColor.translateAlternateColorCodes('&', "&7&lSelect Potion Type"));
		
		List<PotionEffectType> allPotionEffects = new ArrayList<>();
		
		for(PotionEffectType pe : PotionEffectType.values()) {
			allPotionEffects.add(pe);
		}
		
		for(int i = 0; i < 31; i++) {
			PotionEffectType pet = allPotionEffects.get(i);
			ItemStack item = new ItemStack(Material.POTION);
			PotionMeta pm = (PotionMeta)item.getItemMeta();
			pm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&l" + allPotionEffects.get(i).getName()));
			pm.addCustomEffect(new PotionEffect(allPotionEffects.get(i), 0, 1), true);
			item.setItemMeta(pm);
			addButton(new InventoryButton(item) {
				@Override
				public void onPlayerClick(Player p, ClickAction action) {
					close(p);
					PotionEffectStatSetterInventory pessi = new PotionEffectStatSetterInventory(pet, pesi);
					pessi.open(p);
				}
			}, i);
		}
		
		InventoryButton ib = new InventoryButton(Material.BLACK_STAINED_GLASS_PANE, " ", "") {
			public void onPlayerClick(Player p, ClickAction action) {}
		};
		
		for(int i = 31; i < 35; i++) {
			addButton(ib, i);
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