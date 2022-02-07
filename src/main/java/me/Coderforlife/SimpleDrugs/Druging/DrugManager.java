package me.Coderforlife.SimpleDrugs.Druging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.DrugPlants.DrugPlantItem;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.PotionUtil.InventoryPotionEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.DrugCraftingInventories.SubInventories.AbstractDrugCraftingInventory;
import me.Coderforlife.SimpleDrugs.Util.JsonFileInterpretter;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;
import net.md_5.bungee.api.ChatColor;

public class DrugManager {
    Main plugin = Main.plugin;
    
    private final File folder = new File(plugin.getDataFolder(), "Drugs");
    private final File recipeFolder = new File(plugin.getDataFolder(), "Recipes");
    private final File drugRFolder = new File(recipeFolder, "Drugs");
    private final File drugSFolder = new File(recipeFolder, "Seeds");
    
    private Map<String, Drug> drugs = new HashMap<>();
    private Map<Drug, DrugPlantItem> drugSeeds = new HashMap<>();

    public void loadFiles() {
    	if(!folder.exists()) folder.mkdir();
    	if(!recipeFolder.exists()) recipeFolder.mkdir();
    	if(!drugRFolder.exists()) drugRFolder.mkdir();
    	if(!drugSFolder.exists()) drugSFolder.mkdir();
    	
    	loadDrugs();
    	loadRecipes();
    	loadSeedRecipes();
    	
    	if(drugs.size() == 0) {
    		createDrugs();
    	}
    	
    	StringJoiner enabled = new StringJoiner(", ");
    	for(Drug d : getallDrugs()) {
    		enabled.add(d.getName());
    	}
    	if(enabled.length() > 0) sendConsoleMessage("§6Enabled Drugs: §a" + enabled.toString().trim());
    }

