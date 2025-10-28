package plugins.excavations.factories;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import plugins.excavations.Excavations;
import plugins.excavations.data.models.Machine;
import plugins.excavations.util.Colorize;

import java.util.ArrayList;
import java.util.List;

public class MachineItemFactory {

    private static final NamespacedKey MACHINE_TYPE_KEY = new NamespacedKey(Excavations.getInstance(), "machine_type");

    public static ItemStack createMachineItem(Machine.MachineType type) {
        Material material;
        String displayName;
        List<String> lore = new ArrayList<>();

        switch (type) {
            case DROPPER -> {
                material = Material.BROWN_CONCRETE;
                displayName = Colorize.colorize("&bDropper");
                lore.add(Colorize.colorize("&7Spawns ores over time."));
            }
            case CONVEYOR -> {
                material = Material.BLACK_CONCRETE;
                displayName = Colorize.colorize("&eConveyor");
                lore.add(Colorize.colorize("&7Moves ores forward"));
            }
            case UPGRADER -> {
                material = Material.YELLOW_CONCRETE;
                displayName = Colorize.colorize("&aUpgrader");
                lore.add(Colorize.colorize("&7Multiplies ore value once per pass."));
            }
            case COLLECTOR -> {
                material = Material.RED_CONCRETE;
                displayName = Colorize.colorize("&6Collector");
                lore.add(Colorize.colorize("&7Sells ore automatically."));
            }
            default -> throw new IllegalArgumentException("Unknown type");
        }
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);

        meta.getPersistentDataContainer().set(MACHINE_TYPE_KEY, PersistentDataType.STRING, type.name());
        item.setItemMeta(meta);

        return item;
    }

}
