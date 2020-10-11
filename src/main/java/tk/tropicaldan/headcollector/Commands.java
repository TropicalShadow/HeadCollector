package tk.tropicaldan.headcollector;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import tk.tropicaldan.headcollector.Enchants.Decapitator;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {
    HeadCollector plugin;

    public Commands(HeadCollector plug){
        plugin = plug;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean isPlayer = sender instanceof Player;
        if(command.getName().equalsIgnoreCase("headcollector") && isPlayer){
            if(args.length>=1){
                if(args[0].equalsIgnoreCase("enchantDecapitator")){
                    ((Player)sender).getInventory().getItemInMainHand().addUnsafeEnchantment(Decapitator.enchantment,1);
                    ItemMeta meta = ((Player)sender).getInventory().getItemInMainHand().getItemMeta();
                    meta.setDisplayName("DECAPITATOR");
                    ((Player)sender).getInventory().getItemInMainHand().setItemMeta(meta);
                    return true;
                }
            }
            ((Player)sender).sendMessage("Usage: /headcollector [enchantDecapitator]");
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        boolean isPlayer = sender instanceof Player;
        ArrayList<String> list = new ArrayList<>();
        if(command.getName().equalsIgnoreCase("headcollector") && isPlayer){
            if(args.length>=1) {
                list.add("giveDecapitator");
            }
        }
        return list;
    }
}
