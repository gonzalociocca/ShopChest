package de.epiceric.shopchest.fastRunnable;

/**
 * Created by ciocca on 11/12/16.
 */

import org.bukkit.plugin.java.JavaPlugin;

public class Updater
        implements Runnable
{
    private JavaPlugin _plugin;

    public Updater(JavaPlugin plugin)
    {
        _plugin = plugin;
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(_plugin, this, 0L, 50L);
    }

    @Override
    public void run()
    {
        for (UpdateType updateType : UpdateType.values())
        {
            if (updateType.Elapsed())
            {
                _plugin.getServer().getPluginManager().callEvent(new UpdateEvent(updateType));
            }
        }
    }
}