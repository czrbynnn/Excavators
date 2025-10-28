package plugins.excavations.data.models;

import org.bukkit.Location;
import plugins.excavations.helpers.DirectionalHelper;

import java.util.UUID;

public abstract class Dropper extends Machine {

    private double baseValue;

    public Dropper(UUID id, UUID ownerUUID, Location anchor,
                   DirectionalHelper.Direction direction, double baseValue) {
        super(id, ownerUUID, MachineType.DROPPER, anchor, direction, 1.0, true);
        this.baseValue = baseValue;
    }

    public double getBaseValue() { return baseValue; }

    @Override
    public abstract void tick();
}
