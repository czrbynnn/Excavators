package plugins.excavations.listeners.bukkit;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import plugins.excavations.Excavations;
import plugins.excavations.blueprint.BlueprintRegistry;
import plugins.excavations.data.models.*;
import plugins.excavations.helpers.DirectionalHelper;
import plugins.excavations.managers.CollectorManager;
import plugins.excavations.managers.ConveyorManager;
import plugins.excavations.managers.MachineManager;
import plugins.excavations.managers.TycoonManager;
import plugins.excavations.util.Colorize;

import java.util.UUID;

public class BlockPlaceListener implements Listener {

    private static final NamespacedKey MACHINE_TYPE_KEY = new NamespacedKey(Excavations.getInstance(), "machine_type");
    private final TycoonManager tycoonManager;

    public BlockPlaceListener(TycoonManager manager) {
        this.tycoonManager = manager;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        ItemStack item = e.getItemInHand();
        ItemMeta meta = item.getItemMeta();

        if (meta != null && meta.getPersistentDataContainer().has(MACHINE_TYPE_KEY, PersistentDataType.STRING)) {
            e.setCancelled(true);

            Player p = e.getPlayer();
            String typeName = meta.getPersistentDataContainer().get(MACHINE_TYPE_KEY, PersistentDataType.STRING);
            Machine.MachineType type = Machine.MachineType.valueOf(typeName);

            Tycoon tycoon = tycoonManager.getTycoon(p.getUniqueId());
            if (tycoon == null) {
                tycoon = tycoonManager.createTycoon(p, p.getLocation());
                p.sendMessage(Colorize.colorize("&7Created a new tycoon for you!"));
            }

            DirectionalHelper.Direction dir = DirectionalHelper.getDirection(p);

            Location loc = e.getBlockAgainst().getLocation();

            Machine machine = new Machine(UUID.randomUUID(), p.getUniqueId(), type, loc, dir, 1.0, false);
            tycoonManager.addMachine(p.getUniqueId(), machine);

            if (type == Machine.MachineType.DROPPER) {
                BlueprintRegistry.placeBlueprint("dropper", loc, dir);
                tycoon.setDropper(new Dropper(machine.getId(), machine.getOwnerUUID(), machine.getAnchor(), machine.getDirection(), 1));
            }
            if (type == Machine.MachineType.CONVEYOR) {
                BlueprintRegistry.placeBlueprint("conveyor", loc, dir);
                Conveyor conveyor = new Conveyor(machine.getId(), machine.getOwnerUUID(), machine.getAnchor(), machine.getDirection(), 1);
                tycoon.addMachine(conveyor);
                ConveyorManager.register(conveyor);
            }
            if (type == Machine.MachineType.COLLECTOR) {
                BlueprintRegistry.placeBlueprint("collector", loc, dir);
                Collector collector = new Collector(machine.getId(), machine.getOwnerUUID(), machine.getAnchor(), machine.getDirection());
                tycoon.addMachine(collector);
                CollectorManager.registerCollector(collector);
            }
            if (type == Machine.MachineType.UPGRADER) {
                BlueprintRegistry.placeBlueprint("upgrader", loc, dir);
                Upgrader upgrader = new Upgrader(machine.getId(), machine.getOwnerUUID(), machine.getAnchor(), machine.getDirection(), 1, 1.5);
                tycoon.addMachine(upgrader);
                ConveyorManager.register(upgrader);
            }

            p.sendMessage(Colorize.colorize("&aPlaced a &e" + type + "&a machine!"));


            e.getBlockAgainst().setType(item.getType());
        }
    }

}
