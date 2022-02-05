package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

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

public class CreateNewDrug implements Listener{

    private Inventory inv;
    
    private final String invName = ChatColor.translateAlternateColorCodes('&', "&4&lCreate New Drug");

    public Inventory drugCreator(){
        inv = Bukkit.createInventory(null, 18,invName);
        inv.setItem(0, nameStack());
        inv.setItem(1, setEffects());
        inv.setItem(2, setSeedItem());
        inv.setItem(3, setDrugItem());
        inv.setItem(4, setHasSeed());
        inv.setItem(5, setPermission());
        inv.setItem(6, setAddictionLevel());
        inv.setItem(7, setCrafting());
        return inv;
    }

    @EventHandler
    public void PlayerClick(InventoryClickEvent ev){

        if(ev.getCurrentItem() == null){
            return;
        }
        if(!ev.getView().getTitle().equals(invName)){
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

        }else if(s.equals(setHasSeed())){

        }else if(s.equals(setPermission())){

        }else if(s.equals(setAddictionLevel())){

        }else if(s.equals(setCrafting())){
            
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

    private ItemStack setAddictionLevel(){
        ItemStack s = new ItemStack(Material.WITHER_SKELETON_SKULL);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&oSet Addiction Level"));
        s.setItemMeta(im);
        return s;
    }

    private ItemStack setPermission(){
        ItemStack s = new ItemStack(Material.PAPER);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&oSet Drug Permission"));
        s.setItemMeta(im);
        return s;
    }

    private ItemStack setHasSeed(){
        ItemStack s = new ItemStack(Material.GREEN_SHULKER_BOX);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&oSet If The Drug Has A Seed"));
        s.setItemMeta(im);
        return s;
    }

    private ItemStack setCrafting(){
        ItemStack s = new ItemStack(Material.GREEN_SHULKER_BOX);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&oSet If The Drug Has A Seed"));
        s.setItemMeta(im);
        return s;
    }
}
