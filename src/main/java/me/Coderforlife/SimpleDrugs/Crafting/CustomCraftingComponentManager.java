package me.Coderforlife.SimpleDrugs.Crafting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Util.JsonFileInterpretter;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;
import net.md_5.bungee.api.ChatColor;

public class CustomCraftingComponentManager {

	private Map<String, CraftingComponent> craftingComponents;
	private final File recipeFolder = new File(Main.plugin.getDataFolder(), "Recipes");
	private final File drugCFolder = new File(recipeFolder, "DrugComponents");
	
	public CustomCraftingComponentManager() {
		craftingComponents = new HashMap<>();
    	if(!drugCFolder.exists()) drugCFolder.mkdirs();
		
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
	
	public void registerAllRecipe() {
		for(CraftingComponent cc : craftingComponents.values()) {
			cc.getRecipe().registerRecipe();
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
	
	private void createCraftingComponentFile(CraftingComponent cc) {
		File f = new File(drugCFolder, cc.getFileName());
		if(f.exists()) f.delete();
		SDRecipe rec = cc.getRecipe();
		
		JsonObject main = new JsonObject();
		main.addProperty("name", cc.getName());
		main.addProperty("type", cc.getType().toString().toUpperCase());
		
		JsonObject item = new JsonObject();
		item.addProperty("displayname", cc.getItem().getItemMeta().getDisplayName());
		item.addProperty("material", cc.getItem().getType().toString().toUpperCase());
		item.addProperty("amount", 1);
		
		main.add("item", item);
		
		JsonObject recipe = new JsonObject();
    	if(rec instanceof SDShaped) {
    		for(int i = 0; i < rec.getItems().size(); i++) {
    			recipe.addProperty(String.valueOf(i + 1), rec.getItems().get(i).getType().toString());
    		}
    	} else if(rec instanceof SDShapeless) {
    		JsonArray ja = new JsonArray();
    		for(ItemStack i : rec.getItems()) {
    			ja.add(i.getType().toString());
    		}
    		recipe.add("items", ja);
    	} else if(rec instanceof SDFurnace) {
    		recipe.addProperty("item", rec.getItems().get(0).getType().toString());
    	}
    	
    	main.add("recipe", recipe);
    	
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
        	FileWriter writer = new FileWriter(f);
			gson.toJson(main, writer);
			writer.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createCraftingComponent(String n, ItemStack i, DrugCraftingType dct, List<ItemStack> items) {
		String name = ChatColor.stripColor(n).replaceAll(" ", "_");
		String displayName = n;
		ItemStack item = new ItemStack(i.getType());
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(displayName);
		im.getPersistentDataContainer().set(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE, (byte)1);
		im.getPersistentDataContainer().set(Main.plugin.getCraftingComponentName(), PersistentDataType.STRING, name.toUpperCase());
		item.setItemMeta(im);
		
		if(craftingComponents.containsKey(name.toUpperCase())) {
			craftingComponents.remove(name.toUpperCase());
		}
		
		CraftingComponent cc = new CraftingComponent(name, name + ".json", item, dct, items);
		craftingComponents.put(name.toUpperCase(), cc);
		createCraftingComponentFile(cc);
	}
	
	private void createCraftingComponentFromJSON(String fileName, JsonObject jo) {
		JsonFileInterpretter config = new JsonFileInterpretter(jo);
		
		String name = config.getString("name");
		ItemStack result = config.getItem("item");
		ItemMeta im = result.getItemMeta();
		im.getPersistentDataContainer().set(Main.plugin.isCraftingComponent(), PersistentDataType.BYTE, (byte)1);
		im.getPersistentDataContainer().set(Main.plugin.getCraftingComponentName(), PersistentDataType.STRING, name.toUpperCase());
		result.setItemMeta(im);
		
		DrugCraftingType dct = config.getDrugCraftingType("type");
		JsonObject ja = config.getJsonObject("recipe");
		
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