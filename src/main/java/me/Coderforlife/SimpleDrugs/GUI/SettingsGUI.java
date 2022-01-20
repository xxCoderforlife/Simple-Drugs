package me.Coderforlife.SimpleDrugs.GUI;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsGUI {

    public Inventory create() {
        Inventory inventory = Bukkit.createInventory(null, (9 * 5), "§6§lDrugs Settings");

        ArrayList<ItemStack> items = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            items.add(blackglass());
        }

        items.add(stack(Material.PAPER, Settings.CheckForUpdate, "§6§lCheck for Updates" + enabledordisabled(Settings.CheckForUpdate), Arrays.asList("§7Making sure the plugin is up to date", " ", clickto(Settings.CheckForUpdate))));
        items.add(stack(Material.PAPER, Settings.UpdateMessage, "§6§lUpdate Message" + enabledordisabled(Settings.UpdateMessage), Arrays.asList("§7Whether to send a Message to Players", "§7with the Permission 'drugs.updater'", "§7once a new update is available", " ", clickto(Settings.UpdateMessage))));
        items.add(stack(Material.PAPER, Settings.JoinMessage, "§6§lJoin Message" + enabledordisabled(Settings.JoinMessage), Arrays.asList("§7Sends the player a plugin message on Join", " ", clickto(Settings.JoinMessage))));

        for(int i = 0; i < 6; i++) {
            items.add(new ItemStack(Material.AIR));
        }

        for(int i = 0; i < 9; i++) {
            items.add(stack(Material.BLACK_STAINED_GLASS_PANE, false, "§7⇧ General Settings", Arrays.asList("§7⇩ Bag Settings")));
        }

        items.add(stack(Material.BOOK, Settings.BagOfDrugs_CanMove, "§6§lBag Movable" + enabledordisabled(Settings.BagOfDrugs_CanMove), Arrays.asList("§7If the player can move the bag in their Inventory", " ", clickto(Settings.BagOfDrugs_CanMove))));

        items.add(stack(Material.BOOK, Settings.BagOfDrugs_CanDrop, "§6§lBag Droppable" + enabledordisabled(Settings.BagOfDrugs_CanDrop), Arrays.asList("§7If the player can drop the bag in their Inventory", " ", clickto(Settings.BagOfDrugs_CanDrop))));

        items.add(stack(Material.BOOK, Settings.BagOfDrugs_GiveOnJoin, "§6§lGive Bag on Join" + enabledordisabled(Settings.BagOfDrugs_GiveOnJoin), Arrays.asList("§7Is the bag given on player join", " ", clickto(Settings.BagOfDrugs_GiveOnJoin))));

        items.add(stack(Material.BOOK, Settings.BagOfDrugs_DropOnDeath, "§6§lBag Dropped on Death" + enabledordisabled(Settings.BagOfDrugs_DropOnDeath), Arrays.asList("§7If the Bag is dropped on death or not.", " ", clickto(Settings.BagOfDrugs_DropOnDeath))));

        items.add(stack(Material.BOOK, Settings.BagOfDrugs_GiveOnRespawn, "§6§lKeep Bag on Respawn" + enabledordisabled(Settings.BagOfDrugs_GiveOnRespawn), Arrays.asList("§7If the player Keeps the Bag when they Respawn", " ", clickto(Settings.BagOfDrugs_GiveOnRespawn))));

        for(int i = 0; i < 4; i++) {
            items.add(new ItemStack(Material.AIR));
        }

        for(int i = 0; i < 9; i++) {
            items.add(blackglass());
        }

        inventory.setContents(items.toArray(new ItemStack[0]));
        return inventory;
    }

    public void handleClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        ItemStack stack = event.getCurrentItem();

        if(stack == null || stack.getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
            event.setCancelled(true);
            return;
        }

        String[] name = stack.getItemMeta().getDisplayName().split(" ");
        String settingsname = String.join(" ", Arrays.copyOfRange(name, 0, name.length - 1));
        boolean isEnabled = name[name.length - 1].equalsIgnoreCase("§a(Enabled)");

        Settings s = new Settings();

        switch(settingsname) {
            case "§6§lCheck for Updates" -> {
                s.CheckForUpdate(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aChecking for Updates.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Checking for Updates.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            }
            case "§6§lUpdate Message" -> {
                s.UpdateMessage(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §asending Update Message");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled sending Update Message.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            }
            case "§6§lJoin Message" -> {
                s.JoinMessage(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §asending Join Message");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled sending Join Message.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            }
            case "§6§lBag Movable" -> {
                s.BagOfDrugs_CanMove(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aMovable Bag of Drugs.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Movable Bag of Drugs.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            }
            case "§6§lBag Droppable" -> {
                s.BagOfDrugs_CanDrop(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aDroppable Bag of Drugs.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Droppable Bag of Drugs.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            }
            case "§6§lGive Bag on Join" -> {
                s.BagOfDrugs_GiveOnJoin(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §agiving Bag of Drugs on Join.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled giving Bag of Drugs on Join.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            }
            case "§6§lBag Dropped on Death" -> {
                s.BagOfDrugs_DropOnDeath(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aBag of Drugs Dropped on Death.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Bag of Drugs Dropped on Death.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            }
            case "§6§lKeep Bag on Respawn" -> {
                s.BagOfDrugs_GiveOnRespawn(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §agiving Bag of Drugs on Respawn.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled giving Bag of Drugs on Respawn.");
                }
                p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            }
            default -> {
            }
        }
        event.setCancelled(true);
        p.openInventory(new SettingsGUI().create());
    }


    private ItemStack stack(Material mat, boolean ench, String name, List<String> lore) {
        ItemStack stack = new ItemStack(mat);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        if(ench)
            stack.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
        return stack;
    }

    private String enabledordisabled(boolean i) {
        return i ? " §a(Enabled)" : " §c(Disabled)";
    }

    private String clickto(boolean i) {
        return i ? "§cClick to Disable" : "§aClick to Enable";
    }

    private ItemStack blackglass() {
        ItemStack stack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(" ");
        stack.setItemMeta(meta);
        return stack;
    }

}
