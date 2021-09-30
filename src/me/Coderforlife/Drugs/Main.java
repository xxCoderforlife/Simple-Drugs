package me.Coderforlife.Drugs;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	Drugs D = new Drugs(this);
	Logger log = Logger.getLogger("Minecraft");
	@Override
	public void onEnable() {
	    this.getCommand("drugs").setExecutor(new KillerCommands(this));
	    D.WeedRecipe();
	    D.AcidRecipe();
	    D.CokeRecipe();
	    D.HeroinRecipe();
	    D.PercocetRecipe();
	    D.MollyRecipe();
	    D.CiggyRecipe();
	    try {    
	    	log.info("Loading Molly Class...");
			this.getServer().getPluginManager().registerEvents(new Molly(this), this);
			log.info("Loaded Molly Class Successfully");
			log.info("Loading Acid Class...");
			this.getServer().getPluginManager().registerEvents(new Acid(this), this);
			log.info("Loaded Acid Class Successfully");
			log.info("Loading Weed Class...");
			this.getServer().getPluginManager().registerEvents(new Weed(this), this);
			log.info("Loaded Weed Class Successfully");
			log.info("Loading Heroin Class...");
			this.getServer().getPluginManager().registerEvents(new Heroin(this), this);
			log.info("Loaded Herion Class Successfully");
			log.info("Loading Percocet Class...");
			this.getServer().getPluginManager().registerEvents(new Percocet(this), this);
			log.info("Loaded Percocet Class Successfully");
			log.info("Loading Coke Class...");
			this.getServer().getPluginManager().registerEvents(new Coke(this), this);
			log.info("Load Coke Class Successfully");
			log.info("Loading Ciggy Class...");
			this.getServer().getPluginManager().registerEvents(new Ciggy(this), this);
			log.info("Loaded Ciggy Class Successfully");

	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	   
		
	}
	
	
	@Override
	public void onDisable() {
	}
}
