package me.Coderforlife.SimpleDrugs.Util;

import java.nio.charset.StandardCharsets;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.CraftingComponent;

public class CCMaterialConverter {

	public static String createUpperCase(String input) {
		byte[] bytes = input.replaceAll(" ", "_").getBytes(StandardCharsets.UTF_8);
		String EncodedString = new String(bytes, StandardCharsets.UTF_8);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < EncodedString.length(); i++) {
			sb.append(Character.toUpperCase(EncodedString.charAt(i)));
		}
		return sb.toString();
	}
	
	public static ItemStack getCCOrMaterial(String fileName, String name) {
		Material m = Material.getMaterial(name.toUpperCase());
		if(m == null) {
			CraftingComponent cc = Main.plugin.getCraftingManager().getItem(name.toUpperCase());
			if(cc == null) {
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Material: §7" + name.toUpperCase() + " §cdoes not exist as a Minecraft Material or Custom Crafting Component");
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
				return null;
			}
			return cc.getItem();
		}
		return new ItemStack(m);
	}
	
	public static String getCCOrMaterial(ItemStack item) {
		if(item.hasItemMeta()) {
			ItemMeta im = item.getItemMeta();
			PersistentDataContainer pdc = im.getPersistentDataContainer();
			if(pdc.has(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE) && (pdc.get(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE) == (byte)1)) {
				return pdc.get(Main.plugin.getCraftingComponentName(), PersistentDataType.STRING).toUpperCase();
			}
		}
		return item.getType().toString().toUpperCase();
	}
	
}