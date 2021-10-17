package me.Coderforlife.Drugs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	Drugs D = new Drugs(this);
	public String header1 = ChatColor.WHITE + "" + ChatColor.BOLD + "============" + ChatColor.DARK_RED + ""
			+ ChatColor.BOLD + "[Simple-Drugs]" + ChatColor.WHITE + "" + ChatColor.BOLD + "============";
	Logger log = Logger.getLogger("Minecraft");

	public static String prefix = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD
			+ "SD" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET;
	public static String stack = ChatColor.RED + "" + ChatColor.BOLD + "Do Not Use It In A Stack.";

	public File drugsConfigFile;
	public FileConfiguration drugsConfig;

	@Override
	public void onEnable() {

		createCustomConfig();

		D.WeedRecipe();
		D.AcidRecipe();
		D.CokeRecipe();
		D.HeroinRecipe();
		D.PercocetRecipe();
		D.MollyRecipe();
		D.CiggyRecipe();
		getServer().getConsoleSender().sendMessage(header1);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loading all Class files and Handlers...");

		try {
			this.getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
			this.getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
			this.getServer().getPluginManager().registerEvents(new BagOfDrugs(this), this);
			this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
			this.getServer().getPluginManager().registerEvents(new Molly(this), this);
			this.getServer().getPluginManager().registerEvents(new Acid(this), this);
			this.getServer().getPluginManager().registerEvents(new Weed(this), this);
			this.getServer().getPluginManager().registerEvents(new Heroin(this), this);
			this.getServer().getPluginManager().registerEvents(new Percocet(this), this);
			this.getServer().getPluginManager().registerEvents(new Coke(this), this);
			this.getServer().getPluginManager().registerEvents(new Ciggy(this), this);
			this.getCommand("drugs").setExecutor(new KillerCommands(this));

		} catch (Exception e) {
			e.printStackTrace();
		}

		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loaded without Errors.");

	}

	@Override
	public void onDisable() {
	}



	private void createCustomConfig() {
		drugsConfigFile = new File(getDataFolder(), "config.yml");
		if (!drugsConfigFile.exists()) {
			drugsConfigFile.getParentFile().mkdir();

			saveResource("config.yml", true);
		}

		drugsConfig = new YamlConfiguration();
		try {
			drugsConfig.load(drugsConfigFile);
			drugsConfig.set("#", "Spigot: https://www.spigotmc.org/resources/simple-drugs-with-gui.9684/");
			drugsConfig.set("#", "Discord: https://discord.com/invite/jnmKj7Z");
			drugsConfig.set("#", "If the player can move the bag in their Inventory");
			drugsConfig.addDefault("Core.Drugs.BagOfDrugs.CanMove", true);
			drugsConfig.set("#", "If the player can drop the bag");
			drugsConfig.addDefault("Core.Drugs.BagOfDrugs.CanDrop", true);
			drugsConfig.set("#", "Is the bag given on player join");
			drugsConfig.set("#", "If you set this to false use /drugs bagofdrugs to get it");
			drugsConfig.addDefault("Core.Drugs.BagOfDrugs.GiveOnJoin", true);
			drugsConfig.set("#", "If the Bag is dropped on death or not");
			drugsConfig.addDefault("Core.Drugs.BagOfDrugs.DropOnDeath", true);
			drugsConfig.set("#", "If the player keeps the bag on respawn");
			drugsConfig.addDefault("Core.Drugs.BagOfDrugs.GiveOnRespawn", true);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();

		}
	}
	
	public FileConfiguration getCustomConfig() {
		return drugsConfig;
	}
	

}
