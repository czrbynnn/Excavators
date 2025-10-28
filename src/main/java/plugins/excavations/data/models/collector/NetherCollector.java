package plugins.excavations.data.models.collector;

import org.bukkit.Location;
import plugins.excavations.data.models.Collector;
import plugins.excavations.helpers.DirectionalHelper;

import java.util.UUID;

public class NetherCollector extends Collector {

    public NetherCollector(UUID id, UUID ownerUUID, Location anchor, DirectionalHelper.Direction direction) {
        super(id, ownerUUID, anchor, direction, 10.0);
    }

    @Override
    public void collectNearbyOres() {

    }
}
