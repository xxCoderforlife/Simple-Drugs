package me.Coderforlife.SimpleDrugs.GUI;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BagOfDrugsGUI {

    private static final int maxdrugs = 45;
    public static String bagName = "§6§lBag Of Drugs";
    public static String invName = "§6§l§oBag Of Drugs";
    private final String sober = ChatColor.ITALIC + "Remove Drugs With" + " §c/d soberup";

    public Inventory create() {
        ArrayList<Drug> drugs = new Drug().getallDrugs();
        int amountofdrugs = drugs.size();

        ArrayList<ItemStack> stack = new ArrayList<>();
        amountofdrugs = Math.min(amountofdrugs, maxdrugs);

        for(int i = 0; i < amountofdrugs; i++) {
            stack.add(drugs.get(i).getItem());
        }
        while(stack.size() % 9 != 0) {
            stack.add(new ItemStack(Material.AIR));
        }

        stack.add(BackwardButton(false, 1));
        for(int i = 0; i < 7; i++) {
            stack.add(GreyGlassPane());
        }
        stack.add(ForwardButton(drugs.size() > maxdrugs, 2));

        Inventory inv = Bukkit.createInventory(null, stack.size(), invName);

        inv.setContents(stack.toArray(new ItemStack[0]));
        return inv;
    }

    public Inventory openPage(int page) {
        ArrayList<Drug> drugs = new Drug().getallDrugs();
        int amountofdrugs = drugs.size();

        int drugsleft = amountofdrugs - ((page - 1) * maxdrugs);
        int startfrom = (maxdrugs * (page - 1));

        ArrayList<ItemStack> stack = new ArrayList<>();
        amountofdrugs = Math.min(drugsleft, maxdrugs);

        for(int i = startfrom; i < amountofdrugs + (startfrom - 1); i++) {
            stack.add(drugs.get(i).getItem());
        }
        while(stack.size() % 9 != 0) {
            stack.add(new ItemStack(Material.AIR));
        }

        stack.add(BackwardButton(true, page - 1));
        for(int i = 0; i < 7; i++) {
            stack.add(GreyGlassPane());
        }
        stack.add(ForwardButton(drugs.size() > maxdrugs * page, page + 1));

        Inventory inv = Bukkit.createInventory(null, stack.size(), invName);
        inv.setContents(stack.toArray(new ItemStack[0]));
        return inv;
    }

    public void handleInventoryClick(InventoryClickEvent ev) {
        Player p = (Player) ev.getWhoClicked();
        ItemStack clickedItem = ev.getCurrentItem();
        if(clickedItem == null || clickedItem.getType().isAir() || !clickedItem.hasItemMeta())
            return;

        if(Settings.BagOfDrugs_CanMove) {
            if(clickedItem.getItemMeta().getDisplayName().equals(invName)) {
                ev.setCancelled(true);
                p.getItemOnCursor();
                p.setItemOnCursor(null);
            }
        }

        if(!ev.getView().getTitle().equals(invName)) {
            return;
        }
        ev.setCancelled(true);

        BagOfDrugsGUI bag = new BagOfDrugsGUI();
        String itemname = clickedItem.getItemMeta().getDisplayName();
        String[] pagenumber = itemname.split(" ");

        if(ev.getCurrentItem().getType().equals(Material.ARROW) && itemname.startsWith("§6Page")) {
            int page = Integer.parseInt(pagenumber[1]);
            if(page == 1) {
                p.openInventory(bag.create());
            } else {
                p.openInventory(bag.openPage(page));
            }
            return;
        }

        for(Drug drug : new Drug().getallDrugs()) {
            if(!drug.isDrugItem(clickedItem)) {
                continue;
            }
            p.getInventory().addItem(drug.getItem());
            p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drug.getDisplayname());
            p.playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1, (float) 0.5);
            p.closeInventory();
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));
        }
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
        if(!active) {
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
        if(!active) {
            stack.setType(Material.BARRIER);
            meta.setDisplayName("§cYou are at the first page");
        }
        stack.setItemMeta(meta);
        return stack;
    }


}
