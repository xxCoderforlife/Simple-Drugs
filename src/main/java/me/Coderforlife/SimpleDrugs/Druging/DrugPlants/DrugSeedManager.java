package me.Coderforlife.SimpleDrugs.Druging.DrugPlants;

import java.io.File;
import java.util.StringJoiner;

import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Util.AbstractSDCraftableManager;
import me.Coderforlife.SimpleDrugs.Util.JsonFileInterpretter;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.DrugPlantItemAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.ItemStackAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDFurnaceAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDShapedAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDShapelessAdapter;

public class DrugSeedManager extends AbstractSDCraftableManager<DrugPlantItem> {

	public DrugSeedManager() {
		super(new File(Main.plugin.getDataFolder() + File.separator + "Recipes" + File.separator + "Seeds"));
		
		StringJoiner enabled = new StringJoiner(", ");
    	for(DrugPlantItem d : getItems().values()) {
    		enabled.add(d.getDrug().getName());
    	}
    	if(enabled.length() > 0) sendConsoleMessage("§6Enabled Seeds: §a" + enabled.toString().trim());
	}
	
	public ItemStack getItemStackFromDrug(Drug d) {
    	return getItems().get(d.getName().toUpperCase()).getItem();
    }
    
    public DrugPlantItem getDrugPlantItemFromDrug(Drug d) {
    	return getItems().get(d.getName().toUpperCase());
    }

	protected void registerTypeAdapters() {
		typeAdapters.put(SDShaped.class, new SDShapedAdapter());
		typeAdapters.put(SDShapeless.class, new SDShapelessAdapter());
		typeAdapters.put(SDFurnace.class, new SDFurnaceAdapter());
		typeAdapters.put(DrugPlantItem.class, new DrugPlantItemAdapter());
		typeAdapters.put(ItemStack.class, new ItemStackAdapter());
	}

	protected void createFromJson(String fileName, JsonObject jo) {
		Gson gson = builder.create();
		DrugPlantItem dpi = gson.fromJson(jo, DrugPlantItem.class);
		dpi.getRecipe().registerRecipe();
		dpi.setFile(fileName);
		addItem(dpi.getDrug().getName().toUpperCase(), dpi);
	}

	protected DrugLoadError canMake(String fileName, JsonObject jo) {
		DrugLoadError dle = new DrugLoadError();
    	JsonFileInterpretter config = new JsonFileInterpretter(jo);
    	
    	if(!config.contains("drug")) {
    		dle.addError("§c[ERROR] JSON File missing 'drug'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("type")) {
    		dle.addError("§c[ERROR] JSON File missing 'type'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("recipe")) {
    		dle.addError("§c[ERROR] JSON File missing 'recipe'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("seed-item")) {
    		dle.addError("§c[ERROR] JSON File missing 'seed-item'");
    		dle.unLoad();
    	}
    	
    	return dle;
	}
	
}