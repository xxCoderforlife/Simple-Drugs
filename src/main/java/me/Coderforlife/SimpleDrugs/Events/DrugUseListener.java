package me.Coderforlife.SimpleDrugs.Events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.Coderforlife.SimpleDrugs.Main;
import me.Coderforlife.SimpleDrugs.Druging.Drug;


public class DrugUseListener implements Listener {

    private Main plugin = Main.plugin;
    private HashMap<UUID, Long> cooldownMap = new HashMap<>();

    @EventHandler
    public void RightClickEvent(PlayerInteractEvent ev) {
        Player p = ev.getPlayer();
        Action pa = ev.getAction();

        if(!pa.equals(Action.RIGHT_CLICK_AIR) && !pa.equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        Drug drug = Main.plugin.getDrugManager().matchDrug(itemInHand);
        if(drug == null)
            return;

        ev.setCancelled(true);

        if(ev.getHand().equals(EquipmentSlot.OFF_HAND))
            return;

        if(!p.hasPermission(drug.getPermission())) {
            p.sendMessage(plugin.getMessages().getPrefix() + "§4You can't use " + drug.getName());
            return;
        }

        if(cooldownMap.getOrDefault(p.getUniqueId(), System.currentTimeMillis()) > System.currentTimeMillis()) {
            p.sendMessage(plugin.getMessages().getPrefix() + "§4You can't use " + drug.getName() + " for another §c" + (cooldownMap.get(p.getUniqueId()) - System.currentTimeMillis()) / 1000 + "§4 seconds");
            return;
        }

        drug.influencePlayer(p);
        p.playSound(p.getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 10, 29);
        itemInHand.setAmount(itemInHand.getAmount() - 1);
        cooldownMap.put(p.getUniqueId(), System.currentTimeMillis() + plugin.getSettings().getCooldown() * 1000);
    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent ev) {
        Player p = ev.getPlayer();

        ItemStack stack = p.getInventory().getItemInMainHand() == null ? p.getInventory().getItemInOffHand() : p.getInventory().getItemInMainHand();
        boolean isDrug = plugin.getDrugManager().matchDrug(stack) != null;
        if(isDrug) {
            ev.setCancelled(true);
        }
    }

    @EventHandler
    public void HitPlayerWithDrug(EntityDamageByEntityEvent ev){
        if(!(ev.getDamager() instanceof Player) || !(ev.getEntity() instanceof Player)){
            return;
        }
        Player hitter = (Player) ev.getDamager();
        Player victim = (Player) ev.getEntity();
        ItemStack hand = hitter.getInventory().getItemInMainHand();
        if(plugin.getDrugManager().isDrugItem(hand)){
            Drug d = plugin.getDrugManager().matchDrug(hand);
            hand.setAmount(0);
            d.influencePlayer(victim);
        }
    }
}
