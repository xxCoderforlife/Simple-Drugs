package me.Coderforlife.SimpleDrugs.GUI;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsGUI {

    Settings s = Main.plugin.getSettings();

    public Inventory create() {
        Inventory inventory = Bukkit.createInventory(null, (9 * 5), "§6§lDrugs Settings");

        ArrayList<ItemStack> items = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            items.add(blackglass());
        }

        items.add(stack(Material.PAPER, Main.plugin.getSettings().CheckForUpdate, "§6§lCheck for Updates" + enabledordisabled(Main.plugin.getSettings().CheckForUpdate), Arrays.asList("§7Making sure the plugin is up to date", " ", clickto(Main.plugin.getSettings().CheckForUpdate))));
        items.add(stack(Material.PAPER, Main.plugin.getSettings().UpdateMessage, "§6§lUpdate Message" + enabledordisabled(Main.plugin.getSettings().UpdateMessage), Arrays.asList("§7Whether to send a Message to Players", "§7with the Permission 'drugs.updater'", "§7once a new update is available", " ", clickto(Main.plugin.getSettings().UpdateMessage))));
        items.add(stack(Material.PAPER, Main.plugin.getSettings().JoinMessage, "§6§lJoin Message" + enabledordisabled(Main.plugin.getSettings().JoinMessage), Arrays.asList("§7Sends the player a plugin message on Join", " ", clickto(Main.plugin.getSettings().JoinMessage))));
        items.add(stack(Material.PAPER, (Main.plugin.getSettings().Cooldown >= 1), "§6§lDrug Cooldown" + enabledordisabled(Main.plugin.getSettings().Cooldown >= 1), Arrays.asList("§7Cooldown between Drug Use", "§c➯Right Click to Decrease", "§a➯LeftClick to Increase", " ", "§7Current Cooldown: §a" + Main.plugin.getSettings().Cooldown + " Seconds")));

        for(int i = 0; i < 5; i++) {
            items.add(new ItemStack(Material.AIR));
        }

        for(int i = 0; i < 9; i++) {
            items.add(stack(Material.BLACK_STAINED_GLASS_PANE, false, "§7⇧ General Settings", List.of("§7⇩ Bag Settings")));
        }

        items.add(stack(Material.BOOK, Main.plugin.getSettings().BagOfDrugs_CanMove, "§6§lBag Movable" + enabledordisabled(Main.plugin.getSettings().BagOfDrugs_CanMove), Arrays.asList("§7If the player can move the bag in their Inventory", " ", clickto(Main.plugin.getSettings().BagOfDrugs_CanMove))));
        items.add(stack(Material.BOOK, Main.plugin.getSettings().BagOfDrugs_CanDrop, "§6§lBag Droppable" + enabledordisabled(Main.plugin.getSettings().BagOfDrugs_CanDrop), Arrays.asList("§7If the player can drop the bag in their Inventory", " ", clickto(Main.plugin.getSettings().BagOfDrugs_CanDrop))));
        items.add(stack(Material.BOOK, Main.plugin.getSettings().BagOfDrugs_GiveOnJoin, "§6§lGive Bag on Join" + enabledordisabled(Main.plugin.getSettings().BagOfDrugs_GiveOnJoin), Arrays.asList("§7Is the bag given on player join", " ", clickto(Main.plugin.getSettings().BagOfDrugs_GiveOnJoin))));
        items.add(stack(Material.BOOK, Main.plugin.getSettings().BagOfDrugs_DropOnDeath, "§6§lBag Dropped on Death" + enabledordisabled(Main.plugin.getSettings().BagOfDrugs_DropOnDeath), Arrays.asList("§7If the Bag is dropped on death or not.", " ", clickto(Main.plugin.getSettings().BagOfDrugs_DropOnDeath))));
        items.add(stack(Material.BOOK, Main.plugin.getSettings().BagOfDrugs_GiveOnRespawn, "§6§lKeep Bag on Respawn" + enabledordisabled(Main.plugin.getSettings().BagOfDrugs_GiveOnRespawn), Arrays.asList("§7If the player Keeps the Bag when they Respawn", " ", clickto(Main.plugin.getSettings().BagOfDrugs_GiveOnRespawn))));

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
        p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
        event.setCancelled(true);
        p.openInventory(this.create());
        switch(settingsname) {
            case "§6§lDrug Cooldown":
                if(event.getClick().equals(ClickType.RIGHT)) {
                    if(Main.plugin.getSettings().Cooldown < 1) {
                        p.sendMessage(Main.prefix + "§c§lDrug Cooldown is Disabled. Right Click to Increase");
                    } else {
                        s.Cooldown(Main.plugin.getSettings().Cooldown - 1);
                        p.sendMessage(Main.prefix + "§aDrug Cooldown set to §e" + Main.plugin.getSettings().Cooldown + " §aSeconds");
                    }
                } else {
                    s.Cooldown(Main.plugin.getSettings().Cooldown + 1);
                    p.sendMessage(Main.prefix + "§aDrug Cooldown set to §e" + Main.plugin.getSettings().Cooldown + " §aSeconds");
                }
                break;
            case "§6§lCheck for Updates":
                s.CheckForUpdate(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aChecking for Updates.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Checking for Updates.");
                }
                break;
            case "§6§lUpdate Message":
                s.UpdateMessage(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §asending Update Message");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled sending Update Message.");
                }
                break;
            case "§6§lJoin Message":
                s.JoinMessage(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §asending Join Message");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled sending Join Message.");
                }
                break;
            case "§6§lBag Movable":
                s.BagOfDrugs_CanMove(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aMovable Bag of Drugs.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Movable Bag of Drugs.");
                }
                break;
            case "§6§lBag Droppable":
                s.BagOfDrugs_CanDrop(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aDroppable Bag of Drugs.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Droppable Bag of Drugs.");
                }
                break;
            case "§6§lGive Bag on Join":
                s.BagOfDrugs_GiveOnJoin(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §agiving Bag of Drugs on Join.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled giving Bag of Drugs on Join.");
                }
                break;
            case "§6§lBag Dropped on Death":
                s.BagOfDrugs_DropOnDeath(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §aBag of Drugs Dropped on Death.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled Bag of Drugs Dropped on Death.");
                }
                break;
            case "§6§lKeep Bag on Respawn":
                s.BagOfDrugs_GiveOnRespawn(!isEnabled);
                if(isEnabled) {
                    p.sendMessage(Main.prefix + "§cDisabled §agiving Bag of Drugs on Respawn.");
                } else {
                    p.sendMessage(Main.prefix + "§aEnabled giving Bag of Drugs on Respawn.");
                }
                break;
            default:
                event.setCancelled(true);
                return;
        }
        p.openInventory(create());
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
