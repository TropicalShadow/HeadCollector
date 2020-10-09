package tk.tropicaldan.headcollector;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import tk.tropicaldan.headcollector.Events.BlockBreakPlace;
import tk.tropicaldan.headcollector.Events.ObtainingHeads;
import tk.tropicaldan.headcollector.utils.Config;
import tk.tropicaldan.headcollector.utils.Logging;

public final class HeadCollector extends JavaPlugin {
    String VERSION = "";
    private NamespacedKey HeadUUIDKey;
    PluginManager manager;
    ObtainingHeads obtainingHeads;
    BlockBreakPlace blockBreakPlace;
    Config config;
    @Override
    public void onLoad(){
        Logging.info("Loading HeadCollector...");
        HeadUUIDKey = new NamespacedKey(this,"HeadUUIDKey");
        manager = getServer().getPluginManager();
        obtainingHeads = new ObtainingHeads(this);
        blockBreakPlace = new BlockBreakPlace(this);
        config = new Config(this);
        Logging.info(String.format("Loaded HeadCollector. v %s",VERSION));
    }

    @Override
    public void onEnable() {
        Logging.info("Enabling HeadCollector...");
        manager.registerEvents(obtainingHeads,this);
        manager.registerEvents(blockBreakPlace,this);
        Logging.info("Enabled HeadCollector.");
    }

    @Override
    public void onDisable() {
        Logging.info("Disabling HeadCollector...");
        HandlerList.unregisterAll(obtainingHeads);
        HandlerList.unregisterAll(blockBreakPlace);
        Logging.info("Disabled HeadCollector.");
    }

    public String setVERSION(String str){
        VERSION = str;
        return VERSION;
    }
    public String getVERSION(){
        return VERSION;
    }
    public NamespacedKey getHeadUUIDKey(){
        return HeadUUIDKey;
    }
}
