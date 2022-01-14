package me.Coderforlife.Drugs.Settings;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUI {

    public static Inventory create() {
        Inventory inventory = Bukkit.createInventory(null, (9 * 5), "§6§lDrugs Settings");

        ArrayList<ItemStack> items = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            items.add(stack(Material.BLACK_STAINED_GLASS_PANE, false, " ", Arrays.asList(" ")));
        }

        items.add(stack(Material.PAPER, Settings.CheckForUpdate(), "§6§lCheck for Updates" + enabledordisabled(Settings.CheckForUpdate()), Arrays.asList("§7Making sure the plugin is up to date", " ", clickto(Settings.CheckForUpdate()))));

        items.add(stack(Material.PAPER, Settings.UpdateMessage(), "§6§lUpdate Message" + enabledordisabled(Settings.UpdateMessage()), Arrays.asList("§7Whether to send a Message to Players", "§7with the Permission 'drugs.updater'", "§7once a new update is available", " ", clickto(Settings.UpdateMessage()))));

        items.add(stack(Material.PAPER, Settings.JoinMessage(), "§6§lJoin Message" + enabledordisabled(Settings.JoinMessage()), Arrays.asList("§7Sends the player a plugin message on Join", " ", clickto(Settings.JoinMessage()))));

        for(int i = 0; i < 6; i++) {
            items.add(new ItemStack(Material.AIR));
        }

        for(int i = 0; i < 9; i++) {
            items.add(stack(Material.BLACK_STAINED_GLASS_PANE, false, "§7⇧ General Settings", Arrays.asList("§7⇩ Bag Settings")));
        }

        items.add(stack(Material.CHEST, Settings.BagOfDrugs_CanMove(), "§6§lBag Movable" + enabledordisabled(Settings.BagOfDrugs_CanMove()), Arrays.asList("§7If the player can move the bag in their Inventory", " ", clickto(Settings.BagOfDrugs_CanMove()))));

        items.add(stack(Material.CHEST, Settings.BagOfDrugs_CanDrop(), "§6§lBag Dropable" + enabledordisabled(Settings.BagOfDrugs_CanDrop()), Arrays.asList("§7If the player can drop the bag in their Inventory", " ", clickto(Settings.BagOfDrugs_CanDrop()))));

        items.add(stack(Material.CHEST, Settings.BagOfDrugs_GiveOnJoin(), "§6§lGive Bag on Join" + enabledordisabled(Settings.BagOfDrugs_GiveOnJoin()), Arrays.asList("§7Is the bag given on player join", " ", clickto(Settings.BagOfDrugs_GiveOnJoin()))));

        items.add(stack(Material.CHEST, Settings.BagOfDrugs_DropOnDeath(), "§6§lBag Dropped on Death" + enabledordisabled(Settings.BagOfDrugs_DropOnDeath()), Arrays.asList("§7If the Bag is dropped on death or not.", " ", clickto(Settings.BagOfDrugs_DropOnDeath()))));

        items.add(stack(Material.CHEST, Settings.BagofDrugs_GiveOnRespawn(), "§6§lKeep Bag on Respawn" + enabledordisabled(Settings.BagofDrugs_GiveOnRespawn()), Arrays.asList("§7If the player Keeps the Bag when they Respawn", " ", clickto(Settings.BagofDrugs_GiveOnRespawn()))));

        for(int i = 0; i < 4; i++) {
            items.add(new ItemStack(Material.AIR));
        }

        for(int i = 0; i < 9; i++) {
            items.add(stack(Material.BLACK_STAINED_GLASS_PANE, false, " ", Arrays.asList(" ")));
        }

        inventory.setContents(items.toArray(new ItemStack[0]));
        return inventory;
    }

    private static ItemStack stack(Material mat, boolean ench, String name, List<String> lore) {
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

    private static Material button(boolean i) {
        return i ? Material.RED_CONCRETE : Material.GREEN_CONCRETE;
    }

    private static String enabledordisabled(boolean i) {
        return i ? " §a(Enabled)" : " §c(Disabled)";
    }

    private static String clickto(boolean i) {
        return i ? "§cClick to Disable" : "§aClick to Enable";
    }

}
