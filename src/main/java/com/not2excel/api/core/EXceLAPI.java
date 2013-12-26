package com.not2excel.api.core;

/**
 * @author Richmond Steele
 * @since 12/24/13
 * All rights Reserved
 * Please read included LICENSE file
 */
public class EXceLAPI extends EXceLPlugin
{
    @Override
    public void load(){}

    @Override
    public void enable()
    {
        logger.log("This API is designed to be depended on by plugins.  Not standalone.");
    }

    @Override
    public void disable(){}
}
