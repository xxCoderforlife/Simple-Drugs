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
import me.Coderforlife.SimpleDrugs.DrugPlants.DrugPlantItem;
import me.Coderforlife.SimpleDrugs.Util.JsonFileInterpretter;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;
import net.md_5.bungee.api.ChatColor;

public class DrugManager {
    Main plugin = Main.plugin;
    private final File folder = new File(plugin.getDataFolder() + File.separator + "Drugs");
    private Map<String, Drug> drugs = new HashMap<>();

    public void loadFiles() {
    	if(!folder.exists()) folder.mkdir();
    	// TODO: Create default drugs
    	for(File f : folder.listFiles()) {
    		if(f.getName().endsWith(".json")) {
    			try {
					JsonObject obj = new Gson().fromJson(new FileReader(f), JsonObject.class);
					DrugLoadError dle = canDrugLoad(obj);
					if(!dle.canLoad()) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						dle.printAllErrors();
						continue;
					}
					drugFromJson(obj, f.getName());
				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    	StringJoiner enabled = new StringJoiner(", ");
    	for(Drug d : getallDrugs()) {
    		enabled.add(d.getName());
    	}
    	Bukkit.getConsoleSender().sendMessage("§6Enabled Drugs: §a" + enabled.toString().trim());
    	
//    	 StringBuilder enabled = new StringBuilder();
//         StringBuilder disabled = new StringBuilder();
//         for(Drug drug : getallDrugs()) {
//             if(drug.isCraftable()) {
//                 enabled.append(drug.getName()).append(", ");
//             } else {
//                 disabled.append(drug.getName()).append(", ");
//             }
//         }
//         if(enabled.length() > 0)
//             sendConsoleMessage("§6Enabled Drugs: §a" + enabled);
//
//         if(disabled.length() > 0)
//             sendConsoleMessage("§6Disabled Drugs: §c" + disabled);
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
    	
    	ItemStack is = createItem(displayName, mat, effects);
    	
    	if(config.getAllError().size() > 0) {
    		Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
    		config.getAllError().forEach(e -> {
    			Bukkit.getConsoleSender().sendMessage(e);
    		});
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Drug");
    		return;
    	}
    	
    	addDrug(new Drug(name, displayName, is, effects, permission), name);
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
			Integer time = config.contains("time") ? config.getInteger("time") : 90;
			Integer intensity = config.contains("intensity") ? config.getInteger("intensity") : 1;
			
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
