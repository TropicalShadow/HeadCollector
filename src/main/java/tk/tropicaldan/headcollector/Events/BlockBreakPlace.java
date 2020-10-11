package tk.tropicaldan.headcollector.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import tk.tropicaldan.headcollector.HeadCollector;
import tk.tropicaldan.headcollector.utils.Heads;
import tk.tropicaldan.headcollector.utils.UUIDDataType;

import java.util.Objects;
import java.util.UUID;

public class BlockBreakPlace implements Listener {
    HeadCollector plugin;
    public BlockBreakPlace(HeadCollector headCollector){
        plugin = headCollector;
    }

    @EventHandler()
    public void onHeadBreak(BlockBreakEvent event){
        if((!event.getBlock().getType().equals(Material.PLAYER_HEAD) && !event.getBlock().getType().equals(Material.PLAYER_WALL_HEAD))|| event.isCancelled()){return;}
        event.setCancelled(true);
        Block block = event.getBlock();
        BlockState blockState = block.getState();
        Skull skull = (Skull) blockState;
        ItemStack item = Heads.HeadItem(Objects.requireNonNull(skull.getOwningPlayer()));
        if(plugin.getConfig().getBoolean("AutoPickUp.Mine")){
            Player player = event.getPlayer();
            PlayerInventory inv = player.getInventory();
            if(inv.contains(item) || inv.firstEmpty()>=0){
                inv.addItem(item);
            }else{
                player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("AutoPickUp.NoRoomMessage", "No Room in inv")));
                return;
            }
        }else{
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),item);
        }
        event.getBlock().setType(Material.AIR);
    }
    @EventHandler()
    public void onHeadPlace(BlockPlaceEvent event){
        if((!event.getBlock().getType().equals(Material.PLAYER_HEAD) && !event.getBlock().getType().equals(Material.PLAYER_WALL_HEAD)) || event.isCancelled()){return;}
        ItemStack item = event.getItemInHand();
        Block block = event.getBlockPlaced();
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        boolean hasMeta = itemMeta.getPersistentDataContainer().has(plugin.getHeadUUIDKey(),new UUIDDataType());
        SkullMeta skullMeta = (SkullMeta) itemMeta;
        if(!hasMeta){
            if(skullMeta.getOwningPlayer()!=null){
                Heads.HeadBlock(skullMeta.getOwningPlayer().getUniqueId(),block);
            }else{
                event.getPlayer().sendMessage("The Head you tried and placed was not registered to anyone");
                event.setCancelled(true);
            }
        }else {
            UUID uuid = itemMeta.getPersistentDataContainer().get(plugin.getHeadUUIDKey(), new UUIDDataType());
            Heads.HeadBlock(uuid, block);
        }


    }
}
