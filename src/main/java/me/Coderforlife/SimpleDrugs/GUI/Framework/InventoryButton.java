package me.Coderforlife.SimpleDrugs.GUI.Framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class InventoryButton {

	private static final Integer LORE_LINE_LENGTH = Integer.valueOf(40);
	private ItemStack item;

	/**
	 * Creates an InventoryButton using the specified ItemStack
	 * 
	 * @param stack ItemStack to represent button
	 */
	public InventoryButton(ItemStack stack) {
		item = stack;
	}

	/**
	 * Creates an InventoryButton using the specified variables to create a new
	 * itemStack
	 * 
	 * @param mat   Material of item
	 * @param title Title of the item
	 * @param lore  Lore of the item
	 */
	public InventoryButton(Material mat, String title, String lore) {
		setItemStackUsing(mat, Integer.valueOf(1), title, lore);
	}

	protected final void setItemStackUsing(Material mat, Integer i, String title, String lore) {
		item = new ItemStack(mat, i.intValue());
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
		meta.setLore(wrapLoreText(lore));
		item.setItemMeta(meta);
	}

	private List<String> wrapLoreText(String s) {
		String workS = ChatColor.translateAlternateColorCodes('&', s);
		if (workS.length() <= LORE_LINE_LENGTH.intValue())
			return Arrays.asList(new String[] { workS });
		double numberOfLines = Math.ceil((workS.length() / LORE_LINE_LENGTH.intValue()));
		List<String> lines = new ArrayList<String>();
		String lastColor = null;
		for (int lineIndex = 0; lineIndex < numberOfLines; lineIndex++) {
			String line = workS.substring(lineIndex * LORE_LINE_LENGTH.intValue(),
					Math.min((lineIndex + 1) * LORE_LINE_LENGTH.intValue(), workS.length()));
			if (lastColor != null)
				line = lastColor + line;
			lastColor = ChatColor.getLastColors(line);
			lines.add(line);
		}
		return lines;
	}

	/**
	 * Set the ItemStack of the item for representing the InventoryButton
	 * 
	 * @param stack ItemStack to set to
	 */
	public void setStack(ItemStack stack) {
		item = stack;
	}

	/**
	 * Get the ItemStack of the InventoryButton
	 * 
	 * @return ItemStack
	 */
	public ItemStack getItem() {
		return item;
	}

	/**
	 * Handles what will happen if the player interacts with the InventoryButton
	 * 
	 * @param p      Player that clicked the InventoryButton
	 * @param action The type of Click the player performed
	 */
	public abstract void onPlayerClick(Player p, ClickAction action);
	
}