package plugins.excavations.managers;

import org.bukkit.scheduler.BukkitRunnable;
import plugins.excavations.Excavations;
import plugins.excavations.data.models.Upgrader;
import plugins.excavations.managers.interfaces.MachineManager;

import java.util.*;

public class UpgraderManager implements MachineManager<Upgrader> {

    private static final Map<UUID, Upgrader> upgraders = new HashMap<>();
    private BukkitRunnable task;

    @Override
    public void registerMachine(Upgrader upgrader) {
        upgraders.put(upgrader.getId(), upgrader);
    }

    @Override
    public void unregisterMachine(UUID id) {
        upgraders.remove(id);
    }

    @Override
    public Upgrader getMachine(UUID id) {
        return upgraders.get(id);
    }

    @Override
    public Collection<Upgrader> getAllMachines() {
        return upgraders.values();
    }

    @Override
    public void startAll() {
        stopAll();
        task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Upgrader upgrader : upgraders.values()) {
                    upgrader.checkForUpgrades();
                }
            }
        };
        task.runTaskTimer(Excavations.getInstance(), 1L, 10L);
    }

    @Override
    public void stopAll() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}
