package me.Coderforlife.SimpleDrugs;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import me.Coderforlife.SimpleDrugs.Crafting.CustomCraftingComponentManager;
import me.Coderforlife.SimpleDrugs.Crafting.RecipeManager;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.RecipeChecker;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.Brewing.BrewingRecipeListener;
import me.Coderforlife.SimpleDrugs.DrugPlants.PlantItemListener;
import me.Coderforlife.SimpleDrugs.Druging.DrugManager;
import me.Coderforlife.SimpleDrugs.Druging.Addiction.AddictionListener;
import me.Coderforlife.SimpleDrugs.Druging.Addiction.AddictionManager;
import me.Coderforlife.SimpleDrugs.Events.CraftingEvent;
import me.Coderforlife.SimpleDrugs.Events.DrugUseListener;
import me.Coderforlife.SimpleDrugs.Events.InventoryListener;
import me.Coderforlife.SimpleDrugs.Events.PlayerJoin;
import me.Coderforlife.SimpleDrugs.Events.PlayerRespawn;
import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;
import me.Coderforlife.SimpleDrugs.Util.Messages;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

    public static Main plugin;

    //Mess of Strings
    public String header1 = ChatColor.WHITE + "" + ChatColor.BOLD + "============" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "[Simple-Drugs]" + ChatColor.WHITE + "" + ChatColor.BOLD + "============";
    private NamespacedKey drugMain;
    private NamespacedKey drugPlantedOn;
    private NamespacedKey drugKey;
    private NamespacedKey drugHarvestAmount;
    private NamespacedKey drugSeedKey;
    
    private DrugManager drugManager;
    private CustomCraftingComponentManager craftingManager;
    private Settings settings;
    private Messages messages;
    private RecipeManager recipeManager;
    private AddictionManager addictionManager;
    
    @Override
    public void onEnable() {
        plugin = this;
        
        drugMain = new NamespacedKey(plugin, "SimpleDrugs-DrugMain");
        drugPlantedOn = new NamespacedKey(plugin, "SimpleDrugs-DrugPlantedOn");
        drugKey = new NamespacedKey(plugin, "SimpleDrugs-DrugName");
        drugHarvestAmount = new NamespacedKey(plugin, "SimpleDrugs-HarvestAmount");
        drugSeedKey = new NamespacedKey(plugin, "SimpleDrugs-DrugSeed");
        
        settings = new Settings();
        messages = new Messages();
        addictionManager = new AddictionManager();
        registerListeners();
        
        sendConsoleMessage(header1);
        sendConsoleMessage("§aLoading Plugin...");

        new Setup();
/** TODO
        ItemStack result = new ItemStack(Material.DIAMOND);
        ItemStack ing = new ItemStack(Material.COAL);
        ItemStack fuel = new ItemStack(Material.GOLD_INGOT);
        ItemStack input = craftingManager.getByName("WeedComponentNew".toUpperCase()).getStack();
        BrewingRecipe br = new BrewingRecipe("TestThing", result, ing, fuel, input, 25, 25, 100);
        br.registerRecipe();
*/

        sendConsoleMessage("§aLoaded without Errors. Plugin is ready to Use :D");
    }

    @Override
    public void onDisable() {
        getSettings().save();
    }

    private void sendConsoleMessage(String message) {
        this.getServer().getConsoleSender().sendMessage(message);
    }
    
    public RecipeManager getRecipeManager() {
    	return recipeManager;
    }
    
    public void setRecipeManager(RecipeManager rm) {
    	recipeManager = rm;
    }
    
    public DrugManager getDrugManager() {
    	return drugManager;
    }
    
    public void setDrugManager(DrugManager dm) {
    	drugManager = dm;
    }
    
    public CustomCraftingComponentManager getCraftingManager() {
    	return craftingManager;
    }
    
    public void setCraftingManager(CustomCraftingComponentManager cccm) {
    	craftingManager = cccm;
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
    
    public Messages getMessages(){
        return messages;
    }

    public AddictionManager gAddictionManager(){
        return addictionManager;
    }

    private void registerListeners(){
        this.getServer().getPluginManager().registerEvents(new RecipeChecker(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        this.getServer().getPluginManager().registerEvents(new BagOfDrugsGUI(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new DrugUseListener(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        this.getServer().getPluginManager().registerEvents(new AddictionListener(), this);
        this.getServer().getPluginManager().registerEvents(new CraftingEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlantItemListener(), this);
        this.getServer().getPluginManager().registerEvents(new BrewingRecipeListener(), this);
        this.getCommand("drugs").setExecutor(new Commands());
        this.getCommand("drugs").setTabCompleter(new TabCommands());
    }
}
