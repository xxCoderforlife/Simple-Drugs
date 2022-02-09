package me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.InventoryAddons;
import me.Coderforlife.SimpleDrugs.Util.AbstractSDCraftableManager;
import me.Coderforlife.SimpleDrugs.Util.JsonFileInterpretter;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.CraftingComponentAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.ItemStackAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDFurnaceAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDShapedAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.Recipes.RecipeType.SDShapelessAdapter;
import net.md_5.bungee.api.ChatColor;

public class CCManager extends AbstractSDCraftableManager<CraftingComponent> {

	public CCManager() {
		super(new File(Main.plugin.getDataFolder() + File.separator + "Recipes" + File.separator + "DrugComponents"));
	}

	protected void registerTypeAdapters() {
		typeAdapters.put(SDShaped.class, new SDShapedAdapter());
		typeAdapters.put(SDShapeless.class, new SDShapelessAdapter());
		typeAdapters.put(SDFurnace.class, new SDFurnaceAdapter());
		typeAdapters.put(ItemStack.class, new ItemStackAdapter());
		typeAdapters.put(CraftingComponent.class, new CraftingComponentAdapter());
	}

	public void createFromJson(String fileName, JsonObject jo) {
		Gson gson = builder.create();
		CraftingComponent cc = gson.fromJson(jo, CraftingComponent.class);
		cc.setFile(fileName);
		addItem(cc.getName(), cc);
		cc.getRecipe().registerRecipe();
		saveFile(cc);
	}

	protected DrugLoadError canMake(String fileName, JsonObject jo) {
		DrugLoadError dle = new DrugLoadError();
    	JsonFileInterpretter config = new JsonFileInterpretter(jo);
    	
    	if(!config.contains("name")) {
    		dle.addError("§c[ERROR] JSON File missing 'name'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("type")) {
    		dle.addError("§c[ERROR] JSON File missing 'type'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("item")) {
    		dle.addError("§c[ERROR] JSON File missing 'item'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("recipe")) {
    		dle.addError("§c[ERROR] JSON File missing 'recipe'");
    		dle.unLoad();
    	}
    	
    	return dle;
	}

	public void addOrUpdateItem(String n, InventoryAddons ad) {
		n = ChatColor.translateAlternateColorCodes('&', n);
		String name = ChatColor.stripColor(n).replaceAll(" ", "_");
		
		ItemStack item = (ItemStack)ad.getOptionValues().get("ResultMaterial");
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(n);
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "Simple Drugs Crafting Component");
		im.setLore(lore);
		item.setItemMeta(im);
		
		CraftingComponent cc = new CraftingComponent(name, n, item);
		cc.setFile(new File(getMainFile(), name + ".json").getAbsolutePath());
		
		if(getItems().containsKey(name.toUpperCase())) getItems().remove(name.toUpperCase());
    	
		@SuppressWarnings("unchecked")
		SDRecipe sd = Main.plugin.getRecipeManager().loadRecipe(cc, (List<ItemStack>)ad.getOptionValues().get("Recipe"), (DrugCraftingType)ad.getOptionValues().get("RecipeType"));
		cc.setRecipe(sd);
		sd.registerRecipe();
		
    	addItem(name.toUpperCase(), cc);
    	saveFile(cc);
	}
	
}