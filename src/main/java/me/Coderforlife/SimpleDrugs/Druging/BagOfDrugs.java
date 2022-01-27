package me.Coderforlife.SimpleDrugs.Druging;

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
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import me.Coderforlife.SimpleDrugs.GUI.BagOfDrugsGUI;

public class BagOfDrugs implements Listener {

    private final String bagName = "§6§lBag Of Drugs";
    private final String invName = "§6§l§oBag Of Drugs";
    private Main plugin = Main.plugin;
    Settings s = plugin.getSettings();

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
                p.sendMessage(plugin.getMessages().getPrefix() + "§c§oCan " + "not use " + bagName + " §c§oto craft a " + "§b" + inv.getResult().getType());
                e.setCancelled(true);
            }
        }
    }

    public String getBagName(){
        return bagName;
    }

}
