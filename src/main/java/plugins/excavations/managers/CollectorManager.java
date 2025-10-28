package plugins.excavations.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import plugins.excavations.data.models.Collector;

import java.util.*;

public class CollectorManager {

    private static final Map<UUID, Collector> activeCollectors = new HashMap<>();

    public static void registerCollector(Collector collector) {
        activeCollectors.put(collector.getId(), collector);
        collector.startTask();
        Bukkit.getLogger().info("Registered collector " + collector.getId());
    }

    public static void unregisterCollector(UUID id) {
        Collector c = activeCollectors.remove(id);
        if (c != null) c.stopTask();
    }

    public static Collector getCollector(UUID id) {
        return activeCollectors.get(id);
    }

    public static Collection<Collector> getAllCollectors() {
        return activeCollectors.values();
    }

    public static void clearAll() {
        for (Collector c : activeCollectors.values()) {
            c.stopTask();
        }
        activeCollectors.clear();
    }

    public static void startAll() {
        Bukkit.getLogger().info("Starting " + activeCollectors.size() + " collectors");
        for (Collector c : activeCollectors.values()) {
            c.startTask();
        }
    }

    public static void stopAll() {
        for (Collector c : activeCollectors.values()) {
            c.stopTask();
        }
    }

    public static List<Collector> getNearbyCollectors(Location loc, double radius) {
        return activeCollectors.values().stream()
                .filter(c -> c.getAnchor().getWorld().equals(loc.getWorld()))
                .filter(c -> c.getAnchor().distanceSquared(loc) <= radius * radius)
                .toList();
    }

}
