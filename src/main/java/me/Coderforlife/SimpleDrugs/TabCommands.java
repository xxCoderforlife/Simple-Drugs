package me.Coderforlife.SimpleDrugs;

import me.Coderforlife.SimpleDrugs.Druging.Drug;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCommands implements TabCompleter {

    private Main plugin;

    public TabCommands(Main plugin) {
        this.setPlugin(plugin);
    }

    public Main getPlugin() {
        return this.plugin;
    }

    public void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tab = new ArrayList<>();
        if(args.length == 0) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                tab.remove(all.getName());
            }
            tab.add("drugs");
        } else if(args.length == 1) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                tab.remove(all.getName());
            }
            tab.add("help");
            tab.add("list");
            tab.add("reload");
            tab.add("bagofdrugs");
            tab.add("soberup");
            tab.add("give");
            tab.add("version");
            tab.add("settings");
            //tab.add("sell");
        } else if(args.length == 2) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                if(args[0].equalsIgnoreCase("bagofdrugs") || args[0].equalsIgnoreCase("soberup")) {
                    tab.add(all.getName());
                }
            }
            if(args[0].equalsIgnoreCase("give")) {
                for(Drug drugs : Drug.getallDrugs()) {
                    tab.add(drugs.getName());
                }
            }
        } else if(args.length == 3) {
            for(Player all : Bukkit.getOnlinePlayers()) {
                tab.add(all.getName());
            }
        }

        return tab;
    }

}
