package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

/**
 * A listener to handle clicking on any registered drug.
 */
public class DrugUseListener implements Listener {

    private final Main plugin;

    public DrugUseListener(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Tries to match a right clicked item with a drug item. Influences the player
     * with the drug if the item was one.
     */
    @EventHandler
    public void RightClickEvent(PlayerInteractEvent ev) {
        Player p = ev.getPlayer();
        Action pa = ev.getAction();

        if(!pa.equals(Action.RIGHT_CLICK_AIR) && !pa.equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        Drug drug = Drug.matchDrug(itemInHand);
        if(null == drug)
            return;

        if(ev.getHand().equals(EquipmentSlot.OFF_HAND)) {
            return;
        }

        if(!p.hasPermission(drug.getPermission())) {
            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "You can't use " + drug.getName());
            return;
        }

        try {
            drug.influencePlayer(p);
        } catch(Exception e1) {
            p.sendMessage(Main.prefix + ChatColor.DARK_RED + "Error in the Console");
            Bukkit.getLogger().severe(Main.prefix + ChatColor.GREEN + "Send this Error to xxCoderforlife on https://Spigotmc.org");
            e1.printStackTrace();
        }

        p.playSound(p.getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 10, 29);
        itemInHand.setAmount(itemInHand.getAmount() - 1);
        ev.setCancelled(true);
    }
}
