package plugins.excavations.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugins.excavations.managers.BalanceManager;
import plugins.excavations.util.Colorize;

public class BalanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            return false;
        }

        sender.sendMessage(Colorize.colorize("&7Balance: &a$" + BalanceManager.getBalance(p.getUniqueId())));

        return true;
    }
}
