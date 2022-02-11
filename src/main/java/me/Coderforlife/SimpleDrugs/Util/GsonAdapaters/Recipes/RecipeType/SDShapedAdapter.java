package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;

public class SDShapedAdapter implements JsonSerializer<SDShaped>, JsonDeserializer<SDShaped> {

	@Override
	public SDShaped deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		SDShaped recipe = new SDShaped(null, null);
		
		JsonObject main = json.getAsJsonObject();
		
		for(int i = 1; i < 10; i++) {
			recipe.addItemStack(main.has(String.valueOf(i)) ? main.get(String.valueOf(i)).getAsString().toUpperCase() : "AIR");
		}
		
		return recipe;
	}

	@Override
	public JsonElement serialize(SDShaped src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jo = new JsonObject();
		
		for(int i = 1; i < 10; i++) {
			jo.addProperty(String.valueOf(i), src.getItems().get(i - 1));
		}
		
		return jo;
	}
	
}