package com.not2excel.api.core;

import com.not2excel.api.command.CommandManager;
import com.not2excel.api.logging.LevelLogger;
import com.not2excel.api.time.TimeManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Richmond Steele
 * @since 12/24/13
 * All rights Reserved
 * Please read included LICENSE file
 */
public abstract class EXceLPlugin extends JavaPlugin
{
    protected LevelLogger logger      = new LevelLogger();
    private   TimeManager timeManager = TimeManager.newInstance();

    @Override
    public void onLoad()
    {
        logger.setTimeStamped(false);
        logger.setLogType("EXceL");
        timeManager.resetLastTime();
        load();
        logger.log("Loaded in " + timeManager.timeDifferenceMillis() + "ms");
    }

    @Override
    public void onEnable()
    {
        timeManager.resetLastTime();
        enable();
        CommandManager commandManager = new CommandManager(this);
        commandManager.registerCommands();
        logger.log("Enabled in " + timeManager.timeDifferenceMillis() + "ms");
    }

    @Override
    public void onDisable()
    {
        timeManager.resetLastTime();
        disable();
        logger.log("Disabled in " + timeManager.timeDifferenceMillis() + "ms");
    }

    public abstract void load();

    public abstract void enable();

    public abstract void disable();
}
