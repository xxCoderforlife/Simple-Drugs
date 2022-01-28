package me.Coderforlife.SimpleDrugs.DrugPlants;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Util.CustomBlockData;

public class PlantItemListener implements Listener {

	@EventHandler
	public void onPlant(PlayerInteractEvent e) {
		// Make sure they are using their main hand and right clicking on a block with an item
		if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		if(!e.getHand().equals(EquipmentSlot.HAND)) return;
		if(!e.hasItem()) return;
		
		// Make sure the item is not air
		ItemStack is = e.getPlayer().getInventory().getItemInMainHand();
		if(is == null) return;
		
		// get the item meta and ensure that all values are present
		ItemMeta im = is.getItemMeta();
		if(im == null) return;
		PersistentDataContainer pdc = im.getPersistentDataContainer();
		if(!pdc.has(Main.plugin.getDrugMain(), PersistentDataType.BYTE)) return;
		if(pdc.get(Main.plugin.getDrugMain(), PersistentDataType.BYTE) != (byte)1) return;
		if(!pdc.has(Main.plugin.getDrugPlantedOn(), PersistentDataType.STRING)) return;
		if(!pdc.has(Main.plugin.getDrugKey(), PersistentDataType.STRING)) return;
		if(!pdc.has(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING)) return;
		if(!pdc.has(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER)) return;
		
		Material m = Material.valueOf(pdc.get(Main.plugin.getDrugPlantedOn(), PersistentDataType.STRING));
		if(!e.getClickedBlock().getType().equals(m)) return;
		
		is.setAmount(is.getAmount() - 1);
		Block b = e.getClickedBlock().getLocation().add(0, 1, 0).getBlock();
		b.setType(Material.WHEAT);
		PersistentDataContainer bPDC = new CustomBlockData(b, Main.plugin);
		bPDC.set(Main.plugin.getDrugMain(), PersistentDataType.BYTE, (byte)1);
		bPDC.set(Main.plugin.getDrugKey(), PersistentDataType.STRING, pdc.get(Main.plugin.getDrugKey(), PersistentDataType.STRING));
		bPDC.set(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER, pdc.get(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER));
		bPDC.set(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING, pdc.get(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING));
	}
	
	@EventHandler
	public void onTrample(PlayerInteractEvent e) {
		if(!e.getAction().equals(Action.PHYSICAL)) return;
		if(!e.getClickedBlock().getType().equals(Material.FARMLAND)) return;
		if(!e.getClickedBlock().getLocation().add(0, 1, 0).getBlock().getType().equals(Material.WHEAT)) return;
		
		Block b = e.getClickedBlock().getLocation().add(0, 1, 0).getBlock();
		CustomBlockData cbd = new CustomBlockData(b, Main.plugin);
		PersistentDataContainer bPDC = cbd;
		if(!bPDC.has(Main.plugin.getDrugMain(), PersistentDataType.BYTE)) return;
		if(bPDC.get(Main.plugin.getDrugMain(), PersistentDataType.BYTE) != (byte)1) return;
		if(!bPDC.has(Main.plugin.getDrugKey(), PersistentDataType.STRING)) return;
		if(!bPDC.has(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER)) return;
		if(!bPDC.has(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING)) return;
		
		Ageable age = (Ageable)b.getBlockData();
		Drug d = Main.plugin.getDrugManager().getDrug(bPDC.get(Main.plugin.getDrugKey(), PersistentDataType.STRING).toUpperCase());
		if(age.getAge() >= age.getMaximumAge()) {
			ItemStack i = d.getItem();
			i.setAmount(bPDC.get(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER));
			e.getPlayer().getWorld().dropItemNaturally(b.getLocation(), i);
		}
		
		ItemStack seed = Main.plugin.getDrugManager().getItemStackFromDrug(d);
		Material m = e.getClickedBlock().getType();
		Integer amountHarvest = bPDC.get(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER);
		
		DrugPlantItem cpi = new DrugPlantItem(d, seed, m, amountHarvest);
		e.getPlayer().getWorld().dropItemNaturally(b.getLocation(), cpi.makeItem());
		
		b.setType(Material.AIR);
		cbd.removeBlock(b);
	}
	
