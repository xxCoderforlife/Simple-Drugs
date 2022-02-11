package me.Coderforlife.SimpleDrugs.GUI.DrugCreator.CraftingInventories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreator.Util.SDObjectType;
import me.Coderforlife.SimpleDrugs.Util.CCMaterialConverter;
import net.md_5.bungee.api.ChatColor;

public class SDFurnaceCInventory extends AbstractSDCInventory {

	public SDFurnaceCInventory(String name, Map<Integer, ItemStack> i) {
		super(ChatColor.translateAlternateColorCodes('&', "&6&lCreate Furnace Recipe"), name, i);
	}

	public void handleAccept(Player p) {
		if(getInventory().getItem(4) == null || getInventory().getItem(4).getType().equals(Material.AIR)) return;
		if(getInventory().getItem(17) == null || getInventory().getItem(17).getType().equals(Material.AIR)) return;
		getAddons().getOptionValues().put("ResultMaterial", getInventory().getItem(17));
		getAddons().getOptionValues().put("Recipe", getRecipe());
		getAddons().getOptionValues().put("RecipeType", DrugCraftingType.FURNACE);
		close(p);
		if(getType().equals(SDObjectType.DRUG)) {
			Main.plugin.getDrugManager().addOrUpdateItem(getName(), getAddons());
		} else if(getType().equals(SDObjectType.CC)) {
			Main.plugin.getCraftingManager().addOrUpdateItem(getName(), getAddons());
		} else if(getType().equals(SDObjectType.SEED)) {
			Main.plugin.getDrugSeedManager().addOrUpdateItem(getName(), getAddons());
		}
	}

	public void setCraftingSlots() {
		addNullItem(3);
		addNullItem(5);
		addNullItem(12);
		addNullItem(13);
		addNullItem(14);
		addNullItem(21);
		addNullItem(23);
	}

	public List<String> getRecipe() {
		List<String> materials = new ArrayList<>();
		materials.add(CCMaterialConverter.getCCOrMaterial(getInventory().getItem(4)));
		return materials;
	}
	
}