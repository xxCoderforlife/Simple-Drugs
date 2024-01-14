package me.Coderforlife.SimpleDrugs.Druging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Druging.Util.DrugEffect;
import me.Coderforlife.SimpleDrugs.Util.AbstractSDCraftableManager;

public class Drug implements SDCraftableItem {

    private String name;
    private String displayname;
    private Double addictionLevel;
    private SDRecipe sdRecipe;
    private ArrayList<DrugEffect> effects;
    private String fileName;
    private ItemStack item;
    private Material mat;
    private String permission;
    private String carftingPerm;
    private Double sellPrice;
    private Double buyPrice;

    public Drug(String name, String displayname, Material item, ArrayList<DrugEffect> effects, String permission, Double addlvl, String carftPerm,Double sellp,Double buyp) {
        this.name = name;
        this.displayname = displayname;
        this.effects = effects;
        this.permission = permission;
        this.mat = item;
        this.item = createItem();
        this.addictionLevel = addlvl;
        this.carftingPerm = carftPerm;
        this.sellPrice = sellp;
        this.buyPrice = buyp;
    }

    /**
     * Default influence method for the player
     * 
     * @param p The Player to influence
     */
    public void influencePlayer(Player p) {
        HashMap<UUID,Double> addic = Main.plugin.getAddictionManager().addictionMap();
        Double addLvl = addic.get(p.getUniqueId());
        if(addLvl < Main.plugin.getConfig().getDouble("Drugs.Addiction.OverDose-Limit")){
            for (DrugEffect effect : this.effects) {
                if (!(p.hasPotionEffect(effect.getEffect()))) {
                    addic.put(p.getUniqueId(), addLvl + addictionLevel);
                    p.addPotionEffect(new PotionEffect(effect.getEffect(), effect.getTime(), effect.getIntensity()));
                } else {
                    for (PotionEffect effects : p.getActivePotionEffects()) {
                        p.addPotionEffect(new PotionEffect(effect.getEffect(), effects.getDuration() + 20, effect.getIntensity()));
                    }
                    addic.put(p.getUniqueId(), addLvl + addictionLevel);
                }
            }
        }else if(addLvl >= Main.plugin.getConfig().getDouble("Drugs.Addiction.OverDose-Limit")){
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

    /**
     * Used to influence any type of Mob
     * 
     * @param e Mob 
     * @apiNote See for info {@link Mob}
     */
    public void influenceAnimal(Mob e){
        if(!(e instanceof Mob)){
            return;
        }
        for(DrugEffect effect : effects){
            e.addPotionEffect(new PotionEffect(effect.getEffect() ,effect.getTime(), effect.getIntensity()));
        }
    }
    

    private ItemStack createItem() {
    	ItemStack is = new ItemStack(mat);
    	ItemMeta im = is.getItemMeta();
    	im.getPersistentDataContainer().set(Main.plugin.isDrugItem(), PersistentDataType.BYTE, (byte)1);
    	im.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayname));
    	im.addItemFlags(ItemFlag.HIDE_ENCHANTS , ItemFlag.HIDE_ATTRIBUTES);
    	List<String> lore = new ArrayList<String>();
    	if(effects.size() > 0) {
    		lore.add("§a§lEffects:");
    		for(DrugEffect de : effects) {
    			lore.add("§7- §6" + de.getEffect().getKey().getKey().toUpperCase(Locale.ROOT));
    		}
    	}
    	lore.add(ChatColor.translateAlternateColorCodes('&', 
        "&7- &oLeft-Click to get the drug"));
        lore.add(ChatColor.translateAlternateColorCodes('&', 
        "&7- &oRight-Click to get the effects"));
        lore.add(ChatColor.translateAlternateColorCodes('&', 
        "&7- &oShift-Left-Click to get a 64 stack"));
        lore.add(ChatColor.translateAlternateColorCodes('&', 
        "&7- &oShift-Right-Click to see the recipe"));
    	im.setLore(lore);
    	is.setItemMeta(im);
    	is.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
    	return is;
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
    
    public String getNamespaceName() {
    	return "Drug_" + name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getFile() {
    	return fileName;
    }
    
    public void setFile(String s) {
    	fileName = s;
    }

    public String getDisplayName() {
        return ChatColor.translateAlternateColorCodes('&', displayname);
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
    
    public double getAddictionLevel() {
    	return addictionLevel;
    }

    public String getCraftingPermission(){
        return carftingPerm;
    }
    public Double getSellPrice(){
        return sellPrice;
    }
    public Double getBuyPrice(){
        return buyPrice;
    }
    @Override
	public AbstractSDCraftableManager<Drug> getManager() {
		return Main.plugin.getDrugManager();
	}
    
}
