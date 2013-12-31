package com.not2excel.api.region;

import com.not2excel.api.exceptions.CuboidException;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author Richmond Steele
 * @since 12/28/13
 * All rights Reserved
 * Please read included LICENSE file
 */
public class Cuboid
{
    private int    id;
    private String name;
    private World  world;
    private int    minX, minY, minZ, maxX, maxY, maxZ;


    public Cuboid(int id, String name, Location l1, Location l2) throws CuboidException
    {
        if (!l1.getWorld().equals(l2.getWorld()))
        {
            throw new CuboidException("World's do not match.  Failed to create Cuboid.");
        }
        this.id = id;
        this.name = name;
        this.world = l1.getWorld();
        minX = Math.min(l1.getBlockX(), l2.getBlockX());
        minY = Math.min(l1.getBlockY(), l2.getBlockY());
        minZ = Math.min(l1.getBlockZ(), l2.getBlockZ());
        maxX = Math.max(l1.getBlockX(), l2.getBlockX());
        maxY = Math.max(l1.getBlockY(), l2.getBlockY());
        maxZ = Math.max(l1.getBlockZ(), l2.getBlockZ());
    }

    public Cuboid(int id, String name, World world, int minX, int minY, int minZ, int maxX, int maxY, int maxZ)
    {
        Validate.notNull(world);
        this.id = id;
        this.name = name;
        this.world = world;
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.minZ = Math.min(minZ, maxZ);
        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        this.maxZ = Math.max(minZ, maxZ);
    }


}
