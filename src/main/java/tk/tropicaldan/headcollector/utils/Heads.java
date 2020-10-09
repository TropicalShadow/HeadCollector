package tk.tropicaldan.headcollector.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

public class Heads {
    public static ItemStack Head(OfflinePlayer player){
        ItemStack IS = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta IM = (SkullMeta) IS.getItemMeta();
        assert IM != null;
        IM.setDisplayName(player.getName()+"'s head");
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(ChatColor.BLUE.toString()+player.getName()+"'s head");
        IM.setLore(Lore);
        IM.setOwningPlayer(player);
        IS.setItemMeta(IM);
        return IS;
    }
    public static ItemStack Head(UUID uuid){
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        return Head(player);
    }
}