	@EventHandler
	public void onHarvest(PlayerInteractEvent e) {
		if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		if(!e.getHand().equals(EquipmentSlot.HAND)) return;
		if(!(e.getClickedBlock().getBlockData() instanceof Ageable)) return;
		if(!e.getClickedBlock().getType().equals(Material.WHEAT)) return;
		
		PersistentDataContainer bPDC = new CustomBlockData(e.getClickedBlock(), Main.plugin);
		if(!bPDC.has(Main.plugin.getDrugMain(), PersistentDataType.BYTE)) return;
		if(bPDC.get(Main.plugin.getDrugMain(), PersistentDataType.BYTE) != (byte)1) return;
		if(!bPDC.has(Main.plugin.getDrugKey(), PersistentDataType.STRING)) return;
		if(!bPDC.has(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER)) return;
		if(!bPDC.has(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING)) return;
		
		Ageable age = (Ageable)e.getClickedBlock().getBlockData();
		if(age.getAge() < age.getMaximumAge()) return;
		age.setAge(0);
		e.getClickedBlock().setBlockData(age);
		Drug d = Main.plugin.getDrugManager().getDrug(bPDC.get(Main.plugin.getDrugKey(), PersistentDataType.STRING).toUpperCase());
		ItemStack i = d.getItem();
		i.setAmount(bPDC.get(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER));
		e.getPlayer().getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), i);
	}
	
	@EventHandler
	public void onBreakSoil(BlockBreakEvent e) {
		if(!e.getBlock().getType().equals(Material.FARMLAND)) return;
		if(!e.getBlock().getLocation().add(0, 1, 0).getBlock().getType().equals(Material.WHEAT)) return;
		Block b = e.getBlock().getLocation().add(0, 1, 0).getBlock();
		CustomBlockData cbd = new CustomBlockData(b, Main.plugin);
		PersistentDataContainer bPDC = cbd;
		if(!bPDC.has(Main.plugin.getDrugMain(), PersistentDataType.BYTE)) return;
		if(bPDC.get(Main.plugin.getDrugMain(), PersistentDataType.BYTE) != (byte)1) return;
		if(!bPDC.has(Main.plugin.getDrugKey(), PersistentDataType.STRING)) return;
		if(!bPDC.has(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER)) return;
		if(!bPDC.has(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING)) return;
		
		Ageable age = (Ageable)b.getBlockData();
		Drug d = Main.plugin.getDrugManager().getDrug(bPDC.get(Main.plugin.getDrugKey(), PersistentDataType.STRING).toUpperCase());
		if(age.getAge() >= age.getMaximumAge()) {
			ItemStack i = d.getItem();
			i.setAmount(bPDC.get(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER));
			e.getPlayer().getWorld().dropItemNaturally(b.getLocation(), i);
		}
		
		ItemStack seed = Main.plugin.getDrugManager().getItemStackFromDrug(d);
		Material m = e.getBlock().getLocation().subtract(0, 1, 0).getBlock().getType();
		Integer amountHarvest = bPDC.get(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER);
		
		DrugPlantItem cpi = new DrugPlantItem(d, seed, m, amountHarvest);
		e.getPlayer().getWorld().dropItemNaturally(b.getLocation(), cpi.makeItem());
		cbd.removeBlock(b);
		b.setType(Material.AIR);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(!e.getBlock().getType().equals(Material.WHEAT)) return;
		CustomBlockData cbd = new CustomBlockData(e.getBlock(), Main.plugin);
		PersistentDataContainer bPDC = cbd;
		if(!bPDC.has(Main.plugin.getDrugMain(), PersistentDataType.BYTE)) return;
		if(bPDC.get(Main.plugin.getDrugMain(), PersistentDataType.BYTE) != (byte)1) return;
		if(!bPDC.has(Main.plugin.getDrugKey(), PersistentDataType.STRING)) return;
		if(!bPDC.has(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER)) return;
		if(!bPDC.has(Main.plugin.getDrugSeedKey(), PersistentDataType.STRING)) return;
		e.setDropItems(false);
		
		Ageable age = (Ageable)e.getBlock().getBlockData();
		Drug d = Main.plugin.getDrugManager().getDrug(bPDC.get(Main.plugin.getDrugKey(), PersistentDataType.STRING).toUpperCase());
		if(age.getAge() >= age.getMaximumAge()) {
			ItemStack i = d.getItem();
			i.setAmount(bPDC.get(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER));
			e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
		}
		
		ItemStack seed = Main.plugin.getDrugManager().getItemStackFromDrug(d);
		Material m = e.getBlock().getLocation().subtract(0, 1, 0).getBlock().getType();
		Integer amountHarvest = bPDC.get(Main.plugin.getDrugHarvestAmount(), PersistentDataType.INTEGER);
		
		DrugPlantItem cpi = new DrugPlantItem(d, seed, m, amountHarvest);
		e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), cpi.makeItem());
		cbd.removeBlock(e.getBlock());
	}
	
}