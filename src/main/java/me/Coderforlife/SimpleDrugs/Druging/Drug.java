package me.Coderforlife.SimpleDrugs.Druging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.potion.PotionEffect;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Druging.Addiction.AddictionManager;

public class Drug {

    private Main plugin = Main.plugin;
    private AddictionManager am = plugin.gAddictionManager();
    private String name;
    private String displayname;
    private SDRecipe sdRecipe;
    private ArrayList<DrugEffect> effects;
    private ItemStack item;
    private String permission;
    private boolean crafting;
    private boolean hasSeed = false;
    private Material seedItem = Material.WHEAT_SEEDS;
    private Recipe seedRecipe = null;
    private Integer harvestAmount = 1;

    public Drug(String name, String displayname, ItemStack item, ArrayList<DrugEffect> effects, String permission) {
        this.name = name;
        this.displayname = displayname;
        this.effects = effects;
        this.permission = permission;
        this.item = item;
    }

    /**
     * Default influence method for the player
     * 
     * @param p The Player to influence
     */
    public void influencePlayer(Player p) {
        HashMap<UUID,Double> addic = am.addictionMap();
        Double addLvl = addic.get(p.getUniqueId());
        if(addLvl < 3){
            for (DrugEffect effect : this.effects) {
                if (!(p.hasPotionEffect(effect.getEffect()))) {
                    addic.put(p.getUniqueId(), addLvl + 0.3);
                    p.addPotionEffect(new PotionEffect(effect.getEffect(), effect.getTime(), effect.getIntensity()));
                } else {
                    for (PotionEffect effects : p.getActivePotionEffects()) {
                        p.addPotionEffect(new PotionEffect(effect.getEffect(), effects.getDuration() + 20, effect.getIntensity()));
                    }
                    addic.put(p.getUniqueId(), addLvl + 0.1);
                    p.sendMessage(Double.toString(addLvl));
                }
            }
        }else if(addLvl >= 3){
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_HURT, 1.0f, 1.0f);
            p.setHealth(0.0);
        }
    }

    /**
     * A method to influence other players via command or event
     * 
     * @param d The Drug you want to influence the another Player
     * @param p The player to influence
     */
    public void influenceOtherPlayers(Drug d, Player p) {
        for (DrugEffect effect : d.getEffects()) {
            p.addPotionEffect(new PotionEffect(effect.getEffect(), effect.getTime(), effect.getIntensity()));
        }
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

    public SDRecipe getRecipe() {
        return sdRecipe;
    }

    public void setRecipe(SDRecipe recipe) {
        sdRecipe = recipe;
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

    public boolean isCraftable() {
        return crafting;
    }

    public void setCraftable(boolean crafting) {
        this.crafting = crafting;
    }

    public void setHasSeed(boolean b) {
        hasSeed = b;
    }

    public boolean hasSeed() {
        return hasSeed;
    }

    public void setSeedRecipe(Recipe r) {
        seedRecipe = r;
    }

    public Recipe getSeedRecipe() {
        return seedRecipe;
    }

    public Material getSeedItem() {
        return seedItem;
    }

    public void setSeedItem(Material m) {
        seedItem = m;
    }

    public Integer getHarvestAmount() {
        return harvestAmount;
    }

    public void setHarvestAmount(int i) {
        harvestAmount = i;
    }

}
