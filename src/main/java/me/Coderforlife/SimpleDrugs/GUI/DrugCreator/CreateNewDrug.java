package me.Coderforlife.SimpleDrugs.GUI.DrugCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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

    private HashMap<UUID,Inventory> pInvMap = new HashMap<>();
    
    private final String invName = ChatColor.translateAlternateColorCodes('&', "&5&lCreate New Drug");


    /**
     * 
     * @return
     */
    public HashMap<UUID,Inventory> getNewDurgGUIMap(){
        return pInvMap;
    }


    /**
     * Main Menu Drug Creator Menu
     * 
     * @param p {@link Player}
     * @return Drug Creator Inventory
     */
    public Inventory drugCreator(Player p){
        inv = Bukkit.createInventory(null, 27,invName);
        inv.setItem(0, nameStack());
        inv.setItem(1, setEffects());
        inv.setItem(2, setSeedItem());
        inv.setItem(3, setDrugItem());
        inv.setItem(4, setHasSeed());
        inv.setItem(5, setPermission());
        inv.setItem(6, setAddictionLevel());
        inv.setItem(7, setCrafting());
        inv.setItem(18, mainMenu());
        inv.setItem(25, saveButton());
        inv.setItem(26, cancelButton());
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
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
            
        }else if(s.equals(mainMenu())){
            DrugGUI dgui = new DrugGUI();
            p.openInventory(dgui.drugMainMenu(p));
        }else if(s.equals(saveButton())){
            ConfirmGUI cGUI = new ConfirmGUI();
            pInvMap.put(p.getUniqueId(), inv);
            p.openInventory(cGUI.confirmGUI(p));

        }else if(s.equals(cancelButton())){

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
    private ItemStack mainMenu(){
        ItemStack s = new ItemStack(Material.ARROW);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&o<- Back To Main Menu"));
        List<String> sList = new ArrayList<>();
        sList.add(ChatColor.translateAlternateColorCodes('&', "&8&oTakes you back to the Drug Creator"));
        im.setLore(sList);
        s.setItemMeta(im);
        return s;
    }
    private ItemStack saveButton(){
        ItemStack s = new ItemStack(Material.GREEN_BED);
        ItemMeta im = s.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&a&oSaves the current Drug"));
        im.setLore(lore);
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2&lSAVE"));
        s.setItemMeta(im);
        return s;
    }
    private ItemStack cancelButton(){
        ItemStack s = new ItemStack(Material.RED_BED);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lCANCEL"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&c&oDiscards the current Drug"));
        im.setLore(lore);
        s.setItemMeta(im);
        return s;
    }
}
