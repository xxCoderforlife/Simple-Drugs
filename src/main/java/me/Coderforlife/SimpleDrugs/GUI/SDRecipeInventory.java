package me.Coderforlife.SimpleDrugs.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.Brewing.SDBrewingRecipe;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Druging.Util.DrugEffect;
import me.Coderforlife.SimpleDrugs.Util.CCMaterialConverter;
import net.md_5.bungee.api.ChatColor;

public class SDRecipeInventory implements Listener {
    private Main plugin = Main.plugin;
    private Inventory inv;
    private Drug drug;
    private Set<UUID> players = new HashSet<UUID>();
    private boolean registered = false;

    public SDRecipeInventory(Drug d) {
        this.drug = d;
    }

    public void createSDRecipeInventory(Player p) {
        ItemMeta im = this.drug.getItem().getItemMeta();
        List<String> lores = new ArrayList<>();
        im.getLore().clear();
        im.setLore(lores);
        for(DrugEffect ef : this.drug.getEffects()){
            lores.add(ChatColor.translateAlternateColorCodes('&', 
            "&7- &6&o" + ef.getEffect().getKey().getKey()));
        }
        lores.add(ChatColor.translateAlternateColorCodes('&', 
        "&7&oClick to craft."));
        im.setLore(lores);
        this.drug.getItem().setItemMeta(im);
        if (drug.getRecipe() instanceof SDShapeless) {
            inv = Bukkit.createInventory(null, InventoryType.WORKBENCH, 
            ChatColor.translateAlternateColorCodes('&', drug.getDisplayName() + " &6&lRecipe"));

            SDShapeless recipe = (SDShapeless) drug.getRecipe();
            inv.setItem(0, drug.getItem());
            inv.setItem(1, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(0)));
            inv.setItem(2, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(1)));
            inv.setItem(3, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(2)));
            inv.setItem(4, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(3)));
            inv.setItem(5, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(4)));
            inv.setItem(6, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(5)));
            inv.setItem(7, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(6)));
            inv.setItem(8, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(7)));
            inv.setItem(9, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(8)));
            if (players.contains(p.getUniqueId()))
                return;
            players.add(p.getUniqueId());
            if (players.size() == 1 && !registered)
                plugin.getServer().getPluginManager().registerEvents(this, plugin);
            registered = true;
            p.openInventory(inv);

        }

        if (drug.getRecipe() instanceof SDShaped) {
            inv = Bukkit.createInventory(null, InventoryType.WORKBENCH,
            ChatColor.translateAlternateColorCodes('&', drug.getDisplayName() + " &6&lRecipe"));
            // ArrayList<ItemStack> stack = new ArrayList<>();

            SDShaped recipe1 = (SDShaped) drug.getRecipe();
            inv.setItem(0, drug.getItem());
            inv.setItem(1, CCMaterialConverter.getCCOrMaterial(null, recipe1.getItems().get(0)));
            inv.setItem(2, CCMaterialConverter.getCCOrMaterial(null, recipe1.getItems().get(1)));
            inv.setItem(3, CCMaterialConverter.getCCOrMaterial(null, recipe1.getItems().get(2)));
            inv.setItem(4, CCMaterialConverter.getCCOrMaterial(null, recipe1.getItems().get(3)));
            inv.setItem(5, CCMaterialConverter.getCCOrMaterial(null, recipe1.getItems().get(4)));
            inv.setItem(6, CCMaterialConverter.getCCOrMaterial(null, recipe1.getItems().get(5)));
            inv.setItem(7, CCMaterialConverter.getCCOrMaterial(null, recipe1.getItems().get(6)));
            inv.setItem(8, CCMaterialConverter.getCCOrMaterial(null, recipe1.getItems().get(7)));
            inv.setItem(9, CCMaterialConverter.getCCOrMaterial(null, recipe1.getItems().get(8)));
            if (players.contains(p.getUniqueId()))
                return;
            players.add(p.getUniqueId());
            if (players.size() == 1 && !registered)
                Main.plugin.getServer().getPluginManager().registerEvents(this, Main.plugin);
            registered = true;
            p.openInventory(inv);
        }
        if (drug.getRecipe() instanceof SDFurnace) {
            ItemStack coal = new ItemStack(Material.COAL);
            ItemStack charcoal = new ItemStack(Material.CHARCOAL);
            ItemStack blaze = new ItemStack(Material.BLAZE_POWDER);
            ItemStack tool = new ItemStack(Material.WOODEN_PICKAXE);
            inv = Bukkit.createInventory(null, InventoryType.FURNACE,
            ChatColor.translateAlternateColorCodes('&', drug.getDisplayName() + " &6&lRecipe"));
            SDFurnace recipe = (SDFurnace) drug.getRecipe();
            inv.setItem(0, CCMaterialConverter.getCCOrMaterial(null, recipe.getItems().get(0)));
            inv.setItem(2, drug.getItem());
            inv.setItem(1, new ItemStack(Material.FIRE));
            new BukkitRunnable() {
                @Override
                public void run(){
                    int chance = new Random().nextInt(11);
                    if(chance < 3){
                        inv.setItem(1, coal);
                        p.updateInventory();
                    }else
                    if(chance < 6){
                        inv.setItem(1, charcoal);
                        p.updateInventory();
                    }else
                    if(chance < 9){
                        inv.setItem(1, blaze);
                        p.updateInventory();
                    }else{
                        inv.setItem(1, tool);
                        p.updateInventory();
                    }
                    p.updateInventory();

                }
            }.runTaskTimer(plugin, 20 * 1, 20 * 1);
            if (players.contains(p.getUniqueId()))
                return;
            players.add(p.getUniqueId());
            if (players.size() == 1 && !registered)
                Main.plugin.getServer().getPluginManager().registerEvents(this, Main.plugin);
            registered = true;
            p.openInventory(inv);
        }
        if(drug.getRecipe() instanceof SDBrewingRecipe){
            inv = Bukkit.createInventory(null, InventoryType.BREWING,drug.getDisplayName() + 
            ChatColor.translateAlternateColorCodes('&', "&6&l Recipe"));
            if (players.contains(p.getUniqueId()))
                return;
            players.add(p.getUniqueId());
            if (players.size() == 1 && !registered)
                Main.plugin.getServer().getPluginManager().registerEvents(this, Main.plugin);
            registered = true;
            p.openInventory(inv);
            // TODO Add Brewing Recipe
        }

    }

    @EventHandler
    public final void onInvClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player))
            return;
        if (!e.getInventory().equals(inv))
            return;
        Player p = (Player) e.getPlayer();
        players.remove(p.getUniqueId());
    }
    @EventHandler
    public void InventoryClick(InventoryClickEvent ev){
        if(ev.getClickedInventory() == null){
            return;
        }
        if(ev.getCurrentItem() == null){
            return;
        }
        if(!(ev.getInventory().equals(inv))){
            return;
        }
        Player p = (Player) ev.getWhoClicked();
        if(ev.getCurrentItem().equals(drug.getItem())){
            if(p.getGameMode() == GameMode.CREATIVE){
                p.sendMessage("SD " + "You crafted " + drug.getDisplayName());
                p.getInventory().addItem(drug.getItem());
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, (float) 1.0, (float) 1.0);
                p.closeInventory();
                return;
            }
            if(cancraft(p, drug.getRecipe())){
                for(String s : drug.getRecipe().getItems()){
                    removeInventoryItems(p.getInventory(), CCMaterialConverter.getCCOrMaterial(null, s).getType(), 1);
                }
                p.sendMessage("SD " + "You crafted " + drug.getDisplayName());
                p.getInventory().addItem(drug.getItem());
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, (float) 1.0, (float) 1.0);
                p.closeInventory();
            }else{
                p.sendMessage("SD " + "You can't craft " + drug.getDisplayName());
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, (float) 1.0, (float) 1.0);

            }
        }
        ev.setCancelled(true);

    }
    private void removeInventoryItems(PlayerInventory inv, Material drug, int amount) {
        for (ItemStack is : inv.getContents()) {
                if(plugin.getDrugManager().isDrugItem(is)){
                    return;
            }
            if (is != null && is.getType() == drug) {
                int newamount = is.getAmount() - amount;
                if (newamount > 0) {
                    is.setAmount(newamount);
                    break;
                } else {
                    inv.remove(is);
                    amount = -newamount;
                    if (amount == 0) break;
                }
            }
        }
    }
    private boolean cancraft(Player p, SDRecipe recipe) {
        HashMap<ItemStack, Integer> needed = new HashMap<>();
       for(String stack : recipe.getItems()) {
           if(stack.equals("AIR")) {
               continue;
           }
           if(needed.containsKey(CCMaterialConverter.getCCOrMaterial(null, stack))) {
               needed.put(CCMaterialConverter.getCCOrMaterial(null, stack), needed.get(CCMaterialConverter.getCCOrMaterial(null, stack)) + 1);
           } else {
               needed.put(CCMaterialConverter.getCCOrMaterial(null, stack), 1);
           }
       }
       for(ItemStack stack : needed.keySet()) {
           if(!p.getInventory().containsAtLeast(stack, needed.get(stack)))
               return false;
       }
       return true;
   }
   @EventHandler(priority = EventPriority.HIGH)
		public final void onPlayerLeave(PlayerQuitEvent e) {
			Player p = e.getPlayer();
			if (players.contains(p.getUniqueId()))
				players.remove(p.getUniqueId());
		}
}
