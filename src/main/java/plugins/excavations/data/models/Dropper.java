package plugins.excavations.data.models;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import plugins.excavations.Excavations;
import plugins.excavations.helpers.DirectionalHelper;
import plugins.excavations.managers.OreManager;

import java.util.UUID;

public class Dropper extends Machine {

    private int tier;
    private double baseValue;
    private int speedTicks;
    private BukkitRunnable task;

    public Dropper(UUID id, UUID owner, Location anchor, DirectionalHelper.Direction dir, int tier) {
        super(id, owner, MachineType.DROPPER, anchor, dir, 1.0, false);
        this.tier = tier;
        this.baseValue = getBaseValueForTier(tier);
        this.speedTicks = getTickDelayForTier(tier);
        startTask();
    }

    private double getBaseValueForTier(int tier) {
        return 1.0 + (tier - 1) * 0.5;
    }

    private int getTickDelayForTier(int tier) {
        return Math.max(20, 100 - (tier * 10));
    }

    public void startTask() {
        stopTask();

        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                if (isDisabled()) return;
                OreManager.spawnOre(Dropper.this);
            }
        };

        this.task.runTaskTimer(Excavations.getInstance(), 0L, speedTicks);
    }

    public void stopTask() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    public void upgradeTier() {
        this.tier++;
        this.baseValue = getBaseValueForTier(tier);
        this.speedTicks = getTickDelayForTier(tier);
        startTask();
    }

    public int getTier() {
        return tier;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public int getSpeedTicks() {
        return speedTicks;
    }
}
