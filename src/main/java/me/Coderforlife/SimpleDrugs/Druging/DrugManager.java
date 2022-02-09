package me.Coderforlife.SimpleDrugs.Druging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Druging.Util.DrugEffect;
import me.Coderforlife.SimpleDrugs.Druging.Util.DrugRecipe;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.InventoryAddons;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.InventoryPotionEffect;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.PotionEffectInventoryUtil;
import me.Coderforlife.SimpleDrugs.Util.AbstractSDCraftableManager;
import me.Coderforlife.SimpleDrugs.Util.JsonFileInterpretter;
import me.Coderforlife.SimpleDrugs.Util.Errors.DrugLoadError;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.DrugAdapter;
import me.Coderforlife.SimpleDrugs.Util.GsonAdapaters.DrugAdapters.DrugEffectAdapter;
import net.md_5.bungee.api.ChatColor;

public class DrugManager extends AbstractSDCraftableManager<Drug> {

	public DrugManager() {
		super(new File(Main.plugin.getDataFolder() + File.separator + "Drugs"));
		
		StringJoiner enabled = new StringJoiner(", ");
    	for(Drug d : getItems().values()) {
    		enabled.add(d.getName());
    	}
    	if(enabled.length() > 0) sendConsoleMessage("§6Enabled Drugs: §a" + enabled.toString().trim());
	}

	public void addOrUpdateItem(String n, InventoryAddons addons) {
		n = ChatColor.translateAlternateColorCodes('&', n);
    	String disName = n;
    	String name = ChatColor.stripColor(n).replaceAll(" ", "_");
    	ArrayList<DrugEffect> drugEffects = new ArrayList<>();
    	for(InventoryPotionEffect ipe : ((PotionEffectInventoryUtil)addons.getOptionValues().get("DrugEffects")).getPotionEffects()) {
    		drugEffects.add(new DrugEffect(ipe.getType(), ipe.getTime(), ipe.getIntensity()));
    	}
    	String permission = "drugs.use." + name.toLowerCase();
    	Double addLevel = (Double) addons.getOptionValues().get("AddictionLevel");
    	
    	Drug d = new Drug(name, disName, ((ItemStack)addons.getOptionValues().get("ResultMaterial")).getType(), drugEffects, permission, addLevel);
    	d.setFile(new File(getMainFile(), name + ".json").getAbsolutePath());
    	
    	if(getItems().containsKey(name.toUpperCase())) getItems().remove(name.toUpperCase());
    	
    	addItem(name.toUpperCase(), d);
    	saveFile(d);
    	
		@SuppressWarnings("unchecked")
		SDRecipe sd = Main.plugin.getRecipeManager().loadRecipe(d, (List<ItemStack>)addons.getOptionValues().get("Recipe"), (DrugCraftingType)addons.getOptionValues().get("RecipeType"));
		d.setRecipe(sd);
		sd.registerRecipe();
		
		DrugRecipe dr = new DrugRecipe(d, sd);
		dr.setFile(new File(Main.plugin.getDrugRecipeManager().getMainFile(), d.getName() + ".json").getAbsolutePath());
		
		Main.plugin.getDrugRecipeManager().updateRecipeFile(dr);
	}
	
	protected void registerTypeAdapters() {
		typeAdapters.put(Drug.class, new DrugAdapter());
		typeAdapters.put(DrugEffect.class, new DrugEffectAdapter());
	}

	public void createFromJson(String fileName, JsonObject jo) {
		Gson gson = builder.create();
		Drug cc = gson.fromJson(jo, Drug.class);
		cc.setFile(fileName);
		addItem(cc.getName().toUpperCase(), cc);
		saveFile(cc);
	}

	@Override
	public void removeItem(String name) {
		super.removeItem(name);
		
		Main.plugin.getDrugRecipeManager().removeItem(name);
		Main.plugin.getDrugSeedManager().removeItem(name);
	}
	
	protected DrugLoadError canMake(String fileName, JsonObject jo) {
		DrugLoadError dle = new DrugLoadError();
    	JsonFileInterpretter config = new JsonFileInterpretter(jo);
    	
    	if(!config.contains("name")) {
    		dle.addError("§c[ERROR] JSON File missing 'name'");
    		dle.unLoad();
    	}
    	
    	if(!config.contains("item")) {
    		dle.addError("§c[ERROR] JSON File missing 'item'");
    		dle.unLoad();
    	}
    	
    	return dle;
	}
	
	public Drug matchDrug(@Nullable ItemStack item) {
		if(isDrugItem(item)){
			for(Drug drug : getItems().values()) {
				if(item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(drug.getDisplayName())) {
					return drug;
				}
			}
		}
        return null;
    }
	
	public boolean isDrugItem(ItemStack item) {
		if(item == null){
			return false;
		}
        for(Drug d : getItems().values()) {
            if(item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(d.getDisplayName())) {
                return true;
            }
        }
        return false;
    }
	
}