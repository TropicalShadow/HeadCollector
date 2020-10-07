package tk.tropicaldan.headcollector.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logging {
    public static void info(String str){
        Bukkit.getLogger().info("[HC] "+ ChatColor.BLUE.toString()+ str);
    }
    public static void warning(String str){
        Bukkit.getLogger().warning("[HC] "+ChatColor.YELLOW.toString()+str);
    }
    public static void danager(String str){
        Bukkit.getLogger().severe("[HC] "+ChatColor.RED.toString()+str);
    }
}
