package me.abravepanda.servermanager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.abravepanda.servermanager.messages.Messages;

public class CommandSelfChatClear implements CommandExecutor {

	String prefix = Messages.Prefix;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("scc")) {
			for (int i = 0; i <= 250; i++) {
				p.sendMessage("");
			}
			p.sendMessage(prefix + Messages.SelfToSender.replace("{name}", p.getName()));
		}

		return true;
	}
}
