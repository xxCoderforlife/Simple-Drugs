package me.Coderforlife.SimpleDrugs.GUI.Framework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import me.Coderforlife.SimpleDrugs.Main;

public class InventoryUI implements Listener {
	
		private String name;
	
		private Set<UUID> players = new HashSet<UUID>();
	
		private final Map<Integer, InventoryButton> buttons = new HashMap<Integer, InventoryButton>();
	
		private Set<Integer> updatedSlots = new HashSet<Integer>();
	
		private Inventory inv;
	
		private boolean registered = false;
		
		private boolean shouldRemove = true;
	
		/**
		 * Creates an InventoryUI of the specified InventoryType and Title
		 * 
		 * @param type  InventoryType to be displayed
		 * @param title Title of the inventory
		 */
		public InventoryUI(InventoryType type, String title) {
			inv = Bukkit.createInventory(null, type, title);
			this.name = title;
		}
	
		/**
		 * Creates an InventoryUI of the specified size and title (Chest Inventory)
		 * 
		 * @param size  Size (% 9)
		 * @param title Title of the inventory
		 */
		public InventoryUI(int size, String title) {
			inv = Bukkit.createInventory(null, size, title);
			this.name = title;
		}
	
		/**
		 * Opens the inventory for the following Player
		 * 
		 * @param p Player to open
		 */
		public void open(Player p) {
			if (players.contains(p.getUniqueId()))
				return;
			players.add(p.getUniqueId());
			if (players.size() == 1 && !registered)
				Main.plugin.getServer().getPluginManager().registerEvents(this, Main.plugin);
			registered = true;
			p.openInventory(inv);
		}
	
		/**
		 * Opens the inventory for all of the given players
		 * 
		 * @param pls Players to open
		 */
		public void open(Iterable<Player> pls) {
			for (Player p : pls)
				open(p);
		}
		
		public void setShouldRemove(boolean b) {
			shouldRemove = b;
		}
	
		/**
		 * Close the inventory for all of of the given players
		 * 
		 * @param pls Players to close
		 */
		public void close(Iterable<Player> pls) {
			for (Player p : pls)
				close(p);
		}
	
		/**
		 * Properly closes Inventory for the given player and calls the onClose method.
		 * But can be skipped by just closing the players inventory
		 * 
		 * @param p Player to close
		 */
		public void close(Player p) {
			if (!players.contains(p.getUniqueId()))
				return;
			players.remove(p.getUniqueId());
			if (players.size() == 0 && this.registered) {
				HandlerList.unregisterAll(this);
				registered = false;
			}
			p.closeInventory();
			onClose(p);
		}
	
		/**
		 * Add an InventoryButton at the next available open slot if there are any slots
		 * open
		 * 
		 * @param but InventoryButton to add
		 */
		public void addButton(InventoryButton but) {
			Integer nextOpenSlot = getNextOpenSlot();
			if (nextOpenSlot == null)
				throw new IllegalStateException("Unable to place the button! No room!");
			addButton(but, nextOpenSlot);
		}
	
		/**
		 * Add an InventoryButton at the current slot
		 * 
		 * @param but  InventoryButton to add
		 * @param slot Index to put InventoryButton
		 */
		public void addButton(InventoryButton but, int slot) {
			buttons.put(slot, but);
		}
	
		/**
		 * Removes the suppled InventoryButton from the InventoryUI
		 * 
		 * @param but InventoryButton to remove
		 */
		public void removeButton(InventoryButton but) {
			clearSlot(getSlotFor(but));
		}
	
		/**
		 * Removes the InventoryButton at the given slot
		 * 
		 * @param slot Index of slot to remove
		 */
		public void clearSlot(int slot) {
			buttons.remove(slot);
			markForUpdate(slot);
		}
	
		/**
		 * Will move the button and mark it for update
		 * 
		 * @param but  InventoryButton to move
		 * @param slot New index to set InventoryButton
		 */
		public void moveButton(InventoryButton but, int slot) {
			removeButton(but);
			addButton(but, slot);
		}
	
		/**
		 * Makes the slot of the InventoryButton for update
		 * 
		 * @param button InventoryButton to get Index of
		 */
		public void markForUpdate(InventoryButton button) {
			markForUpdate(getSlotFor(button));
		}
	
