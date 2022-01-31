package me.Coderforlife.SimpleDrugs.GUI;

import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDFurnace;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDRecipe;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShapeless;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import net.md_5.bungee.api.ChatColor;
import me.Coderforlife.SimpleDrugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class RecipeGUI implements Listener{

    public RecipeGUI() {

    }

    private Drug drug;
    private Main plugin = Main.plugin;
    private Inventory inv;
    private HashMap<ItemStack, Integer> needed = new HashMap<>();


    public RecipeGUI(Drug d) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.drug = d;

    }

    // TODO: Recreate

    public Inventory create(Player p) {
        if(drug.getRecipe() instanceof SDShapeless){
            inv = Bukkit.createInventory(null, InventoryType.WORKBENCH);
           // ArrayList<ItemStack> stack = new ArrayList<>();
            // ShapedRecipe recipe = (ShapedRecipe) drug.getRecipe();
    
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
    
        }
    
        if(drug.getRecipe() instanceof SDShaped){
            inv = Bukkit.createInventory(p, InventoryType.WORKBENCH);
           // ArrayList<ItemStack> stack = new ArrayList<>();
            // ShapedRecipe recipe = (ShapedRecipe) drug.getRecipe();
    
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
        }
        if(drug.getRecipe() instanceof SDFurnace){
             inv = Bukkit.createInventory(null, InventoryType.FURNACE, drug.getDisplayname() + 
            ChatColor.translateAlternateColorCodes('&', " &6&lRecipe"));
        }
        return inv;


        /**for(int i = 0; i < 10; i++) {
            stack.add(blackglass());
        }

        // First row
        stack.add(recipe.getItems().get(0));
        stack.add(recipe.getItems().get(1));
        stack.add(recipe.getItems().get(2));

        for(int i = 0; i < 6; i++) {
            stack.add(blackglass());
        }

        // Second row
        stack.add(recipe.getItems().get(3));
        stack.add(recipe.getItems().get(4));
        stack.add(recipe.getItems().get(5));

        for(int i = 0; i < 2; i++) {
            stack.add(blackglass());
        }

        // Result Item
        stack.add(drug.getItem());

        for(int i = 0; i < 3; i++) {
            stack.add(blackglass());
        }

        stack.add(recipe.getItems().get(6));
        stack.add(recipe.getItems().get(7));
        stack.add(recipe.getItems().get(8));

        for(int i = 0; i < 13; i++) {
            stack.add(blackglass());
        }

        if(cancraft(p, recipe)) {
            stack.add(craftingTable());
        } else {
            stack.add(noItems());
        }

        inv.setContents(stack.toArray(new ItemStack[45]));*/
   }
    @EventHandler
    public void handleClick(InventoryClickEvent ev) {
        if(ev.getInventory().equals(inv)){
            Player p = (Player) ev.getWhoClicked();
            if(ev.getCurrentItem() == null){return;}
            if(ev.getCurrentItem().equals(drug.getItem())){
                if(cancraft(p, drug.getRecipe())){
                    for(ItemStack s : drug.getRecipe().getItems()){
                        removeInventoryItems(p.getInventory(), s.getType(), 1);
                    }
                    p.sendMessage(plugin.getMessages().getPrefix() + "You crafted " + drug.getDisplayname());
                    p.getInventory().addItem(drug.getItem());
                    p.closeInventory();
                }else{
                    p.sendMessage(plugin.getMessages().getPrefix() + "You can't craft " + drug.getDisplayname());
                }
            }
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void handleDrag(InventoryDragEvent ev) {
        if(ev.getInventory().equals(inv)){
        if(ev.getInventory() != null && !ev.getInventory().getType().equals(InventoryType.PLAYER)) {
            ev.setCancelled(true);
        }
    }
}
  public void removeInventoryItems(PlayerInventory inv, Material type, int amount) {
    for (ItemStack is : inv.getContents()) {
        if(is == null){return;}
        for(Drug d : plugin.getDrugManager().getallDrugs()){
            if(is.isSimilar(d.getItem())){
                return;
            }
        }
        if (is != null && is.getType() == type) {
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

    private ItemStack blackglass() {
        ItemStack stack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(" ");
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack craftingTable() {
        ItemStack craft = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta im = craft.getItemMeta();
        im.setDisplayName("§3§lClick Me To Craft The Drug");
        craft.setItemMeta(im);
        return craft;
    }
    private ItemStack noItems(){
        ItemStack bar = new ItemStack(Material.BARRIER);
        ItemMeta im = bar.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&o&lYou don't have enough items to make " + drug.getDisplayname()));
        bar.setItemMeta(im);
        return bar;
    }

    private boolean cancraft(Player p, SDRecipe recipe) {
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
