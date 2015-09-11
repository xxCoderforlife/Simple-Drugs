package me.Coderforlife.Drugs;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
{
  public FileConfiguration config;
  public File cfile;
  Logger logger = Logger.getLogger("Minecraft");
  
  public void onEnable()
  {
    this.config = getConfig();
    this.cfile = new File(getDataFolder(), "config.yml");
    getCommand("drugs").setExecutor(new KillerCommands(this));
    getServer().getPluginManager().registerEvents(new Events(this), this);
    getConfig().options().header("Config For Drugs Plugin By xxCoderforlife");
    getConfig().options().copyDefaults(true);
    saveConfig();
  }
  
  public void onDisable()
  {
    PluginDescriptionFile pdffile = getDescription();
    this.logger.info(pdffile.getName() + ChatColor.RED + 
      " Has Been Disabled!." + "Server Mod by: " + pdffile.getAuthors());
  }
}
