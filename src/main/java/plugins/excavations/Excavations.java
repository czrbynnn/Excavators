package plugins.excavations;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import plugins.excavations.commands.BalanceCommand;
import plugins.excavations.commands.GiveMachineCommand;
import plugins.excavations.listeners.bukkit.BlockPlaceListener;
import plugins.excavations.listeners.bukkit.JoinListener;
import plugins.excavations.managers.CollectorManager;
import plugins.excavations.managers.ConveyorManager;
import plugins.excavations.managers.TycoonManager;

public final class Excavations extends JavaPlugin {

    private static Excavations instance;

    @Override
    public void onEnable() {
        instance = this;
        TycoonManager tycoonManager = new TycoonManager();
        ConveyorManager.startGlobalTask();
        CollectorManager.startAll();

        getServer().getPluginManager().registerEvents(new BlockPlaceListener(tycoonManager), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);

        getCommand("givemachine").setExecutor(new GiveMachineCommand());
        getCommand("balance").setExecutor(new BalanceCommand());
    }

    @Override
    public void onDisable() {
        CollectorManager.stopAll();
    }

    public static Excavations getInstance() {
        return instance;
    }


}
