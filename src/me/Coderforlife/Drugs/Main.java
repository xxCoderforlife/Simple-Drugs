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

public class Main extends JavaPlugin {

   public static ItemStack RawCoke = new ItemStack(Material.SUGAR_CANE);
   public static ItemStack wheatd = new ItemStack(Material.WHEAT);
   public static ItemStack suagrd = new ItemStack(Material.SUGAR);
   public static ItemStack paperd = new ItemStack(Material.PAPER);
   public static ItemStack WeedBuk = new ItemStack(Material.WATER_BUCKET);
   public static String weedcom = "";
   public static String cokecom = "";
   public static String acidcom = "";
   public static String combinedweed = "Combine 3 of these to make the WaterBuk";
   public static String coke = "Coke: Night Vison, Fire Res, FastDig";
   public static String acid = "Acid: Night Vison, Jump, Heal";
   Logger logger = Logger.getLogger("Minecraft");
   File f = new File(this.getDataFolder() + "/");

   static {
   }

   public void onEnable() {
	   //Loading in the custom recipes
	  this.WeedRec();
      this.CokeRec();
      this.AcidRec();
      //Loading in all the Commands and Listeners
      this.getCommand("drugs").setExecutor(new KillerCommands(this));
      this.getServer().getPluginManager().registerEvents(new Items(this), this);
      this.getServer().getPluginManager().registerEvents(new CraftingCheck(this), this);
     //Creating a DIR for the config.yml
      if (!this.f.exists()) {
         this.f.mkdir();// Creates the Folder
      } else {
         System.out.println("Folder already exists.");
      }

      this.getConfig().options().header("Simple Drugs Config.");
      this.loadConfiguration();
   }

   public void loadConfiguration() {
      String wheat = "Drugs.Weed.Enable";
      String suagr = "Drugs.Coke.Enable";
      String paper = "Drugs.Acid.Enable";
      String effectCoke = "Drugs.Coke.EffectLength";
      String effectWeed = "Drugs.Weed.EffectLength";
      String effectAcid = "Drugs.Acid.EffectLength";
      String cTimeWeed = "Drugs.Weed.CookingTime";
      String cTimeCoke = "Drugs.Coke.CookingTime";
      String cTimeAcid = "Drugs.Acid.CookingTime";
      
      //Add Defaults to the Config
      this.getConfig().addDefault(wheat, true);
      this.getConfig().addDefault(suagr, true);
      this.getConfig().addDefault(paper, true);
      this.getConfig().addDefault(cTimeAcid, 140);
      this.getConfig().addDefault(cTimeCoke, 140);
      this.getConfig().addDefault(cTimeWeed, 140);
      this.getConfig().addDefault(effectWeed, 5220);
      this.getConfig().addDefault(effectCoke, 5220);
      this.getConfig().addDefault(effectAcid, 5220);
      this.getConfig().options().copyDefaults(true);
      this.saveConfig();
   }
   

   public void WeedRec() {
      ItemMeta meta = wheatd.getItemMeta();
      meta.setDisplayName(org.bukkit.ChatColor.GREEN + "Combined Weed");
      meta.setLore(Arrays.asList(combinedweed));
      meta.addEnchant(Enchantment.DAMAGE_ALL, CraftingCheck.valuekey2, true);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      wheatd.setItemMeta(meta);
      NamespacedKey key = new NamespacedKey(this, "drug_weed");
      ShapedRecipe recipe = new ShapedRecipe(key, wheatd);
      recipe.shape(new String[]
    		  {       " W ",
    				  " W ",
    				  " W "});
      recipe.setIngredient('W', Material.WHEAT);
      Bukkit.addRecipe(recipe);
   }
   public void CokeRec() {
      ItemMeta meta = suagrd.getItemMeta();
      meta.setLore(Arrays.asList(coke));
      meta.setDisplayName(org.bukkit.ChatColor.GOLD + "Coke");
      meta.addEnchant(Enchantment.DAMAGE_ALL, CraftingCheck.valuekey, true);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      suagrd.setItemMeta(meta);
      NamespacedKey key = new NamespacedKey(this, "drugs_coke");
      ShapedRecipe recipe = new ShapedRecipe(key, suagrd);
      recipe.shape(new String[]
    		  {         
    				    " S "
    				  , " S "
    				  , " S "
    	});
      recipe.setIngredient('S', Material.SUGAR);
      Bukkit.addRecipe(recipe);
   }
   public void AcidRec() {
      ItemMeta meta = paperd.getItemMeta();
      meta.setLore(Arrays.asList(acid));
      meta.setDisplayName(org.bukkit.ChatColor.AQUA + "Acid");
      meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      paperd.setItemMeta(meta);
      NamespacedKey key = new NamespacedKey(this, "drugs_acid");
      ShapedRecipe recipe = new ShapedRecipe(key, paperd);
      recipe.shape(new String[]{" P ", " P ", " P "});
      recipe.setIngredient('P', Material.PAPER);
      Bukkit.addRecipe(recipe);
   }
  
   public void onDisable() {
   }
}
