package me.abravepanda.servermanager.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.messages.Messages;
import net.md_5.bungee.api.ChatColor;

public class CommandMessagePeek implements CommandExecutor {

	String prefix = Messages.Prefix;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		File playerFile = Main.instance.getPlayerFile(p.getUniqueId());
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

		if (cmd.getName().equalsIgnoreCase("msgpeek")) {
			if (p.hasPermission(Permissions.messagePeek)) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("on") | args[0].equalsIgnoreCase("off")) {
						if (args[0].equalsIgnoreCase("on")) {

							playerData.set("MessagePeek.Enabled", "True");
							try {
								playerData.save(playerFile);

							} catch (IOException e1) {
								e1.printStackTrace();
							}
							sender.sendMessage(prefix + ChatColor.GOLD + "MessagePeek: " + ChatColor.GREEN + "Enabled");
						} else if (args[0].equalsIgnoreCase("off")) {

							playerData.set("MessagePeek.Enabled", "False");
							try {
								playerData.save(playerFile);

							} catch (IOException e1) {
								e1.printStackTrace();
							}
							sender.sendMessage(prefix + ChatColor.GOLD + "MessagePeek: " + ChatColor.RED + "Disabled");
						} else {
							sender.sendMessage(prefix + ChatColor.RED + "Usage: /msgpeek <on|off>");
						}
					} else {
						sender.sendMessage(prefix + ChatColor.RED + "Usage: /msgpeek <on|off>");
					}
				} else {
					String enabled = playerData.getString("MessagePeek.Enabled");
					String cleanEnabled;
					if (enabled.equalsIgnoreCase("True")) {
						cleanEnabled = ChatColor.GREEN + "Enabled";
					} else {
						cleanEnabled = ChatColor.RED + "Disabled";
					}
					p.sendMessage(
							prefix + ChatColor.GOLD + "You have Message Peek: " + ChatColor.YELLOW + cleanEnabled);
				}
			} else {
				p.sendMessage(prefix + Messages.NoPermission);
			}
		}
		return true;
	}

}
