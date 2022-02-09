package me.Coderforlife.SimpleDrugs.Druging;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Druging.Util.DrugRecipe;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.InventoryAddons;
import me.Coderforlife.SimpleDrugs.Util.AbstractSDCraftableManager;
import me.Coderforlife.SimpleDrugs.Util.JsonFileInterpretter;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.DrugRecipeAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDFurnaceAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDShapedAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDShapelessAdapter;

public class DrugRecipeManager extends AbstractSDCraftableManager<DrugRecipe> {

	public DrugRecipeManager() {
		super(new File(Main.plugin.getDataFolder() + File.separator + "Recipes" + File.separator + "Drugs"));
	}

	public void updateRecipeFile(DrugRecipe dr) {
		removeItem(dr.getDrug().getName().toUpperCase());
		saveFile(dr);
	}
	
	@Override
	protected void registerTypeAdapters() {
		typeAdapters.put(SDShaped.class, new SDShapedAdapter());
		typeAdapters.put(SDShapeless.class, new SDShapelessAdapter());
		typeAdapters.put(SDFurnace.class, new SDFurnaceAdapter());
		typeAdapters.put(DrugRecipe.class, new DrugRecipeAdapter());
	}

	@Override
	public void createFromJson(String fileName, JsonObject jo) {
		Gson gson = builder.create();
		DrugRecipe cc = gson.fromJson(jo, DrugRecipe.class);
		cc.setFile(fileName);
		addItem(cc.getDrug().getName().toUpperCase(), cc);
		
		Drug d = Main.plugin.getDrugManager().getItem(cc.getDrug().getName().toUpperCase());
		
		SDRecipe rec = null;
		
		if(cc.getRecipe() instanceof SDShaped) {
			rec = Main.plugin.getRecipeManager().loadRecipe(d, cc.getRecipe().getItems(), DrugCraftingType.SHAPED);
		} else if(cc.getRecipe() instanceof SDShapeless) {
			rec = Main.plugin.getRecipeManager().loadRecipe(d, cc.getRecipe().getItems(), DrugCraftingType.SHAPELESS);
		} else if(cc.getRecipe() instanceof SDFurnace) {
			rec = Main.plugin.getRecipeManager().loadRecipe(d, cc.getRecipe().getItems(), DrugCraftingType.FURNACE);
		}
		
		d.setRecipe(rec);
		rec.registerRecipe();
		saveFile(cc);
	}

	@Override
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
    	
    	return dle;
	}

	public void addOrUpdateItem(String name, InventoryAddons ad) {
		return;
	}
	
}