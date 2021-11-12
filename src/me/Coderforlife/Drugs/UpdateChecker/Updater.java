package me.Coderforlife.Drugs.UpdateChecker;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
	private static Boolean isNewVersion = false;
	private static String spigotVersion;
	private static String currVersion;
	private Main plugin;
	private Integer recourceID;
	private String permission;
	JSONParser parser = new JSONParser();
	public Updater(Main plugin, Integer recourceID, String permission) {
		this.plugin = plugin;
		this.recourceID = recourceID;
		this.permission = permission;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	public void checkForUpdate() {
		currVersion = plugin.getDescription().getVersion();
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
			getLatestVersion(version -> {spigotVersion = version;});
			if(spigotVersion == null) {return;}
			String version1 = spigotVersion.replaceAll("[-+.]*", "");
			String version2 = currVersion.replaceAll("[-+.]*", "");
			int spigotVersionINT = Integer.parseInt(version1.replace("v27devbuild", ""));
			int currVersionINT = Integer.parseInt(version2.replace("27devbuild", ""));
			if(currVersionINT == spigotVersionINT) {plugin.getLogger().info(String.format("You are using the most recent version. (v%s)", currVersion)); return;}
			List<String> lines = new ArrayList<>();
			if(currVersionINT > spigotVersionINT) {
				lines.add(String.format("You are using the beta version of &b%s&e!", plugin.getName()));
				lines.add(" ");
				lines.add(String.format("Current version: &6v%s", currVersion));
				lines.add(String.format(" Public version: &av%s", spigotVersion));
				lines.add(" ");
				lines.add("Please note that this version is not officially released!");
			} else {
				isNewVersion = true;
				lines.add(String.format("There is a new version of &b%s &eavailable!", plugin.getName()));
				lines.add(" ");
				lines.add(String.format("Current version: &cv%s", currVersion));
				lines.add(String.format("    New version: &av%s", spigotVersion));
				lines.add(" ");
			}
			lines.add(String.format("Download: %shttps://www.spigotmc.org/resources/%s", ChatColor.GOLD, recourceID));
			Bukkit.getConsoleSender().sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', "&7&oThere is new version of Simple Durgs"
					+ " &e&l" + spigotVersion));
			Bukkit.getConsoleSender().sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', "&cDownload: &r "
					+ "https://www.spigotmc.org/resources/simple-drugs-gui.9684/"));
		}, 20l);
	}

	private void getLatestVersion(final Consumer<String> consumer) {
		try(InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + recourceID).openStream(); Scanner scanner = new Scanner(inputStream)) {
			if(scanner.hasNext()) {consumer.accept(scanner.next());}
		} catch (Exception e) {plugin.getLogger().info("Failed to check for updates: " + e.getMessage());}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				Player player = e.getPlayer();
				if(permission == null) {return;}
				if(isNewVersion && player.hasPermission(permission)) {
					player.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', "&7&oThere is new version of Simple Durgs"
							+ " &e&l" + spigotVersion));
					player.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', "&cDownload: &r "
							+ "https://www.spigotmc.org/resources/simple-drugs-gui.9684/"));
					if (plugin.drugsConfig.getBoolean("Drugs.JoinMessage")) {
						player.sendMessage(Main.prefix
								+ ChatColor.translateAlternateColorCodes('&', "&f&lServer is running &5&l&oSIMPLE DRUGS"));
					}

				}
			}
		}, 40l);
	}

}