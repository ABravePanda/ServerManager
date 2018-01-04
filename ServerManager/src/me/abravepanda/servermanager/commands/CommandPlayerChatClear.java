package me.abravepanda.servermanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.messages.Messages;

public class CommandPlayerChatClear implements CommandExecutor {

	String prefix = Messages.Prefix;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("pcc")) {
			if (sender.hasPermission(Permissions.playerChatClear)) {
				if (args.length == 0) {
					p.sendMessage(prefix + ChatColor.RED + "Incorrect Arguments! Usage: " + ChatColor.DARK_RED
							+ "/pcc {name}");
				} else {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target == null) {
						p.sendMessage(prefix + ChatColor.RED + args[0] + " Is Not Online!");
					} else {
						p.sendMessage(Messages.PlayerToSender.replace("{name}", p.getName()).replace("{target}",
								target.getName()));
						for (int i = 0; i <= 250; i++) {
							target.sendMessage("");
						}
						target.sendMessage(prefix + Messages.PlayerToTarget.replace("{name}", p.getName())
								.replace("{target}", target.getName()));
					}
				}
			} else {
				p.sendMessage(prefix + Messages.NoPermission);
			}
		}
		return true;
	}
}
