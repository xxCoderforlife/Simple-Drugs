package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;

public class SDShapelessAdapter implements JsonSerializer<SDShapeless>, JsonDeserializer<SDShapeless> {

	@Override
	public SDShapeless deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		SDShapeless shapeless = new SDShapeless();
		
		JsonObject jo = json.getAsJsonObject();
		JsonArray ja = jo.get("items").getAsJsonArray();
		
		for(JsonElement je : ja) {
			shapeless.addItemStack(je.getAsString().toUpperCase());
		}
		
		return shapeless;
	}

	@Override
	public JsonElement serialize(SDShapeless src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject items = new JsonObject();
		JsonArray ja = new JsonArray();
		for(String i : src.getItems()) {
			ja.add(i);
		}
		items.add("items", ja);
		return items;
	}

}