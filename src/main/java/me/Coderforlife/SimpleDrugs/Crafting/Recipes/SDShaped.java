package me.Coderforlife.SimpleDrugs.Crafting.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import me.Coderforlife.SimpleDrugs.Main;

public class SDShaped extends SDRecipe {
	
	public SDShaped(String name, ItemStack r) {
		super(name, r);
	}
	
	public void addItemStack(ItemStack i) {
		items.add(i);
	}

	@Override
	public void registerRecipe() {
		ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, "drugs_crafting_" + getName()), getResult());
		r.shape("ABC", "DEF", "GHI");
		r.setIngredient('A', new RecipeChoice.ExactChoice(items.get(0)));
		r.setIngredient('B', new RecipeChoice.ExactChoice(items.get(1)));
		r.setIngredient('C', new RecipeChoice.ExactChoice(items.get(2)));
		r.setIngredient('D', new RecipeChoice.ExactChoice(items.get(3)));
		r.setIngredient('E', new RecipeChoice.ExactChoice(items.get(4)));
		r.setIngredient('F', new RecipeChoice.ExactChoice(items.get(5)));
		r.setIngredient('G', new RecipeChoice.ExactChoice(items.get(6)));
		r.setIngredient('H', new RecipeChoice.ExactChoice(items.get(7)));
		r.setIngredient('I', new RecipeChoice.ExactChoice(items.get(8)));
		
		Bukkit.getServer().addRecipe(r);
		Main.plugin.getRecipeManager().addRecipe(this);
	}
	
}