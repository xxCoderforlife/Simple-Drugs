package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util;

import org.bukkit.potion.PotionEffectType;

public class InventoryPotionEffect {

	private PotionEffectType type;
	private Integer time;
	private Integer intensity;
	
	public InventoryPotionEffect(PotionEffectType pet, Integer t, Integer i) {
		type = pet;
		time = t;
		intensity = i;
	}
	
	public PotionEffectType getType() {
		return type;
	}
	
	public Integer getTime() {
		return time;
	}
	
	public Integer getIntensity() {
		return intensity;
	}
	
}