package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.md_5.bungee.api.ChatColor;

public class ItemStackAdapter implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {

	@Override
	public ItemStack deserialize(JsonElement je, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		JsonObject jo = je.getAsJsonObject();
		
		String displayName = ChatColor.translateAlternateColorCodes('&', jo.get("displayname").getAsString());
		Material mat = Material.getMaterial(jo.get("material").getAsString().toUpperCase());
		
		ItemStack item = new ItemStack(mat);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(displayName);
		
		if(jo.has("lore") && jo.get("lore").isJsonArray()) {
			List<String> lore = new ArrayList<String>();
			for(JsonElement s : jo.get("lore").getAsJsonArray()) {
				lore.add(ChatColor.translateAlternateColorCodes('&', s.getAsString()));
			}
			im.setLore(lore);
		}
		
		item.setItemMeta(im);
		return item;
	}

	@Override
	public JsonElement serialize(ItemStack item, Type arg1, JsonSerializationContext arg2) {
		
		JsonObject main = new JsonObject();
		
		main.addProperty("displayname", item.getItemMeta().getDisplayName());
		main.addProperty("material", item.getType().toString());
		
		if(item.getItemMeta().hasLore()) {
			JsonArray ja = new JsonArray();
			for(String s : item.getItemMeta().getLore()) {
				ja.add(s);
			}
			main.add("lore", ja);
		}
		
		return main;
	}

	
	
}