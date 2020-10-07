package tk.tropicaldan.headcollector.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import tk.tropicaldan.headcollector.HeadCollector;

import java.io.File;
import java.io.IOException;

public class Config {
    HeadCollector plugin;
    public Config(HeadCollector headCollector) {
        plugin = headCollector;
        File ConfigFile = new File(  plugin.getDataFolder(),"config.yaml");
        if(!ConfigFile.exists() || !plugin.getDataFolder().exists()){
            try{
                plugin.getDataFolder().mkdir();
                ConfigFile.createNewFile();
                YamlConfiguration YamlConfig = YamlConfiguration.loadConfiguration(ConfigFile);
                YamlConfig.set("Version","0.0.1");
                YamlConfig.set("AutoPickUpHead",false);
                YamlConfig.save(ConfigFile);
            }catch(IOException e){
                Logging.danager("Failed To Create Plugin Yaml File");
            }
        }
        YamlConfiguration YamlConfig = YamlConfiguration.loadConfiguration(ConfigFile);
        plugin.setVERSION((String) YamlConfig.get("Version"));



    }



}
