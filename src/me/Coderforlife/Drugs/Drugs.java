package me.Coderforlife.Drugs;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Drugs {
	
	public ItemStack weed1 = new ItemStack(Material.WHEAT);
	public ItemStack percocet = new ItemStack(Material.PUMPKIN_SEEDS);
	public ItemStack acid = new ItemStack(Material.PAPER);
	public ItemStack coke = new ItemStack(Material.SUGAR);
	public ItemStack heroin = new ItemStack(Material.WITHER_ROSE);
	public ItemStack molly = new ItemStack(Material.IRON_NUGGET);




	private Main plugin;

	   public Drugs(Main plugin) {
	      this.setPlugin(plugin);
	   }

	   public Drugs(KillerCommands killerCommands) {
		// TODO Auto-generated constructor stub
	}

	public Main getPlugin() {
	      return this.plugin;
	   }

	   public void setPlugin(Main plugin) {
	      this.plugin = plugin;
	   }

	public void WeedRecipe() {
		ItemMeta weedMeta = weed1.getItemMeta();
		weedMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		weedMeta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES});
		weedMeta.setDisplayName(ChatColor.DARK_GREEN + ""
				+ ChatColor.BOLD + "WEED");
		ArrayList<String> weedLore = new ArrayList<String>();
		weedLore.add(ChatColor.GRAY + "" + 
				ChatColor.ITALIC + "Right-Click to get " + 
				ChatColor.RESET + "" +
		        ChatColor.DARK_RED + "" +
				ChatColor.BOLD + "LIT!!");
		weedLore.add(ChatColor.GREEN + "" + 
				ChatColor.BOLD + "Effects:");
		weedLore.add(ChatColor.GRAY + "- " +
				ChatColor.GOLD + "SLOWNESS");
		weedLore.add(ChatColor.GRAY + "- " + 
				ChatColor.GOLD + "LUCK");
		weedLore.add(ChatColor.DARK_RED +
			  "" +ChatColor.BOLD + "Duration: " 
				+ ChatColor.GOLD + "45 Seconds");
		weedLore.add(ChatColor.GRAY + "" + 
				ChatColor.UNDERLINE + "More Effects coming soon.");
		weedMeta.setLore(weedLore);
		weed1.setItemMeta(weedMeta);
		ShapedRecipe weed = new ShapedRecipe(
				new NamespacedKey (this.plugin, "drug_wheat-weed"
				), new ItemStack(weed1));
		
		weed.shape("WDW"," D ","WWW");
		weed.setIngredient('W', Material.WHEAT);
		weed.setIngredient('D', Material.DIAMOND);
		Bukkit.addRecipe(weed);
	}
	
	public void PercocetRecipe() {
		ItemMeta percocetMeta = percocet.getItemMeta();
		percocetMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		percocetMeta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES});
		percocetMeta.setDisplayName(ChatColor.WHITE + "" +
		ChatColor.BOLD + "PERCOCET");
		ArrayList<String> percocetLore = new ArrayList<String>();
		percocetLore.add(ChatColor.GRAY + "" + 
		ChatColor.ITALIC + "Right-Click to get " +
				ChatColor.DARK_RED + "" + 
		ChatColor.BOLD + "LIT!!");
		percocetLore.add(ChatColor.GREEN + "" + 
		ChatColor.BOLD + "Effects:");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "CONFUSION");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "SLOW");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "LUCK");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "GLOWING");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "NIGHT VISION");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.DARK_PURPLE + ChatColor.BOLD + "THIS HITS DIFFERENT");
		percocetLore.add(ChatColor.GRAY + "" + 
				ChatColor.UNDERLINE + "More Effects coming soon.");
		
		percocetMeta.setLore(percocetLore);
		percocet.setItemMeta(percocetMeta);
		ShapedRecipe percocet1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_percocet-melon/seeds"), new ItemStack(percocet));
		percocet1.shape("AAA","MDM","AAA");
		percocet1.setIngredient('M', Material.MILK_BUCKET);
		percocet1.setIngredient('A', Material.ARROW);
		percocet1.setIngredient('D', Material.WHITE_DYE);
		Bukkit.addRecipe(percocet1);
	}
	
	public void AcidRecipe() {
		 ItemMeta acidMeta = acid.getItemMeta();
		 acidMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			acidMeta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES});
			acidMeta.setDisplayName(ChatColor.AQUA + "" +
			ChatColor.BOLD + "ACID");
			ArrayList<String> acidLore = new ArrayList<String>(); 
			acidLore.add(ChatColor.GRAY + "" + 
			ChatColor.ITALIC + "Right-Click to get " +
						ChatColor.DARK_RED + "" + 
			ChatColor.BOLD + "LIT!!");
			acidLore.add(ChatColor.GREEN + "" + 
			ChatColor.BOLD + "Effects:");
			acidLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "CONFUSION");
			acidLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "GLOWING");
			acidLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "HEALTH BOOST");
			acidLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "SLOW FALLING");
			acidLore.add(ChatColor.GRAY + "- " + ChatColor.DARK_PURPLE + ChatColor.BOLD + "THIS HITS DIFFERENT");
			acidLore.add(ChatColor.GRAY + "" + 
					   ChatColor.UNDERLINE + "More Effects coming soon.");
				
			acidMeta.setLore(acidLore);
			acid.setItemMeta(acidMeta);
			ShapedRecipe acid1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_acid-paper"), new ItemStack(acid));
			acid1.shape("SPS","PWP","SPS");
			acid1.setIngredient('P', Material.PAPER);
			acid1.setIngredient('W', Material.WATER_BUCKET);
			acid1.setIngredient('S', Material.SPIDER_EYE);
			Bukkit.addRecipe(acid1);
	}
	
	public void CokeRecipe() {
		ItemMeta cokeMeta = coke.getItemMeta();
		cokeMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		cokeMeta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES});
		cokeMeta.setDisplayName(ChatColor.AQUA +
		"" + ChatColor.BOLD + "COKE");
		
		ArrayList<String> cokeLore = new ArrayList<String>();
		cokeLore.add(ChatColor.GRAY + "" + 
		ChatColor.ITALIC + "Right-Click to get " + ChatColor.DARK_RED + "" 
				+ ChatColor.BOLD + "LIT!!");
		cokeLore.add(ChatColor.GREEN + "" + 
				ChatColor.BOLD + "Effects:");
		cokeLore.add(ChatColor.GRAY + "- " +
				ChatColor.GOLD + "SPEED");
		cokeLore.add(ChatColor.GRAY + "- " +
				ChatColor.GOLD + "GLOWING");
		cokeLore.add(ChatColor.GRAY + "- "
				 + ChatColor.GOLD + "FAST DIGGING");
		cokeLore.add(ChatColor.DARK_RED + ""
				 + ChatColor.BOLD + "Duration: " +
				ChatColor.GOLD + "45 Seconds");
		cokeLore.add(ChatColor.GRAY + "" + 
				ChatColor.UNDERLINE + "More Effects coming soon.");
		cokeMeta.setLore(cokeLore);
		coke.setItemMeta(cokeMeta);
		ShapedRecipe coke1 = new ShapedRecipe(new NamespacedKey (this.plugin, "drugs_coke_sugar"), new ItemStack(coke));
		coke1.shape("SSS","SBS","SSS");
		coke1.setIngredient('S', Material.SUGAR);
		coke1.setIngredient('B', Material.WATER_BUCKET);
		Bukkit.addRecipe(coke1);

	}
	
	public void HeroinRecipe() {
		ItemMeta heroinMeta = heroin.getItemMeta();
		heroinMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		heroinMeta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES});
		heroinMeta.setDisplayName(ChatColor.BLACK + "" +
		ChatColor.MAGIC + "HEROINHEROIN");
		ArrayList<String> heroinLore = new ArrayList<String>();
		heroinLore.add(ChatColor.GRAY + "" + 
		ChatColor.ITALIC + "Right-Click to get " +
				ChatColor.DARK_RED + "" + 
		ChatColor.BOLD + "LIT!!");
		heroinLore.add(ChatColor.GREEN + "" + 
		ChatColor.BOLD + "Effects:");
		heroinLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "WEAKNESS");
		heroinLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "SLOW");
		heroinLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "UNLUCK");
		heroinLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "POISON");
		heroinLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "BAD OMEN");
		heroinLore.add(ChatColor.GRAY + "" + 
				ChatColor.UNDERLINE + "More Effects coming soon.");
		
		heroinMeta.setLore(heroinLore);
		heroin.setItemMeta(heroinMeta);
		ShapedRecipe heroin1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_heroin-wither/rose"), new ItemStack(heroin));
		heroin1.shape("DRR"," DW"," D ");
		heroin1.setIngredient('D', Material.DIRT);
		heroin1.setIngredient('R', Material.RED_MUSHROOM);
		heroin1.setIngredient('W', Material.WOODEN_SWORD);
		Bukkit.addRecipe(heroin1);
	}
	
	public void MollyRecipe() {
		ItemMeta MollyMeta = molly.getItemMeta();
		MollyMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		MollyMeta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES});
		MollyMeta.setDisplayName(ChatColor.DARK_AQUA + "" +
		ChatColor.BOLD + "MOLLY");
		ArrayList<String> MollyLore = new ArrayList<String>();
		MollyLore.add(ChatColor.GRAY + "" + 
		ChatColor.ITALIC + "Right-Click to get " +
				ChatColor.DARK_RED + "" + 
		ChatColor.BOLD + "LIT!!");
		MollyLore.add(ChatColor.GREEN + "" + 
		ChatColor.BOLD + "Effects:");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "CONFUSIN");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "SPEED");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "FAST DIGGING");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "FIRE RESISTANCE");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "NIGHT VISION");
		MollyLore.add(ChatColor.GRAY + "" + 
				ChatColor.UNDERLINE + "More Effects coming soon.");
		
		MollyMeta.setLore(MollyLore);
		molly.setItemMeta(MollyMeta);
		ShapedRecipe molly1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_molly-IRON/INGOT"), new ItemStack(molly));
		molly1.shape("III","GWG","III");
		molly1.setIngredient('I', Material.IRON_INGOT);
		molly1.setIngredient('W', Material.WATER_BUCKET);
		molly1.setIngredient('G', Material.GUNPOWDER);
		Bukkit.addRecipe(molly1);
	}
}
