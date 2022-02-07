package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util;

import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;

public abstract class PotionEffectSetterInventory extends InventoryUI {
	
	public PotionEffectSetterInventory(int size, String title) {
		super(size, title);
	}
	
	public abstract PotionEffectInventoryUtil getPotionEffects();
	public abstract void updateEffectsButton();
	public abstract void updateAddictionButton();
	public abstract void setAddLevel(double b);
}