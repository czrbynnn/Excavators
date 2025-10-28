package plugins.excavations.data.models;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import plugins.excavations.Excavations;
import plugins.excavations.helpers.DirectionalHelper;
import plugins.excavations.managers.BalanceManager;
import plugins.excavations.managers.OreManager;

import java.util.Collection;
import java.util.UUID;

public class Collector extends Machine {

    private BukkitRunnable task;
    private double sellMultiplier = 1.0;

    public Collector(UUID id, UUID ownerUUID, Location anchor, DirectionalHelper.Direction dir) {
        super(id, ownerUUID, MachineType.COLLECTOR, anchor, dir, 1.0, false);
    }

    public void setSellMultiplier(double newMulti) {
        this.sellMultiplier = newMulti;
    }

    public void startTask() {
        stopTask();

        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                if (isDisabled()) return;
                collectOreItems();
            }
        };

        this.task.runTaskTimer(Excavations.getInstance(), 20L, 20L);
    }

    private void collectOreItems() {
        World world = getAnchor().getWorld();
        if (world == null) return;

        Location center = getAnchor().clone().add(0.5, 1, 0.5);

        Collection<OreItem> ores = OreManager.getActiveOres().values().stream()
                .filter(ore -> ore.getItemEntity() != null
                && ore.getItemEntity().isValid()
                && ore.getLoc().distanceSquared(center) <= 0.6 * 0.6)
                .toList();

        if (ores.isEmpty()) { return; }

        for (OreItem ore : ores) {
            double totalValue = ore.getValue() * sellMultiplier;
            BalanceManager.addBalance(getOwnerUUID(), totalValue);
            OreManager.removeOre(ore);
            System.out.println("Collector sold OreItem worth " + totalValue);
        }
    }

    public void stopTask() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

}
