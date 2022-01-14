package me.Coderforlife.Drugs.Settings;

import me.Coderforlife.Drugs.Main;

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

    static {
        setCheckForUpdate(plugin.drugsConfig.getBoolean("Drugs.CheckForUpdate"));
        setUpdateMessage(plugin.drugsConfig.getBoolean("Drugs.UpdateMessage"));
        setJoinMessage(plugin.drugsConfig.getBoolean("Drugs.JoinMessage"));
        setBagOfDrugs_CanMove(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.CanMove"));
        setBagOfDrugs_CanDrop(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.CanDrop"));
        setBagofDrugs_GiveOnRespawn(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnRespawn"));
        setBagOfDrugs_GiveOnJoin(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.GiveOnJoin"));
        setBagOfDrugs_DropOnDeath(plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.DropOnDeath"));
    }

    public static boolean CheckForUpdate() {
        return CheckForUpdate;
    }

    public static void setCheckForUpdate(boolean checkForUpdate) {
        CheckForUpdate = checkForUpdate;
        plugin.drugsConfig.set("Drugs.CheckForUpdate", checkForUpdate);
    }

    public static boolean UpdateMessage() {
        return UpdateMessage;
    }

    public static void setUpdateMessage(boolean updateMessage) {
        UpdateMessage = updateMessage;
        plugin.drugsConfig.set("Drugs.UpdateMessage", updateMessage);
    }

    public static boolean JoinMessage() {
        return JoinMessage;
    }

    public static void setJoinMessage(boolean joinMessage) {
        JoinMessage = joinMessage;
        plugin.drugsConfig.set("Drugs.JoinMessage", joinMessage);
    }

    public static boolean BagOfDrugs_CanMove() {
        return BagOfDrugs_CanMove;
    }

    public static void setBagOfDrugs_CanMove(boolean bagOfDrugs_CanMove) {
        BagOfDrugs_CanMove = bagOfDrugs_CanMove;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.CanMove", bagOfDrugs_CanMove);
    }

    public static boolean BagOfDrugs_CanDrop() {
        return BagOfDrugs_CanDrop;
    }

    public static void setBagOfDrugs_CanDrop(boolean bagOfDrugs_CanDrop) {
        BagOfDrugs_CanDrop = bagOfDrugs_CanDrop;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.CanDrop", bagOfDrugs_CanDrop);
    }

    public static boolean BagOfDrugs_GiveOnJoin() {
        return BagOfDrugs_GiveOnJoin;
    }

    public static void setBagOfDrugs_GiveOnJoin(boolean bagOfDrugs_GiveOnJoin) {
        BagOfDrugs_GiveOnJoin = bagOfDrugs_GiveOnJoin;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.GiveOnJoin", bagOfDrugs_GiveOnJoin);
    }

    public static boolean BagOfDrugs_DropOnDeath() {
        return BagOfDrugs_DropOnDeath;
    }

    public static void setBagOfDrugs_DropOnDeath(boolean bagOfDrugs_DropOnDeath) {
        BagOfDrugs_DropOnDeath = bagOfDrugs_DropOnDeath;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.DropOnDeath", bagOfDrugs_DropOnDeath);
    }

    public static boolean BagofDrugs_GiveOnRespawn() {
        return BagofDrugs_GiveOnRespawn;
    }

    public static void setBagofDrugs_GiveOnRespawn(boolean bagofDrugs_GiveOnRespawn) {
        BagofDrugs_GiveOnRespawn = bagofDrugs_GiveOnRespawn;
        plugin.drugsConfig.set("Drugs.BagOfDrugs.GiveOnRespawn", bagofDrugs_GiveOnRespawn);
    }
}

