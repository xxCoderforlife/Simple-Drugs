package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.bukkit.Material;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Druging.Util.DrugEffect;

public class DrugAdapter implements JsonSerializer<Drug>, JsonDeserializer<Drug> {

	@Override
	public Drug deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonObject jo = json.getAsJsonObject();
		
		String name = jo.get("name").getAsString();
		String displayname = jo.get("displayname").getAsString();
		Material item = Material.getMaterial(jo.get("item").getAsString().toUpperCase());
		
		ArrayList<DrugEffect> effects = new ArrayList<DrugEffect>();
		JsonArray ja = jo.get("effects").getAsJsonArray();
		for(JsonElement je : ja) {
			DrugEffect de = context.deserialize(je, DrugEffect.class);
			effects.add(de);
		}
		
		String permission = jo.get("permission").getAsString();
		Double addicLevel = jo.get("addictionLevel").getAsDouble();
		
		Drug d = new Drug(name, displayname, item, effects, permission, addicLevel);
		
		return d;
	}

	@Override
	public JsonElement serialize(Drug src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject main = new JsonObject();
		
		main.addProperty("name", src.getName());
		main.addProperty("displayname", src.getDisplayname());
		
		JsonArray effects = new JsonArray();
		for(DrugEffect de : src.getEffects()) {
			effects.add(context.serialize(de, DrugEffect.class));
		}
		
		main.add("effects", effects);
		
		main.addProperty("item", src.getItem().getType().toString());
		main.addProperty("permission", src.getPermission());
		main.addProperty("addictionLevel", src.getAddictionLevel());
		
		return main;
	}
	
}