package me.Coderforlife.SimpleDrugs.Util.Errors;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

public class DrugLoadError {

	private boolean loaded = true;
	private List<String> loadErrors = new ArrayList<>();
	
	public void unLoad() {
		loaded = false;
	}
	
	public Boolean canLoad() {
		return loaded;
	}
	
	public void addError(String s) {
		loadErrors.add(s);
	}
	
	public void printAllErrors() {
		for(String s : loadErrors) {
			Bukkit.getConsoleSender().sendMessage(s);
		}
	}
	
}