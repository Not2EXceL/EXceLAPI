package com.not2excel.api.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Richmond Steele
 * @since 12/26/13 All rights Reserved Please read included LICENSE file
 */
public class MenuManager implements Listener
{
	private Plugin plugin;
	private Map<String, IconMenu> iconMenus;
	private static MenuManager instance;

	public MenuManager(Plugin plugin)
	{
		this.plugin = plugin;
		this.iconMenus = new HashMap<String, IconMenu>();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		instance = this;
	}

	public static MenuManager getInstance()
	{
		if(instance == null)
		{
			return null;
		}
		return instance;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if (!iconMenus.containsKey(event.getInventory().getName()))
		{
			return;
		}
		event.setCancelled(true);
		int slot = event.getRawSlot();
		if (slot >= 0 && slot < event.getInventory().getSize())
		{
			final Player player = (Player) event.getWhoClicked();
			ItemStack itemStack = event.getInventory().getItem(slot);
			MenuClickEvent menuClickEvent = new MenuClickEvent(player, slot,
					itemStack);
			IconMenu iconMenu = iconMenus.get(event.getInventory().getName());
			iconMenu.onMenuClick(menuClickEvent);
			if (menuClickEvent.isClose())
			{
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
						new Runnable()
						{
							public void run()
							{
								player.closeInventory();
							}
						}, 1);
			}
		}
	}

	public void addIconMenu(IconMenu iconMenu)
	{
		iconMenus.put(iconMenu.getName(), iconMenu);
	}

	public void removeIconMenu(String name)
	{
		if (iconMenus.containsKey(name))
		{
			iconMenus.remove(name);
		}
	}

	public void destroy()
	{
		HandlerList.unregisterAll(this);
	}
}
