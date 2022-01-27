package me.Coderforlife.SimpleDrugs.Util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonFileInterpretter {

	private JsonObject json;
	
	public JsonFileInterpretter(JsonObject jo) {
		json = jo;
	}
	
	public JsonObject getJsonObject(String key) throws IllegalTypeError {
		if(!json.get(key).isJsonObject()) {
			throw new IllegalTypeError("JSON Key: " + key + " Must be JsonObject {}");
		}
		return json.getAsJsonObject(key);
	}
	
	public JsonArray getJsonArray(String key) throws IllegalTypeError {
		if(!json.get(key).isJsonArray()) {
			throw new IllegalTypeError("JSON Key: " + key + " Must be JsonArray []");
		}
		return json.get(key).getAsJsonArray();
	}
	
	public Boolean contains(String key) {
		return json.has(key);
	}
	
	public String getString(String key) throws IllegalTypeError {
		try {
			String s = json.get(key).getAsString();
			return s;
		} catch (Exception e) {
			throw new IllegalTypeError("JSON Key: " + key + " Must be a String Value");
		}
	}
	
	public Boolean getBoolean(String key) throws IllegalTypeError {
		try {
			Boolean s = json.get(key).getAsBoolean();
			return s;
		} catch (Exception e) {
			throw new IllegalTypeError("JSON Key: " + key + " Must be a Boolean Value");
		}
	}
	
	public Double getDouble(String key) throws IllegalTypeError {
		try {
			Double s = json.get(key).getAsDouble();
			return s;
		} catch (Exception e) {
			throw new IllegalTypeError("JSON Key: " + key + " Must be a Double Value");
		}
	}
	
	public Float getFloat(String key) throws IllegalTypeError {
		try {
			Float s = json.get(key).getAsFloat();
			return s;
		} catch (Exception e) {
			throw new IllegalTypeError("JSON Key: " + key + " Must be a Float Value");
		}
	}
	
	public Integer getInteger(String key) throws IllegalTypeError {
		try {
			Integer s = json.get(key).getAsInt();
			return s;
		} catch (Exception e) {
			throw new IllegalTypeError("JSON Key: " + key + " Must be an Integer Value");
		}
	}
	
}