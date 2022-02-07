package me.Coderforlife.SimpleDrugs;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.Coderforlife.SimpleDrugs.Crafting.CustomCraftingComponentManager;
import me.Coderforlife.SimpleDrugs.Crafting.RecipeManager;
import me.Coderforlife.SimpleDrugs.Druging.DrugManager;
import me.Coderforlife.SimpleDrugs.PlaceHolder.DrugPlaceHolders;
import me.Coderforlife.SimpleDrugs.UpdateChecker.Updater;
import net.milkbowl.vault.economy.Economy;

public class Setup {

    private Economy econ;
    private Main plugin = Main.plugin;

    public Setup() {
        new Metrics(plugin,13155);
        plugin.getSettings().setup();
        loadPlaceHolders();
        checkForUpdate();
        loadVault();

        Main.plugin.setRecipeManager(new RecipeManager());
        
        Main.plugin.setCraftingManager(new CustomCraftingComponentManager());
        Main.plugin.getCraftingManager().registerAllRecipe();
        
        Main.plugin.setDrugManager(new DrugManager());
        Main.plugin.getDrugManager().loadFiles();
    }

    private void loadPlaceHolders() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§aFound PlaceHolderAPI.");
            new DrugPlaceHolders().register();
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§aHooked into PlaceHolderAPI");
        } else {
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§cPlaceHolderAPI.jar was not found. Disabling all PlaceHolderAPI elements!");
        }
    }

    private void checkForUpdate() {
        if(Main.plugin.getSettings().isCheckForUpdate()) {
            new Updater(9684).checkForUpdate();
        } else {
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§c§oDisabled Update Checking");
        }
    }

    public void loadVault() {
        if(plugin.getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
            if(rsp != null)
                econ = rsp.getProvider();
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§aVault has been found.");
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§aHooked into Vault.");
            return;
        }
        sendConsoleMessage(plugin.getMessages().getPrefix() + "§cVault.jar was not found or you don't have an Economy Plugin");
        sendConsoleMessage(plugin.getMessages().getPrefix() + "§cDisabling all Vault elements");
    }

    private void sendConsoleMessage(String message) {
        Main.plugin.getServer().getConsoleSender().sendMessage(message);
    }
}
