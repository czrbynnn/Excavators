package plugins.excavations.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import plugins.excavations.data.models.Machine;
import plugins.excavations.factories.MachineItemFactory;

public class GiveMachineCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        if (!(sender instanceof Player p)) {
            return false;
        }

        if (command.getName().equalsIgnoreCase("givemachine")) {
            if (args.length == 1) {
                Machine.MachineType type = Machine.MachineType.valueOf(args[0].toUpperCase());
                ItemStack item = MachineItemFactory.createMachineItem(type);
                p.getInventory().addItem(item);
            }
        }

        return true;
    }
}
