package plugins.excavations.data.models.upgraders;

import org.bukkit.Location;
import plugins.excavations.data.models.OreItem;
import plugins.excavations.data.models.Upgrader;
import plugins.excavations.helpers.DirectionalHelper;

import java.util.*;

public class TNTUpgrader extends Upgrader {

    public TNTUpgrader(UUID id, UUID ownerUUID, Location anchor, DirectionalHelper.Direction direction) {
        super(id, ownerUUID, anchor, direction, 3.0, 0);
    }

    @Override
    protected void upgradeOre(OreItem ore) {
        if (Math.random() < 0.5) {
            ore.getItemEntity().remove();
            if (ore.getHologram() != null) ore.getHologram().remove();
            return;
        }
        super.upgradeOre(ore);
    }

    @Override
    public void tick() {
        System.out.println("tick");
    }
}
