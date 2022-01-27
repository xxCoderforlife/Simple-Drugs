package me.Coderforlife.SimpleDrugs.Druging;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.potion.PotionEffect;

public class Drug {

    private String name;
    private String displayname;
    private Recipe recipe;
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
    
    public Drug(String name, String displayname, Recipe recipe, ArrayList<DrugEffect> effects, ItemStack item, String permission, boolean crafting) {
        this.name = name;
        this.displayname = displayname;
        this.recipe = recipe;
        this.effects = effects;
        this.item = item;
        this.permission = permission;
        this.crafting = crafting;
    }

    public void influencePlayer(Player player) {
        for(DrugEffect effect : this.effects) {
            player.addPotionEffect(new PotionEffect(effect.getEffect(), effect.getTime(), effect.getIntensity()));
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
