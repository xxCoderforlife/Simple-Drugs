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
    private String[] commands = {"help","recipe","list","bagofdrugs","soberup","give","version","settings","addiction"};
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tab = new ArrayList<>();
        if(args.length == 0) {
            tab.add("drugs");
        } else if(args.length == 1) {
            for(String s : commands){
                tab.add(s);
            }
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("bagofdrugs") || args[0].equalsIgnoreCase("soberup")) {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    tab.add(p.getName());
                }
            }
            if(args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("recipe")) {
                for(Drug drugs : Main.plugin.getDrugManager().getallDrugs()) {
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
