package me.Coderforlife.SimpleDrugs.PlaceHolder;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import me.Coderforlife.SimpleDrugs.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class DrugPlaceHolders extends PlaceholderExpansion {

	@SuppressWarnings("unused")
	private final Main plugin;

	public DrugPlaceHolders(Main plugin) {
		this.plugin = plugin;
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
		for (Drug drugs : Drug.getallDrugs()) {
			if (params.equalsIgnoreCase(drugs.getName())) {
				return drugs.getDisplayname();
			}
		}
		return null; // Placeholder is unknown by the Expansion
	}

}
