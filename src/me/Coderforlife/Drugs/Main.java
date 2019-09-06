package me.Coderforlife.Drugs;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main
  extends JavaPlugin
{

	public static ItemStack wheatd = new ItemStack(Material.WHEAT);
    public static ItemStack suagrd = new ItemStack(Material.SUGAR);
	public static ItemStack paperd = new ItemStack(Material.PAPER);
	public static ItemStack brown_mushroomd = new ItemStack(Material.BROWN_MUSHROOM);



	
	public static String weed = "Weed: Slowness, Hunger, Luck";
	public static String coke = "Coke: Night Vison, Fire Res, FastDig";
	public static String acid = "Acid: Night Vison, Jump, Heal";
	public static String shrooms = "Shrooms: Night Vison";
  Logger logger = Logger.getLogger("Minecraft");
  File f = new File(this.getDataFolder() + "/");
  
  public void onEnable()
  {

	  
	  //Adding in the Recipes for the Drugs
	  WeedRec();
	  CokeRec();
	  AcidRec();
	  ShroomsRec();
	  
	  //Registering Commands and Events
	    getCommand("drugs").setExecutor(new KillerCommands(this));
	    getServer().getPluginManager().registerEvents(new Items(this), this);
	  
	    //Checking and Creating a folder for the plugin
	  if(!f.exists()) {
		  f.mkdir();
	  }else {
		  System.out.println("Folder already exists.");
	  }
	  
	 //Checking and loading in plugin config
    getConfig().options().header("Simple Drugs Config.");
    loadConfiguration();
  }
  
  
  //What is inside the config
  public void loadConfiguration()
  {
    String wheat = "Drugs.Toggle.wheat";
    String suagr = "Drugs.Toggle.sugar";
    String paper = "Drugs.Toggle.paper";
    String brown_mushroom = "Drugs.Toggle.brown_mushroom";
    String effect = "Drugs.Effect.length";

    
    getConfig().addDefault(wheat, Boolean.valueOf(true));
    getConfig().addDefault(suagr, Boolean.valueOf(true));
    getConfig().addDefault(paper, Boolean.valueOf(true));
    getConfig().addDefault(brown_mushroom, Boolean.valueOf(true));
    
    getConfig().addDefault(effect, Integer.valueOf(5220));
    
    getConfig().options().copyDefaults(true);
    saveConfig();
  }
  //Weed Recipe
  public void WeedRec() {
	  ItemMeta meta = wheatd.getItemMeta();
	  
	  
	  meta.setDisplayName(ChatColor.GREEN+ "Weed");
	  meta.setLore(Arrays.asList(weed));
	  meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	  meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	  wheatd.setItemMeta(meta);
	  
	  NamespacedKey key = new NamespacedKey(this, "drug_weed");
	  ShapedRecipe recipe = new ShapedRecipe(key, wheatd);
	  
	  recipe.shape(" W ",
			       " W ",
			       " W ");
	  
	  recipe.setIngredient('W', Material.WHEAT);
	  
	  Bukkit.addRecipe(recipe);
  }
  //Coke Recipe
  public void CokeRec() {
	  ItemMeta meta = suagrd.getItemMeta();
	  
	  meta.setLore(Arrays.asList(coke));
	  meta.setDisplayName(ChatColor.GOLD + "Coke");
	  meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	  meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	  suagrd.setItemMeta(meta);
	  
	  NamespacedKey key = new NamespacedKey(this,"drugs_coke");
	  ShapedRecipe recipe = new ShapedRecipe(key, suagrd);
	  
	  recipe.shape(" S ",
			       " S ",
			       " S ");
	  
	  recipe.setIngredient('S', Material.SUGAR);
	  
	  Bukkit.addRecipe(recipe);
	  }
  //Acid Recipe
  public void AcidRec() {
	  ItemMeta meta = paperd.getItemMeta();

	  meta.setLore(Arrays.asList(acid));
	  meta.setDisplayName(ChatColor.BLUE + "Acid");
	  meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	  meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	  paperd.setItemMeta(meta);
	  
	  NamespacedKey key = new NamespacedKey(this, "drugs_acid");
	  ShapedRecipe recipe = new ShapedRecipe(key, paperd);
	  
	  recipe.shape(" P ",
			       " P ",
			       " P ");
	  
	  recipe.setIngredient('P', Material.PAPER);
	  
	  Bukkit.addRecipe(recipe);
  }
  //Shrooms Recipe
  public void ShroomsRec() {
	  ItemMeta meta = paperd.getItemMeta();

	  meta.setLore(Arrays.asList(shrooms));
	  meta.setDisplayName(ChatColor.BLUE + "Shrooms");
	  meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
	  meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	  paperd.setItemMeta(meta);
	  
	  NamespacedKey key = new NamespacedKey(this, "drugs_shrooms");
	  ShapedRecipe recipe = new ShapedRecipe(key, brown_mushroomd);
	  
	  recipe.shape(" B ",
			       " B ",
			       " B ");
	  
	  recipe.setIngredient('B', Material.BROWN_MUSHROOM);
	  
	  Bukkit.addRecipe(recipe);
  }
  


//Nothing
  public void onDisable() {}
  
}
