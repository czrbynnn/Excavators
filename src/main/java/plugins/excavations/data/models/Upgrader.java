package plugins.excavations.data.models;

import org.bukkit.Location;
import org.bukkit.World;
import plugins.excavations.helpers.DirectionalHelper;
import plugins.excavations.managers.OreManager;
import plugins.excavations.util.Colorize;

import java.util.UUID;

public class Upgrader extends Conveyor {

    private final double multiplier;

    public Upgrader(UUID id, UUID ownerUUID, Location anchor, DirectionalHelper.Direction dir, int speed, double multiplier) {
        super(id, ownerUUID, anchor, dir, speed);
        this.multiplier = multiplier;
    }

    @Override
    public void moveItemsOneBlock() {
        super.moveItemsOneBlock();

        Location center = getAnchor().clone().add(0.5, 1, 0.5);
        World world = center.getWorld();
        if (world == null) return;

        System.out.println("Checking uprader at " + center);

        OreManager.getActiveOres().values()
                        .forEach(ore -> {
                            System.out.println(ore.getLoc().distance(center));
                        });

        OreManager.getActiveOres().values().stream()
                .filter(ore -> ore.getLoc().getWorld().equals(world))
                .filter(ore -> ore.getLoc().distanceSquared(center) <= 0.4 * 0.4)
                .forEach(ore -> {
                    System.out.println("Ore at " + ore.getLoc() + " distance^2=" + ore.getLoc().distanceSquared(center));

                    double oldValue = ore.getValue();
                    double newValue = oldValue * multiplier;
                    ore.setValue(newValue);

                    if (ore.getHologram() != null && ore.getHologram().isValid()) {
                        ore.getHologram().setCustomName(Colorize.colorize("&b$" + newValue));
                    }
                });
    }

    public double getMultiplier() {
        return multiplier;
    }
}
