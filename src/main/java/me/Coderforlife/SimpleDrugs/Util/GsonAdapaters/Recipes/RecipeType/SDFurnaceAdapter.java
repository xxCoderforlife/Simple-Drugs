package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;

public class SDFurnaceAdapter implements JsonSerializer<SDFurnace>, JsonDeserializer<SDFurnace> {

	@Override
	public SDFurnace deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		SDFurnace furnace = new SDFurnace();
		
		JsonObject jo = json.getAsJsonObject();
		furnace.getItems().add(jo.get("item").getAsString().toUpperCase());
		
		return furnace;
	}

	@Override
	public JsonElement serialize(SDFurnace src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject main = new JsonObject();
		main.addProperty("item", src.getItems().get(0));
		return main;
	}

}