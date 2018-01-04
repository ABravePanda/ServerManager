package me.abravepanda.servermanager.commands;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.configmethods.Config;
import me.abravepanda.servermanager.messages.Messages;

public class CommandMessage implements CommandExecutor {

	String prefix = Messages.Prefix;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("msg")) {
			if (args.length < 2) {
				sender.sendMessage(prefix + ChatColor.RED + "Usage: /msg <player> <msg>");
				return true;
			} else if (args.length >= 2) {
				Player target = Bukkit.getPlayer(args[0]);
				String message = StringUtils.join(args, ' ', 1, args.length);
				if (target != null) {

					String msgformatto = Messages.MessageToTarget.replace("{fromname}", sender.getName())
							.replace("{toname}", target.getName()).replace("{message}", message);
					String msgformatfrom = Messages.MessageToSender.replace("{fromname}", sender.getName())
							.replace("{toname}", target.getName()).replace("{message}", message);
					String msgformattoadmin = Messages.MessageToAdmin.replace("{fromname}", sender.getName())
							.replace("{toname}", target.getName()).replace("{message}", message);

					target.sendMessage(prefix + msgformatto);
					sender.sendMessage(prefix + msgformatfrom);

					for (Player ps : Bukkit.getOnlinePlayers()) {
						File playerFile = Main.instance.getPlayerFile(ps.getUniqueId());
						FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
						if (playerData.getString("MessagePeek.Enabled").equalsIgnoreCase("True")) {
							ps.sendMessage(prefix + msgformattoadmin);
						}
					}

				} else {
					sender.sendMessage(prefix + Messages.NotOnlineMessage.replace("{name}", sender.getName())
							.replace("{toname}", args[0]));
				}
			}
		}

		return true;
	}

}
