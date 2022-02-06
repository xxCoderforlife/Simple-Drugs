package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ConfirmGUI implements Listener {
  
    private Boolean Confirm;

    private Inventory inv;

    private final String invName = new String(ChatColor.translateAlternateColorCodes('&', "&3&o&lAPPROVAL SCREEN"));


    public ConfirmGUI(){

    }



    public Boolean isConfirmed(){
        return Confirm;
    }

    public void setConfirmed(Boolean b){
        this.Confirm = b;
    }


    @EventHandler
    public void onConfirmGUIClose(InventoryCloseEvent ev){
        if(!(ev.getView().getTitle().equals(invName))){
            return;
        }

    }


    @EventHandler
    public void onConfirmGUIOpen(InventoryOpenEvent ev){
        if(!(ev.getView().getTitle().equals(invName))){
            return;
        }

    }


    @EventHandler
    public void onPlayerClick(InventoryClickEvent ev){
        if(ev.getCurrentItem() == null){
            return;
        }

        if(!(ev.getView().getTitle().equals(invName))){
            return;
        }

        ItemStack s = ev.getCurrentItem();
        Player p = (Player) ev.getWhoClicked();
        CreateNewDrug cNewDrug = new CreateNewDrug();
        DrugGUI dGUI = new DrugGUI();
        if(s.equals(confirm())){

            if(cNewDrug.getNewDurgGUIMap().containsKey(p.getUniqueId())){
                cNewDrug.getNewDurgGUIMap().remove(p.getUniqueId());
                //TODO Save and Reload Configuration
                p.openInventory(dGUI.drugMainMenu(p));
            }
            p.closeInventory();
        }else if(s.equals(deny())){
            if(cNewDrug.getNewDurgGUIMap().containsKey(p.getUniqueId())){
                Inventory pInv = cNewDrug.getNewDurgGUIMap().get(p.getUniqueId());
                Bukkit.getConsoleSender().sendMessage(pInv.toString());
                p.openInventory(pInv);
            }
        }
        ev.setCancelled(true);

    }

    public Inventory confirmGUI(Player p){
        inv = Bukkit.createInventory(null, 9,invName);
        inv.setItem(0, confirm());
        inv.setItem(8, deny());
        p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 1.0f);
        return inv;
    }

    private ItemStack confirm(){
        ItemStack s = new ItemStack(Material.GREEN_BED);
        ItemMeta im = s.getItemMeta();

        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2&o&lCONFIRM"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&a&oConfirm what you are about to do."));
        im.setLore(lore);
        s.setItemMeta(im);
        return s;
    }

    private ItemStack deny(){
        ItemStack s = new ItemStack(Material.RED_BED);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&o&lDENY"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&c&oDeny what you are about to do."));
        im.setLore(lore);
        s.setItemMeta(im);
        return s;
    }
}
