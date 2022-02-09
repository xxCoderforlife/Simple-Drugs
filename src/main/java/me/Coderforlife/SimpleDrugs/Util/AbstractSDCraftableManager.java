package me.Coderforlife.SimpleDrugs.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.InventoryAddons;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;

public abstract class AbstractSDCraftableManager<E extends SDCraftableItem> {

	protected Map<Type, Object> typeAdapters = new HashMap<Type, Object>();
	private Map<String, E> items = new HashMap<String, E>();
	private File mainFile;
	protected GsonBuilder builder;
	
	public AbstractSDCraftableManager(File mF) {
		mainFile = mF;
		if(!mainFile.exists()) mainFile.mkdirs();
		registerTypeAdapters();
		builder = new GsonBuilder().setPrettyPrinting();
		
		typeAdapters.forEach((k, v) -> {
			builder.registerTypeAdapter(k, v);
		});
		
		loadFiles();
	}
	
	public E getItem(String name) {
		return items.get(name.toUpperCase());
	}
	
	public void addItem(String name, E item) {
		items.put(name.replaceAll(" ", "_").toUpperCase(), item);
	}
	
	public Map<String, E> getItems() {
		return items;
	}
	
	public void removeItem(String name) {
		if(items.containsKey(name.replaceAll(" ", "_").toUpperCase())) {
			File f = new File(items.get(name.replaceAll(" ", "_").toUpperCase()).getFile());
			if(f.exists()) f.delete();
		}
		items.remove(name);
	}
	
	public File getMainFile() {
		return mainFile;
	}
	
	protected void saveFile(E clazz) {
		Gson gson = builder.create();
		String s = gson.toJson(clazz, clazz.getClass());
		try {
			Writer writer = new FileWriter(clazz.getFile());
			writer.write(s);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadFiles() {
		for(File f : getMainFile().listFiles()) {
			if(f.getName().endsWith(".json")) {
				try {
					JsonObject obj = new Gson().fromJson(new FileReader(f), JsonObject.class);
					DrugLoadError dle = canMake(f.getName(), obj);
					
					if(!dle.canLoad()) {
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + f.getName());
						dle.printAllErrors();
						Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Crafting Component");
						continue;
					}
					
					createFromJson(f.getAbsolutePath(), obj);
				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public abstract void addOrUpdateItem(String name, InventoryAddons ad);
	protected abstract void registerTypeAdapters();
	public abstract void createFromJson(String fileName, JsonObject jo);
	protected abstract DrugLoadError canMake(String fileName, JsonObject jo);
	
	
	protected void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
	
}