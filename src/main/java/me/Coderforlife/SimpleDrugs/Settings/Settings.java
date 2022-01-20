package me.Coderforlife.SimpleDrugs.Settings;

import me.Coderforlife.SimpleDrugs.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
    public static boolean BagofDrugs_GiveOnRespawn;

    private static Main plugin = (Main) Main.plugin;
    private static File drugsConfigFile;
    private static FileConfiguration drugsConfig;

    public Settings() {
        SetupConfig();
        setCheckForUpdate(drugsConfig.getBoolean("Drugs.CheckForUpdate"));
        setUpdateMessage(drugsConfig.getBoolean("Drugs.UpdateMessage"));
        setJoinMessage(drugsConfig.getBoolean("Drugs.JoinMessage"));
        setBagOfDrugs_CanMove(drugsConfig.getBoolean("Drugs.BagOfDrugs.CanMove"));
        setBagOfDrugs_CanDrop(drugsConfig.getBoolean("Drugs.BagOfDrugs.CanDrop"));
        setBagofDrugs_GiveOnRespawn(drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnRespawn"));
        setBagOfDrugs_GiveOnJoin(drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnJoin"));
        setBagOfDrugs_DropOnDeath(drugsConfig.getBoolean("Drugs.BagOfDrugs.DropOnDeath"));
    }

    public static void setCheckForUpdate(boolean checkForUpdate) {
        CheckForUpdate = checkForUpdate;
        drugsConfig.set("Drugs.CheckForUpdate", checkForUpdate);
    }

    public static void setUpdateMessage(boolean updateMessage) {
        UpdateMessage = updateMessage;
        drugsConfig.set("Drugs.UpdateMessage", updateMessage);
    }

    public static void setJoinMessage(boolean joinMessage) {
        JoinMessage = joinMessage;
        drugsConfig.set("Drugs.JoinMessage", joinMessage);
    }

    public static void setBagOfDrugs_CanMove(boolean bagOfDrugs_CanMove) {
        BagOfDrugs_CanMove = bagOfDrugs_CanMove;
        drugsConfig.set("Drugs.BagOfDrugs.CanMove", bagOfDrugs_CanMove);
    }

    public static void setBagOfDrugs_CanDrop(boolean bagOfDrugs_CanDrop) {
        BagOfDrugs_CanDrop = bagOfDrugs_CanDrop;
        drugsConfig.set("Drugs.BagOfDrugs.CanDrop", bagOfDrugs_CanDrop);
    }

    public static void setBagOfDrugs_GiveOnJoin(boolean bagOfDrugs_GiveOnJoin) {
        BagOfDrugs_GiveOnJoin = bagOfDrugs_GiveOnJoin;
        drugsConfig.set("Drugs.BagOfDrugs.GiveOnJoin", bagOfDrugs_GiveOnJoin);
    }

    public static void setBagOfDrugs_DropOnDeath(boolean bagOfDrugs_DropOnDeath) {
        BagOfDrugs_DropOnDeath = bagOfDrugs_DropOnDeath;
        drugsConfig.set("Drugs.BagOfDrugs.DropOnDeath", bagOfDrugs_DropOnDeath);
    }

    public static void setBagofDrugs_GiveOnRespawn(boolean bagofDrugs_GiveOnRespawn) {
        BagofDrugs_GiveOnRespawn = bagofDrugs_GiveOnRespawn;
        drugsConfig.set("Drugs.BagOfDrugs.GiveOnRespawn", bagofDrugs_GiveOnRespawn);
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

