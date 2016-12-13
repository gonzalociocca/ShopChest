package de.epiceric.shopchest.fastRunnable;

/**
 * Created by ciocca on 11/12/16.
 */
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    private final UpdateType _type;

    public UpdateEvent(UpdateType example)
    {
        _type = example;
    }

    public UpdateType getType()
    {
        return _type;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}