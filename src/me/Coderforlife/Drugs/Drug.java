package me.Coderforlife.Drugs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A class to hold all information related to a drug.
 */
public class Drug {
	
	private final String name;
	private final String displayName;
	private final ItemStack drugItem;
	private final String usePermission;
	private final PotionEffectType[] effects;
	private Recipe itemRecipe;
	
	public Drug(String name,
	            String displayName,
	            Material drugMaterial,
	            PotionEffectType... effects) {
		this.name = name;
		this.displayName = displayName;
		this.drugItem = createDrugItem(drugMaterial);
		this.usePermission = "drugs.use." + name.toLowerCase();
		this.effects = effects;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUsePermission() {
		return usePermission;
	}
	
	public void setItemRecipe(Recipe itemRecipe) {
		this.itemRecipe = itemRecipe;
	}
	
	public Recipe getItemRecipe() {
		return itemRecipe;
	}
	
	/**
	 * Gives the player the effects of the drug.
	 */
	public void influencePlayer(Player p, FileConfiguration effectConfig) {
		for (PotionEffectType type : effects) {
			int duration = 20 * effectConfig.getInt("Drugs." + name + "." + type.getName() + ".Time");
			int lvl = -1 + effectConfig.getInt("Drugs." + name + "." + type.getName() + ".Level");
			p.addPotionEffect(type.createEffect(duration, lvl));
		}
	}
	
	public boolean isDrugItem(ItemStack item) {
		return item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(drugItem.getItemMeta().getDisplayName());
	}
	
	/**
	 * Creates the drug item automatically with information about the drug
	 */
	private ItemStack createDrugItem(Material drugMaterial) {
		ItemStack drugItem = new ItemStack(drugMaterial);
		ItemMeta meta = drugItem.getItemMeta();
		
		meta.setDisplayName(displayName);
		meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
		
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		
		for (PotionEffectType type : effects) {
			String effectName = type.getName();
			effectName = effectName.substring(0, 1).toLowerCase() + effectName.substring(1).toLowerCase();
			lore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + effectName);
		}
		lore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		meta.setLore(lore);
		drugItem.setItemMeta(meta);
		return drugItem;
	}
}
