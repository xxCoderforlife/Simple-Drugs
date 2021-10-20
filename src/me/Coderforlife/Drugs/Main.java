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
	public static String bagofdrugs = "Core.Drugs.BagOfDrugs";
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
			this.getServer().getPluginManager().registerEvents(new Alcohol(this), this);
			this.getServer().getPluginManager().registerEvents(new Flakka(this), this);
			this.getServer().getPluginManager().registerEvents(new Meth(this), this);
			this.getServer().getPluginManager().registerEvents(new Ketamine(this), this);
			this.getServer().getPluginManager().registerEvents(new PCP(this), this);
			this.getServer().getPluginManager().registerEvents(new DMT(this), this);
			this.getServer().getPluginManager().registerEvents(new Shrooms(this), this);
			this.getServer().getPluginManager().registerEvents(new Salvia(this), this);

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
			//Acid Time
			drugsConfig.addDefault("Core.Drugs.Acid.Time.CONFUSION" , 200);
			drugsConfig.addDefault("Core.Drugs.Acid.Time.HEALTH_BOOST" , 2400);
			drugsConfig.addDefault("Core.Drugs.Acid.Time.NIGHT_VISION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Acid.Time.SLOW_FALLING" , 2400);
			//Ciggy Time
			drugsConfig.addDefault("Core.Drugs.Ciggy.Time.SLOW_DIGGING" , 2400);
			drugsConfig.addDefault("Core.Drugs.Ciggy.Time.JUMP" , 2400);
			drugsConfig.addDefault("Core.Drugs.Ciggy.Time.SATURATION" ,2400);
			drugsConfig.addDefault("Core.Drugs.Ciggy.Time.DAMAGE_RESISTANCE" , 2400);
			//Coke Time
			drugsConfig.addDefault("Core.Drugs.Coke.Time.INCREASE_DAMAGE" , 2400);
			drugsConfig.addDefault("Core.Drugs.Coke.Time.SPEED" , 2400);
			drugsConfig.addDefault("Core.Drugs.Coke.Time.FAST_DIGGING" , 2400);
			drugsConfig.addDefault("Core.Drugs.Coke.Time.DAMAGE_RESISTANCE" , 2400);
			drugsConfig.addDefault("Core.Drugs.Coke.Time.HEALTH_BOOST" , 2400);
			//Heroin Time
			drugsConfig.addDefault("Core.Drugs.Heroin.Time.SLOW" , 2400);
			drugsConfig.addDefault("Core.Drugs.Heroin.Time.WEAKNESS" , 2400);
			drugsConfig.addDefault("Core.Drugs.Heroin.Time.POISON" , 2400);
			drugsConfig.addDefault("Core.Drugs.Heroin.Time.UNLUCK" , 2400);
			//Molly Time
			drugsConfig.addDefault("Core.Drugs.Molly.Time.CONFUSION" , 200);
			drugsConfig.addDefault("Core.Drugs.Molly.Time.FAST_DIGGING" , 2400);
			drugsConfig.addDefault("Core.Drugs.Molly.Time.SPEED" , 2400);
			drugsConfig.addDefault("Core.Drugs.Molly.Time.FIRE_RESISTANCE" , 2400);
			drugsConfig.addDefault("Core.Drugs.Molly.Time.NIGHT_VISION" , 2400);
			//Percocet Time
			drugsConfig.addDefault("Core.Drugs.Percocet.Time.SLOW" , 2400);
			drugsConfig.addDefault("Core.Drugs.Percocet.Time.CONFUSION" , 200);
			drugsConfig.addDefault("Core.Drugs.Percocet.Time.NIGHT_VISION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Percocet.Time.LUCK" , 2400);
			//Weed Time
			drugsConfig.addDefault("Core.Drugs.Weed.Time.SLOW" , 2400);
			drugsConfig.addDefault("Core.Drugs.Weed.Time.SLOW_DIGGING" , 2400);
			drugsConfig.addDefault("Core.Drugs.Weed.Time.SLOW_FALLING" , 2400);
			drugsConfig.addDefault("Core.Drugs.Weed.Time.LUCK" , 2400);
			//Meth Time
			drugsConfig.addDefault("Core.Drugs.Meth.Time.ABSORPTION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Meth.Time.SLOWNESS" , 2400);
			drugsConfig.addDefault("Core.Drugs.Meth.Time.WEAKNESS" , 2400);
			drugsConfig.addDefault("Core.Drugs.Meth.Time.LEVITATION" , 2400);
			//Ketamine Time
			drugsConfig.addDefault("Core.Drugs.Ketamine.Time.NIGHT_VISION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Ketamine.Time.SPEED" , 2400);
			drugsConfig.addDefault("Core.Drugs.Ketamine.Time.FAST_DIGGING" , 2400);
			drugsConfig.addDefault("Core.Drugs.Ketamine.Time.CONFUSION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Ketamine.Time.SLOW_FALLING" , 2400);
			//DMT Time
			drugsConfig.addDefault("Core.Drugs.DMT.Time.DAMAGE_RESISTANCE" , 2400);
			drugsConfig.addDefault("Core.Drugs.DMT.Time.SLOW_FALLING" , 2400);
			drugsConfig.addDefault("Core.Drugs.DMT.Time.SLOWNESS" , 2400);
			drugsConfig.addDefault("Core.Drugs.DMT.Time.GLOWING" , 2400);

			//Alcohol Time
			drugsConfig.addDefault("Core.Drugs.Alcohol.Time.SPEED" , 2400);
			drugsConfig.addDefault("Core.Drugs.Alcohol.Time.NIGHT_VISION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Alcohol.Time.HUNGER" , 2400);
			drugsConfig.addDefault("Core.Drugs.Alcohol.Time.CONFUSION" , 2400);

			//PCP Time
			drugsConfig.addDefault("Core.Drugs.PCP.Time.DAMAGE_RESISTANCE" , 2400);
			drugsConfig.addDefault("Core.Drugs.PCP.Time.BAD_OMEN" , 2400);
			drugsConfig.addDefault("Core.Drugs.PCP.Time.CONFUSION" , 2400);

			//Shrooms Time
			drugsConfig.addDefault("Core.Drugs.Shrooms.Time.LUCK" , 2400);
			drugsConfig.addDefault("Core.Drugs.Shrooms.Time.NIGHT_VISION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Shrooms.Time.CONFUSION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Shrooms.Time.GLOWING" , 2400);
			//Salvia Time
			drugsConfig.addDefault("Core.Drugs.Salvia.Time.NIGHT_VISION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Salvia.Time.REGENERATION" , 2400);
			drugsConfig.addDefault("Core.Drugs.Salvia.Time.WEAKNESS" , 2400);
			drugsConfig.addDefault("Core.Drugs.Salvia.Time.GLOWING" , 2400);

			//Flakka Time
			drugsConfig.addDefault("Core.Drugs.Flakka.Time.SPEED" , 2400);
			drugsConfig.addDefault("Core.Drugs.Flakka.Time.DAMAGE_RESISTANCE" , 2400);
			drugsConfig.addDefault("Core.Drugs.Flakka.Time.UNLUCK" , 2400);
			drugsConfig.addDefault("Core.Drugs.Flakka.Time.DOLPHINS_GRACE" , 2400);




		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();

		}
	}
	
	public FileConfiguration getCustomConfig() {
		return drugsConfig;
	}
	

}
