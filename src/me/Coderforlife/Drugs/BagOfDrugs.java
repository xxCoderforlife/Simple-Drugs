package me.Coderforlife.Drugs;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
	private Main plugin;
	Drugs drugs = new Drugs();
	Weed weed = new Weed();
	Coke coke = new Coke();
	Ciggy ciggy = new Ciggy();
	Heroin heroin = new Heroin();
	Percocet percocet = new Percocet();
	Acid acid = new Acid();
	Molly molly = new Molly();

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
						Inventory gui = Bukkit.createInventory(p, 18, bagName);
						ItemStack[] menu_items = { drugs.WeedStack, drugs.Acid, drugs.Ciggy, drugs.Coke, drugs.Heroin,
								drugs.Molly, drugs.Percocet, drugs.Shrooms, drugs.Alcohol, drugs.DMT, drugs.Flakka
								,drugs.Ketamine, drugs.Meth, drugs.PCP, drugs.Salvia};
						gui.setContents(menu_items);
						p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_OPEN, 8, 12);
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
			if (plugin.drugsConfig.getBoolean("Core.Drugs.BagOfDrugs.CanMove") == false) {
				if (clickedItem.getItemMeta().getDisplayName().equals(bagName)) {
					ev.setCancelled(true);
					p.getItemOnCursor();
					p.setItemOnCursor(null);
				}
			}
			if (ev.getView().getTitle().equals(bagName)) {
				ev.setCancelled(true);
				if (clickedItem.getItemMeta().getDisplayName().equals(weed.WeedName)) {
					p.getInventory().addItem(drugs.WeedStack);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + weed.WeedName);
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));
				}

				if (clickedItem.getItemMeta().getDisplayName().equals(coke.CokeName)) {
					p.getInventory().addItem(drugs.Coke);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + coke.CokeName);
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(molly.MollyName)) {
					p.getInventory().addItem(drugs.Molly);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + molly.MollyName);
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Heroin.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Heroin);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drugs.Heroin.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(percocet.PercocetName)) {
					p.getInventory().addItem(drugs.Percocet);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + percocet.PercocetName);
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(ciggy.CiggyName)) {
					p.getInventory().addItem(drugs.Ciggy);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + ciggy.CiggyName);
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}

				if (clickedItem.getItemMeta().getDisplayName().equals(acid.AcidName)) {
					p.getInventory().addItem(drugs.Acid);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + acid.AcidName);
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Shrooms.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Shrooms);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drugs.Shrooms.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Alcohol.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Alcohol);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drugs.Alcohol.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.DMT.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.DMT);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drugs.DMT.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Flakka.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Flakka);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drugs.Flakka.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Meth.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Meth);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drugs.Meth.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.PCP.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.PCP);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drugs.PCP.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Salvia.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Salvia);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drugs.Salvia.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
					p.closeInventory();
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(sober));

				}
				if (clickedItem.getItemMeta().getDisplayName().equals(drugs.Ketamine.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(drugs.Ketamine);
					p.sendMessage(Main.prefix + ChatColor.GRAY + "You've been given " + drugs.Ketamine.getItemMeta().getDisplayName());
					p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 10, 32);
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
		if (ev.getView().getTitle().equals(bagName)) {
			ev.setCancelled(true);
		}
	}

	@EventHandler
	public void onDropItem(PlayerDropItemEvent ev) {
		if (plugin.drugsConfig.getBoolean("Core.Drugs.BagOfDrugs.CanDrop") == false) {
			if (ev.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(bagName)) {
				ev.setCancelled(true);
			}
		}
	}

}
