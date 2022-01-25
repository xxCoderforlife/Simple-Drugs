package me.Coderforlife.SimpleDrugs.Events;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Settings;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;


public class DrugUseListener implements Listener {

    private static HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void RightClickEvent(PlayerInteractEvent ev) {
        Player p = ev.getPlayer();
        Action pa = ev.getAction();

        if(!pa.equals(Action.RIGHT_CLICK_AIR) && !pa.equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        Drug drug = new Drug().matchDrug(itemInHand);
        if(drug == null)
            return;

        ev.setCancelled(true);

        if(ev.getHand().equals(EquipmentSlot.OFF_HAND))
            return;

        if(!p.hasPermission(drug.getPermission())) {
            p.sendMessage(Main.prefix + "§4You can't use " + drug.getName());
            return;
        }

        if(cooldown.getOrDefault(p.getUniqueId(), System.currentTimeMillis()) > System.currentTimeMillis()) {
            p.sendMessage(Main.prefix + "§4You can't use " + drug.getName() + " for another §c" + (cooldown.get(p.getUniqueId()) - System.currentTimeMillis()) / 1000 + "§4 seconds");
            return;
        }

        drug.influencePlayer(p);
        p.playSound(p.getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 10, 29);
        itemInHand.setAmount(itemInHand.getAmount() - 1);
        cooldown.put(p.getUniqueId(), System.currentTimeMillis() + Settings.Cooldown * 1000);
    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent ev) {
        Player p = ev.getPlayer();

        ItemStack stack = p.getInventory().getItemInMainHand() == null ? p.getInventory().getItemInOffHand() : p.getInventory().getItemInMainHand();
        boolean isDrug = new Drug().matchDrug(stack) != null;
        if(isDrug) {
            ev.setCancelled(true);
        }
    }
}
