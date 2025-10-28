package plugins.excavations.blueprint;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;
import plugins.excavations.helpers.DirectionalHelper;

import java.util.Objects;

public class BlueprintPlacer {

    public static void placeDropper(Location origin, DirectionalHelper.Direction dir) {
        /* Base */
        placeBlock(origin, Material.GOLD_BLOCK);

        /* Platform */
        buildPlatform(3, origin.clone().add(0, 1, 0), Material.SMOOTH_STONE);

        /* Row 2 */
        placeBlock(origin.clone().add(0, 2, 0), Material.SMOOTH_STONE);

        /* Row 3 */
        placeBlock(origin.clone().add(0, 3, 0), Material.SMOOTH_STONE);

        /* Row 4 */
        placeBlock(origin.clone().add(0, 4, 0), Material.SMOOTH_STONE);

        /* Directional */
        switch (dir) {
            case NORTH -> {
                placeStair(origin.clone().add(0, 5, 0), Material.COBBLESTONE_STAIRS, BlockFace.NORTH, false);
                placeBlock(origin.clone().add(0, 5, -1), Material.SMOOTH_STONE);
                placeBlock(origin.clone().add(0, 5, -2), Material.SMOOTH_STONE);
                placeStair(origin.clone().add(0, 5, -3), Material.COBBLESTONE_STAIRS, BlockFace.SOUTH, false);

                placeStair(origin.clone().add(0, 2, -1), Material.COBBLESTONE_STAIRS, BlockFace.SOUTH, false);
                placeStair(origin.clone().add(1, 2, 0), Material.COBBLESTONE_STAIRS, BlockFace.WEST, false);
                placeStair(origin.clone().add(0, 2, 1), Material.COBBLESTONE_STAIRS, BlockFace.NORTH, false);
                placeStair(origin.clone().add(-1, 2, 0), Material.COBBLESTONE_STAIRS, BlockFace.EAST, false);

                placeStair(origin.clone().add(0, 4, -1), Material.COBBLESTONE_STAIRS, BlockFace.SOUTH, true);
                placeBlock(origin.clone().add(0, 4, -3), Material.STONE_BRICK_WALL);
                placeBlock(origin.clone().add(0, 3, -3), Material.CHAIN);
            }
            case SOUTH -> {
                placeStair(origin.clone().add(0, 5, 0), Material.COBBLESTONE_STAIRS, BlockFace.SOUTH, false);
                placeBlock(origin.clone().add(0, 5, 1), Material.SMOOTH_STONE);
                placeBlock(origin.clone().add(0, 5, 2), Material.SMOOTH_STONE);
                placeStair(origin.clone().add(0, 5, 3), Material.COBBLESTONE_STAIRS, BlockFace.NORTH, false);

                placeStair(origin.clone().add(0, 2, 1), Material.COBBLESTONE_STAIRS, BlockFace.NORTH, false);
                placeStair(origin.clone().add(-1, 2, 0), Material.COBBLESTONE_STAIRS, BlockFace.EAST, false);
                placeStair(origin.clone().add(0, 2, -1), Material.COBBLESTONE_STAIRS, BlockFace.SOUTH, false);
                placeStair(origin.clone().add(1, 2, 0), Material.COBBLESTONE_STAIRS, BlockFace.WEST, false);

                placeStair(origin.clone().add(0, 4, 1), Material.COBBLESTONE_STAIRS, BlockFace.NORTH, true);
                placeBlock(origin.clone().add(0, 4, 3), Material.STONE_BRICK_WALL);
                placeBlock(origin.clone().add(0, 3, 3), Material.CHAIN);
            }
            case EAST -> {
                placeStair(origin.clone().add(0, 5, 0), Material.COBBLESTONE_STAIRS, BlockFace.EAST, false);
                placeBlock(origin.clone().add(1, 5, 0), Material.SMOOTH_STONE);
                placeBlock(origin.clone().add(2, 5, 0), Material.SMOOTH_STONE);
                placeStair(origin.clone().add(3, 5, 0), Material.COBBLESTONE_STAIRS, BlockFace.WEST, false);

                placeStair(origin.clone().add(1, 2, 0), Material.COBBLESTONE_STAIRS, BlockFace.WEST, false);
                placeStair(origin.clone().add(0, 2, -1), Material.COBBLESTONE_STAIRS, BlockFace.SOUTH, false);
                placeStair(origin.clone().add(-1, 2, 0), Material.COBBLESTONE_STAIRS, BlockFace.EAST, false);
                placeStair(origin.clone().add(0, 2, 1), Material.COBBLESTONE_STAIRS, BlockFace.NORTH, false);

                placeStair(origin.clone().add(1, 4, 0), Material.COBBLESTONE_STAIRS, BlockFace.WEST, true);
                placeBlock(origin.clone().add(3, 4, 0), Material.STONE_BRICK_WALL);
                placeBlock(origin.clone().add(3, 3, 0), Material.CHAIN);
            }
            case WEST -> {
                placeStair(origin.clone().add(0, 5, 0), Material.COBBLESTONE_STAIRS, BlockFace.WEST, false);
                placeBlock(origin.clone().add(-1, 5, 0), Material.SMOOTH_STONE);
                placeBlock(origin.clone().add(-2, 5, 0), Material.SMOOTH_STONE);
                placeStair(origin.clone().add(-3, 5, 0), Material.COBBLESTONE_STAIRS, BlockFace.EAST, false);

                placeStair(origin.clone().add(-1, 2, 0), Material.COBBLESTONE_STAIRS, BlockFace.EAST, false);
                placeStair(origin.clone().add(0, 2, 1), Material.COBBLESTONE_STAIRS, BlockFace.NORTH, false);
                placeStair(origin.clone().add(1, 2, 0), Material.COBBLESTONE_STAIRS, BlockFace.WEST, false);
                placeStair(origin.clone().add(0, 2, -1), Material.COBBLESTONE_STAIRS, BlockFace.SOUTH, false);

                placeStair(origin.clone().add(-1, 4, 0), Material.COBBLESTONE_STAIRS, BlockFace.EAST, true);
                placeBlock(origin.clone().add(-3, 4, 0), Material.STONE_BRICK_WALL);
                placeBlock(origin.clone().add(-3, 3, 0), Material.CHAIN);
            }
        }

    }

