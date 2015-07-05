package me.Coderforlife.Drugs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import me.Coderforlife.Drugs.Metrics;
public class Drugs
  extends JavaPlugin
{
	public FileConfiguration config;
	public File cfile;
  Logger logger = Logger.getLogger("Minecraft");
  
  public void onEnable()
  {
	  config = getConfig();
	  cfile = new File(getDataFolder(), "config.yml");
	    try {
	    	System.out.println("Attemping to upload data to Metrics :3");
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	    } catch (IOException e) {
	    	System.out.println("Mettics failed to upload data :c");
	    }
    getCommand("drugs").setExecutor(new Cmds(this));
    PluginDescriptionFile pdffile = getDescription();
    this.logger.info(pdffile.getName() + ChatColor.RED + 
      " Has Been Enabled." + "Version: " + pdffile.getVersion() + " Website: " + pdffile.getWebsite());
    getServer().getPluginManager().registerEvents(new Events(this), this);
    getConfig().options().header("Config For Drugs Plugin By xxCoderforlife");
    getConfig().options().copyDefaults(true);
    saveConfig();
  }
  
  public void onDisable(){
    PluginDescriptionFile pdffile = getDescription();
    this.logger.info(pdffile.getName() + ChatColor.RED + 
      " Has Been Disabled!." + "Server Mod by: " + pdffile.getAuthors());
  }
  public boolean onCommand(CommandSender sender, Command cmd, String Commandlabel, String[] args){
	  if(cmd.getName().equalsIgnoreCase("dreload")){
		  Player player = (Player) sender;
		  player.sendMessage(ChatColor.RED + "Reloading Config...");	
		this.reloadConfig();
		player.sendMessage(ChatColor.RED + "Reloaded Config.");
	  }
	return true;
  }
	
  }
