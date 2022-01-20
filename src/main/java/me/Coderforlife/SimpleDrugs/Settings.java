package me.Coderforlife.SimpleDrugs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Settings {
    public static boolean CheckForUpdate;
    public static boolean UpdateMessage;
    public static boolean JoinMessage;
    public static boolean BagOfDrugs_CanMove;
    public static boolean BagOfDrugs_CanDrop;
    public static boolean BagOfDrugs_GiveOnJoin;
    public static boolean BagOfDrugs_DropOnDeath;
    public static boolean BagOfDrugs_GiveOnRespawn;

    private static final Plugin plugin = Main.plugin;
    private static File drugsConfigFile;
    private static FileConfiguration drugsConfig;

    public static void setup() {
        SetupConfig();
        CheckForUpdate(drugsConfig.getBoolean("Drugs.CheckForUpdate"));
        UpdateMessage(drugsConfig.getBoolean("Drugs.UpdateMessage"));
        JoinMessage(drugsConfig.getBoolean("Drugs.JoinMessage"));
        BagOfDrugs_CanMove(drugsConfig.getBoolean("Drugs.BagOfDrugs.CanMove"));
        BagOfDrugs_CanDrop(drugsConfig.getBoolean("Drugs.BagOfDrugs.CanDrop"));
        BagOfDrugs_GiveOnRespawn(drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnRespawn"));
        BagOfDrugs_GiveOnJoin(drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnJoin"));
        BagOfDrugs_DropOnDeath(drugsConfig.getBoolean("Drugs.BagOfDrugs.DropOnDeath"));
    }

    public static void CheckForUpdate(boolean bol) {
        CheckForUpdate = bol;
        drugsConfig.set("Drugs.CheckForUpdate", bol);
    }

    public static void UpdateMessage(boolean bol) {
        UpdateMessage = bol;
        drugsConfig.set("Drugs.UpdateMessage", bol);
    }

    public static void JoinMessage(boolean bol) {
        JoinMessage = bol;
        drugsConfig.set("Drugs.JoinMessage", bol);
    }

    public static void BagOfDrugs_CanMove(boolean bol) {
        BagOfDrugs_CanMove = bol;
        drugsConfig.set("Drugs.BagOfDrugs.CanMove", bol);
    }

    public static void BagOfDrugs_CanDrop(boolean bol) {
        BagOfDrugs_CanDrop = bol;
        drugsConfig.set("Drugs.BagOfDrugs.CanDrop", bol);
    }

    public static void BagOfDrugs_GiveOnJoin(boolean bol) {
        BagOfDrugs_GiveOnJoin = bol;
        drugsConfig.set("Drugs.BagOfDrugs.GiveOnJoin", bol);
    }

    public static void BagOfDrugs_DropOnDeath(boolean bol) {
        BagOfDrugs_DropOnDeath = bol;
        drugsConfig.set("Drugs.BagOfDrugs.DropOnDeath", bol);
    }

    public static void BagOfDrugs_GiveOnRespawn(boolean bol) {
        BagOfDrugs_GiveOnRespawn = bol;
        drugsConfig.set("Drugs.BagOfDrugs.GiveOnRespawn", bol);
    }

    public static void SetupConfig() {
        drugsConfigFile = new File(plugin.getDataFolder(), "config.yml");
        if(!drugsConfigFile.exists()) {
            drugsConfigFile.getParentFile().mkdir();
            plugin.saveResource("config.yml", false);
        }
        drugsConfig = new YamlConfiguration();
        try {
            drugsConfig.load(drugsConfigFile);
        } catch(IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            drugsConfig.save(drugsConfigFile);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

