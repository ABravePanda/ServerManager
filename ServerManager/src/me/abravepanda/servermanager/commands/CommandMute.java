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

public class CommandMute implements CommandExecutor {

	String prefix = Messages.Prefix;
	private Main plugin;

	public CommandMute(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("mute")) {
			if (args.length != 0) {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					p.sendMessage(prefix + ChatColor.RED + "Please Choose Someone Online.");
					return true;
				}
				String mutetoPlayer = Messages.muteToPlayer.replace("{name}", p.getName()).replace("{target}",
						target.getName());
				String mutetoStaff = Messages.muteToStaff.replace("{name}", p.getName()).replace("{target}",
						target.getName());

				File playerFile = plugin.getPlayerFile(target.getUniqueId());
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

				if (playerData.get("MuteSystem.muted").equals("False")) {
					playerData.set("MuteSystem.muted", "True");
					playerData.set("MuteSystem.mutedcount", playerData.getInt("MuteSystem.mutedcount") + 1);
					p.sendMessage(prefix + mutetoStaff);
					target.sendMessage(prefix + mutetoPlayer);

					try {
						playerData.save(playerFile);

					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} else {
					p.sendMessage(prefix + ChatColor.RED + "That person is already muted.");
				}

			} else {
				p.sendMessage(prefix + ChatColor.RED + "Incorrect Arguments! Usage: " + ChatColor.DARK_RED
						+ "/mute {player}");
			}
		}

		return true;
	}

}
