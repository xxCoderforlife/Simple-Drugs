package me.Coderforlife.SimpleDrugs.Settings;

import me.Coderforlife.SimpleDrugs.Main;

public class Settings {

    private static boolean CheckForUpdate;
    private static boolean UpdateMessage;
    private static boolean JoinMessage;
    private static boolean BagOfDrugs_CanMove;
    private static boolean BagOfDrugs_CanDrop;
    private static boolean BagOfDrugs_GiveOnJoin;
    private static boolean BagOfDrugs_DropOnDeath;
    private static boolean BagofDrugs_GiveOnRespawn;

    private static Main plugin = (Main) Main.plugin;

     {
        setCheckForUpdate(plugin.drugsConfig.getBoolean("Drugs.CheckForUpdate"));
        setUpdateMessage(plugin.drugsConfig.getBoolean("Drugs.UpdateMessage"));
        setJoinMessage(plugin.drugsConfig.getBoolean("Drugs.JoinMessage"));
        setBagOfDrugs_CanMove(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.CanMove"));
        setBagOfDrugs_CanDrop(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.CanDrop"));
        setBagofDrugs_GiveOnRespawn(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnRespawn"));
        setBagOfDrugs_GiveOnJoin(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnJoin"));
        setBagOfDrugs_DropOnDeath(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.DropOnDeath"));

    }

    public boolean CheckForUpdate() {
        return CheckForUpdate;
    }

    public void setCheckForUpdate(boolean checkForUpdate) {
        CheckForUpdate = checkForUpdate;
        plugin.drugsConfig.set("Drugs.CheckForUpdate", checkForUpdate);
    }

    public boolean UpdateMessage() {
        return UpdateMessage;
    }

    public void setUpdateMessage(boolean updateMessage) {
        UpdateMessage = updateMessage;
        plugin.drugsConfig.set("Drugs.UpdateMessage", updateMessage);
    }

    public boolean JoinMessage() {
        return JoinMessage;
    }

    public void setJoinMessage(boolean joinMessage) {
        JoinMessage = joinMessage;
        plugin.drugsConfig.set("Drugs.JoinMessage", joinMessage);
    }

    public boolean BagOfDrugs_CanMove() {
        return BagOfDrugs_CanMove;
    }

    public void setBagOfDrugs_CanMove(boolean bagOfDrugs_CanMove) {
        BagOfDrugs_CanMove = bagOfDrugs_CanMove;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.CanMove", bagOfDrugs_CanMove);
    }

    public boolean BagOfDrugs_CanDrop() {
        return BagOfDrugs_CanDrop;
    }

    public void setBagOfDrugs_CanDrop(boolean bagOfDrugs_CanDrop) {
        BagOfDrugs_CanDrop = bagOfDrugs_CanDrop;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.CanDrop", bagOfDrugs_CanDrop);
    }

    public boolean BagOfDrugs_GiveOnJoin() {
        return BagOfDrugs_GiveOnJoin;
    }

    public void setBagOfDrugs_GiveOnJoin(boolean bagOfDrugs_GiveOnJoin) {
        BagOfDrugs_GiveOnJoin = bagOfDrugs_GiveOnJoin;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.GiveOnJoin", bagOfDrugs_GiveOnJoin);
    }

    public boolean BagOfDrugs_DropOnDeath() {
        return BagOfDrugs_DropOnDeath;
    }

    public void setBagOfDrugs_DropOnDeath(boolean bagOfDrugs_DropOnDeath) {
        BagOfDrugs_DropOnDeath = bagOfDrugs_DropOnDeath;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.DropOnDeath", bagOfDrugs_DropOnDeath);
    }

    public boolean BagofDrugs_GiveOnRespawn() {
        return BagofDrugs_GiveOnRespawn;
    }

    public void setBagofDrugs_GiveOnRespawn(boolean bagofDrugs_GiveOnRespawn) {
        BagofDrugs_GiveOnRespawn = bagofDrugs_GiveOnRespawn;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.GiveOnRespawn", bagofDrugs_GiveOnRespawn);
    }
}

