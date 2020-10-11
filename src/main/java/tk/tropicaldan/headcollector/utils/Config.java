package tk.tropicaldan.headcollector.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import tk.tropicaldan.headcollector.HeadCollector;

import java.io.File;
import java.io.IOException;

public class Config {
    HeadCollector plugin;
    public Config(HeadCollector headCollector) {
        plugin = headCollector;
        File ConfigFile = new File(  plugin.getDataFolder(),"config.yml");
        if(!ConfigFile.exists() || !plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
            plugin.saveResource("config.yml", false);
        }
        YamlConfiguration YamlConfig = YamlConfiguration.loadConfiguration(ConfigFile);
        plugin.setVERSION((String) YamlConfig.get("Version"));
        plugin.reloadConfig();
    }
    public Object get(String key){
        File ConfigFile = new File(  plugin.getDataFolder(),"config.yml");
        YamlConfiguration ConfigYaml = YamlConfiguration.loadConfiguration(ConfigFile);
        return ConfigYaml.get(key);
    }
    public boolean set(String key,Object Value){
        File ConfigFile = new File(  plugin.getDataFolder(),"config.yml");
        YamlConfiguration ConfigYaml = YamlConfiguration.loadConfiguration(ConfigFile);
        ConfigYaml.set(key,Value);
        try {
            ConfigYaml.save(ConfigFile);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }



}
