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
	public static String bagofdrugs = "Drugs.BagOfDrugs";
	Drugs D = new Drugs(this);
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

		getServer().getConsoleSender().sendMessage(header1);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loading all Class files and Handlers...");
		createCustomConfig();

		if (!drugsConfig.getBoolean("Drugs.Weed.DisableCrafting")) {
			D.WeedRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "Weed Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Weed Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Acid.DisableCrafting")) {
			D.AcidRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "Acid Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Acid Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Coke.DisableCrafting")) {
			D.CokeRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "Coke Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Coke Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Heroin.DisableCrafting")) {
			D.HeroinRecipe();
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Heroin Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Heroin Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Percocet.DisableCrafting")) {
			D.PercocetRecipe();
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Percocet Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Percocet Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Molly.DisableCrafting")) {
			D.MollyRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "Molly Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Molly Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Ciggy.DisableCrafting")) {
			D.CiggyRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "Ciggy Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Ciggy Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Alcohol.DisableCrafting")) {
			D.AlcoholRecipe();
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Alcohol Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Alcohol Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.DMT.DisableCrafting")) {
			D.DMTRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "DMT Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "DMT Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Flakka.DisableCrafting")) {
			D.FlakkaRecipe();
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Flakka Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Flakka Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Ketamine.DisableCrafting")) {
			D.KetamineRecipe();
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Ketamine Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Ketamine Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Meth.DisableCrafting")) {
			D.MethRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "Meth Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Meth Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Oxy.DisableCrafting")) {
			D.OxyRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "Oxy Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "Oxy Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.PCP.DisableCrafting")) {
			D.PCPRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "PCP Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "PCP Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Salvia.DisableCrafting")) {
			D.SalviaRecipe();
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Salvia Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Salvia Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Shrooms.DisableCrafting")) {
			D.ShroomsRecipe();
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Shrooms Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Shrooms Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Tussin.DisableCrafting")) {
			D.TussinRecipe();
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Tussin Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Tussin Recipe:" + ChatColor.RED + " NOT LOADED");
		}
		if (!drugsConfig.getBoolean("Drugs.Xannx.DisableCrafting")) {
			D.XannxRecipe();
			getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "Xanax Recipe:" + ChatColor.GREEN + " LOADED");
		} else {
			getServer().getConsoleSender()
					.sendMessage(ChatColor.WHITE + "Xanax Recipe:" + ChatColor.RED + " NOT LOADED");
		}

		try {

			RegisterEvents();

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
		this.getServer().getPluginManager().registerEvents(new BagOfDrugs(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		this.getServer().getPluginManager().registerEvents(new Molly(this), this);
		this.getServer().getPluginManager().registerEvents(new Acid(this), this);
		this.getServer().getPluginManager().registerEvents(new Weed(this), this);
		this.getServer().getPluginManager().registerEvents(new Heroin(this), this);
		this.getServer().getPluginManager().registerEvents(new Percocet(this), this);
		this.getServer().getPluginManager().registerEvents(new Coke(this), this);
		this.getServer().getPluginManager().registerEvents(new Ciggy(this), this);
		this.getServer().getPluginManager().registerEvents(new Alcohol(this), this);
		this.getServer().getPluginManager().registerEvents(new Flakka(this), this);
		this.getServer().getPluginManager().registerEvents(new Meth(this), this);
		this.getServer().getPluginManager().registerEvents(new Ketamine(this), this);
		this.getServer().getPluginManager().registerEvents(new PCP(this), this);
		this.getServer().getPluginManager().registerEvents(new DMT(this), this);
		this.getServer().getPluginManager().registerEvents(new Shrooms(this), this);
		this.getServer().getPluginManager().registerEvents(new Salvia(this), this);
		this.getServer().getPluginManager().registerEvents(new Tussin(this), this);
		this.getServer().getPluginManager().registerEvents(new Oxy(this), this);
		this.getServer().getPluginManager().registerEvents(new Xannx(this), this);
		this.getCommand("drugs").setExecutor(new KillerCommands(this));
		this.getCommand("drugs").setTabCompleter(new TabCommands());

	}
}
