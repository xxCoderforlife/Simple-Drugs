package me.Coderforlife.Drugs.UpdateChecker;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Consumer;
import org.json.simple.parser.JSONParser;

import me.Coderforlife.Drugs.Main;
import net.md_5.bungee.api.ChatColor;

public class Updater implements Listener {
	public static String spigotVersion;
	private static String currVersion;
	private Boolean isnew = false;
	private Boolean isdev = false;
	private Main plugin;
	private Integer recourceID;
	JSONParser parser = new JSONParser();

	public Updater(Main plugin, Integer recourceID) {
		this.plugin = plugin;
		this.recourceID = recourceID;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	public void checkForUpdate() {
		currVersion = plugin.getDescription().getVersion();
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
			getLatestVersion(version -> {
				spigotVersion = version;
			});
			if (spigotVersion == null) {
				return;
			}
			String version1 = spigotVersion.replaceAll("[-+.]*", "");
			String version2 = currVersion.replaceAll("[-+.]*", "");
			int spigotVersionINT = Integer.parseInt(version1.replace("v27devbuild", ""));
			int currVersionINT = Integer.parseInt(version2.replace("27devbuild", ""));
			if (spigotVersionINT == currVersionINT) {
				Bukkit.getConsoleSender().sendMessage(Main.prefix + "Running the most current build " + currVersion);
			} else {
				if (currVersionINT > spigotVersionINT) {
					isdev = true;
					Bukkit.getConsoleSender().sendMessage(Main.prefix + "You are running a Dev build.");

				} else {
					isnew = true;
					Bukkit.getConsoleSender().sendMessage(Main.prefix
							+ ChatColor.translateAlternateColorCodes('&', "&c&oYou are running an outdated version."));
					Bukkit.getConsoleSender().sendMessage(Main.prefix
							+ ChatColor.translateAlternateColorCodes('&', "&dDownload the newest version here:"));
					Bukkit.getConsoleSender().sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&',
							"&e&nhttps://www.spigotmc.org/resources/simple-drugs-gui.9684/"));

				}
			}
		}, 20l);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (isnew == true) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					if (p.hasPermission("drugs.updater")) {
						p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&',
								"&bThere is a new update!" + spigotVersion));
						p.sendMessage(Main.prefix
								+ ChatColor.translateAlternateColorCodes('&', "&6&lDownload the new version!"));
						p.sendMessage(Main.prefix + "https://www.spigotmc.org/resources/simple-drugs-gui.9684/");
					}
				}
			}, 60l);
		} else if (isdev == true) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					if (p.hasPermission("drugs.updater")) {
						p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&',
								"&eYou are running Dev Build : " + currVersion));
						p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&',
								"&cIf you find any bugs create a ticket on GitHub"));
						p.sendMessage(Main.prefix + "https://github.com/xxCoderforlife/Simple-Drugs/issues/new");
						p.sendMessage(Main.prefix
								+ ChatColor.translateAlternateColorCodes('&', "&eProvide screenshots if you can"));
					}
				}
			}, 60l);
		}
	}

	private void getLatestVersion(final Consumer<String> consumer) {
		try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + recourceID)
				.openStream(); Scanner scanner = new Scanner(inputStream)) {
			if (scanner.hasNext()) {
				consumer.accept(scanner.next());
			}
		} catch (Exception e) {
			plugin.getLogger().info("Failed to check for updates: " + e.getMessage());
		}
	}
}