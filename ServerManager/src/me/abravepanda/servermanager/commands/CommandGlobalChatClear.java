package me.abravepanda.servermanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.messages.Messages;

public class CommandGlobalChatClear implements CommandExecutor {

	String prefix = Messages.Prefix;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("cc")) {

			if (sender.hasPermission(Permissions.globalChatClear)) {

				for (Player ps : Bukkit.getOnlinePlayers()) {

					for (int i = 0; i <= 250; i++) {
						ps.sendMessage("");
					}
					ps.sendMessage(prefix + Messages.GlobalToChat.replace("{name}", p.getName()));
				}
				p.sendMessage(prefix + Messages.GlobalToSender.replace("{name}", p.getName()));
			} else {

				p.sendMessage(prefix + Messages.NoPermission);
			}
			return true;

		}

		return true;
	}

}
