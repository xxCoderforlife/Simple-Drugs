package me.Coderforlife.Drugs;

import me.Coderforlife.Drugs.Events.DrugUseListener;
import me.Coderforlife.Drugs.Events.PlayerJoin;
import me.Coderforlife.Drugs.Events.PlayerRespawn;
import me.Coderforlife.Drugs.PlaceHolder.DrugPlaceHolders;
import me.Coderforlife.Drugs.Settings.SettingsClickEvent;
import me.Coderforlife.Drugs.UpdateChecker.Updater;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Main extends JavaPlugin {


    //Vault Economy
    private static Economy econ = null;
    public static Plugin plugin;

    //Config File
    public File drugsConfigFile;
    public FileConfiguration drugsConfig;

    //Instance of Drugs
    Drugs D;

    //Good Ole Minecraft Logger
    Logger log = Logger.getLogger("Minecraft");

    //Mess of Strings
    public String header1 = ChatColor.WHITE + "" + ChatColor.BOLD + "============" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "[Simple-Drugs]" + ChatColor.WHITE + "" + ChatColor.BOLD + "============";

    public static String prefix = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "SD" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET;
    public static String stack = ChatColor.RED + "" + ChatColor.BOLD + "Do Not Use It In A Stack.";

    public static String bagofdrugs = "Drugs.BagOfDrugs";

    //Check for Update
    @SuppressWarnings("unused")
    @Override
    public void onEnable() {
        int pluginId = 13155;
        Metrics metrics = new Metrics(this, pluginId);

        getServer().getConsoleSender().sendMessage(header1);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loading all Class files and Handlers...");
        createCustomConfig();

        plugin = this;

        D = new Drugs(this);
        for(Drug drug : D.getAllDrugs()) {
            boolean isCraftingDisabled = drugsConfig.getBoolean("Drugs." + drug.getName() + ".DisableCrafting");

            if(isCraftingDisabled) {
                getServer().getConsoleSender().sendMessage(ChatColor.WHITE + drug.getName() + ":" + ChatColor.RED + " Disabled");
            } else {
                D.enableDrug(drug);
                getServer().getConsoleSender().sendMessage(ChatColor.WHITE + drug.getName() + ":" + ChatColor.GREEN + " Enabled");
            }
        }
        try {
            RegisterEvents();
            loadPlaceHolders();
            loadVault();
            CheckforUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loaded without Errors.");
    }

    @Override
    public void onDisable() {
        D.getAllDrugs().clear();
        try {
            getCustomConfig().save(drugsConfigFile);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void createCustomConfig() {
        drugsConfigFile = new File(getDataFolder(), "config.yml");
        if(!drugsConfigFile.exists()) {
            drugsConfigFile.getParentFile().mkdir();
            saveResource("config.yml", false);
        }
        drugsConfig = new YamlConfiguration();
        try {
            drugsConfig.load(drugsConfigFile);
        } catch(IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getCustomConfig() {
        return drugsConfig;
    }

    public void RegisterEvents() {
        this.getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
        this.getServer().getPluginManager().registerEvents(new BagOfDrugs(this, D), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        this.getServer().getPluginManager().registerEvents(new DrugUseListener(this, D), this);
        this.getServer().getPluginManager().registerEvents(new SettingsClickEvent(this), this);
        this.getCommand("drugs").setExecutor(new KillerCommands(this, D));
        this.getCommand("drugs").setTabCompleter(new TabCommands(this, D));
    }

    public void loadPlaceHolders() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&aFound PlaceHolderAPI."));
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&aHooked into PlaceHolderAPI"));
            new DrugPlaceHolders(this, D).register();
        } else {
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cPlaceHolderAPI.jar was not found."));
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cDisabling all PlaceHolderAPI elements"));
        }
    }

    public void CheckforUpdate() {
        if(this.drugsConfig.getBoolean("Drugs.CheckForUpdate") == true) {
            new Updater(this, 9684).checkForUpdate();
        } else {
            Bukkit.getConsoleSender().sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', "&c&oDisabled Update Checking"));
        }
    }

    public void loadVault() {
        if(!setupEconomy()) {
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cVault.jar was not found or you don't have an Economy Plugin"));
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cDisabling all Vault elements"));
        } else {
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&aVault has been found."));
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&aHooked into Vault."));
        }
    }

    private boolean setupEconomy() {
        if(getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
