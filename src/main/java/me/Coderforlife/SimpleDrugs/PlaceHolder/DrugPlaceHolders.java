package me.Coderforlife.SimpleDrugs.PlaceHolder;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class DrugPlaceHolders extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "drug";
    }

    @Override
    public @NotNull String getAuthor() {
        return "xxCoderforlife";
    }

    @Override
    public @NotNull String getVersion() {
        return "2.7";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        for(Drug drugs : Main.plugin.getDrugManager().getItems().values()) {
            if(params.equalsIgnoreCase(drugs.getName())) {
                return drugs.getDisplayName();
            }
        }
        return null;
    }

}
