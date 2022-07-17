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
    private String[] commands = {"addiction","bagofdrugs","soberup","shop","settings","help","recipe","list","give","giveSeed","version","editor"};
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tab = new ArrayList<>();
        if(args.length == 0) {
            tab.add("drugs");
        } else if(args.length == 1) {
            for(String s : commands){
                tab.add(s);
            }
            if(args[0].startsWith("s")){
                tab.clear();
                tab.add("shop");
                tab.add("settings");
                tab.add("soberup");
            }else if(args[0].startsWith("g")){
                tab.clear();
                tab.add("give");
                tab.add("giveSeed");

            }else if(args[0].startsWith("a")){
                tab.clear();
                tab.add("addiction");

            }else if(args[0].startsWith("r")){
                tab.clear();
                tab.add("recipe");
            }else if (args[0].startsWith("h")){
                tab.clear();
                tab.add("help");
            }else if(args[0].startsWith("v")){
                tab.clear();
                tab.add("version");
            }else if(args[0].startsWith("b")){
                tab.clear();
                tab.add("bagofdrugs");
            }else if(args[0].startsWith("e")){
                tab.clear();
                tab.add("editor");
            }else if(args[0].startsWith("l")){
                tab.clear();
                tab.add("list");
            }
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("bagofdrugs") || args[0].equalsIgnoreCase("soberup") || args[0].equalsIgnoreCase("addiction")) {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    tab.add(p.getName());
                }
            }
            if(args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("recipe") || args[0].equalsIgnoreCase("giveSeed") ) {
                for(Drug drugs : Main.plugin.getDrugManager().getItems().values()) {
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
