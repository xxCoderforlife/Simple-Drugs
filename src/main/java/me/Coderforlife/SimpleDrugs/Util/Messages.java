package me.Coderforlife.SimpleDrugs.Util;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class Messages {

    private final String prefix = new String(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSD&8&l]&r "));
    private final String header = new String(ChatColor.translateAlternateColorCodes('&',
            "&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&f&l[&4&o&lSIMPLE DRUGS&f&l]"
                    + "&8&l&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l="));
    private final String permission = new String(prefix + ChatColor.translateAlternateColorCodes('&', 
    "&cYou don't have permission for this command."));
    private final String consoleHeader = new String("&f&l============&4&l[Simple-Drugs]&4&l============");

    public Messages(){

    }

    public String getPrefix(){
        return prefix;
    }

    public String getHeader(){
        return header;
    }

    public String getPermission(){
        return permission;
    }

    public String getConsoleHeader(){
        return consoleHeader;
    }

}
