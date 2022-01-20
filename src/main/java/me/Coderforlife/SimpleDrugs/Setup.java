package me.Coderforlife.SimpleDrugs;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.PlaceHolder.DrugPlaceHolders;
import me.Coderforlife.SimpleDrugs.UpdateChecker.Updater;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Setup {

    private static Economy econ;
    private static JavaPlugin plugin;

    public Setup(JavaPlugin plugin) {
        this.plugin = plugin;
        new Metrics(plugin, 13155);
        new Settings().setup();
        loadPlaceHolders();
        checkForUpdate();
        loadVault();

        Drug.loadDrugs();
    }

    private void loadPlaceHolders() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            sendConsoleMessage(Main.prefix + "§aFound PlaceHolderAPI.");
            new DrugPlaceHolders().register();
            sendConsoleMessage(Main.prefix + "§aHooked into PlaceHolderAPI");
        } else {
            sendConsoleMessage(Main.prefix + "§cPlaceHolderAPI.jar was not found. Disabling all PlaceHolderAPI elements!");
        }
    }

    private void checkForUpdate() {
        if(Settings.CheckForUpdate) {
            new Updater(plugin, 9684).checkForUpdate();
        } else {
            sendConsoleMessage(Main.prefix + "§c§oDisabled Update Checking");
        }
    }

    public void loadVault() {
        if(plugin.getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
            if(rsp != null)
                econ = rsp.getProvider();
            sendConsoleMessage(Main.prefix + "§aVault has been found.");
            sendConsoleMessage(Main.prefix + "§aHooked into Vault.");
            return;
        }
        sendConsoleMessage(Main.prefix + "§cVault.jar was not found or you don't have an Economy Plugin");
        sendConsoleMessage(Main.prefix + "§cDisabling all Vault elements");
    }

    private void sendConsoleMessage(String message) {
        Main.plugin.getServer().getConsoleSender().sendMessage(message);
    }
}
