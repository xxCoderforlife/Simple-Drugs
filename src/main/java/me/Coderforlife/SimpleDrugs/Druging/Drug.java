package me.Coderforlife.SimpleDrugs.Druging;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.Coderforlife.SimpleDrugs.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Drug {

    private static HashMap<String, Drug> drugs = new HashMap<>();

    private String name;
    private String displayname;
    private Recipe recipe;
    private ArrayList<DrugEffect> effects;
    private ItemStack item;
    private String permission;
    private boolean crafting;

    public Drug() {

    }

    public Drug(String name, String displayname, Recipe recipe, ArrayList<DrugEffect> effects, ItemStack item, String permission, boolean crafting) {
        this.name = name;
        this.displayname = displayname;
        this.recipe = recipe;
        this.effects = effects;
        this.item = item;
        this.permission = permission;
        this.crafting = crafting;
        drugs.put(name, this);
    }

    public void loadDrugs() {
        try {
            JsonArray array = new Gson().fromJson(new FileReader("plugins/Simple-Drugs/drugs.json"), JsonArray.class);

            for(JsonElement element : array) {
                JsonObject drug = element.getAsJsonObject();

                /* Grab the Name */
                String name = drug.get("name").getAsString();

                /* Grab the Name */
                String displayname = drug.get("displayname").getAsString().replace("&", "§");

                /* Get the Effects */
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

                drugs.put(name, new Drug(name, displayname, shapedRecipe, effectsList, result, permission, craftable));
            }
            StringBuilder enabled = new StringBuilder();
            StringBuilder disabled = new StringBuilder();
            for(Drug drug : drugs.values()) {
                if(drug.isCrafting()) {
                    enabled.append(drug.name).append(", ");
                } else {
                    disabled.append(drug.name).append(", ");
                }
            }
            if(enabled.length() > 0)
                sendConsoleMessage("§6Enabled Drugs: §a" + enabled);

            if(disabled.length() > 0)
                sendConsoleMessage("§6Disabled Drugs: §c" + disabled);

        } catch(FileNotFoundException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[INFO] Could not find drugs.json, creating a new one.");
            try {
                byte[] in = Drug.class.getResourceAsStream("/drugs.json").readAllBytes();
                File targetFile = new File("plugins/Simple-Drugs/drugs.json");
                targetFile.createNewFile();
                OutputStream outStream = new FileOutputStream(targetFile);
                outStream.write(in);
                outStream.close();
                loadDrugs();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Drugs.json Created!");
            } catch(IOException ex) {
                ex.printStackTrace();
            }
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

    /* Grabbing and Setting Drug Data */
    public Drug getDrug(String name) {
        return drugs.getOrDefault(name, null);
    }

    public ArrayList<Drug> getDrugs() {
        return new ArrayList<>(drugs.values());
    }

    public void influencePlayer(Player player) {
        for(DrugEffect effect : this.effects) {
            player.addPotionEffect(new PotionEffect(effect.getEffect(), effect.getTime(), effect.getIntensity()));
        }
    }

    public boolean isDrugItem(ItemStack item) {
        return item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(this.displayname);
    }

    public Drug matchDrug(ItemStack item) {
        for(Drug drug : drugs.values()) {
            if(item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(drug.getDisplayname())) {
                return drug;
            }
        }
        return null;
    }

    public ArrayList<Drug> getallDrugs() {
        return new ArrayList<>(drugs.values());
    }

    public ArrayList<DrugEffect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<DrugEffect> effects) {
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isCrafting() {
        return crafting;
    }

    public void setCrafting(boolean crafting) {
        this.crafting = crafting;
    }
}
