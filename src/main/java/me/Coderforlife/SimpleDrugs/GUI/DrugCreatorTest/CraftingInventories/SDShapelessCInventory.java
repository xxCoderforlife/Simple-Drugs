package me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.CraftingInventories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.CraftingComponent.DrugCraftingType;
import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.SDObjectType;
import net.md_5.bungee.api.ChatColor;

public class SDShapelessCInventory extends AbstractSDCInventory {

	public SDShapelessCInventory(String name, Map<Integer, ItemStack> i) {
		super(ChatColor.translateAlternateColorCodes('&', "&6&lCreate Shapeless Recipe"), name, i);
	}
	
	public void handleAccept(Player p) {
		if(allBlank()) return;
		if(getInventory().getItem(17) == null || getInventory().getItem(17).getType().equals(Material.AIR)) return;
		getAddons().getOptionValues().put("ResultMaterial", getInventory().getItem(17));
		getAddons().getOptionValues().put("Recipe", getRecipe());
		getAddons().getOptionValues().put("RecipeType", DrugCraftingType.SHAPELESS);
		close(p);
		if(getType().equals(SDObjectType.DRUG)) {
			Main.plugin.getDrugManager().addOrUpdateItem(getName(), getAddons());
		} else if(getType().equals(SDObjectType.CC)) {
			Main.plugin.getCraftingManager().addOrUpdateItem(getName(), getAddons());
		} else if(getType().equals(SDObjectType.SEED)) {
			Main.plugin.getDrugSeedManager().addOrUpdateItem(getName(), getAddons());
		}
		
	}

	public void setCraftingSlots() { return; }

	public List<ItemStack> getRecipe() {
		List<ItemStack> items = new ArrayList<>();
		
		items.add(isSlotNull(3) ? new ItemStack(Material.AIR) : getInventory().getItem(3));
		items.add(isSlotNull(4) ? new ItemStack(Material.AIR) : getInventory().getItem(4));
		items.add(isSlotNull(5) ? new ItemStack(Material.AIR) : getInventory().getItem(5));
		items.add(isSlotNull(12) ? new ItemStack(Material.AIR) : getInventory().getItem(12));
		items.add(isSlotNull(13) ? new ItemStack(Material.AIR) : getInventory().getItem(13));
		items.add(isSlotNull(14) ? new ItemStack(Material.AIR) : getInventory().getItem(14));
		items.add(isSlotNull(21) ? new ItemStack(Material.AIR) : getInventory().getItem(21));
		items.add(isSlotNull(22) ? new ItemStack(Material.AIR) : getInventory().getItem(22));
		items.add(isSlotNull(23) ? new ItemStack(Material.AIR) : getInventory().getItem(23));
		
		return items;
	}

}
