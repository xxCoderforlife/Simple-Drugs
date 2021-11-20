package me.Coderforlife.Drugs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.Coderforlife.Drugs.Events.DrugUseListener;
import me.Coderforlife.Drugs.Events.PlayerJoin;
import me.Coderforlife.Drugs.Events.PlayerRespawn;
import me.Coderforlife.Drugs.PlaceHolder.DrugPlaceHolders;
import me.Coderforlife.Drugs.UpdateChecker.Updater;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	public static String bagofdrugs = "Drugs.BagOfDrugs";
	Drugs D;
	public String header1 = ChatColor.WHITE + "" + ChatColor.BOLD + "============" + ChatColor.DARK_RED + ""
			+ ChatColor.BOLD + "[Simple-Drugs]" + ChatColor.WHITE + "" + ChatColor.BOLD + "============";
	Logger log = Logger.getLogger("Minecraft");

	public static String prefix = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD
			+ "SD" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET;
	public static String stack = ChatColor.RED + "" + ChatColor.BOLD + "Do Not Use It In A Stack.";

	public File drugsConfigFile;
	public FileConfiguration drugsConfig;

	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		int pluginId = 13155;
		Metrics metrics = new Metrics(this, pluginId);
		new Updater(this, 9684).checkForUpdate();

		getServer().getConsoleSender().sendMessage(header1);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loading all Class files and Handlers...");
		createCustomConfig();

		D = new Drugs(this);
		for (Drug drug : D.getAllDrugs()) {
			boolean isCraftingDisabled = drugsConfig.getBoolean("Drugs." + drug.getName() + ".DisableCrafting");

			if (isCraftingDisabled) {
				getServer().getConsoleSender()
						.sendMessage(ChatColor.WHITE + drug.getName() + ":" + ChatColor.RED + " NOT LOADED");
			} else {
				D.enableDrug(drug);
				getServer().getConsoleSender()
						.sendMessage(ChatColor.WHITE + drug.getName() + ":" + ChatColor.GREEN + " LOADED");
			}
		}
		try {

			RegisterEvents();
			loadPlaceHolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loaded without Errors.");

	}

	@Override
	public void onDisable() {
		D.getAllDrugs().clear();
	}

	private void createCustomConfig() {
		drugsConfigFile = new File(getDataFolder(), "config.yml");
		if (!drugsConfigFile.exists()) {
			drugsConfigFile.getParentFile().mkdir();

			saveResource("config.yml", false);
		}

		drugsConfig = new YamlConfiguration();
		try {
			drugsConfig.load(drugsConfigFile);

		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();

		}
	}

	public FileConfiguration getCustomConfig() {
		return drugsConfig;
	}

	public void RegisterEvents() {
		this.getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
		this.getServer().getPluginManager().registerEvents(new BagOfDrugs(this, D), this);
		this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		this.getServer().getPluginManager().registerEvents(new DrugUseListener(this, D), this);
		this.getCommand("drugs").setExecutor(new KillerCommands(this, D));
		this.getCommand("drugs").setTabCompleter(new TabCommands(this, D));

	}

	public void loadPlaceHolders() {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			log.info("Hooked into PlaceHolderAPI");
			new DrugPlaceHolders(this, D).register();
		} else {
			Bukkit.getConsoleSender()
					.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&cCould not find&e PlacecHolderAPI\n " + "&bDownload it here:\n"
									+ "&d&n&ohttps://www.spigotmc.org/resources/placeholderapi.6245/"));
		}
	}
}
