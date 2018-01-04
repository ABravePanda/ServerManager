package me.abravepanda.servermanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.commands.methods.HelpBuilder;
import me.abravepanda.servermanager.commands.methods.InventoryBuilder;
import me.abravepanda.servermanager.configmethods.Config;
import me.abravepanda.servermanager.messages.Messages;
import me.abravepanda.servermanager.updater.Updater;
import net.md_5.bungee.api.ChatColor;

public class CommandServerManager implements CommandExecutor {

	String prefix = Messages.Prefix;
	private Main plugin;

	public CommandServerManager(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("ServerManager")) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("info")) {
					p.sendMessage(prefix + ChatColor.AQUA + "ServerManager was created by:" + ChatColor.GOLD
							+ " A_Brave_Panda");
					p.sendMessage(prefix + ChatColor.AQUA + "ServerManager Spigot page: " + ChatColor.GOLD
							+ "https://www.spigotmc.org/resources/ServerManager.16452/");
					p.sendMessage(prefix + ChatColor.AQUA + "ServerManager was fully recoded on its second birthday!"
							+ ChatColor.GOLD + " (12/31/17)");
				} else if (args[0].equalsIgnoreCase("version")) {
					plugin.checkUpdate();
					p.sendMessage(prefix + ChatColor.DARK_RED + "You Are Running ServerManager Version: "
							+ ChatColor.RED + plugin.getDescription().getVersion());
					if (Main.updateNeeded == true) {

						final Updater updater = new Updater(plugin, 16452, false);
						p.sendMessage(prefix + ChatColor.RED + "You're Running An Outdated Version ("
								+ plugin.getDescription().getVersion() + "), Please Check: " + ChatColor.GOLD
								+ "https://www.spigotmc.org/resources/ServerManager.16452/ " + ChatColor.RED
								+ "for newest version: " + ChatColor.GOLD + updater.getVersion());

					} else {
						p.sendMessage(prefix + ChatColor.GREEN + "No Updates Available!");
					}
				} else if (args[0].equalsIgnoreCase("help")) {
					HelpBuilder.buildHelp(p);
				} else if (args[0].equalsIgnoreCase("join")) {
					if (p.hasPermission(Permissions.fakeJoin)) {
						if (args.length == 1) {
							Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " joined the game");
							for (Player ps : Bukkit.getOnlinePlayers()) {
								if (ps.isOp()) {
									p.sendMessage(prefix + ChatColor.RED + p.getName() + " fake joined.");
								}
							}
						} else {
							Bukkit.broadcastMessage(ChatColor.YELLOW + args[1] + " joined the game");
							for (Player ps : Bukkit.getOnlinePlayers()) {
								if (ps.isOp()) {
									p.sendMessage(prefix + ChatColor.RED + args[1] + " fake joined.");
								}
							}
						}
					} else {
						p.sendMessage(prefix + Messages.NoPermission);
					}
				} else if (args[0].equalsIgnoreCase("leave")) {
					if (p.hasPermission(Permissions.fakeQuit)) {
						if (args.length == 1) {
							Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " left the game");
							for (Player ps : Bukkit.getOnlinePlayers()) {
								if (ps.isOp()) {
									p.sendMessage(prefix + ChatColor.RED + p.getName() + " fake quit.");
								}
							}
						} else {
							Bukkit.broadcastMessage(ChatColor.YELLOW + args[1] + " left the game");
							for (Player ps : Bukkit.getOnlinePlayers()) {
								if (ps.isOp()) {
									p.sendMessage(prefix + ChatColor.RED + args[1] + " fake quit.");
								}
							}
						}
					} else {
						p.sendMessage(prefix + Messages.NoPermission);
					}
				} else if (args[0].equalsIgnoreCase("menu")) {
					if (p.hasPermission(Permissions.useGUI)) {

						Inventory i = InventoryBuilder.buildGUI(prefix + ChatColor.GOLD + "Help Menu", 54);
						ItemStack helpItem = InventoryBuilder.buildHelpItem(Material.NETHER_STAR, "Help Menu",
								"Disable Global Chat", 1, p);

						i.setItem(Config.getConfigInt("Global"), InventoryBuilder.clearGlobal);
						i.setItem(Config.getConfigInt("Help"), helpItem);
						i.setItem(Config.getConfigInt("Self"), InventoryBuilder.clearSelf);
						i.setItem(Config.getConfigInt("Other Player"), InventoryBuilder.clearOther);
						i.setItem(Config.getConfigInt("Enable Chat"), InventoryBuilder.enableChat);
						i.setItem(Config.getConfigInt("Disable Chat"), InventoryBuilder.disableChat);
						i.setItem(Config.getConfigInt("Close Menu"), InventoryBuilder.closeMenu);
						i.setItem(Config.getConfigInt("FakeJoin"), InventoryBuilder.fakeJoin);
						i.setItem(Config.getConfigInt("FakeLeave"), InventoryBuilder.fakeLeave);

						p.openInventory(i);

					} else {
						p.sendMessage(prefix + Messages.NoPermission);
					}
				}
			} else {
				p.sendMessage(prefix + ChatColor.RED + "Incorrect Arguments! Usage: " + ChatColor.DARK_RED
						+ "/ServerManager [help,version,menu,info]");
			}
		}
		return true;
	}

}