package me.Coderforlife.SimpleDrugs;

import me.Coderforlife.SimpleDrugs.Druging.BagOfDrugs;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Events.DrugUseListener;
import me.Coderforlife.SimpleDrugs.Events.InventoryClickListener;
import me.Coderforlife.SimpleDrugs.Events.PlayerJoin;
import me.Coderforlife.SimpleDrugs.Events.PlayerRespawn;
import me.Coderforlife.SimpleDrugs.PlaceHolder.DrugPlaceHolders;
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
    private static Economy econ;
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
        plugin = this;
        new Metrics(this, 13155);

        sendConsoleMessage(header1);
        sendConsoleMessage("§aLoading Plugin...");

        Drug.loadDrugs();
        Settings.setup();
        RegisterEvents();
        loadPlaceHolders();
        loadVault();
        checkForUpdate();

        sendConsoleMessage("§aLoaded without Errors. Plugin is ready to Use :D");
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
        this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        this.getCommand("drugs").setExecutor(new KillerCommands(this));
        this.getCommand("drugs").setTabCompleter(new TabCommands(this));
    }

    public void loadPlaceHolders() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            sendConsoleMessage(prefix + "§aFound PlaceHolderAPI.");
            new DrugPlaceHolders(this).register();
            sendConsoleMessage(prefix + "§aHooked into PlaceHolderAPI");
        } else {
            sendConsoleMessage(prefix + "§cPlaceHolderAPI.jar was not found. Disabling all PlaceHolderAPI elements!");
        }
    }

    public void checkForUpdate() {
        if(Settings.CheckForUpdate) {
            new Updater(this, 9684).checkForUpdate();
        } else {
            sendConsoleMessage(Main.prefix + "§c§oDisabled Update Checking");
        }
    }

    public void loadVault() {
        if(getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
            if(rsp != null)
                econ = rsp.getProvider();
            sendConsoleMessage(prefix + "§aVault has been found.");
            sendConsoleMessage(prefix + "§aHooked into Vault.");
            return;
        }
        sendConsoleMessage(prefix + "§cVault.jar was not found or you don't have an Economy Plugin");
        sendConsoleMessage(prefix + "§cDisabling all Vault elements");
    }

    private void sendConsoleMessage(String message) {
        this.getServer().getConsoleSender().sendMessage(message);
    }
}