		/**
		 * Used for when calling updating to make sure that editing of button maps does
		 * not take place when iterating through it
		 * 
		 * @param slot Index to mark for update
		 */
		public void markForUpdate(int slot) {
			updatedSlots.add(slot);
		}
	
		/**
		 * Get the index of the supplied InventoryButton
		 * 
		 * @param but InventoryButton to search for
		 * @return Index of button
		 */
		public Integer getSlotFor(InventoryButton but) {
			for (Entry<Integer, InventoryButton> buttonEnt : buttons.entrySet()) {
				if (((InventoryButton) buttonEnt.getValue()).equals(but))
					return buttonEnt.getKey();
			}
			return Integer.valueOf(-1);
		}
	
		/**
		 * Abstract class that can be used to perform an action when the slot is closed
		 * 
		 * @param p Player that will handle the closing
		 */
		public void onClose(Player p) {
			
		}
	
		/**
		 * Checks of the following slot already has an item in it
		 * 
		 * @param slot Index of slot
		 * @return If slot has an item
		 */
		public boolean isFilled(int slot) {
			return buttons.containsKey(slot);
		}
		
		public Inventory getInventory() {
			return inv;
		}
	
		/**
		 * Will update and set items from the buttons mappings
		 */
		public void updateInventory() {
			for (int x = 0; x < inv.getSize(); x++) {
				InventoryButton but = buttons.get(Integer.valueOf(x));
				if (shouldRemove && but == null && inv.getItem(x) != null)
					inv.setItem(x, null);
				else if ((inv.getItem(x) == null && but != null) || updatedSlots.contains(Integer.valueOf(x))) {
					assert but != null;
					inv.setItem(x, but.getItem());
				}
			}
			for (UUID u : players) {
				Player p = Bukkit.getPlayer(u);
				p.updateInventory();
			}
			updatedSlots = new HashSet<Integer>();
		}
	
		/**
		 * Will return the value of the next slot that can place an item
		 * 
		 * @return Integer of next open slot
		 */
		public Integer getNextOpenSlot() {
			Integer nextSlot = Integer.valueOf(0);
			for (Integer ints : buttons.keySet()) {
				if (ints.equals(nextSlot))
					nextSlot = Integer.valueOf(ints.intValue() + 1);
			}
			return (nextSlot.intValue() >= inv.getSize()) ? null : nextSlot;
		}
	
		// Everything below here handles what happens inside of inventory in terms of
		// events
	
		@EventHandler(priority = EventPriority.HIGH)
		public final void onPlayerLeave(PlayerQuitEvent e) {
			Player p = e.getPlayer();
			if (players.contains(p.getUniqueId()))
				players.remove(p.getUniqueId());
		}
	
		@EventHandler
		public final void onInvClose(InventoryCloseEvent e) {
			if (!(e.getPlayer() instanceof Player))
				return;
			if (!e.getInventory().equals(inv))
				return;
			Player p = (Player) e.getPlayer();
			players.remove(p.getUniqueId());
			onClose(p);
		}
	
		@EventHandler(priority = EventPriority.HIGHEST)
		public final void onInventoryClick(InventoryClickEvent e) {
			if (e.getClickedInventory() == null)
				return;
			if ((e.getClickedInventory().getHolder() instanceof Player)) {
				e.setCancelled(false);
			} else {
				if (!(e.getWhoClicked() instanceof Player))
					return;
				InventoryView view = e.getView();
				if (!view.getTitle().equals(name))
					return;
				if (!players.contains(e.getWhoClicked().getUniqueId()))
					return;
				Player p = (Player) e.getWhoClicked();
				InventoryButton but = buttons.get(Integer.valueOf(e.getSlot()));
				if (but == null)
					return;
				try {
					but.onPlayerClick(p, ClickAction.from(e.getClick()));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				e.setCancelled(true);
			}
		}
	
		@EventHandler(priority = EventPriority.HIGHEST)
		public void onPlace(InventoryMoveItemEvent e) {
			if (e.getDestination().equals(inv))
				e.setCancelled(true);
		}
	
		public final void onPlayerInventoryMove(InventoryMoveItemEvent e) {
			if (!(e.getDestination().getHolder() instanceof InventoryUI))
				return;
			e.setCancelled(true);
		}
}

