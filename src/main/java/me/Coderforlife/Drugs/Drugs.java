package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Drugs {

	// a list with all drugs stored in it
	private List<Drug> allDrugs;
	// an array that holds all the items of drugs
	private ItemStack[] drugItemArray;

	private Main plugin;

	public Drugs(Main plugin) {
		this.setPlugin(plugin);
		this.allDrugs = new LinkedList<Drug>();
		loadDrugs();
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	/**
	 * Returns a list of all drugs in the plugin
	 */
	public List<Drug> getAllDrugs() {
		return allDrugs;
	}

	/**
	 * Returns an array containing the items of all drugs
	 */
	public ItemStack[] getDrugItemArray() {
		return drugItemArray;
	}

	public void enableDrug(Drug drug) {
		Bukkit.addRecipe(drug.getItemRecipe());
	}
	
	public void disableDrug(Drug drug) {
		Bukkit.getServer().removeRecipe(null);
	}

	/**
	 * Creates all drugs and their recipes. The recipes are not added to the server
	 * here.
	 */
	private void loadDrugs() {
		allDrugs.clear();

		Drug weed = new Drug("Weed", ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "WEED", Material.GREEN_DYE,
				PotionEffectType.SLOW, PotionEffectType.LUCK, PotionEffectType.SLOW_FALLING,
				PotionEffectType.SLOW_DIGGING);

		Drug percocet = new Drug("Percocet", ChatColor.WHITE + "" + ChatColor.BOLD + "PERCOCET", Material.PUMPKIN_SEEDS,
				PotionEffectType.CONFUSION, PotionEffectType.SLOW, PotionEffectType.LUCK,
				PotionEffectType.NIGHT_VISION);

		Drug acid = new Drug("Acid", ChatColor.AQUA + "" + ChatColor.BOLD + "ACID", Material.PUMPKIN_SEEDS,
				PotionEffectType.CONFUSION, PotionEffectType.NIGHT_VISION, PotionEffectType.HEALTH_BOOST,
				PotionEffectType.SLOW_FALLING);

		Drug coke = new Drug("Coke", ChatColor.AQUA + "" + ChatColor.BOLD + "COKE", Material.SUGAR,
				PotionEffectType.INCREASE_DAMAGE, PotionEffectType.FAST_DIGGING, PotionEffectType.HEALTH_BOOST,
				PotionEffectType.DAMAGE_RESISTANCE);

		Drug heroin = new Drug("Heroin", ChatColor.DARK_RED + "" + ChatColor.BOLD + "HEROIN", Material.WITHER_ROSE,
				PotionEffectType.WEAKNESS, PotionEffectType.SLOW, PotionEffectType.UNLUCK, PotionEffectType.POISON);

		Drug molly = new Drug("Molly", ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "MOLLY", Material.IRON_NUGGET,
				PotionEffectType.CONFUSION, PotionEffectType.SPEED, PotionEffectType.FAST_DIGGING,
				PotionEffectType.FIRE_RESISTANCE, PotionEffectType.NIGHT_VISION);

		Drug ciggy = new Drug("Ciggy", ChatColor.GOLD + "" + ChatColor.BOLD + "CIGGY", Material.REDSTONE_TORCH,
				PotionEffectType.SATURATION, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.JUMP,
				PotionEffectType.SLOW_DIGGING);

		Drug shrooms = new Drug("Shrooms", ChatColor.GRAY + "" + ChatColor.BOLD + "SHROOMS", Material.CRIMSON_FUNGUS,
				PotionEffectType.LUCK, PotionEffectType.NIGHT_VISION, PotionEffectType.CONFUSION,
				PotionEffectType.GLOWING);

		Drug salvia = new Drug("Salvia", ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "SALVIA", Material.DRIED_KELP,
				PotionEffectType.NIGHT_VISION, PotionEffectType.REGENERATION, PotionEffectType.WEAKNESS,
				PotionEffectType.GLOWING);

		Drug pcp = new Drug("PCP", ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "PCP", Material.SPORE_BLOSSOM,
				PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.BAD_OMEN, PotionEffectType.CONFUSION);

		Drug dmt = new Drug("DMT", ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "DMT", Material.GLOWSTONE_DUST,
				PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.SLOW_FALLING, PotionEffectType.SLOW,
				PotionEffectType.GLOWING);

		Drug alcohol = new Drug("Alcohol", ChatColor.GOLD + "" + ChatColor.BOLD + "ALCOHOL", Material.WATER_BUCKET,
				PotionEffectType.SPEED, PotionEffectType.NIGHT_VISION, PotionEffectType.HUNGER,
				PotionEffectType.CONFUSION);

		Drug flakka = new Drug("Flakka", ChatColor.YELLOW + "" + ChatColor.BOLD + "FLAKKA", Material.QUARTZ,
				PotionEffectType.SPEED, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.UNLUCK,
				PotionEffectType.DOLPHINS_GRACE);

		Drug meth = new Drug("Meth", ChatColor.AQUA + "" + ChatColor.BOLD + "METH", Material.PRISMARINE_SHARD,
				PotionEffectType.ABSORPTION, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.FIRE_RESISTANCE,
				PotionEffectType.LEVITATION);

		Drug ketamine = new Drug("Ketamine", ChatColor.RED + "" + ChatColor.BOLD + "KETAMINE",
				Material.POWDER_SNOW_BUCKET, PotionEffectType.NIGHT_VISION, PotionEffectType.SPEED,
				PotionEffectType.FAST_DIGGING, PotionEffectType.CONFUSION, PotionEffectType.SLOW_FALLING);

		Drug oxy = new Drug("Oxy", ChatColor.AQUA + "" + ChatColor.BOLD + "OXY", Material.WHITE_DYE,
				PotionEffectType.ABSORPTION, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.CONFUSION,
				PotionEffectType.GLOWING);

		Drug tussin = new Drug("Tussin", ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "TUSSIN", Material.PURPLE_CANDLE,
				PotionEffectType.INCREASE_DAMAGE, PotionEffectType.HEAL, PotionEffectType.JUMP,
				PotionEffectType.CONFUSION);

		Drug xannx = new Drug("Xannx", ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "XANAX", Material.GREEN_CANDLE,
				PotionEffectType.SLOW, PotionEffectType.SLOW_DIGGING, PotionEffectType.SLOW_FALLING,
				PotionEffectType.BLINDNESS);

		// creates the recipes one by one
		weed.setItemRecipe(WeedRecipe(weed.getDrugItem()));
		acid.setItemRecipe(AcidRecipe(acid.getDrugItem()));
		percocet.setItemRecipe(PercocetRecipe(percocet.getDrugItem()));
		coke.setItemRecipe(CokeRecipe(coke.getDrugItem()));
		heroin.setItemRecipe(HeroinRecipe(heroin.getDrugItem()));
		molly.setItemRecipe(MollyRecipe(molly.getDrugItem()));
		ciggy.setItemRecipe(CiggyRecipe(ciggy.getDrugItem()));
		shrooms.setItemRecipe(ShroomsRecipe(shrooms.getDrugItem()));
		salvia.setItemRecipe(SalviaRecipe(salvia.getDrugItem()));
		pcp.setItemRecipe(PCPRecipe(pcp.getDrugItem()));
		dmt.setItemRecipe(DMTRecipe(dmt.getDrugItem()));
		alcohol.setItemRecipe(AlcoholRecipe(alcohol.getDrugItem()));
		flakka.setItemRecipe(FlakkaRecipe(flakka.getDrugItem()));
		meth.setItemRecipe(MethRecipe(meth.getDrugItem()));
		ketamine.setItemRecipe(KetamineRecipe(ketamine.getDrugItem()));
		oxy.setItemRecipe(OxyRecipe(oxy.getDrugItem()));
		tussin.setItemRecipe(TussinRecipe(tussin.getDrugItem()));
		xannx.setItemRecipe(XannxRecipe(xannx.getDrugItem()));

		allDrugs.addAll(Arrays.asList(weed, acid, percocet, coke, heroin, molly, ciggy, shrooms, salvia, pcp, dmt,
				alcohol, flakka, meth, ketamine, oxy, tussin, xannx));
		drugItemArray = allDrugs.stream().map(Drug::getDrugItem).toArray(ItemStack[]::new);
	}

	public Recipe WeedRecipe(ItemStack item) {
		ShapedRecipe weed = new ShapedRecipe(new NamespacedKey(this.plugin, "drug_wheat-weed"), new ItemStack(item));

		weed.shape("WDW", " D ", "WWW");
		weed.setIngredient('W', Material.WHEAT);
		weed.setIngredient('D', Material.DIAMOND);
		return weed;
	}

	public Recipe PercocetRecipe(ItemStack item) {

		ShapedRecipe percocet1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_percocet-melon/seeds"),
				new ItemStack(item));
		percocet1.shape("AAA", "MDM", "AAA");
		percocet1.setIngredient('M', Material.MILK_BUCKET);
		percocet1.setIngredient('A', Material.ARROW);
		percocet1.setIngredient('D', Material.WHITE_DYE);
		return percocet1;
	}

	public Recipe AcidRecipe(ItemStack item) {

		ShapedRecipe acid1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_acid-paper"), new ItemStack(item));
		acid1.shape("SPS", "PWP", "SPS");
		acid1.setIngredient('P', Material.PAPER);
		acid1.setIngredient('W', Material.WATER_BUCKET);
		acid1.setIngredient('S', Material.SPIDER_EYE);
		return acid1;
	}

	public Recipe CokeRecipe(ItemStack item) {
		ShapedRecipe coke1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_coke_sugar"), new ItemStack(item));
		coke1.shape("SSS", "SBS", "SSS");
		coke1.setIngredient('S', Material.SUGAR);
		coke1.setIngredient('B', Material.WATER_BUCKET);
		return coke1;
	}

	public Recipe HeroinRecipe(ItemStack item) {

		ShapedRecipe heroin1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_heroin-wither/rose"),
				new ItemStack(item));
		heroin1.shape("DRR", " DW", " D ");
		heroin1.setIngredient('D', Material.DIRT);
		heroin1.setIngredient('R', Material.RED_MUSHROOM);
		heroin1.setIngredient('W', Material.WOODEN_SWORD);
		return heroin1;
	}

	public Recipe MollyRecipe(ItemStack item) {

		ShapedRecipe molly1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_molly-IRON/INGOT"),
				new ItemStack(item));
		molly1.shape("III", "GWG", "III");
		molly1.setIngredient('I', Material.IRON_INGOT);
		molly1.setIngredient('W', Material.WATER_BUCKET);
		molly1.setIngredient('G', Material.GUNPOWDER);
		return molly1;
	}

	public Recipe AlcoholRecipe(ItemStack item) {

		ShapedRecipe alcohol = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_alcohol/WATERBUCKET"),
				new ItemStack(item));
		alcohol.shape(" P ", "GGG", "WWW");
		alcohol.setIngredient('W', Material.WATER_BUCKET);
		alcohol.setIngredient('P', Material.PAPER);
		alcohol.setIngredient('G', Material.GUNPOWDER);
		return alcohol;
	}

	public Recipe DMTRecipe(ItemStack item) {

		ShapedRecipe dmt = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_DMT/GLOWSTONEDUST"),
				new ItemStack(item));
		dmt.shape("SSS", "SGS", "SGS");
		dmt.setIngredient('S', Material.SUGAR);
		dmt.setIngredient('G', Material.GLOWSTONE_DUST);
		return dmt;
	}

	public Recipe FlakkaRecipe(ItemStack item) {

		ShapedRecipe flakka = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_flakka/QUARTZ"),
				new ItemStack(item));
		flakka.shape(" D ", "SSS", "W W");
		flakka.setIngredient('S', Material.SWEET_BERRIES);
		flakka.setIngredient('W', Material.WATER_BUCKET);
		flakka.setIngredient('D', Material.BIRCH_DOOR);
		return flakka;
	}

	public Recipe KetamineRecipe(ItemStack item) {

		ShapedRecipe ketamine = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_ketamine/SNOWBUCKET"),
				new ItemStack(item));
		ketamine.shape("SSS", "SLS", "SSS");
		ketamine.setIngredient('S', Material.SNOWBALL);
		ketamine.setIngredient('L', Material.LAVA_BUCKET);
		return ketamine;
	}

	public Recipe MethRecipe(ItemStack item) {

		ShapedRecipe meth = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_meth/PRISMINESHARD"),
				new ItemStack(item));
		meth.shape(" D ", " P ", " G ");
		meth.setIngredient('D', Material.DIRT);
		meth.setIngredient('P', Material.PAPER);
		meth.setIngredient('G', Material.GUNPOWDER);
		return meth;
	}

	public Recipe OxyRecipe(ItemStack item) {

		ShapedRecipe oxy = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_oxy/WHITEDYE"), new ItemStack(item));
		oxy.shape("WWW", "B B", " L ");
		oxy.setIngredient('W', Material.WHITE_WOOL);
		oxy.setIngredient('B', Material.WATER_BUCKET);
		oxy.setIngredient('L', Material.LAVA_BUCKET);
		return oxy;
	}

	public Recipe PCPRecipe(ItemStack item) {

		ShapedRecipe pcp = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_pcp/SUNFLOWER"), new ItemStack(item));
		pcp.shape("WMP", "PMW", "WMP");
		pcp.setIngredient('W', Material.WHEAT_SEEDS);
		pcp.setIngredient('M', Material.MELON_SEEDS);
		pcp.setIngredient('P', Material.PUMPKIN_SEEDS);
		return pcp;
	}

	public Recipe SalviaRecipe(ItemStack item) {

		ShapedRecipe slavia = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_salvia/DIREDKELP"),
				new ItemStack(item));
		slavia.shape(" B ", "CCC", "D D");
		slavia.setIngredient('B', Material.GREEN_BED);
		slavia.setIngredient('C', Material.GREEN_CANDLE);
		slavia.setIngredient('D', Material.GREEN_DYE);
		return slavia;
	}

	public Recipe ShroomsRecipe(ItemStack item) {

		ShapedRecipe shrooms = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_shrooms/BROWNMUSHROOM"),
				new ItemStack(item));
		shrooms.shape("DDD", "S S", " B ");
		shrooms.setIngredient('D', Material.DIRT);
		shrooms.setIngredient('S', Material.STICK);
		shrooms.setIngredient('B', Material.BROWN_MUSHROOM);
		return shrooms;
	}

	public Recipe XannxRecipe(ItemStack item) {

		ShapedRecipe xannx = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_xannx/GREENCANDLE"),
				new ItemStack(item));
		xannx.shape(" G ", "W W", " P ");
		xannx.setIngredient('G', Material.GREEN_BED);
		xannx.setIngredient('W', Material.WHITE_TULIP);
		xannx.setIngredient('P', Material.GUNPOWDER);
		return xannx;
	}

	public Recipe TussinRecipe(ItemStack item) {

		ShapedRecipe tussin = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_tussin/PURPLECANDLE"),
				new ItemStack(item));
		tussin.shape("W W", "G G", "WGW");
		tussin.setIngredient('W', Material.WATER_BUCKET);
		tussin.setIngredient('G', Material.GLASS_BOTTLE);
		return tussin;
	}

	public Recipe CiggyRecipe(ItemStack item) {

		ShapedRecipe ciggy1 = new ShapedRecipe(new NamespacedKey(this.plugin, "drugs_ciggy/REDSTONETORCH"),
				new ItemStack(item));
		ciggy1.shape(" G ", "PSP", "PPP");
		ciggy1.setIngredient('S', Material.STICK);
		ciggy1.setIngredient('P', Material.PAPER);
		ciggy1.setIngredient('G', Material.GUNPOWDER);
		return ciggy1;
	}

}
