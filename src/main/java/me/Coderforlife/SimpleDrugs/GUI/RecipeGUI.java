package me.Coderforlife.SimpleDrugs.GUI;

import me.Coderforlife.SimpleDrugs.Crafting.Recipes.SDShaped;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeGUI {

    public RecipeGUI() {

    }

    private Drug drug;

    public RecipeGUI(Drug d) {
        this.drug = d;

    }

    private Main plugin = Main.plugin;
    // TODO: Recreate

    public Inventory create(Player p) {
        Inventory inv = Bukkit.createInventory(null, 45, "§6§l " + drug.getName() + " Recipe");
        ArrayList<ItemStack> stack = new ArrayList<>();
        // ShapedRecipe recipe = (ShapedRecipe) drug.getRecipe();

        SDShaped recipe = (SDShaped) drug.getRecipe();

        for(int i = 0; i < 10; i++) {
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
            stack.add(new ItemStack(Material.BARRIER));
        }

        inv.setContents(stack.toArray(new ItemStack[45]));
        return inv;
    }

    public void handleClick(InventoryClickEvent ev) {
        if(ev.getClick().isShiftClick() || ev.getClick().isRightClick()) {
            ev.setCancelled(true);
            return;
        }

        if(ev.getClickedInventory() != null && !ev.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            if(ev.getCurrentItem().equals(craftingTable())) {
                ev.setCancelled(true);
                Player p = (Player) ev.getWhoClicked();
                Drug drug = Main.plugin.getDrugManager().getDrug(ev.getView().getTitle().split(" ")[1].toUpperCase());

                SDShaped recipe = (SDShaped) drug.getRecipe();

                ev.setCancelled(true);
            }
        }
    }

    public void handleDrag(InventoryDragEvent ev) {
        if(ev.getInventory() != null && !ev.getInventory().getType().equals(InventoryType.PLAYER)) {
            ev.setCancelled(true);
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

    private boolean cancraft(Player p, SDShaped recipe) {
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
