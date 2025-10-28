package plugins.excavations.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import plugins.excavations.Excavations;
import plugins.excavations.data.models.Dropper;
import plugins.excavations.data.models.Machine;
import plugins.excavations.data.models.Tycoon;
import plugins.excavations.helpers.DirectionalHelper;
import plugins.excavations.util.Colorize;

import java.util.*;

public class MachineManager {

    private static final Map<UUID, Machine> machines = new HashMap<>();

    private static final Map<Location, UUID> machineByLocation = new HashMap<>();

    private final TycoonManager tycoonManager;

    public MachineManager(TycoonManager tycoonManager) {
        this.tycoonManager = tycoonManager;
    }

    /**
     * Creates and registers a new machine for a tycoon
     */
    public Machine placeMachine(Player p, Tycoon tycoon, Machine.MachineType type, Location loc, DirectionalHelper.Direction dir) {
        Machine machine = new Machine(
                UUID.randomUUID(),
                p.getUniqueId(),
                type,
                loc,
                dir,
                getDefaultMultiplier(type),
                false
        );

        machines.put(machine.getId(), machine);
        machineByLocation.put(loc, machine.getId());
        tycoon.addMachine(machine);

        loc.getBlock().setType(getMaterialForType(type));



        return machine;
    }

    /**
     * Removes a machine by location
     */
    public void removeMachine(Location loc) {
        UUID id = machineByLocation.remove(loc);
        if (id != null) {
            Machine machine = machines.remove(id);
            if (machine != null) {
                loc.getBlock().setType(org.bukkit.Material.AIR);
            }
        }
    }

    /**
     * Gets a machine at a specific location
     */
    public Machine getMachineAt(Location loc) {
        UUID id = machineByLocation.get(loc);
        return id != null ? machines.get(id) : null;
    }

    /**
     * Returns all machines of a tycoon
     */
    public List<Machine> getMachinesOfTycoon(UUID owner) {
        List<Machine> list = new ArrayList<>();
        for (Machine m : machines.values()) {
            if (m.getOwnerUUID().equals(owner)) {
                list.add(m);
            }
        }
        return list;
    }

    /**
     * Default multipliers per type
     */
    private static double getDefaultMultiplier(Machine.MachineType type) {
        return switch (type) {
            case UPGRADER -> 1.5;
            case DROPPER, COLLECTOR, CONVEYOR -> 1.0;
            case SPECIAL -> 2.0;
        };
    }

    /**
     * Block material for each type
     */
    private static Material getMaterialForType(Machine.MachineType type) {
        return switch (type) {
            case DROPPER -> Material.BROWN_CONCRETE;
            case CONVEYOR -> Material.BLACK_CONCRETE;
            case UPGRADER -> Material.YELLOW_CONCRETE;
            case COLLECTOR -> Material.RED_CONCRETE;
            case SPECIAL -> Material.EMERALD_BLOCK;
        };
    }

    public static Collection<Machine> getAllMachines() {
        return machines.values();
    }

}
