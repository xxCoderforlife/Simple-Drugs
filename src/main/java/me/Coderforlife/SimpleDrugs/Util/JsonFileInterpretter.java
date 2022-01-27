package me.Coderforlife.SimpleDrugs.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonFileInterpretter {

	private JsonObject json;
	private List<String> errorMessages = new ArrayList<String>();
	
	public JsonFileInterpretter(JsonObject jo) {
		json = jo;
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
	
	public Boolean getBoolean(String key) {
		try {
			Boolean s = json.get(key).getAsBoolean();
			return s;
		} catch (Exception e) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be a Boolean Value");
		}
		return null;
	}
	
	public Double getDouble(String key) {
		try {
			Double s = json.get(key).getAsDouble();
			return s;
		} catch (Exception e) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be a Double Value");
		}
		return null;
	}
	
	public Float getFloat(String key) {
		try {
			Float s = json.get(key).getAsFloat();
			return s;
		} catch (Exception e) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be a Float Value");
		}
		return null;
	}
	
	public Integer getInteger(String key) {
		try {
			Integer s = json.get(key).getAsInt();
			return s;
		} catch (Exception e) {
			errorMessages.add("§c[ERROR] JSON Key: §7" + key + " §cMust be an Integer Value");
		}
		return null;
	}
	
	public List<String> getAllError() {
		return errorMessages;
	}
	
}