package me.Coderforlife.SimpleDrugs.Druging;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import net.md_5.bungee.api.ChatColor;

public class DrugManager {
    final File folder = new File("plugins/Simple-Drugs/");
    private Map<String, Drug> drugs = new HashMap<>();

    public void loadFiles() throws IOException, URISyntaxException {
        if(folder.listFiles().length < 2) {
            createDrugs();
            return;
        }
        for(File file : folder.listFiles()) {
            if(file.getName().endsWith(".json")) {
                JsonObject obj = new Gson().fromJson(new FileReader(file), JsonObject.class);
                if(obj.get("displayname").getAsString() != null) {
                    DrugfromJson(obj, file.getName().replace(".json", ""));
                }
            }
        }
        StringBuilder enabled = new StringBuilder();
        StringBuilder disabled = new StringBuilder();
        for(Drug drug : getallDrugs()) {
            if(drug.isCraftable()) {
                enabled.append(drug.getName()).append(", ");
            } else {
                disabled.append(drug.getName()).append(", ");
            }
        }
        if(enabled.length() > 0)
            sendConsoleMessage("§6Enabled Drugs: §a" + enabled);

        if(disabled.length() > 0)
            sendConsoleMessage("§6Disabled Drugs: §c" + disabled);
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

        addDrug(new Drug(name, displayname, shapedRecipe, effectsList, result, permission, craftable), name);
    }

    private void DrugtoJson(Drug drug) {
        File file = new File(folder, drug.getName() + ".json");
        if(file.exists()) {
            file.delete();
        }
        JsonObject obj = new JsonObject();
        obj.addProperty("displayname", drug.getDisplayname().replace("§", "&"));
        obj.addProperty("permission", drug.getPermission());
        obj.addProperty("crafting", drug.isCraftable());
        obj.addProperty("item", drug.getItem().getType().name());
        JsonArray effects = new JsonArray();
        for(DrugEffect effect : drug.getEffects()) {
            JsonObject effectObject = new JsonObject();
            effectObject.addProperty("type", effect.getEffect().getName());
            effectObject.addProperty("time", effect.getTime());
            effectObject.addProperty("intensity", effect.getIntensity());
            effects.add(effectObject);
        }
        obj.add("effects", effects);
        JsonArray recipe = new JsonArray();
        ShapedRecipe r = (ShapedRecipe) drug.getRecipe();
        recipe.add(r.getIngredientMap().get('A').getType().toString());
        recipe.add(r.getIngredientMap().get('B').getType().toString());
        recipe.add(r.getIngredientMap().get('C').getType().toString());
        recipe.add(r.getIngredientMap().get('D').getType().toString());
        recipe.add(r.getIngredientMap().get('E').getType().toString());
        recipe.add(r.getIngredientMap().get('F').getType().toString());
        recipe.add(r.getIngredientMap().get('G').getType().toString());
        recipe.add(r.getIngredientMap().get('H').getType().toString());
        recipe.add(r.getIngredientMap().get('I').getType().toString());
        obj.add("recipe", recipe);

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(obj.toString());
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void createDrugs() {
        sendConsoleMessage(ChatColor.BLUE + "[INFO] No Drugs where Found in your Folder. Creating Default Drugs!");
        Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/drugs.json"));
        JsonArray drugs = new Gson().fromJson(reader, JsonArray.class);
        for(JsonElement drug : drugs) {
            DrugfromJson(drug.getAsJsonObject(), drug.getAsJsonObject().get("name").getAsString());
        }
        sendConsoleMessage("§aDefault Drugs Created! Enjoy Simple-Drugs :D");
        saveallDrugs();
    }

    public void saveallDrugs() {
        for(Drug drug : getallDrugs()) {
            DrugtoJson(drug);
        }
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
