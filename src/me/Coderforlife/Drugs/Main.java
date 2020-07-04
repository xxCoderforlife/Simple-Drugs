package me.Coderforlife.Drugs;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;
import net.md_5.bungee.api.ChatColor;
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
   public static ItemStack wheatd;
   public static ItemStack suagrd;
   public static ItemStack paperd;
   public static String weed;
   public static String coke;
   public static String acid;
   Logger logger = Logger.getLogger("Minecraft");
   File f = new File(this.getDataFolder() + "/");

   static {
      wheatd = new ItemStack(Material.WHEAT);
      suagrd = new ItemStack(Material.SUGAR);
      paperd = new ItemStack(Material.PAPER);
      weed = "Weed: Slowness, Hunger, Luck";
      coke = "Coke: Night Vison, Fire Res, FastDig";
      acid = "Acid: Night Vison, Jump, Heal";
   }

   public void onEnable() {
      this.WeedRec();
      this.CokeRec();
      this.AcidRec();
      this.getCommand("drugs").setExecutor(new KillerCommands(this));
      this.getServer().getPluginManager().registerEvents(new Items(this), this);
      if (!this.f.exists()) {
         this.f.mkdir();
      } else {
         System.out.println("Folder already exists.");
      }

      this.getConfig().options().header("Simple Drugs Config.");
      this.loadConfiguration();
   }

   public void loadConfiguration() {
      String wheat = "Drugs.Toggle.wheat";
      String suagr = "Drugs.Toggle.sugar";
      String paper = "Drugs.Toggle.paper";
      String effect = "Drugs.Effect.length";
      this.getConfig().addDefault(wheat, true);
      this.getConfig().addDefault(suagr, true);
      this.getConfig().addDefault(paper, true);
      this.getConfig().addDefault(effect, 5220);
      this.getConfig().options().copyDefaults(true);
      this.saveConfig();
   }

   public void WeedRec() {
      ItemMeta meta = wheatd.getItemMeta();
      meta.setDisplayName(ChatColor.GREEN + "Weed");
      meta.setLore(Arrays.asList(weed));
      meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      wheatd.setItemMeta(meta);
      NamespacedKey key = new NamespacedKey(this, "drug_weed");
      ShapedRecipe recipe = new ShapedRecipe(key, wheatd);
      recipe.shape(new String[]{" W ", " W ", " W "});
      recipe.setIngredient('W', Material.WHEAT);
      Bukkit.addRecipe(recipe);
   }

   public void CokeRec() {
      ItemMeta meta = suagrd.getItemMeta();
      meta.setLore(Arrays.asList(coke));
      meta.setDisplayName(ChatColor.GOLD + "Coke");
      meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      suagrd.setItemMeta(meta);
      NamespacedKey key = new NamespacedKey(this, "drugs_coke");
      ShapedRecipe recipe = new ShapedRecipe(key, suagrd);
      recipe.shape(new String[]{" S ", " S ", " S "});
      recipe.setIngredient('S', Material.SUGAR);
      Bukkit.addRecipe(recipe);
   }

   public void AcidRec() {
      ItemMeta meta = paperd.getItemMeta();
      meta.setLore(Arrays.asList(acid));
      meta.setDisplayName(ChatColor.AQUA + "Acid");
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
