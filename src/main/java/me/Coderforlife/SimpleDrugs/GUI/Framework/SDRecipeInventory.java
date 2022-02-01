package me.Coderforlife.SimpleDrugs.GUI.Framework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
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
        if (drug.getRecipe() instanceof SDShapeless) {
            inv = Bukkit.createInventory(null, InventoryType.WORKBENCH, "inventoryName");

            SDShapeless recipe = (SDShapeless) drug.getRecipe();
            inv.setItem(0, drug.getItem());
            inv.setItem(1, recipe.getItems().get(0));
            inv.setItem(2, recipe.getItems().get(1));
            inv.setItem(3, recipe.getItems().get(2));
            inv.setItem(4, recipe.getItems().get(3));
            inv.setItem(5, recipe.getItems().get(4));
            inv.setItem(6, recipe.getItems().get(5));
            inv.setItem(7, recipe.getItems().get(6));
            inv.setItem(8, recipe.getItems().get(7));
            inv.setItem(9, recipe.getItems().get(8));
            if (players.contains(p.getUniqueId()))
                return;
            players.add(p.getUniqueId());
            if (players.size() == 1 && !registered)
                plugin.getServer().getPluginManager().registerEvents(this, plugin);
            registered = true;
            p.openInventory(inv);

        }

        if (drug.getRecipe() instanceof SDShaped) {
            inv = Bukkit.createInventory(null, InventoryType.WORKBENCH, drug.getDisplayname() +
            ChatColor.translateAlternateColorCodes('&', " &6&lRecipe"));
            // ArrayList<ItemStack> stack = new ArrayList<>();

            SDShaped recipe1 = (SDShaped) drug.getRecipe();
            inv.setItem(0, drug.getItem());
            inv.setItem(1, recipe1.getItems().get(0));
            inv.setItem(2, recipe1.getItems().get(1));
            inv.setItem(3, recipe1.getItems().get(2));
            inv.setItem(4, recipe1.getItems().get(3));
            inv.setItem(5, recipe1.getItems().get(4));
            inv.setItem(6, recipe1.getItems().get(5));
            inv.setItem(7, recipe1.getItems().get(6));
            inv.setItem(8, recipe1.getItems().get(7));
            inv.setItem(9, recipe1.getItems().get(8));
            if (players.contains(p.getUniqueId()))
                return;
            players.add(p.getUniqueId());
            if (players.size() == 1 && !registered)
                Main.plugin.getServer().getPluginManager().registerEvents(this, Main.plugin);
            registered = true;
            p.openInventory(inv);
        }
        if (drug.getRecipe() instanceof SDFurnace) {
            inv = Bukkit.createInventory(null, InventoryType.FURNACE,
                    ChatColor.translateAlternateColorCodes('&', " &6&lRecipe"));
            if (players.contains(p.getUniqueId()))
                return;
            players.add(p.getUniqueId());
            if (players.size() == 1 && !registered)
                Main.plugin.getServer().getPluginManager().registerEvents(this, Main.plugin);
            registered = true;
            p.openInventory(inv);
            // TODO Add Furnace Inventory
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
                p.sendMessage(plugin.getMessages().getPrefix() + "You crafted " + drug.getDisplayname());
                p.getInventory().addItem(drug.getItem());
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, (float) 1.0, (float) 1.0);
                p.closeInventory();
                return;
            }
            if(cancraft(p, drug.getRecipe())){
                for(ItemStack s : drug.getRecipe().getItems()){
                    removeInventoryItems(p.getInventory(), s.getType(), 1);
                }
                p.sendMessage(plugin.getMessages().getPrefix() + "You crafted " + drug.getDisplayname());
                p.getInventory().addItem(drug.getItem());
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, (float) 1.0, (float) 1.0);
                p.closeInventory();
            }else{
                p.sendMessage(plugin.getMessages().getPrefix() + "You can't craft " + drug.getDisplayname());
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
       for(ItemStack stack : recipe.getItems()) {
           if(stack.getType().equals(Material.AIR)) {
               continue;
           }
           if(needed.containsKey(stack)) {
               needed.put(stack, needed.get(stack) + 1);
           } else {
               needed.put(stack, 1);
           }
       }
       for(ItemStack stack : needed.keySet()) {
           if(!p.getInventory().containsAtLeast(stack, needed.get(stack)))
               return false;
       }
       return true;
   }
}
