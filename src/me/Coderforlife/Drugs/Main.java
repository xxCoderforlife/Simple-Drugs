package me.Coderforlife.Drugs;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;



public class Main
  extends JavaPlugin
{
  Logger logger = Logger.getLogger("Minecraft");
 public final ItemStack stack = new ItemStack(Material.WHEAT, 1);
  
  public void onEnable()
  {
    getCommand("drugs").setExecutor(new KillerCommands(this));
    getServer().getPluginManager().registerEvents(new Events(this), this);
    getConfig().options().header("Simple Drugs Config.");
    loadConfiguration();
  }
  
   public void loadConfiguration(){
	   String wheat = "Drugs.Toggle.wheat";
	   String suagr = "Drugs.Toggle.sugar";
	   String paper = "Drugs.Toggle.paper";
	   String gunp  = "Drugs.Toggle.gunpowder";
	   String bone  = "Drugs.Toggle.bone";
	   String beet = "Drugs.Toggle.beet";
	   String cactus = "Drugs.Toggle.cactus";
	   String announce = "Drugs.Toggle.announce";
	   String wart = "Drugs.Toggle.wart";
	   //String nether = "Drugs.Toggle.nether";
	   String effect = "Drugs.Effect.length";
	   String console = "Drugs.Console.logs";
	   /*String remove =  "Drugs.Effect.Clear";
	   String sound = "Drugs.Effect.Sound";*/
	   getConfig().addDefault(wheat, true);
	   getConfig().addDefault(suagr, true);
	   getConfig().addDefault(paper, true);
	   getConfig().addDefault(gunp, true);
	   getConfig().addDefault(bone, true);
	   getConfig().addDefault(beet, true);
	   getConfig().addDefault(cactus, true);
	   getConfig().addDefault(wart, true);
	   //getConfig().addDefault(mushrooms, true);
	   getConfig().addDefault(effect, 5220);
	   getConfig().addDefault(console, true);
	   getConfig().addDefault(announce, true);
	  /* getConfig().addDefault(remove, true);
	   * getConfig().addDefault(sound, true)*/
       getConfig().options().copyDefaults(true);
	   saveConfig();
   }
  public void onDisable()
  {
  }
  
  public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args){
	  if(command.getName().equalsIgnoreCase("dreload")){
		  Player p = (Player) sender;
		  if(p.hasPermission("drugs.reload")){
		  reloadConfig();
		  }
	  }
	return true;
	  
  }
}
