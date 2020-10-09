package tk.tropicaldan.headcollector.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tk.tropicaldan.headcollector.HeadCollector;
import tk.tropicaldan.headcollector.utils.Heads;
import tk.tropicaldan.headcollector.utils.UUIDDataType;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class BlockBreakPlace implements Listener {
    HeadCollector plugin;
    public BlockBreakPlace(HeadCollector headCollector){
        plugin = headCollector;
    }

    @EventHandler()
    public void onHeadBreak(BlockBreakEvent event){
        if(!event.getBlock().getType().equals(Material.PLAYER_HEAD) || event.isCancelled()){return;}
        event.setCancelled(true);
        Block block = event.getBlock();
        BlockState blockState = block.getState();
        Skull skull = (Skull) blockState;
        ItemStack item = Heads.HeadItem(Objects.requireNonNull(skull.getOwningPlayer()));
        event.getBlock().setType(Material.AIR);
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);
    }
    @EventHandler()
    public void onHeadPlace(BlockPlaceEvent event){
        if(!event.getItemInHand().getType().equals(Material.PLAYER_HEAD) || event.isCancelled()){return;}
        ItemStack item = event.getItemInHand();
        Block block = event.getBlockPlaced();
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        boolean hasMeta = itemMeta.getPersistentDataContainer().has(plugin.getHeadUUIDKey(),new UUIDDataType());
        if(!hasMeta){
            event.getPlayer().sendMessage("The Head you tried and placed was not registered to anyone");
            event.setCancelled(true);
            return;
        }
        UUID uuid = itemMeta.getPersistentDataContainer().get(plugin.getHeadUUIDKey(),new UUIDDataType());
        Heads.HeadBlock(uuid,block);



    }
}
