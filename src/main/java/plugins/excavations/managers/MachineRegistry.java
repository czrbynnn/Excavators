package plugins.excavations.managers;

public class MachineRegistry {

    private static final ConveyorManager conveyorManager = new ConveyorManager();
    private static final CollectorManager collectorManager = new CollectorManager();
    private static final UpgraderManager upgraderManager = new UpgraderManager();

    public static ConveyorManager getConveyorManager() {
        return conveyorManager;
    }

    public static CollectorManager getCollectorManager() {
        return collectorManager;
    }

    public static UpgraderManager getUpgraderManager() {
        return upgraderManager;
    }

    public static void startAll() {
        conveyorManager.startAll();
        collectorManager.startAll();
        upgraderManager.startAll();
    }

    public static void stopAll() {
        conveyorManager.stopAll();
        collectorManager.stopAll();
        upgraderManager.stopAll();
    }
}
