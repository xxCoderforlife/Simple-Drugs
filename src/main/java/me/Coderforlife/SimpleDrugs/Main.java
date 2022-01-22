package me.Coderforlife.SimpleDrugs;

import me.Coderforlife.SimpleDrugs.Druging.BagOfDrugs;
import me.Coderforlife.SimpleDrugs.Events.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin plugin;

    //Mess of Strings
    public String header1 = ChatColor.WHITE + "" + ChatColor.BOLD + "============" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "[Simple-Drugs]" + ChatColor.WHITE + "" + ChatColor.BOLD + "============";
    public static String prefix = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "SD" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET;

    private Settings s;
    @Override
    public void onEnable() {
        plugin = this;
        s = new Settings();

        sendConsoleMessage(header1);
        sendConsoleMessage("§aLoading Plugin...");

        new Setup(this);

        this.getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
        this.getServer().getPluginManager().registerEvents(new BagOfDrugs(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        this.getServer().getPluginManager().registerEvents(new DrugUseListener(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        this.getServer().getPluginManager().registerEvents(new CraftingEvent(), this);
        this.getCommand("drugs").setExecutor(new Commands(this));
        this.getCommand("drugs").setTabCompleter(new TabCommands());
        sendConsoleMessage("§aLoaded without Errors. Plugin is ready to Use :D");
    }

    @Override
    public void onDisable() {
        s.save();
    }

    private void sendConsoleMessage(String message) {
        this.getServer().getConsoleSender().sendMessage(message);
    }
}
