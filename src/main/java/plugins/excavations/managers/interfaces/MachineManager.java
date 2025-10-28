package plugins.excavations.managers.interfaces;

import plugins.excavations.data.models.Machine;

import java.util.*;

/**
 * Base interface for all machine managers
 */
public interface MachineManager<T extends Machine> {

    /**
     * Registers a new machine instance to this manager
     * @param machine The machine to register.
     */
    void registerMachine(T machine);

    /**
     * Unregisters a machine by its UUID
     * @param id The unique id of the machine
     */
    void unregisterMachine(UUID id);

    /**
     * Retrieves a machine by ID
     *
     * @param id The machines id
     * @return The machine or null if not found
     */
    T getMachine(UUID id);

    /**
     * @return A collection of all registered machines
     */
    Collection<T> getAllMachines();

    /**
     * Called when the plugin starts to begin processing or ticking logic
     */
    void startAll();

    /**
     * Stops or cleans up all machines
     */
    void stopAll();

}
