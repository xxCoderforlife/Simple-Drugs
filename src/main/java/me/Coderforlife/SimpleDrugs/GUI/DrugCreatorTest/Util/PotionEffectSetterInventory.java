package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Crafting.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.Framework.InventoryUI;

public abstract class PotionEffectSetterInventory extends InventoryUI {
	
	public PotionEffectSetterInventory(int size, String title) {
		super(size, title);
	}
	
	public abstract PotionEffectInventoryUtil getPotionEffects();
	public abstract DrugCraftingType getRecipeType();
	public abstract void updateEffectsButton();
	public abstract void updateAddictionButton();
	public abstract void setAddLevel(double b);
	public abstract double getAddLevel();
	public abstract List<ItemStack> getRecipe();
	
	protected boolean isSlotNull(int i) {
		return getInventory().getItem(i) == null || getInventory().getItem(i).getType().equals(Material.AIR);
	}
}