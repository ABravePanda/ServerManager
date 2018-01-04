package me.abravepanda.servermanager.commands.methods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.abravepanda.servermanager.Main;

public class InventoryBuilder {

	static String prefix = Main.PREFIX;

	public static Inventory buildGUI(String name, int size) {
		Inventory inv = Bukkit.createInventory(null, size, name);
		return inv;
	}

	public static ItemStack buildItem(Material material, String name, String description, int amount) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(prefix + ChatColor.YELLOW + "" + ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(" ");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&a&m&l-----------------------------------"));
		lore.add(" ");
		lore.add(" ");
		lore.add(prefix + ChatColor.GOLD + description);
		lore.add(" ");
		lore.add(" ");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&a&m&l-----------------------------------"));

		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack buildHelpItem(Material material, String name, String description, int amount, Player p) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(prefix + ChatColor.YELLOW + "" + ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(" ");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&a&m&l-----------------------------------"));
		lore.add(" ");
		lore.add(" ");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&e&l     [ServerManager Help]"));
		lore.add(" ");
		lore.add(" ");
		lore.add(prefix + HelpBuilder.Helphelp);
		lore.add(prefix + HelpBuilder.HelpVersion);
		lore.add(prefix + HelpBuilder.HelpMenu);
		lore.add(prefix + HelpBuilder.HelpInfo);
		lore.add(prefix + HelpBuilder.HelpJoin);
		lore.add(prefix + HelpBuilder.HelpLeave);
		lore.add(prefix + HelpBuilder.HelpCC);
		lore.add(prefix + HelpBuilder.HelpPCC);
		lore.add(prefix + HelpBuilder.HelpSCC);
		lore.add(prefix + HelpBuilder.HelpOn);
		lore.add(prefix + HelpBuilder.HelpOff);
		lore.add(prefix + HelpBuilder.HelpBC);
		lore.add(prefix + HelpBuilder.HelpNBC);
		lore.add(prefix + HelpBuilder.HelpMute);
		lore.add(prefix + HelpBuilder.HelpUnmute);
		lore.add(prefix + HelpBuilder.HelpMsgPeek);
		lore.add(prefix + HelpBuilder.HelpWarn);
		lore.add(prefix + HelpBuilder.HelpWarns);
		lore.add(prefix + HelpBuilder.HelpDelWarn);
		lore.add(" ");
		lore.add(ChatColor.RED + "       " + ChatColor.BOLD + ChatColor.UNDERLINE + "Key:");
		lore.add("");
		lore.add(ChatColor.AQUA + "          " + ChatColor.BOLD + "Aqua:" + ChatColor.GREEN + " Command.");
		lore.add(ChatColor.GOLD + "          " + ChatColor.BOLD + "Gold:" + ChatColor.GREEN + " Description.");
		lore.add(" ");
		lore.add(" ");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&a&m&l-----------------------------------"));

		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack fakeJoin = InventoryBuilder.buildItem(Material.EMERALD_BLOCK, "Fake Join", "Fake Join", 1);
	public static ItemStack fakeLeave = InventoryBuilder.buildItem(Material.REDSTONE_BLOCK, "Fake Leave", "Fake Leave",
			1);
	public static ItemStack clearGlobal = InventoryBuilder.buildItem(Material.BOOK_AND_QUILL, "Global",
			"Clear Global Chat", 1);
	public static ItemStack clearSelf = InventoryBuilder.buildItem(Material.EMPTY_MAP, "Self", "Clear Your Chat", 1);
	public static ItemStack clearOther = InventoryBuilder.buildItem(Material.NAME_TAG, "Other",
			"Clear Someone Else's Chat", 1);
	public static ItemStack enableChat = InventoryBuilder.buildItem(Material.SLIME_BALL, "Enable Chat",
			"Enable Global Chat", 1);
	public static ItemStack disableChat = InventoryBuilder.buildItem(Material.FIREBALL, "Disable Chat",
			"Disable Global Chat", 1);
	public static ItemStack closeMenu = InventoryBuilder.buildItem(Material.BARRIER, "Close Menu", "Close The Menu", 1);

}
