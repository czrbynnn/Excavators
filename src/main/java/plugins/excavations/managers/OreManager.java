package plugins.excavations.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import plugins.excavations.Excavations;
import plugins.excavations.data.models.Dropper;
import plugins.excavations.data.models.OreItem;
import plugins.excavations.util.Colorize;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OreManager {

    private static final Map<UUID, OreItem> activeOres = new HashMap<>();

    public static OreItem spawnOre(Dropper dropper) {
        UUID owner = dropper.getOwnerUUID();
        double value = dropper.getBaseValue();

        Location loc = switch (dropper.getDirection()) {
            case NORTH -> dropper.getAnchor().clone().add(0.5, 1, -2.5);
            case EAST -> dropper.getAnchor().clone().add(3.5, 1, 0.5);
            case WEST -> dropper.getAnchor().clone().add(-2.5, 1, 0.5);
            case SOUTH -> dropper.getAnchor().clone().add(0.5, 1, 3.5);
        };

        OreItem ore = new OreItem(UUID.randomUUID(), owner, value, loc);

        ItemStack display = new ItemStack(Material.GOLD_BLOCK);
        Item dropped = loc.getWorld().dropItem(loc, display);
        dropped.setVelocity(new Vector(0, 0, 0));
        dropped.setPickupDelay(Integer.MAX_VALUE);

        ore.setItemEntity(dropped);
        activeOres.put(ore.getId(), ore);

        ArmorStand holo = spawnHologram(ore);
        ore.setHologram(holo);

        Bukkit.getScheduler().runTaskTimer(Excavations.getInstance(), () -> {
            if (dropped.isValid() && holo.isValid()) {
                holo.teleport(dropped.getLocation().add(0, 0.5, 0));
            } else {
                removeOre(ore);
            }
        }, 0L, 1L);

        Bukkit.getScheduler().runTaskLater(
                Excavations.getInstance(),
                () -> removeOre(ore),
                ore.getLifetime() * 20L
        );

        return ore;
    }


    private static ArmorStand spawnHologram(OreItem ore) {
        Location holoLoc = ore.getLoc().clone().add(0, 0.5, 0);
        return ore.getLoc().getWorld().spawn(holoLoc, ArmorStand.class, stand -> {
            stand.setCustomName(Colorize.colorize("&e$" + ore.getValue()));
            stand.setCustomNameVisible(true);
            stand.setInvisible(true);
            stand.setMarker(true);
            stand.setGravity(false);
        });
    }

    public static Map<UUID, OreItem> getActiveOres() {
        return activeOres;
    }

    public static void removeOre(OreItem ore) {
        if (ore.getItemEntity() != null && ore.getItemEntity().isValid()) {
            ore.getItemEntity().remove();
        }
        if (ore.getHologram() != null && ore.getHologram().isValid()) {
            ore.getHologram().remove();
        }
        activeOres.remove(ore.getId());
    }

}
