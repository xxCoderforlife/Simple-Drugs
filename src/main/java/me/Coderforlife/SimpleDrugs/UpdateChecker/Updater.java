package me.Coderforlife.SimpleDrugs.UpdateChecker;

import me.Coderforlife.SimpleDrugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.function.Consumer;

import java.io.InputStream;
import java.net.URI;
import java.util.Scanner;

public class Updater implements Listener {

    public String spigotVersion;
    private String currVersion;
    private Boolean isnew = false;
    private Boolean isdev = false;
    private Integer resourceID;
    private Main plugin = Main.plugin;

    public Updater(Integer resourceID) {
        this.resourceID = resourceID;
        if(plugin.getSettings().isUpdateMessage()) {
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
            String sVersion = spigotVersion.replaceAll("[A-Za-z-+.]*", "");
            String cVersion = currVersion.replaceAll("[A-Za-z-+.]*", "");
            //ALWAYS KEEP FOPMAT THE VERSION NUMBER LIKE THIS: x.x.x-DEV
            int spigotVersionINT = Integer.parseInt(sVersion);
            int currVersionINT = Integer.parseInt(cVersion);
            if(spigotVersionINT == currVersionINT) {
                Bukkit.getConsoleSender().sendMessage("SD " + "Running the most current build " + currVersion);
            } else {
                if(currVersionINT > spigotVersionINT) {
                    isdev = true;
                    Bukkit.getConsoleSender().sendMessage("SD " + "You are running a Development build.");
                    Bukkit.getConsoleSender().sendMessage("SD " + "GitHub: https://github.com/xxCoderforlife/Simple-Drugs");

                } else {
                    isnew = true;
                    Bukkit.getConsoleSender().sendMessage("SD " + "§c§oYou are running an " + "outdated version.");
                    Bukkit.getConsoleSender().sendMessage("SD " + "§dDownload the newest " + "version here:");
                    Bukkit.getConsoleSender().sendMessage("SD " + "§e§nhttps://www.spigotmc" + ".org/resources/simple-drugs-gui.9684/");
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
                        p.sendMessage("SD " + "§bThere is a new update!" + spigotVersion);
                        p.sendMessage("SD " + "§6§lDownload the new version!");
                        p.sendMessage("SD " + "https://www.spigotmc.org/resources/simple-drugs-gui.9684/");
                    }
                }
            }, 60L);
        } else if(isdev) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    if(p.hasPermission("drugs.updater")) {
                        p.sendMessage("SD " + "§eYou are running Dev Build : " + currVersion);
                        p.sendMessage("SD " + "§cIf you find any bugs create a ticket on " + "GitHub");
                        p.sendMessage("SD " + "https://github.com/xxCoderforlife/Simple-Drugs/issues/new");
                        p.sendMessage("SD " + "§eProvide screenshots if you can");
                    }
                }
            }, 60L);
        }
    }

    private void getLatestVersion(final Consumer<String> consumer) {
        try(InputStream inputStream = new URI("https://api.spigotmc.org/legacy/update.php?resource=" + resourceID).toURL().openStream(); 
        Scanner scanner = new Scanner(inputStream)) {
            if(scanner.hasNext()) {
                consumer.accept(scanner.next());
            }
        } catch(Exception e) {
            plugin.getLogger().info("Failed to check for updates: " + e.getMessage());
        }
    }
}