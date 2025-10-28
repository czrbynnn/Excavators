package plugins.excavations.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BalanceManager {

    private static final Map<UUID, Double> balances = new HashMap<>();

    public static double getBalance(UUID playerId) {
        return balances.getOrDefault(playerId, 0.0);
    }

    public static void setBalance(UUID playerId, double amount) {
        balances.put(playerId, Math.max(amount, 0));
    }

    public static void addBalance(UUID playerId, double amount) {
        balances.put(playerId, getBalance(playerId) + amount);
    }

    public static boolean removeBalance(UUID playerId, double amount) {
        double current = getBalance(playerId);
        if (current < amount) return false;
        balances.put(playerId, current - amount);
        return true;
    }

    public static String format(double amount) {
        if (amount >= 1_000_000) return String.format("%.2fM", amount / 1_000_000);
        if (amount >= 1_000) return String.format("%.2fK", amount / 1_000);
        return String.format("%.0f", amount);
    }

}
