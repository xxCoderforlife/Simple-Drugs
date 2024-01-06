package me.Coderforlife.SimpleDrugs.Util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Coderforlife.SimpleDrugs.Main;
import net.md_5.bungee.api.ChatColor;

public class Messages {

    private File mes;
    private FileConfiguration mesConfig;
    private Main plugin = Main.plugin;

    private final String header = new String(ChatColor.translateAlternateColorCodes('&',
            "&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&f&l[&4&o&lSIMPLE DRUGS&f&l]"
                    + "&8&l&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l="));
    private final String consoleHeader = new String("&f&l============&4&l[Simple-Drugs]&4&l============");
    
    public String getPrefix(){
        return ChatColor.translateAlternateColorCodes('&', mesConfig.getString("Messages.Prefix" + "&r"));
    }

    public String getHeader(){
        return header;
    }

    public String getPermission(){
        return ChatColor.translateAlternateColorCodes('&', mesConfig.getString("Messages.Permission" + "&r"));
    }

    public String getConsoleHeader(){
        return consoleHeader;
    }

    public void checkForMessagesFile(){
        mes = new File(plugin.getDataFolder(), "messages.yml");
        if(!mes.exists()){
            plugin.saveResource("messages.yml", false);
        }
        mesConfig = new YamlConfiguration();
        try{
            mesConfig.load(mes);
        }catch(Exception e){
            e.printStackTrace();
        }

        }

        public File getMessagesFile(){
            return mes;
        }

        public YamlConfiguration getMessagesConfig(){
            return (YamlConfiguration) mesConfig;
        }

}
