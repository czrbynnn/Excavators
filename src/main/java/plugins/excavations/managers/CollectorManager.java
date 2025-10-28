package plugins.excavations.managers;

import plugins.excavations.data.models.Collector;
import plugins.excavations.managers.interfaces.MachineManager;

import java.util.*;

public class CollectorManager implements MachineManager<Collector> {

    private static final Map<UUID, Collector> collectors = new HashMap<>();

    @Override
    public void registerMachine(Collector collector) {
        collectors.put(collector.getId(), collector);
        collector.startTask();
    }

    @Override
    public void unregisterMachine(UUID id) {

    }

    @Override
    public Collector getMachine(UUID id) {
        return null;
    }

    @Override
    public Collection<Collector> getAllMachines() {
        return List.of();
    }

    @Override
    public void startAll() {

    }

    @Override
    public void stopAll() {

    }
}
