package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.Drug;

public class DeleteDrug implements Listener{

    private Main plugin = Main.plugin;

    private final int maxdrugs = 45;

    private String invName = new String(ChatColor.translateAlternateColorCodes('&', "&4&o&lDELETE DRUGS"));


    @EventHandler
    public void PlayerClick(InventoryClickEvent ev){
        if(ev.getCurrentItem() == null){
            return;
        }

        if(!ev.getView().getTitle().equals(invName)){
            return;
        }

        ItemStack clickedItem = ev.getCurrentItem();
        Player p = (Player) ev.getWhoClicked();
        if(plugin.getDrugManager().isDrugItem(clickedItem)){
            Drug d = plugin.getDrugManager().matchDrug(clickedItem);
            
        }
        if(clickedItem.equals(mainMenu())){
            DrugGUI dgui = new DrugGUI();
            p.openInventory(dgui.drugMainMenu(p));
        }
        ev.setCancelled(true);
    }

    public Inventory editableDrugs(Player p) {
        ArrayList<Drug> drugs = Main.plugin.getDrugManager().getallDrugs();
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

        Inventory inv = Bukkit.createInventory(null, stack.size(), invName);

        inv.setContents(stack.toArray(new ItemStack[0]));
        for(ItemStack s :inv.getContents()){
            s.setAmount(1);
        }
        inv.setItem(22, mainMenu());
        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.5f , 1.0f);
        return inv;
    }

    public Inventory openPage(int page) {
        ArrayList<Drug> drugs = Main.plugin.getDrugManager().getallDrugs();
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

        Inventory inv = Bukkit.createInventory(null, stack.size(), invName);
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
    private ItemStack mainMenu(){
        ItemStack s = new ItemStack(Material.ARROW);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&o<- Back To Main Menu"));
        List<String> sList = new ArrayList<>();
        sList.add(ChatColor.translateAlternateColorCodes('&', "&8&oTakes you back to the Drug Creator"));
        im.setLore(sList);
        s.setItemMeta(im);
        return s;
    }
}
