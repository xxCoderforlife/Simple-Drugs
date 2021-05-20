package me.Coderforlife.Drugs;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static String prefix = ChatColor.WHITE + "[" 
	+ ChatColor.DARK_RED + "SD" + ChatColor.WHITE + "] ";
	
   //
   public File drugsConfigFile;
   public FileConfiguration drugsConfig;
   //
   
   @Override
   public void onEnable() {
	   createCustomConfig();
      //Loading in all the Commands and Listeners
      this.getCommand("drugs").setExecutor(new KillerCommands(this));
      this.getServer().getPluginManager().registerEvents(new Items(this), this);
      
   }
   
   public FileConfiguration getCustomConfig() {
       return drugsConfig;
   }
   
   private void createCustomConfig() {
	   drugsConfigFile = new File(getDataFolder(), "drugs.yml");
	   if(!drugsConfigFile.exists()) {
		   drugsConfigFile.getParentFile().mkdir();
		   saveResource("drugs.yml", true);
	   }
	   
	   drugsConfig = new YamlConfiguration();
	   try {
		   drugsConfig.load(drugsConfigFile);
	   }catch (IOException | InvalidConfigurationException e) {
		   e.printStackTrace();
	   }
   }
   
   
   @Override
   public void onDisable() {
   }
}
