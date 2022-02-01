package me.Coderforlife.SimpleDrugs.GUI;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.BagOfDrugs;
import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.GUI.Framework.SDRecipeInventory;
import net.md_5.bungee.api.ChatColor;

public class BagOfDrugsGUI implements Listener {
    private Main plugin = Main.plugin;

    public BagOfDrugsGUI(){
    }
    private BagOfDrugs bd = new BagOfDrugs();
    private final int maxdrugs = 45;
    public final String bagName = "§6§lBag Of Drugs";
    public final String invName = "§6§l§oBag Of Drugs";

    @EventHandler
    public void BagOpen(PlayerInteractEvent ev) {
        Player p = ev.getPlayer();
        Action pa = ev.getAction();

        if (pa.equals(Action.RIGHT_CLICK_AIR) || pa.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (p.hasPermission("drugs.use.bagofdrugs")) {
                if (p.getInventory().getItemInMainHand().equals(bd.getBagOfDrugs())) {
                    Location loc = p.getLocation();
                    for (int degree = 0; degree < 360; degree++) {
                        double radians = Math.toRadians(degree);
                        double x = Math.cos(radians);
                        double z = Math.sin(radians);
                        loc.add(x, 0, z);
                        loc.getWorld().playEffect(loc, Effect.SMOKE, degree);
                        loc.subtract(x, 0, z);
                    }
                    p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1, (float) 0.4);
                    p.openInventory(create());
                }
            }else{
                //TODO Add perm message
            }
        }
    }

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent ev) {
        Player p = (Player) ev.getWhoClicked();
        ItemStack clickedItem = ev.getCurrentItem();
        if (clickedItem == null){return;}

        if (Main.plugin.getSettings().isBagOfDrugs_CanMove()) {
            if (clickedItem.getItemMeta().getDisplayName().equals(invName)) {
                ev.setCancelled(true);
                p.getItemOnCursor();
                p.setItemOnCursor(null);
            }
        }

        if (!ev.getView().getTitle().equals(invName)) {
            return;
        }
        ev.setCancelled(true);

        String itemname = clickedItem.getItemMeta().getDisplayName();
        String[] pagenumber = itemname.split(" ");

        if (ev.getCurrentItem().getType().equals(Material.ARROW) && itemname.startsWith("§6Page")) {
            int page = Integer.parseInt(pagenumber[1]);
            if (page == 1) {
                p.openInventory(this.create());
            } else {
                p.openInventory(this.openPage(page));
            }
            return;
        }
        if(plugin.getDrugManager().isDrugItem(clickedItem)){
            Drug d = plugin.getDrugManager().matchDrug(clickedItem);
            if(ev.getClick() == ClickType.SHIFT_LEFT){
                ItemStack d64 = d.getItem();
                p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, (float) 1.0, (float) 1.0);
                d64.setAmount(64);
                p.getInventory().addItem(d64);
            }   
            if(ev.isLeftClick()){
                p.getInventory().addItem(d.getItem());
                p.playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, (float) 1.0, (float) 1.0);
                p.sendMessage(plugin.getMessages().getPrefix() + 
                ChatColor.translateAlternateColorCodes('&', "You've been given " + d.getDisplayname()));
            }
            if(ev.isRightClick()){
                p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, (float) 1.0, (float) 1.0);
                p.closeInventory();
                new SDRecipeInventory(d).createSDRecipeInventory(p);
            }
        }
    }

    public Inventory create() {
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
}
