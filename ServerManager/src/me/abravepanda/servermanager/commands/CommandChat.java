package me.abravepanda.servermanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.messages.Messages;

public class CommandChat implements CommandExecutor {

	String prefix = Messages.Prefix;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (cmd.getName().equalsIgnoreCase("chat")) {
				if (args.length == 0) {
					p.sendMessage(prefix + ChatColor.RED + "Incorrect Arguments! Usage: " + ChatColor.DARK_RED
							+ "/chat [on,off]");
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("on")) {
						if (p.hasPermission(Permissions.chatOn)) {
							ps.sendMessage(prefix + Messages.ChatEnabled.replace("{name}", p.getName()));
							Main.chatEnabled = true;
						} else {
							p.sendMessage(Messages.NoPermission);
						}

					} else if (args[0].equalsIgnoreCase("off")) {
						if (p.hasPermission(Permissions.chatOff)) {
							ps.sendMessage(prefix + Messages.ChatDisabled.replace("{name}", p.getName()));
							Main.chatEnabled = false;
						} else {
							p.sendMessage(Messages.NoPermission);
						}

					} else {
						p.sendMessage(prefix + ChatColor.RED + "Incorrect Arguments! Usage: " + ChatColor.DARK_RED
								+ "/chat [on,off]");
					}
				}
			}
		}
		return true;
	}

}
