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

	public ItemStack WeedStack = (weed(new ItemStack(Material.WHEAT)));
	public ItemStack Percocet = (percocet(new ItemStack(Material.PUMPKIN_SEEDS)));
	public ItemStack Acid = (acid(new ItemStack(Material.PAPER)));
	public ItemStack Coke = (coke(new ItemStack(Material.SUGAR)));
	public ItemStack Heroin = (heroin(new ItemStack(Material.WITHER_ROSE)));
	public ItemStack Molly = (molly(new ItemStack(Material.IRON_NUGGET)));
	public ItemStack Ciggy = (ciggy(new ItemStack(Material.REDSTONE_TORCH)));
	public ItemStack Shrooms = (shrooms(new ItemStack(Material.BROWN_MUSHROOM)));
	public ItemStack Salvia  = (salvia(new ItemStack(Material.DRIED_KELP)));
	public ItemStack PCP = (pcp(new ItemStack(Material.SUNFLOWER)));
	public ItemStack DMT = (dmt(new ItemStack(Material.GLOWSTONE_DUST)));
	public ItemStack Alcohol = (alcohol(new ItemStack(Material.WATER_BUCKET)));
	public ItemStack Flakka = (flakka(new ItemStack(Material.QUARTZ)));
	public ItemStack Meth = (meth(new ItemStack(Material.PRISMARINE_SHARD)));
	public ItemStack Ketamine = (ketamine(new ItemStack(Material.POWDER_SNOW_BUCKET)));

	private Main plugin;

	public Drugs(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public Drugs() {
		return;
	}

	private ItemStack weed(ItemStack is) {
		ItemMeta weedMeta = is.getItemMeta();
		weedMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		weedMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		weedMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "WEED");
		ArrayList<String> weedLore = new ArrayList<String>();
		weedLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		weedLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slowness");
		weedLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Luck");
		weedLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slow Falling");
		weedLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slow Digging");
		weedLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		weedMeta.setLore(weedLore);
		is.setItemMeta(weedMeta);
		return is;
	}

	private ItemStack percocet(ItemStack is) {
		ItemMeta percocetMeta = is.getItemMeta();
		percocetMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		percocetMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		percocetMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "PERCOCET");
		ArrayList<String> percocetLore = new ArrayList<String>();
		percocetLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Confusion");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slow");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Luck");
		percocetLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Night Vision");
		percocetLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");

		percocetMeta.setLore(percocetLore);
		is.setItemMeta(percocetMeta);
		return is;
	}

	private ItemStack acid(ItemStack is) {
		ItemMeta acidMeta = is.getItemMeta();
		acidMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		acidMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		acidMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "ACID");
		ArrayList<String> acidLore = new ArrayList<String>();
		acidLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		acidLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Confusion");
		acidLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Night Vision");
		acidLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Health Boost");
		acidLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slow Falling");
		acidLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");

		acidMeta.setLore(acidLore);
		is.setItemMeta(acidMeta);
		return is;
	}

	private ItemStack heroin(ItemStack is) {
		ItemMeta heroinMeta = is.getItemMeta();
		heroinMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		heroinMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		heroinMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "HEROIN");
		ArrayList<String> heroinLore = new ArrayList<String>();
		heroinLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		heroinLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Weakness");
		heroinLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slowness");
		heroinLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Unluck");
		heroinLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Poison");
		heroinLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");

		heroinMeta.setLore(heroinLore);
		is.setItemMeta(heroinMeta);
		return is;
	}

	private ItemStack coke(ItemStack is) {
		ItemMeta cokeMeta = is.getItemMeta();
		cokeMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		cokeMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		cokeMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "COKE");

		ArrayList<String> cokeLore = new ArrayList<String>();
		cokeLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		cokeLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Damage Increase");
		cokeLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Fast Digging");
		cokeLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Health Boost");
		cokeLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Damage Resistance");
		cokeLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		cokeMeta.setLore(cokeLore);
		is.setItemMeta(cokeMeta);
		return is;
	}

	private ItemStack ciggy(ItemStack is) {
		ItemMeta CiggyMeta = is.getItemMeta();
		CiggyMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		CiggyMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		CiggyMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "CIGGY");
		ArrayList<String> CiggyLore = new ArrayList<String>();
		CiggyLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		CiggyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Saturation");
		CiggyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Damage Resistance");
		CiggyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Jump");
		CiggyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slow Digging");
		CiggyLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");

		CiggyMeta.setLore(CiggyLore);
		is.setItemMeta(CiggyMeta);
		return is;
	}

	private ItemStack molly(ItemStack is) {
		ItemMeta MollyMeta = is.getItemMeta();
		MollyMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		MollyMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		MollyMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "MOLLY");
		ArrayList<String> MollyLore = new ArrayList<String>();
		MollyLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Confusion");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Speed");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Fast Digging");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Fire Resistance");
		MollyLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Night Vision");
		MollyLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");

		MollyMeta.setLore(MollyLore);
		is.setItemMeta(MollyMeta);
		return is;
	}
	
	private ItemStack shrooms(ItemStack is) {
		ItemMeta shroomsMeta = is.getItemMeta();
		shroomsMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		shroomsMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		shroomsMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "SHROOMS");
		ArrayList<String> shroomsLore = new ArrayList<String>();
		shroomsLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		shroomsLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Luck");
		shroomsLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Night Vision");
		shroomsLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Confusion");
		shroomsLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Glowing");

		shroomsLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		shroomsMeta.setLore(shroomsLore);
		is.setItemMeta(shroomsMeta);
		return is;
	}
	
	private ItemStack salvia(ItemStack is) {
		ItemMeta salviaMeta = is.getItemMeta();
		salviaMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		salviaMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		salviaMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "SALVIA");
		ArrayList<String> salviaLore = new ArrayList<String>();
		salviaLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		salviaLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Night Vision");
		salviaLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Regeneration");
		salviaLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Weakness");
		salviaLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Glowing");

		salviaLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		salviaMeta.setLore(salviaLore);
		is.setItemMeta(salviaMeta);
		return is;
	}
	
	private ItemStack pcp(ItemStack is) {
		ItemMeta pcpMeta = is.getItemMeta();
		pcpMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		pcpMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		pcpMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "PCP");
		ArrayList<String> pcpLore = new ArrayList<String>();
		pcpLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		pcpLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Damage Resistance");
		pcpLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Bad Omen");
		pcpLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Confusion");
		pcpLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		pcpMeta.setLore(pcpLore);
		is.setItemMeta(pcpMeta);
		return is;
	}
	
	private ItemStack dmt(ItemStack is) {
		ItemMeta dmtMeta = is.getItemMeta();
		dmtMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		dmtMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		dmtMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "DMT");
		ArrayList<String> dmtLore = new ArrayList<String>();
		dmtLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		dmtLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Damage Resistance");
		dmtLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slow Falling");
		dmtLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slowness");
		dmtLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Glowing");
		dmtLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		dmtMeta.setLore(dmtLore);
		is.setItemMeta(dmtMeta);
		return is;
	}
	
	private ItemStack alcohol(ItemStack is) {
		ItemMeta alcoholMeta = is.getItemMeta();
		alcoholMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		alcoholMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		alcoholMeta.setDisplayName(ChatColor.GOLD+ "" + ChatColor.BOLD + "ALCOHOL");
		ArrayList<String> alcoholLore = new ArrayList<String>();
		alcoholLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		alcoholLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Speed");
		alcoholLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Night Vison");
		alcoholLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Hunger");
		alcoholLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Confusion");
		alcoholLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		alcoholMeta.setLore(alcoholLore);
		is.setItemMeta(alcoholMeta);
		return is;
	}
	
	private ItemStack flakka(ItemStack is) {
		ItemMeta flakkaMeta = is.getItemMeta();
		flakkaMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		flakkaMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		flakkaMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "FLAKKA");
		ArrayList<String> flakkaLore = new ArrayList<String>();
		flakkaLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		flakkaLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Speed");
		flakkaLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Damage Resistance");
		flakkaLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Unluck");
		flakkaLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Dolphins Grace");

		flakkaLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		flakkaMeta.setLore(flakkaLore);
		is.setItemMeta(flakkaMeta);
		return is;
	}
	
	private ItemStack meth(ItemStack is) {
		ItemMeta methMeta = is.getItemMeta();
		methMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		methMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		methMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "METH");
		ArrayList<String> methLore = new ArrayList<String>();
		methLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		methLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Absorption");
		methLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slowness");
		methLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Weakness");
		methLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Levitation");
		methLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		methMeta.setLore(methLore);
		is.setItemMeta(methMeta);
		return is;
	}
	
	private ItemStack ketamine(ItemStack is) {
		ItemMeta ketamineMeta = is.getItemMeta();
	    ketamineMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		ketamineMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		ketamineMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "KETAMINE");
		ArrayList<String> ketamineLore = new ArrayList<String>();
		ketamineLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Effects:");
		ketamineLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Night Vison");
		ketamineLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Speed");
		ketamineLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Fast Digging");
		ketamineLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Confusion");
		ketamineLore.add(ChatColor.GRAY + "- " + ChatColor.GOLD + "Slow Falling");
		ketamineLore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Right-Click To Use");
		ketamineMeta.setLore(ketamineLore);
		is.setItemMeta(ketamineMeta);
		return is;
	}

	public void WeedRecipe() {
		ShapedRecipe weed = new ShapedRecipe(new NamespacedKey(this.plugin, "drug_wheat-weed"),
				new ItemStack(WeedStack));

		weed.shape("WDW", " D ", "WWW");
		weed.setIngredient('W', Material.WHEAT);
		weed.setIngredient('D', Material.DIAMOND);
		Bukkit.addRecipe(weed);
	}

	public void PercocetRecipe() {

		ShapedRecipe percocet1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_percocet-melon/seeds"),
				new ItemStack(Percocet));
		percocet1.shape("AAA", "MDM", "AAA");
		percocet1.setIngredient('M', Material.MILK_BUCKET);
		percocet1.setIngredient('A', Material.ARROW);
		percocet1.setIngredient('D', Material.WHITE_DYE);
		Bukkit.addRecipe(percocet1);
	}

	public void AcidRecipe() {

		ShapedRecipe acid1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_acid-paper"), new ItemStack(Acid));
		acid1.shape("SPS", "PWP", "SPS");
		acid1.setIngredient('P', Material.PAPER);
		acid1.setIngredient('W', Material.WATER_BUCKET);
		acid1.setIngredient('S', Material.SPIDER_EYE);
		Bukkit.addRecipe(acid1);
	}

	public void CokeRecipe() {
		ShapedRecipe coke1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_coke_sugar"), new ItemStack(Coke));
		coke1.shape("SSS", "SBS", "SSS");
		coke1.setIngredient('S', Material.SUGAR);
		coke1.setIngredient('B', Material.WATER_BUCKET);
		Bukkit.addRecipe(coke1);

	}

	public void HeroinRecipe() {

		ShapedRecipe heroin1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_heroin-wither/rose"),
				new ItemStack(Heroin));
		heroin1.shape("DRR", " DW", " D ");
		heroin1.setIngredient('D', Material.DIRT);
		heroin1.setIngredient('R', Material.RED_MUSHROOM);
		heroin1.setIngredient('W', Material.WOODEN_SWORD);
		Bukkit.addRecipe(heroin1);
	}

	public void MollyRecipe() {

		ShapedRecipe molly1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_molly-IRON/INGOT"),
				new ItemStack(Molly));
		molly1.shape("III", "GWG", "III");
		molly1.setIngredient('I', Material.IRON_INGOT);
		molly1.setIngredient('W', Material.WATER_BUCKET);
		molly1.setIngredient('G', Material.GUNPOWDER);
		Bukkit.addRecipe(molly1);
	}

	public void CiggyRecipe() {

		ShapedRecipe ciggy1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_ciggy/REDSTONETORCH"),
				new ItemStack(Ciggy));
		ciggy1.shape(" G ", "PSP", "PPP");
		ciggy1.setIngredient('S', Material.STICK);
		ciggy1.setIngredient('P', Material.PAPER);
		ciggy1.setIngredient('G', Material.GUNPOWDER);
		Bukkit.addRecipe(ciggy1);
	}
}
