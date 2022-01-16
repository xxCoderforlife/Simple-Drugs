package me.Coderforlife.SimpleDrugs.Settings;

import me.Coderforlife.SimpleDrugs.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SettingsClickEvent implements Listener {

    private Main plugin;

    public Main getPlugin() {
        return this.plugin;
    }

    public SettingsClickEvent(Main plugin) {
        this.setPlugin(plugin);
    }

    public void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event) {
        Settings s = new Settings();
        Player p = (Player) event.getWhoClicked();
        ItemStack stack = event.getCurrentItem();

        if(!event.getView().getTitle().equalsIgnoreCase("§6§lDrugs Settings")) {
            return;
        }
        if(stack == null || stack.getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
            event.setCancelled(true);
            return;
        }

        String[] name = stack.getItemMeta().getDisplayName().split(" ");
        String settingsname = String.join(" ", Arrays.copyOfRange(name, 0, name.length - 1));
        boolean isEnabled = name[name.length - 1].equalsIgnoreCase("§a(Enabled)");

        switch(settingsname) {
            case "§6§lCheck for Updates":
                s.setCheckForUpdate(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aChecking for Updates.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Checking for Updates.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
                break;
            case "§6§lUpdate Message":
                s.setUpdateMessage(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §asending Update Message");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled sending Update Message.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
                break;
            case "§6§lJoin Message":
                s.setJoinMessage(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §asending Join Message");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled sending Join Message.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
                break;
            case "§6§lBag Movable":
                s.setBagOfDrugs_CanMove(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aMovable Bag of Drugs.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Movable Bag of Drugs.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
                break;
            case "§6§lBag Droppable":
                s.setBagOfDrugs_CanDrop(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aDroppable Bag of Drugs.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Droppable Bag of Drugs.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
                break;
            case "§6§lGive Bag on Join":
                s.setBagOfDrugs_GiveOnJoin(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §agiving Bag of Drugs on Join.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled giving Bag of Drugs on Join.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
                break;
            case "§6§lBag Dropped on Death":
                s.setBagOfDrugs_DropOnDeath(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aBag of Drugs Dropped on Death.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Bag of Drugs Dropped on Death.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
                break;
            case "§6§lKeep Bag on Respawn":
                s.setBagofDrugs_GiveOnRespawn(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §agiving Bag of Drugs on Respawn.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled giving Bag of Drugs on Respawn.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
                break;
            default:
                break;
        }

        event.setCancelled(true);
        GUI g = new GUI();
        p.openInventory(g.create());
    }

}
