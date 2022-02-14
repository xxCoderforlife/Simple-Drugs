package me.Coderforlife.SimpleDrugs.Util.GsonAdapaters;

import java.lang.reflect.Type;

import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.CraftingComponent;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Util.CCMaterialConverter;

public class CraftingComponentAdapter implements JsonSerializer<CraftingComponent>, JsonDeserializer<CraftingComponent> {

	@Override
	public CraftingComponent deserialize(JsonElement je, Type arg1, JsonDeserializationContext jdc)
			throws JsonParseException {
		
		JsonObject main = je.getAsJsonObject();
		
		String name = CCMaterialConverter.createUpperCase(main.get("name").getAsString());
		DrugCraftingType dct = DrugCraftingType.valueOf(main.get("type").getAsString());
		ItemStack item = jdc.deserialize(main.get("item"), ItemStack.class);
		SDRecipe rec = null;
		switch(dct) {
		case FURNACE:
			rec = jdc.deserialize(main.get("recipe"), SDFurnace.class);
			break;
		case SHAPED:
			rec = jdc.deserialize(main.get("recipe"), SDShaped.class);
			break;
		case SHAPELESS:
			rec = jdc.deserialize(main.get("recipe"), SDShapeless.class);
			break;
		}
		
		rec.setResult(item);
		CraftingComponent cc = new CraftingComponent(name, item.getItemMeta().getDisplayName(), item);
		cc.setRecipe(rec);
		return cc;
	}

	@Override
	public JsonElement serialize(CraftingComponent cc, Type arg1, JsonSerializationContext jsc) {
		JsonObject jo = new JsonObject();
		
		jo.addProperty("name", CCMaterialConverter.createUpperCase(cc.getName()));
		if(cc.getRecipe() instanceof SDShaped) {
			jo.addProperty("type", "SHAPED");
		} else if (cc.getRecipe() instanceof SDShapeless) {
			jo.addProperty("type", "SHAPELESS");
		} else if(cc.getRecipe() instanceof SDFurnace) {
			jo.addProperty("type", "FURNACE");
		}
		jo.add("item", jsc.serialize(cc.getRecipe().getResult(), ItemStack.class));
		jo.add("recipe", jsc.serialize(cc.getRecipe(), cc.getRecipe().getClass()));
		
		return jo;
	}
	
}