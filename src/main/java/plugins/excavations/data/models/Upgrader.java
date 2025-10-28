package plugins.excavations.data.models;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Item;
import plugins.excavations.helpers.DirectionalHelper;
import plugins.excavations.managers.OreManager;
import plugins.excavations.util.Colorize;


import java.util.List;
import java.util.UUID;

public abstract class Upgrader extends Machine {

    private final double multiplier;
    private final double additiveBonus;

    public Upgrader(UUID id, UUID ownerUUID, Location anchor, DirectionalHelper.Direction direction,
                    double multiplier, double additiveBonus) {
        super(id, ownerUUID, MachineType.UPGRADER, anchor, direction, 1.0, false);
        this.multiplier = multiplier;
        this.additiveBonus = additiveBonus;
    }

    /**
     * Called periodically by UpgraderManager
     */
    public void checkForUpgrades() {
        Location checkLoc = getAnchor().clone().add(0.5, 1.0, 0.5);
        World world = checkLoc.getWorld();
        if (world == null) return;

        List<Item> items = world.getEntitiesByClass(Item.class).stream()
                .filter(item -> item.getLocation().distanceSquared(checkLoc) <= 0.4)
                .toList();

        if (items.isEmpty()) return;

        for (Item item : items) {
            OreManager.getActiveOres().values().stream()
                    .filter(ore -> ore.getItemEntity() != null && ore.getItemEntity().equals(item))
                    .findFirst()
                    .ifPresent(this::upgradeOre);
        }
    }

    /**
     * Handles upgrading the ore
     */
    protected void upgradeOre(OreItem ore) {
        double oldValue = ore.getValue();
        double newValue = (oldValue * multiplier) + additiveBonus;
        ore.setValue(newValue);

        if (ore.getHologram() != null && ore.getHologram().isValid()) {
            ore.getHologram().setCustomName(Colorize.colorize("&e$" + newValue));
        }
    }

    public double getMultiplier() {
        return multiplier;
    }

    public double getAdditiveBonus() {
        return additiveBonus;
    }


}
