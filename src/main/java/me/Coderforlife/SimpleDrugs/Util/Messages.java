package me.Coderforlife.SimpleDrugs.Util;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Coderforlife.SimpleDrugs.Main;
import net.md_5.bungee.api.ChatColor;

public class Messages {

    public Messages(){
        checkForMessagesFile();
    }

    private File mes;
    private FileConfiguration mesConfig;
    private Main plugin = Main.plugin;
    private Logger logger = plugin.getLogger();

    private final String header = new String(ChatColor.translateAlternateColorCodes('&',
            "&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&f&l[&4&o&lSIMPLE DRUGS&f&l]"
                    + "&8&l&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l=&0&l=&8&l="));
    private final String consoleHeader = new String("&f&l============&4&l[Simple-Drugs]&4&l============");
    


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
            logger.info("Loaded messages.yml");
        }catch(Exception e){
            logger.severe("Could not load messages.yml");
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