    private void loadDrugs() {
    	for(File f : folder.listFiles()) {
    		if(f.getName().endsWith(".json")) {
    			try {
					JsonObject obj = new Gson().fromJson(new FileReader(f), JsonObject.class);
					DrugLoadError dle = canDrugLoad(obj);
					if(!dle.canLoad()) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						dle.printAllErrors();
						sendConsoleMessage("§c[ERROR] Skipping Drug");
						continue;
					}
					drugFromJson(obj, f.getName());
				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
    
    private void loadSeedRecipes() {
    	for(File f : drugSFolder.listFiles()) {
    		if(f.getName().endsWith(".json")) {
    			JsonObject obj;
				try {
					obj = new Gson().fromJson(new FileReader(f), JsonObject.class);
					DrugLoadError dle = canRecipeLoad(obj, true);
					if(!dle.canLoad()) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						dle.printAllErrors();
						sendConsoleMessage("§c[ERROR] Skipping Seed Recipe");
						continue;
					}
					
					JsonFileInterpretter config = new JsonFileInterpretter(obj);
					
					Drug d = config.getDrug("drug");
					
					if(d == null) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						sendConsoleMessage("§c[ERROR] Drug not found: §7" + config.getString("drug"));
						sendConsoleMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					DrugCraftingType dct = config.contains("type") ? config.getDrugCraftingType("type") : DrugCraftingType.SHAPED;
					JsonObject ja = config.getJsonObject("recipe");
					
					Integer harvestAmount = config.getInteger("harvest-amount");
					ItemStack seedItem = config.getItem("seed-item");
					
					if(config.getAllError().size() > 0) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
			    		config.getAllError().forEach(e -> {
			    			Bukkit.getConsoleSender().sendMessage(e);
			    		});
			    		sendConsoleMessage("§c[ERROR] Skipping Drug");
						continue;
					}
					
					DrugPlantItem dpi = new DrugPlantItem(d, f.getName(), seedItem, Material.FARMLAND, harvestAmount);
					
					SDRecipe recipe = Main.plugin.getRecipeManager().loadRecipe(f.getName(), dpi, ja, dct);
					
					if(recipe == null) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						sendConsoleMessage("§c[ERROR] Error in Recipe");
						sendConsoleMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					drugSeeds.put(d, dpi);
					
				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
    
    private void loadRecipes() {
    	for(File f : drugRFolder.listFiles()) {
    		if(f.getName().endsWith(".json")) {
    			try {
					JsonObject obj = new Gson().fromJson(new FileReader(f), JsonObject.class);
					DrugLoadError dle = canRecipeLoad(obj, false);
					if(!dle.canLoad()) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						dle.printAllErrors();
						sendConsoleMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					JsonFileInterpretter config = new JsonFileInterpretter(obj);
					
					Drug d = config.getDrug("drug");
					
					if(d == null) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						sendConsoleMessage("§c[ERROR] Drug not found: §7" + config.getString("drug"));
						sendConsoleMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					DrugCraftingType dct = config.contains("type") ? config.getDrugCraftingType("type") : DrugCraftingType.SHAPED;
					JsonObject ja = config.getJsonObject("recipe");
					
					if(config.getAllError().size() > 0) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
			    		config.getAllError().forEach(e -> {
			    			sendConsoleMessage(e);
			    		});
			    		sendConsoleMessage("§c[ERROR] Skipping Drug");
						continue;
					}
					
					SDRecipe recipe = Main.plugin.getRecipeManager().loadRecipe(f.getName(), d, ja, dct);
					
					if(recipe == null) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						sendConsoleMessage("§c[ERROR] Error in Recipe");
						sendConsoleMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					d.setRecipe(recipe);
					d.setCraftable(true);
					
				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					e.printStackTrace();
				}
    		}
    	}
    }

    private DrugLoadError canRecipeLoad(JsonObject jo, Boolean seed) {
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
    	
    	if(seed == true) {
    		if(!config.contains("harvest-amount")) {
        		dle.addError("§c[ERROR] JSON File missing 'harvest-amount'");
        		dle.unLoad();
        	}
    		if(!config.contains("seed-item")) {
    			dle.addError("§c[ERROR] JSON File missing 'seed-item'");
        		dle.unLoad();
    		}
    	}
    	
    	return dle;
    }
    
    private DrugLoadError canDrugLoad(JsonObject jo) {
    	DrugLoadError dle = new DrugLoadError();
    	JsonFileInterpretter config = new JsonFileInterpretter(jo);
    	
    	if(!config.contains("name")) {
    		dle.addError("§c[ERROR] JSON File missing 'name'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("item")) {
    		dle.addError("§c[ERROR] JSON File missing 'name'");
    		dle.unLoad();
    	}
    	
    	return dle;
    }
    
    private void drugFromJson(JsonObject jo, String fileName) {
    	JsonFileInterpretter config = new JsonFileInterpretter(jo);
    	
    	String name = config.getString("name");
    	String displayName = config.contains("displayname") ? config.getString("displayname").replace("&", "§") : name.replaceAll("_", " ");
    	Material mat = config.getMaterial("item");
		Double addLvl = config.getDouble("addictionLevel");
    	String permission = config.contains("permission") ? config.getString("permission") : "drugs.use." + name.toLowerCase();
    	ArrayList<DrugEffect> effects = (config.contains("effects") && config.isJsonArray("effects")) ? loadEffectsFromJson(config.getJsonArray("effects"), fileName) : new ArrayList<DrugEffect>();
    	
    	if(config.getAllError().size() > 0) {
    		sendConsoleMessage("§c[ERROR] Error in: §7" + fileName);
    		config.getAllError().forEach(e -> {
    			Bukkit.getConsoleSender().sendMessage(e);
    		});
    		sendConsoleMessage("§c[ERROR] Skipping Drug");
    		return;
    	}
    	
    	ItemStack is = createItem(displayName, mat, effects);
    	
    	addDrug(new Drug(name, displayName, fileName, is, effects, permission,addLvl), name.toUpperCase());
    }
    
    private ItemStack createItem(String s, Material item, ArrayList<DrugEffect> effects) {
    	ItemStack is = new ItemStack(item);
    	ItemMeta im = is.getItemMeta();
    	im.getPersistentDataContainer().set(Main.plugin.isDrugItem(), PersistentDataType.BYTE, (byte)1);
    	im.setDisplayName(s);
    	im.addItemFlags(ItemFlag.HIDE_ENCHANTS , ItemFlag.HIDE_ATTRIBUTES);
    	List<String> lore = new ArrayList<String>();
    	if(effects.size() > 0) {
    		lore.add("§a§lEffects:");
    		for(DrugEffect de : effects) {
    			lore.add("§7- §6" + de.getEffect().getName().toUpperCase(Locale.ROOT));
    		}
    	}
    	lore.add("§7Click To Use");
    	im.setLore(lore);
    	is.setItemMeta(im);
    	is.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
    	return is;
    }
    
    private ArrayList<DrugEffect> loadEffectsFromJson(JsonArray ja, String fileName) {
    	ArrayList<DrugEffect> de = new ArrayList<DrugEffect>();
    	
    	ja.forEach(e -> {
    		if(!e.isJsonObject()) return;
    		JsonObject iJO = e.getAsJsonObject();
    		if(!iJO.has("type")) return;
    		
    		JsonFileInterpretter config = new JsonFileInterpretter(iJO);
    		
    		PotionEffectType type = config.getPotionEffect("type");
			int time = config.contains("time") ? config.getInteger("time") : 90;
			int intensity = config.contains("intensity") ? config.getInteger("intensity") : 1;
			
			if(config.getAllError().size() > 0) {
				sendConsoleMessage("§c[ERROR] Error in: §7" + fileName);
				config.getAllError().forEach(e2 -> {
	    			Bukkit.getConsoleSender().sendMessage(e2);
	    		});
				sendConsoleMessage("§c[ERROR] Skipping Effect");
				return;
			}
			
			de.add(new DrugEffect(type, time, intensity));
    	});
    	
    	return de;
    }
    
    private void addDrugFile(Drug d) {
    	File f = new File(folder, d.getFileName());
    	if(f.exists()) f.delete();
    	
    	JsonObject main = new JsonObject();
    	main.addProperty("name", d.getName());
    	main.addProperty("displayname", d.getDisplayname());
    	
    	if(d.getEffects().size() > 0) {
    		JsonArray ja = new JsonArray();
    		for(DrugEffect de : d.getEffects()) {
    			JsonObject jo = new JsonObject();
    			jo.addProperty("type", de.getEffect().getName().toUpperCase());
    			jo.addProperty("time", de.getTime()/20);
    			jo.addProperty("intensity", de.getIntensity());
    			ja.add(jo);
    		}
    		main.add("effects", ja);
    	}
    	
    	main.addProperty("item", d.getItem().getType().toString().toUpperCase());
    	main.addProperty("permission", d.getPermission());
    	main.addProperty("addictionLevel", d.getAddictionLevel());
    	
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
        	FileWriter writer = new FileWriter(f);
			gson.toJson(main, writer);
			writer.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
    }
    
    private void addRecipeFile(Drug d, DrugCraftingType dct, SDRecipe rec) {
    	File f = new File(drugRFolder, d.getFileName());
    	if(f.exists()) f.delete();
    	
    	JsonObject main = new JsonObject();
    	main.addProperty("drug", d.getName());
    	main.addProperty("type", dct.toString().toUpperCase());
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

    public void addDrug(String n, ItemStack i, AbstractDrugCraftingInventory peiu) {
    	String disName = n;
    	String name = ChatColor.stripColor(n).replaceAll(" ", "_").toUpperCase();
    	ArrayList<DrugEffect> drugEffects = new ArrayList<>();
    	for(InventoryPotionEffect ipe : peiu.getPotionEffects().getPotionEffects()) {
    		drugEffects.add(new DrugEffect(ipe.getType(), ipe.getTime(), ipe.getIntensity()));
    	}
    	String permission = "drugs.use." + name.toLowerCase();
    	Double addLevel = peiu.getAddLevel();
    	
    	ItemStack finalItem = createItem(n, i.getType(), drugEffects);
    	
    	Drug d = new Drug(name, disName, name + ".json", finalItem, drugEffects, permission, addLevel);
    	
    	if(drugs.containsKey(name)) {
    		drugs.remove(name);
    	}
    	
    	addDrug(d, name);
    	
    	addDrugFile(d);
    	
    	SDRecipe sd = Main.plugin.getRecipeManager().loadRecipe(d, peiu.getRecipe(), peiu.getRecipeType());
    	d.setRecipe(sd);
    	addRecipeFile(d, peiu.getRecipeType(), sd);
    }
    
    public void deleteDrug(Drug d) {
    	File drugFile = new File(folder, d.getFileName());
    	File drugRecipe = new File(drugRFolder, d.getFileName());
    	
    	if(drugSeeds.containsKey(d)) {
    		File drugSRecipe = new File(drugSFolder, drugSeeds.get(d).getFileName());
    		drugSRecipe.delete();
    		drugSeeds.remove(d);
    	}
    	
    	drugFile.delete();
    	drugRecipe.delete();
    	
    	drugs.remove(d.getName().toUpperCase());
    }
    
    /**
	 * Adds the Drug and it's file name the Map
	 * @param drug Drug
	 * @param name String
	 */
    public void addDrug(Drug drug, String name) {
        drugs.put(name, drug);
    }

	/**
	 * Compare a string to a Drug
	 * @param name Name of the Drug
	 * @return Drug Object
	 */
    public Drug getDrug(String name) {
        return drugs.getOrDefault(name, null);
    }

	/**
	 * Used to get all Drugs loaded
	 * @return Array of all Drug
	 */
    public ArrayList<Drug> getallDrugs() {
        return new ArrayList<>(drugs.values());
    }
    
	//TODO Edit Doc
	/**
	 * Get the ItemStack associated with DrugPlantItem from Drug
0	 * @param d Drug
	 * @return Drug 
	 */
    public ItemStack getItemStackFromDrug(Drug d) {
    	return drugSeeds.get(d).getItem();
    }
    
    /**
     * Get the DrugPlantItem from a Drug
     * @param d Drug
     * @return DrugPlantItem
     */
    public DrugPlantItem getDrugPlantItemFromDrug(Drug d) {
    	return drugSeeds.get(d);
    }

	/**
	 * Converts the current ItemStack into a DrugItemStack
	 * @param item ItemStack
	 * @return An ItemStack that is now considered a Drug
	 */
    public Drug matchDrug(@Nullable ItemStack item) {
		if(isDrugItem(item)){
			for(Drug drug : drugs.values()) {
				if(item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(drug.getDisplayname())) {
					return drug;
				}
			}
		}
        return null;
    }

	/**
	 * Checks if current item is a drug.
	 * @param item
	 * @return true if a drug 
	 */
    public boolean isDrugItem(ItemStack item) {
		if(item == null){
			return false;
		}
        for(Drug d : getallDrugs()) {
            if(item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(d.getDisplayname())) {
                return true;
            }
        }
        return false;
    }

	/** 
	* Default method for loading and creating default drug files.
	*/
    public void createDrugs() {
        sendConsoleMessage(ChatColor.BLUE + "[INFO] No Drugs where Found in your Folder. Creating Default Drugs!");
        
        // Create default drugs
        Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/drugs.json"));
        JsonArray drugs = new Gson().fromJson(reader, JsonArray.class);
        for(JsonElement drug : drugs) {
            drugFromJson(drug.getAsJsonObject(), drug.getAsJsonObject().get("name").getAsString());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
            	FileWriter writer = new FileWriter(new File(folder, drug.getAsJsonObject().get("name").getAsString() + ".json"));
				gson.toJson(drug, writer);
				writer.close();
			} catch (JsonIOException | IOException e) {
				e.printStackTrace();
			}
        }
        
        // Create default drug crafting
        Reader dReader = new InputStreamReader(this.getClass().getResourceAsStream("/drugcrafting.json"));
        JsonArray drugRecipes = new Gson().fromJson(dReader, JsonArray.class);
        for(JsonElement dRec : drugRecipes) {
        	Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
            	FileWriter writer = new FileWriter(new File(drugRFolder, dRec.getAsJsonObject().get("drug").getAsString() + ".json"));
				gson.toJson(dRec, writer);
				writer.close();
			} catch (JsonIOException | IOException e) {
				e.printStackTrace();
			}
        }
        loadRecipes();
        
        // Create default seed crafting
        Reader sReader = new InputStreamReader(getClass().getResourceAsStream("/seedcrafting.json"));
        JsonArray seedRecipes = new Gson().fromJson(sReader, JsonArray.class);
        for(JsonElement sRec : seedRecipes) {
        	Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
            	FileWriter writer = new FileWriter(new File(drugSFolder, sRec.getAsJsonObject().get("drug").getAsString() + ".json"));
				gson.toJson(sRec, writer);
				writer.close();
			} catch (JsonIOException | IOException e) {
				e.printStackTrace();
			}
        }
        loadSeedRecipes();
        
        sendConsoleMessage("§aDefault Drugs Created! Enjoy Simple-Drugs :D");
    }

    private void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

}
