package me.Coderforlife.Drugs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabCommands implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> tab = new ArrayList<>();
		if (args.length == 0) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				tab.remove(all.getName());
			}
			tab.add("drugs");
		} else if (args.length == 1) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				tab.remove(all.getName());
			}
			tab.add("help");
			tab.add("list");
			tab.add("reload");
			tab.add("bagofdrugs");
			tab.add("soberup");
		} else if (args.length == 2) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				tab.remove(all.getName());
			}
		}

		return tab;
	}

}
