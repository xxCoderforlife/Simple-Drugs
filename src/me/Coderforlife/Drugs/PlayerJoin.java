package me.Coderforlife.Drugs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class PlayerJoin implements Listener {

	private Main plugin;

	public ItemStack bag = (HELLO(new ItemStack(Material.NETHER_STAR, (byte) 1)));

	private ItemStack HELLO(ItemStack is) {
		List<String> name = new ArrayList<>();
		name.add(ChatColor.DARK_GRAY + "---------------------");
		name.add(ChatColor.RED + "A Bag Full Of Drugs :)");
		name.add("Enjoy.");
		name.add(ChatColor.ITALIC + "Simple-Drugs®");
		ItemMeta im = is.getItemMeta();
		im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES });
		im.setDisplayName(BagOfDrugs.bagName);
		im.setLore(name);
		is.setItemMeta(im);
		return is;
	}

	public PlayerJoin(Main plugin) {
		this.setPlugin(plugin);
	}

	public PlayerJoin() {
		return;
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		Player p = ev.getPlayer();
		if (plugin.drugsConfig.getBoolean("Drugs.JoinMessage")) {
			p.sendMessage(Main.prefix
					+ ChatColor.translateAlternateColorCodes('&', "&f&lServer is running &5&l&oSIMPLE DRUGS"));
		}
		if (plugin.drugsConfig.getBoolean(Main.bagofdrugs + ".GiveOnJoin")) {
			if (!p.getInventory().contains(bag)) {
				p.getInventory().addItem(bag);

			}
		} else {
			return;
		}

	}
}
