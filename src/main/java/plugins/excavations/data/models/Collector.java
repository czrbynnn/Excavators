package plugins.excavations.data.models;

import org.bukkit.Location;
import plugins.excavations.helpers.DirectionalHelper;

import java.util.UUID;

public abstract class Collector extends Machine {

    public Collector(UUID id, UUID ownerUUID, Location anchor,
                     DirectionalHelper.Direction direction, double multiplier) {
        super(id, ownerUUID, MachineType.COLLECTOR, anchor, direction, 0, true);
    }

    public abstract void collectNearbyOres();

    @Override
    public void tick() {
        collectNearbyOres();
    }

}
