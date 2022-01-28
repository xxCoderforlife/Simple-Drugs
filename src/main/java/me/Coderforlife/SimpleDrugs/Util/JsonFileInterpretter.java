package me.Coderforlife.SimpleDrugs.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import net.md_5.bungee.api.ChatColor;

public class JsonFileInterpretter {

	private JsonObject json;
	private List<String> errorMessages = new ArrayList<String>();
	
	public JsonFileInterpretter(JsonObject jo) {
		json = jo;
	}
	
	public Drug getDrug(String key) {
		Drug d = Main.plugin.getDrugManager().getDrug(getString(key).toUpperCase());
		if(d == null) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust have a valid Drug Name");
		}
		return d;
	}
	
	public JsonObject getJsonObject(String key) {
		if(!json.get(key).isJsonObject()) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be JsonObject {}");
		}
		return json.getAsJsonObject(key);
	}
	
	public boolean isJsonArray(String key) {
		return json.get(key).isJsonArray();
	}
	
	public Material getMaterial(String key) {
		Material m = Material.valueOf(getString(key).toUpperCase());
		if(m == null) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust have a valid Minecraft Material");
		}
		return m;
	}
	
	public ItemStack getItem(String key) {
		JsonObject obj = getJsonObject(key);
		JsonFileInterpretter config = new JsonFileInterpretter(obj);
		if(!config.contains("displayname")) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust have a Display Name");
		}
		
		if(!config.contains("material")) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust have a Material");
		}
		
		String name = config.getString("displayname");
		Material m = config.getMaterial("material");
		Integer amount = config.contains("amount") ? config.getInteger("amount") : 1;
		List<String> lore = config.contains("lore") ? config.getStringList("lore") : new ArrayList<>();
		
		for(int i = 0; i < lore.size(); i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		
		ItemStack is = new ItemStack(m);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		im.setLore(lore);
		is.setItemMeta(im);
		is.setAmount(amount);
		return is;
	}
	
	public DrugCraftingType getDrugCraftingType(String key) {
		DrugCraftingType dct = DrugCraftingType.valueOf(getString(key).toUpperCase());
		if(dct == null) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust have a valid Drug Crafting Type");
		}
		return dct;
	}
	
	public PotionEffectType getPotionEffect(String key) {
		PotionEffectType pet = PotionEffectType.getByName(getString(key).toUpperCase());
		if(pet == null) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust have a valid Minecraft Potion Effect");
		}
		return pet;
	}
	
	public JsonArray getJsonArray(String key) {
		if(!json.get(key).isJsonArray()) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be JsonArray []");
		}
		return json.get(key).getAsJsonArray();
	}
	
	public Boolean contains(String key) {
		return json.has(key);
	}
	
	public String getString(String key) {
		try {
			String s = json.get(key).getAsString();
			return s;
		} catch (Exception e) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be a String Value");
		}
		return null;
	}
	
	public boolean getBoolean(String key) {
		try {
			boolean s = json.get(key).getAsBoolean();
			return s;
		} catch (Exception e) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be a Boolean Value");
		}
		return false;
	}
	
	public double getDouble(String key) {
		try {
			double s = json.get(key).getAsDouble();
			return s;
		} catch (Exception e) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be a Double Value");
		}
		return -1d;
	}
	
	public float getFloat(String key) {
		try {
			float s = json.get(key).getAsFloat();
			return s;
		} catch (Exception e) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be a Float Value");
		}
		return -1f;
	}
	
	public int getInteger(String key) {
		try {
			int s = json.get(key).getAsInt();
			return s;
		} catch (Exception e) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be an Integer Value");
		}
		return -1;
	}
	
	public List<String> getStringList(String key) {
		List<String> sList = new ArrayList<>();
		
		JsonArray ja = getJsonArray(key);
		ja.forEach(e -> {
			sList.add(e.getAsString());
		});
		
		return sList;
	}
	
	public List<String> getAllError() {
		return errorMessages;
	}
	
}