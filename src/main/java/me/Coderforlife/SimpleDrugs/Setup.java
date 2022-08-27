package me.Coderforlife.SimpleDrugs;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import me.Coderforlife.SimpleDrugs.Crafting.RecipeManager;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.CCManager;
import me.Coderforlife.SimpleDrugs.Druging.DrugManager;
import me.Coderforlife.SimpleDrugs.Druging.DrugRecipeManager;
import me.Coderforlife.SimpleDrugs.Druging.DrugPlants.DrugSeedManager;
import me.Coderforlife.SimpleDrugs.PlaceHolder.DrugPlaceHolders;
import me.Coderforlife.SimpleDrugs.UpdateChecker.Updater;
import me.Coderforlife.SimpleDrugs.Util.Messages;
import net.milkbowl.vault.economy.Economy;

public class Setup {

    private Economy econ;
    private Main plugin = Main.plugin;
    private Messages m = new Messages();

    public Setup() {
        new Metrics(plugin, 13155);
        plugin.getSettings().setup();
        loadPlaceHolders();
        checkForUpdate();
        m.checkForMessagesFile();

        Main.plugin.setRecipeManager(new RecipeManager());
        Main.plugin.setCraftingManager(new CCManager());
        Main.plugin.getCraftingManager().loadFiles();
        Main.plugin.setDrugManager(new DrugManager());
        Main.plugin.getDrugManager().loadFiles();
        Main.plugin.setDrugSeedManager(new DrugSeedManager());
        Main.plugin.getDrugSeedManager().loadFiles();
        Main.plugin.setDrugRecipeManager(new DrugRecipeManager());
        Main.plugin.getDrugRecipeManager().loadFiles();

        Main.plugin.getRecipeManager().convertAllRecipes();
        Main.plugin.getRecipeManager().addAllRecipes();

        if (Main.plugin.getDrugManager().getItems().size() <= 0) {
            loadDefaults();
        }
    }

    private void loadPlaceHolders() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§aFound PlaceHolderAPI.");
            new DrugPlaceHolders().register();
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§aHooked into PlaceHolderAPI");
        } else {
            sendConsoleMessage(plugin.getMessages().getPrefix()
                    + "§cPlaceHolderAPI.jar was not found. Disabling all PlaceHolderAPI elements!");
        }
    }

    private void checkForUpdate() {
        if (Main.plugin.getSettings().isCheckForUpdate()) {
            new Updater(9684).checkForUpdate();
        } else {
            sendConsoleMessage(plugin.getMessages().getPrefix() + "§c§oDisabled Update Checking");
        }
    }

    private void loadDefaults() {
        sendConsoleMessage(ChatColor.BLUE + "[INFO] No Drugs where Found in your Folder. Creating Default Drugs!");
        Reader drugReader = new InputStreamReader(this.getClass().getResourceAsStream("/drugs.json"));
        JsonArray drugs = new Gson().fromJson(drugReader, JsonArray.class);
        for (JsonElement drug : drugs) {
            Main.plugin.getDrugManager().createFromJson(
                    new File(Main.plugin.getDrugManager().getMainFile(),
                            drug.getAsJsonObject().get("name").getAsString() + ".json").getAbsolutePath(),
                    drug.getAsJsonObject());
        }

        Reader drReader = new InputStreamReader(this.getClass().getResourceAsStream("/drugcrafting.json"));
        JsonArray drugRecipes = new Gson().fromJson(drReader, JsonArray.class);
        for (JsonElement recipe : drugRecipes) {
            Main.plugin.getDrugRecipeManager().createFromJson(
                    new File(Main.plugin.getDrugRecipeManager().getMainFile(),
                            recipe.getAsJsonObject().get("drug").getAsString() + ".json").getAbsolutePath(),
                    recipe.getAsJsonObject());
        }

        Reader sReader = new InputStreamReader(this.getClass().getResourceAsStream("/seedcrafting.json"));
        JsonArray seeds = new Gson().fromJson(sReader, JsonArray.class);
        for (JsonElement seed : seeds) {
            Main.plugin.getDrugSeedManager().createFromJson(
                    new File(Main.plugin.getDrugSeedManager().getMainFile(),
                            seed.getAsJsonObject().get("drug").getAsString() + ".json").getAbsolutePath(),
                    seed.getAsJsonObject());
        }
        sendConsoleMessage(ChatColor.GREEN + "Default Drugs Created! Enjoy Simple-Drugs :D");
    }

    private void sendConsoleMessage(String message) {
        Main.plugin.getServer().getConsoleSender().sendMessage(message);
    }

    public Economy getEcon() {
        return econ;
    }
}
