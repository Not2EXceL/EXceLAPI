package com.not2excel.api.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Richmond Steele
 * @since 12/26/13
 * All rights Reserved
 * Please read included LICENSE file
 */
public class MenuClickEvent
{
    private final Player    player;
    private final int       slot;
    private final ItemStack item;
    private       boolean   close;

    public MenuClickEvent(Player player, int slot, ItemStack item)
    {
        this.player = player;
        this.slot = slot;
        this.item = item;
        this.close = true;
    }

    public Player getPlayer()
    {
        return player;
    }

    public int getSlot()
    {
        return slot;
    }

    public ItemStack getItem()
    {
        return item;
    }

    public boolean isClose()
    {
        return close;
    }

    public void setClose(boolean close)
    {
        this.close = close;
    }
}
