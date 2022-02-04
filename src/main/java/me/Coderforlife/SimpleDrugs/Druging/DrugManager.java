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
import org.bukkit.potion.PotionEffectType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent;
import me.Coderforlife.SimpleDrugs.Crafting.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.DrugPlants.DrugPlantItem;
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
    private Map<Drug, ItemStack> drugSeeds = new HashMap<>();


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
					JsonArray ja = config.getJsonArray("recipe");
					if(dct.equals(DrugCraftingType.SHAPED) && ja.size() != 9) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						sendConsoleMessage("§c[ERROR] Shaped Recipes require 9 items or air if none");
						sendConsoleMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
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
					
					DrugPlantItem dpi = new DrugPlantItem(d, seedItem, Material.FARMLAND, harvestAmount);
					
					if(!loadSeedCrafting(f.getName(), dpi, ja, dct)) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						sendConsoleMessage("§c[ERROR] Error in Recipe");
						sendConsoleMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					drugSeeds.put(d, seedItem);
					
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
					JsonArray ja = config.getJsonArray("recipe");
					if(dct.equals(DrugCraftingType.SHAPED) && ja.size() != 9) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
						sendConsoleMessage("§c[ERROR] Shaped Recipes require 9 items or air if none");
						sendConsoleMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					if(config.getAllError().size() > 0) {
						sendConsoleMessage("§c[ERROR] Error in: §7" + f.getName());
			    		config.getAllError().forEach(e -> {
			    			sendConsoleMessage(e);
			    		});
			    		sendConsoleMessage("§c[ERROR] Skipping Drug");
						continue;
					}
					
					SDRecipe recipe = loadCraftingRecipe(f.getName(), d, ja, dct);
					
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
    
    private boolean loadSeedCrafting(String fileName, DrugPlantItem dpi, JsonArray ja, DrugCraftingType dct) {
    	switch(dct) {
		case FURNACE:
			List<ItemStack> furnaceMats = loadMaterialsForCrafting(fileName, ja);
			
			if(furnaceMats.size() > 1 || furnaceMats.size() == 0) {
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Can only have one input for Recipe using FURNACE type");
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
			}
			
			SDFurnace furnace = new SDFurnace("DrugSeed_" + dpi.getDrug().getName(), dpi.makeItem(), furnaceMats.get(0), 0f, 90);
			furnace.registerRecipe();
			
			return true;
		case SHAPED:
			SDShaped shaped = new SDShaped("DrugSeed_" + dpi.getDrug().getName(), dpi.makeItem());
			List<ItemStack> mats = loadMaterialsForCrafting(fileName, ja);
			
			if(mats == null) {
				return false;
			}
			
			for(int i = 0; i < mats.size(); i++) {
				shaped.addItemStack(mats.get(i));
			}

			shaped.registerRecipe();
			
			return true;
		case SHAPELESS:
			SDShapeless shapeless = new SDShapeless("DrugSeed_" + dpi.getDrug().getName(), dpi.makeItem());
			List<ItemStack> materials = loadMaterialsForCrafting(fileName, ja);
			
			if(materials == null) {
				return false;
			}
			
			materials.forEach(e -> {
				shapeless.addItemStack(e);
			});
			
			shapeless.registerRecipe();
			
			return true;
		default:
			return false;
    	}
    }
    
    private SDRecipe loadCraftingRecipe(String fileName, Drug d, JsonArray ja, DrugCraftingType dct) {
    	switch(dct) {
		case FURNACE:
			List<ItemStack> furnaceMats = loadMaterialsForCrafting(fileName, ja);
			
			if(furnaceMats.size() > 1 || furnaceMats.size() == 0) {
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Can only have one input for Recipe using FURNACE type");
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
				return null;
			}
			
			SDFurnace furnace = new SDFurnace(d.getName(), d.getItem(), furnaceMats.get(0), 0f, 90);
			furnace.registerRecipe();
			return furnace;
		case SHAPED:
			SDShaped shaped = new SDShaped(d.getName(), d.getItem());
			List<ItemStack> mats = loadMaterialsForCrafting(fileName, ja);
			
			if(mats == null) {
				return null;
			}
			
			for(int i = 0; i < mats.size(); i++) {
				shaped.addItemStack(mats.get(i));
			}

			shaped.registerRecipe();
			
			return shaped;
		case SHAPELESS:
			SDShapeless shapeless = new SDShapeless(d.getName(), d.getItem());
			List<ItemStack> materials = loadMaterialsForCrafting(fileName, ja);
			
			if(materials == null) {
				return null;
			}
			
			materials.forEach(e -> {
				shapeless.addItemStack(e);
			});
			
			shapeless.registerRecipe();
			
			return shapeless;
		default:
			return null;
    	}
    }
    
    private List<ItemStack> loadMaterialsForCrafting(String fileName, JsonArray ja) {
		List<ItemStack> materials = new ArrayList<>();
	    
		for(int i = 0; i < ja.size(); i++) {
    		Material m = Material.getMaterial(ja.get(i).getAsString().toUpperCase());
    		if(m == null) {
    			CraftingComponent cc = Main.plugin.getCraftingManager().getByName(ja.get(i).getAsString().toUpperCase());
    			if(cc == null) {
    				sendConsoleMessage("§c[ERROR] Error in: §7" + fileName);
    				sendConsoleMessage("§c[ERROR] Material: §7" + ja.get(i).getAsString().toUpperCase() + " §cdoes not exist as a Minecraft Material or Custom Crafting Component");
    				sendConsoleMessage("§c[ERROR] Skipping Recipe");
    				return null;
    			}
    			
    			materials.add(cc.getStack());
    		} else {
    			materials.add(new ItemStack(m));
    		}
    	}
		
    	if(materials.size() > 9) {
    		sendConsoleMessage("§c[ERROR] Error in: §7" + fileName);
    		sendConsoleMessage("§c[ERROR] Materials added cannot be above 9");
    		return null;
    	}
    	
    	return materials;
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
    	
    	addDrug(new Drug(name, displayName, is, effects, permission,addLvl), name.toUpperCase());
    }
    
    private ItemStack createItem(String s, Material item, ArrayList<DrugEffect> effects) {
    	ItemStack is = new ItemStack(item);
    	ItemMeta im = is.getItemMeta();
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
	 * Not too sure what this does
0	 * @param d Drug
	 * @return Drug 
	 */
    public ItemStack getItemStackFromDrug(Drug d) {
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
