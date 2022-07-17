package me.Coderforlife.SimpleDrugs;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.Coderforlife.SimpleDrugs.Crafting.RecipeManager;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.CCManager;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.RecipeChecker;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.Brewing.BrewingRecipeListener;
import me.Coderforlife.SimpleDrugs.Druging.DrugManager;
import me.Coderforlife.SimpleDrugs.Druging.DrugRecipeManager;
import me.Coderforlife.SimpleDrugs.Druging.Addiction.AddictionListener;
import me.Coderforlife.SimpleDrugs.Druging.Addiction.AddictionManager;
import me.Coderforlife.SimpleDrugs.Druging.DrugPlants.DrugSeedManager;
import me.Coderforlife.SimpleDrugs.Druging.DrugPlants.PlantItemListener;
import me.Coderforlife.SimpleDrugs.Events.CraftingEvent;
import me.Coderforlife.SimpleDrugs.Events.DrugUseListener;
import me.Coderforlife.SimpleDrugs.Events.InventoryListener;
import me.Coderforlife.SimpleDrugs.Events.PlayerJoin;
import me.Coderforlife.SimpleDrugs.Events.PlayerRespawn;
import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.SDObjectType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.SettingNameListener;
import me.Coderforlife.SimpleDrugs.GUI.Shop.drugShopGUI;
import me.Coderforlife.SimpleDrugs.Util.Messages;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

    public static Main plugin;

    // Mess of Strings
    public String header1 = ChatColor.WHITE + "" + ChatColor.BOLD + "============" + ChatColor.DARK_RED + ""
            + ChatColor.BOLD + "[Simple-Drugs]" + ChatColor.WHITE + "" + ChatColor.BOLD + "============";
    private NamespacedKey drugMain;
    private NamespacedKey drugPlantedOn;
    private NamespacedKey drugKey;
    private NamespacedKey drugHarvestAmount;
    private NamespacedKey drugSeedKey;
    private NamespacedKey isDrugItem;
    private NamespacedKey isCraftingComponent;
    private NamespacedKey craftingComponentName;

    private DrugManager drugManager;
    private DrugRecipeManager drugRecipeManager;
    private DrugSeedManager seedManager;
    private CCManager craftingManager;
    private Settings settings;
    private Messages messages;
    private RecipeManager recipeManager;
    private AddictionManager addictionManager;
    private Economy econ;

    private Map<UUID, SDObjectType> creatingName = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;

        drugMain = new NamespacedKey(plugin, "SimpleDrugs-DrugMain");
        drugPlantedOn = new NamespacedKey(plugin, "SimpleDrugs-DrugPlantedOn");
        drugKey = new NamespacedKey(plugin, "SimpleDrugs-DrugName");
        drugHarvestAmount = new NamespacedKey(plugin, "SimpleDrugs-HarvestAmount");
        drugSeedKey = new NamespacedKey(plugin, "SimpleDrugs-DrugSeed");
        isDrugItem = new NamespacedKey(plugin, "SimpleDrugs-IsDrugItem");
        isCraftingComponent = new NamespacedKey(plugin, "SimpleDrugs-IsCraftingComponent");
        craftingComponentName = new NamespacedKey(plugin, "SimpleDrugs-CraftingComponentName");

        settings = new Settings();
        messages = new Messages();
        addictionManager = new AddictionManager();

        sendConsoleMessage(header1);
        sendConsoleMessage(getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&',
                "&a&oStarting up &f&o&lSimple-&4&l&oDrugs &a&lVersion: &f" + getDescription().getVersion()));
        for (Player p : Bukkit.getOnlinePlayers()) {
            addictionManager.addictionMap().put(p.getUniqueId(), 0.0);
        }
        registerListeners();
        loadVault();
        new Setup();
        /**
         * TODO: Furance Recipe
         * ItemStack result = new ItemStack(Material.DIAMOND);
         * ItemStack ing = new ItemStack(Material.COAL);
         * ItemStack fuel = new ItemStack(Material.GOLD_INGOT);
         * ItemStack input =
         * craftingManager.getByName("WeedComponentNew".toUpperCase()).getStack();
         * BrewingRecipe br = new BrewingRecipe("TestThing", result, ing, fuel, input,
         * 25, 25, 100);
         * br.registerRecipe();
         */

        sendConsoleMessage(getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&',
                "&f&o&lSimple-&4&l&oDrugs &a&lVersion: &f" + getDescription().getVersion() + " &ahas been enabled!"));
    }

    @Override
    public void onDisable() {
        getSettings().save();
    }

    private void sendConsoleMessage(String message) {
        this.getServer().getConsoleSender().sendMessage(message);
    }

    public Map<UUID, SDObjectType> getCreatingName() {
        return creatingName;
    }

    public RecipeManager getRecipeManager() {
        return recipeManager;
    }

    public void setRecipeManager(RecipeManager rm) {
        recipeManager = rm;
    }

    public DrugRecipeManager getDrugRecipeManager() {
        return drugRecipeManager;
    }

    public void setDrugRecipeManager(DrugRecipeManager drm) {
        drugRecipeManager = drm;
    }

    public DrugSeedManager getDrugSeedManager() {
        return seedManager;
    }

    public void setDrugSeedManager(DrugSeedManager dsm) {
        seedManager = dsm;
    }

    public DrugManager getDrugManager() {
        return drugManager;
    }

    public void setDrugManager(DrugManager dm) {
        drugManager = dm;
    }

    public CCManager getCraftingManager() {
        return craftingManager;
    }

    public void setCraftingManager(CCManager cccm) {
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

    public NamespacedKey isDrugItem() {
        return isDrugItem;
    }

    public NamespacedKey isCraftingComponent() {
        return isCraftingComponent;
    }

    public NamespacedKey getCraftingComponentName() {
        return craftingComponentName;
    }

    public Messages getMessages() {
        return messages;
    }

    public AddictionManager getAddictionManager() {
        return addictionManager;
    }

    public Economy getEconomy() {
        return econ;
    }

    private void registerListeners() {
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
        this.getServer().getPluginManager().registerEvents(new drugShopGUI(), this);

        this.getServer().getPluginManager().registerEvents(new SettingNameListener(), this);
        this.getCommand("drugs").setExecutor(new Commands());
        this.getCommand("drugs").setTabCompleter(new TabCommands());
    }

    public void loadVault() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager()
                    .getRegistration(Economy.class);
            if (rsp != null)
                econ = rsp.getProvider();
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§aVault has been found.");
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§aHooked into Vault.");
            return;
        }
        sendConsoleMessage(
                plugin.getMessages().getPrefix() + "§cVault.jar was not found or you don't have an Economy Plugin");
        sendConsoleMessage(plugin.getMessages().getPrefix() + "§cDisabling all Vault elements");
    }
}
