package me.Coderforlife.SimpleDrugs.UpdateChecker;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Consumer;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Updater implements Listener {
    private Main plugin;

    public static String spigotVersion;
    private static String currVersion;
    private Boolean isnew = false;
    private Boolean isdev = false;
    private Integer resourceID;
    private Settings s = new Settings();

    public Updater(Main plugin, Integer resourceID) {
        this.plugin = plugin;
        this.resourceID = resourceID;
        if(s.UpdateMessage) {
            Bukkit.getPluginManager().registerEvents(this, plugin);
        }
    }

    public void checkForUpdate() {
        currVersion = plugin.getDescription().getVersion();
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            getLatestVersion(version -> spigotVersion = version);
            if(spigotVersion == null) {
                return;
            }
            String version1 = spigotVersion.replaceAll("[A-Za-z-+.]*", "");
            String version2 = currVersion.replaceAll("[A-Za-z-+.]*", "");
            //Added remove 44 so we don't get spammed with an outdated version, message every test.
            //We can remove this when we post a new spigot update.
            int spigotVersionINT = Integer.parseInt(version1.replaceAll("44", ""));
            int currVersionINT = Integer.parseInt(version2);
            if(spigotVersionINT == currVersionINT) {
                Bukkit.getConsoleSender().sendMessage(Main.prefix + "Running the most current build " + currVersion);
            } else {
                if(currVersionINT > spigotVersionINT) {
                    isdev = true;
                    Bukkit.getConsoleSender().sendMessage(Main.prefix + "You are running a Dev build.");

                } else {
                    isnew = true;
                    Bukkit.getConsoleSender().sendMessage(Main.prefix + "§c§oYou are running an " + "outdated version.");
                    Bukkit.getConsoleSender().sendMessage(Main.prefix + "§dDownload the newest " + "version here:");
                    Bukkit.getConsoleSender().sendMessage(Main.prefix + "§e§nhttps://www.spigotmc" + ".org/resources/simple-drugs-gui.9684/");
                }
            }
        }, 20L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(isnew) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    if(p.hasPermission("drugs.updater")) {
                        p.sendMessage(Main.prefix + "§bThere is a new update!" + spigotVersion);
                        p.sendMessage(Main.prefix + "§6§lDownload the new version!");
                        p.sendMessage(Main.prefix + "https://www.spigotmc.org/resources/simple-drugs-gui.9684/");
                    }
                }
            }, 60L);
        } else if(isdev) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    if(p.hasPermission("drugs.updater")) {
                        p.sendMessage(Main.prefix + "§eYou are running Dev Build : " + currVersion);
                        p.sendMessage(Main.prefix + "§cIf you find any bugs create a ticket on " + "GitHub");
                        p.sendMessage(Main.prefix + "https://github.com/xxCoderforlife/Simple-Drugs/issues/new");
                        p.sendMessage(Main.prefix + "§eProvide screenshots if you can");
                    }
                }
            }, 60L);
        }
    }

    private void getLatestVersion(final Consumer<String> consumer) {
        try(InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceID).openStream(); Scanner scanner = new Scanner(inputStream)) {
            if(scanner.hasNext()) {
                consumer.accept(scanner.next());
            }
        } catch(Exception e) {
            plugin.getLogger().info("Failed to check for updates: " + e.getMessage());
        }
    }
}