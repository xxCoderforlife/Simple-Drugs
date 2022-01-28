package me.Coderforlife.SimpleDrugs.Crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.google.gson.JsonArray;

import me.Coderforlife.SimpleDrugs.Main;

public class CraftingComponent {

	private String name;
	private String fileName;
	private ItemStack result;
	private DrugCraftingType type;
	private JsonArray materials;
	
	public CraftingComponent(String s, String fn, ItemStack is, DrugCraftingType t, JsonArray m) {
		name = s;
		fileName = fn;
		result = is;
		type = t;
		materials = m;
	}
	
	public DrugCraftingType getType() {
		return type;
	}
	
	public ItemStack getStack() {
		return result;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public JsonArray getMaterials() {
		return materials;
	}
	
	public void registerRecipe() {
		switch(type) {
		case FURNACE:
			break;
		case SHAPED:
			ShapedRecipe shaped = new ShapedRecipe(new NamespacedKey(Main.plugin, "drugs_" + name), result);
			shaped.shape("ABC", "DEF", "GHI");
			List<RecipeChoice> mats = loadMaterialsForCrafting(fileName, materials);
			
			if(mats == null) {
				return;
			}
			
			shaped.setIngredient('A', mats.get(0));
			shaped.setIngredient('B', mats.get(1));
			shaped.setIngredient('C', mats.get(2));
			shaped.setIngredient('D', mats.get(3));
			shaped.setIngredient('E', mats.get(4));
			shaped.setIngredient('F', mats.get(5));
			shaped.setIngredient('G', mats.get(6));
			shaped.setIngredient('H', mats.get(7));
			shaped.setIngredient('I', mats.get(8));
			
			Bukkit.getServer().addRecipe(shaped);
			break;
		case SHAPELESS:
			ShapelessRecipe shapeless = new ShapelessRecipe(new NamespacedKey(Main.plugin, "drugs_" + name), result);
			List<RecipeChoice> recipeChoices = loadMaterialsForCrafting(fileName, materials);
			
			if(recipeChoices == null) {
				return;
			}
			
			recipeChoices.forEach(e -> {
				shapeless.addIngredient(e);
			});
			
			Bukkit.getServer().addRecipe(shapeless);
			break;
		}
	}
	
	private List<RecipeChoice> loadMaterialsForCrafting(String fileName, JsonArray ja) {
		List<RecipeChoice> materials = new ArrayList<>();
	    
		for(int i = 0; i < ja.size(); i++) {
    		Material m = Material.getMaterial(ja.get(i).getAsString().toUpperCase());
    		if(m == null) {
    			CraftingComponent cc = Main.plugin.getCraftingManager().getByName(ja.get(i).getAsString().toUpperCase());
    			if(cc == null) {
    				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
    				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Material: §7" + ja.get(i).getAsString().toUpperCase() + " §cdoes not exist as a Minecraft Material or Custom Crafting Component");
    				Bukkit.getConsoleSender().sendMessage("§c[ERROR] Skipping Recipe");
    				return null;
    			}
    			
    			materials.add(new RecipeChoice.ExactChoice(cc.getStack()));
    		} else {
    			materials.add(new RecipeChoice.ExactChoice(new ItemStack(m)));
    		}
    	}
		
    	if(materials.size() > 9) {
    		Bukkit.getConsoleSender().sendMessage("§c[ERROR] Error in: §7" + fileName);
			Bukkit.getConsoleSender().sendMessage("§c[ERROR] Materials added cannot be above 9");
    		return null;
    	}
    	
    	return materials;
	}
	
}