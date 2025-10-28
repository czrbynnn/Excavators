package plugins.excavations.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import plugins.excavations.data.models.Machine;

import java.util.UUID;

public class MachinePlacedEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final UUID playerId;
    private final Machine machinePlaced;
    private boolean cancelled;

    public MachinePlacedEvent(UUID p, Machine m) {
        this.playerId = p;
        this.machinePlaced = m;
        this.cancelled = false;
    }

    public UUID getPlayerId() { return playerId; }
    public Machine getMachine() { return machinePlaced; }
    public boolean isCancelled() { return cancelled; }

    public void setCancelled(boolean cancel) { this.cancelled = cancel; }

    @Override
    public HandlerList getHandlers() { return HANDLERS; }

    public static HandlerList getHandlerList() { return HANDLERS; }
}
