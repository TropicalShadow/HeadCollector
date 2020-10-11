package tk.tropicaldan.headcollector;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import tk.tropicaldan.headcollector.Enchants.Decapitator;
import tk.tropicaldan.headcollector.Events.BlockBreakPlace;
import tk.tropicaldan.headcollector.Events.ObtainingHeads;
import tk.tropicaldan.headcollector.utils.Config;
import tk.tropicaldan.headcollector.utils.Logging;

import java.util.ArrayList;
import java.util.Objects;

public final class HeadCollector extends JavaPlugin {
    ArrayList<String> CommandNames = new ArrayList<>();
    private NamespacedKey HeadUUIDKey;
    BlockBreakPlace blockBreakPlace;
    ObtainingHeads obtainingHeads;
    PluginManager manager;
    Commands commands;
    String VERSION = "";
    Config config;

    @Override
    public void onLoad(){
        Logging.info("Loading HeadCollector...");
        HeadUUIDKey = new NamespacedKey(this,"HeadUUIDKey");
        manager = getServer().getPluginManager();
        obtainingHeads = new ObtainingHeads(this);
        blockBreakPlace = new BlockBreakPlace(this);
        config = new Config(this);
        commands = new Commands(this);
        Decapitator.register();
        Logging.info(String.format("Loaded HeadCollector. v %s",VERSION));
    }

    @Override
    public void onEnable() {
        Logging.info("Enabling HeadCollector...");
        RegisterEvents(obtainingHeads,blockBreakPlace);
        RegisterCommands();
        Logging.info("Enabled HeadCollector.");
    }

    @Override
    public void onDisable() {
        Logging.info("Disabling HeadCollector...");
        Decapitator.unregister();
        unRegisterEvents();
        Logging.info("Disabled HeadCollector.");
    }


    private void RegisterCommands(){
        CommandNames.add("headcollector");

        CommandNames.forEach(cmd ->{
            Objects.requireNonNull(getCommand(cmd)).setExecutor(commands);
            Objects.requireNonNull(getCommand(cmd)).setTabCompleter(commands);
        });
    }

    private final ArrayList<Listener> Events = new ArrayList<>();


    private void RegisterEvents(Listener... listeners){
        for (Listener listener : listeners) {
            manager.registerEvents(listener,this);
            Events.add(listener);
        }
    }
    private void unRegisterEvents(){
        for (Listener listener : Events) {
            HandlerList.unregisterAll(listener);
            Events.remove(listener);
        }
    }

    public void setVERSION(String str){
        VERSION = str;
    }
    public String getVERSION(){
        return VERSION;
    }
    public NamespacedKey getHeadUUIDKey(){
        return HeadUUIDKey;
    }
    public Config getCustomConfig() {
        return config;
    }
}
