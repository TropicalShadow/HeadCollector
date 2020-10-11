package tk.tropicaldan.headcollector.utils;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import tk.tropicaldan.headcollector.HeadCollector;

import java.util.ArrayList;
import java.util.UUID;

public class Heads {
    public static enum MobHeads{
        CREEPER("CREEPER",Material.CREEPER_HEAD,Material.CREEPER_WALL_HEAD),
        ZOMBIE("ZOMBIE",Material.ZOMBIE_HEAD,Material.ZOMBIE_WALL_HEAD),
        SKELETON("SKELETON",Material.SKELETON_SKULL,Material.SKELETON_WALL_SKULL),
        WITHER_SKELETON("WITHER_SKELETON",Material.WITHER_SKELETON_SKULL,Material.WITHER_SKELETON_WALL_SKULL),
        DRAGON("DRAGON",Material.DRAGON_HEAD,Material.DRAGON_WALL_HEAD);

        public static boolean contains(String name){
            for (MobHeads c : MobHeads.values()) {
                if (c.getName().equals(name)) {
                    return true;
                }
            }

            return false;
        }
        private final String name;
        private final Material DefaultHead;
        private final Material WallHead;

        MobHeads(String name,Material DefaultHead,Material WallHead){
            this.name= name;
            this.DefaultHead = DefaultHead;
            this.WallHead = WallHead;
        }

        public String getName() {
            return name;
        }

        public Material getDefaultHead() {
            return DefaultHead;
        }

        public Material getWallHead() {
            return WallHead;
        }
    }
    public static ItemStack MobHeadItem(LivingEntity entity,Material mat){
        ItemStack IS = new ItemStack(mat, 1);
        SkullMeta IM = (SkullMeta) IS.getItemMeta();
        assert IM != null;
        IM.setDisplayName(entity.getName()+" head");
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(ChatColor.BLUE.toString()+entity.getType().name()+" head");
        IM.setLore(Lore);
        IS.setItemMeta(IM);
        return IS;
    }

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
