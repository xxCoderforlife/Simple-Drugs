package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Druging.Util.DrugRecipe;

public class DrugRecipeAdapter implements JsonSerializer<DrugRecipe>, JsonDeserializer<DrugRecipe> {

	@Override
	public DrugRecipe deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonObject main = json.getAsJsonObject();
		Drug d = Main.plugin.getDrugManager().getItem(main.get("drug").getAsString().toUpperCase());
		DrugCraftingType dct = DrugCraftingType.valueOf(main.get("type").getAsString().toUpperCase());
		SDRecipe rec = null;
		switch(dct) {
		case FURNACE:
			rec = context.deserialize(main.get("recipe"), SDFurnace.class);
			break;
		case SHAPED:
			rec = context.deserialize(main.get("recipe"), SDShaped.class);
			break;
		case SHAPELESS:
			rec = context.deserialize(main.get("recipe"), SDShapeless.class);
			break;
		}
		
		rec.setName(d.getNamespaceName());
		rec.setResult(d.getItem());
		
		DrugRecipe dr = new DrugRecipe(d, rec);
		return dr;
	}

	@Override
	public JsonElement serialize(DrugRecipe src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject main = new JsonObject();
		main.addProperty("drug", src.getDrug().getName().toUpperCase());
		if(src.getRecipe() instanceof SDShaped) {
			main.addProperty("type", "SHAPED");
		} else if (src.getRecipe() instanceof SDShapeless) {
			main.addProperty("type", "SHAPELESS");
		} else if(src.getRecipe() instanceof SDFurnace) {
			main.addProperty("type", "FURNACE");
		}
		main.add("recipe", context.serialize(src.getRecipe(), src.getRecipe().getClass()));
		return main;
	}
	
}