package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.DrugAdapters;

import java.lang.reflect.Type;
import java.util.logging.Logger;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
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

	// PotionEffectType peType = Registry.EFFECT.match(type.toLowerCase());
	private Logger logger = Logger.getLogger("Minecraft");
	private Registry<PotionEffectType> potionReg = Registry.EFFECT;
	private NamespacedKey peKey;

	@Override
	public DrugEffect deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jo = json.getAsJsonObject();
		String type = jo.get("type").getAsString().toLowerCase();
		try {
			potionReg.forEach((k) -> {
				if (k.getKey().getKey().equalsIgnoreCase(type)) {
					peKey = k.getKey();
				}
			});
		} catch (Exception e) {
			logger.info("Error loading drug effect: " + type);
			peKey = Registry.EFFECT.iterator().next().getKey();
			return null;
		}
		PotionEffectType peType = potionReg.get(peKey);
		Integer time = jo.get("time").getAsInt();
		Integer intensity = jo.get("intensity").getAsInt();
		DrugEffect de = new DrugEffect(peType, time, intensity);
		return de;
	}

	@Override
	public JsonElement serialize(DrugEffect src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject main = new JsonObject();
		main.addProperty("type", src.getEffect().getKey().getKey());
		main.addProperty("time", src.getTime() / 20);
		main.addProperty("intensity", src.getIntensity());
		return main;
	}

}