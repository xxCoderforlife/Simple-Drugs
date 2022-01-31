package me.Coderforlife.SimpleDrugs.Druging;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import net.md_5.bungee.api.ChatColor;

public class BagOfDrugs implements Listener {

    private final String bagName = "§6§lBag Of Drugs";
    private final String invName = "§6§l§oBag Of Drugs";
    private ItemStack bagofdrugs = BagOfDrugsStack();
    private Main plugin = Main.plugin;
    Settings s = plugin.getSettings();



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
        meta.addEnchant(Enchantment.BINDING_CURSE, 7766, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        stack.setItemMeta(meta);
        return stack;
    }
    public ItemStack getBagOfDrugs(){
        return this.bagofdrugs;
    }

}
