package me.Coderforlife.SimpleDrugs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Settings {

    private boolean CheckForUpdate;
    private boolean UpdateMessage;
    private boolean JoinMessage;
    private int cooldown;
    private boolean BagOfDrugs_CanMove;
    private boolean BagOfDrugs_CanDrop;
    private boolean BagOfDrugs_GiveOnJoin;
    private boolean BagOfDrugs_DropOnDeath;
    private boolean BagOfDrugs_GiveOnRespawn;

    private File drugsConfigFile;
    private FileConfiguration drugsConfig;

    public void setup() {
        setupConfig();
        setCheckForUpdate(drugsConfig.getBoolean("Drugs.CheckForUpdate"));
        setUpdateMessage(drugsConfig.getBoolean("Drugs.UpdateMessage"));
        setJoinMessage(drugsConfig.getBoolean("Drugs.JoinMessage"));
        setCooldown(drugsConfig.getInt("Drugs.Cooldown"));
        setBagOfDrugs_CanMove(drugsConfig.getBoolean("Drugs.BagOfDrugs.CanMove"));
        setBagOfDrugs_CanDrop(drugsConfig.getBoolean("Drugs.BagOfDrugs.CanDrop"));
        setBagOfDrugs_GiveOnRespawn(drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnRespawn"));
        setBagOfDrugs_GiveOnJoin(drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnJoin"));
        setBagOfDrugs_DropOnDeath(drugsConfig.getBoolean("Drugs.BagOfDrugs.DropOnDeath"));
    }

    public Boolean isCheckForUpdate(){
        return CheckForUpdate;
    }
    public void setCheckForUpdate(boolean bol) {
        CheckForUpdate = bol;
        drugsConfig.set("Drugs.CheckForUpdate", bol);
    }

    public Boolean isUpdateMessage(){
        return UpdateMessage;
    }
    public void setUpdateMessage(boolean bol) {
        UpdateMessage = bol;
        drugsConfig.set("Drugs.UpdateMessage", bol);
    }
    public Boolean isJoinMessage(){
        return JoinMessage;
    }
    public void setJoinMessage(boolean bol) {
        JoinMessage = bol;
        drugsConfig.set("Drugs.JoinMessage", bol);
    }
    public int getCooldown(){
        return cooldown;
    }
    public void setCooldown(int i) {
        cooldown = i;
        drugsConfig.set("Drugs.Cooldown", i);
    }

    public Boolean isBagOfDrugs_CanMove(){
        return BagOfDrugs_CanMove;
    }
    public void setBagOfDrugs_CanMove(boolean bol) {
        BagOfDrugs_CanMove = bol;
        drugsConfig.set("Drugs.BagOfDrugs.CanMove", bol);
    }
    public Boolean isBagOfDrugs_CanDrop(){
        return BagOfDrugs_CanDrop;
    }
    public void setBagOfDrugs_CanDrop(boolean bol) {
        BagOfDrugs_CanDrop = bol;
        drugsConfig.set("Drugs.BagOfDrugs.CanDrop", bol);
    }
    public Boolean isBagOfDrugs_GiveOnJoin(){
        return BagOfDrugs_GiveOnJoin;
    }
    public void setBagOfDrugs_GiveOnJoin(boolean bol) {
        BagOfDrugs_GiveOnJoin = bol;
        drugsConfig.set("Drugs.BagOfDrugs.GiveOnJoin", bol);
    }
    public Boolean isBagOfDrugs_DropOnDeath(){
        return BagOfDrugs_DropOnDeath;
    }
    public void setBagOfDrugs_DropOnDeath(boolean bol) {
        BagOfDrugs_DropOnDeath = bol;
        drugsConfig.set("Drugs.BagOfDrugs.DropOnDeath", bol);
    }
    public Boolean isBagOfDrugs_GiveOnRespawn(){
        return BagOfDrugs_GiveOnRespawn;
    }
    public void setBagOfDrugs_GiveOnRespawn(boolean bol) {
        BagOfDrugs_GiveOnRespawn = bol;
        drugsConfig.set("Drugs.BagOfDrugs.GiveOnRespawn", bol);
    }
    
    private void setupConfig() {
    	drugsConfigFile = new File(Main.plugin.getDataFolder(), "config.yml");
        if(!drugsConfigFile.exists()) {
            drugsConfigFile.getParentFile().mkdir();
            Main.plugin.saveResource("config.yml", false);
        }
        drugsConfig = new YamlConfiguration();
        try {
            drugsConfig.load(drugsConfigFile);
        } catch(IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            drugsConfig.save(drugsConfigFile);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

