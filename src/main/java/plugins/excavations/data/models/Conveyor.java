package plugins.excavations.data.models;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import plugins.excavations.helpers.DirectionalHelper;
import plugins.excavations.managers.OreManager;

import java.util.UUID;

public abstract class Conveyor extends Machine {

    private int tier;

    public Conveyor(UUID id, UUID ownerUUID, Location anchor,
                    DirectionalHelper.Direction direction, int tier) {
        super(id, ownerUUID, MachineType.CONVEYOR, anchor, direction, 1.0, true);
        this.tier = tier;
    }

    @Override
    public void tick() {
        moveItems();
    }

    protected abstract void moveItems();

    public int getTier() { return tier; }

    protected void updateOrePosition(Item item, Location newLoc) {
        OreManager.getActiveOres().values().stream()
                .filter(ore -> ore.getItemEntity() != null && ore.getItemEntity().equals(item))
                .findFirst()
                .ifPresent(ore -> ore.setLoc(newLoc.clone()));
    }

}
