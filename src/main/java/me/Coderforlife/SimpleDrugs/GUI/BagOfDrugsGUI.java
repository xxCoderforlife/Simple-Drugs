package me.Coderforlife.SimpleDrugs.GUI;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BagOfDrugsGUI {

    private static final int maxdrugs = 45;

    public Inventory create() {
        ArrayList<Drug> drugs = Drug.getallDrugs();
        int amountofdrugs = drugs.size();

        ArrayList<ItemStack> stack = new ArrayList<>();
        amountofdrugs = Math.min(amountofdrugs, maxdrugs);

        for (int i = 0; i < amountofdrugs; i++) {
            stack.add(drugs.get(i).getItem());
        }
        while (stack.size() % 9 != 0) {
            stack.add(new ItemStack(Material.AIR));
        }

        stack.add(BackwardButton(false, 1));
        for (int i = 0; i < 7; i++) {
            stack.add(GreyGlassPane());
        }
        stack.add(ForwardButton(drugs.size() > maxdrugs, 2));

        Inventory inv = Bukkit.createInventory(null, stack.size(), "§6§l§oBag Of Drugs");

        inv.setContents(stack.toArray(new ItemStack[0]));
        return inv;
    }

    public Inventory openPage(int page) {
        ArrayList<Drug> drugs = Drug.getallDrugs();
        int amountofdrugs = drugs.size();

        int drugsleft = amountofdrugs - ((page - 1) * maxdrugs);
        int startfrom = (maxdrugs * (page - 1));

        ArrayList<ItemStack> stack = new ArrayList<>();
        amountofdrugs = Math.min(drugsleft, maxdrugs);

        for (int i = startfrom; i < amountofdrugs + (startfrom - 1); i++) {
            stack.add(drugs.get(i).getItem());
        }
        while (stack.size() % 9 != 0) {
            stack.add(new ItemStack(Material.AIR));
        }

        stack.add(BackwardButton(true, page - 1));
        for (int i = 0; i < 7; i++) {
            stack.add(GreyGlassPane());
        }
        stack.add(ForwardButton(drugs.size() > maxdrugs * page, page + 1));

        Inventory inv = Bukkit.createInventory(null, stack.size(), "§6§l§oBag Of Drugs");
        inv.setContents(stack.toArray(new ItemStack[0]));
        return inv;
    }

    private ItemStack GreyGlassPane() {
        ItemStack stack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(" ");
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack ForwardButton(boolean active, int page) {
        ItemStack stack = new ItemStack(Material.ARROW);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§6Page " + page);
        if (!active) {
            stack.setType(Material.BARRIER);
            meta.setDisplayName("§cYou are at the last page");
        }
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack BackwardButton(boolean active, int page) {
        ItemStack stack = new ItemStack(Material.ARROW);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§6Page " + page);
        if (!active) {
            stack.setType(Material.BARRIER);
            meta.setDisplayName("§cYou are at the first page");
        }
        stack.setItemMeta(meta);
        return stack;
    }


}
