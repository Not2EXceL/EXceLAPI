package com.not2excel.api.region;

/**
 * @author Richmond Steele
 * @since 12/28/13
 * All rights Reserved
 * Please read included LICENSE file
 */
public class Region extends ParentRegion
{
    private final String name;
    private Cuboid cuboid;

    public Region(String name, int pos1, int pos2)
    {
        this.name = name;

    }
}
