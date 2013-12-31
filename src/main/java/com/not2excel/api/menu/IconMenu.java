package com.not2excel.api.menu;

import com.not2excel.api.logging.LevelLogger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * @author Richmond Steele
 * @since 12/26/13 All rights Reserved Please read included LICENSE file
 */
public abstract class IconMenu implements MenuClickListener
{
	private String name;
	private int size;
	private ItemStack[] iconItems;
	private LevelLogger logger;

	public IconMenu(String name, int size)
	{
		this.name = name;
		if (size % 9 != 0)
		{
			size = (int) (Math.floor(size / 9) * 9);
		}
		this.size = size;
		this.iconItems = new ItemStack[size];
		this.logger = new LevelLogger();
		this.logger.setLogType("IconMenu");
		this.logger.setTimeStamped(false);
		if (MenuManager.getInstance() == null)
		{
			this.logger
					.log("MenuManager is null. Cannot register this IconMenu.");
		}
		else
		{
			MenuManager.getInstance().addIconMenu(this);
		}
	}

	public IconMenu setIcon(int slot, Material material, int amount,
			String name, String... lore)
	{
		if (slot < size)
		{
			iconItems[slot] = setItemAndLore(new ItemStack(material, amount),
					name, lore);
			return this;
		}
		logger.log("Slot is larger than IconMenu size.  Failed to setIcon.");
		return this;
	}

	public IconMenu setIcon(int slot, ItemStack item, String name,
			String... lore)
	{
		if (slot < size)
		{
			iconItems[slot] = setItemAndLore(item, name, lore);
			return this;
		}
		logger.log("Slot is larger than IconMenu size.  Failed to setIcon.");
		return this;
	}

	public void openInventory(Player player)
	{
		Inventory inventory = Bukkit.createInventory(player, size, name);
		for (int i = 0; i < iconItems.length; i++)
		{
			if (iconItems[i] != null)
			{
				inventory.setItem(i, iconItems[i]);
			}
		}
		player.openInventory(inventory);
	}

	public ItemStack setItemAndLore(ItemStack item, String name, String[] lore)
	{
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		itemMeta.setLore(Arrays.asList(lore));
		item.setItemMeta(itemMeta);
		return item;
	}

	public void editLore(int slot, String[] lore)
	{
		if (slot < size)
		{
			iconItems[slot] = setItemAndLore(iconItems[slot], iconItems[slot]
					.getItemMeta().getDisplayName(), lore);
			return;
		}
		logger.log("Slot is larger than IconMenu size.  Failed to setIcon.");
		return;
	}

	public String[] getLore(int slot)
	{
		if (slot < size)
		{
			return iconItems[slot]
					.getItemMeta()
					.getLore()
					.toArray(
							new String[iconItems[slot].getItemMeta().getLore()
									.size()]);
		}
		logger.log("Slot is larger than IconMenu size.  Failed to setIcon.");
		return new String[0];
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		if (size % 9 != 0)
		{
			size = (int) (Math.floor(size / 9) * 9);
		}
		this.size = size;
		ItemStack[] tempItems = iconItems;
		clearItems();
		for (int i = 0; i < tempItems.length && i < size; ++i)
		{
			iconItems[i] = tempItems[i];
		}
	}

	public void clearItems()
	{
		iconItems = new ItemStack[size];
	}
}
