package me.Coderforlife.SimpleDrugs.Crafting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Util.JsonFileInterpretter;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;

public class CustomCraftingComponentManager {

	private Map<String, CraftingComponent> craftingComponents;
	private final File recipeFolder = new File(Main.plugin.getDataFolder(), "Recipes");
	private final File drugCFolder = new File(recipeFolder, "DrugComponents");
	
	public CustomCraftingComponentManager() {
		craftingComponents = new HashMap<>();
    	if(!drugCFolder.exists()) drugCFolder.mkdir();
		
		for(File f : drugCFolder.listFiles()) {
			if(f.getName().endsWith(".json")) {
				try {
					JsonObject obj = new Gson().fromJson(new FileReader(f), JsonObject.class);
					DrugLoadError dle = canMakeCraftingComponents(f.getName(), obj);
					
					if(!dle.canLoad()) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						dle.printAllErrors();
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Crafting Component");
						continue;
					}
					
					createCraftingComponentFromJSON(f.getName(), obj);
				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void loadAllRecipes() {
		for(CraftingComponent cc : craftingComponents.values()) {
			cc.registerRecipe();
		}
	}
	
	private DrugLoadError canMakeCraftingComponents(String fileName, JsonObject jo) {
		DrugLoadError dle = new DrugLoadError();
		
		JsonFileInterpretter config = new JsonFileInterpretter(jo);
		
		if(!config.contains("name")) {
			dle.addError("§c[ERROR] JSON Key: §7'name' §cis missing");
			dle.unLoad();
		}
		
		if(!config.contains("item")) {
			dle.addError("§c[ERROR] JSON Key: §7'item' §cis missing");
			dle.unLoad();
		}
		
		if(!config.contains("type")) {
			dle.addError("§c[ERROR] JSON Key: §7'type' §cis missing");
			dle.unLoad();
		}
		
		if(!config.contains("recipe")) {
			dle.addError("§c[ERROR] JSON Key: §7'recipe' §cis missing");
			dle.unLoad();
		}
		
		return dle;
	}
	
	private void createCraftingComponentFromJSON(String fileName, JsonObject jo) {
		JsonFileInterpretter config = new JsonFileInterpretter(jo);
		
		String name = config.getString("name");
		ItemStack result = config.getItem("item");
		DrugCraftingType dct = config.getDrugCraftingType("type");
		JsonArray ja = config.getJsonArray("recipe");
		
		if(config.getAllError().size() > 0) {
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
    		config.getAllError().forEach(e -> {
    			Bukkit.getConsoleSender().sendMessage(e);
    		});
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Drug");
    		return;
		}
		
		if(craftingComponents.containsKey(name.toUpperCase())) {
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Name: §7" + name + " §cexists");
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Crafting Component");
			return;
		}
		
		CraftingComponent cc = new CraftingComponent(name, fileName, result, dct, ja);
		craftingComponents.put(name.toUpperCase(), cc);
	}
	
	public CraftingComponent getByName(String s) {
		if(craftingComponents.containsKey(s)) return craftingComponents.get(s);
		return null;
	}
	
	public Map<String, CraftingComponent> getCraftingComponents() {
		return craftingComponents;
	}
	
}