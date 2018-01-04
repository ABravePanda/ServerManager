package me.abravepanda.servermanager.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.messages.Messages;

public class CommandUnmute implements CommandExecutor {

	String prefix = Messages.Prefix;
	private Main plugin;

	public CommandUnmute(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("unmute")) {
			if (args.length != 0) {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					p.sendMessage(prefix + ChatColor.RED + "Please Choose Someone Online.");
					return true;
				}
				String unmutetoPlayer = Messages.unmuteToPlayer.replace("{name}", p.getName()).replace("{target}",
						target.getName());
				String unmutetoStaff = Messages.unmuteToStaff.replace("{name}", p.getName()).replace("{target}",
						target.getName());

				File playerFile = plugin.getPlayerFile(target.getUniqueId());
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

				if (playerData.get("MuteSystem.muted").equals("True")) {
					playerData.set("MuteSystem.muted", "False");
					p.sendMessage(prefix + unmutetoStaff);
					target.sendMessage(prefix + unmutetoPlayer);

					try {
						playerData.save(playerFile);

					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} else {
					p.sendMessage(prefix + ChatColor.RED + "That person is not muted.");
				}

			} else {
				p.sendMessage(prefix + ChatColor.RED + "Incorrect Arguments! Usage: " + ChatColor.DARK_RED
						+ "/unmute {player}");
			}
		}

		return true;
	}
}
