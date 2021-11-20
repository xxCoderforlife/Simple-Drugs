package me.Coderforlife.Drugs.PlaceHolder;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import me.Coderforlife.Drugs.Drug;
import me.Coderforlife.Drugs.Drugs;
import me.Coderforlife.Drugs.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class DrugPlaceHolders extends PlaceholderExpansion {

	@SuppressWarnings("unused")
	private final Main plugin;
	Drugs D;

	public DrugPlaceHolders(Main plugin, Drugs D) {
		this.plugin = plugin;
		this.D = D;
	}

	@Override
	public @NotNull String getIdentifier() {
		// TODO Auto-generated method stub
		return "drug";
	}

	@Override
	public @NotNull String getAuthor() {
		// TODO Auto-generated method stub
		return "xxCoderforlife";
	}

	@Override
	public @NotNull String getVersion() {
		// TODO Auto-generated method stub
		return "2.7";
	}

	@Override
	public boolean persist() {
		return true; // This is required or else PlaceholderAPI will unregister the Expansion on
						// reload
	}

	@Override
	public String onRequest(OfflinePlayer player, String params) {
		for (Drug drugs : D.getAllDrugs()) {
			if (params.equalsIgnoreCase(drugs.getName())) {
				return drugs.getDisplayName();
			}
		}
		return null; // Placeholder is unknown by the Expansion
	}

}
