package me.Coderforlife.Drugs;

import java.util.ArrayList;
import java.util.Random;

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

	public Drug(String name, String displayName, Material drugMaterial, PotionEffectType... effects) {
		this.name = name;
		this.displayName = displayName;
		this.usePermission = "drugs.use." + name.toLowerCase();
		this.effects = effects;
		this.drugItem = createDrugItem(drugMaterial);
	}




	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public ItemStack getDrugItem() {
		return drugItem.clone();
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
		return item.hasItemMeta()
				&& item.getItemMeta().getDisplayName().equals(drugItem.getItemMeta().getDisplayName());
	}

	/**
	 * Creates the drug item automatically with information about the drug
	 */
	private ItemStack createDrugItem(Material drugMaterial) {
		Random r = new Random();
		ItemStack drugItem = new ItemStack(drugMaterial);
		ItemMeta meta = drugItem.getItemMeta();

		meta.setDisplayName(displayName);
		meta.addEnchant(Enchantment.DAMAGE_ALL, r.nextInt(45654), true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");

		for (PotionEffectType type : effects) {
			lore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + toTitleCase(type));
		}
		lore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		meta.setLore(lore);
		drugItem.setItemMeta(meta);
		return drugItem;
	}

	private String toTitleCase(PotionEffectType type) {
		String[] words = type.getName().split("_");
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < words.length; i++) {
			if (i > 0) {
				builder.append(" ");
			}
			builder.append(Character.toUpperCase(words[i].charAt(0)));
			builder.append(words[i].substring(1).toLowerCase());
		}
		return builder.toString().trim();
	}
}