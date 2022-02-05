package me.Coderforlife.SimpleDrugs.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class DrugCreatorGUI implements Listener{

    private Inventory inv;
    
    private final String invName = ChatColor.translateAlternateColorCodes('&', "&4&lDrug Creator");

    public Inventory drugCreator(){
        inv = Bukkit.createInventory(null, 18,invName);
        inv.setItem(0, nameStack());
        inv.setItem(1, setEffects());
        inv.setItem(2, setSeedItem());
        inv.setItem(3, setDrugItem());
        return inv;
    }

    @EventHandler
    public void PlayerClick(InventoryClickEvent ev){

        if(ev.getCurrentItem() == null){
            return;
        }

        ItemStack s = ev.getCurrentItem();
        Player p = (Player) ev.getWhoClicked();
        if(s.equals(nameStack())){
            p.sendMessage(s.getItemMeta().getDisplayName());
        }else if(s.equals(setEffects())){
            p.sendMessage(s.getItemMeta().getDisplayName());

        }else if(s.equals(setSeedItem())){
            p.sendMessage(s.getItemMeta().getDisplayName());

        }else if(s.equals(setDrugItem())){
            p.sendMessage(s.getItemMeta().getDisplayName());

        }
        ev.setCancelled(true);
    }

    private ItemStack nameStack(){
        ItemStack s = new ItemStack(Material.NAME_TAG);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&oSet Drug Name"));
        s.setItemMeta(im);
        return s;
    }

    private ItemStack setEffects(){
        ItemStack s = new ItemStack(Material.POTION);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&oSet Drug Effects"));
        s.setItemMeta(im);
        return s;
    }

    private ItemStack setSeedItem(){
        ItemStack s = new ItemStack(Material.BEETROOT_SEEDS);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&oSet Drug Seed"));
        s.setItemMeta(im);
        return s;
    }

    private ItemStack setDrugItem(){
        ItemStack s = new ItemStack(Material.REDSTONE);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&oSet Drug Item"));
        s.setItemMeta(im);
        return s;
    }
}