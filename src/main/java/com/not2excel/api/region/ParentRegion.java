package com.not2excel.api.region;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Richmond Steele
 * @since 12/28/13
 * All rights Reserved
 * Please read included LICENSE file
 */
public class ParentRegion
{
    protected final Map<String, Region> childRegions = new ConcurrentHashMap<String, Region>();

    public void addChild(String s, Region child)
    {
        synchronized (childRegions)
        {
            childRegions.put(s.toLowerCase(), child);
        }
    }

    public void removeChild(String s)
    {
        if (hasChild(s))
        {
            synchronized (childRegions)
            {
                childRegions.remove(s.toLowerCase());
            }
        }
    }

    public boolean hasChild(String s)
    {
        synchronized (childRegions)
        {
            return childRegions.containsKey(s.toLowerCase());

        }
    }

    public Region getChild(String s)
    {
        synchronized (childRegions)
        {
            return childRegions.get(s.toLowerCase());
        }
    }

    public Map<String, Region> getChildRegions()
    {
        return childRegions;
    }
}
