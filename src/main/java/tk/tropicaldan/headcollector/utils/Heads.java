package tk.tropicaldan.headcollector.utils;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import tk.tropicaldan.headcollector.HeadCollector;

import java.util.ArrayList;
import java.util.UUID;

public class Heads {

    public static void HeadBlock(OfflinePlayer player, Block block){
        BlockState blockState = block.getState();
        Skull skullMeta = (Skull) blockState;
        skullMeta.setOwningPlayer(player);
        skullMeta.getPersistentDataContainer().set(HeadCollector.getPlugin(HeadCollector.class).getHeadUUIDKey(), new UUIDDataType(),player.getUniqueId());
        skullMeta.update();
    }
    public static void HeadBlock(UUID uuid, Block block){
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        HeadBlock(player,block);
    }


    public static ItemStack HeadItem(OfflinePlayer player){
        ItemStack IS = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta IM = (SkullMeta) IS.getItemMeta();
        assert IM != null;
        IM.setDisplayName(player.getName()+"'s head");
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(ChatColor.BLUE.toString()+player.getName()+"'s head");
        IM.setLore(Lore);
        IM.getPersistentDataContainer().set(HeadCollector.getPlugin(HeadCollector.class).getHeadUUIDKey(), new UUIDDataType(),player.getUniqueId());
        IM.setOwningPlayer(player);
        IS.setItemMeta(IM);
        return IS;
    }
    public static ItemStack HeadItem(UUID uuid){
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        return HeadItem(player);
    }
}
