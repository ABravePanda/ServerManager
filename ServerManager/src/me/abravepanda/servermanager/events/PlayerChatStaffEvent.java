package me.abravepanda.servermanager.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.abravepanda.servermanager.Main;
import me.abravepanda.servermanager.commands.Permissions.Permissions;
import me.abravepanda.servermanager.messages.Messages;

public class PlayerChatStaffEvent implements Listener {

	String prefix = Main.PREFIX;
	String warnMessage = Messages.warnMessage;

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String d = p.getDisplayName();
		String m = e.getMessage();
		if (m.length() > 8) {
			String correct = m.substring(0, 9);

			if (e.getMessage().contains("StaffChat") && correct.equalsIgnoreCase("StaffChat")) {
				if (p.hasPermission(Permissions.staffChatSend)) {
					e.setCancelled(true);
					if (m.length() > 8) {
						String pm = m.substring(10, m.length());
						for (Player ps : Bukkit.getOnlinePlayers()) {
							if (ps.hasPermission(Permissions.staffChatRecieve)) {

								String format = Messages.staffChatFormat.replace("{name}", d).replace("{message}", pm);

								ps.sendMessage(prefix + format);

							} else {
							}
						}

					}
				} else {
					e.setCancelled(true);
					p.sendMessage(prefix + Messages.NoPermission);
				}
			}
			if (e.getMessage().contains("StaffHelp") && correct.equalsIgnoreCase("StaffHelp")) {
				if (m.length() > 8) {
					String pm = m.substring(10, m.length());
					for (Player ps : Bukkit.getOnlinePlayers()) {
						if (ps.hasPermission(Permissions.staffChatRecieve)) {

							String stm = Messages.staffHelpFormat.replace("{name}", d).replace("{message}", pm);
							ps.sendMessage(prefix + stm);
						}
					}
					e.setCancelled(true);

					String stm = Messages.staffHelpSuccess.replace("{name}", d);
					p.sendMessage(prefix + stm);
				}
			}
		}
	}
}
