package me.Coderforlife.Drugs;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftingCheck implements Listener{
	
	public static ItemStack dasfish = new ItemStack(Material.TROPICAL_FISH_BUCKET, 64);
	public static ItemStack Nonono  = new ItemStack(Material.BARRIER);
	public static String NopeMeta = "Use only 1 item at a time.";//Meta for the No-Button
	public static String WeedBukMeta = "Combine 3 of these for a GoldNug";//Meta for GoldNug Ing
	public static String GoldNugMeta = "Combine 3 to get your Weed";//Meta for Weed Ing
	public static String LavaBukkitMeta = "Combine 3 to get your Carrot";// Meta for Carrot Ing
	public static String CarrotIngMeta = "Combine 3 to get your Coke."; //Meta for Coke Ing
	public static String FishBukkitMeta = "Combine 3 to get your bREAD";//Meta for bREAD Ing
	public static String bREADMeta = "Combine 3 to get your Acid";//Meta for Acid Ing.
	public static int valuekey = 45;
	public static int valuekey2 = 53;
	public static int valuekey3 = 769;
	public static int NOPEKEY = 666;
	public static String RealCokeMeta = "Right-Click to use me";
	public static String RealAcidMeta = "Right-Click to use me";
	public static String RealWeedMeta = "Right-Click to use me";
	public static int RealWeedKey = 476;
	public static int RealCokeKey = 558;
	public static int RealAcidKey = 775;


	private Main plugin;

	   public CraftingCheck(Main plugin) {
	      this.setPlugin(plugin);
	   }

	   public Main getPlugin() {
	      return this.plugin;
	   }

	   public void setPlugin(Main plugin) {
	      this.plugin = plugin;
	   }
	   //Minecraft Craftbench Inventory
	   /*
	    0 1 2
	    3 4 5 = Matrix
	    6 7 8
	    */
	   @EventHandler                 //Getting the Item Prepare Event
	 public void CraftingInv(PrepareItemCraftEvent e) {
		   CraftingInventory inv = e.getInventory(); //Setting the inv variable
		   if(inv.getSize() < 9) return;
		   ItemStack[] matrix = inv.getMatrix();// Crafting Grid 
			   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL) //Using map key to get specific item
					   && matrix[4].getEnchantments().containsValue(valuekey2) //Same as above but with Key -- For unsafe values use true when setting the enchantment in the Main.java\WeedRec()
					   && matrix[7].getItemMeta().getDisplayName().contentEquals(ChatColor.GREEN + "WaterBukkit Ing.")) //Getting the DisplayName just to make sure.
			       {
				   if(matrix[1].getAmount() == 1 
						   && matrix[4].getAmount() == 1 
						   &&matrix[7].getAmount() == 1) {
					   ItemStack WeedBuk = new ItemStack(Material.WATER_BUCKET);
					   ItemMeta meta = WeedBuk.getItemMeta();
					   meta.setDisplayName(ChatColor.AQUA + "WaterBukkit");
					   meta.setLore(Arrays.asList(WeedBukMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, 95, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
					   WeedBuk.setItemMeta(meta);
					   inv.setResult(WeedBuk);
					   
				   }else {
					   ItemMeta meta = Nonono.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_RED + "Don't use stacks.");
					   meta.setLore(Arrays.asList(NopeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, NOPEKEY, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS});
					   Nonono.setItemMeta(meta);
					   inv.setResult(Nonono);

				   }

				   
				   }
			   
			   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL)
					   &&matrix[4].getEnchantments().containsValue(95)
					   && matrix[7].getItemMeta().getDisplayName().contentEquals(ChatColor.AQUA + "WaterBukkit")) {
				   if(matrix[1].getAmount() == 1 
						   && matrix[4].getAmount() == 1 
						   && matrix[7].getAmount() == 1) {
					   ItemStack GoldNug = new ItemStack(Material.GOLD_INGOT);
					   ItemMeta meta = GoldNug.getItemMeta();
					   meta.setDisplayName(ChatColor.GOLD + "GOLD NUG");
					   meta.setLore(Arrays.asList(GoldNugMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, 120, true);
					   meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS});
					   GoldNug.setItemMeta(meta);
					   inv.setResult(GoldNug);
				   }else {
					   ItemMeta meta = Nonono.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_RED + "Don't use stacks.");
					   meta.setLore(Arrays.asList(NopeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, NOPEKEY, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS});
					   Nonono.setItemMeta(meta);
					   inv.setResult(Nonono);
				   }
			   }
			   
			   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL) 
					   && matrix[4].getEnchantments().containsValue(120) 
					   && matrix[7].getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "GOLD NUG")) {
				   if(matrix[1].getAmount() == 1 
						   && matrix[4].getAmount() == 1 
						   && matrix[7].getAmount() == 1) {
					   ItemStack Weed = new ItemStack(Material.WHEAT);
					   ItemMeta meta = Weed.getItemMeta();
					   meta.setDisplayName(ChatColor.GREEN + "WEED");
					   meta.setLore(Arrays.asList(RealWeedMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, RealWeedKey, true);
					   meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS});
					   Weed.setItemMeta(meta);
					   inv.setResult(Weed);
				   }else {
					   ItemMeta meta = Nonono.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_RED + "Don't use stacks.");
					   meta.setLore(Arrays.asList(NopeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, NOPEKEY, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS});
					   Nonono.setItemMeta(meta);
					   inv.setResult(Nonono);
				   }
			   }
			   //END OF WEED RECIPE
			   
			   
			   // COKE RECIPE
			   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL) 
					   && matrix[4].getEnchantments().containsValue(valuekey)
					   && matrix[7].getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "LavaBukkit Ing.")) {
				   if(matrix[1].getAmount() == 1 
						   && matrix[4].getAmount() == 1
						   && matrix[7].getAmount() == 1) {
					   ItemStack LavaBuk = new ItemStack(Material.LAVA_BUCKET);
					   ItemMeta meta = LavaBuk.getItemMeta();
					   meta.setDisplayName(ChatColor.YELLOW + "Carrot Ing.");
					   meta.setLore(Arrays.asList(LavaBukkitMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL,845, true);
					   meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS});
					   LavaBuk.setItemMeta(meta);
					   inv.setResult(LavaBuk);
					   
				   }else {
					   ItemMeta meta = Nonono.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_RED + "Don't use stacks.");
					   meta.setLore(Arrays.asList(NopeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, NOPEKEY, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS});
					   Nonono.setItemMeta(meta);
					   inv.setResult(Nonono); 
				   }
			   }
			   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL)
					   && matrix[4].getEnchantments().containsValue(845)
					   && matrix[7].getItemMeta().getDisplayName().contentEquals(ChatColor.YELLOW + "Carrot Ing.")) {
				   if(matrix[1].getAmount() ==  1
						   && matrix[4].getAmount() == 1
						   && matrix[7].getAmount() == 1) {
					   ItemStack CarrotIng = new ItemStack(Material.CARROT);
					   ItemMeta meta = CarrotIng.getItemMeta();
					   meta.setDisplayName(ChatColor.AQUA + "Coke Ing.");
					   meta.setLore(Arrays.asList(CarrotIngMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, 632, true);
					   meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS});
					   CarrotIng.setItemMeta(meta);
					   inv.setResult(CarrotIng);
					   
				   }else {
					   ItemMeta meta = Nonono.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_RED + "Don't use stacks.");
					   meta.setLore(Arrays.asList(NopeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, NOPEKEY, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS});
					   Nonono.setItemMeta(meta);
					   inv.setResult(Nonono);
				   }
			   }
			   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL)
					   && matrix[4].getEnchantments().containsValue(632)
					   && matrix[7].getItemMeta().getDisplayName().contentEquals(ChatColor.AQUA + "Coke Ing.")) {
				   if(matrix[1].getAmount() == 1 
						   && matrix[4].getAmount() == 1 
						   && matrix[7].getAmount() == 1) {
					   ItemStack CokeDrug = new ItemStack(Material.SUGAR);
					   ItemMeta meta = CokeDrug.getItemMeta();
					   meta.setDisplayName(ChatColor.GOLD + "COKE");
					   meta.setLore(Arrays.asList(RealCokeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, RealCokeKey, true);
					   meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS});
					   CokeDrug.setItemMeta(meta);
					   inv.setResult(CokeDrug);
				   }else {
					   ItemMeta meta = Nonono.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_RED + "Don't use stacks.");
					   meta.setLore(Arrays.asList(NopeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, NOPEKEY, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS});
					   Nonono.setItemMeta(meta);
					   inv.setResult(Nonono);
				   }
				   
			   }// END OF COKE RECIPE
			   
			   //ACID RECIPE
			   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL) 
					   && matrix[4].getEnchantments().containsValue(valuekey3)
					   && matrix[7].getItemMeta().getDisplayName().contentEquals(ChatColor.AQUA + "FishBukkit Ing.")) {
				   if(matrix[1].getAmount() == 1 
						   && matrix[4].getAmount() == 1
						   && matrix[7].getAmount() == 1) {
					   ItemStack fishbuk = new ItemStack(Material.TROPICAL_FISH_BUCKET);
					   ItemMeta meta = fishbuk.getItemMeta();
					   meta.setDisplayName(ChatColor.LIGHT_PURPLE + "bREAD Ing.");
					   meta.setLore(Arrays.asList(FishBukkitMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, 981, true);
					   meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS});
					   fishbuk.setItemMeta(meta);
					   inv.setResult(fishbuk);
					   
				   }else {
					   ItemMeta meta = Nonono.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_RED + "Don't use stacks.");
					   meta.setLore(Arrays.asList(NopeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, NOPEKEY, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS});
					   Nonono.setItemMeta(meta);
					   inv.setResult(Nonono);
				   }
				   
			   }
			   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL)
					   && matrix[4].getEnchantments().containsValue(981)
					   && matrix[7].getItemMeta().getDisplayName().contentEquals(ChatColor.LIGHT_PURPLE + "bREAD Ing.")) {
				   if(matrix[1].getAmount() == 1
						   && matrix[4].getAmount() == 1
						   && matrix[7].getAmount() == 1) {
					   ItemStack breadin = new ItemStack(Material.BREAD);
					   ItemMeta meta = breadin.getItemMeta();
					   meta.setDisplayName(ChatColor.BLUE + "Acid Ing");
					   meta.setLore(Arrays.asList(bREADMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, 381, true);
					   meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS});
					   breadin.setItemMeta(meta);
					   inv.setResult(breadin);
				   }else {
					   ItemMeta meta = Nonono.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_RED + "Don't use stacks.");
					   meta.setLore(Arrays.asList(NopeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, NOPEKEY, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS});
					   Nonono.setItemMeta(meta);
					   inv.setResult(Nonono); 
				   }
				   
				   
			   }
			   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL)
					   && matrix[4].getEnchantments().containsValue(381)
					   && matrix[7].getItemMeta().getDisplayName().contentEquals(ChatColor.BLUE + "Acid Ing")) {
				   if(matrix[1].getAmount() == 1
						   && matrix[4].getAmount() == 1
						   && matrix[7].getAmount() == 1) {
					   ItemStack paperAcid = new ItemStack(Material.PAPER);
					   ItemMeta meta = paperAcid.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_PURPLE + "ACID");
					   meta.setLore(Arrays.asList(RealAcidMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, RealAcidKey, true);
					   meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ENCHANTS});
					   paperAcid.setItemMeta(meta);
					   inv.setResult(paperAcid);
					   
				   }else {
					   ItemMeta meta = Nonono.getItemMeta();
					   meta.setDisplayName(ChatColor.DARK_RED + "Don't use stacks.");
					   meta.setLore(Arrays.asList(NopeMeta));
					   meta.addEnchant(Enchantment.DAMAGE_ALL, NOPEKEY, true);
					   meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS});
					   Nonono.setItemMeta(meta);
					   inv.setResult(Nonono);
					   
				   }
				   
			   }//END OF ACID RECIPE
			   
			}
	   @EventHandler
	   public void PlayerClick(InventoryClickEvent e) {
		   CraftingInventory inv = (CraftingInventory) e.getInventory();
		   if(inv.getSize() < 9) return;
		   if(e.getCurrentItem().getType() == Material.BARRIER) {
			   if(e.getCurrentItem().getEnchantments().containsKey(Enchantment.DAMAGE_ALL)
					   && e.getCurrentItem().getEnchantments().containsValue(NOPEKEY)
					   && e.getCurrentItem().getItemMeta().getDisplayName().contentEquals(ChatColor.DARK_RED + "Don't use stacks.")) {
				   e.setCancelled(true);
			   }
		   }
	   }
	 
	 /*  @EventHandler //Just in-case above method doesn't work(This doesn't work yet either)
	 public void OnCraftEvent(CraftItemEvent e) {
		 CraftingInventory inv = e.getInventory();
		 Player p = (Player) e.getWhoClicked();
		 if(inv.getSize() < 9) return;
		   ItemStack[] matrix = inv.getMatrix();
		   if(matrix[1].getEnchantments().containsKey(Enchantment.DAMAGE_ALL) 
				   && inv.getResult().equals(Main.wheatd)
				   && matrix[1].getEnchantments().containsValue(valuekey) 
				   && matrix[1].getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Coke")) {
			   if(matrix[1].getAmount() == 1) {

				   
				   
			   }else {
				   e.setCancelled(true);
				   p.sendMessage(Items.prefix2 + "Only 1 item at a time."); 
			   }

		 }
	 }*/
}
