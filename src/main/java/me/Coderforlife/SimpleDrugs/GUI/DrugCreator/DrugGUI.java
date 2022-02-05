package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Util.Messages;
import net.md_5.bungee.api.ChatColor;

public class DrugGUI implements Listener{

    private Main plugin = Main.plugin;

    private Messages m = plugin.getMessages();

    private Inventory inv;
    
    private final String invName = ChatColor.translateAlternateColorCodes('&', "&8&lDrug Creator");

    public Inventory drugMainMenu(){
        inv = Bukkit.createInventory(null, 9,invName);
        inv.setItem(0, createDrug());
        inv.setItem(1, editDrug());
        inv.setItem(2, deleteDrug());
        inv.setItem(8, closeMainMenu());
        return inv;
    }

    @EventHandler
    public void PlayerClick(InventoryClickEvent ev){
        if(ev.getView().getTitle().equals(invName)){
            return;
        }
        if(ev.getCurrentItem() == null){
            return;
        }

        ItemStack s = ev.getCurrentItem();
        Player p = (Player) ev.getWhoClicked();
        if(s.equals(createDrug())){
            CreateNewDrug cd = new CreateNewDrug();
            p.openInventory(cd.drugCreator());
        }else if(s.equals(editDrug())){

        }else if(s.equals(deleteDrug())){

        }else if(s.equals(closeMainMenu())){
            p.closeInventory();
        }
        ev.setCancelled(true);
    }

    private ItemStack createDrug(){
        ItemStack s = new ItemStack(Material.NETHER_STAR);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&oCreate A New Drug"));
        List<String> sList = new ArrayList<>();
        sList.add(ChatColor.translateAlternateColorCodes('&', "&8&oClick me to Create A New Drug"));
        s.setItemMeta(im);
        return s;
    }

    private ItemStack editDrug(){
        ItemStack s = new ItemStack(Material.ANVIL);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&oEdit A Drug"));
        List<String> sList = new ArrayList<>();
        sList.add(ChatColor.translateAlternateColorCodes('&', "&8&oClick me to Edit a Drug"));
        s.setItemMeta(im);
        return s;
    }


    private ItemStack deleteDrug(){
        ItemStack s = new ItemStack(Material.BARRIER);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&o&lDELETE A DRUG"));
        List<String> sList = new ArrayList<>();
        sList.add(ChatColor.translateAlternateColorCodes('&', "&8&oClick me to Delete A Drug"));
        s.setItemMeta(im);
        return s;
    }

    private ItemStack closeMainMenu(){
        ItemStack s = new ItemStack(Material.BARREL);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&oClose Menu"));
        s.setItemMeta(im);
        return s;
    }
}
