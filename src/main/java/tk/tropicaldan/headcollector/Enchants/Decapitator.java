package tk.tropicaldan.headcollector.Enchants;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import tk.tropicaldan.headcollector.utils.Logging;

import java.lang.reflect.Field;
import java.util.Map;

public class Decapitator extends Enchantment{

    public static Decapitator enchantment;

    public Decapitator() {
        super(NamespacedKey.minecraft("decapitator"));
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        String ItemName = item.getType().getKey().getKey();
        return ItemName.contains("SWORD") || ItemName.contains("AXE") || ItemName.contains("HOE") || item.getType().equals(Material.ENCHANTED_BOOK);
    }

    @Override
    public boolean conflictsWith(Enchantment arg0) {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public String getName() {
        return "Decapitator";
    }

    @Override
    public int getStartLevel() {
        // TODO Auto-generated method stub
        return 1;
    }

    public static void register() {
        enchantment = new Decapitator();
        try {
            Field acceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
            acceptingNew.setAccessible(true);
            acceptingNew.set(null, true);
            acceptingNew.setAccessible(false);
            Enchantment.registerEnchantment(enchantment);
            Logging.info("Decapitator Enchant Registered");
        } catch (Exception ex) {
            Logging.danager("Failed To Register Decapitator Enchant");
            ex.printStackTrace();
        }
        Enchantment.stopAcceptingRegistrations();
    }

    @SuppressWarnings({ "unchecked"})
    public static void unregister() {
        try {
            Field byNameField = Enchantment.class.getDeclaredField("byName");
            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            byNameField.setAccessible(true);
            byKeyField.setAccessible(true);
            Map<String, Enchantment> byName = (Map<String, Enchantment>) byNameField.get(null);
            Map<NamespacedKey, Enchantment> byKey = (Map<NamespacedKey, Enchantment>) byKeyField.get(null);
            byName.remove(enchantment.getName());
            byKey.remove(enchantment.getKey());
            Logging.info("Decapitator Enchant unregistered.");
        } catch (Exception ex) {
            Logging.danager("Failed to unregister Decapitator Enchant");
            ex.printStackTrace();
        }
    }
}