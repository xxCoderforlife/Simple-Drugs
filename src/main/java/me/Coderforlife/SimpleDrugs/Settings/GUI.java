package me.Coderforlife.SimpleDrugs.Settings;

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

    public Inventory create() {
        Settings s = new Settings();
        Inventory inventory = Bukkit.createInventory(null, (9 * 5), "§6§lDrugs Settings");

        ArrayList<ItemStack> items = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            items.add(blackglass());
        }

        items.add(stack(Material.PAPER, s.CheckForUpdate(), "§6§lCheck for Updates" + enabledordisabled(s.CheckForUpdate()), Arrays.asList("§7Making sure the plugin is up to date", " ", clickto(s.CheckForUpdate()))));

        items.add(stack(Material.PAPER, s.UpdateMessage(), "§6§lUpdate Message" + enabledordisabled(s.UpdateMessage()), Arrays.asList("§7Whether to send a Message to Players", "§7with the Permission 'drugs.updater'", "§7once a new update is available", " ", clickto(s.UpdateMessage()))));

        items.add(stack(Material.PAPER, s.JoinMessage(), "§6§lJoin Message" + enabledordisabled(s.JoinMessage()), Arrays.asList("§7Sends the player a plugin message on Join", " ", clickto(s.JoinMessage()))));

        for(int i = 0; i < 6; i++) {
            items.add(new ItemStack(Material.AIR));
        }

        for(int i = 0; i < 9; i++) {
            items.add(stack(Material.BLACK_STAINED_GLASS_PANE, false, "§7⇧ General Settings", Arrays.asList("§7⇩ Bag Settings")));
        }

        items.add(stack(Material.BOOK, s.BagOfDrugs_CanMove(), "§6§lBag Movable" + enabledordisabled(s.BagOfDrugs_CanMove()), Arrays.asList("§7If the player can move the bag in their Inventory", " ", clickto(s.BagOfDrugs_CanMove()))));

        items.add(stack(Material.BOOK, s.BagOfDrugs_CanDrop(), "§6§lBag Droppable" + enabledordisabled(s.BagOfDrugs_CanDrop()), Arrays.asList("§7If the player can drop the bag in their Inventory", " ", clickto(s.BagOfDrugs_CanDrop()))));

        items.add(stack(Material.BOOK, s.BagOfDrugs_GiveOnJoin(), "§6§lGive Bag on Join" + enabledordisabled(s.BagOfDrugs_GiveOnJoin()), Arrays.asList("§7Is the bag given on player join", " ", clickto(s.BagOfDrugs_GiveOnJoin()))));

        items.add(stack(Material.BOOK, s.BagOfDrugs_DropOnDeath(), "§6§lBag Dropped on Death" + enabledordisabled(s.BagOfDrugs_DropOnDeath()), Arrays.asList("§7If the Bag is dropped on death or not.", " ", clickto(s.BagOfDrugs_DropOnDeath()))));

        items.add(stack(Material.BOOK, s.BagofDrugs_GiveOnRespawn(), "§6§lKeep Bag on Respawn" + enabledordisabled(s.BagofDrugs_GiveOnRespawn()), Arrays.asList("§7If the player Keeps the Bag when they Respawn", " ", clickto(s.BagofDrugs_GiveOnRespawn()))));

        for(int i = 0; i < 4; i++) {
            items.add(new ItemStack(Material.AIR));
        }

        for(int i = 0; i < 9; i++) {
            items.add(blackglass());
        }

        inventory.setContents(items.toArray(new ItemStack[0]));
        return inventory;
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
