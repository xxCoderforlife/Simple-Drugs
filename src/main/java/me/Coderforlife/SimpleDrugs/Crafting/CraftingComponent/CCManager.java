package me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent;

import java.io.File;

import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Util.AbstractSDCraftableManager;
import me.Coderforlife.SimpleDrugs.Util.JsonFileInterpretter;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.CraftingComponentAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.ItemStackAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDFurnaceAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDShapedAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDShapelessAdapter;

public class CCManager extends AbstractSDCraftableManager<CraftingComponent> {

	public CCManager() {
		super(new File(Main.plugin.getDataFolder() + File.separator + "Recipes" + File.separator + "DrugComponents"));
	}

	@Override
	protected void registerTypeAdapters() {
		typeAdapters.put(SDShaped.class, new SDShapedAdapter());
		typeAdapters.put(SDShapeless.class, new SDShapelessAdapter());
		typeAdapters.put(SDFurnace.class, new SDFurnaceAdapter());
		typeAdapters.put(ItemStack.class, new ItemStackAdapter());
		typeAdapters.put(CraftingComponent.class, new CraftingComponentAdapter());
	}

	@Override
	protected void createFromJson(String fileName, JsonObject jo) {
		Gson gson = builder.create();
		CraftingComponent cc = gson.fromJson(jo, CraftingComponent.class);
		cc.setFile(fileName);
		addItem(cc.getName(), cc);
		cc.getRecipe().registerRecipe();
	}

	@Override
	protected DrugLoadError canMake(String fileName, JsonObject jo) {
		DrugLoadError dle = new DrugLoadError();
    	JsonFileInterpretter config = new JsonFileInterpretter(jo);
    	
    	if(!config.contains("name")) {
    		dle.addError("§c[ERROR] JSON File missing 'name'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("type")) {
    		dle.addError("§c[ERROR] JSON File missing 'type'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("item")) {
    		dle.addError("§c[ERROR] JSON File missing 'item'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("recipe")) {
    		dle.addError("§c[ERROR] JSON File missing 'recipe'");
    		dle.unLoad();
    	}
    	
    	return dle;
	}
	
}