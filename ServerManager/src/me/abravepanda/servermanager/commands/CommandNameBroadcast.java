package me.abravepanda.servermanager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.commands.methods.StringBuilderClass;
import me.abravepanda.servermanager.messages.Messages;
import net.md_5.bungee.api.ChatColor;

public class CommandNameBroadcast implements CommandExecutor {

	String prefix = Messages.Prefix;
	private Main plugin;

	public CommandNameBroadcast(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("namebroadcast")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "Players Only!");

			} else {
				if (!p.hasPermission(Permissions.nameBroadcast)) {
					p.sendMessage(prefix + Messages.NoPermission);

				} else if (args.length == 0) {
					p.sendMessage(prefix + Messages.NoMessage);
				} else {
					plugin.getServer()
							.broadcastMessage(prefix + Messages.NamePart.replace("{name}", p.getName()) + Messages.Color
									+ ChatColor.translateAlternateColorCodes('&', StringBuilderClass.message(args)));
				}

			}
		}
		return true;
	}

}