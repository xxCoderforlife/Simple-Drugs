package me.Coderforlife.SimpleDrugs;

import me.Coderforlife.SimpleDrugs.Druging.BagOfDrugs;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Events.DrugUseListener;
import me.Coderforlife.SimpleDrugs.Events.PlayerJoin;
import me.Coderforlife.SimpleDrugs.Events.PlayerRespawn;
import me.Coderforlife.SimpleDrugs.PlaceHolder.DrugPlaceHolders;
import me.Coderforlife.SimpleDrugs.Settings.Settings;
import me.Coderforlife.SimpleDrugs.Settings.SettingsClickEvent;
import me.Coderforlife.SimpleDrugs.UpdateChecker.Updater;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class Main extends JavaPlugin {


    //Vault Economy
    private static Economy econ = null;
    public static Plugin plugin;

    //Config File
    public File drugsConfigFile;
    public FileConfiguration drugsConfig;

    //Good Ole Minecraft Logger
    Logger log = Logger.getLogger("Minecraft");

    //Mess of Strings
    public String header1 = ChatColor.WHITE + "" + ChatColor.BOLD + "============" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "[Simple-Drugs]" + ChatColor.WHITE + "" + ChatColor.BOLD + "============";

    public static String prefix = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "SD" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET;
    public static String stack = ChatColor.RED + "" + ChatColor.BOLD + "Do Not Use It In A Stack.";

    public static String bagofdrugs = "Drugs.BagOfDrugs";

    @Override
    public void onEnable() {
        new Metrics(this, 13155);

        getServer().getConsoleSender().sendMessage(header1);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loading all Class files and Handlers...");

        plugin = this;
        Drug.loadDrugs();
        new Settings();

        RegisterEvents();
        loadPlaceHolders();
        loadVault();
        CheckforUpdate();

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loaded without Errors.");
    }

    @Override
    public void onDisable() {
        Settings.save();
    }

    public void RegisterEvents() {
        this.getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
        this.getServer().getPluginManager().registerEvents(new BagOfDrugs(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        this.getServer().getPluginManager().registerEvents(new DrugUseListener(), this);
        this.getServer().getPluginManager().registerEvents(new SettingsClickEvent(this), this);
        this.getCommand("drugs").setExecutor(new KillerCommands(this));
        this.getCommand("drugs").setTabCompleter(new TabCommands(this));
    }

    public void loadPlaceHolders() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&aFound PlaceHolderAPI."));
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&aHooked into PlaceHolderAPI"));
            new DrugPlaceHolders(this).register();
        } else {
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cPlaceHolderAPI.jar was not found."));
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cDisabling all PlaceHolderAPI elements"));
        }
    }

    public void CheckforUpdate() {
        if(Settings.CheckForUpdate) {
            new Updater(this, 9684).checkForUpdate();
        } else {
            Bukkit.getConsoleSender().sendMessage(Main.prefix + "§c§oDisabled Update Checking");
        }
    }

    public void loadVault() {
        if(!setupEconomy()) {
            Bukkit.getConsoleSender().sendMessage(prefix + "§cVault.jar was not found or you don't have an Economy Plugin");
            Bukkit.getConsoleSender().sendMessage(prefix + "§cDisabling all Vault elements");
        } else {
            Bukkit.getConsoleSender().sendMessage(prefix + "§aVault has been found.");
            Bukkit.getConsoleSender().sendMessage(prefix + "§aHooked into Vault.");
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
