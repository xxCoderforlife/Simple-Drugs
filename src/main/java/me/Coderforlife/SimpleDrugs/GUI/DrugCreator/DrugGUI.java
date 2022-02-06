package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Util.Messages;
import net.md_5.bungee.api.ChatColor;

public class DrugGUI implements Listener{

    private Main plugin = Main.plugin;

    private Messages m = plugin.getMessages();

    private HashMap<Player,Inventory> pInvMap = new HashMap<>();

    private Inventory inv;
    
    private final String mmName = new String(ChatColor.translateAlternateColorCodes('&', "&8&lDrug Creator"));


    public HashMap<Player,Inventory> getPInvMap(){
        return pInvMap;
    }

    public Inventory drugMainMenu(Player p){
        inv = Bukkit.createInventory(null, 9,mmName);
        inv.setItem(0, createDrug());
        inv.setItem(1, editDrug());
        inv.setItem(2, deleteDrug());
        inv.setItem(8, closeMainMenu());
        p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1.0f, 1.0f);
        return inv;
    }


    @EventHandler
    public void onDrugGUIClose(InventoryCloseEvent ev){
        if(!(ev.getView().getTitle().equals(mmName))){
            return;
        }
    }


    @EventHandler
    public void PlayerClick(InventoryClickEvent ev){
        ItemStack s = ev.getCurrentItem();
        Player p = (Player) ev.getWhoClicked();
        if(s == null){return;}
        if(!ev.getView().getTitle().equals(mmName)){
            return;
        }

        if(s.equals(createDrug())){
            CreateNewDrug cd = new CreateNewDrug();
            p.openInventory(cd.drugCreator(p));
        }else if(s.equals(editDrug())){
            EditDrug ed = new EditDrug();
            p.openInventory(ed.editableDrugs(p));
        }else if(s.equals(deleteDrug())){
            DeleteDrug dd = new DeleteDrug();
            p.openInventory(dd.editableDrugs(p));
        }else if(s.equals(closeMainMenu())){
            p.playSound(p.getLocation(), Sound.BLOCK_BARREL_CLOSE, 1.0f, 1.0f);
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
