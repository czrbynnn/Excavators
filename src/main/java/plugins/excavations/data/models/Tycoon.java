package plugins.excavations.data.models;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tycoon {

    private final UUID ownerUUID;
    private final UUID tycoonUUID;
    private final Location baseOrigin;
    private final double balance;
    private List<Machine> machines;
    private Dropper dropper;

    public Tycoon(UUID owner, Location base, double bal) {
        this.ownerUUID = owner;
        this.tycoonUUID = UUID.randomUUID();
        this.baseOrigin = base;
        this.balance = bal;
        this.machines = new ArrayList<>();
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public UUID getTycoonUUID() {
        return tycoonUUID;
    }

    public Location getBaseOrigin() {
        return baseOrigin;
    }

    public double getBalance() {
        return balance;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void addMachine(Machine machine) {
        machines.add(machine);
    }

    public void removeMachine(Machine machine) {
        machines.remove(machine);
    }

    public Dropper getDropper() { return dropper; }
    public void setDropper(Dropper dropper) { this.dropper = dropper; }

}
