package plugins.excavations.data.models;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;

import java.util.List;
import java.util.UUID;

public class OreItem {

    private final UUID id;
    private final UUID tycoonID;
    private double value;
    private Location loc;
    private List<UUID> appliedMachines;
    private final int lifetime;

    private Item itemEntity;
    private ArmorStand hologram;

    public OreItem(UUID i, UUID t, double v, Location l) {
        this.id = i;
        this.tycoonID = t;
        this.value = v;
        this.loc = l;
        this.lifetime = 60;
    }

    public UUID getId() {
        return id;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public UUID getTycoonID() {
        return tycoonID;
    }

    public double getValue() {
        return value;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public List<UUID> getAppliedMachines() {
        return appliedMachines;
    }

    public int getLifetime() {
        return lifetime;
    }

    public Item getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(Item itemEntity) {
        this.itemEntity = itemEntity;
    }

    public ArmorStand getHologram() {
        return hologram;
    }

    public void setHologram(ArmorStand hologram) {
        this.hologram = hologram;
    }

}
