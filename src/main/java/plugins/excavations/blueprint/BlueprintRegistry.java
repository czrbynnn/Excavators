package plugins.excavations.blueprint;

import org.bukkit.Location;
import plugins.excavations.helpers.DirectionalHelper;

public class BlueprintRegistry {

    public static void placeBlueprint(String name, Location origin, DirectionalHelper.Direction dir) {
        if (name.equalsIgnoreCase("dropper")) {
            BlueprintPlacer.placeDropper(origin, dir);
        } else if (name.equalsIgnoreCase("conveyor")) {
            BlueprintPlacer.placeConveyor(origin, dir);
        } else if (name.equalsIgnoreCase("collector")) {
            BlueprintPlacer.placeCollector(origin, dir);
        } else if (name.equalsIgnoreCase("upgrader")) {
            BlueprintPlacer.placeUpgrader(origin, dir);
        }
    }

}
