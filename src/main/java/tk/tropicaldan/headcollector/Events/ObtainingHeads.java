package tk.tropicaldan.headcollector.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import tk.tropicaldan.headcollector.HeadCollector;
import tk.tropicaldan.headcollector.utils.Heads;
import tk.tropicaldan.headcollector.utils.Logging;

public class ObtainingHeads implements Listener {
    HeadCollector plugin;
    public ObtainingHeads(HeadCollector headCollector){
        plugin = headCollector;
    }
    @EventHandler()
    public void OnDeath(PlayerDeathEvent event){
        if(event.getKeepInventory()) return;
        Player killed = event.getEntity();
        LivingEntity killer = event.getEntity().getKiller();
        if(killer == null){
            //Death Caused from non player event
            return;
        }
        ItemStack Skull = Heads.Head(killed.getUniqueId());
        killed.getWorld().dropItemNaturally(killed.getLocation(),Skull);
    }
}
