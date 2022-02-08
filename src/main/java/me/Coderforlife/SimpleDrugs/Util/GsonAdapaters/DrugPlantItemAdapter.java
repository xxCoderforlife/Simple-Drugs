package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters;

import java.lang.reflect.Type;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
import me.Coderforlife.SimpleDrugs.Druging.DrugPlants.DrugPlantItem;

public class DrugPlantItemAdapter implements JsonSerializer<DrugPlantItem>, JsonDeserializer<DrugPlantItem> {

	@Override
	public DrugPlantItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jo = json.getAsJsonObject();
		Drug d = Main.plugin.getDrugManager().getItem(jo.get("drug").getAsString().toUpperCase());
		DrugCraftingType dct = DrugCraftingType.valueOf(jo.get("type").getAsString().toUpperCase());
		ItemStack item = context.deserialize(jo.get("seed-item"), ItemStack.class);
		SDRecipe rec = null;
		switch(dct) {
		case FURNACE:
			rec = context.deserialize(jo.get("recipe"), SDFurnace.class);
			break;
		case SHAPED:
			rec = context.deserialize(jo.get("recipe"), SDShaped.class);
			break;
		case SHAPELESS:
			rec = context.deserialize(jo.get("recipe"), SDShapeless.class);
			break;
		}
		
		Integer amount = jo.get("harvest-amount").getAsInt();
		
		DrugPlantItem dpi = new DrugPlantItem(d, item, Material.FARMLAND, amount);
		
		rec.setName(dpi.getNamespaceName());
		rec.setResult(item);
		
		dpi.setRecipe(rec);
		return dpi;
	}

	@Override
	public JsonElement serialize(DrugPlantItem src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jo = new JsonObject();
		
		jo.addProperty("drug", src.getDrug().getName().toUpperCase());
		if(src.getRecipe() instanceof SDShaped) {
			jo.addProperty("type", "SHAPED");
		} else if (src.getRecipe() instanceof SDShapeless) {
			jo.addProperty("type", "SHAPELESS");
		} else if(src.getRecipe() instanceof SDFurnace) {
			jo.addProperty("type", "FURNACE");
		}
		jo.add("recipe", context.serialize(src.getRecipe(), src.getRecipe().getClass()));
		jo.add("seed-item", context.serialize(src.getItem(), ItemStack.class));
		jo.addProperty("harvest-amount", src.getAmount());
		
		return jo;
	}

}