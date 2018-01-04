package me.abravepanda.servermanager.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TabCompleteEvent implements Listener {

	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ServerManager") && args.length == 1) {
			if (sender instanceof Player) {

				List<String> subList = new ArrayList<>();
				Player p = (Player) sender;
				subList.add("help");
				subList.add("version");
				if (p.hasPermission("gui.ServerManager")) {
					subList.add("menu");
				}
				subList.add("info");
				if (p.hasPermission("reload.ServerManager")) {
					subList.add("reload");
				}
				return subList;
			}
		}
		return null;
	}

}
