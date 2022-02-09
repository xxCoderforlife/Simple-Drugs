package me.Coderforlife.SimpleDrugs.Druging.DrugPlants;

import java.io.File;
import java.util.List;
import java.util.StringJoiner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.InventoryAddons;
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

	public void createFromJson(String fileName, JsonObject jo) {
		Gson gson = builder.create();
		DrugPlantItem dpi = gson.fromJson(jo, DrugPlantItem.class);
		dpi.getRecipe().registerRecipe();
		dpi.setFile(fileName);
		addItem(dpi.getDrug().getName().toUpperCase(), dpi);
		saveFile(dpi);
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

	public void addOrUpdateItem(String n, InventoryAddons ad) {
		Drug oldDrug = (Drug)ad.getOptionValues().get("OldDrug");
		Drug newDrug = (Drug)ad.getOptionValues().get("Drug");
		
		if(oldDrug != null) {
			removeItem(oldDrug.getName().toUpperCase());
		}
		
		ItemStack item = (ItemStack)ad.getOptionValues().get("ResultMaterial");
		Integer i = (Integer)ad.getOptionValues().get("HarvestAmount");
		
		DrugPlantItem dpi = new DrugPlantItem(newDrug, item, Material.FARMLAND, i);
		dpi.setFile(new File(getMainFile(), newDrug.getName() + ".json").getAbsolutePath());
		
		if(getItems().containsKey(newDrug.getName().toUpperCase())) {
			removeItem(newDrug.getName().toUpperCase());
		}
		
		@SuppressWarnings("unchecked")
		SDRecipe sd = Main.plugin.getRecipeManager().loadRecipe(dpi, (List<ItemStack>)ad.getOptionValues().get("Recipe"), (DrugCraftingType)ad.getOptionValues().get("RecipeType"));
		dpi.setRecipe(sd);
		sd.registerRecipe();
		
		addItem(newDrug.getName().toUpperCase(), dpi);
		saveFile(dpi);
	}
	
}