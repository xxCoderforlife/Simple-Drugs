package me.Coderforlife.SimpleDrugs.Druging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent;
import me.Coderforlife.SimpleDrugs.Crafting.DrugCraftingType;
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
    	
    	StringJoiner enabled = new StringJoiner(", ");
    	for(Drug d : getallDrugs()) {
    		enabled.add(d.getName());
    	}
    	if(enabled.length() > 0) Bukkit.getConsoleSender().sendMessage("§6Enabled Drugs: §a" + enabled.toString().trim());
    }
    
    private void loadDrugs() {
    	for(File f : folder.listFiles()) {
    		if(f.getName().endsWith(".json")) {
    			try {
					JsonObject obj = new Gson().fromJson(new FileReader(f), JsonObject.class);
					DrugLoadError dle = canDrugLoad(obj);
					if(!dle.canLoad()) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						dle.printAllErrors();
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Drug");
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
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						dle.printAllErrors();
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Seed Recipe");
						continue;
					}
					
					JsonFileInterpretter config = new JsonFileInterpretter(obj);
					
					Drug d = config.getDrug("drug");
					
					if(d == null) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Drug not found: §7" + config.getString("drug"));
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					DrugCraftingType dct = config.contains("type") ? config.getDrugCraftingType("type") : DrugCraftingType.SHAPED;
					JsonArray ja = config.getJsonArray("recipe");
					if(dct.equals(DrugCraftingType.SHAPED) && ja.size() != 9) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Shaped Recipes require 9 items or air if none");
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					Integer harvestAmount = config.getInteger("harvest-amount");
					ItemStack seedItem = config.getItem("seed-item");
					
					if(config.getAllError().size() > 0) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
			    		config.getAllError().forEach(e -> {
			    			Bukkit.getConsoleSender().sendMessage(e);
			    		});
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Drug");
						continue;
					}
					
					DrugPlantItem dpi = new DrugPlantItem(d, seedItem, Material.FARMLAND, harvestAmount);
					
					if(!loadSeedCrafting(f.getName(), dpi, ja, dct)) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in Recipe");
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
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
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						dle.printAllErrors();
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					JsonFileInterpretter config = new JsonFileInterpretter(obj);
					
					Drug d = config.getDrug("drug");
					
					if(d == null) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Drug not found: §7" + config.getString("drug"));
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					DrugCraftingType dct = config.contains("type") ? config.getDrugCraftingType("type") : DrugCraftingType.SHAPED;
					JsonArray ja = config.getJsonArray("recipe");
					if(dct.equals(DrugCraftingType.SHAPED) && ja.size() != 9) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Shaped Recipes require 9 items or air if none");
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
					if(config.getAllError().size() > 0) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
			    		config.getAllError().forEach(e -> {
			    			Bukkit.getConsoleSender().sendMessage(e);
			    		});
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Drug");
						continue;
					}
					
					if(!loadCraftingRecipe(f.getName(), d, ja, dct)) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in Recipe");
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
						continue;
					}
					
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
			return false;
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
    
    private boolean loadCraftingRecipe(String fileName, Drug d, JsonArray ja, DrugCraftingType dct) {
    	switch(dct) {
		case FURNACE:
			return false;
		case SHAPED:
			SDShaped shaped = new SDShaped(d.getName(), d.getItem());
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
			SDShapeless shapeless = new SDShapeless(d.getName(), d.getItem());
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
    
    private List<ItemStack> loadMaterialsForCrafting(String fileName, JsonArray ja) {
		List<ItemStack> materials = new ArrayList<>();
	    
		for(int i = 0; i < ja.size(); i++) {
    		Material m = Material.getMaterial(ja.get(i).getAsString().toUpperCase());
    		if(m == null) {
    			CraftingComponent cc = Main.plugin.getCraftingManager().getByName(ja.get(i).getAsString().toUpperCase());
    			if(cc == null) {
    				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
    				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Material: §7" + ja.get(i).getAsString().toUpperCase() + " §cdoes not exist as a Minecraft Material or Custom Crafting Component");
    				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
    				return null;
    			}
    			
    			materials.add(cc.getStack());
    		} else {
    			materials.add(new ItemStack(m));
    		}
    	}
		
    	if(materials.size() > 9) {
    		Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Materials added cannot be above 9");
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
    	String permission = config.contains("permission") ? config.getString("permission") : "drugs.use." + name.toLowerCase();
    	ArrayList<DrugEffect> effects = (config.contains("effects") && config.isJsonArray("effects")) ? loadEffectsFromJson(config.getJsonArray("effects"), fileName) : new ArrayList<DrugEffect>();
    	
    	if(config.getAllError().size() > 0) {
    		Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
    		config.getAllError().forEach(e -> {
    			Bukkit.getConsoleSender().sendMessage(e);
    		});
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Drug");
    		return;
    	}
    	
    	ItemStack is = createItem(displayName, mat, effects);
    	
    	addDrug(new Drug(name, displayName, is, effects, permission), name.toUpperCase());
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
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
				config.getAllError().forEach(e2 -> {
	    			Bukkit.getConsoleSender().sendMessage(e2);
	    		});
				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Effect");
				return;
			}
			
			de.add(new DrugEffect(type, time, intensity));
    	});
    	
    	return de;
    }

    /* Grabbing and Setting Drug Data */
    public void addDrug(Drug drug, String name) {
        drugs.put(name, drug);
    }

    public Drug getDrug(String name) {
        return drugs.getOrDefault(name, null);
    }

    public ArrayList<Drug> getallDrugs() {
        return new ArrayList<>(drugs.values());
    }
    
    public ItemStack getItemStackFromDrug(Drug d) {
    	return drugSeeds.get(d);
    }

    public Drug matchDrug(ItemStack item) {
        for(Drug drug : drugs.values()) {
            if(item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(drug.getDisplayname())) {
                return drug;
            }
        }
        return null;
    }

    public boolean isDrugItem(ItemStack item) {
        for(Drug d : getallDrugs()) {
            if(item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(d.getDisplayname())) {
                return true;
            }
        }
        return false;
    }

    private void DrugfromJson(JsonObject drug, String name) {
        /* Displayname */
        String displayname = drug.get("displayname").getAsString().replace("&", "§");

        /* Effects */
        JsonArray effects = drug.getAsJsonArray("effects");
        ArrayList<DrugEffect> effectsList = new ArrayList<>();
        for(JsonElement effect : effects) {
            JsonObject effectObject = effect.getAsJsonObject();
            PotionEffectType type = getEffectfromJson(effectObject);
            int duration = effectObject.get("time").getAsInt();
            int amplifier = effectObject.get("intensity").getAsInt() - 1;
            effectsList.add(new DrugEffect(type, duration, amplifier));
        }

        /* Get the Item Result for Crafting */
        ItemStack result = new ItemStack(MaterialFromObject(drug, "item"));
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(displayname);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§a§lEffects:");
        for(DrugEffect effect : effectsList) {
            lore.add("§7- §6" + effect.getEffect().getName().toUpperCase(Locale.ROOT));
        }
        lore.add("§7Click to Use");
        meta.setLore(lore);
        result.setItemMeta(meta);
        result.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);

        /* Generate the Recipe */
        JsonArray recipe = drug.getAsJsonArray("recipe");
        NamespacedKey key = new NamespacedKey(Main.plugin, "drugs_" + name);
        ShapedRecipe shapedRecipe = new ShapedRecipe(key, result);

        shapedRecipe.shape("ABC", "DEF", "GHI");
        shapedRecipe.setIngredient('A', MaterialFromArray(recipe, 0));
        shapedRecipe.setIngredient('B', MaterialFromArray(recipe, 1));
        shapedRecipe.setIngredient('C', MaterialFromArray(recipe, 2));
        shapedRecipe.setIngredient('D', MaterialFromArray(recipe, 3));
        shapedRecipe.setIngredient('E', MaterialFromArray(recipe, 4));
        shapedRecipe.setIngredient('F', MaterialFromArray(recipe, 5));
        shapedRecipe.setIngredient('G', MaterialFromArray(recipe, 6));
        shapedRecipe.setIngredient('H', MaterialFromArray(recipe, 7));
        shapedRecipe.setIngredient('I', MaterialFromArray(recipe, 8));

        boolean craftable = drug.get("crafting").getAsBoolean();
        String permission = drug.get("permission").getAsString();
        
        if(craftable)
            Main.plugin.getServer().addRecipe(shapedRecipe);

        Drug d = new Drug(name, displayname, shapedRecipe, effectsList, result, permission, craftable);
        Boolean hasSeed = drug.has("has_seed") ? drug.get("has_seed").getAsBoolean() : false;
        d.setHasSeed(hasSeed);
        
        if(drug.has("seed_recipe")) {
        	JsonArray seedRecipe = drug.getAsJsonArray("seed_recipe");
            NamespacedKey seedKey = new NamespacedKey(Main.plugin, "drugseeds_" + name);
            
            String seedMat = drug.has("seed_item") ? drug.get("seed_item").getAsString() : "WHEAT_SEEDS";
            Integer harvestAmount = drug.has("seed_harvest_amount") ? drug.get("seed_harvest_amount").getAsInt() : 1;
            
            DrugPlantItem dpi = new DrugPlantItem(d, new ItemStack(Material.valueOf(seedMat)), Material.FARMLAND, harvestAmount);
            
            ShapedRecipe seedShapedRecipe = new ShapedRecipe(seedKey, dpi.makeItem());
            
            seedShapedRecipe.shape("ABC", "DEF", "GHI");
            seedShapedRecipe.setIngredient('A', MaterialFromArray(seedRecipe, 0));
            seedShapedRecipe.setIngredient('B', MaterialFromArray(seedRecipe, 1));
            seedShapedRecipe.setIngredient('C', MaterialFromArray(seedRecipe, 2));
            seedShapedRecipe.setIngredient('D', MaterialFromArray(seedRecipe, 3));
            seedShapedRecipe.setIngredient('E', MaterialFromArray(seedRecipe, 4));
            seedShapedRecipe.setIngredient('F', MaterialFromArray(seedRecipe, 5));
            seedShapedRecipe.setIngredient('G', MaterialFromArray(seedRecipe, 6));
            seedShapedRecipe.setIngredient('H', MaterialFromArray(seedRecipe, 7));
            seedShapedRecipe.setIngredient('I', MaterialFromArray(seedRecipe, 8));
            Main.plugin.getServer().addRecipe(seedShapedRecipe);
            
            d.setHarvestAmount(harvestAmount);
            d.setSeedItem(Material.valueOf(seedMat));
            d.setHasSeed(hasSeed);
            d.setSeedRecipe(seedShapedRecipe);
        }
        
        addDrug(d, name);
    }

    public void createDrugs() {
        sendConsoleMessage(ChatColor.BLUE + "[INFO] No Drugs where Found in your Folder. Creating Default Drugs!");
        Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/drugs.json"));
        JsonArray drugs = new Gson().fromJson(reader, JsonArray.class);
        for(JsonElement drug : drugs) {
            DrugfromJson(drug.getAsJsonObject(), drug.getAsJsonObject().get("name").getAsString());
        }
        sendConsoleMessage("§aDefault Drugs Created! Enjoy Simple-Drugs :D");
    }

    private PotionEffectType getEffectfromJson(JsonObject element) {
        PotionEffectType effect = PotionEffectType.getByName(element.get("type").getAsString().toUpperCase());
        if(effect != null)
            return effect;

        sendConsoleMessage("§c[ERROR] Could not find Effect: §7" + element.get("type").getAsString());
        sendConsoleMessage("§c[ERROR] Make Sure the Name is Correct!");
        sendConsoleMessage("§c[INFO] Replacing all Unknown Effects with POISON to Ensure the Plugin keeps Running!");
        return PotionEffectType.POISON;
    }

    private Material MaterialFromArray(JsonArray obj, int i) {
        Material mat = Material.getMaterial(obj.get(i).getAsString().toUpperCase());
        if(mat != null)
            return mat;

        sendConsoleMessage("§c[ERROR] Could not find Material: §7" + obj.get(i).getAsString());
        sendConsoleMessage("§c[ERROR] Make Sure the Name is Correct!");
        sendConsoleMessage("§b[INFO] Replacing all Unknown Items with BONE to Ensure the Plugin keeps Running!");
        return Material.BONE;
    }

    private Material MaterialFromObject(JsonObject obj, String key) {
        Material mat = Material.getMaterial(obj.get(key).getAsString().toUpperCase());
        if(mat != null)
            return mat;

        sendConsoleMessage("§c[ERROR] Could not find Material: §7" + obj.get(key).getAsString());
        sendConsoleMessage("§cMake Sure the Name is Correct!");
        sendConsoleMessage("§b[INFO] Replacing all Unknown Items with BONE to Ensure the Plugin keeps Running!");
        return Material.BONE;
    }

    private void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

}