    public static void placeUpgrader(Location origin, DirectionalHelper.Direction dir) {
        placeBlock(origin.clone().add(0, 2, 0), Material.GOLD_BLOCK);
        placeBlock(origin.clone().add(0, 3, 0), Material.WHITE_CONCRETE);
        placeBlock(origin.clone().add(0, 4, 0), Material.WHITE_CONCRETE);
        placeBlock(origin.clone().add(0, 5, 0), Material.WHITE_CONCRETE);
        placeBlock(origin.clone().add(0, 6, 0), Material.WHITE_CONCRETE);

        switch (dir) {
            case NORTH, SOUTH -> {
                placeBlock(origin.clone().add(-1, 5, 0), Material.WHITE_CONCRETE);
                placeBlock(origin.clone().add(1, 5, 0), Material.WHITE_CONCRETE);
            }
            case EAST, WEST -> {
                placeBlock(origin.clone().add(0, 5, -1), Material.WHITE_CONCRETE);
                placeBlock(origin.clone().add(0, 5, 1), Material.WHITE_CONCRETE);
            }
        }
    }

    public static void placeConveyor(Location origin, DirectionalHelper.Direction dir) {
        placeBlock(origin, Material.BLACK_CONCRETE);
    }

    public static void placeCollector(Location origin, DirectionalHelper.Direction dir) {
        placeBlock(origin, Material.RED_CONCRETE);
    }

    public static void buildPlatform(int size, Location center, Material m) {
        int half = size / 2;

        for (int x = -half; x <= half; x++) {
            for (int z = -half; z <= half; z++) {
                Location blockLoc = center.clone().add(x, 0, z);
                placeBlock(blockLoc, m);
            }
        }
    }

    public static void placeBlock(Location loc, Material m) {
        Objects.requireNonNull(loc.getWorld()).getBlockAt(loc).setType(m);
    }

    public static void placeStair(Location loc, Material m, BlockFace facing, boolean flipped) {
        if (!m.name().endsWith("_STAIRS")) {
            throw new IllegalArgumentException("Material must be a type of stairs.");
        }

        World world = loc.getWorld();
        Block block = world.getBlockAt(loc);
        block.setType(m);

        BlockData data = block.getBlockData();
        if (data instanceof Stairs stairs) {
            stairs.setFacing(facing);
            stairs.setHalf(flipped ? Stairs.Half.TOP : Stairs.Half.BOTTOM);
            block.setBlockData(stairs);
        }
    }

}
