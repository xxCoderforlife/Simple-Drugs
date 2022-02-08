package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.DrugAdapters;

import java.lang.reflect.Type;

import org.bukkit.potion.PotionEffectType;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.Coderforlife.SimpleDrugs.Druging.Util.DrugEffect;

public class DrugEffectAdapter implements JsonSerializer<DrugEffect>, JsonDeserializer<DrugEffect> {

	@Override
	public DrugEffect deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jo = json.getAsJsonObject();
		String type = jo.get("type").getAsString();
		PotionEffectType peType = PotionEffectType.getByName(type.toUpperCase());
		Integer time = jo.get("time").getAsInt();
		Integer intensity = jo.get("intensity").getAsInt();
		DrugEffect de = new DrugEffect(peType, time, intensity);
		return de;
	}

	@Override
	public JsonElement serialize(DrugEffect src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject main = new JsonObject();
		main.addProperty("type", src.getEffect().getName().toUpperCase());
		main.addProperty("time", src.getTime() / 20);
		main.addProperty("intensity", src.getIntensity());
		return main;
	}
	
}