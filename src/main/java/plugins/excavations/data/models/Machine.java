package plugins.excavations.data.models;

import org.bukkit.Location;
import plugins.excavations.helpers.DirectionalHelper;

import java.util.UUID;

public class Machine {

    public enum MachineType {
        DROPPER,
        CONVEYOR,
        UPGRADER,
        COLLECTOR,
        SPECIAL
    }

    private final UUID id;
    private final UUID ownerUUID;
    private final MachineType type;
    private final Location anchor;
    private final DirectionalHelper.Direction direction;
    private final double multiplier;
    private final boolean disabled;

    public Machine(UUID i, UUID o, MachineType t, Location a, DirectionalHelper.Direction d, double m, boolean di) {
        this.id = i;
        this.ownerUUID = o;
        this.type = t;
        this.anchor = a;
        this.direction = d;
        this.multiplier = m;
        this.disabled = di;
    }

    public UUID getId() { return id; }

    public UUID getOwnerUUID() { return ownerUUID; }

    public MachineType getType() { return type; }

    public Location getAnchor() { return anchor; }

    public DirectionalHelper.Direction getDirection() { return direction; }

    public double getMultiplier() { return multiplier; }

    public boolean isDisabled() { return disabled; }


}
