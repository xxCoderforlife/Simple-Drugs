package me.Coderforlife.SimpleDrugs.Crafting.Recipes.Brewing;

import org.bukkit.Material;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;

public class BrewingRecipe extends SDRecipe {
	
	private ItemStack ingredient;
	private ItemStack input;
	private ItemStack fuel;
	private int fuelSet;
	private int fuelCharge;
	private BrewAction action;
	private BrewClock clock;
	private int timeToBrew;
	
	public BrewingRecipe(String n, ItemStack result, ItemStack in, ItemStack f, ItemStack inp, int fs, int fc, int fuelTime) {
		super(n, result);
		ingredient = in;
		input = inp;
		fuel = f;
		fuelSet = fs;
		fuelCharge = fc;
		timeToBrew = fuelTime;
		
		action = new BrewAction() {
			@Override
			public void Brew(BrewerInventory inv, int slot, ItemStack stack, ItemStack ing) {
				if(!stack.isSimilar(input)) return;
				if(ing.isSimilar(ingredient)) {
					inv.setItem(slot, getResult());
				}
			}
        };
	}
	
	public void startBrewing(BrewerInventory inv, int time) {
		clock = new BrewClock(this, inv, time);
	}
	
	public ItemStack getInput() {
		return input;
	}
	
	@Override
	public void registerRecipe() {
		Main.plugin.getRecipeManager().addBrewingRecipe(this);
	}
	
	public ItemStack getIngredient() {
		return ingredient;
	}
	
	public int getTimeToBrew() {
		return timeToBrew;
	}
	
	public ItemStack getFuel() {
		return fuel;
	}
	
	public int getFuelSet() {
		return fuelSet;
	}
	
	public int getFuelCharge() {
		return fuelCharge;
	}
	
	public BrewAction getAction() {
		return action;
	}
	
	public BrewClock getClock() {
		return clock;
	}
	
	public class BrewClock extends BukkitRunnable {
		
		private BrewingRecipe brewingRecipe;
		private BrewerInventory inventory;
		private int timeToCook;
		private ItemStack[] before;
		private ItemStack[] after;
		private BrewingStand stand;
		
		// Create constructor
		public BrewClock(BrewingRecipe br, BrewerInventory bi, int time) {
			brewingRecipe = br;
			inventory = bi;
			timeToCook = time;
			before = bi.getContents();
			stand = bi.getHolder();
			runTaskTimer(Main.plugin, 0L, 1L);
		}
		
		@Override
		public void run() {
			if(timeToCook == 0) {
				
				// Make sure to decrement ingredients or null if the ingredient is equal to 1
				if(inventory.getIngredient().getAmount() > 1) {
					ItemStack is = inventory.getIngredient();
					is.setAmount(inventory.getIngredient().getAmount() - 1);
					inventory.setIngredient(is);
				} else {
					inventory.setIngredient(new ItemStack(Material.AIR));
				}
				
				ItemStack newFuel = brewingRecipe.getFuel();
				
				if(brewingRecipe.getFuel() != null && brewingRecipe.getFuel().getType() != Material.AIR && brewingRecipe.getFuel().getAmount() > 0) {
					int count = 0;
					
					// Ensure that we take all fuel items and add that to the stands fuel charge
					while(inventory.getFuel().getAmount() > 0 && stand.getFuelLevel() + brewingRecipe.getFuelCharge() < 100) {
						stand.setFuelLevel(stand.getFuelLevel() + brewingRecipe.getFuelSet());
						count++;
					}
					
					// Actually taking out the item from the inventory
					if(inventory.getFuel().getAmount() == 0) {
						newFuel = new ItemStack(Material.AIR);
					} else {
						stand.setFuelLevel(100);
						newFuel.setAmount(inventory.getFuel().getAmount() - count);
					}
					
				} else {
					newFuel = new ItemStack(Material.AIR);
				}
				
				inventory.setFuel(newFuel);
				
				// Brew each item individually
				for(int i = 0; i < 3; i++) {
					if(inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) continue;
					brewingRecipe.getAction().Brew(inventory, i, inventory.getItem(i), ingredient);
				}
				
				stand.setFuelLevel(stand.getFuelLevel() - brewingRecipe.getFuelCharge());
				cancel();
				return;
			}
			
			// TODO: Check that the items are not the correct one for the brewing recipe!
			
			if(inventory.getItem(0) == null && inventory.getItem(1) == null && inventory.getItem(2) == null) {
				cancel();
				return;
			}
			
			if(searchedChanged(before, inventory.getContents())) {
				cancel();
				return;
			}
			
			timeToCook--;
			stand.setBrewingTime(timeToCook);
			stand.update(true);
			
		}
		
		public ItemStack[] getBefore() {
			return before;
		}
		
		public ItemStack[] getAfter() {
			return after;
		}
		
		private boolean searchedChanged(ItemStack[] before, ItemStack[] after) {
			for(int i = 0; i < 5; i++) {
				// Checking if item was null and added an item after
				if(before[i] == null && after[i] != null) {
					return true;
				}
				
				// Checking if item was not null and become null
				if(before[i] != null && after[i] == null) {
					return true;
				}
				
			}
			
			if(!before[3].isSimilar(after[3])) {
				return true;
			}
			
			if(!before[4].isSimilar(after[4])) {
				return false;
			}
			
			return false;
		}
		
	}
	
}