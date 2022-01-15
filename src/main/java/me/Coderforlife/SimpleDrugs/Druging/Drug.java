package me.Coderforlife.SimpleDrugs.Druging;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.Coderforlife.SimpleDrugs.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
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

    public static void loadDrugs() {
        try {
            JsonArray array = (JsonArray) JsonParser.parseReader(new FileReader("plugins/Simple-Drugs/drugs.json"));
            for(JsonElement element : array) {
                JsonObject drug = element.getAsJsonObject();

                /* Grab the Name */
                String name = drug.get("name").getAsString();

                /* Grab the Name */
                String displayname = drug.get("displayname").getAsString().replace("&", "§");

                /* Get the Effects */
                JsonArray effects = drug.getAsJsonArray("effects");
                ArrayList<DrugEffect> effectsList = new ArrayList<DrugEffect>();
                for(JsonElement effect : effects) {
                    JsonObject effectObject = effect.getAsJsonObject();
                    PotionEffectType type = PotionEffectType.getByName(effectObject.get("type").getAsString().toUpperCase());
                    int duration = effectObject.get("time").getAsInt();
                    int amplifier = effectObject.get("intensity").getAsInt();
                    effectsList.add(new DrugEffect(type, duration, amplifier));
                }

                /* Get the Item Result for Crafting */
                ItemStack result = new ItemStack(Material.getMaterial(drug.get("item").getAsString().toUpperCase()));
                ItemMeta meta = result.getItemMeta();
                meta.setDisplayName(displayname);
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§a§lEffects:");
                for(DrugEffect effect : effectsList) {
                    lore.add("§7- §6" + effect.getEffect().getName().toUpperCase(Locale.ROOT));
                }
                lore.add("§7Click to Use");
                meta.setLore(lore);
                result.setItemMeta(meta);

                /* Generate the Recipe */
                JsonArray recipe = drug.getAsJsonArray("recipe");
                NamespacedKey key = new NamespacedKey(Main.plugin, "drugs_" + name);
                ShapedRecipe shapedRecipe = new ShapedRecipe(key, result);

                shapedRecipe.shape("ABC", "DEF", "GHI");
                shapedRecipe.setIngredient('A', material(recipe, 0));
                shapedRecipe.setIngredient('B', material(recipe, 1));
                shapedRecipe.setIngredient('C', material(recipe, 2));
                shapedRecipe.setIngredient('D', material(recipe, 3));
                shapedRecipe.setIngredient('E', material(recipe, 4));
                shapedRecipe.setIngredient('F', material(recipe, 5));
                shapedRecipe.setIngredient('G', material(recipe, 6));
                shapedRecipe.setIngredient('H', material(recipe, 7));
                shapedRecipe.setIngredient('I', material(recipe, 8));

                boolean craftable = drug.get("crafting").getAsBoolean();
                String permission = drug.get("permission").getAsString();

                if(craftable)
                    Main.plugin.getServer().addRecipe(shapedRecipe);

                drugs.put(name, new Drug(name, displayname, shapedRecipe, effectsList, result, permission, craftable));
            }
        } catch(FileNotFoundException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "drugs.json not found, creating File");
            try {
                byte[] in = Drug.class.getResourceAsStream("/drugs.json").readAllBytes();
                File targetFile = new File("plugins/Simple-Drugs/drugs.json");
                targetFile.createNewFile();
                OutputStream outStream = new FileOutputStream(targetFile);
                outStream.write(in);
                outStream.close();
                loadDrugs();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Drugs are Ready to Use");
            } catch(FileNotFoundException ex) {
                ex.printStackTrace();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Drug getDrug(String name) {
        return drugs.get(name);
    }

    public static ArrayList<Drug> getDrugs() {
        return new ArrayList<Drug>(drugs.values());
    }

    public void influencePlayer(Player player) {
        for(DrugEffect effect : this.effects) {
            player.addPotionEffect(new PotionEffect(effect.getEffect(), effect.getTime(), effect.getIntensity()));
        }
    }

    public boolean isDrugItem(ItemStack item) {
        return item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(this.displayname);
    }

    public static Drug matchDrug(ItemStack item) {
        for(Drug drug : drugs.values()) {
            if(item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(drug.getDisplayname())) {
                return drug;
            }
        }
        return null;
    }

    private static Character getRandomCharacter() {
        return (char) (Math.random() * 26 + 'a');
    }

    private static Material material(JsonArray obj, int i) {
        return Material.getMaterial(obj.get(i).getAsString().toUpperCase());
    }

    public static ArrayList<Drug> getallDrugs() {
        return new ArrayList<Drug>(drugs.values());
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
