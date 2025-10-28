package plugins.excavations.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import plugins.excavations.data.models.Machine;
import plugins.excavations.data.models.Tycoon;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TycoonManager {

    private final Map<UUID, Tycoon> activeTycoons = new HashMap<>();

    public Tycoon createTycoon(Player player, Location baseOrigin) {
        Tycoon tycoon = new Tycoon(player.getUniqueId(), baseOrigin, 0);
        activeTycoons.put(player.getUniqueId(), tycoon);
        return tycoon;
    }

    public Tycoon getTycoon(UUID playerUUID) {
        return activeTycoons.get(playerUUID);
    }

    public boolean hasTycoon(UUID playerUUID) {
        return activeTycoons.containsKey(playerUUID);
    }

    public void addMachine(UUID ownerUUID, Machine machine) {
        Tycoon tycoon = activeTycoons.get(ownerUUID);
        if (tycoon != null) {
            tycoon.addMachine(machine);
        } else {
            Bukkit.getLogger().warning("Tried to add machine to non-existent tycoon for " + ownerUUID);
        }
    }

    public void removeMachine(UUID ownerUUID, Machine machine) {
        Tycoon tycoon = activeTycoons.get(ownerUUID);
        if (tycoon != null) {
            tycoon.removeMachine(machine);
        }
    }

    public Tycoon getTycoonByLocation(Location loc) {
        for (Tycoon tycoon : activeTycoons.values()) {
            if (tycoon.getBaseOrigin().getWorld().equals(loc.getWorld())) {
                double dist = tycoon.getBaseOrigin().distance(loc);
                if (dist < 50) {
                    return tycoon;
                }
            }
        }
        return null;
    }

    public void unloadTycoon(UUID playerUUID) {
        Tycoon tycoon = activeTycoons.remove(playerUUID);
        if (tycoon != null) {
            //save to file later
        }
    }

    public Collection<Tycoon> getAllTycoons() {
        return activeTycoons.values();
    }

}
