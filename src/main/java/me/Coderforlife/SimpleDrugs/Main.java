package me.Coderforlife.SimpleDrugs;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import me.Coderforlife.SimpleDrugs.DrugPlants.PlantItemListener;
import me.Coderforlife.SimpleDrugs.Druging.BagOfDrugs;
import me.Coderforlife.SimpleDrugs.Druging.DrugManager;
import me.Coderforlife.SimpleDrugs.Events.CraftingEvent;
import me.Coderforlife.SimpleDrugs.Events.DrugUseListener;
import me.Coderforlife.SimpleDrugs.Events.InventoryListener;
import me.Coderforlife.SimpleDrugs.Events.PlayerJoin;
import me.Coderforlife.SimpleDrugs.Events.PlayerRespawn;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

    public static Main plugin;

    //Mess of Strings
    public String header1 = ChatColor.WHITE + "" + ChatColor.BOLD + "============" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "[Simple-Drugs]" + ChatColor.WHITE + "" + ChatColor.BOLD + "============";
    public static String prefix = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "SD" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET;

    private NamespacedKey drugMain;
    private NamespacedKey drugPlantedOn;
    private NamespacedKey drugKey;
    private NamespacedKey drugHarvestAmount;
    private NamespacedKey drugSeedKey;
    
    private DrugManager drugManager;
    private Settings settings;
    
    @Override
    public void onEnable() {
        plugin = this;
        
        drugMain = new NamespacedKey(plugin, "SimpleDrugs-DrugMain");
        drugPlantedOn = new NamespacedKey(plugin, "SimpleDrugs-DrugPlantedOn");
        drugKey = new NamespacedKey(plugin, "SimpleDrugs-DrugName");
        drugHarvestAmount = new NamespacedKey(plugin, "SimpleDrugs-HarvestAmount");
        drugSeedKey = new NamespacedKey(plugin, "SimpleDrugs-DrugSeed");
        
        drugManager = new DrugManager();
        settings = new Settings();
        
        sendConsoleMessage(header1);
        sendConsoleMessage("§aLoading Plugin...");

        new Setup(this);

        this.getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        this.getServer().getPluginManager().registerEvents(new BagOfDrugs(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new DrugUseListener(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        this.getServer().getPluginManager().registerEvents(new CraftingEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlantItemListener(), this);
        this.getCommand("drugs").setExecutor(new Commands(this));
        this.getCommand("drugs").setTabCompleter(new TabCommands());

        sendConsoleMessage("§aLoaded without Errors. Plugin is ready to Use :D");
    }

    @Override
    public void onDisable() {
        new Settings().save();
        new DrugManager().saveallDrugs();
    }

    private void sendConsoleMessage(String message) {
        this.getServer().getConsoleSender().sendMessage(message);
    }
    
    public DrugManager getDrugManager() {
    	return drugManager;
    }
    
    public Settings getSettings() {
    	return settings;
    }
    
    public NamespacedKey getDrugMain() {
    	return drugMain;
    }
    
    public NamespacedKey getDrugKey() {
    	return drugKey;
    }
    
    public NamespacedKey getDrugPlantedOn() {
    	return drugPlantedOn;
    }
    
    public NamespacedKey getDrugHarvestAmount() {
    	return drugHarvestAmount;
    }
    
    public NamespacedKey getDrugSeedKey() {
    	return drugSeedKey;
    }
    
}
