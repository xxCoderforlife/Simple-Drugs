package me.Coderforlife.SimpleDrugs.UpdateChecker;

import me.Coderforlife.SimpleDrugs.Main;
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
                Bukkit.getConsoleSender().sendMessage(plugin.getMessages().getPrefix() + "Running the most current build " + currVersion);
            } else {
                if(currVersionINT > spigotVersionINT) {
                    isdev = true;
                    Bukkit.getConsoleSender().sendMessage(plugin.getMessages().getPrefix() + "You are running a Development build.");
                    Bukkit.getConsoleSender().sendMessage(plugin.getMessages().getPrefix() + "GitHub: https://github.com/xxCoderforlife/Simple-Drugs");

                } else {
                    isnew = true;
                    Bukkit.getConsoleSender().sendMessage(plugin.getMessages().getPrefix() + "§c§oYou are running an " + "outdated version.");
                    Bukkit.getConsoleSender().sendMessage(plugin.getMessages().getPrefix() + "§dDownload the newest " + "version here:");
                    Bukkit.getConsoleSender().sendMessage(plugin.getMessages().getPrefix() + "§e§nhttps://www.spigotmc" + ".org/resources/simple-drugs-gui.9684/");
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
                        p.sendMessage(plugin.getMessages().getPrefix() + "§bThere is a new update!" + spigotVersion);
                        p.sendMessage(plugin.getMessages().getPrefix() + "§6§lDownload the new version!");
                        p.sendMessage(plugin.getMessages().getPrefix() + "https://www.spigotmc.org/resources/simple-drugs-gui.9684/");
                    }
                }
            }, 60L);
        } else if(isdev) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    if(p.hasPermission("drugs.updater")) {
                        p.sendMessage(plugin.getMessages().getPrefix() + "§eYou are running Dev Build : " + currVersion);
                        p.sendMessage(plugin.getMessages().getPrefix() + "§cIf you find any bugs create a ticket on " + "GitHub");
                        p.sendMessage(plugin.getMessages().getPrefix() + "https://github.com/xxCoderforlife/Simple-Drugs/issues/new");
                        p.sendMessage(plugin.getMessages().getPrefix() + "§eProvide screenshots if you can");
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