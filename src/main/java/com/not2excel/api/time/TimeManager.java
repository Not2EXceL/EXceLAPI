package com.not2excel.api.time;

import java.util.concurrent.TimeUnit;

/**
 * Simple time-management class that utilizes TimeUnit to keep things as concurrent as possible
 * Also TimeUnit helps with conversions quite easily
 *
 * @author Richmond Steele
 * @since 12/25/13
 * All rights Reserved
 * Please read included LICENSE file
 */
public class TimeManager
{
    /**
     * Last time held by this object, useful for use by one object
     * For several objects its cheaper to use one instance and manage previous time separately
     */
    private volatile long lastTime;

    /**
     * privatized to force use of <code>newInstance()</code>
     * simply to make code look cleaner
     */
    private TimeManager(){}

    /**
     * Creates a new instance of TimeManager
     * @return timeManager
     */
    public static TimeManager newInstance()
    {
        return new TimeManager();
    }

    /**
     * Resets the lastTime to the current <code>System.nanoTime()</code>
     * Used for some methods, but should only be for when this class is used by a single object
     */
    public synchronized void resetLastTime()
    {
        this.lastTime = System.nanoTime();
    }

    public synchronized boolean sleepMillis(long time)
    {
        return sleep(time, TimeUnit.MILLISECONDS);
    }
    
    public synchronized boolean sleepMillis(long time, long previousTime)
    {
        return sleep(time, previousTime, TimeUnit.MILLISECONDS);
    }

    public synchronized boolean sleepTicks(int ticks)
    {
        return sleep(convertTicksToNano(ticks), TimeUnit.NANOSECONDS);
    }

    public synchronized boolean sleepTicks(int ticks, long previousTime)
    {
        return sleep(convertTicksToNano(ticks), previousTime, TimeUnit.NANOSECONDS);
    }

    public synchronized boolean sleep(long time,  TimeUnit timeUnit)
    {
        return timeUnit.convert(System.nanoTime() - lastTime, TimeUnit.NANOSECONDS) >= time;
    }

    public synchronized boolean sleep(long time, long previousTime,  TimeUnit timeUnit)
    {
        return timeUnit.convert(System.nanoTime() - previousTime, TimeUnit.NANOSECONDS) >= time;
    }

    public synchronized long timeDifferenceMillis()
    {
        return timeDifference(TimeUnit.MILLISECONDS);
    }

    public synchronized long timeDifferenceMillis(long previousTime)
    {
        return timeDifference(previousTime, TimeUnit.MILLISECONDS);
    }

    public synchronized long timeDifference(TimeUnit timeUnit)
    {
        return timeUnit.convert(System.nanoTime() - lastTime, TimeUnit.NANOSECONDS);
    }

    public synchronized long timeDifference(long previousTime, TimeUnit timeUnit)
    {
        return timeUnit.convert(System.nanoTime() - previousTime, TimeUnit.NANOSECONDS);
    }

    private long convertTicksToNano(int ticks)
    {
        return ticks * 20 * 1000000000;
    }
}
