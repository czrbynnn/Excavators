package plugins.excavations.data.models.upgraders;

import org.bukkit.Location;
import plugins.excavations.data.models.Upgrader;
import plugins.excavations.helpers.DirectionalHelper;

import java.util.UUID;

public class HolyUpgrader extends Upgrader {

    public HolyUpgrader(UUID id, UUID ownerUUID, Location anchor, DirectionalHelper.Direction direction) {
        super(id, ownerUUID, anchor, direction, 1.0, 20.0);
    }

    @Override
    public void tick() {
        System.out.println("tick2");
    }
}
