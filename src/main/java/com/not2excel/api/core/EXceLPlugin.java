package com.not2excel.api.core;

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
    private TimeManager timeManager = TimeManager.newInstance();
    private LevelLogger logger      = new LevelLogger();

    @Override
    public void onLoad()
    {
        logger.setTimeStamped(false);
        logger.setLogType("EXceL");
        timeManager.resetLastTime();
        load();
        logger.log(timeManager.timeDifferenceMillis());
    }

    @Override
    public void onEnable()
    {
        timeManager.resetLastTime();
        enable();
        logger.log(timeManager.timeDifferenceMillis());
    }

    @Override
    public void onDisable()
    {
        timeManager.resetLastTime();
        disable();
        logger.log(timeManager.timeDifferenceMillis());
    }

    public abstract void load();

    public abstract void enable();

    public abstract void disable();
}
