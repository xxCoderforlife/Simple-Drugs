package me.Coderforlife.SimpleDrugs.GUI.Shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import net.md_5.bungee.api.ChatColor;

public class drugShopGUI implements Listener {

    public drugShopGUI() {

    }

    private final int maxdrugs = 45;
    private final String invName = new String(ChatColor.translateAlternateColorCodes('&', "&a&lDrug Shop"));
    private final String prefix = Main.plugin.getMessages().getPrefix();
    private ArrayList<ItemStack> pInv = new ArrayList<>();
    private HashMap<UUID,ArrayList<ItemStack>> pInv2 = new HashMap<>();

    @EventHandler
    public void onClick(InventoryClickEvent ev) {
        Player p = (Player) ev.getWhoClicked();
        if (ev.getView().getTitle().equalsIgnoreCase(invName)) {
            Drug d = Main.plugin.getDrugManager().matchDrug(ev.getCurrentItem());
            if (ev.getCurrentItem() == null || d == null) {
                return;
            }
            // Buying
            if (ev.getClick() == ClickType.LEFT) {
                if (Main.plugin.getEconomy().getBalance(p) >= d.getBuyPrice()) {
                    Main.plugin.getEconomy().withdrawPlayer(p, d.getBuyPrice());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&a&lYou have bought &b&l" + d.getName() + "&a&l for &b&l" + d.getBuyPrice() + "&a&l."));
                    p.getInventory().addItem(d.getItem());
                } else {
                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',
                            "&c&lYou don't have enough money to buy this drug."));
                }
            } // Selling
            else if (ev.getClick() == ClickType.RIGHT) {
                Inventory inv = p.getInventory();
                if(!inv.contains(d.getItem())){
                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', 
                        "&c&lYou don't have this drug."));
                        ev.setCancelled(true);
                        return;
                }
                for (ItemStack s : inv.getContents()) {
                    if(s == null){
                        continue;
                    }
                    if (s.getItemMeta().getDisplayName().equalsIgnoreCase(d.getItem().getItemMeta().getDisplayName())
                    && s.getType() == d.getItem().getType()) {
                        Main.plugin.getEconomy().depositPlayer(p, d.getSellPrice());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                "&a&lYou have sold &b&l" + d.getName() + "&a&l for &b&l" + d.getSellPrice() + "&a&l."));
                                if(s.getAmount() > 1){
                                    s.setAmount(s.getAmount() - 1);

                                }else{
                                    inv.remove(s);
                                }
                        return;
                    }

                }
            }
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent ev) {
        if(ev.getView().getTitle().equalsIgnoreCase(invName)){
            Player p = (Player) ev.getPlayer();
            Inventory inv = p.getInventory();
            for(ItemStack is : inv.getStorageContents()){
                pInv.add(is);
            }

        }
    }
    @EventHandler
    public void onnClose(InventoryCloseEvent ev){
        if(ev.getView().getTitle().equalsIgnoreCase(invName)){

        }
    }
    public Inventory openShopGUI() {
        ArrayList<Drug> drugs = new ArrayList<>(Main.plugin.getDrugManager().getItems().values());
        int amountofdrugs = drugs.size();

        ArrayList<ItemStack> stack = new ArrayList<>();
        amountofdrugs = Math.min(amountofdrugs, maxdrugs);

        for (int i = 0; i < amountofdrugs; i++) {
            Drug drug = drugs.get(i);
            ItemStack d = drug.getItem();
            ItemMeta im = d.getItemMeta();
            im.getLore().clear();
            List<String> lores = new ArrayList<>();
            lores.add(ChatColor.GRAY + "Buy Price: " + ChatColor.DARK_GREEN + drug.getBuyPrice());
            lores.add(ChatColor.GRAY + "Sell Price: " + ChatColor.GREEN + drug.getSellPrice());
            lores.add(ChatColor.translateAlternateColorCodes('&',
                    "&a&oLeft-Click to buy."));
            lores.add(ChatColor.translateAlternateColorCodes('&',
                    "&c&oRight-Click to sell."));
            im.setLore(lores);
            d.setItemMeta(im);
            stack.add(d);
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
        for (ItemStack m : inv.getContents()) {
            m.setAmount(1);
        }
        return inv;
    }

    public Inventory openPage(int page) {
        ArrayList<Drug> drugs = new ArrayList<>(Main.plugin.getDrugManager().getItems().values());
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

        stack.add(BackwardButton(page > 1, page - 1));
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
        if (!active)
            return GreyGlassPane();

        ItemStack stack = new ItemStack(Material.ARROW);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§6Page " + page);
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack BackwardButton(boolean active, int page) {
        if (!active)
            return GreyGlassPane();

        ItemStack stack = new ItemStack(Material.ARROW);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§6Page " + page);
        stack.setItemMeta(meta);
        return stack;
    }

}
