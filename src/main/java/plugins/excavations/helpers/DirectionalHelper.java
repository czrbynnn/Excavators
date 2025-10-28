package plugins.excavations.helpers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class DirectionalHelper {

    private static Map<Direction, Integer> values = new HashMap<>();

    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    static {
        values.put(Direction.SOUTH, 0);
        values.put(Direction.WEST, 90);
        values.put(Direction.NORTH, 180);
        values.put(Direction.EAST, -90);
    }


    /**
     * Get a direction from a player's facing yaw
     */
    public static Direction getDirection(Player p) {
        return getDirectionFromYaw(p.getLocation().getYaw());
    }

    /**
     * Convert yaw (-180 to 180) to cardinal direction
     */
    public static Direction getDirectionFromYaw(float yaw) {
        if (yaw >= -45 && yaw < 45) return Direction.SOUTH;
        if (yaw >= 45 && yaw < 135) return Direction.WEST;
        if (yaw >= -135 && yaw < -45) return Direction.EAST;
        return Direction.NORTH;
    }

    /**
     * Rotate clockwise (90deg)
     */
    public Direction rotateClockwise(Direction dir) {
        int yaw = values.get(dir) + 90;
        if (yaw > 180) yaw -= 360;
        return getDirectionFromYaw(yaw);
    }

    /**
     * Rotate anticlockwise (90deg)
     */
    public Direction rotateAntiClockwise(Direction dir) {
        int yaw = values.get(dir) - 90;
        if (yaw < -180) yaw += 360;
        return getDirectionFromYaw(yaw);
    }

}
