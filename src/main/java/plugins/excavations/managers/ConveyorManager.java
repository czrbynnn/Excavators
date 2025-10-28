package plugins.excavations.managers;

import org.bukkit.scheduler.BukkitRunnable;
import plugins.excavations.Excavations;
import plugins.excavations.data.models.Conveyor;

import java.util.ArrayList;
import java.util.List;

public class ConveyorManager {

    private static List<Conveyor> conveyors = new ArrayList<>();
    private static boolean taskRunning = false;

    public static void register(Conveyor conveyor) {
        conveyors.add(conveyor);
    }

    public static void unregisterConveyor(Conveyor conveyor) {
        conveyors.remove(conveyor);
    }

    public static void startGlobalTask() {
        if (taskRunning) return;
        taskRunning = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                Conveyor.clearMovedCache();
                for (Conveyor conveyor : conveyors) {
                    if (!conveyor.isDisabled()) {
                        conveyor.moveItemsOneBlock();
                    }
                }
            }
        }.runTaskTimer(Excavations.getInstance(), 20L, 20L);
    }

}
