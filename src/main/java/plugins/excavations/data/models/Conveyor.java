package plugins.excavations.data.models;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import plugins.excavations.Excavations;
import plugins.excavations.helpers.DirectionalHelper;
import plugins.excavations.managers.OreManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Conveyor extends Machine {

    private int tier;
    private static final Set<UUID> movedThisTick = new HashSet<>();

    public Conveyor(UUID id, UUID ownerUUID, Location anchor, DirectionalHelper.Direction direction, int tier) {
        super(id, ownerUUID, Machine.MachineType.CONVEYOR, anchor, direction, 1.0, false);
        this.tier = tier;
    }

    /**
     * Called once per global tick from ConveyorManager
     */
    public void moveItemsOneBlock() {
        Location center = getAnchor().clone().add(0.5, 1, 0.5);
        World world = center.getWorld();
        if (world == null) return;

        List<Item> itemsOnConveyor = world.getEntitiesByClass(Item.class)
                .stream()
                .filter(item -> item.getLocation().distanceSquared(center) <= 0.5 * 0.5)
                .filter(item -> !movedThisTick.contains(item.getUniqueId()))
                .toList();

        if (itemsOnConveyor.isEmpty()) return;

        for (Item item : itemsOnConveyor) {
            movedThisTick.add(item.getUniqueId());

            Location loc = item.getLocation();
            switch (getDirection()) {
                case NORTH -> loc.add(0, 0, -1);
                case SOUTH -> loc.add(0, 0, 1);
                case EAST  -> loc.add(1, 0, 0);
                case WEST  -> loc.add(-1, 0, 0);
            }
            loc.setY(getAnchor().getY() + 1);
            item.teleport(loc);

            OreManager.getActiveOres().values().stream()
                    .filter(ore -> ore.getItemEntity() != null && ore.getItemEntity().equals(item))
                    .findFirst()
                    .ifPresent(ore -> ore.setLoc(loc.clone()));
        }

        System.out.println("Moving " + itemsOnConveyor.size() + " items on conveyor " + getAnchor());
    }

    /**
     * Called once per tick from the manager to clear the moved cache
     */

    public static void clearMovedCache() {
        movedThisTick.clear();
    }

    public int getTier() {
        return tier;
    }

}
