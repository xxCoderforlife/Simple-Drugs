package me.Coderforlife.SimpleDrugs.GUI;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RecipeGUI {

    public Inventory create(Drug drug) {
        Inventory inv = Bukkit.createInventory(null, 45, "§6§l" + drug.getName() + " Recipe");
        ArrayList<ItemStack> stack = new ArrayList<>();
        ShapedRecipe recipe = (ShapedRecipe) drug.getRecipe();

        for(int i = 0; i < 10; i++) {
            stack.add(blackglass());
        }

        // First row
        stack.add(recipe.getIngredientMap().get('A'));
        stack.add(recipe.getIngredientMap().get('B'));
        stack.add(recipe.getIngredientMap().get('C'));

        for(int i = 0; i < 6; i++) {
            stack.add(blackglass());
        }

        // Second row
        stack.add(recipe.getIngredientMap().get('D'));
        stack.add(recipe.getIngredientMap().get('E'));
        stack.add(recipe.getIngredientMap().get('F'));

        for(int i = 0; i < 2; i++) {
            stack.add(blackglass());
        }

        // Result Item
        stack.add(drug.getItem());

        for(int i = 0; i < 3; i++) {
            stack.add(blackglass());
        }

        stack.add(recipe.getIngredientMap().get('G'));
        stack.add(recipe.getIngredientMap().get('H'));
        stack.add(recipe.getIngredientMap().get('I'));

        for(int i = 0; i < 14; i++) {
            stack.add(blackglass());
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
            ev.setCancelled(true);
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

}
