package me.Coderforlife.SimpleDrugs.GUI;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BagOfDrugsGUI implements Listener {
    private Main plugin = Main.plugin;
    Settings s = plugin.getSettings();

    public BagOfDrugsGUI() {
    }

    private ItemStack bag = BagOfDrugsStack();
    private final int maxdrugs = 45;
    public final String bagName = "§6§lBag Of Drugs";
    public final String invName = "§6§l§oBag Of Drugs";

    @EventHandler
    public void BagOpen(PlayerInteractEvent ev) {
        if(ev.getHand() == null) {
            return;
        }
        if(ev.getHand().equals(EquipmentSlot.OFF_HAND)) {
            return;
        }
        Player p = ev.getPlayer();
        Action pa = ev.getAction();
        if(p.getInventory().getItemInMainHand().getItemMeta() == null) {
            return;
        }

        if(pa.equals(Action.RIGHT_CLICK_AIR) || pa.equals(Action.RIGHT_CLICK_BLOCK)) {
            if(p.hasPermission("drugs.use.bagofdrugs")) {
                if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(bagName) && p.getInventory().getItemInMainHand()
                        .hasItemMeta()) {
                    Location loc = p.getLocation();
                    for(int degree = 0; degree < 360; degree++) {
                        double radians = Math.toRadians(degree);
                        double x = Math.cos(radians);
                        double z = Math.sin(radians);
                        loc.add(x, 0, z);
                        loc.getWorld().playEffect(loc, Effect.SMOKE, degree);
                        loc.subtract(x, 0, z);
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, (float) 0.4);
                    p.openInventory(create());
                }
            } else {
                //TODO Add perm message
            }
        }
    }

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent ev) {
        Player p = (Player) ev.getWhoClicked();
        ItemStack clickedItem = ev.getCurrentItem();
        if(clickedItem == null) {
            return;
        }

        if(Main.plugin.getSettings().isBagOfDrugs_CanMove()) {
            if(!clickedItem.hasItemMeta())
                return;
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

        String itemname = clickedItem.getItemMeta().getDisplayName();
        String[] pagenumber = itemname.split(" ");

        if(ev.getCurrentItem().getType().equals(Material.ARROW) && itemname.startsWith("§6Page")) {
            int page = Integer.parseInt(pagenumber[1]);
            if(page == 1) {
                p.openInventory(this.create());
            } else {
                p.openInventory(this.openPage(page));
            }
            return;
        }
        if(plugin.getDrugManager().isDrugItem(clickedItem)) {
            Drug d = plugin.getDrugManager().matchDrug(clickedItem);
            if(ev.getClick() == ClickType.LEFT) {
                ItemStack drug = d.getItem();
                drug.setAmount(1);
                p.getInventory().addItem(drug);
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, (float) 1.0, (float) 1.0);
                p.sendMessage(
                        plugin.getMessages().getPrefix() + ChatColor.translateAlternateColorCodes('&', "You've been given " + d.getDisplayName()));

            } else if(ev.getClick() == ClickType.SHIFT_LEFT) {
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, (float) 1.0, (float) 1.0);
                ItemStack d64 = d.getItem();
                d64.setAmount(64);
                p.getInventory().addItem(d64);
            } else if(ev.getClick() == ClickType.RIGHT) {
                d.influencePlayer(p);
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, (float) 1.0, (float) 1.0);
                p.closeInventory();
            } else if(ev.getClick() == ClickType.SHIFT_RIGHT) {
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, (float) 1.0, (float) 1.0);
                p.closeInventory();
                new SDRecipeInventory(d).createSDRecipeInventory(p);
            }
        }
    }

    public Inventory create() {
        ArrayList<Drug> drugs = new ArrayList<>(Main.plugin.getDrugManager().getItems().values());
        int amountofdrugs = drugs.size();

        ArrayList<ItemStack> stack = new ArrayList<>();
        amountofdrugs = Math.min(amountofdrugs, maxdrugs);

        for(int i = 0; i < amountofdrugs; i++) {
            stack.add(drugs.get(i).getItem());
        }
        while(stack.size() % 9 != 0) {
            stack.add(new ItemStack(Material.AIR));
        }

        for(int i = 0; i < 8; i++) {
            stack.add(GreyGlassPane());
        }
        stack.add(ForwardButton(drugs.size() > maxdrugs, 2));

        Inventory inv = Bukkit.createInventory(null, stack.size(), invName);

        inv.setContents(stack.toArray(new ItemStack[0]));
        return inv;
    }

    public Inventory openPage(int page) {
        ArrayList<Drug> drugs = new ArrayList<>(Main.plugin.getDrugManager().getItems().values());
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

    @EventHandler
    public void onDragEvent(InventoryDragEvent ev) {
        if(ev.getView().getTitle().equals(invName)) {
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent ev) {
        if(s.isBagOfDrugs_CanDrop()) {
            if(ev.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(invName)) {
                ev.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void BagSpawn(ItemSpawnEvent ev) {
        Item drop = ev.getEntity();
        ItemStack item = drop.getItemStack();
        if(item.hasItemMeta()) {
            if(item.getItemMeta().getDisplayName().equals(bagName)) {
                drop.setCustomName(bagName);
                drop.setCustomNameVisible(true);
            }
        }
    }

    @EventHandler
    public void DisableBeaconDup(CraftItemEvent e) {
        Player p = (Player) e.getView().getPlayer();
        CraftingInventory inv = e.getInventory();
        ItemStack[] mat = inv.getMatrix();

        if(inv.getResult().getType() == Material.BEACON) {
            if(mat[4].getItemMeta().getDisplayName().contentEquals(bagName)) {
                p.sendMessage(
                        plugin.getMessages().getPrefix() + "§c§oCan " + "not use " + bagName + " §c§oto craft a " + "§b" + inv.getResult().getType());
                e.setCancelled(true);
            }
        }
    }

    public String getBagName() {
        return bagName;
    }

    private ItemStack BagOfDrugsStack() {
        ItemStack stack = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(this.bagName);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "---------------------");
        lore.add(ChatColor.RED + "A Bag Full Of Drugs :)");
        lore.add("Enjoy.");
        lore.add(ChatColor.ITALIC + "Simple-Drugs®");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.BINDING_CURSE, 100, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        stack.setItemMeta(meta);
        return stack;
    }

    public ItemStack getBagOfDrugs() {
        return bag;
    }
}
