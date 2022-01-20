package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Druging.BagOfDrugs;
import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerJoin implements Listener {

    public ItemStack bag = HELLO();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();
        if(Settings.BagOfDrugs_GiveOnJoin) {
            if(!p.getInventory().contains(bag)) {
                p.getInventory().addItem(bag);
            }
        }
        if(Settings.JoinMessage) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    p.sendMessage(Main.prefix + "§f§lServer is running §5§l§oSIMPLE DRUGS");
                }
            }, 40L);
        }
    }

    private ItemStack HELLO() {
        ItemStack stack = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(BagOfDrugs.bagName);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "---------------------");
        lore.add(ChatColor.RED + "A Bag Full Of Drugs :)");
        lore.add("Enjoy.");
        lore.add(ChatColor.ITALIC + "Simple-Drugs®");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.BINDING_CURSE, 7766, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        stack.setItemMeta(meta);
        return stack;
    }
}
