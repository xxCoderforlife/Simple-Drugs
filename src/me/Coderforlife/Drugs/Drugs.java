package me.Coderforlife.Drugs;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Drugs
  extends JavaPlugin
{
  Logger logger = Logger.getLogger("Minecraft");
  
  public void onEnable()
  {
    getCommand("drugs").setExecutor(new Cmds(this));
    PluginDescriptionFile pdffile = getDescription();
    this.logger.info(pdffile.getName() + ChatColor.RED + 
      " Has Been Enabled." + "Version: " + pdffile.getVersion() + " Website: " + pdffile.getWebsite());
    getServer().getPluginManager().registerEvents(new Events(this), this);
    getConfig().options().header("Config For Drugs Plugin By xxCoderforlife");
    getConfig().options().copyDefaults(true);
    saveConfig();
    if (getConfig().getBoolean("Drugs.Enabled.update")) {
    }
  }
  
  public void onDisable()
  {
    PluginDescriptionFile pdffile = getDescription();
    this.logger.info(pdffile.getName() + ChatColor.RED + 
      " Has Been Disabled!." + "Server Mod by: " + pdffile.getAuthors());
  }
}
