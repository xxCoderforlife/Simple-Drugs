package me.Coderforlife.SimpleDrugs.Druging;

import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;
import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class BagOfDrugs implements Listener {

    public static String bagName = "§6§lBag Of Drugs";
    public static String invName = "§6§l§oBag Of Drugs";
    private Main plugin;

    public BagOfDrugs(Main plugin) {
        this.setPlugin(plugin);
    }

    public Main getPlugin() {
        return this.plugin;
    }

    public void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    private final String sober = ChatColor.ITALIC + "Remove Drugs With" + " §c/d soberup";

    @EventHandler
    public void BagOpen(PlayerInteractEvent ev) {
        Player p = ev.getPlayer();
        Action pa = ev.getAction();

        if(pa.equals(Action.RIGHT_CLICK_AIR) || pa.equals(Action.RIGHT_CLICK_BLOCK)) {
            if(p.getInventory().getItemInMainHand().hasItemMeta()) {
                if(p.hasPermission("drugs.use.bagofdrugs")) {
                    if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(bagName)) {
                        Location loc = p.getLocation();
                        for(int degree = 0; degree < 360; degree++) {
                            double radians = Math.toRadians(degree);
                            double x = Math.cos(radians);
                            double z = Math.sin(radians);
                            loc.add(x, 0, z);
                            loc.getWorld().playEffect(loc, Effect.SMOKE, degree);
                            loc.subtract(x, 0, z);
                        }
                        p.playSound(p.getLocation(), Sound.AMBIENT_CRIMSON_FOREST_ADDITIONS, 1, (float) 0.4);
                        BagOfDrugsGUI bag = new BagOfDrugsGUI();
                        p.openInventory(bag.create());
                    }
                }

            }
        }

    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent ev) {
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

        for(Drug drug : Drug.getallDrugs()) {
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

    @EventHandler
    public void onDragEvent(InventoryDragEvent ev) {
        if(ev.getView().getTitle().equals(invName)) {
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent ev) {
        if(Settings.BagOfDrugs_CanDrop) {
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
                p.sendMessage(Main.prefix + "§c§oCan " + "not use " + bagName + " §c§oto craft a " + "§b" + inv.getResult().getType());
                e.setCancelled(true);
            }
        }
    }

}
