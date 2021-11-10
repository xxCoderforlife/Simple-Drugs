package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class BagOfDrugs implements Listener {

	public static String bagName = ChatColor.GOLD + "" + ChatColor.BOLD + "Bag Of Drugs";
	public static String invName = ChatColor.translateAlternateColorCodes('&', "          &6&l&oBag Of Drugs");
	private Main plugin;
	Drugs drugs = new Drugs();

	public BagOfDrugs(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	private String sober = ChatColor.ITALIC + "Remove Drugs With" + ChatColor.RED + " /d soberup";

	@EventHandler
	public void BagOpen(PlayerInteractEvent ev) {
		Player p = ev.getPlayer();
		Action pa = ev.getAction();

		if (pa.equals(Action.RIGHT_CLICK_AIR) || pa.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (p.getInventory().getItemInMainHand().hasItemMeta()) {
				if (p.hasPermission("drugs.use.bagofdrugs")) {
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(bagName)) {
						Inventory gui = Bukkit.createInventory(p, 18, invName);
						gui.setContents(drugs.getDrugItemArray());
						p.playSound(p.getLocation(), Sound.AMBIENT_NETHER_WASTES_ADDITIONS, 12, 12);
						p.openInventory(gui);

					}
				}

			}
		}

	}

	@EventHandler
	public void onClickEvent(InventoryClickEvent ev) {
		Player p = (Player) ev.getWhoClicked();
		ItemStack clickedItem = ev.getCurrentItem();
		if (clickedItem == null || clickedItem.getType().isAir())
			return;
		if (clickedItem.hasItemMeta()) {
			if (!plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.CanMove")) {
				if (clickedItem.getItemMeta().getDisplayName().equals(invName)) {
					ev.setCancelled(true);
					p.getItemOnCursor();
					p.setItemOnCursor(null);
				}
			}
			if (ev.getView().getTitle().equals(invName)) {
				ev.setCancelled(true);
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.WeedStack.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.WeedStack);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.WeedStack.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));
				}

				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Coke.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Coke);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Coke.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Molly.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Molly);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Molly.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Heroin.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Heroin);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Heroin.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Percocet.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Percocet);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Percocet.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Ciggy.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Ciggy);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Ciggy.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Acid.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Acid);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Acid.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Shrooms.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Shrooms);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Shrooms.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Alcohol.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Alcohol);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Alcohol.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.DMT.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.DMT);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.DMT.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Flakka.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Flakka);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Flakka.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Meth.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Meth);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Meth.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.PCP.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.PCP);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.PCP.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Salvia.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Salvia);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Salvia.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Ketamine.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Ketamine);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Ketamine.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Oxy.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Oxy);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Oxy.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Tussin.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Tussin);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Tussin.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Xannx.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Xannx);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given "
							+ drugs.Xannx.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURREOW, 20, 12);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

			} else {
				return;
			}
		}

	}

	@EventHandler
	public void onDragEvent(InventoryDragEvent ev) {
		if (ev.getView().getTitle().equals(invName)) {
			ev.setCancelled(true);
		}
	}

	@EventHandler
	public void onDropItem(PlayerDropItemEvent ev) {
		if (!plugin.drugsConfig.getBoolean("Drugs.BagOfDrugs.CanDrop")) {
			if (ev.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(invName)) {
				ev.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void BagSpawn(ItemSpawnEvent ev) {
		Item drop = ev.getEntity();
		ItemStack item = drop.getItemStack();
		if (item.hasItemMeta()) {
			if (item.getItemMeta().getDisplayName().equals(bagName)) {
				drop.setCustomName(bagName);
				drop.setCustomNameVisible(true);
			} else {
				return;
			}

		}
	}

}
