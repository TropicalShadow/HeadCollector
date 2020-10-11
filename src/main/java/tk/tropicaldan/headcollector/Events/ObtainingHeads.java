package tk.tropicaldan.headcollector.Events;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import tk.tropicaldan.headcollector.Enchants.Decapitator;
import tk.tropicaldan.headcollector.HeadCollector;
import tk.tropicaldan.headcollector.utils.Heads;
import tk.tropicaldan.headcollector.utils.Logging;

public class ObtainingHeads implements Listener {
    HeadCollector plugin;
    public ObtainingHeads(HeadCollector headCollector){
        plugin = headCollector;
    }

    @EventHandler()
    public void onEntityDeath(EntityDeathEvent event){
        if(event.getEntity() instanceof Player){return;}
        LivingEntity killed = event.getEntity();
        Player killer = killed.getKiller();
        if(killer == null){
            //Death Caused from non player event
            return;
        }
        if(!killer.getInventory().getItemInMainHand().containsEnchantment(Decapitator.enchantment)){return ;}
        if(Heads.MobHeads.contains(event.getEntityType().name())){
            Material mat = Heads.MobHeads.valueOf(event.getEntityType().name()).getDefaultHead();
            ItemStack skull = Heads.MobHeadItem(event.getEntity(),mat);
            killed.getWorld().dropItemNaturally(killed.getLocation(),skull);
        }
    }

    @EventHandler()
    public void OnPlayerDeath(PlayerDeathEvent event){
        if(event.getKeepInventory()) return;
        Player killed = event.getEntity();
        Player killer = event.getEntity().getKiller();
        if(killer == null ){
            //Death Caused from non player event
            return;
        }
        if(!killer.getInventory().getItemInMainHand().containsEnchantment(Decapitator.enchantment)){return ;}
        ItemStack Skull = Heads.HeadItem(killed.getUniqueId());
        killed.getWorld().dropItemNaturally(killed.getLocation(),Skull);
    }
}
