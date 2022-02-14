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
import java.util.StringJoiner;

import org.bukkit.Bukkit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import me.Coderforlife.SimpleDrugs.Crafting.SDCraftableItem;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.InventoryAddons;
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
	}
	
	public E getItem(String name) {
		String EncodedString = CCMaterialConverter.createUpperCase(name);
		return items.get(EncodedString);
	}
	
	public void addItem(String name, E item) {
		String EncodedString = CCMaterialConverter.createUpperCase(name);
		items.put(EncodedString, item);
	}
	
	public Map<String, E> getItems() {
		return items;
	}
	
	public void removeItem(String name) {
		if(items.containsKey(CCMaterialConverter.createUpperCase(name))) {
			File f = new File(items.get(CCMaterialConverter.createUpperCase(name)).getFile());
			if(f.exists()) f.delete();
		}
		items.remove(CCMaterialConverter.createUpperCase(name));
	}
	
	public File getMainFile() {
		return mainFile;
	}
	
	protected void saveFile(E clazz) {
		Gson gson = builder.create();
		String s = gson.toJson(clazz, clazz.getClass());
		
		File f = new File(clazz.getFile());
		if(f.exists()) f.delete();
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			Writer writer = new FileWriter(clazz.getFile());
			writer.write(s);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFiles() {
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
		
		StringJoiner enabled = new StringJoiner(", ");
    	for(E e : getItems().values()) {
    		enabled.add(CCMaterialConverter.createUpperCase(e.getName()));
    	}
    	if(enabled.length() > 0) sendConsoleMessage("§6Enabled: §a" + enabled.toString().trim());
	}
	
	public abstract void addOrUpdateItem(String name, InventoryAddons ad);
	protected abstract void registerTypeAdapters();
	public abstract void createFromJson(String fileName, JsonObject jo);
	protected abstract DrugLoadError canMake(String fileName, JsonObject jo);
	
	protected void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
	
}