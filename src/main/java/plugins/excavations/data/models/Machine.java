package plugins.excavations.data.models;

import org.bukkit.Location;
import plugins.excavations.helpers.DirectionalHelper;

import java.util.UUID;

public abstract class Machine {

    public enum MachineType {
        DROPPER,
        CONVEYOR,
        UPGRADER,
        COLLECTOR
    }

    private final UUID id;
    private final UUID ownerUUID;
    private final MachineType type;
    private final Location anchor;
    private final DirectionalHelper.Direction direction;
    private final double speed;
    private boolean active;

    public Machine(UUID id, UUID ownerUUID, MachineType type, Location anchor,
                   DirectionalHelper.Direction direction, double speed, boolean active) {
        this.id = id;
        this.ownerUUID = ownerUUID;
        this.type = type;
        this.anchor = anchor;
        this.direction = direction;
        this.speed = speed;
        this.active = active;
    }

    public UUID getId() { return id; }

    public UUID getOwnerUUID() { return ownerUUID; }

    public MachineType getType() { return type; }

    public Location getAnchor() { return anchor; }

    public DirectionalHelper.Direction getDirection() { return direction; }

    public double getSpeed() { return speed; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    /** Override in subclasses */
    public abstract void tick();
}
